/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bilderkennung;

import bilderkennung.SpriteStore.Sprite;
import bilderkennung.SpriteStore.SpriteStore;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Stephan
 */
public class Window implements BildAuswahlWerkzeugBeobachter{
    
    

    
    JFrame frame;
    
    CardLayout layout;

    JPanel bildAuswahlPanel;
    JPanel bildBearbeitungsPanel;
    
    

    public Window() {
     
        
        frame = new JFrame();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800,600));
        
        bildBearbeitungsPanel = new JPanel();
        bildBearbeitungsPanel.setLayout(new BorderLayout(10, 10));
        
        bildAuswahlPanel = new JPanel();
        
        layout = new CardLayout();
        frame.getContentPane().setLayout(layout);
        frame.getContentPane().add(bildBearbeitungsPanel, "bearbeitung");
        frame.getContentPane().add(bildAuswahlPanel, "auswahl");
        
        layout.show(frame.getContentPane(), "auswahl");
        
        frame.pack();
        frame.setVisible(true);
        
    
    }
    
    
    public void addBilddarstellungsWerkzeug(BildDarstellungsWerkzeug werkzeug){
        bildBearbeitungsPanel.add(werkzeug.getMainPanel(),BorderLayout.CENTER);
    }
    
    public void addBildBearbeitungsWerkzeug(BildBearbeitungsWerkzeug werkzeug){
        bildBearbeitungsPanel.add(werkzeug.getMainPanel(),BorderLayout.SOUTH);
    }

    void addBildAuswahlWerkzeug(BildAuswahlWerkzeug bildAuswahlwerkzeug) {
        bildAuswahlPanel.add(bildAuswahlwerkzeug.getMainPanel());
    }

    @Override
    public void informiereUeberBildwahl(Sprite sprite) {
        layout.show(frame.getContentPane(), "bearbeitung");
    }
    
    public void repaint(){
        frame.revalidate();
        
    }
    
}
