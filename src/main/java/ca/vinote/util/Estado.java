package ca.vinote.util;

public enum Estado {
	PENDIENTE_PAGO("PENDIENTE_PAGO"), 
	PENDIENTE_CONFIRMACION("PENDIENTE_CONFIRMACION"), 
	CONFIRMADO("CONFIRMADO"), 
	RECHAZADO("RECHAZADO");

	private final String val;

	Estado(String val) {
		this.val = val;
	}

	public String getEstado() {
		return val;
	}
}
