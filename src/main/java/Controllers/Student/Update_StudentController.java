/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Student;

import Model.StudentDetails;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import DBConnection.DBHandler;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class Update_StudentController implements Initializable {

    @FXML
    private TextField reg_txt_username;
    @FXML
    private TextField reg_txt_cmsid;
    @FXML
    private TextField reg_txt_email;
    @FXML
    private TextField reg_txt_phnmb;
    @FXML
    private TextField reg_txt_nic;
    @FXML
    private TextField reg_txt_address;
    @FXML
    private TextField reg_txt_guardname;
    @FXML
    private TextField reg_txt_guardtel;
    @FXML
    private TextField reg_txt_dept;
    @FXML
    private Button btn_update_student;

    @FXML
    private Button btn_refersh;
    // Initialize observable list to database
    private ObservableList<StudentDetails> data;

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
        handler = new DBHandler();
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
    private void updateStudentButtonAction(MouseEvent event) {
        String nsbmid = reg_txt_cmsid.getText();
        String userName = reg_txt_username.getText();
        String dept = reg_txt_dept.getText();
        String email = reg_txt_email.getText();
        String phoneNumber = reg_txt_phnmb.getText();
        String nic = reg_txt_nic.getText();
        String address = reg_txt_address.getText();
        String guardName = reg_txt_guardname.getText();
        String guardTel = reg_txt_guardtel.getText();

        if (userName.equals("")
                || nsbmid.equals("")
                || email.equals("")
                || phoneNumber.equals("")
                || nic.equals("")
                || address.equals("")
                || guardName.equals("")
                || guardTel.equals("")
                || dept.equals("")) {
            JOptionPane.showMessageDialog(null, "Some Fields are missing!");
        } else {
            String update = "UPDATE register_Students set  Name = ?, Email = ?,Phone_Number = ?,CNIC = ?,address = ?,Father_Name = ?,Father_contact = ?, Department = ?, CMS_ID=? where CMS_ID = '" + nsbmid + "' ";
            connection = handler.connectDB();
            try {
                pst = connection.prepareStatement(update);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                pst.setString(8, reg_txt_dept.getText());
                pst.setString(1, reg_txt_username.getText());
                pst.setString(2, reg_txt_email.getText());
                pst.setString(3, reg_txt_phnmb.getText());
                pst.setString(4, reg_txt_nic.getText());
                pst.setString(5, reg_txt_address.getText());
                pst.setString(6, reg_txt_guardname.getText());
                pst.setString(7, reg_txt_guardtel.getText());
                pst.setString(9,reg_txt_cmsid.getText());

                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Update Student!!");
                autoRefresh();
            } catch (SQLException ex) {
                Logger.getLogger(New_StudentController.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
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
