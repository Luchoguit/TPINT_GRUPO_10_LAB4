package Exceptions;

public class ValidadorTelefono {

	public void validar(String telefono) throws TelefonoInvalidoException {
        if (telefono == null || (!telefono.startsWith("11") && !telefono.startsWith("15"))) {
            throw new TelefonoInvalidoException(); 
        }
    }
}
