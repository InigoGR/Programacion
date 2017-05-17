
/**
 * Write a description of class AvionPapel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AvionPapel
{
   private Linea l1,l2,l3,l4,l5,l6;
   /**
    * Metodo constructor de objetos avion de papel en la posición especificada en pixeles tomando
    * la punta del avion como referencia
    */
   public AvionPapel(int x1, int y1)
    {
        l1=new Linea(x1,y1,x1-40,y1+100);
        l2=new Linea(x1,y1,x1-5,y1+80);
        l3=new Linea(x1-40,y1+100,x1-5,y1+80);
        l4=new Linea(x1,y1,x1+40,y1+100);
        l5=new Linea(x1,y1,x1+5,y1+80);
        l6=new Linea(x1+40,y1+100,x1+5,y1+80);
    }   
    /**
     * Metodo que mueve un objeto avion de papel la cantidad de pixeles especificada
     */
   public void mueve(int x, int y)
    {
        l1.mueve(x,y);
        l2.mueve(x,y);
        l3.mueve(x,y);
        l4.mueve(x,y);
        l5.mueve(x,y);
        l6.mueve(x,y);
    }
}
