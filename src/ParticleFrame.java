import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ParticleFrame extends JFrame {

    private final List<Particle> particles;
    private final double[][] rules;
    private final int width;
    private final int height;

    // Distâncias importantes
    private final double minDist = 8;   // distância mínima (evita toque)
    private final double maxDist = 80;  // alcance da interação

    public ParticleFrame(int width, int height, List<Particle> particles, double[][] rules) {
        this.width = width;
        this.height = height;
        this.particles = particles;
        this.rules = rules;

        setTitle("Particle Life - Java");
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new DrawPanel());

        setVisible(true);

        startLoop();
    }

    private void startLoop() {
        new Timer(16, e -> {
            updateParticles();
            repaint();
        }).start();
    }

    private void updateParticles() {

        for (Particle a : particles) {

            double fx = 0;
            double fy = 0;

            for (Particle b : particles) {
                if (a == b) continue;

                double dx = b.x - a.x;
                double dy = b.y - a.y;

                double dist = Math.sqrt(dx * dx + dy * dy);
                if (dist < 0.001) continue;

                double nx = dx / dist;
                double ny = dy / dist;

                // 1. REPULSÃO FORTE (evita encostar)
                if (dist < minDist) {
                    double force = (minDist - dist) * 0.5;
                    fx -= force * nx;
                    fy -= force * ny;
                }

                // 2. INTERAÇÃO NORMAL (atração/repulsão por tipo)
                else if (dist < maxDist) {
                    double rule = rules[a.type][b.type];

                    double force = rule * (1 - dist / maxDist);

                    fx += force * nx;
                    fy += force * ny;
                }
            }

            // aplica força acumulada
            a.vx += fx * 0.05;
            a.vy += fy * 0.05;
        }

        // Atualiza posição e trata bordas
        for (Particle p : particles) {
            p.update();

            if (p.x < 0 || p.x > width) p.vx *= -1;
            if (p.y < 0 || p.y > height) p.vy *= -1;
        }
    }

    private class DrawPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // fundo preto
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());

            // desenha partículas
            for (Particle p : particles) {
                g.setColor(getColor(p.type));
                g.fillOval((int) p.x, (int) p.y, 4, 4);
            }
        }

        private Color getColor(int type) {
            Color[] colors = {
                    Color.RED,
                    Color.GREEN,
                    Color.BLUE,
                    Color.YELLOW,
                    Color.CYAN,
                    Color.MAGENTA
            };

            return colors[type % colors.length];
        }
    }
}