/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bilderkennung;

import bilderkennung.SpriteStore.Sprite;
import bilderkennung.SpriteStore.SpriteStore;
import java.util.ArrayList;

/**
 *
 * @author Stephan
 */
public class Bilderkennung {

    public static final String pic1 = "/img/test.jpg";
    public static final String pic2 = "/img/test2.jpg";
    public static final String pic3 = "/img/test3.png";
    public static final String pic4 = "/img/test7.png";
    public static final String pic5 = "/img/test haus.png";
    public static final String pic6 = "/img/test5.png";
    public static final String pic7 = "/img/Desert.jpg";
    public static final String pic8 = "/img/UBahn.jpg";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Window window= new Window();
        
        
        BildDarstellungsWerkzeug bildDarstellungsWerkzeug = new BildDarstellungsWerkzeug();
        BildBearbeitungsWerkzeug bildBearbeitungsWerkzeug = new BildBearbeitungsWerkzeug(bildDarstellungsWerkzeug);
        BildAuswahlWerkzeug bildAuswahlwerkzeug = new BildAuswahlWerkzeug(window, bildBearbeitungsWerkzeug);
        
        
        window.addBildAuswahlWerkzeug(bildAuswahlwerkzeug);
        window.addBildBearbeitungsWerkzeug(bildBearbeitungsWerkzeug);
        window.addBilddarstellungsWerkzeug(bildDarstellungsWerkzeug);
        
        
        window.repaint();
        
        
    }
    
    
    public static ArrayList<String> getImages(){
        ArrayList<String> sprites = new ArrayList();
        
        sprites.add(pic1);
        sprites.add(pic2);
        sprites.add(pic3);
        sprites.add(pic4);
        sprites.add(pic5);
        sprites.add(pic6);
        sprites.add(pic7);
        sprites.add(pic8);
        
        return sprites;
    }
}
