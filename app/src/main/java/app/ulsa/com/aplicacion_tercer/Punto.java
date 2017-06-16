package app.ulsa.com.aplicacion_tercer;

/**
 * Created by Alondra on 08/03/2017.
 */
public class Punto  {

    private double latitud;
    private double longitud;
    private String titulo;
    private String descripcion;

    public Punto(){}

    public Punto(double latitud, double longitud, String titulo, String descripcion){
        this.latitud = latitud;
        this.longitud = longitud;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}



