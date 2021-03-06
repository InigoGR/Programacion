///////////////////////////////////////////////////////////////////////
//                       Paquete fundamentos                         //
//  Conjunto de clases para hacer entrada/salida sencilla en Java    //
//                                                                   //
//                     Copyright (C) 2005-2012                       //
//                 Universidad de Cantabria, SPAIN                   //
//                                                                   //
// Authors: Michael Gonzalez   <mgh@unican.es>                       //
//          Mariano Benito Hoz <mbenitohoz at gmail dot com>         //
//                                                                   //
// This program is free software; you can redistribute it and/or     //
// modify it under the terms of the GNU General Public               //
// License as published by the Free Software Foundation; either      //
// version 3 of the License, or (at your option) any later version.  //
//                                                                   //
// This program is distributed in the hope that it will be useful,   //
// but WITHOUT ANY WARRANTY; without even the implied warranty of    //
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU //
// General Public License for more details.                          //
///////////////////////////////////////////////////////////////////////

package fundamentos;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**  Grafica     por Michael Gonzalez y Mariano Benito. 
 *   Version 3.3. Octubre 2012 <p>

     (modificacion de la clase por J M Bishop   July 1999)<p>
 

     Es una clase sencilla que ofrece facilidades para: <p>
     - almacenar puntos <p>
     - mostrarlos como una grafica de puntos o lineas <p>
     Se pueden mostrar varias graficas en la misma ventana <p>
     Los ejes se pintan automaticamente <p>

     Interfaz:  <p>
     ********   <p>

     Basica   <p>
     ------   <p>
     new Grafica ()  Constructor <p>
     inserta(x, y)   Inserta un punto en la lista. Los puntos deben estar  <p>
     ordenados por el eje X  <p>
     pinta()         Muestra los ejes y la grafica; luego, 
     espera a que se pulse el boton Aceptar 
     y cierra la ventana <p>

     Avanzada    <p>
     --------    <p>
     new Grafica (TituloGrafica, TituloEjeX, TituloEjeY)  <p>
     Version del constructor con opciones de titulos <p>
     Usar strings vacios si no se necesitan todos <p>

     otraGrafica()          Comienza una nueva grafica con los mismos ejes <p>
     Se puede usar un unico pinta() para todas  <p>
     las graficas. <p>

     ponColor(int 0 a 3)   Se puede elegir negro, rosa, azul o rojo <p>
     (hay constantes estaticas disponibles para usar <p>
     nombres en vez de numeros) <p>

     ponSimbolo(boolean)   Pone simbolos en la grafica actual. <p>
     el simbolo se deduce del color <p>

     ponSimbolo(int 0 a 3)  Pone simbolos en la grafica actual si no hay <p>
     colores definidos. Los simbolos disponibles son: <p>
     circulo, triangulo, trianguloInvertido, cuadrado <p>
     (hay constantes estaticas disponibles para usar <p>
     nombres en vez de numeros) <p>

     ponLineas(boolean)     Quita o pone la opcion de pintar la grafica  <p>
     con lineas. <p>
     Normalmente la opcion de lineas esta activa <p>

     ponTitulo(String)      Pone el titulo de la grafica actual <p>
     @author Michael Gonzalez Harbour <mgh at unican dot es>
     @author Mariano Benito Hoz <mbenitohoz at gmail dot com>
     @version 3.2
*/

public class Grafica extends JFrame implements ActionListener {

    private static final long serialVersionUID = 3918001L;
    private String xAxisTitle, yAxisTitle, graphTitle;
    private boolean keys;
    private boolean hasData = false;
    private JButton okButton, closeButton;
    private Watcher okWatcher = new Watcher();

    /**
     * Constructor simple, que deja los titulos de la ventana y de los
     * ejes en blanco.
     */
    public Grafica() {
        super();
        initializeGraph();
        xAxisTitle = "";
        yAxisTitle = "";
        graphTitle = "";
    }

    /**
     * Constructor alternativo, que pone los titulos de la ventana
     * y de los ejes.
     */
    public Grafica(String g, String x, String y) {
        super();
        initializeGraph();
        graphTitle = g;
        xAxisTitle = x;
        yAxisTitle = y;
    }

    // Clase sencilla para almacenar la informacion basica del conjunto 
    // de puntos
    private class Dataset {
        int count;
        int plotType;
        String title;
        boolean colorRequired, symbolRequired, titleRequired, lineRequired;
    }

    private Vector<Point> points;
    private Vector<Dataset> datasets;
    private int currentDataset = -1;

    // Metodo privado que inicializa el grafico
    private void initializeGraph() {
        datasets = new Vector<Dataset>(10, 10);
        otraGrafica();
        points = new Vector<Point>(100, 100);
        xMax = yMax = Double.MIN_VALUE;
        xMin = yMin = Double.MAX_VALUE;
        keys = false;
        setSize(640, 480);
        super.setTitle("Grafica");
        Panel p = new Panel(new FlowLayout(FlowLayout.CENTER, 2, 0));
        okButton = new JButton("Aceptar");
        okButton.addActionListener(this);
        okButton.setEnabled(false);
        p.add(okButton);
        closeButton = new JButton("Cerrar Aplicacion");
        closeButton.addActionListener(this);
        closeButton.setEnabled(true);
        p.add(closeButton);
        add("South", p);
        addWindowListener(new WindowAdapter() {

                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
    }

    /**
     *  Permite crear una nueva grafica en la misma ventana.
     */
    public void otraGrafica() {
        Dataset d = new Dataset();
        d.count = 0;
        d.plotType = negro;
        d.title = "";
        d.symbolRequired = false;
        d.colorRequired = false;
        d.titleRequired = false;
        d.lineRequired = true;
        datasets.add(d);
        currentDataset++;
    }

    /**
     * Pone el color de la grafica al valor indicado por c, que debe ser
     * un entero de cero a tres, o una de las constantes estaticas
     * rojo, azulo, rosa, negro.
     */
    public void ponColor(int c) {
        datasets.elementAt(currentDataset).colorRequired = true;
        datasets.elementAt(currentDataset).plotType = c;
    }

    /**
     * Pone o quita la opcion de simbolos en la grafica.
     * El simbolo se elige en funcion del color.
     */
    public void ponSimbolo(boolean b) {
        datasets.elementAt(currentDataset).symbolRequired = b;
    }

    /**
     * Pone el simbolo de la grafica al valor indicado por c, si no
     * se ha especificado color. Si se ha especificado color,
     * el simbolo se elige en funcion del color. C debe ser un entero
     * entre 0 y 3 o una de las constantes estaticas: cuadrado, triangulo,
     * circulo, trianguloInvertido.
     */
    public void ponSimbolo(int c) {
        datasets.elementAt(currentDataset).symbolRequired = true;
        datasets.elementAt(currentDataset).plotType = c;
    }

    /**
     * Pone el titulo de la grafica
     */
    public void ponTitulo(String s) {
        datasets.elementAt(currentDataset).titleRequired = true;
        datasets.elementAt(currentDataset).title = s;
        keys = true;
    }

    /**
     * Pone o quita la opcion de mostrar la grafica con lineas entre
     * cada punto. Por omision la grafica muestra las lineas.
     */
    public void ponLineas(boolean b) {
        datasets.elementAt(currentDataset).lineRequired = b;
        if (b == false) {
            datasets.elementAt(currentDataset).symbolRequired = true;
        }
    }

    /**
     * Inserta el punto (x,y) en la grafica actual. Los puntos deben
     * darse ordenados por eje X
     */
    public void inserta(double x, double y) {
        points.add(new Point(x, y));
        datasets.elementAt(currentDataset).count++;
        if (x > xMax) {
            xMax = x;
        }
        if (x < xMin) {
            xMin = x;
        }
        if (y > yMax) {
            yMax = y;
        }
        if (y < yMin) {
            yMin = y;
        }
        hasData = true;
    }

    /**
     *  Pinta todas las graficas y espera a que se pulse el boton aceptar
     */
    public void pinta() {
        if (points.size()>=2) {
            repaint();
            setVisible(true);
            okButton.setEnabled(true);
            this.setVisible(true);
            okWatcher.watch();
            this.setVisible(false);
        } else {
            msjError("No hay suficientes puntos para pintar la grafica");
        }
    }

    // Clase simple para guardar unas coordenadas x y
    private class Point {

        double xCoord, yCoord;

        Point(double x, double y) {
            xCoord = x;
            yCoord = y;
        }
    }

    // Dibuja la leyenda con los titulos
    private void drawTitles(Graphics g) {
        Dataset d;
        int x = xBorder;
        int y = getHeight() - yBorder / 2;
        for (int cd = 0; cd <= currentDataset; cd++) {
            d = datasets.elementAt(cd);
            if (d.colorRequired) {
                changeColor(g, d.plotType);
            }
            if (d.symbolRequired) {
                drawSymbol(g, d.plotType, x, y - cs);
                x += 4 * cs;
            }
            g.drawString(d.title, x, y);
            x += g.getFontMetrics().stringWidth(d.title) + 20;
        }
        g.setColor(Color.black);
    }

    private static DecimalFormat N = new DecimalFormat();
    private static final String spaces = "                    ";

    // Formatear un numero
    private static String format(double number, int align, int frac) {
        N.setGroupingUsed(false);
        N.setMaximumFractionDigits(frac);
        N.setMinimumFractionDigits(frac);
        String num = N.format(number);
        if (num.length() < align) {
            num = spaces.substring(0, align - num.length()) + num;
        }
        return num;
    }

    // Dibuja los ejes
    private void drawAxes(Graphics g) {
        //---------------------
        Font plain = g.getFont();
        Font small = new Font(plain.getFamily(), Font.PLAIN, 10);
        Font bold = new Font(plain.getFamily(), Font.BOLD, 14);

        g.drawLine(xBorder - 5, yOrigin, xAxisLength + xBorder + 5, yOrigin);
        g.drawLine(xOrigin, yBorder - 5, xOrigin, yAxisLength + yBorder + 5);
        g.drawString
            (xAxisTitle,
             getWidth() - g.getFontMetrics().stringWidth(xAxisTitle) - 
             xBorder / 2,
             yOrigin - 5);
        g.drawString
            (yAxisTitle,
             xOrigin - g.getFontMetrics().stringWidth(yAxisTitle) / 2,
             yBorder - 8);

        g.setFont(bold);
        g.drawString
            (graphTitle,
             (getWidth() - g.getFontMetrics().stringWidth(graphTitle)) / 2,
             yBorder / 2+8);
        g.setFont(plain);
        if (keys) {
            drawTitles(g);
        }

        // Tick and Label the four min/max points only
        int scaleXMin = scaleX(xMin);
        int scaleXMax = scaleX(xMax);
        int scaleYMin = scaleY(yMin);
        int scaleYMax = scaleY(yMax);
        g.drawLine(xOrigin - 5, scaleYMax, xOrigin + 5, scaleYMax);
        g.drawLine(xOrigin - 5, scaleYMin, xOrigin + 5, scaleYMin);
        g.drawLine(scaleXMax, yOrigin + 5, scaleXMax, yOrigin);
        g.drawLine(scaleXMin, yOrigin + 5, scaleXMin, yOrigin);

        g.setFont(small);
        g.drawString(format(xMin, 6, 2), scaleXMin - 10, yOrigin + 15);
        g.drawString(format(xMax, 6, 2), scaleXMax - 10, yOrigin + 15);
        g.drawString(format(yMin, 6, 2), xOrigin - 35, scaleYMin + 4);
        g.drawString(format(yMax, 6, 2), xOrigin - 35, scaleYMax + 4);
        g.setFont(plain);
    }

    private double xSpread, ySpread, xMin, xMax, yMin, yMax;
    private int xAxisLength, yAxisLength, xOrigin, yOrigin;
    private int xBorder, yBorder;

    /**
       No usar directamente esta operacion interna de la clase
    */
    public void paint(Graphics g) {
        g.clearRect(0,0,640,480);
        //-----------------
        // calulate length of axes from window size minus a border of 20
        xBorder = 40;
        yBorder = 80;
        xAxisLength = getWidth() - 2 * xBorder;
        yAxisLength = getHeight() - 2 * yBorder;

        // calculate value spreads from mins and maxs which have
        // been recorded as we go
        xSpread = xMax - xMin;
        ySpread = yMax - yMin;
        if (xMin > 0) {
            xOrigin = scaleX(xMin);
        } else {
            xOrigin = scaleX(0);
        }
        if (yMin > 0) {
            yOrigin = scaleY(yMin);
        } else {
            yOrigin = scaleY(0);
        }
        // only paint graphs if there are enough points
        if (points.size()>=2) {
            drawAxes(g);
            plotGraphs(g);
        }
    }

    // Escalar el eje x
    private int scaleX(double x) {
        //------------------
        return (int) ((x - xMin) / xSpread * xAxisLength) + xBorder;
    }

    // Escalar el eje y
    private int scaleY(double y) {
        //------------------
        return (int) (yAxisLength - ((y - yMin)
                                     / ySpread * yAxisLength)) + yBorder;
    }

    // Cambiar de color
    private void changeColor(Graphics g, int c) {
        //------------------------
        switch (c) {
        case negro: {
            g.setColor(Color.black);
            break;
        }
        case rosa: {
            g.setColor(Color.magenta);
            break;
        }
        case azul: {
            g.setColor(Color.blue);
            break;
        }
        case rojo: {
            g.setColor(Color.red);
            break;
        }
        }
    }

    private static final int cs = 3; // pixel size of a symbol

    // Dibuja un simbolo
    private void drawSymbol(Graphics g, int sy, int x, int y) {
        switch (sy) {
        case negro: {
            g.drawOval(x - cs, y - cs, 2 * cs, 2 * cs);
            break;
        }
        case rosa: {
            g.drawPolygon(trix(x), uptriy(y), 3);
            break;
        }
        case azul: {
            g.drawPolygon(trix(x), triy(y), 3);
            break;
        }
        case rojo: {
            g.drawRect(x - cs, y - cs, 2 * cs, 2 * cs);
            break;
        }
        }
    }

    // Dibuja las graficas
    private void plotGraphs(Graphics g) {
        Point p, q;
        int x1, y1, x2, y2;
        Dataset d;
        boolean lastset;
        Color c;

        // Loop through each dataset
        /* The points are in one big list, split by the
           counts recorded in each dataset */
        int currentPoint = 0;

        for (int cd = 0; cd <= currentDataset; cd++) {
            d = datasets.elementAt(cd);
            if (d.colorRequired) {
                changeColor(g, d.plotType);
            }
            // Start with the first point in the list
            // for this graph
            if (currentPoint<points.size()) {
                p = points.elementAt(currentPoint);
                x1 = scaleX(p.xCoord);
                y1 = scaleY(p.yCoord);
                if (d.symbolRequired) {
                    drawSymbol(g, d.plotType, x1, y1);
                }
                currentPoint++;
                
                // Loop through the points as stored in the list
                for (int i = 1; i < d.count; i++) {
                    q = points.elementAt(currentPoint);
                    x2 = scaleX(q.xCoord);
                    y2 = scaleY(q.yCoord);
                    
                    // plot the line and/or point
                    if (d.lineRequired) {
                        g.drawLine(x1, y1, x2, y2);
                    }
                    if (d.symbolRequired) {
                        drawSymbol(g, d.plotType, x2, y2);
                    }
                    x1 = x2;
                    y1 = y2;
                    currentPoint++;
                }
            }
        }
    }

    private int[] trix(int p) {
        int[] a = new int[3];
        a[0] = p - cs;
        a[1] = p + cs;
        a[2] = p;
        return a;
    }

    private int[] triy(int p) {
        int[] a = new int[3];
        a[0] = p + cs;
        a[1] = p + cs;
        a[2] = p - cs;
        return a;
    }

    private int[] uptriy(int p) {
        int[] a = new int[3];
        a[0] = p - cs;
        a[1] = p - cs;
        a[2] = p + cs;
        return a;
    }

    /**
     * Constantes estaticas que definen los colores disponibles
     */
    public static final int rojo = 3;
    public static final int azul = 2;
    public static final int rosa = 1;
    public static final int negro = 0;
    /**
     * Constantes estaticas que definen los simbolos disponibles
     */
    public static final int circulo = negro;
    public static final int triangulo = azul;
    public static final int trianguloInvertido = rosa;
    public static final int cuadrado = rojo;

    /**
     *  No usar directamente esta operacion interna de la clase
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okButton) {
            okWatcher.ready();
        } else if (e.getSource() == closeButton) {
            System.exit(0);
        }
    }

    class Watcher {

        private boolean ok;

        Watcher() {
            ok = false;
        }

        synchronized void watch() {
            while (!ok) {
                try {
                    wait(500);
                } catch (InterruptedException e) {
                }
            }
            ok = false;
        }

        synchronized void ready() {
            ok = true;
            notify();
        }
    }

    // Pone un mensaje de error en una ventana
    private void msjError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error",
                                      JOptionPane.ERROR_MESSAGE);
    }

}
