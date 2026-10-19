package com.x0j3m.web.servlet;

import com.x0j3m.model.dto.user.UserRequestDto;
import com.x0j3m.model.dto.user.UserResponseDto;
import com.x0j3m.service.ImageService;
import com.x0j3m.service.UserService;
import com.x0j3m.service.util.ImageMediaTypeDetector;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.HttpHeaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@WebServlet("/api/servlet/users/*")
public class UserServlet extends HttpServlet {
    private final UserService userService;
    private final ImageService imageService;
    private final Jsonb jsonb = JsonbBuilder.create();

    @Inject
    public UserServlet(UserService userService, ImageService imageService) {
        this.userService = userService;
        this.imageService = imageService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            List<UserResponseDto> users = userService.fetchAll();
            response.getWriter().write(jsonb.toJson(users));
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        String[] splits = pathInfo.split("/");

        UUID userId;
        try {
            userId = UUID.fromString(splits[1]);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid UUID format");
            return;
        }

        if (splits.length == 3 && splits[2].equals("image")) {
            if (userService.existsById(userId)) {
                byte[] picture = imageService.fetchImage(userId.toString());
                if(picture.length == 0){
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
                    return;
                }
                response.setContentType(ImageMediaTypeDetector.detect(picture));
                response.getOutputStream().write(picture);
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
            }
        } else {
            if (userService.existsById(userId)) {
                UserResponseDto userDto = userService.fetchUserById(userId);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write(jsonb.toJson(userDto));
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            try (BufferedReader reader = request.getReader()) {
                String body = reader.lines().collect(Collectors.joining(System.lineSeparator()));
                UserRequestDto userDto = jsonb.fromJson(body, UserRequestDto.class);
                String userId = userService.createUser(userDto);
                response.setStatus(HttpServletResponse.SC_CREATED);
                response.setHeader(HttpHeaders.LOCATION, "/api/users/" + userId);
                return;
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid request body");
                return;
            }
        }

        String[] splits = pathInfo.split("/");

        UUID userId;
        try {
            userId = UUID.fromString(splits[1]);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid UUID format");
            return;
        }

        if (splits.length != 3 || splits[2].isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid resource path format");
            return;
        }
        if(!userService.existsById(userId)){
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
            return;
        }

        if (splits[2].equals("image")) {
            imageService.createImage(userId.toString(), request.getInputStream());
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.setHeader(HttpHeaders.LOCATION, "/api/users/" + userId + "/image");
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid resource path format");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid resource path format");
            return;
        }
        String[] splits = pathInfo.split("/");

        UUID userId;
        try {
            userId = UUID.fromString(splits[1]);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid UUID format");
            return;
        }

        if (splits.length == 3 && splits[2].equals("image")) {
            if(!userService.existsById(userId)){
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
                return;
            }
            imageService.createImage(userId.toString(), request.getInputStream());
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.setHeader(HttpHeaders.LOCATION, "/api/users/" + userId + "/image");
            return;
        }

        try (BufferedReader reader = request.getReader()) {
            String body = reader.lines().collect(Collectors.joining(System.lineSeparator()));
            UserRequestDto userDto = jsonb.fromJson(body, UserRequestDto.class);
            userService.updateUserById(userId, userDto);
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.setHeader(HttpHeaders.LOCATION, "/api/users/" + userId);
        }catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid request body");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String[] splits = pathInfo.split("/");

        UUID userId;
        try {
            userId = UUID.fromString(splits[1]);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid UUID format");
            return;
        }

        if (splits.length == 3) {
            imageService.deleteImage(userId.toString());
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else if (splits.length == 2) {
            if (!userService.existsById(userId)) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
                return;
            }

            userService.deleteUserById(userId);
            imageService.deleteImage(userId.toString());
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid resource path format");
        }
    }
}
