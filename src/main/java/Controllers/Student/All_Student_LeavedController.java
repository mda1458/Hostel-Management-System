/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Student;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Model.StudentDetails;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

import DBConnection.DBHandler;
import Model.LeavedStudentDetails;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author User
 */
public class All_Student_LeavedController implements Initializable {

    // Initialize observable list to database
    private ObservableList<LeavedStudentDetails> data;

    @FXML
    private TableView<LeavedStudentDetails> tableStudent;
    @FXML
    private TableColumn<StudentDetails, String> col_dept1;
    @FXML
    private TableColumn<StudentDetails, String> col_name1;
    @FXML
    private TableColumn<StudentDetails, String> col_nsbmid1;
    @FXML
    private TableColumn<StudentDetails, String> col_email1;
    @FXML
    private TableColumn<StudentDetails, String> col_phonenumber1;
    @FXML
    private TableColumn<StudentDetails, String> col_nic1;
    @FXML
    private TableColumn<StudentDetails, String> col_address1;
    @FXML
    private TableColumn<StudentDetails, String> col_g_name1;
    @FXML
    private TableColumn<StudentDetails, String> col_g_tel1;
    @FXML
    private TableColumn<StudentDetails, String> col_leftdate;


    /**
     * Initializes the controller class.
     */
    
    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;
    @FXML
    private Button btn_back;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        connection = handler.connectDB();
        data = FXCollections.observableArrayList();

        try {
            // Execure query
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM leaved_students");

            while (rs.next()) {
                // get string from db
                data.add(new LeavedStudentDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),rs.getString(10)));

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        // set cell values
        col_nsbmid1.setCellValueFactory(new PropertyValueFactory<>("nsbmId"));
        col_name1.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_g_name1.setCellValueFactory(new PropertyValueFactory<>("guardName"));
        col_email1.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_phonenumber1.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        col_nic1.setCellValueFactory(new PropertyValueFactory<>("nic"));
        col_dept1.setCellValueFactory(new PropertyValueFactory<>("dept"));
        col_g_tel1.setCellValueFactory(new PropertyValueFactory<>("guardTel"));
        col_address1.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_leftdate.setCellValueFactory(new PropertyValueFactory<>("date"));

        tableStudent.setItems(null);
        tableStudent.setItems(data);
    }    

    @FXML
    private void back_btn_clicked(MouseEvent event) throws IOException {
        btn_back.getScene().getWindow().hide();

        Stage stu_Menu = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Student/Student_Menu.fxml"));
        Scene scene = new Scene(root);
        stu_Menu.setScene(scene);
        stu_Menu.initStyle(StageStyle.TRANSPARENT);
        stu_Menu.show();
        stu_Menu.setResizable(false);
    }
    
}
