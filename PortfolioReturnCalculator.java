import java.util.*;

/**
 * Exercice 1: Calculateur de rendement de portefeuille
 * 
 * Dans la private equity, il est crucial de savoir calculer les rendements
 * de différents investissements. Cet exercice teste vos compétences en:
 * - Manipulation de tableaux et collections
 * - Calculs mathématiques financiers
 * - Gestion des cas limites
 */
public class PortfolioReturnCalculator {
    
    /**
     * Calcule le rendement total d'un portefeuille d'investissements
     * 
     * @param investments tableau des montants investis (positifs)
     * @param returns tableau des rendements correspondants (en pourcentage, ex: 0.15 pour 15%)
     * @return rendement total du portefeuille
     * @throws IllegalArgumentException si les tableaux sont de tailles différentes ou contiennent des valeurs invalides
     */
    public static double calculateTotalReturn(double[] investments, double[] returns) {
        // TODO: Implémentez cette méthode
        double valeurPortefeuilleInitial = 0;
        double valeurPortefeuilleFinal = 0;
        double rendementFinal = 0;
        double[] rendement = new double[returns.length];
        
        // Validation des entrées
        if (investments == null || returns == null) {
            throw new IllegalArgumentException("Les tableaux ne peuvent pas être nuls");
        }
        
        if (investments.length != returns.length) {
            throw new IllegalArgumentException("Les tableaux doivent avoir la même taille");
        }
        
        if (investments.length == 0) {
            return 0.0;
        }

        for(int i=0; i<investments.length; i++) {
            valeurPortefeuilleInitial += investments[i];
            rendementFinal += investments[i] * returns[i];
        }
        
        /*for(int i=0; i<investments.length; i++) {
            valeurPortefeuilleInitial += investments[i];
            rendement[i] = investments[i] * ( 1 + returns[i]);
        

        for (double valeurPortefeuille : rendement) {
            valeurPortefeuilleFinal += valeurPortefeuille;
        }*/

        return rendementFinal / valeurPortefeuilleInitial;
    }
    
    /**
     * Calcule le rendement annualisé (CAGR - Compound Annual Growth Rate)
     * 
     * @param initialValue valeur initiale de l'investissement
     * @param finalValue valeur finale de l'investissement
     * @param years nombre d'années
     * @return rendement annualisé
     */
    public static double calculateCAGR(double initialValue, double finalValue, int years) {
        // TODO: Implémentez cette méthode
        // Formule: (finalValue / initialValue)^(1/years) - 1
        
        if (initialValue <= 0 || finalValue <= 0 || years <= 0) {
            throw new IllegalArgumentException("Valeurs invalides pour le calcul CAGR");
        }
        
        return Math.pow(finalValue / initialValue, 1.0 / years) - 1;
    }
    
    /**
     * Trouve l'investissement avec le meilleur rendement
     * 
     * @param investments tableau des montants investis
     * @param returns tableau des rendements
     * @return indice de l'investissement avec le meilleur rendement
     */
    public static int findBestPerformingInvestment(double[] investments, double[] returns) {
        // TODO: Implémentez cette méthode
        
        if (investments == null || returns == null || investments.length == 0) {
            throw new IllegalArgumentException("Données invalides");
        }

        double maxValue = returns[0];
        int indexMaxValue = 0;

        for(int i=1; i<returns.length; i++) {
            if(returns[i] > maxValue) {
                maxValue = returns[i];
                indexMaxValue = i;
            }
        }

        return indexMaxValue;
    }
    
    // Méthode main pour tester votre implémentation
    public static void main(String[] args) {
        // Cas de test 1: Portefeuille simple
        double[] investments1 = {1000.0, 2000.0, 1500.0};
        double[] returns1 = {0.10, 0.15, -0.05}; // 10%, 15%, -5%
        
        System.out.println("=== Test 1: Portefeuille simple ===");
        System.out.println("Rendement total: " + calculateTotalReturn(investments1, returns1));
        System.out.println("Meilleur investissement: " + findBestPerformingInvestment(investments1, returns1));
        
        // Cas de test 2: Calcul CAGR
        System.out.println("\n=== Test 2: Calcul CAGR ===");
        double cagr = calculateCAGR(10000.0, 15000.0, 3);
        System.out.println("CAGR (3 ans, 10k->15k): " + (cagr * 100) + "%");
        
        // Cas de test 3: Cas limites
        System.out.println("\n=== Test 3: Cas limites ===");
        double[] emptyInvestments = {};
        double[] emptyReturns = {};
        System.out.println("Portefeuille vide: " + calculateTotalReturn(emptyInvestments, emptyReturns));
    }
}
