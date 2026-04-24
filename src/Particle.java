public class Particle {

    public double x, y;
    public double vx = 0, vy = 0;
    public int type;

    public Particle(double x, double y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void update() {
        x += vx;
        y += vy;

        // atrito leve
        vx *= 0.98;
        vy *= 0.98;
    }
}