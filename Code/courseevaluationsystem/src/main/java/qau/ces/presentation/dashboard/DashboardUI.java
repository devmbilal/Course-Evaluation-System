package qau.ces.presentation.dashboard;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import qau.ces.business.MajorInstrument;
import qau.ces.business.MinorInstrument;
import qau.ces.business.Result;
import qau.ces.data.getData;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;



public class DashboardUI implements Initializable {

    // Dahboard
    @FXML
    private AnchorPane main_form;

    @FXML
    private Button close;

    @FXML
    private Button minimize;

    @FXML
    private Label username;

     @FXML
    private Button enterMarks_btn;
 
    @FXML
    private Button minorInstrument_btn;

    @FXML
    private Button majorInstrument_btn;

    @FXML
    private Button studentGrade_btn;

    @FXML
    private Button logout;

     @FXML
    private AnchorPane home_form;
    
    @FXML 
    private AnchorPane minorInstrument_form;

    @FXML 
    private AnchorPane enterMarksForm;
   

    @FXML 
    private AnchorPane majorInstrument_form;
    
@FXML
private AnchorPane studentGrade_form;

// Major Instruments

 @FXML
private TextField majorInstrument_name;

@FXML
private ComboBox<String> majorInstrument_course;

@FXML
private TextField majorInstrument_percentage;

@FXML
private TextField majorInstrument_marks;

@FXML
private Button majorInstrument_addBtn;

@FXML
private Button majorInstrument_updateBtn;

@FXML
private Button majorInstrument_clearBtn;

@FXML
private Button majorInstrument_deleteBtn;

@FXML
private TableView<MajorInstrument> majorInstrument_tableView;

@FXML
private TableColumn<MajorInstrument, String> majorInstrument_col_name;

@FXML
private TableColumn<MajorInstrument, String> majorInstrument_col_course;

@FXML
private TableColumn<MajorInstrument, Double> majorInstrument_col_percentage;

@FXML
private TableColumn<MajorInstrument, Integer> majorInstrument_col_marks;

// Minor Instrument
@FXML
private TextField minorInstrument_name;

@FXML
private ComboBox<String> minorInstrument_majorInstrument;

@FXML
private ComboBox<String> minorInstrument_course;

@FXML
private TextField minorInstrument_percentage;

@FXML
private TextField minorInstrument_marks;

@FXML
private Button minorInstrument_addBtn;

@FXML
private Button minorInstrument_updateBtn;

@FXML
private Button minorInstrument_clearBtn;

@FXML
private Button minorInstrument_deleteBtn;

@FXML
private TableView<MinorInstrument> minorInstrument_tableView;

@FXML
private TableColumn<MinorInstrument, String> minorInstrument_col_name;

@FXML
private TableColumn<MinorInstrument, String> minorInstrument_col_course;

@FXML
private TableColumn<MinorInstrument, String> minorInstrument_col_majorInstrument;

@FXML
private TableColumn<MinorInstrument, Double> minorInstrument_col_percentage;

@FXML
private TableColumn<MinorInstrument, Integer> minorInstrument_col_marks;

// Event handler for course selection
@FXML
private void onCourseSelected() {
    // Fetch the selected course from the ComboBox
    String selectedCourse = minorInstrument_course.getValue();

    // Assuming you have a method in your database class to fetch majors based on course
    MajorInstrument majorInstrument = new MajorInstrument();
    List<String> majorsForCourse = majorInstrument.fetchMajorsForCourse(selectedCourse);

    // Populate the major ComboBox with the fetched majors
    minorInstrument_majorInstrument.setItems(FXCollections.observableArrayList(majorsForCourse));
}



// Student Grade

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

   
    private double x = 0;
    private double y = 0;

// Major Instrument

   public void majorInstrumentAdd() {
    Alert alert;

    String name = majorInstrument_name.getText();
    String course = majorInstrument_course.getValue();
    String percentageStr = majorInstrument_percentage.getText();
    String marksStr = majorInstrument_marks.getText();

    if (name.isEmpty() || course.isEmpty() || percentageStr.isEmpty() || marksStr.isEmpty()) {
        alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please fill all blank fields");
        alert.showAndWait();
    } else {
        try {
            double percentage = Double.parseDouble(percentageStr);
            int marks = Integer.parseInt(marksStr);

            if (MajorInstrument.isMajorInstrumentExist(name)) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Major Instrument: " + name + " already exists!");
                alert.showAndWait();
            } else {
                MajorInstrument majorInstrument = new MajorInstrument(name, course, percentage, marks);
                majorInstrument.insertMajorInstrument();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Added!");
                alert.showAndWait();

                // TO BECOME UPDATED OUR TABLEVIEW ONCE THE DATA WE GAVE SUCCESSFUL
                availableMajorShowListData();
                // TO CLEAR THE TEXT FIELDS
                majorInstrumentClear();
            }
        } catch (NumberFormatException e) {
            // Handle parsing errors
            e.printStackTrace();
        }
    }
}
    
   public void majorInstrumentUpdate() {
    Alert alert;

    if (majorInstrument_name.getText().isEmpty()
            || majorInstrument_course.getValue().isEmpty()
            || majorInstrument_percentage.getText().isEmpty()
            || majorInstrument_marks.getText().isEmpty()) {
        alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please fill all blank fields");
        alert.showAndWait();
    } else {
        alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to update Major Instrument: " + majorInstrument_name.getText() + "?");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get().equals(ButtonType.OK)) {
            // Assuming you have an instance of MajorInstrument class
            MajorInstrument majorInstrument = new MajorInstrument();
            majorInstrument.setName(majorInstrument_name.getText());
            majorInstrument.setCourse(majorInstrument_course.getValue());
            majorInstrument.setPercentage(Double.parseDouble(majorInstrument_percentage.getText()));
            majorInstrument.setMarks(Integer.parseInt(majorInstrument_marks.getText()));

            // Call the update method
            majorInstrument.updateMajorInstrument();

            // TO BECOME UPDATED OUR TABLEVIEW ONCE THE DATA WE GAVE SUCCESSFUL
            availableMajorShowListData();
            // TO CLEAR THE TEXT FIELDS
            majorInstrumentClear();
        }
    }
}

   public void deleteMajorInstrument() {
    try {
        Alert alert;

        if (majorInstrument_name.getText().isEmpty()
                || majorInstrument_course.getValue().isEmpty()
                || majorInstrument_percentage.getText().isEmpty()
                || majorInstrument_marks.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        } else {
            // ALL GOOD! PROCEED TO DELETE THE MAJOR INSTRUMENT
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to DELETE Instrument: " + majorInstrument_name.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.isPresent() && option.get().equals(ButtonType.OK)) {
                // Create an instance of MajorInstrumentDAO
                MajorInstrument majorInstrument= new MajorInstrument();

                // Call the deleteMajorInstrument method
                majorInstrument.deleteMajorInstrument(majorInstrument_name.getText());

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Deleted!");
                alert.showAndWait();

                // TO BECOME UPDATED OUR TABLEVIEW ONCE THE DATA WE GAVE SUCCESSFUL
                availableMajorShowListData();
                // TO CLEAR THE TEXT FIELDS
                majorInstrumentClear();
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

   public void majorInstrumentClear() {
        majorInstrument_name.setText("");
        majorInstrument_course.setValue("");
        majorInstrument_percentage.setText("");
        majorInstrument_marks.setText("");
    }

   private ObservableList<MajorInstrument> MajorInstrumentList = FXCollections.observableArrayList();

    public void availableMajorShowListData() {
        MajorInstrument majorInstrument = new MajorInstrument();
        MajorInstrumentList = majorInstrument.MajorInstrumentListData();
        majorInstrument_tableView.setItems(MajorInstrumentList);
    }

   public void availableMajorSelect() {
        MajorInstrument selectedInstrument = majorInstrument_tableView.getSelectionModel().getSelectedItem();

        if (selectedInstrument == null) {
            return;
        }

        majorInstrument_name.setText(selectedInstrument.getName());
        majorInstrument_course.setValue(selectedInstrument.getCourse());
        majorInstrument_percentage.setText(String.valueOf(selectedInstrument.getPercentage()));
        majorInstrument_marks.setText(String.valueOf(selectedInstrument.getMarks()));
    }


// Minor Instrument

public void minorInstrumentAdd() {
    Alert alert;

    String name = minorInstrument_name.getText();
    String majorInstrumentName = minorInstrument_majorInstrument.getValue();
    String courseName = minorInstrument_course.getValue();
    String percentageStr = minorInstrument_percentage.getText();
    String marksStr = minorInstrument_marks.getText();

    if (name.isEmpty() || majorInstrumentName.isEmpty() || courseName.isEmpty() || percentageStr.isEmpty() || marksStr.isEmpty()) {
        alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please fill all blank fields");
        alert.showAndWait();
    } else {
        try {
            double percentage = Double.parseDouble(percentageStr);
            int marks = Integer.parseInt(marksStr);

            // Assuming you have a method isMinorInstrumentExist in MinorInstrument class
            if (MinorInstrument.isMinorInstrumentExist(name)) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Minor Instrument: " + name + " already exists!");
                alert.showAndWait();
            } else {
                MinorInstrument minorInstrument = new MinorInstrument(name, majorInstrumentName, courseName, percentage, marks);
                minorInstrument.insertMinorInstrument();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Added!");
                alert.showAndWait();

                // TO BECOME UPDATED OUR TABLEVIEW ONCE THE DATA WE GAVE SUCCESSFUL
                availableMinorShowListData();
                // TO CLEAR THE TEXT FIELDS
                minorInstrumentClear();
            }
        } catch (NumberFormatException e) {
            // Handle parsing errors
            e.printStackTrace();
        }
    }
}

public void minorInstrumentUpdate() {
    Alert alert;

    if (minorInstrument_name.getText().isEmpty()
            || minorInstrument_majorInstrument.getValue().isEmpty()
            || minorInstrument_course.getValue().isEmpty()
            || minorInstrument_percentage.getText().isEmpty()
            || minorInstrument_marks.getText().isEmpty()) {
        alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please fill all blank fields");
        alert.showAndWait();
    } else {
        alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to update Minor Instrument: " + minorInstrument_name.getText() + "?");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get().equals(ButtonType.OK)) {
            // Assuming you have an instance of MinorInstrument class
            MinorInstrument minorInstrument = new MinorInstrument();
            minorInstrument.setName(minorInstrument_name.getText());
            minorInstrument.setMajorInstrumentName(minorInstrument_majorInstrument.getValue());
            minorInstrument.setCourseName(minorInstrument_course.getValue());
            minorInstrument.setPercentage(Double.parseDouble(minorInstrument_percentage.getText()));
            minorInstrument.setMarks(Integer.parseInt(minorInstrument_marks.getText()));

            // Call the update method
            minorInstrument.updateMinorInstrument();

            // TO BECOME UPDATED OUR TABLEVIEW ONCE THE DATA WE GAVE SUCCESSFUL
            availableMinorShowListData();
            // TO CLEAR THE TEXT FIELDS
            minorInstrumentClear();
        }
    }
}

public void deleteMinorInstrument() {
    try {
        Alert alert;

        if (minorInstrument_name.getText().isEmpty()
                || minorInstrument_majorInstrument.getValue().isEmpty()
                || minorInstrument_course.getValue().isEmpty()
                || minorInstrument_percentage.getText().isEmpty()
                || minorInstrument_marks.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        } else {
            // ALL GOOD! PROCEED TO DELETE THE MINOR INSTRUMENT
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to DELETE Instrument: " + minorInstrument_name.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.isPresent() && option.get().equals(ButtonType.OK)) {
                // Create an instance of MinorInstrument
                MinorInstrument minorInstrument = new MinorInstrument();

                // Call the deleteMinorInstrument method
                minorInstrument.deleteMinorInstrument(minorInstrument_name.getText());

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Deleted!");
                alert.showAndWait();

                // TO BECOME UPDATED OUR TABLEVIEW ONCE THE DATA WE GAVE SUCCESSFUL
                availableMinorShowListData();
                // TO CLEAR THE TEXT FIELDS
                minorInstrumentClear();
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void minorInstrumentClear() {
    minorInstrument_name.setText("");
    minorInstrument_course.setValue("");
    minorInstrument_majorInstrument.setValue("");
    minorInstrument_percentage.setText("");
    minorInstrument_marks.setText("");
}

private ObservableList<MinorInstrument> MinorInstrumentList = FXCollections.observableArrayList();

 public void availableMinorShowListData() {
        MinorInstrument minorInstrument = new MinorInstrument();
        MinorInstrumentList = minorInstrument.minorInstrumentListData();
        minorInstrument_tableView.setItems(MinorInstrumentList);
    }

public void availableMinorSelect() {
    MinorInstrument selectedInstrument = minorInstrument_tableView.getSelectionModel().getSelectedItem();

    if (selectedInstrument == null) {
        return;
    }

    minorInstrument_name.setText(selectedInstrument.getName());
    minorInstrument_majorInstrument.setValue(selectedInstrument.getMajorInstrumentName());
    minorInstrument_course.setValue(selectedInstrument.getCourseName());
    minorInstrument_percentage.setText(String.valueOf(selectedInstrument.getPercentage()));
    minorInstrument_marks.setText(String.valueOf(selectedInstrument.getMarks()));
}


//Result Methods


    private void updateTable() {
        String year = studentGrade_year.getSelectionModel().getSelectedItem();
        String course = studentGrade_course.getSelectionModel().getSelectedItem();
        if (year != null && course != null) {
            List<Result> students = Result.getStudents(year, course);
            ObservableList<Result> data = FXCollections.observableArrayList(students);
            studentGrade_tableView.setItems(data);
        }
    }


//    Dashboard Methods

    public void logout() {

        try {

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {

                //HIDE YOUR DASHBOARD FORM
                logout.getScene().getWindow().hide();

                //LINK YOUR LOGIN FORM 
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();

            } else {
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

   public void displayUsername(){
    if (username != null) {
        username.setText(getData.username);
    }}

    public void switchForm(ActionEvent event) {
        if (event.getSource() == enterMarks_btn) {
            enterMarksForm.setVisible(true);
            minorInstrument_form.setVisible(false);
            majorInstrument_form.setVisible(false);
            studentGrade_form.setVisible(false);

            enterMarks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            minorInstrument_btn.setStyle("-fx-background-color:transparent");
            majorInstrument_btn.setStyle("-fx-background-color:transparent");
            studentGrade_btn.setStyle("-fx-background-color:transparent");

        }else
        if (event.getSource() == minorInstrument_btn) {
            enterMarksForm.setVisible(false);
            minorInstrument_form.setVisible(true);
            majorInstrument_form.setVisible(false);
            studentGrade_form.setVisible(false);

            minorInstrument_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            enterMarks_btn.setStyle("-fx-background-color:transparent");
            majorInstrument_btn.setStyle("-fx-background-color:transparent");
            studentGrade_btn.setStyle("-fx-background-color:transparent");

        } 
        else if (event.getSource() == majorInstrument_btn) {
            enterMarksForm.setVisible(false);
            minorInstrument_form.setVisible(false);
            majorInstrument_form.setVisible(true);
            studentGrade_form.setVisible(false);

            majorInstrument_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            minorInstrument_btn.setStyle("-fx-background-color:transparent");
            enterMarks_btn.setStyle("-fx-background-color:transparent");
            studentGrade_btn.setStyle("-fx-background-color:transparent");


        } 
        else if (event.getSource() == studentGrade_btn) {
               enterMarksForm.setVisible(false);
               minorInstrument_form.setVisible(false);
               majorInstrument_form.setVisible(false);
               studentGrade_form.setVisible(true);

               studentGrade_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
               minorInstrument_btn.setStyle("-fx-background-color:transparent");
               majorInstrument_btn.setStyle("-fx-background-color:transparent");
               enterMarks_btn.setStyle("-fx-background-color:transparent");

        }
    }

    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    public void defaultNav(){
      majorInstrument_form.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    Platform.runLater(() -> {
        displayUsername();
    });

    defaultNav();
    
    

    // Major Initialization
    majorInstrument_course.setItems(FXCollections.observableArrayList(Result.getCourses()));
   

     // Initialize the TableView columns
        majorInstrument_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        majorInstrument_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        majorInstrument_col_percentage.setCellValueFactory(new PropertyValueFactory<>("percentage"));
        majorInstrument_col_marks.setCellValueFactory(new PropertyValueFactory<>("marks"));

    // Load data into the TableView
     availableMajorShowListData();

    //Minor Initialization
    minorInstrument_course.setItems(FXCollections.observableArrayList(Result.getCourses()));
    

       minorInstrument_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
       minorInstrument_col_course.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCourseName()));
       minorInstrument_col_majorInstrument.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMajorInstrumentName()));
       minorInstrument_col_percentage.setCellValueFactory(new PropertyValueFactory<>("percentage"));
       minorInstrument_col_marks.setCellValueFactory(new PropertyValueFactory<>("marks"));
    
    // Load data into the TableView
     availableMinorShowListData();

    // ResultUI initialization
        studentGrade_year.setItems(FXCollections.observableArrayList(Result.getYears()));
        studentGrade_course.setItems(FXCollections.observableArrayList(Result.getCourses()));

        studentGrade_col_studentNum.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentId()));
        studentGrade_col_year.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getYear()));
        studentGrade_col_course.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCourse()));
        studentGrade_col_firstSem.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMidTerm()));
        studentGrade_col_secondSem.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTerminal()));
        studentGrade_col_final.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFinalGrade()));

        studentGrade_year.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updateTable());
        studentGrade_course.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updateTable());
   
    }
}


