
package com.studysync.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

@WebServlet("/study-plans")
public class StudyPlanServlet extends HttpServlet {

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

        StudyPlanDAO studyPlanDAO =
                new StudyPlanDAOImpl();

        List<StudyPlan> studyPlans =
                studyPlanDAO.getStudyPlansByUserId(user.getId());

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");

        out.println("<meta charset='UTF-8'>");

        out.println("<meta name='viewport' "
                + "content='width=device-width, initial-scale=1.0'>");

        out.println("<title>StudySync - Study Plans</title>");

        out.println("<style>");

        /* ================= COMMON ================= */

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

        /* ================= NAVBAR ================= */

        out.println(".navbar {");
        out.println("    background: linear-gradient(135deg, #667eea, #764ba2);");
        out.println("    padding: 18px 40px;");
        out.println("    color: white;");
        out.println("    display: flex;");
        out.println("    justify-content: space-between;");
        out.println("    align-items: center;");
        out.println("    box-shadow: 0 4px 15px rgba(0,0,0,0.15);");
        out.println("}");

        out.println(".logo {");
        out.println("    font-size: 25px;");
        out.println("    font-weight: bold;");
        out.println("}");

        out.println(".nav-links {");
        out.println("    display: flex;");
        out.println("    gap: 10px;");
        out.println("    flex-wrap: wrap;");
        out.println("}");

        out.println(".nav-links a {");
        out.println("    color: white;");
        out.println("    text-decoration: none;");
        out.println("    padding: 9px 14px;");
        out.println("    border-radius: 20px;");
        out.println("    font-size: 14px;");
        out.println("}");

        out.println(".nav-links a:hover {");
        out.println("    background: rgba(255,255,255,0.2);");
        out.println("}");

        /* ================= CONTAINER ================= */

        out.println(".container {");
        out.println("    width: 90%;");
        out.println("    max-width: 1100px;");
        out.println("    margin: 40px auto;");
        out.println("}");

        out.println(".header {");
        out.println("    display: flex;");
        out.println("    justify-content: space-between;");
        out.println("    align-items: center;");
        out.println("    margin-bottom: 30px;");
        out.println("    gap: 20px;");
        out.println("}");

        out.println(".header h1 {");
        out.println("    color: #333;");
        out.println("    font-size: 32px;");
        out.println("}");

        out.println(".header p {");
        out.println("    color: #777;");
        out.println("    margin-top: 7px;");
        out.println("}");

        /* ================= ADD BUTTON ================= */

        out.println(".add-button {");
        out.println("    background: linear-gradient(135deg, #667eea, #764ba2);");
        out.println("    color: white;");
        out.println("    text-decoration: none;");
        out.println("    padding: 12px 20px;");
        out.println("    border-radius: 10px;");
        out.println("    font-weight: bold;");
        out.println("}");

        out.println(".add-button:hover {");
        out.println("    opacity: 0.85;");
        out.println("}");

        /* ================= PLAN GRID ================= */

        out.println(".plan-grid {");
        out.println("    display: grid;");
        out.println("    grid-template-columns: repeat(3, 1fr);");
        out.println("    gap: 25px;");
        out.println("}");

        /* ================= PLAN CARD ================= */

        out.println(".plan-card {");
        out.println("    background: white;");
        out.println("    padding: 25px;");
        out.println("    border-radius: 18px;");
        out.println("    box-shadow: 0 8px 20px rgba(0,0,0,0.08);");
        out.println("    border-top: 5px solid #667eea;");
        out.println("    transition: 0.3s;");
        out.println("}");

        out.println(".plan-card:hover {");
        out.println("    transform: translateY(-5px);");
        out.println("    box-shadow: 0 15px 30px rgba(0,0,0,0.12);");
        out.println("}");

        out.println(".plan-icon {");
        out.println("    font-size: 35px;");
        out.println("    margin-bottom: 12px;");
        out.println("}");

        out.println(".plan-card h2 {");
        out.println("    color: #667eea;");
        out.println("    margin-bottom: 10px;");
        out.println("}");

        out.println(".description {");
        out.println("    color: #666;");
        out.println("    line-height: 1.5;");
        out.println("    margin-bottom: 18px;");
        out.println("}");

        /* ================= DETAILS ================= */

        out.println(".details {");
        out.println("    border-top: 1px solid #eee;");
        out.println("    padding-top: 15px;");
        out.println("    line-height: 2;");
        out.println("    color: #555;");
        out.println("}");

        /* ================= STATUS ================= */

        out.println(".status {");
        out.println("    display: inline-block;");
        out.println("    padding: 4px 12px;");
        out.println("    border-radius: 15px;");
        out.println("    font-size: 12px;");
        out.println("    font-weight: bold;");
        out.println("}");

        out.println(".planned {");
        out.println("    background: #fff3cd;");
        out.println("    color: #856404;");
        out.println("}");

        out.println(".in-progress {");
        out.println("    background: #dbeafe;");
        out.println("    color: #1d4ed8;");
        out.println("}");

        out.println(".completed {");
        out.println("    background: #d1fae5;");
        out.println("    color: #047857;");
        out.println("}");

        /* ================= ACTION BUTTONS ================= */

        out.println(".actions {");
        out.println("    display: flex;");
        out.println("    gap: 10px;");
        out.println("    margin-top: 20px;");
        out.println("}");

        out.println(".edit-button {");
        out.println("    background: #667eea;");
        out.println("    color: white;");
        out.println("    text-decoration: none;");
        out.println("    padding: 9px 15px;");
        out.println("    border-radius: 8px;");
        out.println("    font-size: 13px;");
        out.println("    font-weight: bold;");
        out.println("}");

        out.println(".delete-button {");
        out.println("    background: #ff5c5c;");
        out.println("    color: white;");
        out.println("    text-decoration: none;");
        out.println("    padding: 9px 15px;");
        out.println("    border-radius: 8px;");
        out.println("    font-size: 13px;");
        out.println("    font-weight: bold;");
        out.println("}");

        out.println(".delete-button:hover {");
        out.println("    background: #e04444;");
        out.println("}");

        /* ================= EMPTY ================= */

        out.println(".empty {");
        out.println("    background: white;");
        out.println("    padding: 60px 20px;");
        out.println("    border-radius: 18px;");
        out.println("    text-align: center;");
        out.println("    box-shadow: 0 5px 18px rgba(0,0,0,0.08);");
        out.println("}");

        out.println(".empty h2 {");
        out.println("    color: #555;");
        out.println("    margin-bottom: 10px;");
        out.println("}");

        out.println(".empty p {");
        out.println("    color: #888;");
        out.println("}");

        /* ================= RESPONSIVE ================= */

        out.println("@media(max-width: 800px) {");

        out.println("    .plan-grid {");
        out.println("        grid-template-columns: 1fr;");
        out.println("    }");

        out.println("    .navbar {");
        out.println("        padding: 15px 20px;");
        out.println("        flex-direction: column;");
        out.println("        gap: 15px;");
        out.println("    }");

        out.println("    .header {");
        out.println("        flex-direction: column;");
        out.println("        align-items: flex-start;");
        out.println("    }");

        out.println("}");

        out.println("</style>");

        out.println("</head>");

        out.println("<body>");

        /* ================= NAVBAR ================= */

        out.println("<div class='navbar'>");

        out.println("<div class='logo'>StudySync</div>");

        out.println("<div class='nav-links'>");

        out.println("<a href='dashboard'>Dashboard</a>");
        out.println("<a href='subjects.html'>Subjects</a>");
        out.println("<a href='tasks'>Tasks</a>");
        out.println("<a href='study-sessions'>Study Sessions</a>");
        out.println("<a href='notes'>Notes</a>");
        out.println("<a href='goals'>Goals</a>");
        out.println("<a href='notifications'>Notifications</a>");

        out.println("</div>");

        out.println("</div>");

        /* ================= MAIN ================= */

        out.println("<div class='container'>");

        out.println("<div class='header'>");

        out.println("<div>");

        out.println("<h1>Study Plans</h1>");

        out.println("<p>");
        out.println("Organize your learning and achieve your study goals.");
        out.println("</p>");

        out.println("</div>");

        out.println("<a class='add-button' href='add-study-plan'>");
        out.println("+ Add Study Plan");
        out.println("</a>");

        out.println("</div>");

        /* ================= DISPLAY PLANS ================= */

        if (studyPlans.isEmpty()) {

            out.println("<div class='empty'>");

            out.println("<h2>No Study Plans Yet</h2>");

            out.println("<p>");
            out.println("Create your first study plan to organize your learning.");
            out.println("</p>");

            out.println("</div>");

        } else {

            out.println("<div class='plan-grid'>");

            for (StudyPlan plan : studyPlans) {

                out.println("<div class='plan-card'>");

                out.println("<div class='plan-icon'>Study</div>");

                out.println("<h2>");
                out.println(plan.getTitle());
                out.println("</h2>");

                out.println("<p class='description'>");
                out.println(plan.getDescription());
                out.println("</p>");

                out.println("<div class='details'>");

                out.println("<div>");
                out.println("Start Date: " + plan.getStartDate());
                out.println("</div>");

                out.println("<div>");
                out.println("End Date: " + plan.getEndDate());
                out.println("</div>");

                String statusClass = "planned";

                if ("IN_PROGRESS".equalsIgnoreCase(plan.getStatus())) {
                    statusClass = "in-progress";
                } else if ("COMPLETED".equalsIgnoreCase(plan.getStatus())) {
                    statusClass = "completed";
                }

                out.println("<div>");
                out.println("Status: ");

                out.println("<span class='status " + statusClass + "'>");
                out.println(plan.getStatus());
                out.println("</span>");

                out.println("</div>");

                out.println("</div>");

                /* ================= ACTIONS ================= */

                out.println("<div class='actions'>");

                out.println("<a class='edit-button' "
                        + "href='edit-study-plan?id="
                        + plan.getId()
                        + "'>");
                out.println("Edit");
                out.println("</a>");

                out.println("<a class='delete-button' "
                        + "href='delete-study-plan?id="
                        + plan.getId()
                        + "' "
                        + "onclick=\"return confirm('Are you sure you want to delete this study plan?');\">");
                out.println("Delete");
                out.println("</a>");

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

