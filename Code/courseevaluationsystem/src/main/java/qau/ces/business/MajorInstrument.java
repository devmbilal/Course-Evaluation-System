package qau.ces.business;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import qau.ces.data.database;

public class MajorInstrument {
    
    private String name;
    private String course;
    private double percentage;
    private int marks;

    // Constructor
     public MajorInstrument() {
        // Initialize default values or perform any other setup if needed
        this.name = "";
        this.course = "";
        this.percentage = 0.0;
        this.marks = 0;
    }
    public MajorInstrument(String name, String course, double percentage, int marks) {
        this.name = name;
        this.course = course;
        this.percentage = percentage;
        this.marks = marks;
    }


    // Getter and Setter methods...

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }
    

    public void setCourse(String course) {
        this.course = course;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public static List<String> getCourses() {
       List<String> courses = new ArrayList<>();
        try (Connection conn = database.connectDb()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DISTINCT year FROM course");
            while (rs.next()) {
                courses.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    // Method to check if a MajorInstrument already exists
    public static boolean isMajorInstrumentExist(String name) {
        // Your logic to check existence in the database
        return false;
    }

    public void insertMajorInstrument() {
        Connection connect = null;
        PreparedStatement prepare = null;

        try {
            // Establish the database connection
            connect = database.connectDb();

            // SQL statement to insert a new MajorInstrument
            String insertData = "INSERT INTO major_instrument (name, course, percentage, marks) VALUES (?, ?, ?, ?)";

            // Prepare the SQL statement
            prepare = connect.prepareStatement(insertData);

            // Set the values for the parameters
            prepare.setString(1, getName());
            prepare.setString(2, getCourse());
            prepare.setDouble(3, getPercentage());
            prepare.setInt(4, getMarks());

            // Execute the SQL statement
            prepare.executeUpdate();

            // Close the resources
            prepare.close();
            connect.close();

            System.out.println("MajorInstrument inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the resources in a finally block to ensure they are closed even if an exception occurs
            try {
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

   public void updateMajorInstrument() {
        Connection connect = null;
        PreparedStatement prepare = null;

        try {
            // Establish the database connection
            connect = database.connectDb();

            // SQL statement to update an existing MajorInstrument
            String updateData = "UPDATE major_instrument SET course = ?, percentage = ?, marks = ? WHERE name = ?";

            // Prepare the SQL statement
            prepare = connect.prepareStatement(updateData);

            // Set the values for the parameters
            prepare.setString(1, getCourse());
            prepare.setDouble(2, getPercentage());
            prepare.setInt(3, getMarks());
            prepare.setString(4, getName());

            // Execute the SQL statement
            int rowsUpdated = prepare.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("MajorInstrument updated successfully!");
            } else {
                System.out.println("MajorInstrument not found or not updated!");
            }

            // Close the resources
            prepare.close();
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the resources in a finally block to ensure they are closed even if an exception occurs
            try {
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

   public void deleteMajorInstrument(String name) throws SQLException {
        Connection connect = null;
        connect = database.connectDb();
        // Your logic to delete from the database
        String deleteData = "DELETE FROM major_instrument WHERE name = ?";

        // Prepare the statement
        try (PreparedStatement prepare = connect.prepareStatement(deleteData)) {
            prepare.setString(1, name);

            // Execute the delete statement
            prepare.executeUpdate();
        }
    }

    public ObservableList<MajorInstrument> MajorInstrumentListData() {
        ObservableList<MajorInstrument> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM major_instrument";

        try (Connection connect = database.connectDb();
             PreparedStatement prepare = connect.prepareStatement(sql);
             ResultSet result = prepare.executeQuery()) {

            while (result.next()) {
                MajorInstrument majorInstrument = new MajorInstrument(
                        result.getString("name"),
                        result.getString("course"),
                        result.getDouble("percentage"),
                        result.getInt("marks")
                );

                listData.add(majorInstrument);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
       System.out.println("Number of records retrieved: " + listData.size());

        return listData;
    }

}

