
/**
 * Write a description of class Principal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Principal
{
    
    public static void main(String[]args)
    {
        Piedra NuevaPiedra= new Piedra(0.0,100,0,9.8);
        System.out.println("La posición x de la piedra es "+NuevaPiedra.getPosX());
        System.out.println("La posición y de la piedra es "+NuevaPiedra.getPosY()); 
        NuevaPiedra.avanzaTiempo(0.5);
        System.out.println("La posición x de la piedra es "+NuevaPiedra.getPosX());
        System.out.println("La posición y de la piedra es "+NuevaPiedra.getPosY());
        
    }
}
   
    
