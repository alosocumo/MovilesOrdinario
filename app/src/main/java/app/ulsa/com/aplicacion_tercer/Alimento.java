package app.ulsa.com.aplicacion_tercer;

import java.io.Serializable;

/**
 * Created by Alondra on 06/09/2016.
 */
public class Alimento implements Serializable
{

    public String id;
    public String nombre;
    public String que;
    public String donde;
    public String imgUrl;
    public String latitud;
    public String longitud;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getQue() {
        return que;
    }

    public void setQue(String que) {
        this.que = que;
    }

    public String getDonde() {
        return donde;
    }

    public void setDonde(String donde) {
        this.donde = donde;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}