import java.util.Arrays;

public class Combinaisons {
    private static final int NB_DES_CONCECUTIFS_POUR_PETITE_SUITE = 4;
    public static final String[] COMBINAISONS_STRING = {"BRELAN     ", "CARRÉ      ", "FULL       ", "PETITE SUITE", "GRANDE SUITE", "YAHTZEE    ", "CHANCE     "};

    /**
     * teste si une liste de dés représente une petite suite.
     *
     * @param listeDes liste des dés a tester
     * @return si c'est une petite suite ou pas.
     */
    public static boolean estPetiteSuite(int[] listeDes) {
        int nbConcecutifs = 1;
        Arrays.sort(listeDes);

        // comptage du nombre de dés concécutifs
        for (int index = 1; index < listeDes.length; index++) {
            if (listeDes[index] == listeDes[index - 1] + 1) { // teste si le dés précédent est concécutif au dé actuel
                nbConcecutifs++;

                // Teste si il y a une petite suite
                if (nbConcecutifs == NB_DES_CONCECUTIFS_POUR_PETITE_SUITE)
                    return true;

            } else if (listeDes[index] != listeDes[index - 1]) { // Teste qu'il n'y ait pas suite de deux nombre égaux (car peut quand meme être une petite suite)
                nbConcecutifs = 1;
            }
        }

        return false;
    }

    /**
     * Teste si la liste des dés est concécutifs
     *
     * @param listeDes liste des dés à tester
     * @return si la liste est une grande suite ou pas.
     */
    public static boolean estGrandeSuite(int[] listeDes) {
        boolean estGrandeSuite = true;
        Arrays.sort(listeDes);

        // teste si les dés de la liste sont concécutifs
        for (int index = 1; index < listeDes.length; index++) {
            if (listeDes[index] != listeDes[index - 1] + 1) {
                estGrandeSuite = false;
                break;
            }
        }
        return estGrandeSuite;
    }

    /**
     * Test si des dés représente un Yatzee
     *
     * @param listeDes à tester
     * @return vrai si yatzee et faux si pas yatzee
     */
    public static boolean estYatzee(int[] listeDes) {
        boolean estYatzee = true;
        for (int listeDe : listeDes) {
            if (listeDe != listeDes[0]) {
                estYatzee = false;
                break;
            }
        }

        return estYatzee;
    }

    /**
     * teste si une suite de dés représente un carré
     *
     * @param nbreDesParFaceVisible nbre de de dés pour chaque face visible
     * @return si la suite de dés représente un carré
     */
    public static boolean estCarre(int[] nbreDesParFaceVisible) {
        for (int nbreFaces : nbreDesParFaceVisible) {
            if (nbreFaces == 4) {
                return true;
            }
        }

        return false;
    }

    /**
     * Teste si une suite de dés représente un brelan (3 dés de la meme face)
     *
     * @param nbreDesParFaceVisible nbre de dés pour chaque face visible
     * @return si la suite de dé est brelan
     */
    public static boolean estBrelan(int[] nbreDesParFaceVisible) {
        boolean estBrelan = false;
        for (int nbDes : nbreDesParFaceVisible) {
            if (nbDes == 3) {
                estBrelan = true;
                break;
            }
        }
        return estBrelan;
    }

    /**
     * teste si une suite de dés représente un full
     *
     * @param nbreDesParFaceVisible nombre de dés qui montre chaque face visible
     * @return si la suite de dés représente un full.
     */
    public static boolean estFull(int[] nbreDesParFaceVisible) {
        boolean aUnePair = false;
        boolean aUnTriple = false;

        for (int nbDes : nbreDesParFaceVisible) {
            if (nbDes == 3) {
                aUnTriple = true;
            } else if (nbDes == 2) {
                aUnePair = true;
            }
        }
        return aUnTriple && aUnePair;
    }

    /**
     * affiche un talbeau contenant les combinaisons et si elles ont déja été trouvé
     *
     * @param combinaisonsRestantes tableau contenant les combinaisons déja utilisé
     */
    public static void afficherCombinaisonsRestantes(boolean[] combinaisonsRestantes) {
        System.out.println("COMBINAISONS RESTANTES :" +
                "\n-----------------");
        for (int index = 0; index < combinaisonsRestantes.length; index++) {
            System.out.print(COMBINAISONS_STRING[index] + "  \t | ");
            if (combinaisonsRestantes[index]) {
                System.out.print("X");
            }
            System.out.print("\n");
        }
        System.out.println("-----------------");
    }
}
