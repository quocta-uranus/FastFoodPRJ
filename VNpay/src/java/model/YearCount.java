/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java.model;

/**
 *
 * @author HAU
 */
public class YearCount {
     private int year;
     private int month;
     private int count;

    public YearCount(int year, int month, int count) {
        this.year = year;
        this.month = month;
        this.count = count;
    }

    public YearCount() {
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "YearCount{" + "year=" + year + ", month=" + month + ", count=" + count + '}';
    }
     
}
