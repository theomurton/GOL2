import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class TheoGUI implements ChangeListener {
    private JFrame frame;
    private BorderLayout borderLayout;
    private JPanel topPanel;
    private GridLayout layout;
    private JPanel panel;
    private Board board;
    private JButton[][] buttons;
    protected PauseButton pauseButton;
    protected SaveButton saveButton;
    protected JButton stepButton;
    protected JButton randomButton;
    protected JSlider slider;
    private JButton resetButton;
    private boolean pauseState = false;
    protected JTextField input;
    private Game game;

    public TheoGUI(int width, int height, Board board, Game game) {
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
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                final int x = i;
                final int y = j;
                buttons[i][j] = new JButton();
                buttons[i][j].setBackground(Color.BLACK);
                buttons[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        swapBoxColour(x, y);
                        board.swapIndex(x, y);
                        board.swapSet(y, x);
                    }
                });
                panel.add(buttons[i][j]);
            }
        }
        topPanel = new JPanel();
        pauseButton = new PauseButton("Play", this.game, this);
        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (!game.getPauseState()) {
                    pauseButton.setText("Play");
                    saveButton.setVisible(true);
                    stepButton.setVisible(true);
                } else {
                    Thread pauseThread = new Thread(pauseButton);
                    pauseButton.setText("Pause");
                    saveButton.setVisible(false);
                    stepButton.setVisible(false);
                    pauseThread.start();
                }
                game.swapPaused();
            }
        });
        randomButton = new JButton("Randomise");
        randomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                random();
            }
        });
        saveButton = new SaveButton("Save", this.game, this);
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Thread saveThread = new Thread(saveButton);
                    saveThread.start();
                } catch (Exception ere) {

                }
            }
        });

        stepButton = new JButton("Step");
        stepButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    step();
                } catch (Exception l) {

                }

            }
        });
        resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        slider = new JSlider(3, 1000, 23);
        slider.addChangeListener(this);
        input = new JTextField("Enter save name",20);
        topPanel.add(slider);
        topPanel.add(pauseButton);
        topPanel.add(saveButton);
        topPanel.add(stepButton);
        topPanel.add(resetButton);
        topPanel.add(randomButton);
        topPanel.add(input);

        frame.add(BorderLayout.NORTH, topPanel);
        // frame.add(panel);
        panel.setVisible(true);
        frame.setVisible(true);
    }

    public void stateChanged(ChangeEvent e) {
        this.game.setDelay(this.slider.getValue());
    }

    public void step() throws Exception {
        this.game.requestStep();
    }

    public void reset() {
        this.game.reset();
    }

    public void swapBoxColour(int y, int x) {
        boolean on = (this.board.getIndex(y, x));
        if (on) {
            this.buttons[y][x].setBackground(Color.BLACK);
        } else {
            this.buttons[y][x].setBackground(Color.WHITE);
        }
    }

    public void setBoxBlack(int y, int x) {
        this.buttons[y][x].setBackground(Color.BLACK);
    }

    public void setBoxWhite(int y, int x) {
        this.buttons[y][x].setBackground(Color.WHITE);
    }

    public void setPause() {
        this.pauseState = false;
        this.stepButton.setVisible(true);
        this.saveButton.setVisible(true);
    }

    public void random() {
        this.game.randomise();
    }

    public void save(String string) {
        try {
            this.game.saveGameBoard(string);
        } catch (Exception er) {

        }
    }

    public Color getBoxColour(int y, int x) {
        return this.buttons[y][x].getBackground();
    }
}

class PauseButton extends JButton implements Runnable {
    Game game;
    TheoGUI theoGui;

    public PauseButton(String text, Game game, TheoGUI theoGui) {
        this.setText(text);
        this.game = game;
        this.theoGui = theoGui;
    }

    public void run() {
        try {
            this.game.gameLoop();
        } catch (InterruptedException ee) {
            System.out.println("Interrupted.");
        } catch (Exception e) {
        }
    }
}

class SaveButton extends JButton implements Runnable {
    Game game;
    TheoGUI theoGui;

    public SaveButton(String text, Game game, TheoGUI theoGui) {
        this.setText(text);
        this.game = game;
        this.theoGui = theoGui;
    }

    public void run() {
        try {
            String value = this.theoGui.input.getText();
            this.theoGui.save(value);
        } catch (Exception e) {
        }
    }
}