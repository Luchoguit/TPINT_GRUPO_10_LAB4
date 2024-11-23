package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Usuario;

/**
 * Servlet Filter implementation class FilterValidarSesion
 */
@WebFilter("/*")
public class FilterValidarSesion implements Filter {

    /**
     * Default constructor. 
     */
    public FilterValidarSesion() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false); // false: no crea una nueva sesión

        String requestURI = httpRequest.getRequestURI();
        
        
        System.out.println("[DEBUG] Validando al usuario logueado");
        
        	
	        if (requestURI.contains("IndexAdmin") || requestURI.contains("VentanasAdmin")) 
	        {  
	            if (session == null || session.getAttribute("usuario") == null) 
	            {
	                // Si no hay sesión activa, redirigimos al login
	            	System.out.println("[DEBUG] No hay sesion activa, redireccionando a login");
	            	
	                httpResponse.sendRedirect("/TPINT_GRUPO_10_LAB4/LOGIN/Login.jsp");
	                
	                return; 
	            }
	
	            // Si hay sesión, verifica el rol del usuario
	            Usuario user = (Usuario) session.getAttribute("usuario");
	            if (user.esAdministrador() == false) 
	            {  
	                
	            	
	            	System.out.println("[DEBUG] No es admin, acceso denegado");
	            	
	                httpResponse.sendRedirect("/TPINT_GRUPO_10_LAB4/MENUS/IndexUser.jsp");
	                return;
	            }
	            
	            chain.doFilter(request, response);
	        }
	        else 
	        {
	        	if(requestURI.contains("IndexUser") || requestURI.contains("IndexCuenta") || requestURI.contains("VentanasUser")) 
	        	{
		            if (session == null || session.getAttribute("usuario") == null) 
		            {
		                // Si no hay sesión activa, redirigimos al login
		            	System.out.println("[DEBUG] No hay sesion activa, redireccionando a login");
		            	
		                httpResponse.sendRedirect("/TPINT_GRUPO_10_LAB4/LOGIN/Login.jsp");
		                
		                return; 
		            }
		            
		            Usuario user = (Usuario) session.getAttribute("usuario");
		            if (user.esAdministrador() == true) 
		            {  
		                
		            	
		            	System.out.println("[DEBUG] Es admin, acceso denegado a cuenta");
		            	
		                httpResponse.sendRedirect("/TPINT_GRUPO_10_LAB4/MENUS/IndexAdmin.jsp");
		                return;
		            }
	        	}
	        	chain.doFilter(request, response);
	        }
	       
	       
        }

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
