package edu.masanz.da.ut3.app;

import edu.masanz.da.ut3.modelo.MaquinaExpendedora;
import edu.masanz.da.ut3.modelo.Producto;
import edu.masanz.da.ut3.gui.GUI;

/** Menu de consola: la logica de las operaciones pertenece a las clases del modelo. */
public class Main {
    /** @param args argumentos no utilizados */
    public static void main(String[] args) {

        // TODO 31: Crea los productos iniciales y el objeto que gestiona sus ventas.
        Producto agua = new Producto("Agua", 100, 3);
        Producto zumo = new Producto("Zumo", 150, 2);
        Producto kitkat = new Producto("KitKat", 150, 0);

        MaquinaExpendedora vending24h = new MaquinaExpendedora(agua, zumo, kitkat);

        // TODO 32: Ofrece las opciones de GUI y permite realizar operaciones hasta elegir salir.


        int opcion = -1;

        while(opcion!=GUI.SALIR){
            // TODO 33: Gestiona cada solicitud mediante los objetos del modelo e informa de su resultado.
            GUI.mostrarMenu();
            opcion = GUI.pedirOpcion();
            switch (opcion){
                case GUI.CONSULTAR:
                    GUI.mostrarMensaje("--- ESTADO ---");
                    for (int i = 1; i <= 3; i++) {
                        Producto producto = vending24h.obtenerProducto(i);
                        GUI.mostrarProducto(i, producto.getNombre(), producto.getPrecioCentimos(), producto.getUnidades());
                    }
                    GUI.mostrarImporte("Saldo actual",vending24h.getSaldoCentimos());
                    GUI.mostrarImporte("Recaudación",vending24h.getRecaudacionCentimos());
                    break;
                case GUI.INTRODUCIR_DINERO:
                    int dinero = GUI.pedirEntero("Importe en centimos: ");
                    vending24h.introducirDinero(dinero);
                    break;
                case GUI.COMPRAR:
                    int numProducto = GUI.pedirEntero("Numero de producto (1-3)");
                    if(vending24h.comprar(numProducto)){
                        GUI.mostrarMensaje("Operacion realizada.");
                    } else {
                        GUI.mostrarMensaje("Operacion incorrecta.");
                    }
                    break;
                case GUI.DEVOLVER_SALDO:
                    int saldo = vending24h.devolverSaldo();
                    GUI.mostrarImporte("Saldo devuelto", saldo);
                    break;
                case GUI.REPONER:
                    int numP = GUI.pedirEntero("¿Qué producto? [1-3]: ");
                    int cant = GUI.pedirEntero("¿Cuántas unidades? [1 o más]: ");
                    if(vending24h.reponerProducto(numP, cant)){
                        GUI.mostrarMensaje("Operacion realizada.");
                    } else {
                        GUI.mostrarMensaje("Operacion incorrecta.");
                    }
                    break;
            }
        }

        // TODO 34: Muestra el estado tras cada solicitud y devuelve el saldo pendiente al salir.
        GUI.mostrarMensaje("--- ESTADO ---");
        for (int i = 1; i <= 3; i++) {
            Producto producto = vending24h.obtenerProducto(i);
            GUI.mostrarProducto(i, producto.getNombre(), producto.getPrecioCentimos(), producto.getUnidades());
        }
        int saldo = vending24h.devolverSaldo();
        GUI.mostrarImporte("Saldo devuelto", saldo);
        GUI.mostrarDespedida();

        // TODO 35: Muestra las existencias finales desde las referencias originales a los productos.
        GUI.mostrarProducto(1, agua.getNombre(), agua.getPrecioCentimos(), agua.getUnidades());
        GUI.mostrarProducto(2, zumo.getNombre(), zumo.getPrecioCentimos(), zumo.getUnidades());
        GUI.mostrarProducto(3, kitkat.getNombre(), kitkat.getPrecioCentimos(), kitkat.getUnidades());
    }
}
