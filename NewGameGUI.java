import javax.swing.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
public class NewGameGUI {
    private int count;
    private int[] parameters = new int[] {4,4,1,1,1};
    private Game game;
    public NewGameGUI(Game game) {
        this.game = game;
        JFrame setup2Frame = new JFrame("Game of Life - Setup");
        setup2Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SpringLayout layout = new SpringLayout();
        
        setup2Frame.setSize(300,300);
        JPanel contentPane = new JPanel();

        contentPane.setLayout(layout);

        
        JLabel heightJLabel = new JLabel("Select game height:");
        JLabel widthJLabel = new JLabel("Select game width:");
        JLabel xLabel = new JLabel("Set x");
        JLabel yLabel = new JLabel("Set y");
        JLabel zLabel = new JLabel("Set z");
        String[] intChoices = new String[9];
        for(int i = 1; i <= 9; i++) {
            intChoices[i - 1] = Integer.toString(i);
        }
        String[] sizeChoices = new String[47];
        for(int i = 4; i <= 50; i++) {
            sizeChoices[i - 4] = Integer.toString(i);
        }
        JComboBox<String> heightBox = new JComboBox<String>(sizeChoices);
        JComboBox<String> widthBox = new JComboBox<String>(sizeChoices);
        JComboBox<String> xBox = new JComboBox<String>(intChoices);
        JComboBox<String> yBox = new JComboBox<String>(intChoices);
        JComboBox<String> zBox = new JComboBox<String>(intChoices);
        heightBox.addItemListener( new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                parameters[0] = Integer.parseInt((String)heightBox.getSelectedItem());
            }
        });
        
        widthBox.addItemListener( new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                parameters[1] = Integer.parseInt((String)widthBox.getSelectedItem());
            }
        });
        xBox.addItemListener( new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                parameters[2] = Integer.parseInt((String)xBox.getSelectedItem());
            }
        });
        xBox.setSelectedIndex(1);
        yBox.addItemListener( new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                parameters[3] = Integer.parseInt((String)yBox.getSelectedItem());
            }
        });
        yBox.setSelectedIndex(2);
        zBox.addItemListener( new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                parameters[4] = Integer.parseInt((String)zBox.getSelectedItem());
            }
        });
        zBox.setSelectedIndex(2);



        contentPane.add(heightJLabel);
        contentPane.add(heightBox);
        contentPane.add(widthJLabel);
        contentPane.add(widthBox);
        contentPane.add(xLabel);
        contentPane.add(xBox);
        contentPane.add(yLabel);
        contentPane.add(yBox);
        contentPane.add(zLabel);
        contentPane.add(zBox);

        layout.putConstraint(SpringLayout.WEST, heightJLabel,5, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, heightJLabel,10,SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.WEST, heightBox, 6, SpringLayout.EAST, heightJLabel);
        layout.putConstraint(SpringLayout.NORTH, heightBox,6,SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.WEST, widthJLabel,5, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, widthJLabel,20,SpringLayout.SOUTH, heightJLabel);

        layout.putConstraint(SpringLayout.WEST, widthBox, 6, SpringLayout.EAST, widthJLabel);
        layout.putConstraint(SpringLayout.NORTH, widthBox, -4, SpringLayout.NORTH, widthJLabel);

        layout.putConstraint(SpringLayout.WEST, xLabel, 6, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, xLabel, 20, SpringLayout.SOUTH, widthJLabel);
        
        layout.putConstraint(SpringLayout.WEST, xBox, 6, SpringLayout.EAST, xLabel);
        layout.putConstraint(SpringLayout.NORTH, xBox, -4, SpringLayout.NORTH, xLabel);

        layout.putConstraint(SpringLayout.WEST, yLabel, 6, SpringLayout.EAST, xBox);
        layout.putConstraint(SpringLayout.NORTH, yLabel, 20, SpringLayout.SOUTH, widthJLabel);

        layout.putConstraint(SpringLayout.WEST, yBox, 6, SpringLayout.EAST, yLabel);
        layout.putConstraint(SpringLayout.NORTH, yBox, -4, SpringLayout.NORTH, yLabel);

        layout.putConstraint(SpringLayout.WEST, zLabel, 6, SpringLayout.EAST, yBox);
        layout.putConstraint(SpringLayout.NORTH, zLabel, 20, SpringLayout.SOUTH, widthJLabel);

        layout.putConstraint(SpringLayout.WEST, zBox, 6, SpringLayout.EAST, zLabel);
        layout.putConstraint(SpringLayout.NORTH, zBox, -4, SpringLayout.NORTH, zLabel);

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try{
                        setup2Frame.dispose();
                    game.setParameters(parameters);
                    game.setReady(true);}
                    catch (Exception exce){
                        System.exit(0);
                    }
                }
            }
        );
        contentPane.add(confirmButton);

        layout.putConstraint(SpringLayout.NORTH, confirmButton, 20, SpringLayout.SOUTH, xBox);
        layout.putConstraint(SpringLayout.WEST, confirmButton, 40, SpringLayout.SOUTH, xBox);
        setup2Frame.add(contentPane);
        setup2Frame.setVisible(true);
    
    }
    public int[] getParameters() {
        return this.parameters;
    }
}