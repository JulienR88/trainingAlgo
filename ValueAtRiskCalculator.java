import java.util.*;

/**
 * Exercice 3: Calcul de Value at Risk (VaR)
 * 
 * La Value at Risk est une mesure de risque cruciale en finance.
 * Elle représente la perte maximale attendue sur un horizon temporel
 * avec un certain niveau de confiance.
 * 
 * Cet exercice teste:
 * - Calculs statistiques avancés
 * - Gestion de collections et tri
 * - Interpolation linéaire
 * - Compréhension des concepts financiers
 */
public class ValueAtRiskCalculator {
    
    /**
     * Calcule la VaR historique (méthode non-paramétrique)
     * 
     * @param returns liste des rendements historiques (ex: -0.05 pour -5%)
     * @param confidenceLevel niveau de confiance (ex: 0.95 pour 95%)
     * @return VaR (positive number representing potential loss)
     */
    public static double calculateHistoricalVaR(List<Double> returns, double confidenceLevel) {
        // TODO: Implémentez cette méthode
        
        if (returns == null || returns.isEmpty()) {
            throw new IllegalArgumentException("Liste de rendements vide");
        }
        
        if (confidenceLevel <= 0 || confidenceLevel >= 1) {
            throw new IllegalArgumentException("Niveau de confiance invalide");
        }
        
        // Votre implémentation ici...
        // 1. Trier les rendements par ordre croissant
        // 2. Trouver le percentile correspondant
        // 3. Retourner la valeur absolue du rendement au percentile
        
        return 0.0;
    }
    
    /**
     * Calcule la VaR paramétrique (méthode normale)
     * 
     * @param returns liste des rendements historiques
     * @param confidenceLevel niveau de confiance
     * @return VaR paramétrique
     */
    public static double calculateParametricVaR(List<Double> returns, double confidenceLevel) {
        // TODO: Implémentez cette méthode
        
        if (returns == null || returns.size() < 2) {
            throw new IllegalArgumentException("Pas assez de données");
        }
        
        // Votre implémentation ici...
        // 1. Calculer la moyenne des rendements
        // 2. Calculer l'écart-type
        // 3. Utiliser la distribution normale pour trouver le quantile
        // 4. VaR = moyenne - écart-type * z_score
        
        return 0.0;
    }
    
    /**
     * Calcule la Conditional VaR (Expected Shortfall)
     * 
     * @param returns liste des rendements historiques
     * @param confidenceLevel niveau de confiance
     * @return CVaR (perte moyenne dans les pires cas)
     */
    public static double calculateConditionalVaR(List<Double> returns, double confidenceLevel) {
        // TODO: Implémentez cette méthode (challenge avancé)
        
        if (returns == null || returns.isEmpty()) {
            throw new IllegalArgumentException("Liste de rendements vide");
        }
        
        // Votre implémentation ici...
        // CVaR = moyenne des rendements pires que la VaR
        
        return 0.0;
    }
    
    /**
     * Calcule la VaR pour un portefeuille multi-actifs
     * 
     * @param portfolioReturns matrice des rendements (lignes: temps, colonnes: actifs)
     * @param weights poids des actifs dans le portefeuille
     * @param confidenceLevel niveau de confiance
     * @return VaR du portefeuille
     */
    public static double calculatePortfolioVaR(List<List<Double>> portfolioReturns, 
                                            List<Double> weights, 
                                            double confidenceLevel) {
        // TODO: Implémentez cette méthode (challenge expert)
        
        if (portfolioReturns == null || weights == null) {
            throw new IllegalArgumentException("Données invalides");
        }
        
        // Votre implémentation ici...
        // 1. Calculer les rendements du portefeuille (pondérés)
        // 2. Appliquer une des méthodes VaR précédentes
        
        return 0.0;
    }
    
    /**
     * Fonction utilitaire pour calculer la moyenne
     */
    private static double calculateMean(List<Double> values) {
        // Implémentez cette méthode utilitaire si nécessaire
        return 0.0;
    }
    
    /**
     * Fonction utilitaire pour calculer l'écart-type
     */
    private static double calculateStandardDeviation(List<Double> values) {
        // Implémentez cette méthode utilitaire si nécessaire
        return 0.0;
    }
    
    /**
     * Approximation du quantile de la distribution normale
     * (Pour les entretiens, une approximation simple est acceptable)
     */
    private static double getNormalQuantile(double confidenceLevel) {
        // Implémentez cette méthode si nécessaire
        // Pour 95%: ~1.645, pour 99%: ~2.326
        return 0.0;
    }
    
    public static void main(String[] args) {
        // Données de test - rendements quotidiens d'un portefeuille
        List<Double> returns = Arrays.asList(
            -0.025, 0.015, -0.032, 0.008, -0.018, 0.022, -0.041, 0.012,
            -0.015, 0.028, -0.008, 0.018, -0.035, 0.005, -0.022, 0.032,
            -0.012, 0.025, -0.028, 0.015, -0.045, 0.018, -0.005, 0.035,
            -0.018, 0.022, -0.038, 0.012, -0.015, 0.028
        );
        
        System.out.println("=== Calcul de Value at Risk (VaR) ===");
        System.out.println("Nombre d'observations: " + returns.size());
        
        // Test 1: VaR Historique à 95%
        System.out.println("\n1. VaR Historique (95%):");
        double historicalVaR95 = calculateHistoricalVaR(returns, 0.95);
        System.out.printf("VaR 95%% (1 jour): %.2f%%%n", historicalVaR95 * 100);
        
        // Test 2: VaR Historique à 99%
        System.out.println("\n2. VaR Historique (99%):");
        double historicalVaR99 = calculateHistoricalVaR(returns, 0.99);
        System.out.printf("VaR 99%% (1 jour): %.2f%%%n", historicalVaR99 * 100);
        
        // Test 3: VaR Paramétrique
        System.out.println("\n3. VaR Paramétrique (95%):");
        double parametricVaR = calculateParametricVaR(returns, 0.95);
        System.out.printf("VaR Paramétrique 95%%: %.2f%%%n", parametricVaR * 100);
        
        // Test 4: Conditional VaR
        System.out.println("\n4. Conditional VaR (95%):");
        double cvar = calculateConditionalVaR(returns, 0.95);
        System.out.printf("CVaR 95%%: %.2f%%%n", cvar * 100);
        
        // Test 5: VaR Portefeuille (optionnel)
        System.out.println("\n5. VaR Portefeuille multi-actifs:");
        List<List<Double>> portfolioReturns = new ArrayList<>();
        List<Double> weights = Arrays.asList(0.6, 0.4); // 60% actif 1, 40% actif 2
        
        // Remplir avec des données de test si vous implémentez cette méthode
        double portfolioVaR = calculatePortfolioVaR(portfolioReturns, weights, 0.95);
        System.out.printf("VaR Portefeuille: %.2f%%%n", portfolioVaR * 100);
    }
}
