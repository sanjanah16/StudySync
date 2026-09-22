
package com.studysync.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import com.studysync.DAO.StudyPlanDAO;
import com.studysync.DAO.SubjectDAO;
import com.studysync.DAOImpl.StudyPlanDAOImpl;
import com.studysync.DAOImpl.SubjectDAOImpl;
import com.studysync.model.StudyPlan;
import com.studysync.model.Subject;
import com.studysync.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/edit-study-plan")
public class EditStudyPlanServlet extends HttpServlet {

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

        SubjectDAO subjectDAO =
                new SubjectDAOImpl();

        List<Subject> subjects =
                subjectDAO.getAllSubjects();

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");

        out.println("<meta charset='UTF-8'>");

        out.println("<meta name='viewport' "
                + "content='width=device-width, initial-scale=1.0'>");

        out.println("<title>StudySync - Edit Study Plan</title>");

        out.println("<style>");

        out.println("* {");
        out.println("    box-sizing: border-box;");
        out.println("    margin: 0;");
        out.println("    padding: 0;");
        out.println("}");

        out.println("body {");
        out.println("    font-family: Arial, sans-serif;");
        out.println("    background: linear-gradient(135deg, #eef2ff, #fdf2f8);");
        out.println("    min-height: 100vh;");
        out.println("}");

        out.println(".navbar {");
        out.println("    background: linear-gradient(135deg, #667eea, #764ba2);");
        out.println("    padding: 18px 40px;");
        out.println("    color: white;");
        out.println("    display: flex;");
        out.println("    justify-content: space-between;");
        out.println("    align-items: center;");
        out.println("}");

        out.println(".logo {");
        out.println("    font-size: 25px;");
        out.println("    font-weight: bold;");
        out.println("}");

        out.println(".back {");
        out.println("    color: white;");
        out.println("    text-decoration: none;");
        out.println("    padding: 9px 15px;");
        out.println("    border-radius: 20px;");
        out.println("    background: rgba(255,255,255,0.2);");
        out.println("}");

        out.println(".container {");
        out.println("    width: 90%;");
        out.println("    max-width: 650px;");
        out.println("    margin: 40px auto;");
        out.println("}");

        out.println(".form-card {");
        out.println("    background: white;");
        out.println("    padding: 35px;");
        out.println("    border-radius: 20px;");
        out.println("    box-shadow: 0 10px 30px rgba(0,0,0,0.10);");
        out.println("}");

        out.println("h1 {");
        out.println("    color: #333;");
        out.println("    margin-bottom: 8px;");
        out.println("}");

        out.println(".subtitle {");
        out.println("    color: #777;");
        out.println("    margin-bottom: 25px;");
        out.println("}");

        out.println("label {");
        out.println("    display: block;");
        out.println("    margin-bottom: 7px;");
        out.println("    color: #444;");
        out.println("    font-weight: bold;");
        out.println("}");

        out.println("input, textarea, select {");
        out.println("    width: 100%;");
        out.println("    padding: 12px;");
        out.println("    border: 1px solid #ddd;");
        out.println("    border-radius: 9px;");
        out.println("    margin-bottom: 18px;");
        out.println("    font-size: 14px;");
        out.println("}");

        out.println("textarea {");
        out.println("    min-height: 100px;");
        out.println("    resize: vertical;");
        out.println("}");

        out.println(".submit-button {");
        out.println("    width: 100%;");
        out.println("    border: none;");
        out.println("    padding: 13px;");
        out.println("    border-radius: 10px;");
        out.println("    background: linear-gradient(135deg, #667eea, #764ba2);");
        out.println("    color: white;");
        out.println("    font-size: 15px;");
        out.println("    font-weight: bold;");
        out.println("    cursor: pointer;");
        out.println("}");

        out.println(".submit-button:hover {");
        out.println("    opacity: 0.9;");
        out.println("}");

        out.println("</style>");

        out.println("</head>");

        out.println("<body>");

        out.println("<div class='navbar'>");

        out.println("<div class='logo'>StudySync</div>");

        out.println("<a class='back' href='study-plans'>");
        out.println("Back to Study Plans");
        out.println("</a>");

        out.println("</div>");

        out.println("<div class='container'>");

        out.println("<div class='form-card'>");

        out.println("<h1>Edit Study Plan</h1>");

        out.println("<p class='subtitle'>");
        out.println("Update your study plan details.");
        out.println("</p>");

        out.println("<form action='edit-study-plan' method='post'>");

        out.println("<input type='hidden' "
                + "name='id' "
                + "value='" + studyPlan.getId() + "'>");

        out.println("<label>Plan Title</label>");

        out.println("<input type='text' "
                + "name='title' "
                + "value='" + studyPlan.getTitle() + "' "
                + "required>");

        out.println("<label>Description</label>");

        out.println("<textarea name='description' required>"
                + studyPlan.getDescription()
                + "</textarea>");

        out.println("<label>Subject</label>");

        out.println("<select name='subjectId' required>");

        for (Subject subject : subjects) {

            String selected = "";

            if (subject.getId() == studyPlan.getSubjectId()) {
                selected = " selected";
            }

            out.println("<option value='"
                    + subject.getId()
                    + "'"
                    + selected
                    + ">");

            out.println(subject.getName());

            out.println("</option>");
        }

        out.println("</select>");

        out.println("<label>Start Date</label>");

        out.println("<input type='date' "
                + "name='startDate' "
                + "value='" + studyPlan.getStartDate() + "' "
                + "required>");

        out.println("<label>End Date</label>");

        out.println("<input type='date' "
                + "name='endDate' "
                + "value='" + studyPlan.getEndDate() + "' "
                + "required>");

        out.println("<label>Status</label>");

        out.println("<select name='status' required>");

        String plannedSelected = "";
        String progressSelected = "";
        String completedSelected = "";

        if ("PLANNED".equalsIgnoreCase(studyPlan.getStatus())) {
            plannedSelected = " selected";
        } else if ("IN_PROGRESS".equalsIgnoreCase(studyPlan.getStatus())) {
            progressSelected = " selected";
        } else if ("COMPLETED".equalsIgnoreCase(studyPlan.getStatus())) {
            completedSelected = " selected";
        }

        out.println("<option value='PLANNED'"
                + plannedSelected
                + ">PLANNED</option>");

        out.println("<option value='IN_PROGRESS'"
                + progressSelected
                + ">IN PROGRESS</option>");

        out.println("<option value='COMPLETED'"
                + completedSelected
                + ">COMPLETED</option>");

        out.println("</select>");

        out.println("<button class='submit-button' type='submit'>");
        out.println("Update Study Plan");
        out.println("</button>");

        out.println("</form>");

        out.println("</div>");

        out.println("</div>");

        out.println("</body>");

        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
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

        StudyPlan oldPlan =
                studyPlanDAO.getStudyPlan(id);

        if (oldPlan == null) {
            response.sendRedirect("study-plans");
            return;
        }

        if (oldPlan.getUserId() != user.getId()) {
            response.sendRedirect("study-plans");
            return;
        }

        String title =
                request.getParameter("title");

        String description =
                request.getParameter("description");

        String subjectIdString =
                request.getParameter("subjectId");

        String startDateString =
                request.getParameter("startDate");

        String endDateString =
                request.getParameter("endDate");

        String status =
                request.getParameter("status");

        int subjectId =
                Integer.parseInt(subjectIdString);

        Date startDate =
                Date.valueOf(startDateString);

        Date endDate =
                Date.valueOf(endDateString);

        StudyPlan updatedPlan = new StudyPlan(
                id,
                user.getId(),
                subjectId,
                title,
                description,
                startDate,
                endDate,
                status,
                oldPlan.getCreatedAt()
        );

        studyPlanDAO.updateStudyPlan(updatedPlan);

        response.sendRedirect("study-plans");
    }
}

