import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

// Logica del Juego de la Vida de John Conway
// Autor:   Jesus Angel Juarez Zazueta

public class gamelife {

    public static final int M = 10;
    public static final int N = 10;

    // Inicia la interfaz grafica del programa
    public static void main(String[] args) {
        new display();
    }

    // Crea la matriz de botones
    public static JButton[][] creaMatriz() {
        JButton[][] matriz = new JButton[M][N];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                // Se crean los botones en cada espacio del matriz y se definen sus propiedades
                matriz[i][j] = new JButton();
                matriz[i][j].setBackground(Color.GRAY);
                matriz[i][j].setPreferredSize(new Dimension(10,10));
                matriz[i][j].addActionListener(new ActionListener() {
                    // Agrega un actionlistener que cambia el color del boton cuando se presiona
                    // Si esta muerta(desactivada) se mantiene en gris, si esta viva cambia a amarillo
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (((JButton) e.getSource()).getBackground() == Color.GRAY) {
                            ((JButton) e.getSource()).setBackground(Color.YELLOW);
                        } else {
                            ((JButton) e.getSource()).setBackground(Color.GRAY);
                        }
                    }
                    
                });
            }
        }

        return matriz;
    }
    // Funcion que recibe la matriz actual, genera y devuelve una nueva segun la logica del problema
    // Nota: Las casillas fuera de los limites de la matriz se consideran muertas
    public static JButton[][] nextGeneration(JButton[][] tabla, int M, int N) {
        JButton[][] nextTabla = creaMatriz();

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                int vivos = 0;
                // Se recorren las casillas(botones) una por una
                for (int k = -1; k <= 1; k++) {
                    for (int k2 = -1; k2 <= 1; k2++) {
                        // Cuenta las casillas vivas alrededor de la casilla actual dentro de los limites de la matriz
                        if ((i+k>=0 && i+k<M) && (j+k2>=0 && j+k2<N)) {
                            if (tabla[i+k][j+k2].getBackground() == Color.YELLOW) {
                                vivos++;
                            }
                        }
                    }
                }
                // Reducimos el conteo si la casilla central(enfocada) esta viva
                if (tabla[i][j].getBackground() == Color.YELLOW) {
                    vivos--;
                }
                // Si la casilla central(enfocada) esta muerta, dependiendo del numero de casillas vivas alrededor se hace la siguiente:
                if (tabla[i][j].getBackground() == Color.GRAY) {
                    // Si hay 3 vivas exactamente la casilla en cuestion vive(se activa), de otra forma se mantiene muerta
                    if (vivos == 3) {
                        nextTabla[i][j].setBackground(Color.YELLOW);
                    } else {
                        nextTabla[i][j].setBackground(Color.GRAY);
                    }
                // Si la casilla esta viva, se hace lo siguiente
                } else {
                    // Si hay alrededor 1 o menos casillas vivas, la casilla muere
                    if (vivos <= 1) {
                        nextTabla[i][j].setBackground(Color.GRAY);
                    // Si entre 2 o 3 casillas vivas, la casilla se mantiene viva
                    } else if (vivos >= 2 && vivos <= 3) {
                        nextTabla[i][j].setBackground(Color.YELLOW);
                    // Si hay 4 o mas casillas vivas, la casilla se muere
                    } else if (vivos >= 4) {
                        nextTabla[i][j].setBackground(Color.GRAY);
                    }
                }

            }
        }

        return nextTabla;
    }
}
