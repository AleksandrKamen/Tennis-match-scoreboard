package servlets;

import current_matches.score.MatchState;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.FinishedMatchesPersistenceService;
import service.MatchScoreCalculationService;
import service.OngoingMatchesService;
import util.JSPUtil;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {
    private UUID uuid = null;
    OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();
    MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService();
    FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var uuid = req.getParameter("uuid");
        if (uuid != null && ongoingMatchesService.containsMatch(UUID.fromString(uuid))) {
            this.uuid = UUID.fromString(uuid);
            var match = ongoingMatchesService.getMatch(this.uuid).get();
            req.setAttribute("match", match);
            req.getRequestDispatcher(JSPUtil.getPath("match-score")).forward(req, resp);
        } else {
            resp.sendRedirect("/new-match");
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var parameter = req.getParameter("player");
        int plyerNumber = Integer.parseInt(parameter);
        var matchState = matchScoreCalculationService.updateScore(uuid, plyerNumber);
        if (matchState == MatchState.NOT_OVER){
            resp.sendRedirect("/match-score?uuid=" + uuid);
        } else {
            var currentMatches = ongoingMatchesService.getMatch(uuid).get();
            req.getSession().setAttribute("winner",currentMatches.getWinner().getName());
            req.getSession().setAttribute("player1",currentMatches.getPlayer1().getName());
            req.getSession().setAttribute("player2",currentMatches.getPlayer2().getName());
            req.getSession().setAttribute("playerScore1",currentMatches.getScore().getPlayerScore(0).getValue());
            req.getSession().setAttribute("playerScore2",currentMatches.getScore().getPlayerScore(1).getValue());

            finishedMatchesPersistenceService.finishMatch(currentMatches);
            ongoingMatchesService.removeMatch(uuid);
            resp.sendRedirect("matchEnd");
        }


    }
}
