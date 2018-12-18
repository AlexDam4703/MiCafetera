package es.MiCafetera.www;

import java.util.Scanner;

public class TestMiCafetera {
	static final Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		//Diferentes variables que se usar en la ejecucion
		int vasos=10;
		double agua =2;
		int cafe=0;
		int opcion =0;
		String contraseniaPedida="";
		String contraseniaAdministrador="1234";
		String usuaroAdministrador ="administrador";
		String usuarioAministradorPedido="";
		//creado el objeto cafetera
		MiCafetera miCafetera = new MiCafetera(vasos,agua);
		//llamada para ver todos datos ingresados en la cafetera
		//miCafetera.valores();
		
		//llamadas al menu con sus diferentes opciones
		do {
			menu();
			System.out.println("¿Que desea hacer?");
			opcion = sc.nextInt();
			switch (opcion) {
			case 1:
				if(!miCafetera.vasosSuficiente())
					System.out.println();
				else {
					if(!miCafetera.comprobarAgua())
						System.out.println();
					else {
						System.out.println("Que cafe desea");
						System.out.println("Opciones:");
						System.out.println("0. solo");
						System.out.println("1. con leche");
						System.out.println("2. bombom");
						System.out.println("3. Cortado");
						System.out.println("4. te");
						cafe = sc.nextInt();
						if(pagar(miCafetera, cafe)) {

							if(miCafetera.pedirCafe(cafe)==0)
								System.out.println("Cafe servido con exito");
							System.out.printf("agua restante %.2f vasos restantes %d ",miCafetera.getAgua(),miCafetera.getVasos());
						}
					}

				}
				break;
			case 2:
				do {
					System.out.println("Ingrese el usuario (administrador): ");
					usuarioAministradorPedido = sc.next();
					System.out.println("Ingrese la contraseña para continuar o 0 para salir(1234)");
					contraseniaPedida= sc.next();
					if(contraseniaPedida.equalsIgnoreCase(contraseniaAdministrador)&&usuarioAministradorPedido.equalsIgnoreCase(usuaroAdministrador)) {
						System.out.println("Bienvenido");
						menuAdministrador(miCafetera);
						break;
					}
					if(Integer.parseInt(contraseniaPedida)!=0)
						System.out.println("Contraseña incorrecta o usuario, intento de nuevo o puse 0 para salir");
				}while(Integer.parseInt(contraseniaPedida)!=0);
				break;

			default:
				break;
			}

		} while (opcion != 3);
		System.out.println("Gracias por su compra");
		sc.close();
	}
	//metodo que nos retorna el menu
	public static void menu () {
		System.out.println("");
		System.out.println("------------------------ Bienvenido -----------------------------------");
		System.out.println("Puse 1 para pedir un nuevo cafe");
		System.out.println("Pulse 2 para acceder al modo administrador");
		System.out.println("Pulse 3 para salir");

	}
	//metodo que se encarga de la recepcion y contabilización del dinero
	public static boolean pagar (MiCafetera miCafetera,int cafe) {
		double euros= 0;
		double eurosRestantes =0;
		System.out.println("El valor de este cafe es de: "+miCafetera.valorCafePedido(cafe)+"€ (intruducir decimales con ',')");
		do {
			System.out.println("Porfavor ingrese el dinero(valor en euros); pulse 0 para cancelar");
			euros = sc.nextDouble();
			if(euros ==0.01 ||euros ==0.02 ||euros ==0.05 ||euros ==0.1 ||euros ==0.2 ||euros ==0.5 ||euros ==1 ||euros ==2) {
				eurosRestantes += euros;
				if(miCafetera.valorCafePedido(cafe)- eurosRestantes >0)
					System.out.printf("Le quedan por ingresar: %.2f€%n",miCafetera.valorCafePedido(cafe)-eurosRestantes);
				else {
					if(!(miCafetera.comprobarCafe(cafe)))
						System.out.println("Cafe no servido, reembolso de: "+eurosRestantes);
					else {
						miCafetera.SumarDinero(cafe);
						if(miCafetera.valorCafePedido(cafe)-eurosRestantes < 0)
						System.out.printf("Su cambio es de: %.2f%n",(eurosRestantes-miCafetera.valorCafePedido(cafe)));
						else { 	
						System.out.println("Su cambio son 0€");
						System.out.println("Gracias por su compra");
						}
						}
					return true;
				}
			}
			else 
				if(euros !=0)
					System.out.println("El valor no es correcto");
				else
					System.out.println("Operacion cancelada, reembolsado: " +eurosRestantes);

		}while(euros!=0);
		return false;
	}
	//menu del administrador con sus diferentes funciones
	public static void menuAdministrador (MiCafetera miCafetera) {
		int opcionAdministrador=0;
		do {
		System.out.println("¿Que desea gestionar?:");
		System.out.println("Pulse 1 para ver/ver el agua de la cafetera");
		System.out.println("Pulse 2 para ver/recargar las capsulas");
		System.out.println("Pulse 3 para ver/recargar los vasos");
		System.out.println("Pulse 4 para ver el dinero recaudado");
		System.out.println("Pulse 5 para recoger todo el dinero");
		System.out.println("Pulse 6 para salir del modo administrador");
		System.out.println("Pulse 7 para apagar el sistema");
		opcionAdministrador = sc.nextInt();
		switch (opcionAdministrador) {
		case 1:
			System.out.println("Agua actual: "+miCafetera.getAgua()+" ¿cuantos litros desea recargar?");
			miCafetera.setAgua(sc.nextDouble()+miCafetera.getAgua());
			System.out.println("Agua recargada; agua actual: " +miCafetera.getAgua());
			break;
		case 7:
			System.out.println("Cafetera apagada.");
			sc.close();
			System.exit(0);
			break;
		case 3: 
			System.out.println("Vasos actules: "+miCafetera.getVasos()+" Cuantos vasos decea poner?: ");
			miCafetera.setVasos(sc.nextInt()+miCafetera.getVasos());
			System.out.println("Vasos actualizados; vasos actuales: "+miCafetera.getVasos());
			break;
		case 4:
			System.out.println("Dinero actual recaudado: "+miCafetera.getDineroRecaudado());
			break;
		case 5:
			System.out.println("Dinero recogido: "+miCafetera.getDineroRecaudado());
			miCafetera.setDineroRecaudado(0);
			break;
		case 2:
			int cualCafe=0;
			int cuantas=0;
			System.out.println("Que cafe desea recargar?(solo hay 5 tipos)");
			cualCafe= sc.nextInt();
			System.out.println("Actualemnte quedan: "+miCafetera.getCapsulasRestantes(cualCafe));
			System.out.println("Cuantas desea recargar?");
			cuantas =sc.nextInt();
			miCafetera.cambiarCapsulas(cualCafe, cuantas);
		break;

		default:
			break;
		}
		}while(opcionAdministrador!=6);
	}
}