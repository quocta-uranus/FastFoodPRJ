/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java.model;

/**
 *
 * @author HAU
 */
public class MonthCount {
    private int year;
    private int month;
    private int date;
    private int count;

    public MonthCount(int year, int month, int date, int count) {
        this.year = year;
        this.month = month;
        this.date = date;
        this.count = count;
    }

    public MonthCount() {
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "MonthCount{" + "year=" + year + ", month=" + month + ", date=" + date + ", count=" + count + '}';
    }
    
}
