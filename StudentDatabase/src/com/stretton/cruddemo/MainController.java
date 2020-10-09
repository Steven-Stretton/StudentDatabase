package com.stretton.cruddemo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

/**
 *
 * @author Steven Stretton
 */
public class MainController implements Initializable {

    TextField[] fields;
    @FXML
    private TextField tfStudentID;
    @FXML
    private TextField tfFirstName;
    @FXML
    private TextField tfSurname;
    @FXML
    private TextField tfYearOfStudy;
    @FXML
    private TextField tfGender;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfContact;
    @FXML
    private TextField tfAddress;

    @FXML
    private TableView<Students> tvStudents;

    @FXML
    private TableColumn<Students, Integer> colStudentID;
    @FXML
    private TableColumn<Students, String> colFirstName;
    @FXML
    private TableColumn<Students, String> colSurname;
    @FXML
    private TableColumn<Students, Integer> colYearOfStudy;
    @FXML
    private TableColumn<Students, String> colGender;
    @FXML
    private TableColumn<Students, String> colEmail;
    @FXML
    private TableColumn<Students, String> colContact;
    @FXML
    private TableColumn<Students, String> colAddress;

    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;

    @FXML
    private void handleButtonAction(ActionEvent event) {

        if (event.getSource() == btnInsert) {
            insertStudents();
        } else if (event.getSource() == btnUpdate) {

        } else if (event.getSource() == btnDelete) {

        } else {
            System.out.println("Something went wrong");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showStudents();
        fields = new TextField[] {tfStudentID, tfFirstName, tfSurname, tfYearOfStudy, tfGender, tfEmail, tfContact, tfAddress};
    }

    public Connection getConnection(){
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crud?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "mexico");
            return connection;
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
            return null;
        }
    }

    public void showStudents(){
        ObservableList<Students> studentsList = getStudentsList();

        colStudentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        colYearOfStudy.setCellValueFactory(new PropertyValueFactory<>("yearOfStudy"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        tvStudents.setItems(studentsList);
    }

    public void insertStudents() {
        for(TextField textField : fields) {
            if (textField.getText().trim().equals("")) {
                textField.setStyle("-fx-text-fill: red");
                return;
            }
        }

        //HEREEEEEEEEEE
        String query = "INSERT INTO students VALUES (" + colStudentID.getText() + "," + tfFirstName.getText() + "," + tfSurname.getText() + "," + tfYearOfStudy.getText() + "," + tfGender.getText() + "," + tfEmail.getText() + "," + tfContact.getText() + "," + tfAddress.getText() + ")";
        executeQuery(query);
        showStudents();
    }

    private void executeQuery(String query) {
        Connection connection = getConnection();
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Students> getStudentsList() {
        ObservableList<Students> studentsList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM students";
        Statement statement;
        ResultSet resultSet;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            Students students;

            while (resultSet.next()) {
                students = new Students(resultSet.getInt("studentID"), resultSet.getString("firstName"), resultSet.getString("surname"), resultSet.getInt("yearOfStudy"), resultSet.getString("gender"), resultSet.getString("email"), resultSet.getString("contact"), resultSet.getString("address"));
                studentsList.add(students);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentsList;
    }
}
