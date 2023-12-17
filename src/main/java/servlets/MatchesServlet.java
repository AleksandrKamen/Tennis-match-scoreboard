package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import matches.dto.ReadMatchesDto;
import service.FinishedMatchesPersistenceService;
import util.JSPUtil;
import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {
    FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ReadMatchesDto> allMatches;
        Integer lastPage;
        var parameter = req.getParameter("page");
        var page = parameter == null||Integer.parseInt(parameter)<1?1:Integer.parseInt(parameter);
        var filterByPlayerName = req.getParameter("filter_by_player_name");

        if (filterByPlayerName == null || filterByPlayerName.isEmpty()){
            allMatches = finishedMatchesPersistenceService.findAllMatchesWithPagination(page);
            lastPage = finishedMatchesPersistenceService.findAllMatches().size();
        } else {
            allMatches = finishedMatchesPersistenceService.findAllMatchesByPlayerNameWithPagination(filterByPlayerName, page);
            req.setAttribute("filter_by_player_name",filterByPlayerName);
            lastPage = finishedMatchesPersistenceService.findAllMatchesByPlayerName(filterByPlayerName).size();
        }

        req.setAttribute("matches", allMatches);
        req.setAttribute("lastPage", (int) (Math.ceil(lastPage/7.0)));
        req.setAttribute("page", page);
        req.getRequestDispatcher(JSPUtil.getPath("matches")).forward(req, resp);


    }
}
