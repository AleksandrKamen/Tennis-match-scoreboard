package servlets;

import current_matches.CurrentMatches;
import exception.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.OngoingMatchesService;
import util.JSPUtil;

import java.io.IOException;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet { // Страница нового матча
    private static final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JSPUtil.getPath("newMatch")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var playerName1 = req.getParameter("playerName1");
        var playerName2 = req.getParameter("playerName2");

        try {
            var currentMatches = ongoingMatchesService.creatNewMatch(playerName1, playerName2);
            resp.sendRedirect("/match-score?uuid="+currentMatches.getUuid());
        } catch (ValidationException validationException){
            req.setAttribute("errors",validationException.getErrors());
            doGet(req,resp);
        }

    }
}
