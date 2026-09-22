
package com.studysync.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.studysync.DAO.SuggestionDAO;
import com.studysync.DAOImpl.SuggestionDAOImpl;
import com.studysync.model.SuggestionView;
import com.studysync.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/suggestions")
public class SuggestionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if (session == null ||
            session.getAttribute("user") == null) {

            response.sendRedirect("login.html");
            return;
        }

        User user =
                (User) session.getAttribute("user");

        if (!"STUDENT".equalsIgnoreCase(user.getRole())) {

            response.sendRedirect("dashboard");
            return;
        }

        SuggestionDAO suggestionDAO =
                new SuggestionDAOImpl();

        List<SuggestionView> suggestions =
                suggestionDAO.getSuggestionViewsByStudentId(
                        user.getId()
                );
        
        System.out.println("Suggestions returned: " + suggestions.size());

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out =
                response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");

        out.println("<head>");

        out.println("<meta charset='UTF-8'>");

        out.println("<meta name='viewport' "
                + "content='width=device-width, initial-scale=1.0'>");

        out.println("<title>StudySync - Mentor Suggestions</title>");

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
        out.println("}");

        /* NAVBAR */

        out.println(".navbar {");
        out.println("background: linear-gradient(135deg, #667eea, #764ba2);");
        out.println("padding: 20px 50px;");
        out.println("color: white;");
        out.println("display: flex;");
        out.println("justify-content: space-between;");
        out.println("align-items: center;");
        out.println("box-shadow: 0 5px 20px rgba(0,0,0,0.15);");
        out.println("}");

        out.println(".logo {");
        out.println("font-size: 27px;");
        out.println("font-weight: bold;");
        out.println("}");

        out.println(".nav-buttons {");
        out.println("display: flex;");
        out.println("gap: 10px;");
        out.println("}");

        out.println(".nav-button {");
        out.println("color: white;");
        out.println("text-decoration: none;");
        out.println("padding: 10px 18px;");
        out.println("border-radius: 20px;");
        out.println("background: rgba(255,255,255,0.2);");
        out.println("font-weight: bold;");
        out.println("transition: 0.3s;");
        out.println("}");

        out.println(".nav-button:hover {");
        out.println("background: rgba(255,255,255,0.35);");
        out.println("}");

        /* CONTAINER */

        out.println(".container {");
        out.println("width: 90%;");
        out.println("max-width: 1100px;");
        out.println("margin: 45px auto;");
        out.println("}");

        /* HEADING */

        out.println(".heading {");
        out.println("text-align: center;");
        out.println("margin-bottom: 35px;");
        out.println("}");

        out.println(".heading h1 {");
        out.println("font-size: 36px;");
        out.println("color: #333;");
        out.println("margin-bottom: 10px;");
        out.println("}");

        out.println(".heading p {");
        out.println("font-size: 17px;");
        out.println("color: #777;");
        out.println("}");

        /* SUGGESTION GRID */

        out.println(".suggestion-grid {");
        out.println("display: grid;");
        out.println("grid-template-columns: repeat(2, 1fr);");
        out.println("gap: 25px;");
        out.println("}");

        /* SUGGESTION CARD */

        out.println(".suggestion-card {");
        out.println("background: white;");
        out.println("padding: 28px;");
        out.println("border-radius: 20px;");
        out.println("box-shadow: 0 10px 25px rgba(0,0,0,0.08);");
        out.println("border-left: 6px solid #667eea;");
        out.println("transition: 0.3s;");
        out.println("}");

        out.println(".suggestion-card:hover {");
        out.println("transform: translateY(-5px);");
        out.println("box-shadow: 0 15px 30px rgba(0,0,0,0.12);");
        out.println("}");

        /* MENTOR */

        out.println(".mentor {");
        out.println("color: #764ba2;");
        out.println("font-size: 15px;");
        out.println("font-weight: bold;");
        out.println("margin-bottom: 15px;");
        out.println("}");

        /* TITLE */

        out.println(".suggestion-title {");
        out.println("font-size: 22px;");
        out.println("color: #667eea;");
        out.println("margin-bottom: 12px;");
        out.println("}");

        /* MESSAGE */

        out.println(".suggestion-message {");
        out.println("color: #555;");
        out.println("line-height: 1.7;");
        out.println("font-size: 15px;");
        out.println("margin-bottom: 18px;");
        out.println("}");

        /* DATE */

        out.println(".suggestion-date {");
        out.println("border-top: 1px solid #eee;");
        out.println("padding-top: 12px;");
        out.println("color: #888;");
        out.println("font-size: 13px;");
        out.println("}");

        /* EMPTY */

        out.println(".empty {");
        out.println("background: white;");
        out.println("padding: 50px;");
        out.println("border-radius: 20px;");
        out.println("text-align: center;");
        out.println("box-shadow: 0 10px 25px rgba(0,0,0,0.08);");
        out.println("}");

        out.println(".empty h2 {");
        out.println("color: #667eea;");
        out.println("margin-bottom: 10px;");
        out.println("}");

        out.println(".empty p {");
        out.println("color: #777;");
        out.println("}");

        /* RESPONSIVE */

        out.println("@media(max-width: 750px) {");

        out.println(".navbar {");
        out.println("padding: 20px;");
        out.println("}");

        out.println(".nav-buttons {");
        out.println("flex-direction: column;");
        out.println("}");

        out.println(".suggestion-grid {");
        out.println("grid-template-columns: 1fr;");
        out.println("}");

        out.println("}");

        out.println("</style>");

        out.println("</head>");

        out.println("<body>");

        /* NAVBAR */

        out.println("<div class='navbar'>");

        out.println("<div class='logo'>");
        out.println("📚 StudySync");
        out.println("</div>");

        out.println("<div class='nav-buttons'>");

        out.println("<a class='nav-button' href='dashboard'>");
        out.println("Dashboard");
        out.println("</a>");

        out.println("<a class='nav-button' href='logout'>");
        out.println("Logout");
        out.println("</a>");

        out.println("</div>");

        out.println("</div>");

        /* MAIN CONTAINER */

        out.println("<div class='container'>");

        out.println("<div class='heading'>");

        out.println("<h1>");
        out.println("Mentor Suggestions");
        out.println("</h1>");

        out.println("<p>");
        out.println("Guidance and feedback provided by your mentor.");
        out.println("</p>");

        out.println("</div>");

        /* NO SUGGESTIONS */

        if (suggestions.isEmpty()) {

            out.println("<div class='empty'>");

            out.println("<h2>");
            out.println("No Suggestions Yet");
            out.println("</h2>");

            out.println("<p>");
            out.println(
                    "Your mentor has not given you any suggestions yet."
            );
            out.println("</p>");

            out.println("</div>");

        } else {

            /* SUGGESTION GRID */

            out.println("<div class='suggestion-grid'>");

            for (SuggestionView suggestion : suggestions) {

                out.println("<div class='suggestion-card'>");

                /* MENTOR NAME */

                out.println("<div class='mentor'>");

                out.println("Mentor: ");

                out.println(suggestion.getMentorName());

                out.println("</div>");

                /* TITLE */

                out.println("<h2 class='suggestion-title'>");

                out.println(suggestion.getTitle());

                out.println("</h2>");

                /* MESSAGE */

                out.println("<p class='suggestion-message'>");

                out.println(suggestion.getMessage());

                out.println("</p>");

                /* DATE */

                out.println("<div class='suggestion-date'>");

                out.println("Received: ");

                out.println(suggestion.getCreatedAt());

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

