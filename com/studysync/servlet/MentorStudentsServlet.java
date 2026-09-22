package com.studysync.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.studysync.DAO.MentorDAO;
import com.studysync.DAO.MentorStudentDAO;
import com.studysync.DAO.UserDAO;
import com.studysync.DAOImpl.MentorDAOImpl;
import com.studysync.DAOImpl.MentorStudentDAOImpl;
import com.studysync.DAOImpl.UserDAOImpl;
import com.studysync.model.Mentor;
import com.studysync.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/mentor-student")
public class MentorStudentsServlet extends HttpServlet {

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

        User loggedUser = (User) session.getAttribute("user");

        if (!"ADMIN".equalsIgnoreCase(loggedUser.getRole())) {

            response.sendRedirect("dashboard.html");
            return;
        }

        MentorDAO mentorDAO = new MentorDAOImpl();
        UserDAO userDAO = new UserDAOImpl();
        MentorStudentDAO mentorStudentDAO =
                new MentorStudentDAOImpl();

        List<Mentor> mentors = mentorDAO.getAllMentors();
        List<User> students = mentorStudentDAO.getAllStudents();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");

        out.println("<meta charset='UTF-8'>");
        out.println("<title>Mentor-Student Management</title>");

        out.println("<style>");

        out.println("* {");
        out.println("box-sizing: border-box;");
        out.println("}");

        out.println("body {");
        out.println("margin: 0;");
        out.println("font-family: Arial, sans-serif;");
        out.println("background: #f5f7fb;");
        out.println("color: #333;");
        out.println("}");

        out.println(".navbar {");
        out.println("background: linear-gradient(135deg, #667eea, #764ba2);");
        out.println("color: white;");
        out.println("padding: 20px 40px;");
        out.println("font-size: 24px;");
        out.println("font-weight: bold;");
        out.println("}");

        out.println(".container {");
        out.println("width: 90%;");
        out.println("max-width: 1200px;");
        out.println("margin: 35px auto;");
        out.println("}");

        out.println(".back {");
        out.println("display: inline-block;");
        out.println("text-decoration: none;");
        out.println("color: #667eea;");
        out.println("font-weight: bold;");
        out.println("margin-bottom: 20px;");
        out.println("}");

        out.println("h1 {");
        out.println("margin-bottom: 8px;");
        out.println("}");

        out.println(".description {");
        out.println("color: #777;");
        out.println("margin-bottom: 30px;");
        out.println("}");

        out.println(".card {");
        out.println("background: white;");
        out.println("padding: 30px;");
        out.println("border-radius: 15px;");
        out.println("box-shadow: 0 5px 20px rgba(0,0,0,0.08);");
        out.println("margin-bottom: 30px;");
        out.println("}");

        out.println(".card h2 {");
        out.println("margin-top: 0;");
        out.println("color: #444;");
        out.println("}");

        out.println(".form-group {");
        out.println("margin-bottom: 20px;");
        out.println("}");

        out.println("label {");
        out.println("display: block;");
        out.println("font-weight: bold;");
        out.println("margin-bottom: 8px;");
        out.println("}");

        out.println("select {");
        out.println("width: 100%;");
        out.println("padding: 12px;");
        out.println("border: 1px solid #ddd;");
        out.println("border-radius: 8px;");
        out.println("font-size: 15px;");
        out.println("}");

        out.println(".assign-btn {");
        out.println("background: linear-gradient(135deg, #667eea, #764ba2);");
        out.println("color: white;");
        out.println("border: none;");
        out.println("padding: 12px 25px;");
        out.println("border-radius: 8px;");
        out.println("font-size: 15px;");
        out.println("font-weight: bold;");
        out.println("cursor: pointer;");
        out.println("}");

        out.println(".assign-btn:hover {");
        out.println("opacity: 0.9;");
        out.println("}");

        out.println("table {");
        out.println("width: 100%;");
        out.println("border-collapse: collapse;");
        out.println("margin-top: 20px;");
        out.println("}");

        out.println("th {");
        out.println("background: #667eea;");
        out.println("color: white;");
        out.println("padding: 14px;");
        out.println("text-align: left;");
        out.println("}");

        out.println("td {");
        out.println("padding: 14px;");
        out.println("border-bottom: 1px solid #eee;");
        out.println("}");

        out.println("tr:hover {");
        out.println("background: #f8f8ff;");
        out.println("}");

        out.println(".remove-btn {");
        out.println("background: #e74c3c;");
        out.println("color: white;");
        out.println("border: none;");
        out.println("padding: 8px 15px;");
        out.println("border-radius: 6px;");
        out.println("cursor: pointer;");
        out.println("}");

        out.println(".remove-btn:hover {");
        out.println("background: #c0392b;");
        out.println("}");

        out.println(".empty {");
        out.println("text-align: center;");
        out.println("color: #888;");
        out.println("padding: 20px;");
        out.println("}");

        out.println("</style>");

        out.println("</head>");
        out.println("<body>");

        out.println("<div class='navbar'>");
        out.println("StudySync Admin");
        out.println("</div>");

        out.println("<div class='container'>");

        out.println("<a class='back' href='admin-dashboard'>");
        out.println("← Admin Dashboard");
        out.println("</a>");

        out.println("<h1>Mentor-Student Management</h1>");

        out.println("<p class='description'>");
        out.println("Assign students to mentors and manage academic relationships.");
        out.println("</p>");

        // ============================
        // ASSIGN STUDENT FORM
        // ============================

        out.println("<div class='card'>");

        out.println("<h2>Assign Student to Mentor</h2>");

        out.println("<form method='post' action='mentor-student'>");

        out.println("<div class='form-group'>");

        out.println("<label>Select Mentor</label>");

        out.println("<select name='mentorId' required>");

        out.println("<option value=''>Select Mentor</option>");

        for (Mentor mentor : mentors) {

            User mentorUser =
                    userDAO.getUser(mentor.getUserId());

            if (mentorUser != null) {

                out.println(
                    "<option value='" +
                    mentor.getId() +
                    "'>" +
                    mentorUser.getName() +
                    " - " +
                    mentor.getSpecialization() +
                    "</option>"
                );
            }
        }

        out.println("</select>");

        out.println("</div>");

        out.println("<div class='form-group'>");

        out.println("<label>Select Student</label>");

        out.println("<select name='studentId' required>");

        out.println("<option value=''>Select Student</option>");

        for (User student : students) {

            out.println(
                "<option value='" +
                student.getId() +
                "'>" +
                student.getName() +
                " - " +
                student.getEmail() +
                "</option>"
            );
        }

        out.println("</select>");

        out.println("</div>");

        out.println("<button type='submit' class='assign-btn'>");
        out.println("Assign Student");
        out.println("</button>");

        out.println("</form>");

        out.println("</div>");

        // ============================
        // ASSIGNED STUDENTS
        // ============================

        out.println("<div class='card'>");

        out.println("<h2>Assigned Students</h2>");

        boolean hasAssignments = false;

        for (Mentor mentor : mentors) {

            List<User> assignedStudents =
                    mentorStudentDAO.getStudentsByMentorId(
                            mentor.getId()
                    );

            User mentorUser =
                    userDAO.getUser(mentor.getUserId());

            if (mentorUser != null &&
                !assignedStudents.isEmpty()) {

                hasAssignments = true;

                for (User student : assignedStudents) {

                    out.println("<table>");

                    out.println("<tr>");
                    out.println("<th>Mentor</th>");
                    out.println("<th>Student</th>");
                    out.println("<th>Email</th>");
                    out.println("<th>Action</th>");
                    out.println("</tr>");

                    out.println("<tr>");

                    out.println("<td>");
                    out.println(mentorUser.getName());
                    out.println("</td>");

                    out.println("<td>");
                    out.println(student.getName());
                    out.println("</td>");

                    out.println("<td>");
                    out.println(student.getEmail());
                    out.println("</td>");

                    out.println("<td>");

                    out.println("<form method='post' action='mentor-student'>");

                    out.println(
                        "<input type='hidden' name='action' value='remove'>"
                    );

                    out.println(
                        "<input type='hidden' name='mentorId' value='" +
                        mentor.getId() +
                        "'>"
                    );

                    out.println(
                        "<input type='hidden' name='studentId' value='" +
                        student.getId() +
                        "'>"
                    );

                    out.println(
                        "<button type='submit' class='remove-btn'>" +
                        "Remove" +
                        "</button>"
                    );

                    out.println("</form>");

                    out.println("</td>");

                    out.println("</tr>");

                    out.println("</table>");
                }
            }
        }

        if (!hasAssignments) {

            out.println(
                "<div class='empty'>" +
                "No students have been assigned to mentors yet." +
                "</div>"
            );
        }

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

        User loggedUser = (User) session.getAttribute("user");

        if (!"ADMIN".equalsIgnoreCase(loggedUser.getRole())) {

            response.sendRedirect("dashboard.html");
            return;
        }

        String action = request.getParameter("action");

        String mentorIdString =
                request.getParameter("mentorId");

        String studentIdString =
                request.getParameter("studentId");

        try {

            int mentorId =
                    Integer.parseInt(mentorIdString);

            int studentId =
                    Integer.parseInt(studentIdString);

            MentorStudentDAO mentorStudentDAO =
                    new MentorStudentDAOImpl();

            // ============================
            // REMOVE STUDENT
            // ============================

            if ("remove".equals(action)) {

                mentorStudentDAO.removeStudent(
                        mentorId,
                        studentId
                );

            }

            // ============================
            // ASSIGN STUDENT
            // ============================

            else {

                boolean alreadyAssigned =
                        mentorStudentDAO.isStudentAssigned(
                                mentorId,
                                studentId
                        );

                if (!alreadyAssigned) {

                    mentorStudentDAO.assignStudent(
                            mentorId,
                            studentId
                    );

                } else {

                    System.out.println(
                        "Student is already assigned to this mentor."
                    );
                }
            }

            response.sendRedirect("mentor-student");

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect("mentor-student");
        }
    }
}