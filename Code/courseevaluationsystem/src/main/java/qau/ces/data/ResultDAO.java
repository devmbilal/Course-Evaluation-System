package qau.ces.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import qau.ces.business.Result;


public class ResultDAO {

    public List<String> getYears() {
        List<String> years = new ArrayList<>();
        try (Connection conn = database.connectDb()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DISTINCT year FROM result");
            while (rs.next()) {
                years.add(rs.getString("year"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return years;
    }

    public List<String> getCourses() {
        List<String> courses = new ArrayList<>();
        try (Connection conn =  database.connectDb()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DISTINCT course FROM result");
            while (rs.next()) {
                courses.add(rs.getString("course"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public List<Result> getStudents(String year, String course) {
        List<Result> students = new ArrayList<>();
        try (Connection conn =  database.connectDb()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM result WHERE year = ? AND course = ?");
            stmt.setString(1, year);
            stmt.setString(2, course);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Result result = new Result();
                result.setStudentId(rs.getString("studentid"));
                result.setYear(rs.getString("year"));
                result.setCourse(rs.getString("course"));
                result.setMidTerm(rs.getString("midterm"));
                result.setTerminal(rs.getString("terminal"));
                result.setFinalGrade(rs.getString("final"));
                students.add(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}