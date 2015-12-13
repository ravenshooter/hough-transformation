/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bilderkennung.materialien;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Stephan
 */
public class Gerade {
    int alpha;
    int d;

    public Gerade(int alpha, int d) {
        this.alpha = alpha;
        this.d = d;
    }
    
    
    public void draw(Graphics g, int width, int height){
        int xAnfang = -width/2;
        int xEnd = width/2;
        if(alpha == 0){
            alpha++;
        }
        int yStart = (int)((d-Math.cos(alpha*(Math.PI/(double)180))*xAnfang)/Math.sin(alpha*(Math.PI/(double)180)));
        int yEnd = (int)((d-Math.cos(alpha*(Math.PI/(double)180))*xEnd)/Math.sin(alpha*(Math.PI/(double)180)));
        
        
        double steigung = ((double)yEnd-(double)yStart)/((double)xAnfang-(double)xEnd);
        System.out.println("steigung:"+steigung);
        System.out.println("height/2:" +(height/2)+"    width/2: "+(width/2));
        g.setColor(Color.RED);
        g.drawLine(xAnfang-xAnfang, yStart+height/2, xEnd+xEnd, yEnd+height/2);
        System.out.println("("+xAnfang +"|"+ yStart +")("+ xEnd +"|"+ yEnd+")");
    }
    
}
