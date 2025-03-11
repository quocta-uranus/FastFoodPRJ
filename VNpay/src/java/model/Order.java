/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java.model;

/**
 *
 * @author Asus
 */
public class Order {

    private int order_id;
    private String date;
    private int customer_id;
    private int totalmoney;
    private int store_id;
    private String status;
    private String receiver_name;
    private String receiver_phone;
    private String receiver_address;
    private String paymentStatus;

    public Order() {
    }

    public Order(int order_id, String date, int customer_id, int totalmoney, int store_id, String status, String receiver_name, String receiver_phone, String receiver_address, String paymentStatus) {
        this.order_id = order_id;
        this.date = date;
        this.customer_id = customer_id;
        this.totalmoney = totalmoney;
        this.store_id = store_id;
        this.status = status;
        this.receiver_name = receiver_name;
        this.receiver_phone = receiver_phone;
        this.receiver_address = receiver_address;
        this.paymentStatus = paymentStatus;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(int totalmoney) {
        this.totalmoney = totalmoney;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public String getReceiver_phone() {
        return receiver_phone;
    }

    public void setReceiver_phone(String receiver_phone) {
        this.receiver_phone = receiver_phone;
    }

    public String getReceiver_address() {
        return receiver_address;
    }

    public void setReceiver_address(String receiver_address) {
        this.receiver_address = receiver_address;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "Order{" + "order_id=" + order_id + ", date=" + date + ", customer_id=" + customer_id + ", totalmoney=" + totalmoney + ", store_id=" + store_id + ", status=" + status + ", receiver_name=" + receiver_name + ", receiver_phone=" + receiver_phone + ", receiver_address=" + receiver_address + ", paymentStatus=" + paymentStatus + '}';
    }

}
