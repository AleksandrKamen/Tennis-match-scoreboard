package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import matches.dto.ReadMatchesDto;
import matches.service.MatchesService;
import service.FinishedMatchesPersistenceService;
import util.JSPUtil;
import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {
   private final FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ReadMatchesDto> allMatches;
        Integer matchesByCriterion;
        var parameter = req.getParameter("page");
        var page = parameter == null||Integer.parseInt(parameter)<1?1:Integer.parseInt(parameter);
        var filterByPlayerName = req.getParameter("filter_by_player_name");

        if (filterByPlayerName == null || filterByPlayerName.isEmpty()){
            allMatches = finishedMatchesPersistenceService.findAllMatchesWithPagination(page);
            matchesByCriterion = finishedMatchesPersistenceService.findAllMatches().size();
        } else {
            allMatches = finishedMatchesPersistenceService.findAllMatchesByPlayerNameWithPagination(filterByPlayerName, page);
            req.setAttribute("filter_by_player_name",filterByPlayerName);
            matchesByCriterion = finishedMatchesPersistenceService.findAllMatchesByPlayerName(filterByPlayerName).size();
        }

        req.setAttribute("matches", allMatches);
        req.setAttribute("lastPage", getLastPage(matchesByCriterion));
        req.setAttribute("page", page);
        req.getRequestDispatcher(JSPUtil.getPath("matches")).forward(req, resp);
        
    }
    private Integer getLastPage(int matchesByCriterion){
        return (int) (Math.ceil(matchesByCriterion/(MatchesService.getMATCH_LIMIT()*1.0)));
    }
    
}
