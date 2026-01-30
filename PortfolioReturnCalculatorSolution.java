import java.util.*;

/**
 * Solution complète pour l'exercice 1
 * Comparez votre solution avec celle-ci après avoir tenté de résoudre l'exercice
 */
public class PortfolioReturnCalculatorSolution {
    
    public static double calculateTotalReturn(double[] investments, double[] returns) {
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
        
        double totalInvestment = 0.0;
        double totalReturnAmount = 0.0;
        
        for (int i = 0; i < investments.length; i++) {
            if (investments[i] <= 0) {
                throw new IllegalArgumentException("Les montants investis doivent être positifs");
            }
            
            totalInvestment += investments[i];
            totalReturnAmount += investments[i] * returns[i];
        }
        
        return totalReturnAmount / totalInvestment;
    }
    
    public static double calculateCAGR(double initialValue, double finalValue, int years) {
        if (initialValue <= 0 || finalValue <= 0 || years <= 0) {
            throw new IllegalArgumentException("Valeurs invalides pour le calcul CAGR");
        }
        
        return Math.pow(finalValue / initialValue, 1.0 / years) - 1.0;
    }
    
    public static int findBestPerformingInvestment(double[] investments, double[] returns) {
        if (investments == null || returns == null || investments.length == 0) {
            throw new IllegalArgumentException("Données invalides");
        }
        
        if (investments.length != returns.length) {
            throw new IllegalArgumentException("Les tableaux doivent avoir la même taille");
        }
        
        int bestIndex = 0;
        double bestReturn = returns[0];
        
        for (int i = 1; i < returns.length; i++) {
            if (returns[i] > bestReturn) {
                bestReturn = returns[i];
                bestIndex = i;
            }
        }
        
        return bestIndex;
    }
    
    public static void main(String[] args) {
        // Tests avec résultats attendus
        double[] investments1 = {1000.0, 2000.0, 1500.0};
        double[] returns1 = {0.10, 0.15, -0.05};
        
        System.out.println("=== Solutions ===");
        System.out.println("Rendement total attendu: 8.57%");
        System.out.println("Rendement total calculé: " + (calculateTotalReturn(investments1, returns1) * 100) + "%");
        
        System.out.println("\nCAGR attendu: 14.47%");
        double cagr = calculateCAGR(10000.0, 15000.0, 3);
        System.out.println("CAGR calculé: " + (cagr * 100) + "%");
        
        System.out.println("\nMeilleur investissement attendu: indice 1 (15%)");
        System.out.println("Meilleur investissement calculé: " + findBestPerformingInvestment(investments1, returns1));
    }
}
