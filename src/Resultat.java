public class Resultat {
    int nbScore = 0;
    public static final int COMBINAISON_BRELAN = 0;
    public static final int COMBINAISON_CARRE = 1;
    public static final int COMBINAISON_FULL = 2;
    public static final int COMBINAISON_PETITE_SUITE = 3;
    public static final int COMBINAISON_GRANDE_SUITE = 4;
    public static final int COMBINAISON_YAHTZEE = 5;
    public static final int COMBINAISON_CHANCE = 6;

    boolean[] combinaisonsUtilisees = {false, false, false, false, false, false, false};

    public int obtenirScore(De[] listeDes){

    }


    /**
     * Trouve la combinaison des dés, affiche la combinaison et retourne le nombre de points.
     *
     * @param combinaisonsUtilisees Combinaisons de dés déja trouvés
     * @param listeDes              liste des dés à trouvé
     * @return le score obtenu d'apres la combinaison
     */
    private static int calculerEtAfficherScoreObtenu(boolean[] combinaisonsUtilisees, int[] listeDes) {
        int nbPointsObtenu = 0;
        int[] nbreDesParFaceVisible = Combinaisons.compterChaqueNbreFaces(listeDes);

        if (estYatzee(listeDes) && !combinaisonsUtilisees[COMBINAISON_YAHTZEE]) {
            nbPointsObtenu = NB_POINTS_YAHTZEE;
            combinaisonsUtilisees[COMBINAISON_YAHTZEE] = true;
            System.out.println("C'est un Yahtzee.");

        } else if (estGrandeSuite(listeDes) && !combinaisonsUtilisees[COMBINAISON_GRANDE_SUITE]) {
            nbPointsObtenu = NB_POINTS_GRANDE_SUITE;
            combinaisonsUtilisees[COMBINAISON_GRANDE_SUITE] = true;
            System.out.println("C'est une grande suite.");

        } else if (estPetiteSuite(listeDes) && !combinaisonsUtilisees[COMBINAISON_PETITE_SUITE]) {
            nbPointsObtenu = NB_POINTS_PETITE_SUITE;
            combinaisonsUtilisees[COMBINAISON_PETITE_SUITE] = true;
            System.out.println("C'est une petite suite.");

        } else if (estCarre(nbreDesParFaceVisible) && !combinaisonsUtilisees[COMBINAISON_CARRE]) {
            nbPointsObtenu = calculerNbrePointsCarre(nbreDesParFaceVisible);
            combinaisonsUtilisees[COMBINAISON_CARRE] = true;
            System.out.println("C'est un carré.");

        } else if (Combinaisons.estFull(nbreDesParFaceVisible) && !combinaisonsUtilisees[COMBINAISON_FULL]) {
            nbPointsObtenu = NB_POINTS_FULL;
            combinaisonsUtilisees[COMBINAISON_FULL] = true;
            System.out.println("C'est un full.");

        } else if (Combinaisons.estBrelan(nbreDesParFaceVisible) && !combinaisonsUtilisees[COMBINAISON_BRELAN]) {
            nbPointsObtenu = calculerNbrePointsBrelan(nbreDesParFaceVisible);
            combinaisonsUtilisees[COMBINAISON_BRELAN] = true;
            System.out.println("C'est un brelan.");

        } else if (!combinaisonsUtilisees[COMBINAISON_CHANCE]) {
            nbPointsObtenu = calculerSommeDes(listeDes);
            combinaisonsUtilisees[COMBINAISON_CHANCE] = true;
            System.out.println("Pas de combinaison trouvé, c'est la chance.");

        } else {
            System.out.println("Pas de combinaison trouvé.");
        }

        return nbPointsObtenu;
    }
}
