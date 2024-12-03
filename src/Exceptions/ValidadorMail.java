package Exceptions;

public class ValidadorMail {

	public void validarMail1 (String mail) throws MailInvalidoException
	{
	        // Verificamos si el correo contiene el símbolo '@' y al menos un punto después del '@'
			int arrobaIndex =-1;
			int puntoIndex=-1;
		    arrobaIndex = mail.indexOf('@'); // Obtiene la posición del '@'
	        puntoIndex = mail.indexOf('.', arrobaIndex); // Busca el punto después del '@'

	        // cualquier cosa que no coincida o que no se encuentre dara error y hara entrar aca..
	        
	        if (arrobaIndex == -1 || puntoIndex == -1 || puntoIndex < arrobaIndex) {
	            throw new MailInvalidoException();
	        }		   		 				  
	}
	}

	

