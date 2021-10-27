

/**
 * @descrition Clase que prueba la conexi�n a una BD utilizando la clase Driver
 *             Manager
 * @author Carlos
 * @date 23/10/2021
 * @version 1.0
 * @license GPLv3
 */

public class PruebaCafes {



	public static void main(String[] args) {

		Cafes miCafe = new Cafes();
		System.out.println("\nContents of CAFES table:");
		 miCafe.verTabla();
		miCafe.buscar("Colombian");
//		 El id de proveedor tiene que existir en la tabla proveedores
		miCafe.insertar("Nescafe", 49, 4.99f, 89, 94);
		// Comprobamos que se insert�
		miCafe.buscar("Nescafe");
		miCafe.borrar("Nescafe");

		// Comprobamos que se borr�
		miCafe.verTabla();

	}
}
