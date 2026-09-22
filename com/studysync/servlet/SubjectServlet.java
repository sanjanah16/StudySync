package com.studysync.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.studysync.DAO.SubjectDAO;
import com.studysync.DAOImpl.SubjectDAOImpl;
import com.studysync.model.Subject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/subjects")
public class SubjectServlet extends HttpServlet {

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

        SubjectDAO subjectDAO = new SubjectDAOImpl();

        List<Subject> subjects = subjectDAO.getAllSubjects();

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>StudySync - Subjects</title>");

        out.println("<style>");

        out.println("body {");
        out.println("font-family: Arial, sans-serif;");
        out.println("background: #f5f7fb;");
        out.println("margin: 0;");
        out.println("}");

        out.println(".navbar {");
        out.println("background: linear-gradient(135deg, #667eea, #764ba2);");
        out.println("color: white;");
        out.println("padding: 20px 40px;");
        out.println("}");

        out.println(".container {");
        out.println("padding: 40px;");
        out.println("}");

        out.println(".subjects {");
        out.println("display: grid;");
        out.println("grid-template-columns: repeat(3, 1fr);");
        out.println("gap: 20px;");
        out.println("}");

        out.println(".card {");
        out.println("background: white;");
        out.println("padding: 25px;");
        out.println("border-radius: 15px;");
        out.println("box-shadow: 0 5px 15px rgba(0,0,0,0.1);");
        out.println("}");

        out.println(".card h2 {");
        out.println("color: #667eea;");
        out.println("}");

        out.println(".card p {");
        out.println("color: #666;");
        out.println("}");

        out.println(".back {");
        out.println("display: inline-block;");
        out.println("margin-bottom: 25px;");
        out.println("text-decoration: none;");
        out.println("color: #667eea;");
        out.println("font-weight: bold;");
        out.println("}");

        out.println("</style>");

        out.println("</head>");

        out.println("<body>");

        out.println("<div class='navbar'>");
        out.println("<h1>📚 StudySync</h1>");
        out.println("</div>");

        out.println("<div class='container'>");

        out.println("<a class='back' href='dashboard'>");
        out.println("← Back to Dashboard");
        out.println("</a>");

        out.println("<h1>My Subjects 📚</h1>");

        out.println("<div class='subjects'>");

        for (Subject subject : subjects) {

            out.println("<div class='card'>");

            out.println("<h2>"
                    + subject.getName()
                    + "</h2>");

            out.println("<p>"
                    + subject.getDescription()
                    + "</p>");

            out.println("</div>");
        }

        out.println("</div>");

        out.println("</div>");

        out.println("</body>");

        out.println("</html>");
    }
}