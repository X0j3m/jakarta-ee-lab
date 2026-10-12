package com.x0j3m.rest.servlet;

import com.x0j3m.model.dto.UserRequestDto;
import com.x0j3m.model.dto.UserResponseDto;
import com.x0j3m.model.entity.UserEntity;
import com.x0j3m.model.mapper.UserMapper;
import com.x0j3m.service.UserService;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.HttpHeaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.UUID;
import java.util.stream.Collectors;

@WebServlet("/api/users/*")
public class UserServlet extends HttpServlet {
    private UserService userService;
    private final Jsonb jsonb = JsonbBuilder.create();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.userService = (UserService) config.getServletContext().getAttribute(UserService.class.getName());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (pathInfo == null || pathInfo.equals("/")) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(jsonb.toJson(userService.fetchAll()));
            return;
        }

        String[] splits = pathInfo.split("/");
        if (splits.length != 2) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid resource path format");
            return;
        }

        UUID userId;
        try {
            userId = UUID.fromString(splits[1]);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid UUID format");
            return;
        }

        if (userService.existsById(userId)) {
            UserResponseDto userDto = userService.fetchUserById(userId);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(jsonb.toJson(userDto));
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo != null || !pathInfo.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid resource path format");
            return;
        }

        try (BufferedReader reader = request.getReader()) {
            String body = reader.lines().collect(Collectors.joining(System.lineSeparator()));
            UserRequestDto userDto = jsonb.fromJson(body, UserRequestDto.class);
            String userId = userService.createUser(userDto);
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.setHeader(HttpHeaders.LOCATION, "/api/users/" + userId);
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
        if (splits.length != 2) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid resource path format");
            return;
        }

        UUID userId;
        try {
            userId = UUID.fromString(splits[1]);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid UUID format");
            return;
        }

        try (BufferedReader reader = request.getReader()) {
            String body = reader.lines().collect(Collectors.joining(System.lineSeparator()));
            UserRequestDto userDto = jsonb.fromJson(body, UserRequestDto.class);
            userService.updateUserById(userId, userDto);
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.setHeader(HttpHeaders.LOCATION, "/api/users/" + userId);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        String[] splits = pathInfo.split("/");
        if (splits.length != 2) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid resource path format");
            return;
        }

        UUID userId;
        try {
            userId = UUID.fromString(splits[1]);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid UUID format");
            return;
        }

        if (!userService.existsById(userId)) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
            return;
        }

        userService.deleteUserById(userId);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}
