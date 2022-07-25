/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author User
 */
public class LeavedStudentDetails {
    // model class holding getters, setters and properties
    private StringProperty id;
    private StringProperty name;
    private StringProperty nsbmid;
    private StringProperty email;
    private StringProperty phoneNumber;
    private StringProperty nic;
    private StringProperty address;
    private StringProperty guardName;
    private StringProperty guardTel;
    private StringProperty dept;
    private StringProperty date;
    
    public LeavedStudentDetails(String name, String guardName, String nsbmid, String email, String phoneNumber, String dept, String nic, String guardTel, String address,String date)
    {
        this.name = new SimpleStringProperty(name);
        this.nsbmid = new SimpleStringProperty(nsbmid);
        this.email = new SimpleStringProperty(email);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.nic = new SimpleStringProperty(nic);
        this.address = new SimpleStringProperty(address);
        this.guardName = new SimpleStringProperty(guardName);
        this.guardTel = new SimpleStringProperty(guardTel);
        this.dept = new SimpleStringProperty(dept);
        this.date = new SimpleStringProperty(date);
    }
    
    // Getters
    public String getName() {
        return name.get();
    }

    public String getNsbmId() {
        return nsbmid.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public String getNIC() {
        return nic.get();
    }

    public String getAddress() {
        return address.get();
    }

    public String getGuardName() {
        return guardName.get();
    }

    public String getGuardTel() {
        return guardTel.get();
    }

    public String getdept() {return dept.get(); }

    public String getDate() {
        return date.get();
    }

    // Setters
    public void setName(String value) {
        name.set(value);
    }

    public void setNsbmid(String value) {
        nsbmid.set(value);
    }

    public void setEmail(String value) {
        email.set(value);
    }

    public void setPhoneNumber(String value) {
        phoneNumber.set(value);
    }

    public void setNic(String value) {
        nic.set(value);
    }

    public void setAddress(String value) {
        address.set(value);
    }

    public void setGuardName(String value) {
        guardName.set(value);
    }

    public void setGuardTel(String value) {
        guardTel.set(value);
    }

    public void setDept(String value) { dept.set(value);}
    
    public void setDate(String value) {
        date.set(value);
    }
    
    // Propert values

    public StringProperty nameProperty() { return name; }

    public StringProperty nsbmIdProperty() { return nsbmid; }

    public StringProperty emailProperty() { return email; }

    public StringProperty phoneNumberProperty() { return phoneNumber; }

    public StringProperty nicProperty() { return nic; }

    public StringProperty addressProperty() { return address; }

    public StringProperty guardNameProperty() { return guardName; }

    public StringProperty guardTelProperty() { return guardTel; }

    public StringProperty deptProperty() { return dept;}
    
    public StringProperty DateProperty() { return date; }
    
}
