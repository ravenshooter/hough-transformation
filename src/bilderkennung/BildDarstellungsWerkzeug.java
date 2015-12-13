/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bilderkennung;

import bilderkennung.SpriteStore.Sprite;
import javax.swing.JPanel;

/**
 *
 * @author Stephan
 */
public class BildDarstellungsWerkzeug{

    BildDarstellungsWerkzeugGUI gui;
    
    Sprite sprite;

    public BildDarstellungsWerkzeug() {
        gui = new BildDarstellungsWerkzeugGUI();
    }
    
    
    
    public JPanel getMainPanel() {
        return gui.getMainPanel();
    }
    
    public void setNewImage(Sprite sprite){
        this.sprite = sprite;
        gui.setImage(sprite);
        getMainPanel().updateUI();
        getMainPanel().revalidate();
        System.out.println(gui.getCanvas().getPreferredSize().toString());
        System.out.println(getMainPanel().getPreferredSize().toString());
    }

 
}
