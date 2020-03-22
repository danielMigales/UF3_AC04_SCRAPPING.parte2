package base;

/**
 * @author Daniel Migales
 */

public class Peliculas {
    
    String enlace;
    String titulo;
    int posicion;

    public Peliculas(String enlace, String titulo, int posicion) {
        this.enlace = enlace;
        this.titulo = titulo;
        this.posicion = posicion;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    @Override
    public String toString() {
        
        String pelicula = "Pelicula " + posicion + ":\n" + "\tURL: " + enlace + "\n" + "\tTitulo: " + titulo;
        return pelicula;
    }
    
    
    
}
