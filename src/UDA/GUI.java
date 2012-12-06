

package UDA;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.*;

/*
 * @author elfinkind
 */
public class GUI extends JFrame {
    
    JPanel mainPanel, panel;
    JButton north, east, west, south, northEast, northWest, southEast, southWest;
    JTextArea center;
    
    String path;
    
    public GUI() {
        
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.blue);
        
        northWest = new JButton(new ImageIcon("Images/NorthWest1.png"));
        northWest.setBackground(Color.blue);
        northWest.setBorder(null);
        north = new JButton(new ImageIcon("Images/North1.png"));
        north.setBackground(Color.blue);
        north.setBorder(null);
        northEast = new JButton(new ImageIcon("Images/NorthEast1.png"));
        northEast.setBackground(Color.blue);
        northEast.setBorder(null);
        west = new JButton(new ImageIcon("Images/West.png"));
        west.setBackground(Color.blue);
        west.setBorder(null);
        east = new JButton(new ImageIcon("Images/East1.png"));
        east.setBackground(Color.blue);
        east.setBorder(null);
        southWest = new JButton(new ImageIcon("Images/SouthWest1.png"));
        southWest.setBackground(Color.blue);
        southWest.setBorder(null);
        south = new JButton(new ImageIcon("Images/South1.png"));
        south.setBackground(Color.blue);
        south.setBorder(null);
        southEast = new JButton(new ImageIcon("Images/SouthEast1.png"));
        southEast.setBackground(Color.blue);
        southEast.setBorder(null);
        
        center = new JTextArea();
        center.setSize(150, 150);
        center.setBackground(Color.WHITE);
        
        //Box textBox = new 
        
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setVisible(true);
        
        addGridBagItem(mainPanel, northWest, 0, 0, 1, 1, GridBagConstraints.NORTHWEST);
        addGridBagItem(mainPanel, north, 1, 0, 1, 1, GridBagConstraints.NORTH);
        addGridBagItem(mainPanel, northEast, 2, 0, 1, 1, GridBagConstraints.NORTHEAST);
        addGridBagItem(mainPanel, west, 0, 1, 1, 1, GridBagConstraints.WEST);
        addGridBagItem(mainPanel, center, 1, 1, 1, 1, GridBagConstraints.CENTER);
        addGridBagItem(mainPanel, east, 2, 1, 1, 1, GridBagConstraints.EAST);
        addGridBagItem(mainPanel, southWest, 0, 2, 1, 1, GridBagConstraints.SOUTHWEST);
        addGridBagItem(mainPanel, south, 1, 2, 1, 1, GridBagConstraints.SOUTH);
        addGridBagItem(mainPanel, southEast, 2, 2, 1, 1, GridBagConstraints.SOUTHEAST);
        
        add(mainPanel);
        
        
    }
    
    private void addGridBagItem (JPanel p, JComponent c, int x, int y, int width, int height, int align) {
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        gbc.weightx = 200.0;
        gbc.weighty = 200.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = align;
        gbc.fill = GridBagConstraints.NONE;
        p.add(c, gbc);
        
    }
    
}
