package com.http.servlet;

import com.http.util.UrlPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(UrlPath.LOCALE)
public class LocaleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lang = req.getParameter("lang");
        req.getSession().setAttribute("lang", lang);

        String referer = req.getHeader("referer");
        String page = referer != null ? referer : UrlPath.LOGIN;
        resp.sendRedirect(referer + "?lang=" + lang);
    }
}
