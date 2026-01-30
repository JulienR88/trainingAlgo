import java.util.*;

/**
 * Exercice 4: Optimisation de portefeuille
 * 
 * L'optimisation de portefeuille est au cœur de la gestion d'actifs en private equity.
 * Elle vise à trouver la meilleure allocation d'actifs pour maximiser le rendement
 * tout en minimisant le risque.
 * 
 * Cet exercice teste:
 * - Algorithmes d'optimisation
 * - Calculs matriciels simples
 * - Programmation dynamique
 * - Résolution de contraintes
 */
public class PortfolioOptimizer {
    
    /**
     * Représente un actif financier avec ses caractéristiques
     */
    public static class Asset {
        public final String name;
        public final double expectedReturn;
        public final double risk;        // Écart-type
        public final double investment;  // Montant minimum d'investissement
        
        public Asset(String name, double expectedReturn, double risk, double investment) {
            this.name = name;
            this.expectedReturn = expectedReturn;
            this.risk = risk;
            this.investment = investment;
        }
        
        @Override
        public String toString() {
            return String.format("%s (R=%.1f%%, σ=%.1f%%, Min=$%.0f)", 
                name, expectedReturn * 100, risk * 100, investment);
        }
    }
    
    /**
     * Représente une allocation de portefeuille
     */
    public static class Portfolio {
        public final Map<Asset, Double> weights;
        public final double expectedReturn;
        public final double risk;
        
        public Portfolio(Map<Asset, Double> weights, double expectedReturn, double risk) {
            this.weights = weights;
            this.expectedReturn = expectedReturn;
            this.risk = risk;
        }
        
        public double getSharpeRatio(double riskFreeRate) {
            return (expectedReturn - riskFreeRate) / risk;
        }
    }
    
    /**
     * Optimisation simple - allocation proportionnelle au rendement/risque
     * 
     * @param assets liste des actifs disponibles
     * @param totalBudget budget total d'investissement
     * @return portefeuille optimisé
     */
    public static Portfolio optimizeSimpleRatio(List<Asset> assets, double totalBudget) {
        // TODO: Implémentez cette méthode
        
        if (assets == null || assets.isEmpty() || totalBudget <= 0) {
            throw new IllegalArgumentException("Paramètres invalides");
        }
        
        // Votre implémentation ici...
        // 1. Calculer le ratio rendement/risque pour chaque actif
        // 2. Allouer proportionnellement à ces ratios
        // 3. Respecter le budget total
        
        Map<Asset, Double> weights = new HashMap<>();
        
        return new Portfolio(weights, 0.0, 0.0);
    }
    
    /**
     * Optimisation avec contraintes - méthode de la frontière efficiente simplifiée
     * 
     * @param assets liste des actifs
     * @param targetRisk risque cible maximum
     * @param totalBudget budget total
     * @return portefeuille optimisé respectant les contraintes
     */
    public static Portfolio optimizeWithConstraints(List<Asset> assets, double targetRisk, double totalBudget) {
        // TODO: Implémentez cette méthode (challenge avancé)
        
        if (assets == null || targetRisk <= 0 || totalBudget <= 0) {
            throw new IllegalArgumentException("Paramètres invalides");
        }
        
        // Votre implémentation ici...
        // 1. Générer des allocations possibles (approche discrète)
        // 2. Calculer le risque et rendement pour chaque allocation
        // 3. Garder la meilleure allocation qui respecte les contraintes
        
        Map<Asset, Double> weights = new HashMap<>();
        
        return new Portfolio(weights, 0.0, 0.0);
    }
    
    /**
     * Optimisation par force brute (pour petit nombre d'actifs)
     * 
     * @param assets liste des actifs
     * @param steps nombre de pas de discrétisation (ex: 10 pour 10% d'incrément)
     * @param totalBudget budget total
     * @return meilleur portefeuille trouvé
     */
    public static Portfolio optimizeBruteForce(List<Asset> assets, int steps, double totalBudget) {
        // TODO: Implémentez cette méthode (challenge expert)
        
        if (assets == null || assets.size() > 5 || steps <= 0 || totalBudget <= 0) {
            throw new IllegalArgumentException("Force brute uniquement pour <= 5 actifs");
        }
        
        // Votre implémentation ici...
        // 1. Générer toutes les combinaisons possibles
        // 2. Évaluer chaque portefeuille
        // 3. Retourner le meilleur selon le ratio de Sharpe
        
        Map<Asset, Double> bestWeights = new HashMap<>();
        double bestSharpe = Double.NEGATIVE_INFINITY;
        
        return new Portfolio(bestWeights, 0.0, 0.0);
    }
    
    /**
     * Calcule les métriques d'un portefeuille
     */
    public static void calculatePortfolioMetrics(Portfolio portfolio, double riskFreeRate) {
        System.out.println("\n=== Métriques du Portefeuille ===");
        System.out.printf("Rendement attendu: %.2f%%%n", portfolio.expectedReturn * 100);
        System.out.printf("Risque (σ): %.2f%%%n", portfolio.risk * 100);
        System.out.printf("Ratio de Sharpe: %.3f%n", portfolio.getSharpeRatio(riskFreeRate));
        
        System.out.println("\nAllocation:");
        for (Map.Entry<Asset, Double> entry : portfolio.weights.entrySet()) {
            System.out.printf("%s: %.1f%% ($%.0f)%n", 
                entry.getKey().name, 
                entry.getValue() * 100,
                entry.getValue() * 1000000); // Suppose $1M total
        }
    }
    
    /**
     * Validation des contraintes
     */
    public static boolean validateConstraints(Portfolio portfolio, double targetRisk) {
        // Vérifie que le poids total = 100%
        double totalWeight = portfolio.weights.values().stream().mapToDouble(Double::doubleValue).sum();
        boolean weightValid = Math.abs(totalWeight - 1.0) < 0.01;
        
        // Vérifie que le risque est dans les limites
        boolean riskValid = portfolio.risk <= targetRisk;
        
        // Vérifie qu'aucun poids n'est négatif
        boolean positiveWeights = portfolio.weights.values().stream().allMatch(w -> w >= 0);
        
        return weightValid && riskValid && positiveWeights;
    }
    
    public static void main(String[] args) {
        // Données de test - actifs typiques en private equity
        List<Asset> assets = Arrays.asList(
            new Asset("Private Equity Fund A", 0.18, 0.25, 1000000),
            new Asset("Venture Capital Fund B", 0.22, 0.35, 500000),
            new Asset("Real Estate Fund C", 0.12, 0.15, 750000),
            new Asset("Infrastructure Fund D", 0.14, 0.18, 800000),
            new Asset("Credit Fund E", 0.08, 0.10, 600000)
        );
        
        double totalBudget = 5000000; // $5M
        double riskFreeRate = 0.03;   // 3% taux sans risque
        double targetRisk = 0.20;     // 20% risque maximum
        
        System.out.println("=== Optimisation de Portefeuille Private Equity ===");
        System.out.println("Budget total: $" + totalBudget);
        System.out.println("Actifs disponibles:");
        assets.forEach(System.out::println);
        
        // Test 1: Optimisation simple
        System.out.println("\n1. Optimisation par ratio rendement/risque:");
        Portfolio simplePortfolio = optimizeSimpleRatio(assets, totalBudget);
        calculatePortfolioMetrics(simplePortfolio, riskFreeRate);
        
        // Test 2: Optimisation avec contraintes
        System.out.println("\n2. Optimisation avec contraintes de risque:");
        Portfolio constrainedPortfolio = optimizeWithConstraints(assets, targetRisk, totalBudget);
        calculatePortfolioMetrics(constrainedPortfolio, riskFreeRate);
        
        // Test 3: Force brute (petit nombre d'actifs)
        System.out.println("\n3. Optimisation par force brute (3 premiers actifs):");
        List<Asset> subsetAssets = assets.subList(0, 3);
        Portfolio bruteForcePortfolio = optimizeBruteForce(subsetAssets, 10, totalBudget);
        calculatePortfolioMetrics(bruteForcePortfolio, riskFreeRate);
        
        // Validation
        System.out.println("\n=== Validation des Contraintes ===");
        System.out.println("Portfolio simple valide: " + validateConstraints(simplePortfolio, targetRisk));
        System.out.println("Portfolio contraint valide: " + validateConstraints(constrainedPortfolio, targetRisk));
        System.out.println("Portfolio force brute valide: " + validateConstraints(bruteForcePortfolio, targetRisk));
    }
}
