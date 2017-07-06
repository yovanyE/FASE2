package WService;

import DBaseDato.Conexion;
import controlador.ClienteController;
import controlador.EmpleadoController;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import modelo.Agencia;
import modelo.Cheque;
import modelo.ClienteIndividual;
import modelo.CuentaBancaria;
import modelo.Empleado;
import modelo.Prestamo;
import modelo.TipoCuenta;

@WebService(serviceName = "BancoController")
public class BancoController {

    @WebMethod(operationName = "registrarClienteindividual")
    public String registrarClienteindividual(@WebParam(name = "numeroCui") int numeroCui, @WebParam(name = "nombres") String nombres, @WebParam(name = "apellidos") String apellidos, @WebParam(name = "fechaNacimiento") String fechaNacimiento, @WebParam(name = "telefono") String telefono, @WebParam(name = "direccion") String direccion, @WebParam(name = "sueldo") double sueldo, @WebParam(name = "login") String login, @WebParam(name = "password") String password) {
        ClienteController cliente = new ClienteController();
        String respuesta = cliente.registrarClienteIndividual(numeroCui, nombres, apellidos, fechaNacimiento, telefono, direccion, sueldo, login, password);
        return respuesta;
    }

    @WebMethod(operationName = "registrarClienteEmpresa")
    public String registrarClienteEmpresa(@WebParam(name = "nombre") String nombre, @WebParam(name = "direccion") String direccion, @WebParam(name = "telefono") String telefono, @WebParam(name = "login") String login, @WebParam(name = "password") String password) {
        ClienteController empresa = new ClienteController();
        String respuesta = empresa.registrarClienteEmpresarial(nombre, direccion, telefono, login, password);
        return respuesta;
    }

    @WebMethod(operationName = "CuentaBancaria")
    public String CuentaBancaria(@WebParam(name = "numeroCuenta") int numeroCuenta, @WebParam(name = "idTipoCuenta") int idTipoCuenta, @WebParam(name = "idClienteindividual") int idClienteindividual, @WebParam(name = "idclienteempresa") int idclienteempresa) {
        ClienteController cuentaBancaria = new ClienteController();
        String respuesta = cuentaBancaria.crearCuentaBancaria(numeroCuenta, idTipoCuenta, idClienteindividual, idclienteempresa);
        return respuesta;
    }

    @WebMethod(operationName = "registrarEmpleado")
    public String registrarEmpleado(@WebParam(name = "numeroCui") int numeroCui, @WebParam(name = "nombre") String nombre, @WebParam(name = "apellidos") String apellidos, @WebParam(name = "direccion") String direccion, @WebParam(name = "telefono") String telefono, @WebParam(name = "idAgencia") int idAgencia, @WebParam(name = "idRol") int idRol, @WebParam(name = "login") String login, @WebParam(name = "password") String password) {
        EmpleadoController registrar = new EmpleadoController();
        String respuesta = registrar.registrarEmpleado(numeroCui, nombre, apellidos, direccion, telefono, idAgencia, idRol, login, password);
        return respuesta;
    }

    @WebMethod(operationName = "validar")
    public ClienteIndividual validar(@WebParam(name = "login") String login, @WebParam(name = "password") String password) {
        ClienteController client = new ClienteController();
        ClienteIndividual cli = client.verificarUsuario(login, password);
        return cli;
    }

    @WebMethod(operationName = "validarSesion")
    public boolean validarSesion(@WebParam(name = "login") String login, @WebParam(name = "password") String password) {
        try {
            PreparedStatement proceso = Conexion.getInstancia().getConexion().prepareCall("CALL Sp_autenticar(?,?)");
            proceso.setString(1, login);
            proceso.setString(2, password);

            ResultSet rs = proceso.executeQuery();
            return rs.next();
        } catch (Exception ex) {

        }
        return false;
    }

    @WebMethod(operationName = "busquedaClienteindividual")
    public ClienteIndividual busquedaClienteindividual(@WebParam(name = "numeroCui") int numeroCui) {
        ClienteController buscarlo = new ClienteController();
        ClienteIndividual cl = buscarlo.buscarclienteindividual(numeroCui);
        return cl;
    }

    @WebMethod(operationName = "eliminarCliente")
    public ClienteIndividual eliminarCliente(@WebParam(name = "idclienteindividual") int numeroCui) {
        ClienteController eliminarlo = new ClienteController();
        ClienteIndividual elimin = eliminarlo.eliminarClienteindividual(numeroCui);
        return elimin;
    }

    @WebMethod(operationName = "modificarCindividual")
    public ClienteIndividual modificarCindividual(@WebParam(name = "numeroCui") int numeroCui, @WebParam(name = "nombres") String nombres, @WebParam(name = "apellidos") String apellidos, @WebParam(name = "fechaNacimiento") String fechaNacimiento, @WebParam(name = "telefono") String telefono, @WebParam(name = "direccion") String direccion, @WebParam(name = "sueldo") double sueldo, @WebParam(name = "login") String login, @WebParam(name = "password") String password) {
        ClienteController modificar = new ClienteController();
        ClienteIndividual mod = modificar.modificarClienteindividual(numeroCui, nombres, apellidos, fechaNacimiento, telefono, direccion, sueldo, login, password);
        return mod;
    }

    @WebMethod(operationName = "rolTipoCuenta")
    public TipoCuenta rolTipoCuenta(@WebParam(name = "idtipoCuenta") int idtipoCuenta) {
        ClienteController tipoC = new ClienteController();
        TipoCuenta tipe = tipoC.buscarCuenta(idtipoCuenta);
        return tipe;
    }

    @WebMethod(operationName = "busquedaEmpleado")
    public Empleado busquedaEmpleado(@WebParam(name = "cui") int cui) {
        EmpleadoController buscar = new EmpleadoController();
        Empleado empleado = buscar.buscarEmpleado(cui);
        return empleado;
    }

    @WebMethod(operationName = "eliminarEmpleado")
    public Empleado eliminarEmpleado(@WebParam(name = "cui") int cui) {
        EmpleadoController eliminar = new EmpleadoController();
        Empleado elimn = eliminar.eliminarEmpleado(cui);
        return elimn;
    }

    @WebMethod(operationName = "modificarEmpleado")
    public Empleado modificarEmpleado(@WebParam(name = "cui") int cui, @WebParam(name = "nombre") String nombre, @WebParam(name = "apellidos") String apellidos, @WebParam(name = "direccion") String direccion, @WebParam(name = "telefono") String telefono, @WebParam(name = "idAgencia") final int idAgencia, @WebParam(name = "idRol") int idRol, @WebParam(name = "login") String login, @WebParam(name = "password") String password) {
        EmpleadoController modifica = new EmpleadoController();
        Empleado modificar = modifica.modificarEmpleado(cui, nombre, apellidos, direccion, telefono, idAgencia, idRol, login, password);
        return modificar;
    }

    @WebMethod(operationName = "loginEmpleado")
    public Empleado loginEmpleado(@WebParam(name = "login") String login, @WebParam(name = "password") String password) {
        EmpleadoController loguin = new EmpleadoController();
        Empleado log = loguin.loginEmpleado(login, password);
        return log;
    }

    @WebMethod(operationName = "modificarCuentaBancaria")
    public CuentaBancaria modificarCuentaBancaria(@WebParam(name = "numeroCuenta") int numeroCuenta, @WebParam(name = "idTipoCuenta") int idTipoCuenta, @WebParam(name = "numeroCui") int numeroCui, @WebParam(name = "idClienteEmpresa") int idClienteEmpresa) {
        ClienteController cuentBanca = new ClienteController();
        CuentaBancaria modifica = cuentBanca.modificarCuenta(numeroCuenta, idTipoCuenta, numeroCui, idClienteEmpresa);
        return modifica;
    }

    @WebMethod(operationName = "buscarCuentaBancaria")
    public CuentaBancaria buscarCuentaBancaria(@WebParam(name = "numeroCuenta") int numeroCuenta) {
        ClienteController buscarCuenta = new ClienteController();
        CuentaBancaria busqueda = buscarCuenta.buscarCuentaBancaria(numeroCuenta);
        return busqueda;
    }

    @WebMethod(operationName = "eliminarCuentaBancaria")
    public CuentaBancaria eliminarCuentaBancaria(@WebParam(name = "numeroCuenta") int numeroCuenta) {
        ClienteController elimina = new ClienteController();
        CuentaBancaria elimn = elimina.eliminarCuentaBancaria(numeroCuenta);
        return elimn;
    }

    @WebMethod(operationName = "registrarPrestamo")
    public String registrarPrestamo(@WebParam(name = "cantidadPrestamo") double cantidadPrestamo, @WebParam(name = "numeroCui") int numeroCui, @WebParam(name = "idModalidad") int idModalidad) {
        ClienteController prestamo = new ClienteController();
        String presar = prestamo.IngresarPrestamos(cantidadPrestamo, numeroCui, idModalidad);
        return presar;
    }

    @WebMethod(operationName = "buscarPrestamos")
    public Prestamo buscarPrestamos(@WebParam(name = "numeroCui") int numeroCui) {
        ClienteController buscaPresta = new ClienteController();
        Prestamo busc = buscaPresta.buscarPrestamo(numeroCui);
        return busc;
    }

    @WebMethod(operationName = "eliminarPrestamo")
    public Prestamo eliminarPrestamo(@WebParam(name = "numeroCui") int numeroCui) {
        ClienteController elimina = new ClienteController();
        Prestamo delete = elimina.eliminarPrestamo(numeroCui);
        return delete;
    }

    @WebMethod(operationName = "registrarCheque")
    public String registrarCheque(@WebParam(name = "descripcion") String descripcion, @WebParam(name = "cantidadPago") double cantidadPago, @WebParam(name = "fechaPago") String fechaPago, @WebParam(name = "numeroCuenta") int numeroCuenta) {
        ClienteController regCheque = new ClienteController();
        String cheuqe = regCheque.registrarCobroDeCheque(descripcion, numeroCuenta, fechaPago, numeroCuenta);
        return cheuqe;
    }

    @WebMethod(operationName = "buscarCheque")
    public Cheque buscarCheque(@WebParam(name = "numeroCuenta") int numeroCuenta) {
        ClienteController bus = new ClienteController();
        Cheque busca = bus.buscarCheque(numeroCuenta);
        return busca;
    }

    @WebMethod(operationName = "modificarCheque")
    public Cheque modificarCheque(@WebParam(name = "descripcion") String descripcion, @WebParam(name = "cantidadPago") double cantidadPago, @WebParam(name = "fechaPago") String fechaPago, @WebParam(name = "numeroCuenta") int numeroCuenta) {
        ClienteController cheque = new ClienteController();
        Cheque chq = cheque.modificarCheque(descripcion, numeroCuenta, fechaPago, numeroCuenta);
        return chq;
    }

    @WebMethod(operationName = "eliminarCheque")
    public Cheque eliminarCheque(@WebParam(name = "numeroCuenta") int numeroCuenta) {
        ClienteController elimina = new ClienteController();
        Cheque delete = elimina.eliminarCheque(numeroCuenta);
        return delete;
    }

    @WebMethod(operationName = "reportePrestamo")
    public void reportePrestamo() {
        ClienteController reporte = new ClienteController();
        reporte.generarReportePrestamo();
    }

    @WebMethod(operationName = "registrarAgencia")
    public String registrarAgencia(@WebParam(name = "nombre") String nombre, @WebParam(name = "direccion") String direccion) {
        EmpleadoController registrarA = new EmpleadoController();
        String registra = registrarA.registrarAgencia(nombre, direccion);
        return registra;
    }

    @WebMethod(operationName = "buscarAgencia")
    public Agencia buscarAgencia(@WebParam(name = "nombre") String nombre) {
        EmpleadoController buscar = new EmpleadoController();
        Agencia bus = buscar.buscarAgencia(nombre);
        return bus;
    }

    @WebMethod(operationName = "eliminarAgencia")
    public Agencia eliminarAgencia(@WebParam(name = "nombre") String nombre) {
        EmpleadoController age = new EmpleadoController();
        Agencia elim = age.eliminAgencia(nombre);
        return elim;
    }

    @WebMethod(operationName = "modificarAgencia")
    public Agencia modificarAgencia(@WebParam(name = "nombre") String nombre, @WebParam(name = "direccion") String direccion) {
        EmpleadoController modifica = new EmpleadoController();
        Agencia mod = modifica.modificarAgencia(nombre, direccion);
        return mod;
    }

}
