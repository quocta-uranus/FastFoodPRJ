/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java.model;

/**
 *
 * @author Asus
 */
public class Customer {

    private int customer_id;
    private String customer_name;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String role;
    private String address;

    public Customer() {
    }

    public Customer(int customer_id, String customer_name, String username, String password, String email, String phoneNumber, String role, String address) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.address = address;
    }

    public Customer(String username, String password, String email, String phoneNumber, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Customer{" + "customer_id=" + customer_id + ", customer_name=" + customer_name + ", username=" + username + ", password=" + password + ", email=" + email + ", phoneNumber=" + phoneNumber + ", role=" + role + ", address=" + address + '}';
    }

}
