import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 * Eine Klasse zur Darstellung einer Schachfigur
 * 
 * @author Florian Baader, Lukas Rosteck
 * @version 1.2
 */
public class FIGUR extends JLabel{
    //Festgelegte Größe
    private static final Dimension size = new Dimension(100,100);
    
    //TYPEN von Figuren
    public static final String BAUER = "BAUER";
    public static final String DAME = "DAME";
    public static final String KOENIG = "KOENIG";
    public static final String TURM = "TURM";
    public static final String SPRINGER = "SPRINGER";
    public static final String LAEUFER = "LAEUFER";
    
    //TYPEN von Farben
    public static final String BLACK = "Black";
    public static final String WHITE = "White";
       
    private String path;
    private ImageIcon icon;
    private boolean checked = false;
    private Point pos;
    private String type;
    private String color;
    private GAME parent;
    private FELD currentFELD;
    private boolean startPos = true;
    
    
    /**
     * Standartkonstruktor für eine Schachfigur
     * @param sender Das Fenster, für das eine Figur erstellt werden soll
     * @param posX Die x-Position der Figur
     * @param posy Die y-Position der Figur
     * @param t Der Typ der Figur: FIGUR.BAUER, FIGUR.DAME, FIGUR.KOENIG, FIGUR.TURM, FIGUR. SPRINGER , FIGUR.LAEUFER
     * @param c Die Farbe der Figur: FIGUR.BLACK , FIGUR.WHITE
     */
    public FIGUR(SCHACHBRETT sender,FELD f, String t, String c){
        super();
        pos = new Point((int)f.getLocation().getX(),(int)f.getLocation().getY());
        type = t;
        color = c;
        currentFELD = f;
        currentFELD.setFigur(this);
        parent = sender.getParentGame();
        path = "/doc/resources/icons/";
        switch(c){
            case BLACK: path += "black/";
                             break;
            case WHITE: path += "white/";
                             break;
        }
        icon = new ImageIcon(getClass().getResource(path + type +  ".png").getPath());
        icon.setImage(icon.getImage().getScaledInstance((int)size.getWidth(), (int)size.getHeight(), Image.SCALE_DEFAULT));
        
        this.setSize(size);
        this.setLocation(pos);
        this.setIcon(icon);
        this.addMouseListener( new MouseAdapter(){
            public void mouseClicked(MouseEvent me){
                FIGUR sender = (FIGUR) me.getComponent();
                sender.getParentGame().clear();
                if(checked){
                    setChecked(false);
                    parent.DeleteCheckedFigur();
                }else{
                    setChecked(true);
                    parent.FigurChecked(sender);
                }
                
            }
        
        });
        
        sender.add(this,1);
    }
    
    public String getType(){
        return type;
    }
    
    public void setChecked(boolean b){
        //Löscht alle Markierungen
        parent.clear();
        if(b){
            icon = new ImageIcon(getClass().getResource("/doc/resources/icons/blue/" + type +  ".png").getPath());
            this.setBorder(BorderFactory.createRaisedBevelBorder());
        }else{
            icon = new ImageIcon(getClass().getResource(path + type +  ".png").getPath());
            this.setBorder(null);
        }
        checked = b;
        icon.setImage(icon.getImage().getScaledInstance((int)size.getWidth(), (int)size.getHeight(), Image.SCALE_DEFAULT));
        this.setIcon(icon);
        this.repaint();
    }
    
    public boolean isChecked(){
        return checked;
    }
    
    public Point getPos(){
        return pos;
    }
    
    public GAME getParentGame(){
        return parent;
    }
    
    public void setFELD(FELD f){
        currentFELD = f;
        startPos = false;
    }
    
    public FELD getFELD(){
        return currentFELD;
    }
    
    public String getColor(){
        return color;
    }
    
    public boolean isStartPos(){
        return startPos;
    }
    

  


}