package ca.vinote.exception;

public class CustomException extends Exception {
	private static final long serialVersionUID = 7354708071767139123L;

	public CustomException(String mensaje) {
		super(mensaje);
	}

}
