/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bilderkennung;

import static bilderkennung.BildBearbeitungsWerkzeug.edgeFilter;
import bilderkennung.SpriteStore.Sprite;
import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author Stephan
 */
public class EckenErkennungsWerkzeug {
    
    
    public Sprite performFarbaenderungsErkennungRGB(Sprite sprite){
        BufferedImage returnImage = new BufferedImage(sprite.getWidth(),sprite.getHeight(),BufferedImage.TYPE_INT_ARGB);
        for(int y = 1; y < sprite.getHeight()-1;y++){
            for(int x = 1; x < sprite.getWidth()-1;x++){
                Color ownColor = new Color(sprite.getImage().getRGB(x, y));
                
                //calc overal difference
                int totalColorDiff = 0;
                totalColorDiff += getColorDiff(x, y-1, sprite.getImage(), ownColor);
                totalColorDiff += getColorDiff(x-1, y, sprite.getImage(), ownColor);
                totalColorDiff = totalColorDiff/2;
                
                //recalc in 0-255
                int newRGB = totalColorDiff/3;
                
                Color returnColor = new Color(newRGB,newRGB,newRGB);
                
                returnImage.setRGB(x, y, returnColor.getRGB());
                
                
            }
        }
        return new Sprite(returnImage);
    }
    
    
    
    public Sprite performFarbaenderungsErkennungContrast(Sprite sprite){
        BufferedImage returnImage = new BufferedImage(sprite.getWidth(),sprite.getHeight(),BufferedImage.TYPE_INT_ARGB);
        for(int y = 1; y < sprite.getHeight()-1;y++){
            for(int x = 1; x < sprite.getWidth()-1;x++){
                Color ownColor = new Color(sprite.getImage().getRGB(x, y));
                //calc overal difference
                int totalColorDiff = 0;
                totalColorDiff += getColorDiff(x, y-1, sprite.getImage(), ownColor);
                totalColorDiff += getColorDiff(x-1, y, sprite.getImage(), ownColor);
                totalColorDiff = totalColorDiff/2;
                
                //recalc in 0-255
                int newRGB = totalColorDiff/3;
                
                Color returnColor = new Color(newRGB,newRGB,newRGB);
                
                returnImage.setRGB(x, y, returnColor.getRGB());
                
                
            }
        }
        return new Sprite(returnImage);
    }
    
    /**
     * Sets color depending on value of edgeFilter either to white or to black.
     * @param sprite 
     */
    public Sprite filterSprite(Sprite sprite){
        BufferedImage image = sprite.getImage();
        for(int y = 1; y < sprite.getHeight()-1;y++){
            for(int x = 1; x < sprite.getWidth()-1;x++){
                if(new Color(image.getRGB(x, y)).getRed() > edgeFilter){
                    image.setRGB(x, y, new Color(0,0,0).getRGB());
                }else{
                    image.setRGB(x, y, new Color(255,255,255).getRGB());
                }
            }
        }
        return sprite;
    }
    
    
    private int getColorDiff(int x, int y, BufferedImage image, Color ownColor) {
        Color compareColor = new Color(image.getRGB(x, y));
        int rDiff = Math.abs(ownColor.getRed() - compareColor.getRed());
        int gDiff = Math.abs(ownColor.getGreen() - compareColor.getGreen());
        int bDiff = Math.abs(ownColor.getBlue() - compareColor.getBlue());
        
        return rDiff + gDiff + bDiff;
    }

}
