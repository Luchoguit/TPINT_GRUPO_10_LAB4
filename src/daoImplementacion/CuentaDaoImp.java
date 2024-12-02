package daoImplementacion;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import dao.CuentaDao;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.Localidad;
import entidad.Movimiento;
import entidad.Provincia;
import entidad.TipoCuenta;
import entidad.TipoMovimiento;
import entidad.Usuario;
import negocio.CuentaNegocio;
import negocioimplementacion.CuentaNegocioImp;

public class CuentaDaoImp implements CuentaDao {
	
	
	 private static final String qryInsert = "INSERT INTO cuentas (numero_cuenta, fecha_creacion, cbu, saldo, id_usuario, id_tipoCuenta) "
	            + "VALUES (?, ?, ?, ?, ?, ?)";
	 
	@Override
	public boolean altaCuenta(Cuenta cuenta) {
		PreparedStatement statement;
        Connection conexion = Conexion.getConexion().getSQLConexion();
        boolean isInsertExitoso = false;

        try {
            statement = conexion.prepareStatement(qryInsert);

            // Seteando los parámetros de la consulta
            statement.setString(1, cuenta.getNumeroCuenta());
            statement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            statement.setString(3, cuenta.getCbu());
            statement.setBigDecimal(4, cuenta.getSaldo());
            statement.setInt(5, cuenta.getUsuario().getCliente().getId());
            statement.setInt(6, cuenta.getTipoCuenta().getId());
                

            // Ejecución de la consulta
            if (statement.executeUpdate() > 0) {
                conexion.commit(); // Confirmar la transacción si todo va bien
                isInsertExitoso = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conexion.rollback(); // Revertir la transacción en caso de error
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            Conexion.getConexion().cerrarConexion(); // Cerrar la conexión
        }

        return isInsertExitoso;
	}
	
	private static final String qryListarCuentas = "SELECT C.*, TC.descripcion AS tipoCuentaDescripcion "
			+ "FROM cuentas C "
			+ "JOIN tipos_de_cuentas TC ON TC.id = C.id_tipoCuenta "
			+ "WHERE C.id_usuario = ? AND C.estado = 1";

	@Override
	public List<Cuenta> listarCuentas(Usuario usuario) {
		
		List<Cuenta> listaCuentas = new ArrayList<>();

	    try {
	     
	        Connection con = Conexion.getConexion().getSQLConexion();	
	        System.out.println("[DEBUG] Conexión a la base de datos establecida");

	        System.out.println("[DEBUG] Conexión establecida: " + con);

	        PreparedStatement statement = con.prepareStatement(qryListarCuentas);
            statement.setInt(1, usuario.getCliente().getId());
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	        	int id = resultSet.getInt("id");
	            String numeroCuenta = resultSet.getString("numero_cuenta");
	            LocalDateTime fechaCreacion = resultSet.getTimestamp("fecha_creacion").toLocalDateTime();
	            String cbu = resultSet.getString("cbu");
	            BigDecimal saldo = resultSet.getBigDecimal("saldo");
	            
	            Cliente clienteCuenta = new Cliente();
	            clienteCuenta.setId(resultSet.getInt("id_usuario"));            
	            
	            Usuario usuarioCuenta = new Usuario();
	            usuarioCuenta.setCliente(clienteCuenta);
	            
	            TipoCuenta tipoCuenta = new TipoCuenta();
	            tipoCuenta.setId(resultSet.getInt("id_tipoCuenta"));
	            tipoCuenta.setDescripcion(resultSet.getString("tipoCuentaDescripcion"));
	            	            			            
	            boolean estado = resultSet.getBoolean("estado");

	        
	            Cuenta cuenta = new Cuenta(id, numeroCuenta, fechaCreacion, cbu, saldo, usuarioCuenta, tipoCuenta, estado);
	            listaCuentas.add(cuenta);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        Conexion.getConexion().cerrarConexion();
	    }

	    return listaCuentas;
	}

	@Override
	public boolean modificarCuenta(Cuenta cuenta) {
		// TODO Auto-generated method stub
		return false;
	}

	
	@Override
	public List<Cuenta> listarTodasLasCuentas() {
	    List<Cuenta> listaCuentas = new ArrayList<>();

	    try (Connection con = Conexion.getConexion().getSQLConexion()) {
	        System.out.println("[DEBUG] Conexion a la base de datos establecida");

	    
	        String qryListarCuentas = 
	            "SELECT CU.*, TC.*, CL.* " 
	            + "FROM cuentas CU "
	            + "JOIN clientes CL ON CL.id = CU.id_usuario " 
	            + "JOIN tipos_de_cuentas TC ON CU.id_tipoCuenta = TC.id "
	            + "WHERE CU.estado = 1";
	         
	        PreparedStatement statementCuentas = con.prepareStatement(qryListarCuentas);
	        ResultSet resultSetCuentas = statementCuentas.executeQuery();

	        
	        while (resultSetCuentas.next()) {
	            Cuenta cuenta = new Cuenta();

	            cuenta.setId(resultSetCuentas.getInt("CU.id"));
	            cuenta.setNumeroCuenta(resultSetCuentas.getString("CU.numero_cuenta"));
	            
	       
	            TipoCuenta tipoCuenta = new TipoCuenta();
	            tipoCuenta.setId(resultSetCuentas.getInt("TC.id"));
	            tipoCuenta.setDescripcion(resultSetCuentas.getString("TC.descripcion"));

	           
	            cuenta.setTipoCuenta(tipoCuenta);
	            
	         // CLIENTE
	        	Cliente cliente = new Cliente();
	        	cliente.setId(resultSetCuentas.getInt("CL.id"));
	        	cliente.setDni(resultSetCuentas.getString("CL.dni"));
	        	cliente.setCuil(resultSetCuentas.getString("CL.cuil"));
	        	cliente.setNombre(resultSetCuentas.getString("CL.nombre"));
	        	cliente.setApellido(resultSetCuentas.getString("CL.apellido"));
	        	cliente.setSexo(resultSetCuentas.getString("CL.sexo"));
	        	cliente.setNacionalidad(resultSetCuentas.getString("CL.nacionalidad"));
	        	
	        	java.sql.Date sqlDate = resultSetCuentas.getDate("CL.fecha_nacimiento");
	            LocalDate fechaNacimiento = sqlDate != null ? sqlDate.toLocalDate() : null;
	            cliente.setFechaNacimiento(fechaNacimiento);
	            
	            Localidad localidadCliente = new Localidad();
	            localidadCliente.setId(resultSetCuentas.getInt("CL.id_localidad"));
	        	cliente.setLocalidadCliente(localidadCliente);
	        	
	        	Provincia provinciaCliente = new Provincia();
	            provinciaCliente.setId(resultSetCuentas.getInt("CL.id_provincia"));
	        	cliente.setProvinciaCliente(provinciaCliente);
	        	
	        	cliente.setCorreo(resultSetCuentas.getString("CL.correo"));
	        	cliente.setTelefono(resultSetCuentas.getString("CL.telefono"));
	        	cliente.setDireccion(resultSetCuentas.getString("CL.direccion"));
	        	cliente.setEstado(resultSetCuentas.getBoolean("CL.estado"));            
	            
	            Usuario usuarioCuenta = new Usuario();
	            usuarioCuenta.setCliente(cliente);
	            
	            cuenta.setUsuario(usuarioCuenta);
	            cuenta.setEstado(resultSetCuentas.getBoolean("CU.estado"));

	          
	            listaCuentas.add(cuenta);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    finally {
	        Conexion.getConexion().cerrarConexion();
	    }

	    // Devolvemos solo la lista de cuentas
	    return listaCuentas;
	}

	private static final String qryEliminarCuenta = "UPDATE cuentas SET estado = false WHERE id = ?";
	

	@Override
	public boolean eliminarCuenta(int id) {
		try (Connection con = Conexion.getConexion().getSQLConexion();
		          PreparedStatement statement = con.prepareStatement(qryEliminarCuenta)) {

		
		         if (!con.getAutoCommit()) {
		             con.setAutoCommit(true); 
		             System.out.println("[DEBUG] Autocommit habilitado");
		         }

		         statement.setInt(1, id);

		         int rowsAffected = statement.executeUpdate();
		         System.out.println("[DEBUG] filas afectadas: " + rowsAffected);

		         if (rowsAffected > 0 && !con.getAutoCommit()) {
		             con.commit();  
		             System.out.println("[DEBUG] Commit realizado");
		         }

		         return rowsAffected > 0;

		     } catch (SQLException e) {
		         e.printStackTrace();
			    } finally {
			        Conexion.getConexion().cerrarConexion();
			    }
		     
		     return false;
	}

	private static final String qryObtenerCuentaPorId = "SELECT C.*, TC.descripcion, U.* "
			+ "FROM cuentas C "
			+ "JOIN tipos_de_cuentas TC ON TC.id = C.id_tipoCuenta "
			+ "JOIN usuarios U ON U.id = C.id_usuario "
			+ "WHERE C.id = ?";
	
	@Override
	public Cuenta obtenerCuentaPorId(int id) {
		Cuenta cuenta = new Cuenta();

	    try {
	     
	        Connection con = Conexion.getConexion().getSQLConexion();	
	        System.out.println("[DEBUG] Conexión a la base de datos establecida");

	        System.out.println("[DEBUG] Conexión establecida: " + con);

	        PreparedStatement statement = con.prepareStatement(qryObtenerCuentaPorId);
            statement.setInt(1, id);
	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            cuenta.setId(id);
	            cuenta.setNumeroCuenta(resultSet.getString("C.numero_cuenta"));
	            
	            LocalDateTime fechaCreacion = resultSet.getTimestamp("C.fecha_creacion").toLocalDateTime();
	            cuenta.setFechaCreacion(fechaCreacion);
	            cuenta.setCbu(resultSet.getString("C.cbu"));
	            cuenta.setSaldo(resultSet.getBigDecimal("C.saldo"));
	            
	            TipoCuenta tipoCuenta = new TipoCuenta();
	            tipoCuenta.setId(resultSet.getInt("C.id_tipoCuenta"));
	            tipoCuenta.setDescripcion(resultSet.getString("TC.descripcion"));
	            
	            cuenta.setTipoCuenta(tipoCuenta);
	            
	            Cliente cliente = new Cliente();
	            cliente.setId(resultSet.getInt("U.id"));
	            
	            Usuario usuario = new Usuario();
	            usuario.setCliente(cliente);
	            usuario.setNombreUsuario(resultSet.getString("U.nombre_usuario"));
	            usuario.setContraseña(resultSet.getString("U.contrasenia"));
	            
	            LocalDateTime fechaCreacionUsuario = resultSet.getTimestamp("U.fecha_creacion").toLocalDateTime();
	            usuario.setFechaCreacion(fechaCreacionUsuario);
	            usuario.setTipoUsuario(resultSet.getString("U.tipo"));
	            usuario.setEstado(resultSet.getBoolean("U.estado"));
	            
	            cuenta.setUsuario(usuario);
	            cuenta.setEstado(resultSet.getBoolean("C.estado"));
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        Conexion.getConexion().cerrarConexion();
	    }

	    return cuenta;
	}

	private static final String qryObtenerCuentaPorCBU = "SELECT C.*, TC.descripcion, U.* "
			+ "FROM cuentas C "
			+ "JOIN tipos_de_cuentas TC ON TC.id = C.id_tipoCuenta "
			+ "JOIN usuarios U ON U.id = C.id_usuario "
			+ "WHERE C.cbu = ?";
	
	@Override
	public Cuenta obtenerCuentaPorCBU(String cbu) {
		Cuenta cuenta = new Cuenta();
		
		
		
		try {
			Connection con = Conexion.getConexion().getSQLConexion();
			PreparedStatement statement = con.prepareStatement(qryObtenerCuentaPorCBU);
			statement.setString(1, cbu);
	        ResultSet resultSet = statement.executeQuery();
	        if (resultSet.next()) {
	            cuenta.setCbu(cbu);
	            cuenta.setNumeroCuenta(resultSet.getString("C.numero_cuenta"));
	            
	            LocalDateTime fechaCreacion = resultSet.getTimestamp("C.fecha_creacion").toLocalDateTime();
	            cuenta.setFechaCreacion(fechaCreacion);
	            cuenta.setId(resultSet.getInt("C.Id"));
	            cuenta.setSaldo(resultSet.getBigDecimal("C.saldo"));
	            
	            TipoCuenta tipoCuenta = new TipoCuenta();
	            tipoCuenta.setId(resultSet.getInt("C.id_tipoCuenta"));
	            tipoCuenta.setDescripcion(resultSet.getString("TC.descripcion"));
	            
	            cuenta.setTipoCuenta(tipoCuenta);
	            
	            Cliente cliente = new Cliente();
	            cliente.setId(resultSet.getInt("U.id"));
	            
	            Usuario usuario = new Usuario();
	            usuario.setCliente(cliente);
	            usuario.setNombreUsuario(resultSet.getString("U.nombre_usuario"));
	            usuario.setContraseña(resultSet.getString("U.contrasenia"));
	            
	            LocalDateTime fechaCreacionUsuario = resultSet.getTimestamp("U.fecha_creacion").toLocalDateTime();
	            usuario.setFechaCreacion(fechaCreacionUsuario);
	            usuario.setTipoUsuario(resultSet.getString("U.tipo"));
	            usuario.setEstado(resultSet.getBoolean("U.estado"));
	            
	            cuenta.setUsuario(usuario);
	            cuenta.setEstado(resultSet.getBoolean("C.estado"));
	        	}
			}
			catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        Conexion.getConexion().cerrarConexion();
		    }
	    return cuenta;
	       
		}
	
	public int contarCuentasActivasPorUsuario(int idUsuario) {
	    int cuentasActivas = 0;
	    String qryContarCuentasActivas = "SELECT COUNT(*) FROM cuentas C WHERE C.id_usuario = ? AND C.estado = TRUE";

	    try {
	   
	        Connection con = Conexion.getConexion().getSQLConexion();
	        System.out.println("[DEBUG] Conexión a la base de datos establecida");

	 
	        PreparedStatement statement = con.prepareStatement(qryContarCuentasActivas);
	        statement.setInt(1, idUsuario);  
	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            cuentasActivas = resultSet.getInt(1); 
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        Conexion.getConexion().cerrarConexion(); 
	    }

	    return cuentasActivas;  
	}
	
	private static final String qryActivarCuenta = "UPDATE cuentas SET estado = true WHERE id = ?";
	
	@Override
	public boolean ActivarCuenta(int id) {
		try (Connection con = Conexion.getConexion().getSQLConexion();
		          PreparedStatement statement = con.prepareStatement(qryActivarCuenta)) {

		
		         if (!con.getAutoCommit()) {
		             con.setAutoCommit(true); 
		             System.out.println("[DEBUG] Autocommit habilitado");
		         }

		         statement.setInt(1, id);

		         int rowsAffected = statement.executeUpdate();
		         System.out.println("[DEBUG] filas afectadas: " + rowsAffected);

		         if (rowsAffected > 0 && !con.getAutoCommit()) {
		             con.commit();  
		             System.out.println("[DEBUG] Commit realizado");
		         }

		         return rowsAffected > 0;

		     } catch (SQLException e) {
		         e.printStackTrace();
			    } finally {
			        Conexion.getConexion().cerrarConexion();
			    }
		     
		     return false;
	}
	
	@Override
	public List<Cuenta> listarTodasLasCuentasEliminadas() {
	    List<Cuenta> listaCuentas = new ArrayList<>();

	    try (Connection con = Conexion.getConexion().getSQLConexion()) {
	        System.out.println("[DEBUG] Conexion a la base de datos establecida");

	    
	        String qryListarCuentas = 
	        				"SELECT CU.*, TC.*, CL.* " 
	        	            + "FROM cuentas CU "
	        	            + "JOIN clientes CL ON CL.id = CU.id_usuario " 
	        	            + "JOIN tipos_de_cuentas TC ON CU.id_tipoCuenta = TC.id "
	        	            + "WHERE CU.estado = 0";
	         
	        PreparedStatement statementCuentas = con.prepareStatement(qryListarCuentas);
	        ResultSet resultSetCuentas = statementCuentas.executeQuery();

	        
	        while (resultSetCuentas.next()) {
	        	Cuenta cuenta = new Cuenta();

	            cuenta.setId(resultSetCuentas.getInt("CU.id"));
	            cuenta.setNumeroCuenta(resultSetCuentas.getString("CU.numero_cuenta"));
	            
	       
	            TipoCuenta tipoCuenta = new TipoCuenta();
	            tipoCuenta.setId(resultSetCuentas.getInt("TC.id"));
	            tipoCuenta.setDescripcion(resultSetCuentas.getString("TC.descripcion"));

	           
	            cuenta.setTipoCuenta(tipoCuenta);
	            
	         // CLIENTE
	        	Cliente cliente = new Cliente();
	        	cliente.setId(resultSetCuentas.getInt("CL.id"));
	        	cliente.setDni(resultSetCuentas.getString("CL.dni"));
	        	cliente.setCuil(resultSetCuentas.getString("CL.cuil"));
	        	cliente.setNombre(resultSetCuentas.getString("CL.nombre"));
	        	cliente.setApellido(resultSetCuentas.getString("CL.apellido"));
	        	cliente.setSexo(resultSetCuentas.getString("CL.sexo"));
	        	cliente.setNacionalidad(resultSetCuentas.getString("CL.nacionalidad"));
	        	
	        	java.sql.Date sqlDate = resultSetCuentas.getDate("CL.fecha_nacimiento");
	            LocalDate fechaNacimiento = sqlDate != null ? sqlDate.toLocalDate() : null;
	            cliente.setFechaNacimiento(fechaNacimiento);
	            
	            Localidad localidadCliente = new Localidad();
	            localidadCliente.setId(resultSetCuentas.getInt("CL.id_localidad"));
	        	cliente.setLocalidadCliente(localidadCliente);
	        	
	        	Provincia provinciaCliente = new Provincia();
	            provinciaCliente.setId(resultSetCuentas.getInt("CL.id_provincia"));
	        	cliente.setProvinciaCliente(provinciaCliente);
	        	
	        	cliente.setCorreo(resultSetCuentas.getString("CL.correo"));
	        	cliente.setTelefono(resultSetCuentas.getString("CL.telefono"));
	        	cliente.setDireccion(resultSetCuentas.getString("CL.direccion"));
	        	cliente.setEstado(resultSetCuentas.getBoolean("CL.estado"));            
	            
	            Usuario usuarioCuenta = new Usuario();
	            usuarioCuenta.setCliente(cliente);
	            
	            cuenta.setUsuario(usuarioCuenta);
	            cuenta.setEstado(resultSetCuentas.getBoolean("CU.estado"));

	          
	            listaCuentas.add(cuenta);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    finally {
	        Conexion.getConexion().cerrarConexion();
	    }

	    // Devolvemos solo la lista de cuentas
	    return listaCuentas;
	}
	
	
	private static final String qryExisteCbu = "select id FROM cuentas where cbu = ? and estado = true";
	
	public boolean existeCuentaConCbu(String cbu)
	{
		boolean resultado = false;
		int id = 0;
		
		try 
		{
			Connection con = Conexion.getConexion().getSQLConexion();
			PreparedStatement statement = con.prepareStatement(qryObtenerCuentaPorCBU);
			statement.setString(1, cbu);
			
			ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            id = resultSet.getInt(1); 
	            resultado = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    finally {
	    	Conexion.getConexion().cerrarConexion();
	    }
		
		return resultado;
	}

	
	private static final String qryInsertMovimiento = "INSERT INTO movimientos (id_cuenta, id_tipoMovimiento, detalle, fechaHora, importe, id_cuentaDestino, Saldo_disponible) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";
	
	@Override
	public boolean realizarTransferencia(Movimiento movimiento) {
		
		PreparedStatement statement;
        Connection conexion = Conexion.getConexion().getSQLConexion();
        boolean isInsertExitoso = false;

        try {
            

            BigDecimal saldoActualizado = movimiento.getCuentaDestino().getSaldo().add(movimiento.getImporte());
            //BigDecimal saldoActualizado = new BigDecimal(movimiento.getCuentaDestino().getSaldo().doubleValue() - movimiento.getImporte().doubleValue());
        	
            if(saldoActualizado.floatValue() >= 0.00)
            {
                statement = conexion.prepareStatement(qryInsertMovimiento);
                
                // Seteando los parámetros de la consulta
                statement.setInt(1,  movimiento.getCuentaOrigen().getId());
                statement.setInt(2, movimiento.getTipoMovimiento().getId());
                statement.setString(3, movimiento.getDetalle());
                statement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
                statement.setBigDecimal(5, movimiento.getImporte());
                statement.setInt(6,  movimiento.getCuentaDestino().getId());
                statement.setBigDecimal(7, saldoActualizado);
              

                // Ejecución de la consulta
                if (statement.executeUpdate() > 0) {
                    conexion.commit(); // Confirmar la transacción si todo va bien
                    isInsertExitoso = true;
                }
            }
            

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conexion.rollback(); // Revertir la transacción en caso de error
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            Conexion.getConexion().cerrarConexion(); // Cerrar la conexión
        }
        
        return isInsertExitoso;
	}

	
	private static final String qryActualizarSaldo = "UPDATE cuentas SET saldo = ? WHERE id = ?";
	
	@Override
	public boolean actualizarSaldo(Movimiento movimiento, boolean salida) {
		try (Connection con = Conexion.getConexion().getSQLConexion();
		          PreparedStatement statement = con.prepareStatement(qryActualizarSaldo)) {

		
		         if (!con.getAutoCommit()) {
		             con.setAutoCommit(true); 
		             System.out.println("[DEBUG] Autocommit habilitado");
		         }
		         
		         
		         
		         if(salida == true)
		         {
		        	 BigDecimal saldoActualizado = movimiento.getCuentaOrigen().getSaldo().subtract(movimiento.getImporte());
		        	 statement.setBigDecimal(1, saldoActualizado);
		        	 statement.setInt(2, movimiento.getCuentaOrigen().getId());
		         }
		         else {
		        	 BigDecimal saldoActualizado = movimiento.getCuentaOrigen().getSaldo().add(movimiento.getImporte());
		        	 statement.setBigDecimal(1, saldoActualizado);
		        	 statement.setInt(2, movimiento.getCuentaDestino().getId());
		         }
		         

		         int rowsAffected = statement.executeUpdate();
		         System.out.println("[DEBUG] filas afectadas: " + rowsAffected);

		         if (rowsAffected > 0 && !con.getAutoCommit()) {
		             con.commit();  
		             System.out.println("[DEBUG] Commit realizado");
		         }

		         return rowsAffected > 0;

		     } catch (SQLException e) {
		         e.printStackTrace();
			    } finally {
			        Conexion.getConexion().cerrarConexion();
			    }
		     
		     return false;
	}

	
	private static final String qryListarMovimientos = "select id, id_cuenta, id_tipoMovimiento, detalle, fechaHora, importe, id_cuentaDestino from movimientos where id_cuenta = ? or id_cuentaDestino = ?";
	
	@Override
	public List<Movimiento> listarMovimientosCuenta(Cuenta cuenta) {
		
		List<Movimiento> listaMovimientos = new ArrayList<>();

	    try (Connection con = Conexion.getConexion().getSQLConexion()) {
	        System.out.println("[DEBUG] Conexion a la base de datos establecida");
	        
	        PreparedStatement statementCuentas = con.prepareStatement(qryListarMovimientos);
	        statementCuentas.setInt(1, cuenta.getId());
	        statementCuentas.setInt(2, cuenta.getId());
	        
	        ResultSet resultSetCuentas = statementCuentas.executeQuery();

	        
	        while (resultSetCuentas.next()) {
	        	
	        	
	        	int id = resultSetCuentas.getInt("id");
	        	int id_cuenta = resultSetCuentas.getInt("id_cuenta");
	        	int id_tipoMovimiento = resultSetCuentas.getInt("id_tipoMovimiento");
	        	String detalle = resultSetCuentas.getString("detalle");
	        	Timestamp fechaHora = resultSetCuentas.getTimestamp("fechaHora");
	        	
	        	Instant instant = fechaHora.toInstant();
	        	LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
	        	
	        	
	        	BigDecimal importe = resultSetCuentas.getBigDecimal("importe");
	        	int id_cuentaDestino = resultSetCuentas.getInt("id_cuentaDestino");
	        	
	        	TipoMovimiento tipo = new TipoMovimiento(id_tipoMovimiento, null);
	        	Cuenta cuentaDestino = new Cuenta();
	        	cuentaDestino.setId(id_cuentaDestino);
	        	
	        	Movimiento movimiento = new Movimiento(id, cuenta, tipo, detalle, localDateTime, importe, cuentaDestino);

	            
	
	            listaMovimientos.add(movimiento);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    finally {
	        Conexion.getConexion().cerrarConexion();
	    }

	    return listaMovimientos;
		
	}
	
	@Override
	public boolean verificarCuentaAsociadaAPrestamo(int idCuenta) {
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    String sql = "SELECT VerificarCuentaAsociadaAPrestamo(?) AS cuentaAsociada"; 
	    
	    try {
	        conn = Conexion.getConexion().getSQLConexion(); 
	        stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, idCuenta);  

	        rs = stmt.executeQuery();

	        if (rs.next()) {
	            return rs.getInt("cuentaAsociada") == 1; 
	        }
	        return false;  
	    } catch (SQLException e) {
	        e.printStackTrace(); 
	        return false;
	    } finally {
	        try {
	            if (rs != null) rs.close();  
	            if (stmt != null) stmt.close();  
	            Conexion.getConexion().cerrarConexion();  
	        } catch (SQLException e) {
	            e.printStackTrace();  
	        }
	    }
	}
	
	private static final String qryDestinatariosFrecuentes = 
		    "SELECT C.*, TC.descripcion, U.*, COUNT(M.id_cuentaDestino) AS frecuencia " +
		    "FROM movimientos M " +
		    "JOIN cuentas C ON C.id = M.id_cuentaDestino " +
		    "JOIN tipos_de_cuentas TC ON TC.id = C.id_tipoCuenta " +
		    "JOIN usuarios U ON U.id = C.id_usuario " +
		    "WHERE M.id_cuenta = ? AND M.id_cuentaDestino IS NOT NULL " +
		    "GROUP BY M.id_cuentaDestino " +
		    "ORDER BY frecuencia DESC " +
		    "LIMIT 3";


	@Override
	public List<Cuenta> obtenerDestinatariosFrecuentes(int idCuenta) {
	    List<Cuenta> destinatariosFrecuentes = new ArrayList<>();

	    try (Connection con = Conexion.getConexion().getSQLConexion();
	         PreparedStatement statement = con.prepareStatement(qryDestinatariosFrecuentes)) {
	        
	        statement.setInt(1, idCuenta);

	        try (ResultSet resultSet = statement.executeQuery()) {
	            while (resultSet.next()) {
	                Cuenta cuenta = new Cuenta();
	                cuenta.setId(resultSet.getInt("C.id"));
	                cuenta.setNumeroCuenta(resultSet.getString("C.numero_cuenta"));
	                
	                LocalDateTime fechaCreacion = resultSet.getTimestamp("C.fecha_creacion").toLocalDateTime();
	                cuenta.setFechaCreacion(fechaCreacion);
	                cuenta.setCbu(resultSet.getString("C.cbu"));
	                cuenta.setSaldo(resultSet.getBigDecimal("C.saldo"));
	                cuenta.setEstado(resultSet.getBoolean("C.estado"));

	                TipoCuenta tipoCuenta = new TipoCuenta();
	                tipoCuenta.setId(resultSet.getInt("C.id_tipoCuenta"));
	                tipoCuenta.setDescripcion(resultSet.getString("TC.descripcion"));
	                cuenta.setTipoCuenta(tipoCuenta);

	                Cliente cliente = new Cliente();
	                cliente.setId(resultSet.getInt("U.id"));

	                Usuario usuario = new Usuario();
	                usuario.setCliente(cliente);
	                usuario.setNombreUsuario(resultSet.getString("U.nombre_usuario"));
	                usuario.setContraseña(resultSet.getString("U.contrasenia"));
	                
	                LocalDateTime fechaCreacionUsuario = resultSet.getTimestamp("U.fecha_creacion").toLocalDateTime();
	                usuario.setFechaCreacion(fechaCreacionUsuario);
	                usuario.setTipoUsuario(resultSet.getString("U.tipo"));
	                usuario.setEstado(resultSet.getBoolean("U.estado"));

	                cuenta.setUsuario(usuario);

	                destinatariosFrecuentes.add(cuenta);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
            Conexion.getConexion().cerrarConexion();  
	    }

	    return destinatariosFrecuentes;
	}


	
}
