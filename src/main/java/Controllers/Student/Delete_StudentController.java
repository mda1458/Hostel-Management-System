/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Student;

import DBConnection.DBHandler;
import Model.StudentDetails;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class Delete_StudentController implements Initializable {

    @FXML
    private TableView<StudentDetails> tableStudent;
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
    private TextField reg_txt_guardname;
    @FXML
    private TextField reg_txt_guardtel;
    @FXML
    private TextField reg_txt_email;
    @FXML
    private TextField reg_txt_phnmb;
    @FXML
    private TextField reg_txt_address;
    @FXML
    private TextField reg_txt_nic;
    @FXML
    private TextField reg_txt_cmsid;
    @FXML
    private TextField reg_txt_username;
    @FXML
    private TextField reg_txt_dept;
    @FXML
    private Button btn_delete_student;
    @FXML
    private Button btn_refersh;
    @FXML
    private DatePicker dateLeaved;
    @FXML
    private Button btn_back;

    // Initialize observable list to database
    private ObservableList<StudentDetails> data;

    /**
     * Initializes the controller class.
     */
    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;
    private String leavedID;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        handler = new DBHandler();
        reg_txt_nic.setDisable(false);
        reg_txt_cmsid.setDisable(false);
        reg_txt_guardname.setDisable(false);
        reg_txt_guardtel.setDisable(false);
        reg_txt_email.setDisable(false);
        reg_txt_phnmb.setDisable(false);
        reg_txt_address.setDisable(false);
        reg_txt_username.setDisable(false);
        reg_txt_dept.setDisable(false);
    }

    @FXML
    private void deleteStudentButtonAction(MouseEvent event) {
        updateLeavedStudentDB();

        String delete = "DELETE from register_students where CMS_ID = ?";
        connection = handler.connectDB();
        try {
            pst = connection.prepareStatement(delete);
            pst.setString(1, reg_txt_cmsid.getText());
            leavedID = reg_txt_cmsid.getText();
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Deleted Selected Student");
            clearFields();
            autoRefresh();

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    private void updateLeavedStudentDB() {
        String query = "INSERT INTO leaved_students (CMS_ID,Name,`Father Name`,Email,Phone,CNIC,Address,Department,Father_Phone,Leaved_Date) VALUES(?,?,?,?,?,?,?,?,?,?)";
        connection = handler.connectDB();
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, reg_txt_cmsid.getText());
            pst.setString(2, reg_txt_username.getText());
            pst.setString(3, reg_txt_guardname.getText());
            pst.setString(4, reg_txt_email.getText());
            pst.setString(5, reg_txt_phnmb.getText());
            pst.setString(6, reg_txt_nic.getText());
            pst.setString(7, reg_txt_address.getText());
            pst.setString(8, reg_txt_dept.getText());
            pst.setString(9, reg_txt_guardtel.getText());
            pst.setString(10, ((TextField)dateLeaved.getEditor()).getText());
            pst.executeUpdate();

            autoRefresh();

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    private void autoRefresh() {
        connection = handler.connectDB();
        data = FXCollections.observableArrayList();

        try {
            // Execure query
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM register_students");

            while (rs.next()) {
                // get string from db
                data.add(new StudentDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));

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

        tableStudent.setItems(null);
        tableStudent.setItems(data);
    }

    @FXML
    private void refreshButtionClickAction(MouseEvent event) {
        connection = handler.connectDB();
        data = FXCollections.observableArrayList();

        try {
            // Execure query
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM register_students");

            while (rs.next()) {
                // get string from db
                data.add(new StudentDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));

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

        tableStudent.setItems(null);
        tableStudent.setItems(data);
    }

    @FXML
    private void displaySelectedAction(MouseEvent event) {
        StudentDetails student = tableStudent.getSelectionModel().getSelectedItem();
        if (student == null) {
            JOptionPane.showMessageDialog(null, "Nothing Selected!");
        } else {
            String name = student.getName();
            String nsbmid = student.getNsbmId();
            String email = student.getEmail();
            String phonenumber = student.getPhoneNumber();
            String nic = student.getNIC();
            String address = student.getAddress();
            String g_name = student.getGuardName();
            String g_tel = student.getGuardTel();

            reg_txt_username.setText(name);
            reg_txt_cmsid.setText(nsbmid);
            reg_txt_email.setText(email);
            reg_txt_phnmb.setText(phonenumber);
            reg_txt_nic.setText(nic);
            reg_txt_address.setText(address);
            reg_txt_guardname.setText(g_name);
            reg_txt_guardtel.setText(g_tel);
        }
    }

    private void clearFields() {
        reg_txt_username.setText("");
        reg_txt_cmsid.setText("");
        reg_txt_email.setText("");
        reg_txt_phnmb.setText("");
        reg_txt_nic.setText("");
        reg_txt_address.setText("");
        reg_txt_guardname.setText("");
        reg_txt_guardtel.setText("");
        dateLeaved.setValue(null);
    }

    @FXML
    private void displaySelectedActio(MouseEvent event) {
        StudentDetails student = tableStudent.getSelectionModel().getSelectedItem();
        if (student == null) {
            JOptionPane.showMessageDialog(null, "Nothing Selected!");
        } else {
            String name = student.getName();
            String nsbmid = student.getNsbmId();
            String email = student.getEmail();
            String phonenumber = student.getPhoneNumber();
            String nic = student.getNIC();
            String address = student.getAddress();
            String g_name = student.getGuardName();
            String g_tel = student.getGuardTel();
            String dept = student.getdept();

            reg_txt_username.setText(name);
            reg_txt_cmsid.setText(nsbmid);
            reg_txt_email.setText(email);
            reg_txt_phnmb.setText(phonenumber);
            reg_txt_nic.setText(nic);
            reg_txt_address.setText(address);
            reg_txt_guardname.setText(g_name);
            reg_txt_guardtel.setText(g_tel);
            reg_txt_dept.setText(dept);
        }
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
