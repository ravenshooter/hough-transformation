/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bilderkennung;

import bilderkennung.SpriteStore.Sprite;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Stephan
 */
public class BildDarstellungsWerkzeugGUI {
     ImageCanvas canvas;

     JPanel panel;
     
    public BildDarstellungsWerkzeugGUI() {
        panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(800, 600));
        canvas = new ImageCanvas();
        panel.add(new JScrollPane(canvas),BorderLayout.CENTER);
    }
     
    
    public void setImage(Sprite sprite){
        canvas.setNewImage(sprite);
        canvas.updateUI();
    }

    public ImageCanvas getCanvas() {
        return canvas;
    }
    
    public JPanel getMainPanel(){
        return panel;
    }
    
     
    
}
