public class Particle {

    public double x, y;
    public double vx = 0, vy = 0;

    public DNA dna;

    public Particle(double x, double y, DNA dna) {
        this.x = x;
        this.y = y;
        this.dna = dna;
    }

    public void update() {
        x += vx;
        y += vy;

        vx *= 0.98;
        vy *= 0.98;
    }
}