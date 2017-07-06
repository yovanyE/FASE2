
package modelo;
public class CuentaBancaria {
    private int numeroCuenta;
    private int idTipoCuenta;
    private int numeroCui;
    private int idClienteEmpresa;

    public CuentaBancaria() {
    }

    public CuentaBancaria(int numeroCuenta, int idTipoCuenta, int numeroCui, int idClienteEmpresa) {
        this.numeroCuenta = numeroCuenta;
        this.idTipoCuenta = idTipoCuenta;
        this.numeroCui = numeroCui;
        this.idClienteEmpresa = idClienteEmpresa;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public int getIdTipoCuenta() {
        return idTipoCuenta;
    }

    public void setIdTipoCuenta(int idTipoCuenta) {
        this.idTipoCuenta = idTipoCuenta;
    }

    public int getNumeroCui() {
        return numeroCui;
    }

    public void setNumeroCui(int numeroCui) {
        this.numeroCui = numeroCui;
    }

    public int getIdClienteEmpresa() {
        return idClienteEmpresa;
    }

    public void setIdClienteEmpresa(int idClienteEmpresa) {
        this.idClienteEmpresa = idClienteEmpresa;
    }
    
}
