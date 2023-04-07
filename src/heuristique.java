public class heuristique {

    public static void main(String[] args) {
        int n = 8;
        int[] positions = new int[n];
        placerReines(positions, 0, n);
    }

    public static boolean estValide(int[] positions, int ligne, int colonne) {
        for (int i = 0; i < ligne; i++) {
            if (positions[i] == colonne ||
                    positions[i] - i == colonne - ligne ||
                    positions[i] + i == colonne + ligne) {
                return false;
            }
        }
        return true;
    }

    public static void nbSolution(int[] positions, int ligne , int colonne ){
        if(ligne == 0 ){

        }


    }
    public static void placerReines(int[] positions, int ligne, int n) {
        if (ligne == n) {
            afficherPositions(positions);
        } else {
            int meilleureColonne = -1;
            int meilleureNbLibres = -1;
            for (int colonne = 0; colonne < n; colonne++) {
                int nbLibres = nbCasesLibres(positions, ligne, colonne);
                if (nbLibres > meilleureNbLibres) {
                    meilleureColonne = colonne;
                    meilleureNbLibres = nbLibres;
                }
            }
            positions[ligne] = meilleureColonne;
            placerReines(positions, ligne+1, n);
        }
    }

    public static int nbCasesLibres(int[] positions, int ligne, int colonne) {
        int nbLibres = 0;
        for (int i = ligne+1; i < positions.length; i++) {
            if (estValide(positions, i, colonne)) {
                nbLibres++;
            }
        }
        return nbLibres;
    }

    public static void afficherPositions(int[] positions) {
        for (int i = 0; i < positions.length; i++) {
            for (int j = 0; j < positions.length; j++) {
                if (positions[i] == j) {
                    System.out.print("S ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}