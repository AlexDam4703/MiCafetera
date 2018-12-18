package es.MiCafetera.www;

public class MiCafetera {
	private int vasos;
	private double agua;
	private double dineroRecaudado=0;
	private double capsulas [][] = new double[5][5];
	//constructor
	public MiCafetera(int vasos, double agua) {
		this.vasos = vasos;
		this.agua = agua;
		inicializarDatos();
	}
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
	public void valores () {
		for (int i = 0; i < 5; i++) {
			System.out.println("------------------------");
			for (int j = 0; j < 5; j++) {
				System.out.println(capsulas[i][j]);

			}
		}

	}
	public boolean comprobarAgua() {
		if (agua <0.25) {
			System.out.println("agua baja porfavor recarguela");
			return false;
		}
		return true;
	}
	private boolean aguaSiguienteCafe (int tipoCafe) {
		if(capsulas[tipoCafe][3]>agua) {
			System.out.println("agua insuficiente para este cafe");
			return false;
		}
		return true;
	}
	public boolean vasosSuficiente () {
		if(vasos==0) {
			System.out.println("Vasos insuficiente porfavor ponga mas");
			return false;
		}
		return true;
	}
	public boolean hayCapsulas(int tipoCafe) {
		if(capsulas[tipoCafe][0]==0) {
			System.out.println("No quedan capsulas: "+tipoCafe);
			return false;
	}
			
		return true;
	}
	private boolean comprobarCafe(int tipoCafe) {
		if(aguaSiguienteCafe(tipoCafe)&& vasosSuficiente() && hayCapsulas(tipoCafe))
			return true;
		return false;
	}
	public int pedirCafe(int tipoCafe) {
		if(comprobarCafe(tipoCafe)) {
			agua -= capsulas[tipoCafe][3];
			vasos -=1;
			capsulas[tipoCafe][0]-=1;
			SumarDinero(tipoCafe);
			return 0;
		}
		return 1;
	}
	

	public void cambiarCapsulas(int cualCafe, int cuantas) {
		this.capsulas[cualCafe][0]+=cuantas;
		System.out.println("Capsulas del cafe: "+cualCafe+" actualizadas; actuales: "+ capsulas[cualCafe][0]);
	}
	private double SumarDinero(int tipoCafe) {
		return dineroRecaudado += capsulas[tipoCafe][4];
	}
	public double valorCafePedido(int tipoCafe) {
		return capsulas[tipoCafe][4];
	}
	
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

	
	
}



