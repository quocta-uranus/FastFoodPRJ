/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java.model;

/**
 *
 * @author Asus
 */
public class Staff {

    private int staff_id;
    private String username;
    private String password;
    private String role;
    private int store_id;

    public Staff(int staff_id, String username, String password, String role, int store_id) {
        this.staff_id = staff_id;
        this.username = username;
        this.password = password;
        this.store_id = store_id;
        this.role = role;
    }

    public Staff() {
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    @Override
    public String toString() {
        return "Staff{" + "staff_id=" + staff_id + ", username=" + username + ", password=" + password + ", role=" + role + ", store_id=" + store_id + '}';
    }

}
