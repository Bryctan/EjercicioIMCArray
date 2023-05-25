import javax.swing.JOptionPane;

public class Procesos {
	String[] arrayNombres;
	String[] arrayTelefonos;
	String[] arrayEstado;
	double[] arrayImc;
	String nombreAct ="", menuActu = "";
	int posicion;

	public Procesos() {
		validarMenu();
	}

	private void validarMenu() {

		String menu = "";
		menu += "-----Bienvenido al menu de calculo de IMC-----\n";
		menu += "1. Ingresar Personas \n";
		menu += "2. Mostrar por nombre \n";
		menu += "3. Mostrar Lista Completa \n";
		menu += "4. Actualizar persona \n";
		menu += "5. Salir \n";

		int opcionMenu = 0;

		do {
			opcionMenu = Integer.parseInt(JOptionPane.showInputDialog(menu));

			switch (opcionMenu) {
			case 1:
				ingresarPersonas();
				break;
			case 2:
				if (arrayNombres == null) {
					JOptionPane.showMessageDialog(null, "No hay personas registradas");
				} else {
					imprimirPorNombre();
				}
				break;
			case 3:
				if (arrayNombres == null) {
					JOptionPane.showMessageDialog(null, "No hay personas registradas");
				} else {
					imprimirLista();
				}
				break;
			case 4:
				if (arrayNombres == null) {
					JOptionPane.showMessageDialog(null, "No hay personas registradas");
				} else {
					actualizarPersona();
				}
				break;
			case 5:
				JOptionPane.showMessageDialog(null, "¡Gracias por usar el programa!");
				break;
			default:
				JOptionPane.showMessageDialog(null, "¡Opción invalida!");
				break;
			}

		} while (opcionMenu != 5);

	}
	
	private void crearMenu() {
		menuActu = "¿Que desea actualizar? \n";
		menuActu += "1. Actualizar el nombre \n";
		menuActu += "2. Actualizar el telefono \n";
		menuActu += "3. Actualizar el nombre y el telefono \n";
		menuActu += "4 Salir \n";
		
	}

	private void actualizarPersona() {
		crearMenu();
		
		int opcion = Integer.parseInt(JOptionPane.showInputDialog(menuActu));
		
		switch (opcion) {
		case 1:
			actualizarNombre();
			break;
		case 2:
			actualizarTelefono();
			break;
		case 3:
			actualizarNombreYTelefono();
			break;
		case 4:
			break;
		default:
			System.out.println("Opcion invalida");
			break;
		}
		
	}
	

	private void imprimirLista() {
		for (int i = 0; i < arrayNombres.length; i++) {
			System.out.println("Persona = " + arrayNombres[i] + "\n" + "Telefono = " + arrayTelefonos[i] + "\n" + "Imc:"
					+ arrayImc[i] + "\n" + "Resultado = " + arrayEstado[i]);
		}

	}

	private void imprimirPorNombre() {
		String pregNombre = JOptionPane.showInputDialog("Ingrese el nombre de la persona a buscar").toLowerCase();

		int cantidad = 0;

		boolean registro = false;

		for (int i = 0; i < arrayNombres.length; i++) {
			if (arrayNombres[i].equalsIgnoreCase(pregNombre)) {
				cantidad++;
				System.out.println("La persona " + pregNombre + " tiene un estado de salud de " + arrayEstado[i]
						+ " En la posicion #" + i);
				System.out.println("La persona " + pregNombre + " se encontro " + cantidad + " vez/veces");

				registro = true;
			}
		}

		if (registro) {
			System.out.println("Persona encontrada");
		} else {
			JOptionPane.showMessageDialog(null, "La persona " + pregNombre + " no se encuentra registrada");
		}

	}

	private void ingresarPersonas() {
		double peso = 0, talla = 0, imc = 0;
		String telefono = "", nombre = "";

		int cantidadPersonas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de personas que desea calcular el IMC"));
		arrayNombres = new String[cantidadPersonas];
		arrayTelefonos = new String[cantidadPersonas];
		arrayEstado = new String[cantidadPersonas];
		arrayImc = new double[cantidadPersonas];

		for (int i = 0; i < cantidadPersonas; i++) {
			nombre = JOptionPane.showInputDialog("Ingrese su nombre").toLowerCase();
			telefono = JOptionPane.showInputDialog("Ingrese su telefono");
			peso = Double.parseDouble(JOptionPane.showInputDialog("Ingrese su peso"));
			talla = Double.parseDouble(JOptionPane.showInputDialog("Ingrese su talla"));
			arrayNombres[i] = nombre;
			arrayTelefonos[i] = telefono;
			imc = peso / (talla * talla);
			arrayImc[i] = imc;
			validarImc(imc, i);
		}
	}

	private void validarImc(double imc, int i) {
		String estadoSalud = "";
		if (imc < 18) {
			estadoSalud = "Anorexia";
		} else if (imc >= 18 && imc < 20) {
			estadoSalud = "Delgadez";
		} else if (imc >= 20 && imc < 27) {
			estadoSalud = "Normalidad";
		} else if (imc >= 27 && imc < 30) {
			estadoSalud = "Obecidad (Grado 1)";
		} else if (imc >= 30 && imc < 35) {
			estadoSalud = "Obecidad (Grado 2)";
		} else if (imc >= 35 && imc < 40) {
			estadoSalud = "Obecidad (Grado 3";
		} else if (imc >= 40) {
			estadoSalud = "Obecidad morbida";
		}
		arrayEstado[i] = estadoSalud;
	}
	
	private void actualizarNombre() {
		nombreAct = JOptionPane.showInputDialog("Ingrese el nombre que desea actualizar").toLowerCase();
		boolean actualizacion = false;
		int [] arrayPosiciones = new int [arrayNombres.length];
		String mensaje ="Se encontro la persona"+nombreAct+"en las siguientes posiciones; "+ "\n";
		for (int i = 0; i < arrayNombres.length; i++) {
			if (arrayNombres[i].equalsIgnoreCase(nombreAct)) {
				mensaje += "Posicion #"+i+" "+nombreAct+ " \n";
				arrayPosiciones[i] = i;
			} 
			else {
				actualizacion = false;
			}
			
		}
		mensaje += "Ingrese la posicion que desea actualizar";
		
		if (actualizacion != false ) {
	
			posicion = Integer.parseInt(JOptionPane.showInputDialog(mensaje));
			
			for (int i = 0; i < arrayPosiciones.length; i++) {
				if (arrayPosiciones[i] == posicion) {
					String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre");
					arrayNombres[i] = nuevoNombre;
					actualizacion = true;
				}
				
			}
			
		}
		
		if (actualizacion) {
			System.out.println("Actualizacion exitosa");
		}
		else {
			JOptionPane.showMessageDialog(null, "No se encuentra registrado en nombre de la persona: "+nombreAct);
		}
	}
	
	private void actualizarTelefono() {
		nombreAct = JOptionPane.showInputDialog("Ingrese el nombre que desea actualizar el telefono").toLowerCase();
		boolean actualizacion = false;
		int [] arrayPosiciones = new int [arrayNombres.length];
		String mensaje ="Se encontro la persona"+nombreAct+"en las siguientes posiciones; "+ "\n";;
		for (int i = 0; i < arrayNombres.length; i++) {
			if (arrayNombres[i].equalsIgnoreCase(nombreAct)) {
				mensaje += "Posicion #"+i+" "+nombreAct +"Telefono: "+arrayTelefonos[i]+"\n";
				actualizacion = true;
				arrayPosiciones[i] = i;
			} else {
				actualizacion = false;
			}
			
		}
		mensaje += "Ingrese la posicion que desea actualizar";
	
	if (actualizacion != false ) {	
		
		posicion = Integer.parseInt(JOptionPane.showInputDialog(mensaje));
	
		for (int i = 0; i < arrayPosiciones.length; i++) {
			if (arrayPosiciones[i] == posicion) {
				String nuevoTelefono = JOptionPane.showInputDialog("Ingrese el nuevo telefono");
				arrayTelefonos[i] = nuevoTelefono;
			}
		}
	}
		
		if (actualizacion) {
			System.out.println("Actualizacion exitosa");
		}
		else {
			JOptionPane.showMessageDialog(null, "No se encuentra registrado en nombre de la persona: "+nombreAct);
		}
	}
	
	private void actualizarNombreYTelefono() {
		nombreAct = JOptionPane.showInputDialog("Ingrese el nombre que desea actualizar el nombre y el telefono").toLowerCase();
		boolean actualizacion = false;
		int [] arrayPosiciones = new int [arrayNombres.length];
		String mensaje ="Se encontro la persona"+nombreAct+"en las siguientes posiciones; "+ "\n";
		for (int i = 0; i < arrayNombres.length; i++) {
			if (arrayNombres[i].equalsIgnoreCase(nombreAct)) {
				mensaje += "Posicion #"+i+" "+nombreAct +"Telefono: "+arrayTelefonos[i]+"\n";
				arrayPosiciones[i] = i;
			}
			else {
				actualizacion = false;
			}
			
		}
		mensaje += "Ingrese la posicion que desea actualizar";
		
		if (actualizacion != false ) {
			
			posicion = Integer.parseInt(JOptionPane.showInputDialog(mensaje));
			
			for (int i = 0; i < arrayPosiciones.length; i++) {
				if (arrayPosiciones[i] == posicion) {
					String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre");
					String nuevoTelefono = JOptionPane.showInputDialog("Ingrese el nuevo telefono");
					arrayNombres[i] = nuevoNombre;
					arrayTelefonos[i] = nuevoTelefono;
					actualizacion = true;

				}
			}
		}
			
		if (actualizacion) {
			System.out.println("Actualizacion exitosa");
		}
		else {
			JOptionPane.showMessageDialog(null, "No se encuentra registrado en nombre de la persona: "+nombreAct);
		}
	}
	
	
}
