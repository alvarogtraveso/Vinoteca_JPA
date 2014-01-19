package ca.vinote.util;

public enum NivelUsuario {
	ADMIN("ADMIN"), 
	FREE("FREE"),
	PREMIUM("PREMIUM");

	private final String val;

	NivelUsuario(String val) {
		this.val = val;
	}

	public String getEstado() {
		return val;
	}
}
