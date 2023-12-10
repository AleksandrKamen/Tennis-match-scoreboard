package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.FinishedMatchesPersistenceService;
import util.JSPUtil;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {
    FinishedMatchesPersistenceService finishedMatchesPersistenceService = FinishedMatchesPersistenceService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       var parameter = req.getParameter("page");
       var page = parameter == null||Integer.parseInt(parameter)<1?1:Integer.parseInt(parameter);
       var filterByPlayerName = req.getParameter("filter_by_player_name");

        if (filterByPlayerName == null || filterByPlayerName.isEmpty()){
            req.setAttribute("matches", finishedMatchesPersistenceService.findAllMatches(page));
        } else {
            req.setAttribute("filter_by_player_name",filterByPlayerName);
            req.setAttribute("matches", finishedMatchesPersistenceService.findAllMatches(filterByPlayerName,page));
        }
        req.setAttribute("page", page);
        req.getRequestDispatcher(JSPUtil.getPath("matches")).forward(req, resp);


    }
}
