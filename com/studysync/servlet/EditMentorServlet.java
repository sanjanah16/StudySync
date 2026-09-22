
package com.studysync.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.studysync.DAO.MentorDAO;
import com.studysync.DAO.UserDAO;
import com.studysync.DAOImpl.MentorDAOImpl;
import com.studysync.DAOImpl.UserDAOImpl;
import com.studysync.model.Mentor;
import com.studysync.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/edit-mentor")
public class EditMentorServlet extends HttpServlet {

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

        User admin = (User) session.getAttribute("user");

        if (!"ADMIN".equalsIgnoreCase(admin.getRole())) {

            response.sendRedirect("dashboard");
            return;
        }

        String idString = request.getParameter("id");

        if (idString == null || idString.isEmpty()) {

            response.sendRedirect("admin-mentors");
            return;
        }

        int mentorId;

        try {

            mentorId = Integer.parseInt(idString);

        } catch (NumberFormatException e) {

            response.sendRedirect("admin-mentors");
            return;
        }

        MentorDAO mentorDAO = new MentorDAOImpl();
        UserDAO userDAO = new UserDAOImpl();

        Mentor mentor = mentorDAO.getMentor(mentorId);

        if (mentor == null) {

            response.sendRedirect("admin-mentors");
            return;
        }

        User mentorUser =
                userDAO.getUser(mentor.getUserId());

        if (mentorUser == null) {

            response.sendRedirect("admin-mentors");
            return;
        }

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");

        out.println("<head>");

        out.println("<meta charset='UTF-8'>");

        out.println("<meta name='viewport' "
                + "content='width=device-width, initial-scale=1.0'>");

        out.println("<title>StudySync - Edit Mentor</title>");

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

        out.println(".navbar {");
        out.println("background: linear-gradient(135deg, #667eea, #764ba2);");
        out.println("padding: 20px 50px;");
        out.println("color: white;");
        out.println("display: flex;");
        out.println("justify-content: space-between;");
        out.println("align-items: center;");
        out.println("}");

        out.println(".logo {");
        out.println("font-size: 27px;");
        out.println("font-weight: bold;");
        out.println("}");

        out.println(".back {");
        out.println("color: white;");
        out.println("text-decoration: none;");
        out.println("padding: 10px 18px;");
        out.println("border-radius: 20px;");
        out.println("background: rgba(255,255,255,0.2);");
        out.println("font-weight: bold;");
        out.println("}");

        out.println(".container {");
        out.println("width: 90%;");
        out.println("max-width: 750px;");
        out.println("margin: 50px auto;");
        out.println("}");

        out.println(".card {");
        out.println("background: white;");
        out.println("padding: 35px;");
        out.println("border-radius: 20px;");
        out.println("box-shadow: 0 10px 30px rgba(0,0,0,0.10);");
        out.println("}");

        out.println("h1 {");
        out.println("color: #667eea;");
        out.println("margin-bottom: 10px;");
        out.println("}");

        out.println(".subtitle {");
        out.println("color: #777;");
        out.println("margin-bottom: 30px;");
        out.println("}");

        out.println(".form-grid {");
        out.println("display: grid;");
        out.println("grid-template-columns: 1fr 1fr;");
        out.println("gap: 20px;");
        out.println("}");

        out.println(".form-group {");
        out.println("display: flex;");
        out.println("flex-direction: column;");
        out.println("}");

        out.println(".full {");
        out.println("grid-column: 1 / -1;");
        out.println("}");

        out.println("label {");
        out.println("font-weight: bold;");
        out.println("color: #444;");
        out.println("margin-bottom: 8px;");
        out.println("}");

        out.println("input, textarea {");
        out.println("padding: 13px;");
        out.println("border: 1px solid #ddd;");
        out.println("border-radius: 10px;");
        out.println("font-size: 15px;");
        out.println("font-family: Arial, sans-serif;");
        out.println("}");

        out.println("textarea {");
        out.println("resize: vertical;");
        out.println("min-height: 100px;");
        out.println("}");

        out.println(".buttons {");
        out.println("display: flex;");
        out.println("gap: 12px;");
        out.println("margin-top: 25px;");
        out.println("}");

        out.println(".update-button {");
        out.println("background: linear-gradient(135deg, #667eea, #764ba2);");
        out.println("color: white;");
        out.println("border: none;");
        out.println("padding: 13px 25px;");
        out.println("border-radius: 10px;");
        out.println("font-weight: bold;");
        out.println("font-size: 15px;");
        out.println("cursor: pointer;");
        out.println("}");

        out.println(".cancel-button {");
        out.println("background: #eee;");
        out.println("color: #444;");
        out.println("padding: 13px 25px;");
        out.println("border-radius: 10px;");
        out.println("text-decoration: none;");
        out.println("font-weight: bold;");
        out.println("}");

        out.println("@media(max-width: 650px) {");

        out.println(".navbar {");
        out.println("padding: 20px;");
        out.println("}");

        out.println(".form-grid {");
        out.println("grid-template-columns: 1fr;");
        out.println("}");

        out.println(".full {");
        out.println("grid-column: auto;");
        out.println("}");

        out.println("}");

        out.println("</style>");

        out.println("</head>");

        out.println("<body>");

        out.println("<div class='navbar'>");

        out.println("<div class='logo'>StudySync Admin</div>");

        out.println("<a class='back' href='admin-mentors'>");
        out.println("← Manage Mentors");
        out.println("</a>");

        out.println("</div>");

        out.println("<div class='container'>");

        out.println("<div class='card'>");

        out.println("<h1>Edit Mentor</h1>");

        out.println("<p class='subtitle'>");
        out.println("Update mentor account and professional information.");
        out.println("</p>");

        out.println("<form method='post' action='edit-mentor'>");

        out.println("<input type='hidden' name='mentorId' value='"
                + mentor.getId()
                + "'>");

        out.println("<div class='form-grid'>");

        out.println("<div class='form-group'>");

        out.println("<label>Name</label>");

        out.println("<input type='text' name='name' value='"
                + mentorUser.getName()
                + "' required>");

        out.println("</div>");

        out.println("<div class='form-group'>");

        out.println("<label>Email</label>");

        out.println("<input type='email' name='email' value='"
                + mentorUser.getEmail()
                + "' required>");

        out.println("</div>");

        out.println("<div class='form-group'>");

        out.println("<label>Password</label>");

        out.println("<input type='text' name='password' value='"
                + mentorUser.getPassword()
                + "' required>");

        out.println("</div>");

        out.println("<div class='form-group'>");

        out.println("<label>Phone</label>");

        out.println("<input type='text' name='phone' value='"
                + mentorUser.getPhone()
                + "'>");

        out.println("</div>");

        out.println("<div class='form-group'>");

        out.println("<label>Specialization</label>");

        out.println("<input type='text' name='specialization' value='"
                + mentor.getSpecialization()
                + "' required>");

        out.println("</div>");

        out.println("<div class='form-group'>");

        out.println("<label>Experience (Years)</label>");

        out.println("<input type='number' name='experienceYears' value='"
                + mentor.getExperienceYears()
                + "' min='0' required>");

        out.println("</div>");

        out.println("<div class='form-group full'>");

        out.println("<label>Bio</label>");

        out.println("<textarea name='bio'>"
                + mentor.getBio()
                + "</textarea>");

        out.println("</div>");

        out.println("</div>");

        out.println("<div class='buttons'>");

        out.println("<button class='update-button' type='submit'>");
        out.println("Update Mentor");
        out.println("</button>");

        out.println("<a class='cancel-button' href='admin-mentors'>");
        out.println("Cancel");
        out.println("</a>");

        out.println("</div>");

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

        if (session == null ||
            session.getAttribute("user") == null) {

            response.sendRedirect("login.html");
            return;
        }

        User admin = (User) session.getAttribute("user");

        if (!"ADMIN".equalsIgnoreCase(admin.getRole())) {

            response.sendRedirect("dashboard");
            return;
        }

        int mentorId =
                Integer.parseInt(
                        request.getParameter("mentorId"));

        String name =
                request.getParameter("name");

        String email =
                request.getParameter("email");

        String password =
                request.getParameter("password");

        String phone =
                request.getParameter("phone");

        String specialization =
                request.getParameter("specialization");

        int experienceYears =
                Integer.parseInt(
                        request.getParameter("experienceYears"));

        String bio =
                request.getParameter("bio");

        MentorDAO mentorDAO =
                new MentorDAOImpl();

        UserDAO userDAO =
                new UserDAOImpl();

        Mentor mentor =
                mentorDAO.getMentor(mentorId);

        if (mentor == null) {

            response.sendRedirect("admin-mentors");
            return;
        }

        User mentorUser =
                userDAO.getUser(mentor.getUserId());

        if (mentorUser == null) {

            response.sendRedirect("admin-mentors");
            return;
        }

        mentorUser.setName(name);
        mentorUser.setEmail(email);
        mentorUser.setPassword(password);
        mentorUser.setPhone(phone);

        userDAO.updateUser(mentorUser);

        mentor.setSpecialization(specialization);
        mentor.setExperienceYears(experienceYears);
        mentor.setBio(bio);

        mentorDAO.updateMentor(mentor);

        response.sendRedirect("admin-mentors");
    }
}

