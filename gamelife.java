import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class gamelife {

    public static int M = 10;
    public static int N = 10;

    public static void main(String[] args) {
        new display();
    }

    public static JButton[][] creaMatriz() {
        JButton[][] matriz = new JButton[M][N];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                matriz[i][j] = new JButton();
                matriz[i][j].setBackground(Color.GRAY);
                matriz[i][j].setPreferredSize(new Dimension(10,10));
                matriz[i][j].addActionListener(new ActionListener() {

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

    public static JButton[][] nextGeneration(JButton[][] tabla, int M, int N) {
        JButton[][] nextTabla = creaMatriz();

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                int vivos = 0;
                for (int k = -1; k <= 1; k++) {
                    for (int k2 = -1; k2 <= 1; k2++) {
                        if ((i+k>=0 && i+k<M) && (j+k2>=0 && j+k2<N)) {
                            if (tabla[i+k][j+k2].getBackground() == Color.YELLOW) {
                                vivos++;
                            }
                        }
                    }
                }
                if (tabla[i][j].getBackground() == Color.YELLOW) {
                    vivos--;
                }
                if (tabla[i][j].getBackground() == Color.GRAY) {
                    if (vivos == 3) {
                        nextTabla[i][j].setBackground(Color.YELLOW);
                    } else {
                        nextTabla[i][j].setBackground(Color.GRAY);
                    }
                } else {
                    if (vivos <= 1) {
                        nextTabla[i][j].setBackground(Color.GRAY);
                    } else if (vivos >= 2 && vivos <= 3) {
                        nextTabla[i][j].setBackground(Color.YELLOW);
                    } else if (vivos >= 4) {
                        nextTabla[i][j].setBackground(Color.GRAY);
                    }
                }

            }
        }

        return nextTabla;
    }
}
