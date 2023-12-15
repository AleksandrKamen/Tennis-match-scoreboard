package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.JSPUtil;

import java.io.IOException;

@WebServlet("/matchEnd")
public class MatchEndServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var winner = req.getSession().getAttribute("winner");
        var player1 = req.getSession().getAttribute("player1");
        var player2 = req.getSession().getAttribute("player2");
        var playerScore1 = req.getSession().getAttribute("playerScore1");
        var playerScore2 = req.getSession().getAttribute("playerScore2");
        if (winner == null || player1 == null || player2 == null || playerScore1 == null || playerScore2 == null){
            resp.sendRedirect("/new-match");
        } else {
            req.setAttribute("winner", winner);
            req.setAttribute("player1", player1);
            req.setAttribute("player2", player2);
            req.setAttribute("playerScore1", playerScore1);
            req.setAttribute("playerScore2", playerScore2);
            req.getRequestDispatcher(JSPUtil.getPath("matchEnd")).forward(req, resp);
        }
    }
}
