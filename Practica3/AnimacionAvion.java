
/**
 * Clase que ejecuta una animacion de un avion de papel chocandose contra un edificio
 * 
 * @author Iñigo González Ruiz
 * @version 07/03/16
 */
public class AnimacionAvion
{
     public void main()
    {
       Imagen edificio=new Imagen(500,0,"edificio.jpg");
       AvionPapel avion=new AvionPapel(300,700);
       Figura.espera(1000);
       int y;
       for(y=900;y>300;y=y-70)
       {
           avion.mueve(0,-70);
           Figura.espera(200);
       }
       int x;
       for(x=300;x<500;x=x+70)
       {
           avion.mueve(70,0);
           Figura.espera(200);
       }
       Imagen explosion=new Imagen(500,100,"boom.png");
    }
}
    
