/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java.model;

/**
 *
 * @author Asus
 */
public class Store {

    private int store_id;
    private String store_name;
    private String address;
    private String ocTime;
    private String image;
    private String store_PhoneNumber;
    private int revenue;

    public Store() {
    }

    public Store( int store_id,String store_name, String address, int revenue) {
        this.store_id = store_id;
        this.store_name = store_name;
        this.address = address;
        this.revenue = revenue;
    }

    public Store(int store_id, String store_name, String address, String ocTime, String image, String store_PhoneNumber, int revenue) {
        this.store_id = store_id;
        this.store_name = store_name;
        this.address = address;
        this.ocTime = ocTime;
        this.image = image;
        this.store_PhoneNumber = store_PhoneNumber;
        this.revenue = revenue;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getStore_id() {
        return store_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOcTime() {
        return ocTime;
    }

    public void setOcTime(String ocTime) {
        this.ocTime = ocTime;
    }

    public String getStore_PhoneNumber() {
        return store_PhoneNumber;
    }

    public void setStore_PhoneNumber(String store_PhoneNumber) {
        this.store_PhoneNumber = store_PhoneNumber;
    }

    @Override
    public String toString() {
        return "Store{" + "store_id=" + store_id + ", store_name=" + store_name + ", address=" + address + ", ocTime=" + ocTime + ", image=" + image + ", store_PhoneNumber=" + store_PhoneNumber + ", revenue=" + revenue + '}';
    }

}
