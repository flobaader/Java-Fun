import java.awt.*;
import javax.swing.*;
public class GAME extends JFrame{
    FIGUR checkedFigur;
    FIGUR checkedEnemy;
    FELD checkedFeld;
    FELD possibleFields[];
    SCHACHBRETT s;
    LABELS labels;
    String activeColor;

    public GAME (){
        this.setSize(1200, 900);
        this.setTitle("Schach - by Florian Baader & Lukas Rosteck");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);        
        s = new SCHACHBRETT(this);
        labels = new LABELS(this);
        activeColor = FIGUR.BLACK;
        labels.setOnTurnText(FIGUR.BLACK);
        this.setVisible(true);

    }

    public void FigurChecked(FIGUR sender){
        //Gleiche Figur wird angeklickt --> wird in der Figur abgefangen
        if(checkedFeld == null && sender.getColor() != activeColor){
            sender.setChecked(false);
        }
        
        //Erste Figur nur wenn eigene Farbe gewählt wird
        if(checkedFeld == null && sender.getColor() == activeColor){

            //Figur wird angeklickt
            if(checkedFigur != null){
                checkedFigur.setChecked(false);
            }
            checkedFigur = sender;
            checkedFigur.setChecked(true);

            //Mögliche Felder werden gesetzt
            possibleFields = new FELD[64];
            int indX = checkedFigur.getFELD().getFeldIndexX();
            int indY = checkedFigur.getFELD().getFeldIndexY();
            switch(checkedFigur.getType()){

                case FIGUR.LAEUFER:
                addArrays(possibleFields, possibleDiagonal(indX,indY ));
                break;
                case FIGUR.TURM:
                addArrays(possibleFields, possibleDirectional(indX,indY ));
                break;
                case FIGUR.DAME:
                addArrays(possibleFields, possibleDiagonal(indX,indY ));
                addArrays(possibleFields, possibleDirectional(indX,indY ));
                break;

        
            }
            //Felder mit Figuren der gleichen Farbe löschen
            int counter = 0;
            for( FELD f : possibleFields){
                if(f != null && f.getFigur() != null){
                    if(f.getFigur().getColor() == activeColor){
                        possibleFields[counter] = null;
                    }
                }
                counter++;
            }

            //Alle Felder markieren
            for( FELD f: possibleFields){
                if(f != null){
                    f.setOkay(true);
                }
            }

            checkSelected();
        }else if (checkedFigur != null && sender.getColor() != activeColor){
            //erste Figur ist markiert und die jetztige Figur von der Gegnerfarbe
            //Zweite Figur wird markiert --> Figur wird geschmissen
            
        
        
        }
    }

    public void FeldChecked(FELD sender){
        //Move Figur --> Feld is empty

        if(checkedFigur != null && checkFeld(sender, possibleFields) && checkedFigur.getColor() == activeColor){
            sender.setFigur(checkedFigur);
            checkedFigur.setChecked(false);
            checkedFigur = null;
            changeActivePlayer();
        }else if (checkedFigur != null){
            //Falsches Feld wird angeklickt
            checkedFigur.setChecked(false);
            checkedFigur = null;

        }
    }

    private void checkSelected(){
        if(checkedFeld != null & checkedFigur != null){
            checkedFeld.setChecked(false);
            checkedFigur.setChecked(false);
            checkedFeld.setFigur(checkedFigur);
            checkedFigur = null;
            checkedFeld = null;
        }

    }

    private FELD[] possibleDiagonal(int posY, int posX){
        //Diagonale Felder werden ausgewählt
        FELD felder[][] = s.getFields();
        FELD possible[] = new FELD[63];

        int counter = 0;
        for(int y = 0; y < 8; y++){
            for(int x = 0; x < 8; x++){
                if(Math.abs(x-posX) == Math.abs(y - posY)){
                    possible[counter] = felder[x][y];
                    counter++;
                }

            }

        }
        return possible;
    }

    private FELD[] possibleDirectional(int posY, int posX){
        //Waagrechte und Horizontale Felder werden ausgewählt
        FELD felder[][] = s.getFields();
        FELD possible[] = new FELD[63];

        int counter = 0;

        for(int y = 0; y < 8; y++){
            for(int x = 0; x < 8; x++){
                
                if(x == posX || y == posY){
                    possible[counter] = felder[x][y];
                    counter++;
                    if(felder[x][y].getFigur() != null && felder[x][y].getFigur().getColor() == activeColor){
                        break;
                    }
                }
            }
        }
        return possible;
    }

    private void addArrays(FELD[] feld1, FELD[] feld2){
        int counter = 0;
        for(FELD f: feld1){
            if(f != null){
                counter++;;
            }
        }

        for(FELD f: feld2){
            if( f!= null){
                feld1[counter] = f;
                counter++;
            }
        }

    }

    public void clear(){
        FELD felder[][] = s.getFields();
        for(FELD[] fe: felder){
            for(FELD f:fe){
                f.setOkay(false);
                f.setLocked(false);
            }
        }
        s.repaint();
    }

    private boolean checkFeld(FELD feld, FELD[] array){
        //Wenn das Feld leer ist
        if(array == null){
            return false;
        }

        for(FELD f: array){
            if( f == feld){
                return true;
            }
        }
        return false;

    }

    private void changeActivePlayer(){
        if(activeColor == FIGUR.BLACK){
            activeColor = FIGUR.WHITE;
            labels.setOnTurnText(FIGUR.WHITE);
        }else{
            activeColor = FIGUR.BLACK;
            labels.setOnTurnText(FIGUR.BLACK);
        }

    }
    
    public void DeleteCheckedFigur(){
        checkedFigur = null;
    }
    
    
}