package es.MiCafetera.www;

public class MiCafetera {
	private int vasos;
	private double agua;
	private double dineroRecaudado=0;
	private double capsulas [][] = new double[5][5];
	//constructor habra que pasarle dos enteros, numero de vasos, y litros de agua
	public MiCafetera(int vasos, double agua) {
		this.vasos = vasos;
		this.agua = agua;
		inicializarDatos();
	}
	//este metodo se encarga de rellenar de forma automatica la cafetera de forma predeterminada
	private void inicializarDatos () {
		//numero de capsulas
		capsulas[0][0] = 10;
		capsulas[1][0] = 10;
		capsulas[2][0] = 10;
		capsulas[3][0] = 10;
		capsulas[4][0] = 10;
		//Temperatura en grados
		capsulas[0][1] = 35;
		capsulas[1][1] = 45;
		capsulas[2][1] = 75;
		capsulas[3][1] = 85;
		capsulas[4][1] = 95;
		//tiempo en segundos
		capsulas[0][2] = 60;
		capsulas[1][2] = 45;
		capsulas[2][2] = 47;
		capsulas[3][2] = 90;
		capsulas[4][2] = 65;
		//agua
		capsulas[0][3] = 2.3;
		capsulas[1][3] = 0.8;
		capsulas[2][3] = 0.35;
		capsulas[3][3] = 0.25;
		capsulas[4][3] = 0.40;
		//valor
		capsulas[0][4] = 1;
		capsulas[1][4] = 0.5;
		capsulas[2][4] = 1.2;
		capsulas[3][4] = 1.1;
		capsulas[4][4] = 0.75;
	}
	//Este metodo lo he creado para asegurarme de que los datos estan bien insertados en el vector
	public void valores () {
		for (int i = 0; i < 5; i++) {
			System.out.println("------------------------");
			for (int j = 0; j < 5; j++) {
				System.out.println(capsulas[i][j]);

			}
		}

	}
	//Metodo que nos retorna false si es nivel de agua es menor que la cantidad de agua que necesita el cafe mas pequeño.
	public boolean comprobarAgua() {
		if (agua <0.25) {
			System.out.println("agua baja porfavor recarguela");
			return false;
		}
		return true;
	}
	//metodo que retorna un false si el agua que necesa el cafe es menor que el agua de la cafetera.
	private boolean aguaSiguienteCafe (int tipoCafe) {
		if(capsulas[tipoCafe][3]>agua) {
			System.out.println("agua insuficiente para este cafe");
			return false;
		}
		return true;
		//metodo que nos retorna false si los vasos son insuficientes.
	}
	public boolean vasosSuficiente () {
		if(vasos==0) {
			System.out.println("Vasos insuficiente porfavor ponga mas");
			return false;
		}
		return true;
	}
	//metodo que nos retorna un false si no quedan capsulas de ese cafe
	public boolean hayCapsulas(int tipoCafe) {
		if(capsulas[tipoCafe][0]==0) {
			System.out.println("No quedan capsulas: "+tipoCafe);
			return false;
		}

		return true;
	}
	//metodo que nos retorna false si ha ocurrido un error con alguna de las cualidades necesarias para hacer un cafe
	public boolean comprobarCafe(int tipoCafe) {
		if(aguaSiguienteCafe(tipoCafe)&& vasosSuficiente() && hayCapsulas(tipoCafe) && comprobarAgua())
			return true;
		return false;
	}
	//Metodo que retorna 0 si el cafe es servido con exito o 1 si ha habido algun error.
	public int pedirCafe(int tipoCafe) {
		if(comprobarCafe(tipoCafe)) {
			agua -= capsulas[tipoCafe][3];
			vasos -=1;
			capsulas[tipoCafe][0]-=1;
			return 0;
		}
		return 1;
	}
	//metodo que permite cambiar el numero de capsulas,  hayq que pasarle dos enteros: el cafe que se desea modificar 
	//y cuantas unidades se quieren poner.
	public void cambiarCapsulas(int cualCafe, int cuantas) {
		this.capsulas[cualCafe][0]+=cuantas;
		System.out.println("Capsulas del cafe: "+cualCafe+" actualizadas; actuales: "+ capsulas[cualCafe][0]);
	}
	//Metodo que nos acumula el precio del cafe siempre y cuando el cafe sea servido
	public double SumarDinero(int tipoCafe) {
		return dineroRecaudado += capsulas[tipoCafe][4];
	}
	//metodo que nos retorna el costo del cafe especiaficado
	public double valorCafePedido(int tipoCafe) {
		return capsulas[tipoCafe][4];
	}
	//getters and setters generados automaticamente
	public int getVasos() {
		return vasos;
	}
	public void setVasos(int vasos) {
		this.vasos = vasos;
	}
	public double getAgua() {
		return agua;
	}
	public void setAgua(double agua) {
		this.agua = agua;
	}
	public double getDineroRecaudado() {
		return dineroRecaudado;
	}
	public void setDineroRecaudado(double dineroRecaudado) {
		this.dineroRecaudado = dineroRecaudado;
	}
	public double getCapsulasRestantes (int tipoCafe) {
		return capsulas[tipoCafe][0];
	}



}
