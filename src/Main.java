import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int width = 800;
        int height = 600;

        int numParticles = 400;

        List<Color> types = Arrays.asList(Color.RED,Color.GREEN,Color.BLUE);
        List<DNA> dnaList = new ArrayList<>();

        for(Color a: types){
            DNA dna = new DNA(a);
            for(Color b: types){
                dna.setInteraction(b, rand());
            }
            dnaList.add(dna);
            System.out.println(dna.toString());
        }

        // ===== CRIAÇÃO DOS DNAs =====
/*        DNA red = new DNA(Color.RED)
                .setInteraction(Color.RED, rand())
                .setInteraction(Color.GREEN, rand())
                .setInteraction(Color.BLUE, rand());*/


        List<Particle> particles = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < numParticles; i++) {

            DNA dna = dnaList.get(rand.nextInt(dnaList.size()));

            particles.add(new Particle(
                    rand.nextDouble() * width,
                    rand.nextDouble() * height,
                    dna
            ));
        }

        new ParticleFrame(width, height, particles);
    }

    private static double rand() {
        return rand(-0.5,0.5);
    }

    private static double rand(double min, double max) {
        return min + (Math.random() * (max - min));
    }
}