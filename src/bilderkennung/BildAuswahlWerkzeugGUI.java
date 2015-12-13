/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bilderkennung;

import bilderkennung.SpriteStore.Sprite;
import bilderkennung.SpriteStore.SpriteStore;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Stephan
 */
public class BildAuswahlWerkzeugGUI {
    JPanel mainPanel;
    JPanel imagePanel;
    BildAuswahlWerkzeugBeobachter[] observer;
    
    JButton addOtherFile;

    public BildAuswahlWerkzeugGUI() {
        mainPanel = new JPanel(new BorderLayout(20,20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        
        JLabel header = new JLabel("Choose image:");
        header.setFont(new Font("ARIAL", Font.BOLD, 32));
        header.setAlignmentY(JLabel.CENTER_ALIGNMENT);
        mainPanel.add(header,BorderLayout.NORTH);
        
        imagePanel = new JPanel();
        ArrayList<String> sprites = Bilderkennung.getImages();
        
        int gridWidth = sprites.size()/2+1;
        
        imagePanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        imagePanel.setLayout(new GridLayout(2, gridWidth,10,10));
        
        for(String s: sprites){
            JButton button = new JButton(new ImageIcon(resize(SpriteStore.get().getSprite(s, false).getImage(),100,100)));
            button.addActionListener(new BildButtonActionListener(s));
            imagePanel.add(button);
        }
        
        mainPanel.add(imagePanel, BorderLayout.CENTER);
        
        addOtherFile = new JButton("Load another file");
        mainPanel.add(addOtherFile,BorderLayout.SOUTH);
        
    }
    
    
    public void setBeobachter(BildAuswahlWerkzeugBeobachter ... b){
       if(observer == null){
           observer = b;
       } else {
           BildAuswahlWerkzeugBeobachter[] array = new BildAuswahlWerkzeugBeobachter[observer.length+b.length];
           for(int i = 0; i < observer.length;i++){
               array[i] = observer[i];
           }
           for(int i = 0; i < b.length;i++){
               array[i+observer.length-1] = b[i];
           }
           observer = array;
       }
       
    }
    
    public void notifyObserver(String s){
        if(observer != null){
            Sprite sprite = SpriteStore.get().getSprite(s, false);
            for(BildAuswahlWerkzeugBeobachter b : observer){
                
                b.informiereUeberBildwahl(sprite);
            }
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    
    public void showErrorMessage(Exception ex){
        JOptionPane.showMessageDialog(mainPanel, ex.getMessage(), "An error occured", JOptionPane.ERROR_MESSAGE);
    }
    
    public static BufferedImage resize(BufferedImage image, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return bi;
}

    public JButton getAddOtherFile() {
        return addOtherFile;
    }
    
    
    
    
    class BildButtonActionListener implements ActionListener {

        String name;

        public BildButtonActionListener(String name) {
            this.name = name;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            notifyObserver(name);
        }
    }
}
