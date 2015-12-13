/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bilderkennung;

import bilderkennung.SpriteStore.Sprite;
import bilderkennung.SpriteStore.SpriteFromFileLoader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

/**
 *
 * @author Stephan
 */
public class BildAuswahlWerkzeug implements BildAuswahlWerkzeugBeobachter{
    BildAuswahlWerkzeugGUI gui;
    
    ArrayList<BildAuswahlWerkzeugBeobachter>observer;

    public BildAuswahlWerkzeug(BildAuswahlWerkzeugBeobachter ... beobachter) {
        gui = new BildAuswahlWerkzeugGUI();
        observer = new ArrayList<>(2);
        setBeobachter(beobachter);
        gui.setBeobachter(this);
        
        
        
        gui.getAddOtherFile().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser();
                fc.showDialog(gui.getMainPanel(), "Load image");
                try {
                    Sprite sprite = SpriteFromFileLoader.createSprite(fc.getSelectedFile());
                    informiereUeberBildwahl(sprite);
                } catch (IOException ex) {
                    gui.showErrorMessage(ex);
                }
            }
        });
    }
    
    
    public final void setBeobachter(BildAuswahlWerkzeugBeobachter ... beobachter){
        observer.addAll(Arrays.asList(beobachter));
    }
    
    
    public JPanel getMainPanel(){
        return gui.getMainPanel();
    }

    @Override
    public void informiereUeberBildwahl(Sprite sprite) {
        for(BildAuswahlWerkzeugBeobachter b : observer){
            b.informiereUeberBildwahl(sprite);
        }
    }
    
    
    
}
