import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

/**
 *  Eine Klasse zur Darstellung eines Feldes auf einem Schachbrett
 *  
 *  @author Florian Baader, Lukas Rosteck
 *  
 *  @version 1.2
 *  
 */
public class FELD extends JPanel{
    //Festgelegte Größe
    public static final Dimension size = new Dimension(100,100);
    
    //Festgelegte FARBEN
    public static final Color BLACK = new Color(132,0,0);
    public static final Color WHITE = new Color(192,192,192);
    public static final Color RED = new Color(255,0,0);
    public static final Color GREEN = new Color(0,196,0);
    
    //Position
    public Point pos;
    
    //Zusätzliche Variablen
    private Color farbe;
    private FIGUR figur; //Die Figur, die auf dem Feld steht
    private String name; // z.B. G6 oder A3
    private boolean checked = false;
    private SCHACHBRETT parent;
    private int FeldIndexX;
    private int FeldIndexY;
    
    
    /*
     * Konstruktor, der das Schachfeld erzeugt
     * 
     * @param sender Das Fenster, dass ein Schachfeld erzeugt
     * @param posX Die x-Koordinate, des Felds
     * @param posY Die y-Koordinate, des Felds
     * @param f Die Farbe des Feldes
     * @param n Der Name des Feldes z.B. A3
     */
    public FELD(SCHACHBRETT sender,int posX, int posY, Color f, String n){
        super();
        name = n;
        pos = new Point(posX,posY);
        farbe = f;
        parent = sender;
        FeldIndexX = (posX-5) / 100;
        FeldIndexY = (posY-5) / 100;
        this.setLocation(pos);
        this.setSize(size);
        this.setBackground(farbe);
        this.addMouseListener( new MouseAdapter(){
            public void mouseClicked(MouseEvent me){
                FELD feld = (FELD) me.getComponent();
                feld.getParentSchachbrett().getParentGame().FeldChecked(feld);
            }
            
        });
        sender.add(this,0);
    }
    
    public void setChecked(boolean b){
        if(b == false){
            checked = false;
            this.setBorder(null);
        }else{
            checked = true;
            this.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.black));
        }
        this.repaint();
    }
     
    public void setFigur(FIGUR f){
        f.setLocation((int) this.getLocation().getX(), (int)this.getLocation().getY());
        figur = f;
        figur.setFELD(this);
    }
    
    public FIGUR getFigur(){
        return figur;
    }
    
    private void deleteFigur(){
        figur = null;
    }
    
    private boolean isFull(){
        if(figur == null){
            return false;
        }else{
            return true;
        }
    }
    
    public void setLocked(boolean b){
        if(b){
            this.setBackground(RED);
        }else{
            this.setBackground(farbe);
        }
        
    }
    
    public void setOkay(boolean b){
        if(b){
            this.setBackground(GREEN);
        }else{
            this.setBackground(farbe);
        }
        
        if(b == false){
            this.setBorder(null);
        }else{
            this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        }
      
    }
    
    public SCHACHBRETT getParentSchachbrett(){
        return parent;
    }
    
    public int getFeldIndexX(){
        return FeldIndexX;
    }
    
    public int getFeldIndexY(){
        return FeldIndexY;
    }
    
    
    
    
    
    
    
}