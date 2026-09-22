package com.studysync.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.studysync.DAO.SubjectDAO;
import com.studysync.model.Subject;
import com.studysync.util.DBConnection;

public class SubjectDAOImpl implements SubjectDAO {

    @Override
    public void addSubject(Subject subject) {

        String sql = "INSERT INTO subject (name, description) VALUES (?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, subject.getName());
            ps.setString(2, subject.getDescription());

            ps.executeUpdate();

            System.out.println("Subject Added Successfully");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    @Override
    public Subject getSubject(int id) {

        Subject subject = null;

        String sql = "SELECT * FROM subject WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                subject = new Subject(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getTimestamp("created_at")
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return subject;
    }


    @Override
    public void updateSubject(Subject subject) {

        String sql = "UPDATE subject SET name=?, description=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, subject.getName());
            ps.setString(2, subject.getDescription());
            ps.setInt(3, subject.getId());

            ps.executeUpdate();

            System.out.println("Subject Updated Successfully");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    @Override
    public void deleteSubject(int id) {

        String sql = "DELETE FROM subject WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println("Subject Deleted Successfully");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    @Override
    public List<Subject> getAllSubjects() {

        List<Subject> subjects = new ArrayList<>();

        String sql = "SELECT * FROM subject";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Subject subject = new Subject(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getTimestamp("created_at")
                );

                subjects.add(subject);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return subjects;
    }
}