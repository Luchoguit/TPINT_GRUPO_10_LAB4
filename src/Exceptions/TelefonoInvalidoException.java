package Exceptions;

public class TelefonoInvalidoException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TelefonoInvalidoException()
	{
		
	}

	@Override
	public String getMessage() {
		
		return " El telefono debe iniciar con 11 o 15 ";
	}
}
