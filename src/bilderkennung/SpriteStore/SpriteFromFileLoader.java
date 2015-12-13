/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bilderkennung.SpriteStore;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Stephan
 */
public class SpriteFromFileLoader {

    public static Sprite createSprite(File file) throws IOException {
        BufferedImage image = ImageIO.read(file);
        return new Sprite(image);


    }
}
