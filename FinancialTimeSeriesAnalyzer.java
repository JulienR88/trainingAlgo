import java.util.*;
import java.util.stream.Collectors;

/**
 * Exercice 2: Analyse de séries temporelles financières
 * 
 * Les séries temporelles sont fondamentales en finance pour analyser les prix,
 * volumes et indicateurs de marché. Cet exercice teste:
 * - Manipulation de données temporelles
 * - Calculs statistiques (moyenne mobile, écart-type)
 * - Détection de tendances et patterns
 */
public class FinancialTimeSeriesAnalyzer {
    
    /**
     * Représente un point de donnée financière avec sa date
     */
    public static class PricePoint {
        public final String date;  // Format: "YYYY-MM-DD"
        public final double price;
        
        public PricePoint(String date, double price) {
            this.date = date;
            this.price = price;
        }
        
        @Override
        public String toString() {
            return String.format("%s: %.2f", date, price);
        }
    }
    
    /**
     * Calcule la moyenne mobile simple sur N périodes
     * 
     * @param prices liste des points de prix
     * @param period nombre de périodes pour la moyenne mobile
     * @return liste des moyennes mobiles (null pour les premières positions insuffisantes)
     */
    public static List<Double> calculateSimpleMovingAverage(List<PricePoint> prices, int period) {
        // TODO: Implémentez cette méthode
        
        if (prices == null || period <= 0 || period > prices.size()) {
            throw new IllegalArgumentException("Paramètres invalides");
        }
        
        List<Double> sma = new ArrayList<>();
        
        for(int i = 0; i < prices.size() ; i++) {
            if( i< period - 1 ){
                sma.add(null);
            } else {
                double sum = 0;
                for(int j = i - period + 1; j <= i; j++){
                    sum += prices.get(j).price;
                }
                sma.add(sum / period);
            }
        }
        return sma;
    }
    
    /**
     * Calcule la volatilité (écart-type des rendements)
     * 
     * @param prices liste des points de prix
     * @return volatilité annualisée (écart-type)
     */
    public static double calculateVolatility(List<PricePoint> prices) {
        // TODO: Implémentez cette méthode
        
        if (prices == null || prices.size() < 2) {
            throw new IllegalArgumentException("Pas assez de données pour calculer la volatilité");
        }
        
        // Votre implémentation ici...
        // 1. Calculer les rendements quotidiens, moyenne des rendements quotidiens
        // 2. Calculer l'écart-type des rendements
        // 3. Annualiser (multiplier par sqrt(252) pour les jours de bourse)

        // Calcul des rendement quotidien
        List<Double> rendementQuotidien = new ArrayList<>();

        for(int i = 1; i < prices.size(); i++) {
            double currentPrice = prices.get(i).price;
            double beforeCurrentPrice = prices.get(i - 1).price;
            double rendement = (currentPrice - beforeCurrentPrice) / beforeCurrentPrice;

            rendementQuotidien.add(rendement);
        }

        //System.out.println(rendementQuotidien.stream().map(String::valueOf).collect(Collectors.joining(", ")));

        // calcul de la moyenne des rendements
        double avgRendement = 0;
        double sommeRendement =0;
        for(double rendement : rendementQuotidien ){
            sommeRendement += rendement;
        }
        avgRendement = sommeRendement / rendementQuotidien.size();

        // Calcul variance et ecart-type
        //variance = somme((rendement[i] - moyenne)²) / (n - 1)
        //écart_type = √variance
        double variance =  0;
        double sommeVariance = 0;
        double ecartType = 0;
        for(int i = 0; i < rendementQuotidien.size(); i++) {
            sommeVariance += (rendementQuotidien.get(i) - avgRendement) * (rendementQuotidien.get(i) - avgRendement);
        }

        variance = sommeVariance / (rendementQuotidien.size() - 1);
        ecartType = Math.sqrt(variance);

        return ecartType * Math.sqrt(252);
    }
    
    /**
     * Détecte les croisements de moyennes mobiles (signaux d'achat/vente)
     * 
     * @param shortSMA moyenne mobile courte
     * @param longSMA moyenne mobile longue
     * @return liste des indices où des croisements se produisent
     */
    public static List<Integer> detectCrossovers(List<Double> shortSMA, List<Double> longSMA) {
        // TODO: Implémentez cette méthode
        
        if (shortSMA == null || longSMA == null || shortSMA.size() != longSMA.size()) {
            throw new IllegalArgumentException("Données invalides pour détection de croisements");
        }
        
        List<Integer> crossoverPoints = new ArrayList<>();
        
        // Votre implémentation ici...
        // Un croisement se produit quand SMA courte passe au-dessus/en dessous de SMA longue
        for(int i = 1; i < shortSMA.size(); i++) {
            if((shortSMA.get(i) != null && longSMA.get(i) != null && shortSMA.get(i-1) != null && longSMA.get(i-1) != null)) {
                double currentShortSmaPoint = shortSMA.get(i);
                double beforeCurrentShortSmaPoint = shortSMA.get(i-1);
                double currentLongSmaPoint = longSMA.get(i);
                double beforeCurrentLongSmaPoint = longSMA.get(i-1);

                if((beforeCurrentShortSmaPoint < beforeCurrentLongSmaPoint && currentShortSmaPoint > currentLongSmaPoint)
                        || (beforeCurrentShortSmaPoint > beforeCurrentLongSmaPoint && currentShortSmaPoint < currentLongSmaPoint)){

                    crossoverPoints.add(i);
                }
            }
        }
        
        return crossoverPoints;
    }
    
    /**
     * Trouve le prix maximum et sa date sur une période
     * 
     * @param prices liste des points de prix
     * @param startIndex indice de début
     * @param endIndex indice de fin
     * @return PricePoint du maximum
     */
    public static PricePoint findMaximumPrice(List<PricePoint> prices, int startIndex, int endIndex) {
        // TODO: Implémentez cette méthode
        
        if (prices == null || startIndex < 0 || endIndex >= prices.size() || startIndex > endIndex) {
            throw new IllegalArgumentException("Indices invalides");
        }

        /* Solution boucle
        PricePoint maxPrice = prices.get(startIndex);

        for (int i = startIndex + 1; i <= endIndex; i++) {
            if (prices.get(i).price > maxPrice.price) {
                maxPrice = prices.get(i);
            }
        */

        // Solution Stream
        List<PricePoint> range = new ArrayList<>();
        for(int i = startIndex; i<= endIndex; i++){
            range.add(prices.get(i));
        }
        
        PricePoint maxPrice = range.stream().max(Comparator.comparingDouble(value -> value.price)).get();
        
        return maxPrice;
    }
    
    /**
     * Calcule le drawdown maximum (perte maximale par rapport au sommet précédent)
     * 
     * @param prices liste des points de prix
     * @return drawdown maximum en pourcentage
     */
    public static double calculateMaxDrawdown(List<PricePoint> prices) {
        // TODO: Implémentez cette méthode (challenge avancé)
        
        if (prices == null || prices.isEmpty()) {
            return 0.0;
        }
        
        // Votre implémentation ici...
        // Drawdown = (valeur_max - valeur_actuelle) / valeur_max

        double maxValue = prices.get(0).price;
        double maxDrawdown = 0;
        for(int i = 1; i < prices.size(); i++){

            double currentPrice = prices.get(i).price;
            if(currentPrice > maxValue) {
                maxValue = currentPrice;
            }

            double drawdown = (maxValue - currentPrice) / maxValue;
            if(drawdown > maxDrawdown) {
                maxDrawdown = drawdown;
            }

        }
        
        return maxDrawdown;
    }
    
    public static void main(String[] args) {
        // Données de test - prix d'une action sur 30 jours
        List<PricePoint> priceData = Arrays.asList(
            new PricePoint("2024-01-01", 100.0),
            new PricePoint("2024-01-02", 102.5),
            new PricePoint("2024-01-03", 101.2),
            new PricePoint("2024-01-04", 103.8),
            new PricePoint("2024-01-05", 105.2),
            new PricePoint("2024-01-08", 104.1),
            new PricePoint("2024-01-09", 106.5),
            new PricePoint("2024-01-10", 107.8),
            new PricePoint("2024-01-11", 106.2),
            new PricePoint("2024-01-12", 108.9),
            new PricePoint("2024-01-15", 109.5),
            new PricePoint("2024-01-16", 107.3),
            new PricePoint("2024-01-17", 105.8),
            new PricePoint("2024-01-18", 104.2),
            new PricePoint("2024-01-19", 103.5)
        );
        
        System.out.println("=== Analyse de séries temporelles financières ===");
        
        // Test 1: Moyenne mobile
        System.out.println("\n1. Moyenne mobile sur 5 périodes:");
        List<Double> sma5 = calculateSimpleMovingAverage(priceData, 5);
        for (int i = 0; i < sma5.size(); i++) {
            if (sma5.get(i) != null) {
                System.out.printf("Jour %d: SMA5 = %.2f%n", i + 1, sma5.get(i));
            }
        }
        
        // Test 2: Volatilité
        System.out.println("\n2. Volatilité:");
        double volatility = calculateVolatility(priceData);
        System.out.printf("Volatilité annualisée: %.2f%%%n", volatility * 100);
        
        // Test 3: Détection de croisements
        System.out.println("\n3. Détection de croisements:");
        List<Double> sma3 = calculateSimpleMovingAverage(priceData, 3);
        List<Double> sma8 = calculateSimpleMovingAverage(priceData, 8);
        List<Integer> crossovers = detectCrossovers(sma3, sma8);
        System.out.println("Points de croisement: " + crossovers);
        
        // Test 4: Prix maximum
        System.out.println("\n4. Prix maximum sur les 10 premiers jours:");
        PricePoint maxPrice = findMaximumPrice(priceData, 0, 9);
        System.out.println("Maximum: " + maxPrice);
        
        // Test 5: Drawdown maximum
        System.out.println("\n5. Drawdown maximum:");
        double maxDD = calculateMaxDrawdown(priceData);
        System.out.printf("Drawdown maximum: %.2f%%%n", maxDD * 100);
    }
}
