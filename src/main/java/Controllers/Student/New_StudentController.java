/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Student;

import DBConnection.DBHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author User
 */
public class New_StudentController implements Initializable {
    @FXML
    public TextField reg_txt_dept;
    @FXML
    private TextField reg_txt_username;
    @FXML
    private TextField reg_txt_nsbmid;
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


    /**
     * Initializes the controller class.
     */
    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;
    @FXML
    private Button btn_back;
    @FXML
    private Button btn_reg_student;
    @FXML
    private Button btn_cam_open;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        handler = new DBHandler();
    }

    @FXML
    private void registerButtonAction(MouseEvent event) {
        String userName = reg_txt_username.getText();
        String nsbmID = reg_txt_nsbmid.getText();
        String email = reg_txt_email.getText();
        String phoneNumber = reg_txt_phnmb.getText();
        String nic = reg_txt_nic.getText();
        String address = reg_txt_address.getText();
        String guardName = reg_txt_guardname.getText();
        String guardTel = reg_txt_guardtel.getText();
        String dept = reg_txt_dept.getText();

        if (userName.equals("")
                || nsbmID.equals("")
                || email.equals("")
                || phoneNumber.equals("")
                || nic.equals("")
                || address.equals("")
                || guardName.equals("")
                || guardTel.equals("")
                || dept.equals("")) {
            JOptionPane.showMessageDialog(null, "All Fields Are Required!");

        } else {
            String insert = "INSERT INTO register_Students(Name,Father_Name,CMS_ID,Email,Phone_Number,Department,CNIC,Father_contact,Address)" + "Value(?,?,?,?,?,?,?,?,?)";
            connection = DBHandler.connectDB();
            try {
//                assert connection != null;
                pst = connection.prepareStatement(insert);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                pst.setString(1, userName);
                pst.setString(2, guardName);
                pst.setString(3, nsbmID);
                pst.setString(4, email);
                pst.setString(5, phoneNumber);
                pst.setString(6, dept);
                pst.setString(7, nic);
                pst.setString(8, guardTel);
                pst.setString(9, address);

                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Registered!");

            } catch (SQLException ex) {
                Logger.getLogger(New_StudentController.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    @FXML
    private void back_btn_clicked(MouseEvent event) throws IOException {
        btn_back.getScene().getWindow().hide();

        Stage stu_Menu = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/Student/Student_Menu.fxml")));
        Scene scene = new Scene(root);
        stu_Menu.initStyle(StageStyle.TRANSPARENT);
        stu_Menu.setScene(scene);
        stu_Menu.show();
        stu_Menu.setResizable(false);
    }



}
