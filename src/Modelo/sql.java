package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;

public class sql {
    Connection conectar=null;
    public Connection conectar(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conectar=DriverManager.getConnection(""
                    + "jdbc:mysql://duocsql.mysql.database.azure.com/examen","german@duocsql","#Loquesea2008");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return conectar;
    }
}