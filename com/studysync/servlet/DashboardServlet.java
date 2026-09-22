
package com.studysync.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.studysync.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

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

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");

        out.println("<head>");

        out.println("<meta charset='UTF-8'>");

        out.println("<meta name='viewport' "
                + "content='width=device-width, initial-scale=1.0'>");

        out.println("<title>StudySync Dashboard</title>");

        // ================= CSS =================

        out.println("<style>");

        out.println("* {");
        out.println("margin: 0;");
        out.println("padding: 0;");
        out.println("box-sizing: border-box;");
        out.println("}");

        out.println("body {");
        out.println("font-family: Arial, sans-serif;");
        out.println("background: linear-gradient(135deg, #eef2ff, #fdf2f8);");
        out.println("min-height: 100vh;");
        out.println("color: #333;");
        out.println("}");

        /* ================= NAVBAR ================= */

        out.println(".navbar {");
        out.println("height: 75px;");
        out.println("background: linear-gradient(135deg, #667eea, #764ba2);");
        out.println("display: flex;");
        out.println("align-items: center;");
        out.println("justify-content: space-between;");
        out.println("padding: 0 50px;");
        out.println("color: white;");
        out.println("box-shadow: 0 4px 15px rgba(0,0,0,0.15);");
        out.println("}");

        out.println(".logo {");
        out.println("font-size: 28px;");
        out.println("font-weight: bold;");
        out.println("}");

        out.println(".logout {");
        out.println("text-decoration: none;");
        out.println("background: rgba(255,255,255,0.2);");
        out.println("color: white;");
        out.println("padding: 10px 20px;");
        out.println("border-radius: 25px;");
        out.println("font-weight: bold;");
        out.println("transition: 0.3s;");
        out.println("}");

        out.println(".logout:hover {");
        out.println("background: white;");
        out.println("color: #764ba2;");
        out.println("}");

        /* ================= MAIN CONTAINER ================= */

        out.println(".container {");
        out.println("width: 90%;");
        out.println("max-width: 1200px;");
        out.println("margin: 40px auto;");
        out.println("}");

        /* ================= WELCOME ================= */

        out.println(".welcome {");
        out.println("background: linear-gradient(135deg, #667eea, #764ba2);");
        out.println("color: white;");
        out.println("padding: 35px;");
        out.println("border-radius: 20px;");
        out.println("box-shadow: 0 10px 25px rgba(102,126,234,0.3);");
        out.println("margin-bottom: 30px;");
        out.println("}");

        out.println(".welcome h1 {");
        out.println("font-size: 32px;");
        out.println("margin-bottom: 10px;");
        out.println("}");

        out.println(".welcome p {");
        out.println("font-size: 17px;");
        out.println("opacity: 0.9;");
        out.println("}");

        /* ================= USER INFO ================= */

        out.println(".info-grid {");
        out.println("display: grid;");
        out.println("grid-template-columns: repeat(3, 1fr);");
        out.println("gap: 20px;");
        out.println("margin-bottom: 35px;");
        out.println("}");

        out.println(".info-card {");
        out.println("background: white;");
        out.println("padding: 25px;");
        out.println("border-radius: 18px;");
        out.println("box-shadow: 0 6px 18px rgba(0,0,0,0.08);");
        out.println("transition: 0.3s;");
        out.println("}");

        out.println(".info-card:hover {");
        out.println("transform: translateY(-5px);");
        out.println("box-shadow: 0 12px 25px rgba(0,0,0,0.12);");
        out.println("}");

        out.println(".icon {");
        out.println("font-size: 35px;");
        out.println("margin-bottom: 12px;");
        out.println("}");

        out.println(".info-card h3 {");
        out.println("color: #667eea;");
        out.println("margin-bottom: 8px;");
        out.println("}");

        out.println(".info-card p {");
        out.println("color: #666;");
        out.println("font-size: 15px;");
        out.println("}");

        /* ================= FEATURES ================= */

        out.println(".section-title {");
        out.println("font-size: 25px;");
        out.println("margin-bottom: 20px;");
        out.println("color: #333;");
        out.println("}");

        out.println(".feature-grid {");
        out.println("display: grid;");
        out.println("grid-template-columns: repeat(3, 1fr);");
        out.println("gap: 20px;");
        out.println("}");

        out.println(".feature-card {");
        out.println("background: white;");
        out.println("padding: 30px;");
        out.println("border-radius: 18px;");
        out.println("text-decoration: none;");
        out.println("color: #333;");
        out.println("box-shadow: 0 6px 18px rgba(0,0,0,0.08);");
        out.println("transition: 0.3s;");
        out.println("border-top: 5px solid #667eea;");
        out.println("}");

        out.println(".feature-card:hover {");
        out.println("transform: translateY(-7px);");
        out.println("box-shadow: 0 15px 30px rgba(0,0,0,0.15);");
        out.println("}");

        out.println(".feature-card .emoji {");
        out.println("font-size: 40px;");
        out.println("margin-bottom: 15px;");
        out.println("}");

        out.println(".feature-card h3 {");
        out.println("margin-bottom: 8px;");
        out.println("color: #667eea;");
        out.println("}");

        out.println(".feature-card p {");
        out.println("color: #777;");
        out.println("line-height: 1.5;");
        out.println("}");

        /* ================= DIFFERENT CARD COLORS ================= */

        out.println(".subjects-card {");
        out.println("border-top-color: #667eea;");
        out.println("}");

        out.println(".tasks-card {");
        out.println("border-top-color: #ff6b6b;");
        out.println("}");

        out.println(".progress-card {");
        out.println("border-top-color: #8854d0;");
        out.println("}");

        out.println(".plans-card {");
        out.println("border-top-color: #f7b731;");
        out.println("}");

        out.println(".notes-card {");
        out.println("border-top-color: #fa8231;");
        out.println("}");

        out.println(".goals-card {");
        out.println("border-top-color: #eb3b5a;");
        out.println("}");

        out.println(".suggestions-card {");
        out.println("border-top-color: #00b894;");
        out.println("}");

        /* ================= RESPONSIVE ================= */

        out.println("@media(max-width: 800px) {");

        out.println(".info-grid, .feature-grid {");
        out.println("grid-template-columns: 1fr;");
        out.println("}");

        out.println(".navbar {");
        out.println("padding: 0 20px;");
        out.println("}");

        out.println(".container {");
        out.println("width: 92%;");
        out.println("}");

        out.println("}");

        out.println("</style>");

        out.println("</head>");

        out.println("<body>");

        /* ================= NAVBAR ================= */

        out.println("<div class='navbar'>");

        out.println("<div class='logo'>📚 StudySync</div>");

        out.println("<a class='logout' href='logout'>Logout</a>");

        out.println("</div>");

        /* ================= MAIN ================= */

        out.println("<div class='container'>");

        /* ================= WELCOME ================= */

        out.println("<div class='welcome'>");

        out.println("<h1>Welcome back, "
                + user.getName()
                + "! 👋</h1>");

        out.println("<p>");

        out.println("Stay organized, study smarter and "
                + "achieve your goals with StudySync.");

        out.println("</p>");

        out.println("</div>");

        /* ================= USER INFORMATION ================= */

        out.println("<div class='info-grid'>");

        out.println("<div class='info-card'>");

        out.println("<div class='icon'>👤</div>");

        out.println("<h3>Student</h3>");

        out.println("<p>"
                + user.getName()
                + "</p>");

        out.println("</div>");

        out.println("<div class='info-card'>");

        out.println("<div class='icon'>📧</div>");

        out.println("<h3>Email</h3>");

        out.println("<p>"
                + user.getEmail()
                + "</p>");

        out.println("</div>");

        out.println("<div class='info-card'>");

        out.println("<div class='icon'>🎓</div>");

        out.println("<h3>Role</h3>");

        out.println("<p>"
                + user.getRole()
                + "</p>");

        out.println("</div>");

        out.println("</div>");

        /* ================= FEATURES ================= */

        out.println("<h2 class='section-title'>");

        out.println("Your Study Space 🚀");

        out.println("</h2>");

        out.println("<div class='feature-grid'>");

        /* ================= SUBJECTS ================= */

        out.println("<a class='feature-card subjects-card' "
                + "href='subjects'>");

        out.println("<div class='emoji'>📚</div>");

        out.println("<h3>My Subjects</h3>");

        out.println("<p>");

        out.println("View your subjects and explore "
                + "the topics you are learning.");

        out.println("</p>");

        out.println("</a>");

        /* ================= TASKS ================= */

        out.println("<a class='feature-card tasks-card' "
                + "href='tasks'>");

        out.println("<div class='emoji'>✅</div>");

        out.println("<h3>Tasks</h3>");

        out.println("<p>");

        out.println("Manage assignments, deadlines and "
                + "daily study tasks.");

        out.println("</p>");

        out.println("</a>");

        /* ================= PROGRESS ================= */

        out.println("<a class='feature-card progress-card' "
                + "href='progress'>");

        out.println("<div class='emoji'>📊</div>");

        out.println("<h3>My Progress</h3>");

        out.println("<p>");

        out.println("Track your learning progress and "
                + "improve your study performance.");

        out.println("</p>");

        out.println("</a>");

        /* ================= STUDY PLAN ================= */

        out.println("<a class='feature-card plans-card' "
                + "href='study-plans'>");

        out.println("<div class='emoji'>🗓️</div>");

        out.println("<h3>Study Plan</h3>");

        out.println("<p>");

        out.println("Create and manage your personalized "
                + "study schedule.");

        out.println("</p>");

        out.println("</a>");

        /* ================= NOTES ================= */

        out.println("<a class='feature-card notes-card' "
                + "href='notes'>");

        out.println("<div class='emoji'>📝</div>");

        out.println("<h3>Notes</h3>");

        out.println("<p>");

        out.println("Keep your important study notes "
                + "organized in one place.");

        out.println("</p>");

        out.println("</a>");

        /* ================= GOALS ================= */

        out.println("<a class='feature-card goals-card' "
                + "href='goals'>");

        out.println("<div class='emoji'>🎯</div>");

        out.println("<h3>Goals</h3>");

        out.println("<p>");

        out.println("Set learning goals and stay motivated "
                + "to achieve them.");

        out.println("</p>");

        out.println("</a>");

        /* ================= MENTOR SUGGESTIONS ================= */

        out.println("<a class='feature-card suggestions-card' "
                + "href='suggestions'>");

        out.println("<div class='emoji'>👨‍🏫</div>");

        out.println("<h3>Mentor Suggestions</h3>");

        out.println("<p>");

        out.println("View guidance and feedback "
                + "provided by your mentor.");

        out.println("</p>");

        out.println("</a>");

        out.println("</div>");

        out.println("</div>");

        out.println("</body>");

        out.println("</html>");
    }
}

