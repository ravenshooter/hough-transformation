package bilderkennung.SpriteStore;


import java.awt.image.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 *
 * @author ausfragezeichen
 */
public class SpriteStore {

    private static SpriteStore instance = new SpriteStore();
    
    private GraphicsConfiguration gc;
  
    private HashMap sprites;

    /**
     * Constructor for objects of class SpriteStore
     */
    private SpriteStore() {
        sprites = new HashMap();
        gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        
    }

    public boolean loadSprite(String ref, boolean autoTransparency) {
        BufferedImage sourceImage = null;
        try {
            
            sourceImage = ImageIO.read(getClass().getResource(ref));  
        } catch (IOException ex) {
            System.err.println("could not load image " + ref);
        }
        if (sourceImage == null) {
            return false;
        }
        
        BufferedImage copyImage = gc.createCompatibleImage(sourceImage.getWidth(), sourceImage.getHeight(), BufferedImage.TYPE_INT_ARGB);

        //takes color of upper left corner(0,0) to use this later as transparency
        Color c = new Color(sourceImage.getRGB(0, 0));
        
        Graphics2D g2d = copyImage.createGraphics();
        
        //image transformation adds transparency
        
        if(autoTransparency){
            Image transparent = makeColorTransparent(sourceImage, c);
            g2d.drawImage(transparent, null, null);
        }else{
            g2d.drawImage(sourceImage, null, null);
        }
        
        //try custom filters
        //BufferedImageOp op = new BufferedImageOp();
        //g2d.drawImage(sourceImage, op, 0, 0);
        
        g2d.dispose();
        Sprite sprite = new Sprite(copyImage);
        sprites.put(ref, sprite);
        return true;
    }
    

    protected void addSprite(Sprite sprite){
        
    }

    private Image makeColorTransparent(BufferedImage im, final Color color) {
        ImageFilter filter = new RGBImageFilter() {
            public int markerRGB = color.getRGB() | 0xFF000000;

            @Override
            public final int filterRGB(int x, int y, int rgb) {
                if ((rgb | 0xFF000000) == markerRGB) {
                    return 0x00FFFFFF & rgb;
                } else {
                    return rgb;
                }
            }
        };

        ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
        return Toolkit.getDefaultToolkit().createImage(ip);
    }

    public Sprite getSprite(String ref, boolean autoTransparency) {
        Sprite sprite = (Sprite) sprites.get(ref);
        if (sprite == null) {
            if (loadSprite(ref, autoTransparency)) {
                return (Sprite) sprites.get(ref);
            }
        }
        return sprite;
    }
    
    
    
    
    
    public static SpriteStore get() {
        return instance;
    }
    
}