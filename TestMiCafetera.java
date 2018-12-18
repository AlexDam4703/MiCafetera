package es.MiCafetera.www;

import java.util.Scanner;

public class TestMiCafetera {
	static final Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {

		int vasos=10;
		double agua =2;
		int cafe=0;
		int opcion =0;
		String contraseniaPedida="";
		String contraseniaAdministrador="1234";

		MiCafetera miCafetera = new MiCafetera(vasos,agua);
		//miCafetera.valores();
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
						cafe = sc.nextInt();
						if(pagar(miCafetera, cafe)) {

							if(miCafetera.pedirCafe(cafe)==0)
								System.out.println("Cafe servido con exito");
							System.out.println("agua restante "+miCafetera.getAgua()+" vasos: "+miCafetera.getVasos()+"");
						}
					}

				}
				break;
			case 2:
				do {
					System.out.println("Ingrese la contraseña para continuar o 0 para salir(1234)");
					contraseniaPedida= sc.next();
					if(contraseniaPedida.equalsIgnoreCase(contraseniaAdministrador)) {
						System.out.println("Bienvenido");
						menuAdministrador(miCafetera);
						break;
					}
					if(Integer.parseInt(contraseniaPedida)!=0)
						System.out.println("Contraseña incorrecta, intento de nuevo o puse 0 para salir");
				}while(Integer.parseInt(contraseniaPedida)!=0);
				break;

			default:
				break;
			}

		} while (opcion != 3);
		System.out.println("Gracias por su compra");
	}

	public static void menu () {
		System.out.println("");
		System.out.println("------------------------ Bienvenido -----------------------------------");
		System.out.println("Puse 1 para pedir un nuevo cafe");
		System.out.println("Pulse 2 para acceder al modo administrador");
		System.out.println("Pulse 3 para salir");

	}
	public static boolean pagar (MiCafetera miCafetera,int cafe) {
		double euros= 0;
		double eurosRestantes =0;
		System.out.println("El valor de este cafe es de: "+miCafetera.valorCafePedido(cafe)+"€");
		do {
			System.out.println("Porfavor ingrese el dinero(valor en euros); pulse 0 para cancelar");
			euros = sc.nextDouble();
			if(euros ==0.01 ||euros ==0.02 ||euros ==0.05 ||euros ==0.1 ||euros ==0.2 ||euros ==0.5 ||euros ==1 ||euros ==2) {
				eurosRestantes += euros;
				if(miCafetera.valorCafePedido(cafe)- eurosRestantes >0)
					System.out.printf("Le quedan por ingresar: %.2f€%n",miCafetera.valorCafePedido(cafe)-eurosRestantes);
				else {
					if(miCafetera.valorCafePedido(cafe)-eurosRestantes < 0)
						System.out.println("Su cambio es de : "+ (eurosRestantes-miCafetera.valorCafePedido(cafe)));
					else 
						System.out.println("Su cambio son 0€");
					System.out.println("Gracias por su compra");
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
	public static void menuAdministrador (MiCafetera miCafetera) {
		int opcionAdministrador=0;
		do {
		System.out.println("¿Que desea gestionar?:");
		System.out.println("Pulse 1 para llenar la cafetera de agua");
		System.out.println("Pulse 2 para recargar las capsulas");
		System.out.println("Pulse 3 para recargar los vasos");
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
