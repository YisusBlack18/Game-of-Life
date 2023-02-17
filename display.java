import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class display extends JFrame {

    private JLabel label;
    private JPanel panel;
    private JPanel panel2;
    private Font fuente;
    protected JButton matriz[][];
    protected JButton startButton;
    protected JButton nexButton;
    protected JButton clearButton;
    protected Thread game;

    public static void main(String[] args) {
        gamelife.main(args);

    }
    
    public display() {
        setLayout(new BorderLayout(5,5));
        setBounds(10,10,800,800);
        setTitle("Game of Life");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        label = new JLabel("Bienvenido al Juego de la Vida");
        label.setBounds(10,10,600,100);
        fuente = new Font("Comic Sans", Font.BOLD, 24);
        label.setFont(fuente);
        add(label,BorderLayout.NORTH);

        panel = new JPanel();
        panel.setLayout(new GridLayout(gamelife.M,gamelife.N));

        matriz = gamelife.creaMatriz();

        for (int i = 0; i < gamelife.M; i++) {
            for (int j = 0; j < gamelife.N; j++) {
                panel.add(matriz[i][j]);
            }
        }
        panel.setPreferredSize(new Dimension(500,500));

        add(panel,BorderLayout.CENTER);
        
        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1,2));

        fuente = new Font("Comic Sans", Font.PLAIN, 18);
        
        startButton = new JButton("Start");
        startButton.setFont(fuente);
        startButton.setPreferredSize(new Dimension(30,50));
        startButton.addActionListener(new ActionListener() {
           public void actionPerformed (ActionEvent e) {
                Runnable oullea = new Runnable() {
                    public void run() {
                        try {
                            if (startButton.getText() == "Start") {
                                startButton.setText("Stop");
                                
                                while (startButton.getText() == "Stop") {
                                    matriz = gamelife.nextGeneration(matriz,gamelife.M,gamelife.N);
                                    panel.removeAll();
                                    for (int i = 0; i < gamelife.M; i++) {
                                        for (int j = 0; j < gamelife.N; j++) {
                                            panel.add(matriz[i][j]);
                                        }
                                    }
                                    panel.updateUI();
                                    espera();
                                }
                    
                            } else {
                                startButton.setText("Start");
                            }
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                Thread game = new Thread(oullea);
                game.start();
           }
        });

        panel2.add(startButton);

        nexButton = new JButton("Next");
        nexButton.setFont(fuente);
        nexButton.setPreferredSize(new Dimension(30,50));
        nexButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                matriz = gamelife.nextGeneration(matriz,gamelife.M,gamelife.N);
                panel.removeAll();
                for (int i = 0; i < gamelife.M; i++) {
                    for (int j = 0; j < gamelife.N; j++) {
                        panel.add(matriz[i][j]);
                    }
                }
                panel.updateUI();
            }

        });
        panel2.add(nexButton);
        
        clearButton = new JButton("Clear");
        clearButton.setFont(fuente);
        clearButton.setPreferredSize(new Dimension(30,50));
        clearButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                startButton.setText("Start");
                for (int i = 0; i < gamelife.M; i++) {
                    for (int j = 0; j < gamelife.N; j++) {
                        matriz[i][j].setBackground(Color.GRAY);
                    }
                }
            }

        });
        panel2.add(clearButton);

        add(panel2,BorderLayout.SOUTH);

        setVisible(true);
        
    }

    public void espera() {
        try {
            Thread.sleep(500);
        } catch (Exception e) {
        }
    }

}


