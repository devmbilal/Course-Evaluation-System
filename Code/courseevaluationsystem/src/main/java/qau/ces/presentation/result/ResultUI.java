package qau.ces.presentation.result;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import qau.ces.business.Result;

import java.util.List;

public class ResultUI {

    @FXML
    private ComboBox<String> studentGrade_year;

    @FXML
    private ComboBox<String> studentGrade_course;

    @FXML
    private TableView<Result> studentGrade_tableView;

    @FXML
    private TableColumn<Result, String> studentGrade_col_studentNum;

    @FXML
    private TableColumn<Result, String> studentGrade_col_year;

    @FXML
    private TableColumn<Result, String> studentGrade_col_course;

    @FXML
    private TableColumn<Result, String> studentGrade_col_firstSem;

    @FXML
    private TableColumn<Result, String> studentGrade_col_secondSem;

    @FXML
    private TableColumn<Result, String> studentGrade_col_final;

    @FXML
    private TextField studentGrade_search;

    @FXML
    public void initialize() {
        // Populate the year and course ComboBoxes
        studentGrade_year.setItems(FXCollections.observableArrayList(Result.getYears()));
        studentGrade_course.setItems(FXCollections.observableArrayList(Result.getCourses()));

        // Set up the TableViewstudentGrade_col_studentNum.setCellValueFactory(cellData -> cellData.getValue().getStudentId());
        studentGrade_col_studentNum.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentId()));
        studentGrade_col_year.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getYear()));
        studentGrade_col_course.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCourse()));
        studentGrade_col_firstSem.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMidTerm()));
        studentGrade_col_secondSem.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTerminal()));
        studentGrade_col_final.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFinalGrade()));
        

        // Add a listener to the ComboBoxes to update the TableView when a new year or course is selected
        studentGrade_year.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updateTable());
        studentGrade_course.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updateTable());
    }

    private void updateTable() {
        String year = studentGrade_year.getSelectionModel().getSelectedItem();
        String course = studentGrade_course.getSelectionModel().getSelectedItem();
        if (year != null && course != null) {
            List<Result> students = Result.getStudents(year, course);
            ObservableList<Result> data = FXCollections.observableArrayList(students);
            studentGrade_tableView.setItems(data);
        }
    }
}