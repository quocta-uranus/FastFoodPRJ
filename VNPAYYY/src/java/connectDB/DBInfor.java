/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java.connectDB;

/**
 *
 * @author HAU
 */
public interface DBInfor {
     public static String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static String url ="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=KFCStore;encrypt=true;trustServerCertificate=true;";
    public static String user="sa";
    public static String pass ="admin";
}
