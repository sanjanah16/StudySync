
package com.studysync.servlet;

import java.io.IOException;

import com.studysync.DAO.StudyPlanDAO;
import com.studysync.DAOImpl.StudyPlanDAOImpl;
import com.studysync.model.StudyPlan;
import com.studysync.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/delete-study-plan")
public class DeleteStudyPlanServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.html");
            return;
        }

        User user = (User) session.getAttribute("user");

        String idParam = request.getParameter("id");

        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect("study-plans");
            return;
        }

        int id = Integer.parseInt(idParam);

        StudyPlanDAO studyPlanDAO =
                new StudyPlanDAOImpl();

        StudyPlan studyPlan =
                studyPlanDAO.getStudyPlan(id);

        if (studyPlan == null) {
            response.sendRedirect("study-plans");
            return;
        }

        if (studyPlan.getUserId() != user.getId()) {
            response.sendRedirect("study-plans");
            return;
        }

        studyPlanDAO.deleteStudyPlan(id);

        response.sendRedirect("study-plans");
    }
}

