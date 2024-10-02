public class Resultat {
    public static final int COMBINAISON_BRELAN = 0;
    public static final int COMBINAISON_CARRE = 1;
    public static final int COMBINAISON_FULL = 2;
    public static final int COMBINAISON_PETITE_SUITE = 3;
    public static final int COMBINAISON_GRANDE_SUITE = 4;
    public static final int COMBINAISON_YAHTZEE = 5;
    public static final int COMBINAISON_CHANCE = 6;
    public static final int NB_POINTS_FULL = 25;
    public static final int NB_POINTS_PETITE_SUITE = 30;
    public static final int NB_POINTS_GRANDE_SUITE = 40;
    public static final int NB_POINTS_YAHTZEE = 50;
    public static final int NBRE_FACES = 6;
    public static final int NB_MEMES_FACE_BRELAN = 3;
    public static final int NB_MEMES_FACE_CARRE = 4;

    private int nbPointsFinal = 0;
    private int nbPointsActuel = 0;
    boolean[] combinaisonsUtilisees = {false, false, false, false, false, false, false};

    public int getNbPointsFinal() {
        return nbPointsFinal;
    }

    public void setNbPointsActuel(int nbPointsActuel) {
        this.nbPointsActuel = nbPointsActuel;
    }

    public int getNbPointsActuel() {
        return nbPointsActuel;
    }

    /**
     * calcule la somme
     *
     * @param listeDes liste des dés
     * @return la somme
     */
    private static int calculerSommeDes(int[] listeDes) {
        int totalDes = 0;
        for (int listeDe : listeDes) {
            totalDes += listeDe;
        }
        return totalDes;
    }

    /**
     * Compte le nombre de dés qui affiche chaque face
     *
     * @param listeDes liste des dés
     */
    private static int[] compterChaqueNbreFaces(int[] listeDes) {
        int[] nbDesParFacesVisibles = new int[NBRE_FACES];

        for (int de : listeDes) {
            nbDesParFacesVisibles[de - 1]++;
        }

        return nbDesParFacesVisibles;
    }

    /**
     * Calcule le nombre de points si il y a un brelan
     *
     * @param nbreDesParFaceVisible nbre de dés qui montre chaque face visible
     * @return le nombre de points obtenu avec le brelan.
     */
    private static int calculerNbrePointsBrelan(int[] nbreDesParFaceVisible) {
        for (int index = nbreDesParFaceVisible.length - 1; index >= 0; index--) {
            if (nbreDesParFaceVisible[index] == NB_MEMES_FACE_BRELAN)
                return (index + 1) * NB_MEMES_FACE_BRELAN;
        }
        return 0;
    }

    /**
     * Calcule le nombre de points si il y a un brelan
     *
     * @param nbreDesParFaceVisible nbre de dés qui montre chaque face visible
     * @return le nombre de points obtenu avec le brelan.
     */
    private static int calculerNbrePointsCarre(int[] nbreDesParFaceVisible) {
        for (int index = 0; index < nbreDesParFaceVisible.length; index++)
            if (nbreDesParFaceVisible[index] == NB_MEMES_FACE_CARRE)
                return (index + 1) * NB_MEMES_FACE_CARRE;

        return 0;
    }

    /**
     * Trouve la combinaison des dés, affiche la combinaison et retourne le nombre de points.
     * @param listeDes              liste des dés à trouvé
     * @return le score obtenu d'apres la combinaison
     */
    int calculerScore(int[] listeDes) {
        int nbPointsObtenu = 0;
        int[] nbreDesParFaceVisible = compterChaqueNbreFaces(listeDes);

        if (Combinaisons.estYatzee(listeDes) && !combinaisonsUtilisees[COMBINAISON_YAHTZEE]) {
            nbPointsObtenu = NB_POINTS_YAHTZEE;
            combinaisonsUtilisees[COMBINAISON_YAHTZEE] = true;

        } else if (Combinaisons.estGrandeSuite(listeDes) && !combinaisonsUtilisees[COMBINAISON_GRANDE_SUITE]) {
            nbPointsObtenu = NB_POINTS_GRANDE_SUITE;
            combinaisonsUtilisees[COMBINAISON_GRANDE_SUITE] = true;

        } else if (Combinaisons.estPetiteSuite(listeDes) && !combinaisonsUtilisees[COMBINAISON_PETITE_SUITE]) {
            nbPointsObtenu = NB_POINTS_PETITE_SUITE;
            combinaisonsUtilisees[COMBINAISON_PETITE_SUITE] = true;

        } else if (Combinaisons.estCarre(nbreDesParFaceVisible) && !combinaisonsUtilisees[COMBINAISON_CARRE]) {
            nbPointsObtenu = calculerNbrePointsCarre(nbreDesParFaceVisible);
            combinaisonsUtilisees[COMBINAISON_CARRE] = true;

        } else if (Combinaisons.estFull(nbreDesParFaceVisible) && !combinaisonsUtilisees[COMBINAISON_FULL]) {
            nbPointsObtenu = NB_POINTS_FULL;
            combinaisonsUtilisees[COMBINAISON_FULL] = true;

        } else if (Combinaisons.estBrelan(nbreDesParFaceVisible) && !combinaisonsUtilisees[COMBINAISON_BRELAN]) {
            nbPointsObtenu = calculerNbrePointsBrelan(nbreDesParFaceVisible);
            combinaisonsUtilisees[COMBINAISON_BRELAN] = true;

        } else if (!combinaisonsUtilisees[COMBINAISON_CHANCE]) {
            nbPointsObtenu = calculerSommeDes(listeDes);
            combinaisonsUtilisees[COMBINAISON_CHANCE] = true;

        }

        return nbPointsObtenu;
    }

    public void addScoreActuel(){
        nbPointsFinal += nbPointsActuel;
    }
}
