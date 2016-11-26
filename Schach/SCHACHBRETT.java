import javax.swing.*;
import java.awt.*;
public class SCHACHBRETT extends JLayeredPane {
    private static final Dimension SIZE = new Dimension(810,810);
    private static final Point LOCATION = new Point(0,0);
    
    private FELD[][] felder;
    private GAME parent;
    
    public SCHACHBRETT(GAME sender){
        super();
        this.setSize(SIZE);
        this.setLocation(LOCATION);
        this.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
        parent = sender;
        felder = new FELD[8][8];
        String buchstaben[] = {"A","B","C","D","E","F","G","H"};
        for(int y = 0; y < 8; y++){
            for(int x = 0; x < 8; x++){
                Color c;
                if(y % 2 == 0){
                    if(x%2 == 0){
                        c = FELD.WHITE;
                    }else{
                        c = FELD.BLACK;
                    }
                }else{
                    if(x%2 == 0){
                        c = FELD.BLACK;
                        
                    }else{
                        c = FELD.WHITE;
                        
                    };
                }
                felder[y][x] = new FELD(this, (int) (x * FELD.size.getWidth()) + 5,(int) (y * FELD.size.getHeight()) + 5,c, buchstaben[x] + Integer.toString(y +1));
            }
        }
        sender.add(this);
        createFigurs();
        
    }
    
    public GAME getParentGame(){
        return parent;
    }
    
    private void createFigurs(){
        /* FELD AUFBAU
        00 01 02 03 04 05 06 07 Weiße Figuren
        08 09 10 11 12 13 14 15 Weiße Bauern
        16 17 18 19 20 21 22 23
        24 25 26 27 28 29 30 31
        32 33 34 35 36 37 38 39
        40 41 42 43 44 45 46 47
        48 49 50 51 52 53 54 55 Schwarze Bauern
        56 57 58 59 60 61 62 63 Schwarze Figuren
        */
        
        
        
        //Weiße
        //######################
        
        //Bauern
        for(int x = 0;x < 8; x++){
            new FIGUR(this,felder[1][x], FIGUR.BAUER, FIGUR.WHITE);
        }
        
        //Türme
        new FIGUR(this,felder[0][0], FIGUR.TURM, FIGUR.WHITE);
        new FIGUR(this,felder[0][7], FIGUR.TURM, FIGUR.WHITE);
        
        //Springer
        new FIGUR(this,felder[0][1], FIGUR.SPRINGER, FIGUR.WHITE);
        new FIGUR(this,felder[0][6], FIGUR.SPRINGER, FIGUR.WHITE);
        
        //Laeufer
        new FIGUR(this,felder[0][2], FIGUR.LAEUFER, FIGUR.WHITE);
        new FIGUR(this,felder[0][5], FIGUR.LAEUFER, FIGUR.WHITE);
        
        //König & Dame
        
        new FIGUR(this,felder[0][3], FIGUR.KOENIG, FIGUR.WHITE);
        new FIGUR(this,felder[0][4], FIGUR.DAME, FIGUR.WHITE);
        
        
        //Schwarze
        //######################
        //Bauern
        for(int x = 0;x < 8; x++){
            new FIGUR(this,felder[6][x], FIGUR.BAUER, FIGUR.BLACK);
        }
        
        //Türme
        new FIGUR(this,felder[7][0], FIGUR.TURM, FIGUR.BLACK);
        new FIGUR(this,felder[7][7], FIGUR.TURM, FIGUR.BLACK);
        
        //Springer
        new FIGUR(this,felder[7][1], FIGUR.SPRINGER, FIGUR.BLACK);
        new FIGUR(this,felder[7][6], FIGUR.SPRINGER, FIGUR.BLACK);
        
        //Laeufer
        new FIGUR(this,felder[7][2], FIGUR.LAEUFER, FIGUR.BLACK);
        new FIGUR(this,felder[7][5], FIGUR.LAEUFER, FIGUR.BLACK);
        
        //König & Dame
        
        new FIGUR(this,felder[7][3], FIGUR.KOENIG, FIGUR.BLACK);
        new FIGUR(this,felder[7][4], FIGUR.DAME, FIGUR.BLACK);
        
    }

    public FELD[][] getFields(){
        return felder;
    }
    
    
}