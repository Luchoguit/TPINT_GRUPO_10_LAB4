package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.TipoCuenta;
import entidad.Usuario;
import negocio.ClienteNegocio;
import negocio.CuentaNegocio;
import negocio.TipoCuentaNegocio;
import negocioimplementacion.ClienteNegocioImp;
import negocioimplementacion.CuentaNegocioImp;
import negocioimplementacion.TipoCuentaNegocioImp;

@WebServlet("/ServletVerCuentas")
public class ServletVerCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

				System.out.println("[DEBUG] Entra al doGet de ServletVerCuentas");

				CuentaNegocio cuentaNegocio = new CuentaNegocioImp();
				TipoCuentaNegocio tcNegocio = new TipoCuentaNegocioImp();
				ClienteNegocio clienteNegocio = new ClienteNegocioImp();
				
				
				Usuario userActual = new Usuario();
				userActual = (Usuario) request.getSession().getAttribute("usuario");
				
				Cliente clienteActual = new Cliente();
				clienteActual = clienteNegocio.obtenerPorId(userActual.getIdCliente());
				
				request.setAttribute("cliente", clienteActual);
				
				
				List<Cuenta> listaCuentas = cuentaNegocio.listarCuentas(userActual);
				List<TipoCuenta> listaTC = tcNegocio.ObtenerTiposCuenta();
				
				for (Cuenta cuenta : listaCuentas) {
				    int tipoCuentaId = cuenta.getTipoCuenta().getId();

				    for (TipoCuenta tipoCuenta : listaTC) {
				        if (tipoCuenta.getId() == tipoCuentaId) {
				            cuenta.getTipoCuenta().setDescripcion(tipoCuenta.getDescripcion());
				            break;
				        }
				    }
				}
				
				request.setAttribute("listaCuentas", listaCuentas);
				
				
				
				
		        System.out.println(listaCuentas.size());

				
		        RequestDispatcher dispatcher = request.getRequestDispatcher("VentanasUser/VerCuentas.jsp");
		        dispatcher.forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

}
