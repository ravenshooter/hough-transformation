/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bilderkennung;

import bilderkennung.materialien.HoughSpace;
import static bilderkennung.BildBearbeitungsWerkzeug.edgeFilter;
import bilderkennung.SpriteStore.Sprite;
import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author Stephan
 */
public class GeradenErkennungsWerkzeug {
    
    public HoughSpace calcHoughSpace(Sprite sprite){
        long start = System.currentTimeMillis();
        
        BufferedImage image = sprite.getImage();
        
        int maxDis = (int)Math.sqrt(Math.pow(sprite.getWidth()/2,2)+Math.pow(sprite.getHeight()/2, 2));

        int xOffset = image.getWidth()/2;
        int yOffset = image.getHeight()/2;
        
        System.out.println("maxDis:"+maxDis);
        int[][] houghSpace = new int[maxDis*2][180];
        
        int maxD = 0;
        int minD = 0;
        
        double PiUmrechnungsfaktor = Math.PI/180;
        for(int y = 1; y < sprite.getHeight()-1;y++){
            for(int x = 1; x < sprite.getWidth()-1;x++){
                if(new Color(image.getRGB(x, y)).getRed()==0){
                    for(int i = 0; i < 180;i++){
                        int d = (int)(Math.cos(i*PiUmrechnungsfaktor)*(x-xOffset)+Math.sin(i*PiUmrechnungsfaktor)*(y-yOffset));
                        houghSpace[d+maxDis][i]++;
                        if(d> maxD){
                            maxD = d;
                        }
                        if(d< minD){
                            minD = d;
                        }
                    }
                }
            }
        }
        
        long end = System.currentTimeMillis();
        
        System.out.println("maxD: " +maxD);
        System.out.println("minD: " +minD);
        
        System.out.println("Hough Time: " + (end-start) +"ms");
                
        return new HoughSpace(houghSpace);
    }
    
    public Sprite drawHoughSpace(HoughSpace houghSpace){
        BufferedImage image = new BufferedImage(houghSpace.getMaxD()*2, houghSpace.getMaxAlpha(), BufferedImage.TYPE_INT_ARGB);
        
        int maxValue = houghSpace.getMaxValue();
        
        //put values to array
        int[] values = new int[maxValue+1];
        for(int d = -houghSpace.getMaxD(); d < houghSpace.getMaxD();d++){
            for(int alpha = 0; alpha < houghSpace.getMaxAlpha();alpha++){
                values[houghSpace.getValue(d, alpha)]++;
            }
        }
        for(int i = 0; i < values.length; i++){
            //System.out.println(i+"  " + values[i]);
        }
        
        double factor = (double)255/(double)maxValue;
        System.out.println("factor " +factor);
        
        for(int d = -houghSpace.getMaxD(); d < houghSpace.getMaxD();d++){
            for(int alpha = 0; alpha < houghSpace.getMaxAlpha();alpha++){
                
//                nur schwarz weiß
                
//                int colorValue = (int)(houghSpace[d][alpha]*factor);
//                if(colorValue > 150){
//                    image.setRGB(d,alpha, new Color(255,255,255).getRGB());
//                    image.setRGB(d-1,alpha, new Color(255,255,255).getRGB());
//                    image.setRGB(d,alpha-1, new Color(255,255,255).getRGB());
//                    image.setRGB(d-1,alpha-1, new Color(255,255,255).getRGB());
//                    System.out.println("gerade: "+alpha+"° "+d);
//                }else{
//                    image.setRGB(d,alpha, new Color(0,0,0).getRGB());
//                }
                
                
                int colorValue = (int)(houghSpace.getValue(d, alpha)*factor);
                image.setRGB(d+houghSpace.getMaxD(),alpha, new Color(colorValue,colorValue,colorValue).getRGB());
                
                
            }
        }
        
        
        //draw Grid
        image.getGraphics().setColor(Color.GRAY);
        image.getGraphics().drawLine(houghSpace.getMaxD(), 0, houghSpace.getMaxD(), 400);
        for(int i = 0; i <= 180;i++){
            image.getGraphics().drawLine(0, i*20, houghSpace.getMaxD()*2, i*20);
        }
        
        
        return new Sprite(image);
    }
    
    
    
    
    public Sprite reconstructLines(int[][] houghSpace){
        
        
        
        return null;
    }
}
