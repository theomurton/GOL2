import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class LoadGameGUI {
    private int count;
    private int[] parameters = new int[] {4,4,1,1,1};
    private Game game;
    public LoadGameGUI(Game game) {
        

        JFrame loadGameFrame = new JFrame("Game of Life - Load save");
        loadGameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel topPane = new JPanel();
        JPanel bottomPane = new JPanel();
        BorderLayout layout = new BorderLayout();
        loadGameFrame.setSize(300,300);
        this.game = game;
        File folder = new File(".");
        ArrayList<String> saves = new ArrayList<String>();
        Pattern pattern = Pattern.compile("(.*\\.class|.*\\.java|.*\\.git)");
        for(String file : folder.list()) {
            Matcher matcher = pattern.matcher(file);
            if(!matcher.matches()) {
                saves.add(file);
            }
        }
        String[] savesArray = new String[saves.size()];
        saves.toArray(savesArray);

        JComboBox<String> savesBox = new JComboBox<String>(savesArray);
        JButton savesButton = new JButton("Load Save");
        savesButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                String file = savesBox.getSelectedItem().toString();
                loadGameFrame.dispose();
                game.setFilename(file);
                game.setReady(true);
                
                } catch (Exception exce) {
                    System.exit(0);
                }
            }
        });
        topPane.add(savesBox);
        bottomPane.add(savesButton);
        loadGameFrame.add(BorderLayout.NORTH, topPane);
        loadGameFrame.add(BorderLayout.SOUTH, bottomPane);
        loadGameFrame.setVisible(true);
        
    
    }
    public void load(String file) throws Exception{
        this.game.loadGame(file);
    }
}