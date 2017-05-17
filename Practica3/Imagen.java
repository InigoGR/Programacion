import fundamentos.Dibujo;

/**
 * Clase que representa una imagen que se pinta en una ventana de la clase Dibujo
 */
public class Imagen extends Figura
{
    // esquina superior izquierda
    private int x1,y1;
    // nombre del fichero
    private String nombreFichero;

    /**
     * Constructor al que se le pasan la posicion de la esquina 
     * superior izquierda (en pixels) y el nombre del fichero 
     * que contiene la imagen (usar formato jpg o png)
     */
    public Imagen (int x1, int y1, String nombreFichero)
    {
        this.x1=x1;
        this.y1=y1;
        this.nombreFichero=nombreFichero;
        Figura.lista.anadeFigura(this);
        Figura.lista.pinta();
    }

    /**
     * Pinta la imagen
     */
    protected void pinta(Dibujo dib)
    {
        dib.dibujaImagen(x1,y1,nombreFichero);
    }
    
    /**
     * Mueve la imagen en la cantidad relativa indicada en pixels
     */
    public  void mueve(int deltaX, int deltaY) {
        x1=x1+deltaX;
        y1=y1+deltaY;
        Figura.lista.pinta();        
    }

}
