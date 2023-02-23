import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Interfaz Grafica del Juego de la Vida
// Autor:   Jesus Angel Juarez Zazueta

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

    // Inicia la logica del programa
    public static void main(String[] args) {
        gamelife.main(args);

    }

    // Contructor de la interfaz
    public display() {
        // Se inicializan las propiedades de la ventana principal
        setLayout(new BorderLayout(5,5));
        setBounds(10,10,800,800);
        setTitle("Game of Life");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Propiedades del titulo
        label = new JLabel("Bienvenido al Juego de la Vida");
        label.setBounds(10,10,600,100);
        fuente = new Font("Comic Sans", Font.BOLD, 24);
        label.setFont(fuente);
        add(label,BorderLayout.NORTH);

        // Inicializacion del panel principal de la matriz
        panel = new JPanel();
        panel.setLayout(new GridLayout(gamelife.M,gamelife.N));

        // Manda a crear la matriz(tabla) de botones y la agrega al panel principal
        matriz = gamelife.creaMatriz();
        for (int i = 0; i < gamelife.M; i++) {
            for (int j = 0; j < gamelife.N; j++) {
                panel.add(matriz[i][j]);
            }
        }
        panel.setPreferredSize(new Dimension(500,500));

        add(panel,BorderLayout.CENTER);
        
        // Se inicializa el panel de control del programa
        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1,2));

        fuente = new Font("Comic Sans", Font.PLAIN, 18);
        
        // Se crea el boton de incio que da comienzo a un hilo que ejecutara la logica del programa constatemente hasta detenerlo
        // presionando nuevamente el boton o limpiando la matriz y se añade al panel de control
        startButton = new JButton("Start");
        startButton.setFont(fuente);
        startButton.setPreferredSize(new Dimension(30,50));
        startButton.addActionListener(new ActionListener() {
           public void actionPerformed (ActionEvent e) {
                // Inicio del hilo de ejecucion
                Runnable oullea = new Runnable() {
                    public void run() {
                        try {
                            if (startButton.getText() == "Start") {
                                startButton.setText("Stop");
                                // Manda a llamar la funcion que genera la nueva instancia del matriz 
                                // segun la logica del problema y la agrega al panel
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

        // Se crea el boton que ejecuta la logica del programa manualmente paso a paso y se añade al panel de control
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
        
        // Se crea el boton que limpia la matriz y tambien detiene el programa
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

        // Añade el panel de control a la ventana principal
        add(panel2,BorderLayout.SOUTH);

        setVisible(true);
        
    }

    // Funcio que limita la velocidad de ejecucion de la logica
    public void espera() {
        try {
            Thread.sleep(500);
        } catch (Exception e) {
        }
    }

}


