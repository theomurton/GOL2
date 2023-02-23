import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class TheoGUI{
    private JFrame frame;
    private BorderLayout borderLayout;
    private JPanel topPanel;
    private GridLayout layout;
    private JPanel panel;
    private Board board;
    private JButton[][] buttons;
    private JButton pauseButton; 
    private JButton saveButton;
    private JButton stepButton;
    private JButton speedUpButton;
    private JButton slowDownButton;
    private JButton resetButton;
    private boolean pauseState = false;
    private Game game;
    public TheoGUI(int width, int height, Board board, Game game){
        this.game = game;
        this.board = board;
        panel = new JPanel();
        frame = new JFrame();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setTitle("Game of Life");
        Dimension dimensions = new Dimension(screen.width, screen.height - frame.getInsets().top);
        frame.setSize(dimensions);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        this.buttons = new JButton[width][height];
        frame.setResizable(false);
        layout = new GridLayout(width, height);
        panel.setLayout(layout);
        frame.add(panel);
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                final int x = i;
                final int y = j;
                buttons[i][j] = new JButton();
                buttons[i][j].setBackground(Color.BLACK);
                buttons[i][j].addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        swapBoxColour(x, y);
                        board.swapIndex(x, y);
                    }
                });
                panel.add(buttons[i][j]);
            }
        }
        topPanel = new JPanel();

        pauseButton = new JButton("Play");

        pauseButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                swapPauseState();
            }
        });
        
        
        saveButton = new JButton("Save");
        //saveButton.addActionListener(this); 
     
        stepButton = new JButton("Step");
        stepButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                step();
            }
        });
        resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                reset();
            }
        });
        speedUpButton = new JButton("Speed up");
        //speedUpButton.addActionListener(this); 

        slowDownButton = new JButton("Slow down");
        //slowDownButton.addActionListener(this);

        topPanel.add(pauseButton);
        topPanel.add(saveButton);
        topPanel.add(stepButton);
        topPanel.add(resetButton);
    
        frame.add(BorderLayout.NORTH, topPanel);
        //frame.add(panel);
        panel.setVisible(true);
        frame.setVisible(true);
    }
    public void swapPauseState(){
        if (this.pauseState){
            this.pauseButton.setText("Play");
            this.saveButton.setVisible(true);
        } else {
            this.pauseButton.setText("Pause");
            this.saveButton.setVisible(false);
        }
        if (this.pauseState){
            this.pauseState = false;
        } else {
            this.pauseState = true;
        }
        this.game.swapPaused();
        
    }
    public void step(){
        this.game.requestStep();
    }
    public void reset(){
        this.game.reset();
    }
    public void swapBoxColour(int y, int x){
        System.out.println("changed" + x + " " + y);
        boolean on = this.board.getIndex(y, x);
        if (on){
            this.buttons[y][x].setBackground(Color.BLACK);
        } else {
            this.buttons[y][x].setBackground(Color.WHITE);
        }
    }
    public void setBoxBlack(int y, int x){
        this.buttons[y][x].setBackground(Color.BLACK);
    }
    
}
