import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int width = 800;
        int height = 600;

        int numParticles = 400;
        int numTypes = 5;

        double[][] rules = {
                { rand(), rand(), rand(), rand(), rand() },
                { rand(), rand(), rand(), rand(), rand() },
                { rand(), rand(), rand(), rand(), rand() },
                { rand(), rand(), rand(), rand(), rand() },
                { rand(), rand(), rand(), rand(), rand() }
        };

        List<Particle> particles = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < numParticles; i++) {
            int type = rand.nextInt(numTypes);

            particles.add(new Particle(
                    rand.nextDouble() * width,
                    rand.nextDouble() * height,
                    type
            ));
        }

        new ParticleFrame(width, height, particles, rules);
    }

    private static double rand(double min, double max) {
        return min + (Math.random() * (max - min));
    }

    private static double rand() {
        double min = -0.5;
        double max = 0.5;
        return min + (Math.random() * (max - min));
    }
}