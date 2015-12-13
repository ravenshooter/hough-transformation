/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bilderkennung;

import bilderkennung.SpriteStore.Sprite;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Stephan
 */
public class ImageCanvas extends JPanel{
    
    Sprite sprite;

    public ImageCanvas(Sprite sprite) {
        this.sprite = sprite;
        this.setPreferredSize(new Dimension(sprite.getWidth(), sprite.getHeight()));
    }
    
    public ImageCanvas(){
        
    }
    
    
    @Override
    public void paintComponent(Graphics g){
        if(sprite != null){
            g.drawImage(sprite.getImage(), 0, 0, this);
        }
    }
    
    public void setNewImage(Sprite sprite){
        this.sprite = sprite;
        this.setPreferredSize(new Dimension(sprite.getWidth(), sprite.getHeight()));
        

    }
    
    
}
