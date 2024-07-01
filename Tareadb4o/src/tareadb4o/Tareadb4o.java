/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareadb4o;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import java.util.Scanner;

/**
 *
 * @author diego
 */
public class Tareadb4o {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
                "vehiculo.db4o");
        Scanner sc = new Scanner(System.in);
        String input;
        //La base de datos física es el fichero "vehiculo.db4o" almacenado en la
        //carpeta raíz del proyecto
        try {
            almacenarVehiculo(db);
            System.out.println("Muestra los registros con Bastidor JTDKB20UX67000002, cilindrada 2500, "
                    + "carburante gasoil y distintivo_ambiental Eco");
            String vin = "JTDKB20UX67000002";
            int cil = 2500;
            String comb = "Gasoil";
            String da = "Eco";
            consultaVinJTD(db, vin, cil, comb, da);

            System.out.println("Pulsa intro para continuar");
            input = sc.nextLine();
            System.out.println("Muestra todos los registros");
            if (input.isEmpty()) {
                consultarVehiculos(db);
            }

            System.out.println("Pulsa intro para continuar");
            input = sc.nextLine();
            System.out.println("Muestra todos los registros con etiqueta Eco");
            if (input.isEmpty()) {
                consultaEco(db, da);
            }

        } finally {
            db.close(); //cerrar la conexión a bd
        }
    }

    //Método para mostrar objetos recuperados de la Base de Objetos
    public static void mostrarConsulta(ObjectSet resul) {
        System.out.println("Recuperados " + resul.size() + " Objetos");
        while (resul.hasNext()) {
            System.out.println(resul.next());
        }
    }

    //Método para almacenar datos en la Base de Objetos.
    public static void almacenarVehiculo(ObjectContainer db) {

        // Consulta para obtener el número de registros
        Query query = db.query();
        query.constrain(Object.class); // Constrain cualquier clase, ya que estamos interesados en contar todos los objetos
        ObjectSet<Object> result = query.execute();
        int numRegistros = result.size();

        if (numRegistros <= 0) {
            //se crean cuatro objetos tipo Vehiculo con valores asignados
            Vehiculo v1 = new Vehiculo("ABC-123", "1HGCM82633A400001", 1600, "Gasolina", "Cero emisiones");
            Vehiculo v2 = new Vehiculo("XYZ-789", "JTDKB20UX67000002", 2500, "Gasoil", "Eco");
            Vehiculo v3 = new Vehiculo("JKQ-876", "5N1AN0NU3AC000012", 3000, "Gasolina", "Cero emisiones");
            Vehiculo v4 = new Vehiculo("WXY-321", "3C4PDCAB6HT000011", 2000, "Gasolina", "Cero emisiones");
            Vehiculo v5 = new Vehiculo("OPQ-654", "JTHBK1EG4A23900010", 1800, "Gasoil", "Eco");
            //Persistir Objetos: almacenamos los objetos con el método store()
            db.store(v1);
            db.store(v2);
            db.store(v3);
            db.store(v4);
            db.store(v5);
        }

    }

    // Metodo para el apartado 1.1 registros con Bastidor JTDKB20UX67000002 , cilindrada 2500, 
    // carburante Gasoil y distintivo_ambiental Eco
    public static void consultaVinJTD(ObjectContainer db, String vin, int cil, String comb, String da) {
        Vehiculo v = new Vehiculo(null, vin, cil, comb, da); //prototipo de búsqueda
        ObjectSet res = db.queryByExample(v); //realización de consulta
        mostrarConsulta(res);//obtención de resultados
    }

    // Metodo para el apartado 1.2 Consulta de todos los vehiculos
    public static void consultarVehiculos(ObjectContainer db) {
        Vehiculo v = new Vehiculo(); //prototipo de búsqueda
        ObjectSet res = db.queryByExample(v); //realización de consulta
        mostrarConsulta(res);//obtención de resultados
    }

    // Metodo para el apartado 1.3 Todos los registros con distintivo ambiental eco
    public static void consultaEco(ObjectContainer db, String da) {
        Vehiculo v = new Vehiculo(null, null, 0, null, da); //prototipo de búsqueda
        ObjectSet res = db.queryByExample(v); //realización de consulta
        mostrarConsulta(res);//obtención de resultados
    }

}
