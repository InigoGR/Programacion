import fundamentos.Dibujo;
import java.util.*;
/**
 * Clase que representa una figura abstracta que se dibuja en una  
 * ventana de la clase fundamentos.Dibujo
 */
public abstract class Figura
{

    protected static ListaFiguras lista=new ListaFiguras();
    
    /**
     * Clase que representa una lista de figuras que se pintan
     * en una ventana de la clase Dibujo
     */
    protected static class ListaFiguras
    {
        // lista de figuras
        private ArrayList<Figura> lista=new ArrayList<Figura>();
        // dibujo
        private Dibujo dib;

        /**
         * Constructor de la lista de figuras; 
         * crea la ventana y hace la lista vacia
         */
        public ListaFiguras()
        {
            dib=new Dibujo("Lista de figuras",900,900);
            dib.ponGrosorLapiz(3);
        }

        /**
         * Anade la figura indicada a la lista. Se pueden anadir figuras derivadas
         * de la clase Figura, tales como Linea, Circulo, Rectangulo o Imagen
         */
        public void anadeFigura(Figura fig)
        {
            lista.add(fig);
        }

        /**
         * Pinta todas las figuras almacenadas en la lista, y espera a que 
         * se pulse el boton OK
         */
        public void pintaYEsperaConfirmacion() {
            dib.borra();
            for (Figura fig : lista) {
                fig.pinta(dib);
            }
            dib.espera();
        }

        /**
         * Pinta todas las figuras almacenadas en la lista
         * sin esperar a la pulsacion del boton OK
         */
        public void pinta() {
            dib.borra();
            for (Figura fig : lista) {
                fig.pinta(dib);
            }
            dib.pinta();
            // espera un rato
            espera(10);
        }

    } 

    /**
     * Pinta la figura
     */
    protected abstract void pinta (Dibujo dib);

    /**
     * Mueve la figura en la cantidad indicada en Pixels
     */
    public abstract void mueve(int deltaX, int deltaY);

    /**
     *  Espera un tiempo especificado en milisegundos
     */
    public static void espera(int milis) {
        try{
            Thread.sleep(milis); 
        } catch (Exception e) {

        }
    }

}
