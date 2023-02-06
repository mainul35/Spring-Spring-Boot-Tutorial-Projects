package com.mainul35.jakartaeeservletdemo;

import com.mainul35.jakartaeeservletdemo.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "registrationServlet", value = "/registration")
public class RegistrationServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("registration.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var user = new User();
        user.setFirstName(request.getParameter("first_name"));
        user.setLastName(request.getParameter("last_name"));

        request.setAttribute("user", user);
        request.getRequestDispatcher("registration-success.jsp").forward(request, response);
    }
}
