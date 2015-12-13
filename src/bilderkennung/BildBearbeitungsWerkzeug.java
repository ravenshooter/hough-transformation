/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bilderkennung;

import bilderkennung.materialien.HoughSpace;
import bilderkennung.SpriteStore.Sprite;
import bilderkennung.SpriteStore.SpriteStore;
import bilderkennung.materialien.Gerade;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Stephan
 */
public class BildBearbeitungsWerkzeug implements BildAuswahlWerkzeugBeobachter{
    
    public static final int edgeFilter = 30;
    
    Sprite sprite;
    Sprite farbAenderungsSprite;
    
    BildDarstellungsWerkzeug darstellung;
    EckenErkennungsWerkzeug eckenErkennungsWerkzeug;
    BildAuswahlWerkzeug bildAuswahlWerkzeug;
    
    BildBearbeitungsWerkzeugGUI gui;
    
    
    HoughSpace houghSpace;

    public BildBearbeitungsWerkzeug(BildDarstellungsWerkzeug darstellung) {
        
        this.darstellung = darstellung;
        
        eckenErkennungsWerkzeug = new EckenErkennungsWerkzeug();

        bildAuswahlWerkzeug = new BildAuswahlWerkzeug();
        
        
        gui = new BildBearbeitungsWerkzeugGUI();
        gui.getErkenneFarbaenderungButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                farbAenderungsSprite = eckenErkennungsWerkzeug.performFarbaenderungsErkennungRGB(sprite);
                //filterSprite(farbAenderungsSprite);
                updateImageCanvas(farbAenderungsSprite);
            }
        });
        
        gui.getFilterEdgeButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                farbAenderungsSprite = eckenErkennungsWerkzeug.filterSprite(farbAenderungsSprite);
                updateImageCanvas(farbAenderungsSprite);
            }
        });
        
        
        gui.getCreateHoughSpaceButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GeradenErkennungsWerkzeug werkzeug = new GeradenErkennungsWerkzeug();
                houghSpace = werkzeug.calcHoughSpace(farbAenderungsSprite);
                updateImageCanvas(werkzeug.drawHoughSpace(houghSpace));
                
            }
        });
        
        gui.getRecreateLinesButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Gerade> geraden = houghSpace.returnMaxima();
                BufferedImage image = new BufferedImage(sprite.getWidth(), sprite.getHeight(), BufferedImage.TYPE_INT_ARGB);
                image.getGraphics().drawImage(sprite.getImage(), 0, 0, null);
                for(Gerade g : geraden){
                    g.draw(image.getGraphics(),image.getWidth(),image.getHeight());
                }
                updateImageCanvas(new Sprite(image));
            }
        });
    }
    
    
    
    
    
    
    
    private void updateImageCanvas(Sprite sprite){
        darstellung.setNewImage(sprite);
    }
    
    
    public JPanel getMainPanel(){
        return gui.getBearbeitungsPanel();
    }
    
    public void setImage(Sprite sprite){
        this.sprite = sprite;
        updateImageCanvas(sprite);
    }
    
    public void loadImage(String path){
        sprite = SpriteStore.get().getSprite(path, false);
        darstellung.setNewImage(sprite);
        
    }

    @Override
    public void informiereUeberBildwahl(Sprite sprite) {
        setImage(sprite);
    }
}
