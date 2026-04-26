import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * Representa o DNA de uma "espécie"
 * - possui uma cor
 * - possui regras de interação com outras cores
 */
public class DNA {

    private final Color color;

    // regra: cor alvo -> força
    private final Map<Color, Double> interactions = new HashMap<>();

    public DNA(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    /**
     * Define como esse DNA reage a uma cor
     */
    public DNA setInteraction(Color target, double value) {
        interactions.put(target, value);
        return this; // permite uso fluente
    }

    /**
     * Retorna a força de interação com uma cor
     */
    public double getInteraction(Color target) {
        return interactions.getOrDefault(target, 0.0);
    }

}