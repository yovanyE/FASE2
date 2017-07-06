package modelo;

public class Prestamo {
    private double cantidadDinero;
    private int numeroCui;
    private int modalidad;

    public Prestamo() {
        
    }

    public Prestamo(Double cantidadDinero, int numeroCui, int modalidad) {
        this.cantidadDinero = cantidadDinero;
        this.numeroCui = numeroCui;
        this.modalidad = modalidad;
    }

    public double getCantidadDinero() {
        return cantidadDinero;
    }

    public void setCantidadDinero(double cantidadDinero) {
        this.cantidadDinero = cantidadDinero;
    }

    public int getNumeroCui() {
        return numeroCui;
    }

    public void setNumeroCui(int numeroCui) {
        this.numeroCui = numeroCui;
    }

    public int getModalidad() {
        return modalidad;
    }

    public void setModalidad(int modalidad) {
        this.modalidad = modalidad;
    }

 
    
}
