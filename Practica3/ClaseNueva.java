import fundamentos.*;
/**
 * Clase que ejecuta una animacion de un dron chocandose contra un edificio
 * 
 * @author Iñigo González 
 * @version 07/03/16
 */
public class ClaseNueva
{
    // instance variables - replace the example below with your own

    /**
     * Constructor for objects of class ClaseNueva
     */
    public void main()
    {
       Imagen edificio=new Imagen(500,0,"edificio.jpg");
       Imagen dron=new Imagen(300,700,"dron.jpg");
       Figura.espera(1000);
       int y;
       for(y=900;y>300;y=y-70)
       {
        dron.mueve(0,-70);
        Figura.espera(200);
       }
       int x;
       for(x=300;x<500;x=x+70)
       {
       dron.mueve(70,0);
        Figura.espera(200);
       }
       Imagen explosion=new Imagen(500,100,"boom.png");
    }

    
}
