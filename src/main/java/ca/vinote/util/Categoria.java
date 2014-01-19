package ca.vinote.util;

public enum Categoria {
	TINTO("tinto"), 
	BLANCO("blanco"), 
	ROSADO("rosado");

	private final String val;

	Categoria(String val) {
		this.val = val;
	}

	public String getCategoria() {
		return val;
	}
	
	public static void main(String[] args) {
		System.out.println("Prueba: " + Categoria.valueOf("TINTO"));
		System.out.println("Prueba: " + Categoria.valueOf("cosa"));
	}
}
