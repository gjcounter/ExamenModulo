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
    
    // consulta 2 -> mostrar empleados de redes
    public DefaultTableModel mostrar_redes(){
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"Código","Nombre","Apellido","Rut","Celular","Email", "Sueldo", "Estado Civil", "Departamento"};
      try{
         PreparedStatement pstm = conectara.conectar().prepareStatement( "SELECT count(*) as total FROM examen.empleados WHERE nom_depto = 'Redes';");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
      Object[][] data = new String[registros][9];
      try{
         PreparedStatement pstm = conectara.conectar().prepareStatement("SELECT * FROM examen.empleados WHERE nom_depto = 'Redes';");
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
    
    //consulta 3 -> eliminar empleados con sueldo = 120000
    public boolean eliminar_120(){
        boolean res=false;
        String q = " DELETE FROM examen.empleados WHERE sueldo_bruto = 120000; " ;
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
    
    //consulta 4 -> aumentar sueldos en 10%
    public boolean aumentar_10(){
        String q= "UPDATE examen.empleados set sueldo_bruto = sueldo_bruto*1.1";
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
}
