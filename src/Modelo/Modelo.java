/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class Modelo {   

    public Modelo(){ }
    
    sql conectara = new sql();
    //
    public boolean agregar(int codigo, String rut, String nombre, String apellido, int celular, String email, int sueldo, String estadocivil, String departamento){
        boolean valor = false;
        //Se arma la consulta
        String q=" INSERT INTO examen.empleados(codigo,rut,nombre,apellido,celular,email,sueldo_bruto,est_civil, nom_depto) "
                + "VALUES ( '" + codigo + "','" + rut + "','"+ nombre +"','" + apellido +"','" + celular +"','" + email +"','" + sueldo +"','" + estadocivil +"','" + departamento +"' );";
        //se ejecuta la consulta
        try {
            PreparedStatement pstm = conectara.conectar().prepareStatement(q);
            pstm.execute();
            pstm.close();
            valor = true;
        }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return valor;
    }
    
    public boolean eliminar(int codigo){
        boolean res=false;
        String q = " DELETE FROM examen.empleados WHERE codigo=" + codigo + "; " ;
        try {
            PreparedStatement pstm = conectara.conectar().prepareStatement(q);
            if (pstm.executeUpdate() == 1){
                res=true;
            }
            pstm.close();
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return res;
    }
    
    public boolean modificar(int codigo, String rut, String nombre, String apellido, int celular, String email, int sueldo, String estadocivil, String departamento){
        String q= "UPDATE examen.empleados SET rut='"+rut+"', nombre='"+nombre+"', apellido='"+apellido+"', celular='"+celular+"', email='"+email+"', sueldo_bruto='"+sueldo+"', est_civil='"+estadocivil+"', nom_depto='"+departamento+"' WHERE codigo='"+codigo+"';";
        try {
            PreparedStatement pstm = conectara.conectar().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return false;
    }
    
    public DefaultTableModel buscar(int codigo){
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"Código","Nombre","Apellido","Rut","Celular","Email", "Sueldo", "Estado Civil", "Departamento"};
      try{
         PreparedStatement pstm = conectara.conectar().prepareStatement( "SELECT count(*) as total FROM examen.empleados WHERE codigo ="+codigo+";");
         ResultSet res = pstm.executeQuery();
         
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
      
      Object[][] data = new String[registros][9];
      try{
         PreparedStatement pstm = conectara.conectar().prepareStatement("SELECT * FROM examen.empleados WHERE codigo ="+codigo+";");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "codigo" );
                data[i][1] = res.getString( "nombre" );
                data[i][2] = res.getString( "apellido" );
               // data[i][3] = nombre_categoria (Integer.parseInt(res.getString( "id_categoria" )));
                data[i][3] = res.getString( "rut" );
                data[i][4] = res.getString( "celular" );
                data[i][5] = res.getString( "email" );
                data[i][6] = res.getString( "sueldo_bruto" );
                data[i][7] = res.getString( "est_civil" );
                data[i][8] = res.getString( "nom_depto" );
            i++;
         }
         res.close();
         tablemodel.setDataVector(data, columNames );
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return tablemodel;
    }
    
    public DefaultTableModel mostrar(){
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"Código","Nombre","Apellido","Rut","Celular","Email", "Sueldo", "Estado Civil", "Departamento"};
      try{
         PreparedStatement pstm = conectara.conectar().prepareStatement( "SELECT count(*) as total FROM examen.empleados");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
      Object[][] data = new String[registros][9];
      try{
         PreparedStatement pstm = conectara.conectar().prepareStatement("SELECT * FROM examen.empleados");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){ 
                data[i][0] = res.getString( "codigo" );
                data[i][1] = res.getString( "nombre" );
                data[i][2] = res.getString( "apellido" );
               // data[i][3] = nombre_categoria (Integer.parseInt(res.getString( "id_categoria" )));
                data[i][3] = res.getString( "rut" );
                data[i][4] = res.getString( "celular" );
                data[i][5] = res.getString( "email" );
                data[i][6] = res.getString( "sueldo_bruto" );
                data[i][7] = res.getString( "est_civil" );
                data[i][8] = res.getString( "nom_depto" );
            i++;
         }
         res.close();
         tablemodel.setDataVector(data, columNames );
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return tablemodel;
    }
        
    //consulta1
   /*
public boolean Consulta1(int codigo, String nombre, int precio, String formato4k){
        if( valida_datos(codigo, nombre, precio) ) {
            //Se arma la consulta
            String q=" INSERT INTO taller3.pelicula(codigo,id_categoria,nombre,precio,formato4k) "
                    + "VALUES ( '" + codigo + "',5,'"+ nombre +"','" + precio +"','" + formato4k +"');"; // 5 -> CATEGORIA DRAMA
            //se ejecuta la consulta
            try {
                PreparedStatement pstm = conectara.conectar().prepareStatement(q);
                pstm.execute();
                pstm.close();
                return true;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
            }
            return false;
        }
        else
        return false;
    }
    
    //consulta 2
     public boolean Consulta2(int codigo, String nombre, int precio, String formato4k){
        if( valida_datos(codigo, nombre, precio) ) {
            //Se arma la consulta
            String q=" INSERT INTO taller3.pelicula(codigo,id_categoria,nombre,precio,formato4k) "
                    + "VALUES ( '" + codigo + "',6,'"+ nombre +"','" + precio +"','" + formato4k +"');"; // 6 -> CATEGORIA COMEDIA
            //se ejecuta la consulta
            try {
                PreparedStatement pstm = conectara.conectar().prepareStatement(q);
                pstm.execute();
                pstm.close();
                return true;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
            }
            return false;
        }
        else
        return false;
    }
     
     public DefaultTableModel Consulta4(){
     DefaultTableModel tablemodel = new DefaultTableModel();
     int registros = 0;
     String[] columNames = {"Código","Nombre","Categoría","Precio","Calidad 4k"};
     try{
        PreparedStatement pstm = conectara.conectar().prepareStatement( "SELECT count(*) as total FROM taller3.pelicula WHERE id_categoria = 7;"); //7 = romance
        ResultSet res = pstm.executeQuery();
        res.next();
        registros = res.getInt("total");
        res.close();
     }catch(SQLException e){
        System.err.println( e.getMessage() );
     }
     Object[][] data = new String[registros][9];
     try{
        PreparedStatement pstm = conectara.conectar().prepareStatement("SELECT * FROM taller3.pelicula WHERE id_categoria = 7;");//7 = romance
        ResultSet res = pstm.executeQuery();
        int i=0;
        while(res.next()){
               data[i][0] = res.getString( "codigo" );
               data[i][1] = res.getString( "nombre" );
               data[i][2] = nombre_categoria (Integer.parseInt(res.getString( "id_categoria" )));
               data[i][3] = res.getString( "precio" );
               data[i][4] = res.getString( "formato4k" );
           i++;
        }
        res.close();
        tablemodel.setDataVector(data, columNames );
        }catch(SQLException e){
           System.err.println( e.getMessage() );
       }
       return tablemodel;
   }
    
    public boolean Consulta5(){
        boolean res=false;
        String q = " DELETE FROM taller3.pelicula WHERE precio > 2000; " ;
        try {
            PreparedStatement pstm = conectara.conectar().prepareStatement(q);
            if (pstm.executeUpdate() == 1){
                res=true;
            }
            pstm.close();
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return res;
    }
    
    public boolean Consulta6(){
        String q= "UPDATE taller3.pelicula set nombre = REPLACE(nombre, nombre, CONCAT('P', nombre)) WHERE codigo > 0;";
        try {
            PreparedStatement pstm = conectara.conectar().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
         }catch(SQLException e){
            System.err.println( e.getMessage() );
            return false;
        }
        
    }
*/
}
