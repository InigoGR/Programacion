
/**
 * Clase que crea un objeto de la clase Satelite y calcula su posicion en instantes con el motor apagado
 * e instantes con el motor encendido
 * 
 * @author Iñigo Gonzalez Ruiz 
 * @version 27/03/16
 */
public class PruebaSatelite
{
   public void main()
   {
       //Se crea el objeto
       Satelite prueba=new Satelite(4000,0,20);
       //Se pinta su posicion y se avanza temporalmente, con el motor apagado
       System.out.println(prueba.posX()+", "+prueba.posY());//0
       prueba.avanzaTiempo(2);
       System.out.println(prueba.posX()+", "+prueba.posY());//2
       prueba.avanzaTiempo(2);
       System.out.println(prueba.posX()+", "+prueba.posY());//4
       prueba.avanzaTiempo(2);
       System.out.println(prueba.posX()+", "+prueba.posY());//6
       //Se enciende el motor
       prueba.enciende();
       //Se pinta su posicion y se avanza temporalmente, con el motor encendido 
       prueba.avanzaTiempo(2);
       System.out.println(prueba.posX()+", "+prueba.posY());//8
       prueba.avanzaTiempo(2);
       System.out.println(prueba.posX()+", "+prueba.posY());//10
       prueba.avanzaTiempo(2);
       System.out.println(prueba.posX()+", "+prueba.posY());//12
    }
}