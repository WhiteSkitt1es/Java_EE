package com.http.servlet;

import com.http.service.FlightService;
import com.http.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet("/flights")
public class FlightServlet extends HttpServlet {

    private final FlightService flightService = FlightService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("flights", flightService.findAll());

        req.getRequestDispatcher(JspHelper.getPath("flights"))
                        .forward(req, resp);

//        resp.setContentType("text/html");
//        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
//
//        try (PrintWriter printWriter = resp.getWriter()) {
//            printWriter.write("<h1>Список перелетов</h1>");
//            printWriter.write("<ul>");
//            flightService.findAll().forEach(flightDto -> {
//                printWriter.write("""
//                        <li>
//                            <a href="/tickets?flightId=%d">%s</a>
//                        </li>
//                        """.formatted(flightDto.getId(), flightDto.getDescription()));
//            });
//            printWriter.write("</ul>");
//        }
    }
}
