/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Partíendo de la base de datos Tienda, ya creada en el script Tienda.sql, y
 * compuesta de las tablas Artículos y Fabricante.
 *
 * Hacer un programa en Java, que cree una tabla ArtFab que contendrá el nombre
 * del artículo, el nombre del fabricante, el precio y una nueva columna
 * denominada iva que será el 10% del precio de aquellos artículos que cuesten
 * menos de 200 €, el 8% del precio de aquellos artículos que cuesten menos de
 * 500€, el 6% para aquellos artículos que cuesten menos de 700€ y el resto se
 * quedan como están.
 *
 * Finalmente, sacar un listado de toda la información contenida en la nueva
 * tabla.
 *
 * @author Morad
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        Connection conn = null; //objeto para hacer conexion
        conn = conexion();//conectamos con la ayuda del metodo conexion

        if (conn != null) {
            System.out.println("conectado correctamente");

            Statement st = conn.createStatement();

            //sentencia para crear la nueva tabla
            String cadena = "CREATE TABLE IF NOT EXISTS ArtFab("
                     +" Id int primary key AUTO_INCREMENT,"
                    + " NombreArticulo varchar(30),"
                    + " NombreFabricante varchar(30),"
                    + " Precio int,"
                    + " Iva decimal);";
                   
            //ejecutamos la secuencia
            st.execute(cadena);
            //sentencia para sacar los datos de las tablas, se utilizan alias    
            cadena ="SELECT art.Nombre, art.Precio, fab.Nombre as NombreFabricante from fabricantes as fab, articulos as art where art.CLFAB=fab.CLFAB;";
            
            ResultSet rs = st.executeQuery(cadena);
            Statement st1 =conn.createStatement();
            
                String NombreArticulo;
                String NombreFabricante;
                int precio;
                Double iva;
            
            while(rs.next()){
                
                 NombreArticulo = rs.getString("Nombre");
                 NombreFabricante = rs.getString("NombreFabricante");
                 precio =  rs.getInt("Precio");
                 
           
                 
                 iva = 0.0;
                
                if(precio<=200){
                    
                    iva = (double)(precio*10)/100;
                    
                }else if(precio<=500){
                    
                    iva= (double)(precio*8)/100;
                    
                }else if(precio<=700){
                    
                    iva =(double) (precio*6)/100;
                    
                }else{
                    
                    iva=0.0;
                    
                }
              cadena= "INSERT INTO ArtFab (NombreArticulo,NombreFabricante,Precio,Iva) values"
                    + "('"+NombreArticulo+"','"+NombreFabricante+"',"+precio+","+iva+");";
              
              st1.executeUpdate(cadena);
              System.out.println("Datos insertados correctamente");
          
            
            System.out.println("Nombre Articulo: "+NombreArticulo+"   Nombre Fabricante: "+NombreFabricante+"  Precio: "+precio+"  Iva: "+iva);
           
            }
            
            
            st.close();
            st1.close();
            rs.close();
            //cerramos la conexion, llamando al metodo
            cerrarConexion(conn);

        } else {
            System.out.println("no se ha podido conectar");
        }
    }

    public static Connection conexion() {

        String bbdd = "jdbc:mysql://localhost/tienda";//se usa la base de datos america,facilitada por el script
        String usuario = "root";
        String clave = "";
        Connection conn = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(bbdd, usuario, clave);

        } catch (Exception ex) {
            System.out.println("Error al conectar con la base de datos.\n"
                    + ex.getMessage().toString());
        }

        return conn;
    }

    //metodo para cerrar la conexion a la bbdd
    public static void cerrarConexion(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("la conexion no se ha cerrado");
            System.out.println(e.getMessage().toString());
        }
    }

}
