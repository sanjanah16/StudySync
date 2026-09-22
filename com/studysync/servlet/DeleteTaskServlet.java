
package com.studysync.servlet;

import java.io.IOException;
import java.util.List;

import com.studysync.DAO.ProgressDAO;
import com.studysync.DAO.TaskDAO;

import com.studysync.DAOImpl.ProgressDAOImpl;
import com.studysync.DAOImpl.TaskDAOImpl;

import com.studysync.model.Progress;
import com.studysync.model.Task;
import com.studysync.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/delete-task")
public class DeleteTaskServlet extends HttpServlet {

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

        String idString = request.getParameter("id");

        if (idString == null || idString.isEmpty()) {

            response.sendRedirect("tasks");
            return;
        }

        int taskId = Integer.parseInt(idString);

        TaskDAO taskDAO = new TaskDAOImpl();

        Task task = taskDAO.getTask(taskId);

        if (task == null) {

            response.sendRedirect("tasks");
            return;
        }

        /*
         * Check whether the task belongs
         * to the logged-in user.
         */

        if (task.getUserId() != user.getId()) {

            response.getWriter().println(
                    "You cannot delete this task."
            );

            return;
        }


        /*
         * Store the subject ID before deleting.
         *
         * We need this because after deleting the task,
         * we use the subject ID to recalculate progress.
         */

        int subjectId = task.getSubjectId();


        /*
         * Delete the task.
         */

        taskDAO.deleteTask(taskId);


        /*
         * Recalculate progress after deleting
         * the task.
         */

        updateProgress(
                user.getId(),
                subjectId
        );


        response.sendRedirect("tasks");
    }


    /*
     * Method to recalculate progress
     * for a particular subject.
     */

    private void updateProgress(
            int userId,
            int subjectId) {

        ProgressDAO progressDAO =
                new ProgressDAOImpl();


        /*
         * Count total tasks for this
         * user and subject.
         */

        int totalTasks =
                progressDAO.getTotalTasks(
                        userId,
                        subjectId
                );


        /*
         * Count completed tasks for this
         * user and subject.
         */

        int completedTasks =
                progressDAO.getCompletedTasks(
                        userId,
                        subjectId
                );


        double progressPercentage = 0;


        /*
         * Calculate percentage.
         */

        if (totalTasks > 0) {

            progressPercentage =
                    ((double) completedTasks
                    / totalTasks) * 100;
        }


        /*
         * Get existing progress record.
         */

        List<Progress> progressList =
                progressDAO.getProgressByUserId(
                        userId
                );


        Progress existingProgress = null;


        /*
         * Find progress record for
         * the selected subject.
         */

        for (Progress progress : progressList) {

            if (progress.getSubjectId()
                    == subjectId) {

                existingProgress = progress;

                break;
            }
        }


        /*
         * If no progress record exists,
         * create one.
         */

        if (existingProgress == null) {

            Progress progress =
                    new Progress(
                            userId,
                            subjectId,
                            completedTasks,
                            totalTasks,
                            progressPercentage
                    );

            progressDAO.addProgress(progress);

        } else {

            /*
             * Update existing progress.
             */

            existingProgress.setCompletedTasks(
                    completedTasks
            );

            existingProgress.setTotalTasks(
                    totalTasks
            );

            existingProgress.setProgressPercentage(
                    progressPercentage
            );

            progressDAO.updateProgress(
                    existingProgress
            );
        }
    }
}

