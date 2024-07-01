
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareapostgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Charo Holgado. Profesora de Acceso a Datos
 */
public class Frame extends javax.swing.JFrame {
   //cadena url de la base de datos frutería en el servidor local
    String url = "jdbc:postgresql://localhost/fruteria";
    /**
     * Creates new form NewJFrame
     */
    public Frame() throws SQLException {
        initComponents();
        //conexión con la base de datos
        Connection conn = null;
        try{
             conn = DriverManager.getConnection(url, "postgres", "diego");
               //Crear la base de datos fruteria si no existe
              Borrar_tabla(conn);
              Crear_Tipo(conn);
              Crear_Funcion(conn);
              Crear_Tabla(conn);
              Insertar_Registros(conn);
              Rellenar_ComboBox(conn);
          
        }catch(SQLException e){
              e.printStackTrace();
            
             
        }
        finally{
            conn.close();
        }
    }
  private static void Borrar_tabla(Connection conn) throws SQLException {
     
    //consulta
    String consulta = "DROP FUNCTION IF EXISTS mensaje()"; 
    try{    
    //comando auxiliar para ejecutar la consulta
        Statement sta = conn.createStatement();
    //ejecuta la consulta 
        sta.execute(consulta);
    //cierra el objeto auxiliar Statement
        sta.close();
      }catch(Exception e){
           e.printStackTrace();
      }
  } 
  private static void Crear_Tipo(Connection conn) throws SQLException {

    //consulta
        String consulta1 = "CREATE TYPE datos_empleado AS "
                + "(Cod_EMPLEADO integer, Nombre_empleado varchar(50), Fecha_Alta date, NIF varchar(9));";
        String consulta2 = "CREATE TYPE datos_direccion AS"
                + "(Calle varchar(50), Numero integer, Poblacion varchar(50), codigo_postal varchar(5));";
    try{   
    //comando auxiliar para ejecutar la consulta
        Statement sta = conn.createStatement();

    //ejecuta la consulta 
        sta.execute(consulta1);
        sta.execute(consulta2);

    //cierra el objeto auxiliar Statement
        sta.close();
    }catch(Exception e){
             e.printStackTrace();
    }
  }
   private static void Crear_Funcion(Connection conn) throws SQLException {

    //consulta
        String consulta = "CREATE FUNCTION mensaje(datos_empleado, datos_direccion) RETURNS varchar AS $$"
                + "SELECT $1.Nombre_empleado||' reside en calle '||$2.Calle||' en\n y número '||$2.Numero||' de '||$2.Poblacion|| "
                + "' con código postal '||$2.codigo_postal;$$"
                + "LANGUAGE SQL";
    try{       
    //comando auxiliar para ejecutar la consulta
    Statement sta = conn.createStatement();

    //ejecuta la consulta 
    sta.execute(consulta);

    //cierra el objeto auxiliar Statement
    sta.close();
    }catch(Exception e){
           e.printStackTrace();
    }
  }
   private static void Crear_Tabla(Connection conn) {

    //consulta
String consulta1 = "CREATE TABLE empleados "
        + "(cod_empleado serial PRIMARY KEY, datos datos_empleado, dir_lugar datos_direccion);";
    try{
    //comando auxiliar para ejecutar la consulta
        Statement sta = conn.createStatement();

    //ejecuta la consulta 
        sta.execute(consulta1);
       
    //cierra el objeto auxiliar
        sta.close();
    }catch(SQLException e){
        e.printStackTrace();
    }
  }
 private static void Insertar_Registros(Connection conn) throws SQLException {
    
    //consulta
        String consultaEMPLEADOS = "INSERT INTO empleados (datos, dir_lugar) VALUES(ROW(1,'JOSE','1999/02/12','111111111'), ROW('El Peral',12,'CACERES','10100'));"
                + "INSERT INTO empleados (datos, dir_lugar) VALUES(ROW(2,'ANTONIO','1999/12/02','111111112'), ROW('El Guindo',10,'PLASENCIA','10600'));"
                + "INSERT INTO empleados (datos, dir_lugar) VALUES(ROW(3,'MARIA','2009/02/12','111111113'), ROW('La Plaza',4,'CACERES','10100'));"
                + "INSERT INTO empleados (datos, dir_lugar) VALUES(ROW(4,'LISA','2000/1/05','111111114'), ROW('El Rio',5,'NAVALMORAL','10300'));"
                + "INSERT INTO empleados (datos, dir_lugar) VALUES(ROW(5,'JOSEMARIA','2014/02/15','111000101'), ROW('La Mano',6,'CACERES','10100'));"
                + "INSERT INTO empleados (datos, dir_lugar) VALUES(ROW(6,'CARLOS','2015/02/02','111111116'), ROW('Antonio Soria',7,'CACERES','10100'));";
    try{    
//comando auxiliar para ejecutar la consulta
        Statement sta = conn.createStatement();
    //ejecuta la consulta 
        sta.execute(consultaEMPLEADOS);
    //cierra el objeto auxiliar
        sta.close();
    }catch(SQLException e){
        e.printStackTrace();
    }
 }
  
  private void Rellenar_ComboBox(Connection conn) throws SQLException {

    //consulta SQL. En ComboBox los datos son String
    String consulta = "SELECT DISTINCT (dir_lugar).Poblacion FROM empleados ORDER BY (dir_lugar).Poblacion ASC";
//(datos).Nombre_Empleado
    //comando auxiliar para ejecutar la consulta
    try{
        Statement sta = conn.createStatement();

    //ejecuta la consulta para que devuelva un conjunto de registros
        ResultSet res = sta.executeQuery(consulta);
        jComboBox1.removeAllItems();
    //imprime el resultado
        while (res.next()){
            jComboBox1.addItem((String)res.getObject(1));        
        }
        res.close();
        sta.close();
    }catch(SQLException e){
        e.printStackTrace();
    }
   }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Elije el código de un empleado:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Púlsame para ver la dirección del empleado");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.setBackground(new java.awt.Color(102, 102, 102));
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setDisabledTextColor(new java.awt.Color(204, 0, 204));
        jTextField1.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         
    int filaSeleccionada = jTable1.getSelectedRow();
    Object empleadoId = 0;

    try {
      //si hay alguna fila seleccionada en la cuadrícula
      if (filaSeleccionada > -1) {
        //anota su clave principal (almacenada en la columna de índice 0)
        empleadoId = jTable1.getValueAt(filaSeleccionada, 0);
        
        //llama al método mensaje con la clave anotada
        mensaje(empleadoId);
      } else {
        //borra el mensaje publicitario de la caja de texto
        jTextField1.setText(null);
        //muestra un mensaje emergente
        JOptionPane.showMessageDialog(null, "¡Seleccione una población!");
      }
      
    } catch (SQLException e) {
      //depura la excepción
      e.printStackTrace();
    }
    }//GEN-LAST:event_jButton1ActionPerformed
private void mensaje(Object codempleado) throws SQLException {

    //conexión con la base de datos
    Connection conn = null;

    try {
  
      conn = DriverManager.getConnection(url, "postgres", "diego");

      //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
      //escribe una consulta SQL que devuelva el valor de la función
      //'publicidad', sobre la columna 'caracteristicas' de la vivienda
      //de clave principal recibida como parámetro
      String consulta = String.format("SELECT mensaje (datos, dir_lugar) FROM empleados "
              + "WHERE (datos).Cod_EMPLEADO = %s",jTable1.getValueAt(jTable1.getSelectedRow(), 0));
    
      //comando auxiliar para ejecutar la consulta
      Statement sta = conn.createStatement();

      //ejecuta la consulta
      ResultSet res = sta.executeQuery(consulta);
      //jTextField1.setVisible(false);
      jTextField1.setOpaque(true);
//muestra el resultado
      while (res.next()) {
        jTextField1.setText(res.getString(1));
      }
      sta.close();

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
      //cierra la conexión
      conn.close();
    }
  }

//Al seleccionar una población en el jComboBox, se genera un evento
    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        //anota el nombre de la  como Objeto para evitar conversiones
        Object nombrePoblacion = jComboBox1.getSelectedItem();
        Connection conn = null;
        //borra el mensaje 
            jTextField1.setText(null);
        try {
            conn = DriverManager.getConnection(url, "postgres", "diego");
      //método que rellena la tabla a partir de la conexión y la provincia
            rellenar_JTable(conn, nombrePoblacion);
        }catch (SQLException e) {
      //trata la excepción
            e.printStackTrace();
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void rellenar_JTable(Connection conn, Object poblacion) 
          throws SQLException {
    
        if (poblacion != null) {
      //cuadrícula por defecto
            DefaultTableModel modelo = new DefaultTableModel();
            jTable1.setModel(modelo);
            String consulta = String.format("SELECT * from empleados where (dir_lugar).Poblacion = "
                    + ""+"'"+jComboBox1.getSelectedItem()+"';");
      //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
      try{
        //comando auxiliar para ejecutar la consulta
            Statement sta = conn.createStatement();
        //ejecuta la consulta para que devuelva un conjunto de registros
            ResultSet res = sta.executeQuery(consulta);
        //estructura del conjunto del registros
            ResultSetMetaData rsMd = res.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
        //nombres de campo como encabezado de cuadrícula
            for (int i = 1; i <= cantidadColumnas; i++) {
                modelo.addColumn(rsMd.getColumnLabel(i));
            }    
        //mientras quedan registros por leer
            while (res.next()) {
        //array de objetos
                Object[] fila = new Object[cantidadColumnas];       
        //rellena los datos de la fila
                for (int i = 0; i < cantidadColumnas; i++) {
                    fila[i] = res.getObject(i + 1);
                }        
            //agrega el array a la cuadrícula
                modelo.addRow(fila);
            }
      //cierra los objetos auxiliares
            res.close();
            sta.close();
        }catch (SQLException e) {
      //trata la excepción
            e.printStackTrace();
        }finally{
          conn.close();
      }
     }
  }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Frame().setVisible(true);
                } catch (SQLException e) {
                     e.printStackTrace();
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
