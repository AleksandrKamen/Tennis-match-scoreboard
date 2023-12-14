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
        req.setAttribute("winner", req.getSession().getAttribute("winner"));
        req.setAttribute("player1", req.getSession().getAttribute("player1"));
        req.setAttribute("player2", req.getSession().getAttribute("player2"));
        req.setAttribute("playerScore1", req.getSession().getAttribute("playerScore1"));
        req.setAttribute("playerScore2", req.getSession().getAttribute("playerScore2"));
        req.getRequestDispatcher(JSPUtil.getPath("matchEnd")).forward(req, resp);
    }
}
