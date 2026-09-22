
package com.studysync.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.studysync.DAO.ProgressDAO;
import com.studysync.DAO.SubjectDAO;
import com.studysync.DAOImpl.ProgressDAOImpl;
import com.studysync.DAOImpl.SubjectDAOImpl;
import com.studysync.model.Subject;
import com.studysync.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/progress")
public class ProgressServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null ||
            session.getAttribute("user") == null) {

            response.sendRedirect("login.html");
            return;
        }

        User user = (User) session.getAttribute("user");

        int userId = user.getId();

        ProgressDAO progressDAO =
                new ProgressDAOImpl();

        SubjectDAO subjectDAO =
                new SubjectDAOImpl();

        List<Subject> subjectList =
                subjectDAO.getAllSubjects();

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");

        out.println("<meta charset='UTF-8'>");

        out.println("<meta name='viewport' "
                + "content='width=device-width, initial-scale=1.0'>");

        out.println("<title>StudySync - Progress</title>");

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
        out.println("    color: #333;");
        out.println("}");

        /* NAVBAR */

        out.println(".navbar {");
        out.println("    background: linear-gradient(135deg, #667eea, #764ba2);");
        out.println("    color: white;");
        out.println("    padding: 20px 50px;");
        out.println("    display: flex;");
        out.println("    justify-content: space-between;");
        out.println("    align-items: center;");
        out.println("    box-shadow: 0 4px 15px rgba(0,0,0,0.15);");
        out.println("}");

        out.println(".navbar h1 {");
        out.println("    font-size: 25px;");
        out.println("}");

        out.println(".nav-buttons {");
        out.println("    display: flex;");
        out.println("    gap: 12px;");
        out.println("    flex-wrap: wrap;");
        out.println("}");

        out.println(".nav-button {");
        out.println("    text-decoration: none;");
        out.println("    color: white;");
        out.println("    background: rgba(255,255,255,0.18);");
        out.println("    padding: 10px 18px;");
        out.println("    border-radius: 10px;");
        out.println("    font-weight: bold;");
        out.println("}");

        out.println(".nav-button:hover {");
        out.println("    background: rgba(255,255,255,0.30);");
        out.println("}");

        /* CONTAINER */

        out.println(".container {");
        out.println("    width: 90%;");
        out.println("    max-width: 1100px;");
        out.println("    margin: 40px auto;");
        out.println("}");

        out.println(".heading {");
        out.println("    text-align: center;");
        out.println("    margin-bottom: 35px;");
        out.println("}");

        out.println(".heading h2 {");
        out.println("    font-size: 32px;");
        out.println("    color: #4c1d95;");
        out.println("    margin-bottom: 10px;");
        out.println("}");

        out.println(".heading p {");
        out.println("    color: #666;");
        out.println("    font-size: 16px;");
        out.println("}");

        /* PROGRESS GRID */

        out.println(".progress-grid {");
        out.println("    display: grid;");
        out.println("    grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));");
        out.println("    gap: 25px;");
        out.println("}");

        /* PROGRESS CARD */

        out.println(".progress-card {");
        out.println("    background: white;");
        out.println("    border-radius: 18px;");
        out.println("    padding: 25px;");
        out.println("    box-shadow: 0 10px 25px rgba(0,0,0,0.08);");
        out.println("    border-top: 5px solid #667eea;");
        out.println("    transition: transform 0.2s;");
        out.println("}");

        out.println(".progress-card:hover {");
        out.println("    transform: translateY(-5px);");
        out.println("}");

        out.println(".subject-name {");
        out.println("    font-size: 21px;");
        out.println("    font-weight: bold;");
        out.println("    color: #4c1d95;");
        out.println("    margin-bottom: 15px;");
        out.println("}");

        out.println(".task-info {");
        out.println("    display: flex;");
        out.println("    justify-content: space-between;");
        out.println("    align-items: center;");
        out.println("    margin-bottom: 12px;");
        out.println("    font-size: 14px;");
        out.println("    color: #666;");
        out.println("    gap: 10px;");
        out.println("}");

        out.println(".percentage {");
        out.println("    font-size: 24px;");
        out.println("    font-weight: bold;");
        out.println("    color: #667eea;");
        out.println("}");

        /* PROGRESS BAR */

        out.println(".progress-bar {");
        out.println("    width: 100%;");
        out.println("    height: 14px;");
        out.println("    background: #eee;");
        out.println("    border-radius: 20px;");
        out.println("    overflow: hidden;");
        out.println("}");

        out.println(".progress-fill {");
        out.println("    height: 100%;");
        out.println("    background: linear-gradient(90deg, #667eea, #764ba2);");
        out.println("    border-radius: 20px;");
        out.println("}");

        /* EMPTY */

        out.println(".empty {");
        out.println("    background: white;");
        out.println("    padding: 40px;");
        out.println("    text-align: center;");
        out.println("    border-radius: 18px;");
        out.println("    box-shadow: 0 10px 25px rgba(0,0,0,0.08);");
        out.println("    color: #666;");
        out.println("}");

        /* RESPONSIVE */

        out.println("@media(max-width: 800px) {");

        out.println("    .navbar {");
        out.println("        padding: 15px 20px;");
        out.println("        flex-direction: column;");
        out.println("        gap: 15px;");
        out.println("    }");

        out.println("}");

        out.println("</style>");

        out.println("</head>");

        out.println("<body>");

        /* NAVBAR */

        out.println("<div class='navbar'>");

        out.println("<h1>StudySync</h1>");

        out.println("<div class='nav-buttons'>");

        out.println("<a class='nav-button' href='dashboard'>");
        out.println("Dashboard");
        out.println("</a>");

        out.println("<a class='nav-button' href='tasks'>");
        out.println("Tasks");
        out.println("</a>");

        out.println("<a class='nav-button' href='study-plans'>");
        out.println("Study Plans");
        out.println("</a>");

        out.println("<a class='nav-button' href='study-sessions'>");
        out.println("Study Sessions");
        out.println("</a>");

        out.println("</div>");

        out.println("</div>");

        /* MAIN CONTAINER */

        out.println("<div class='container'>");

        out.println("<div class='heading'>");

        out.println("<h2>My Learning Progress</h2>");

        out.println("<p>");
        out.println("Track your subject-wise learning progress.");
        out.println("</p>");

        out.println("</div>");

        if (subjectList.isEmpty()) {

            out.println("<div class='empty'>");

            out.println("<h3>No Subjects Available</h3>");

            out.println("<p>");
            out.println("Add subjects to start tracking your progress.");
            out.println("</p>");

            out.println("</div>");

        } else {

            out.println("<div class='progress-grid'>");

            for (Subject subject : subjectList) {

                int totalTasks =
                        progressDAO.getTotalTasks(
                                userId,
                                subject.getId()
                        );

                int completedTasks =
                        progressDAO.getCompletedTasks(
                                userId,
                                subject.getId()
                        );

                double percentage = 0;

                if (totalTasks > 0) {

                    percentage =
                            (completedTasks * 100.0)
                            / totalTasks;
                }

                out.println("<div class='progress-card'>");

                out.println("<div class='subject-name'>");

                out.println(subject.getName());

                out.println("</div>");

                out.println("<div class='task-info'>");

                out.println("<span>");

                out.println(
                        completedTasks
                        + " / "
                        + totalTasks
                        + " Tasks Completed"
                );

                out.println("</span>");

                out.println("<span class='percentage'>");

                out.println(
                        String.format("%.0f", percentage)
                        + "%"
                );

                out.println("</span>");

                out.println("</div>");

                out.println("<div class='progress-bar'>");

                out.println(
                        "<div class='progress-fill' style='width:"
                        + percentage
                        + "%;'></div>"
                );

                out.println("</div>");

                out.println("</div>");
            }

            out.println("</div>");
        }

        out.println("</div>");

        out.println("</body>");

        out.println("</html>");
    }
}

