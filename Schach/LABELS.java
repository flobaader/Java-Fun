import javax.swing.*;
import java.awt.*;
public class LABELS{
    JLabel Labels[] = new JLabel[16];
    String buchstaben[] = {"A","B","C","D","E","F","G","H"};
    Font font =  new Font("SansSerif", Font.BOLD, 20);
    JLabel LabelTurn;
    
    public LABELS(GAME sender){
        
        for(int x = 0; x < 8; x++){
            JLabel l = new JLabel();
            l.setText(buchstaben[x]);
            l.setVerticalAlignment(JLabel.CENTER);
            l.setVerticalTextPosition(JLabel.CENTER);
            l.setHorizontalTextPosition(JLabel.CENTER);
            l.setHorizontalAlignment(JLabel.CENTER);
            l.setSize(100, 20);
            l.setLocation(30 + x * 100, 10);
            l.setFont(font);
            sender.add(l);
        
        
        }
        
        for(int x = 0; x < 8; x++){
            JLabel l = new JLabel();
            l.setText(Integer.toString(x + 1));
            l.setVerticalAlignment(JLabel.CENTER);
            l.setVerticalTextPosition(JLabel.CENTER);
            l.setHorizontalTextPosition(JLabel.CENTER);
            l.setHorizontalAlignment(JLabel.CENTER);
            l.setSize(20, 100);
            l.setLocation(10, 30 + x * 100);
            l.setFont(font);
            sender.add(l);
        
        
        }
    
        LabelTurn = new JLabel();
        LabelTurn.setLocation(850, 40);
        LabelTurn.setSize(300,30);
        LabelTurn.setFont(new Font("SansSerif", Font.BOLD, 20));
        LabelTurn.setText("Hallo");
        sender.add(LabelTurn);
    
    }
    
    public void setOnTurnText(String txt){
        if(txt == FIGUR.BLACK){
            txt = "Schwarz";
        }else{
            txt = "Weiß";
        }
        
        LabelTurn.setText("An der Reihe:    " +  txt);
        LabelTurn.revalidate();
    }
    



}