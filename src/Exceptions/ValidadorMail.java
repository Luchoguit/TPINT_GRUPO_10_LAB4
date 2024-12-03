package Exceptions;

public class ValidadorMail {

	public void validarMail1 (String mail) throws MailInvalidoException
	{
	        // Verificamos si el correo contiene el s�mbolo '@' y al menos un punto despu�s del '@'
			int arrobaIndex =-1;
			int puntoIndex=-1;
		    arrobaIndex = mail.indexOf('@'); // Obtiene la posici�n del '@'
	        puntoIndex = mail.indexOf('.', arrobaIndex); // Busca el punto despu�s del '@'

	        // cualquier cosa que no coincida o que no se encuentre dara error y hara entrar aca..
	        
	        if (arrobaIndex == -1 || puntoIndex == -1 || puntoIndex < arrobaIndex) {
	            throw new MailInvalidoException();
	        }		   		 				  
	}
	}

	

