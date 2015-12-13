/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bilderkennung;

import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Stephan
 */
public class BildBearbeitungsWerkzeugGUI {
    JPanel bearbeitungsPanel;

    JButton erkenneFarbaenderungButton;
    JButton filterEdgeButton;
    JButton createHoughSpaceButton;
    JButton recreateLinesButton;
    
    public BildBearbeitungsWerkzeugGUI() {
        bearbeitungsPanel = new JPanel();
        
        erkenneFarbaenderungButton = new JButton("Farberkennung");
        filterEdgeButton = new JButton("Filter Edges");
        createHoughSpaceButton = new JButton("Create HoughSpace");
        recreateLinesButton = new JButton("Recreate Lines");
        
        bearbeitungsPanel.add(erkenneFarbaenderungButton);
        bearbeitungsPanel.add(filterEdgeButton);
        bearbeitungsPanel.add(createHoughSpaceButton);
        bearbeitungsPanel.add(recreateLinesButton);
        
    }

    public JButton getErkenneFarbaenderungButton() {
        return erkenneFarbaenderungButton;
    }

    public JPanel getBearbeitungsPanel() {
        return bearbeitungsPanel;
    }

    public JButton getFilterEdgeButton() {
        return filterEdgeButton;
    }

    public JButton getCreateHoughSpaceButton() {
        return createHoughSpaceButton;
    }

    public JButton getRecreateLinesButton() {
        return recreateLinesButton;
    }
    
     

    
    
    
    
    
}
