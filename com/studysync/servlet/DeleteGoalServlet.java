
package com.studysync.servlet;

import java.io.IOException;

import com.studysync.DAO.GoalDAO;
import com.studysync.DAOImpl.GoalDAOImpl;
import com.studysync.model.Goal;
import com.studysync.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/delete-goal")
public class DeleteGoalServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // Check login session
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.html");
            return;
        }

        // Get logged-in user
        User user = (User) session.getAttribute("user");

        // Get goal ID
        String idParam = request.getParameter("id");

        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect("goals");
            return;
        }

        int goalId = Integer.parseInt(idParam);

        // Create DAO
        GoalDAO goalDAO = new GoalDAOImpl();

        // Get goal
        Goal goal = goalDAO.getGoal(goalId);

        // Check whether goal exists
        if (goal == null) {
            response.sendRedirect("goals");
            return;
        }

        // Make sure goal belongs to logged-in user
        if (goal.getUserId() != user.getId()) {
            response.sendRedirect("goals");
            return;
        }

        // Delete goal
        goalDAO.deleteGoal(goalId);

        // Return to Goals page
        response.sendRedirect("goals");
    }
}

