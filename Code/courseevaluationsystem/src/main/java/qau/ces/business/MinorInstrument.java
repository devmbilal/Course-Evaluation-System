package qau.ces.business;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import qau.ces.data.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MinorInstrument {

    private String name;
    private String majorInstrumentName;
    private String courseName;
    private double percentage;
    private int marks;

    // Constructors
    public MinorInstrument() {
        this.name = "";
        this.majorInstrumentName = "";
        this.courseName = "";
        this.percentage = 0.0;
        this.marks = 0;
    }

    public MinorInstrument(String name, String majorInstrumentName, String courseName, double percentage, int marks) {
        this.name = name;
        this.majorInstrumentName = majorInstrumentName;
        this.courseName = courseName;
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

    public String getMajorInstrumentName() {
        return majorInstrumentName;
    }

    public void setMajorInstrumentName(String majorInstrumentName) {
        this.majorInstrumentName = majorInstrumentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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

    public static boolean isMinorInstrumentExist(String name) {
        // Your logic to check existence in the database
        return false;
    }

    public void insertMinorInstrument() {
        Connection connect = null;
        PreparedStatement prepare = null;

        try {
            connect = database.connectDb();

            String insertData = "INSERT INTO minor_instrument (name, major_instrument_name, course_name, percentage, marks) " +
                                "VALUES (?, ?, ?, ?, ?)";

            prepare = connect.prepareStatement(insertData);

            prepare.setString(1, getName());
            prepare.setString(2, getMajorInstrumentName());
            prepare.setString(3, getCourseName());
            prepare.setDouble(4, getPercentage());
            prepare.setInt(5, getMarks());

            prepare.executeUpdate();

            prepare.close();
            connect.close();

            System.out.println("MinorInstrument inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateMinorInstrument() {
        Connection connect = null;
        PreparedStatement prepare = null;

        try {
            connect = database.connectDb();

            String updateData = "UPDATE minor_instrument SET major_instrument_name = ?, " +
                                "course_name = ?, percentage = ?, marks = ? WHERE name = ?";

            prepare = connect.prepareStatement(updateData);

            prepare.setString(1, getMajorInstrumentName());
            prepare.setString(2, getCourseName());
            prepare.setDouble(3, getPercentage());
            prepare.setInt(4, getMarks());
            prepare.setString(5, getName());

            int rowsUpdated = prepare.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("MinorInstrument updated successfully!");
            } else {
                System.out.println("MinorInstrument not found or not updated!");
            }

            prepare.close();
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteMinorInstrument(String name) throws SQLException {
        Connection connect = null;
        connect = database.connectDb();

        String deleteData = "DELETE FROM minor_instrument WHERE name = ?";

        try (PreparedStatement prepare = connect.prepareStatement(deleteData)) {
            prepare.setString(1, name);

            prepare.executeUpdate();
        }
    }

    public ObservableList<MinorInstrument> minorInstrumentListData() {
        ObservableList<MinorInstrument> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM minor_instrument";

        try (Connection connect = database.connectDb();
             PreparedStatement prepare = connect.prepareStatement(sql);
             ResultSet result = prepare.executeQuery()) {

            while (result.next()) {
                MinorInstrument minorInstrument = new MinorInstrument(
                        result.getString("name"),
                        result.getString("major_instrument_name"),
                        result.getString("course_name"),
                        result.getDouble("percentage"),
                        result.getInt("marks")
                );

                listData.add(minorInstrument);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Number of records retrieved: " + listData.size());

        return listData;
    }
}
