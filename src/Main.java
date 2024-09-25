import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static final int NB_DES = 5;
    public static final int NBRE_FACES = 6;
    public static final int NB_TENTATIVES_MAX = 3;
    public static final int NB_DES_CONCECUTIFS_POUR_PETITE_SUITE = 4;
    public static final int NB_MANCHES = 5;

    public static final int COMBINAISON_BRELAN = 0;
    public static final int COMBINAISON_CARRE = 1;
    public static final int COMBINAISON_FULL = 2;
    public static final int COMBINAISON_PETITE_SUITE = 3;
    public static final int COMBINAISON_GRANDE_SUITE = 4;
    public static final int COMBINAISON_YAHTZEE = 5;
    public static final int COMBINAISON_CHANCE = 6;
    public static final String[] COMBINAISONS_STRING = {"BRELAN     ", "CARRÉ      ", "FULL       ", "PETITE SUITE", "GRANDE SUITE", "YAHTZEE    ", "CHANCE     "};

    public static final int NB_MEMES_FACE_BRELAN = 3;
    public static final int NB_MEMES_FACE_CARRE = 4;

    public static final int NB_POINTS_FULL = 25;
    public static final int NB_POINTS_PETITE_SUITE = 30;
    public static final int NB_POINTS_GRANDE_SUITE = 40;
    public static final int NB_POINTS_YAHTZEE = 50;


    /**
     * Point d'entrée du programme
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        boolean[] combinaisonsUtilisees = {false, false, false, false, false, false, false};
        int[] listeDes;
        int nbPointsTotal = 0;

        // démarrage du jeu
        for (int nbManche = 1; nbManche <= NB_MANCHES; nbManche++) {

            // Affichage nombre Manche
            System.out.println("\n\nManche " + nbManche + ".");

            // Lancement des dés
            listeDes = lancerDesDes(NB_DES);
            int nbTentativesRestantes = NB_TENTATIVES_MAX - 1;

            // affichage et relance des dés si demandé par l'utilisateur
            do {
                afficherDes(listeDes);

                System.out.println("Il reste " + nbTentativesRestantes + " tentatives.");

                // Demande si il faut relancer les dés
                if (saisirChaine("Relancer [y/n] ? ").equals("y")) {
                    // Saisie et relance des dés.
                    relancerCertainsDes(saisirChaine("Quelles dés voulez-vous relancer ? "), listeDes);
                    --nbTentativesRestantes;
                } else {
                    nbTentativesRestantes = 0;
                }
            } while (nbTentativesRestantes != 0);

            // afficher Combinaisons restantes
            afficherCombinaisonsRestantes(combinaisonsUtilisees);

            // Calcule et affichage des points de la manche
            int nbPointsObtenu = calculerEtAfficherScoreObtenu(combinaisonsUtilisees, listeDes);
            System.out.println("Nombre de points obtenu : " + nbPointsObtenu);

            // Ajouts et affichage de ces points au nombre de points final
            nbPointsTotal += nbPointsObtenu;
            System.out.println("\nNombre de points final : " + nbPointsTotal);

            if (nbManche != NB_MANCHES) // si ce n'est pas la derniere manche
                saisirChaine("Appuyez sur entrée pour passer à la manche suivante.");
        }

        // Affichage des combinaisons restantes
        afficherCombinaisonsRestantes(combinaisonsUtilisees);

        // Affichage finale des points
        System.out.println("Score final : " + nbPointsTotal);
    }

    /**
     * Lance un dé
     *
     * @return le numéro du lancer
     */
    private static int lancerDe() {
        Random random = new Random();
        return random.nextInt(NBRE_FACES) + 1;
    }

    /**
     * Affiche les des
     *
     * @param listeDes liste des dés
     */
    private static void afficherDes(int[] listeDes) {
        // Affichage des lancers des dés
        for (int index = 0; index < NB_DES; index++) {
            System.out.println("Dé " + (index + 1) + " : " + listeDes[index]);
        }
    }

    /**
     * lancer des dés
     *
     * @return les lancers de dés
     */
    private static int[] lancerDesDes(int nbLancers) {
        int[] listeDes = new int[nbLancers];
        for (int index = 0; index < nbLancers; index++) {
            listeDes[index] = lancerDe();
        }
        return listeDes;
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
     * Fait la saisie d'une chaine de caractères
     *
     * @param prompt pour l'utilisateur
     * @return la saisie
     */
    private static String saisirChaine(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.nextLine();
    }

    /**
     * Relance certains dés d'une liste de dés
     *
     * @param desARelancer liste des dés à relancer
     * @param listeDes     liste des dés actuelle
     */
    private static void relancerCertainsDes(String desARelancer, int[] listeDes) {
        for (String deARelancer : desARelancer.split(" ")) {
            listeDes[Integer.parseInt(deARelancer) - 1] = lancerDe();
        }
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
     * affiche le nombre de dés qui montre chaque face
     *
     * @param nbreDesParFaceVisible nbre de dés qui montre chaque face
     */
    private static void afficherNbreFacesVisibles(int[] nbreDesParFaceVisible) {
        for (int index = 0; index < nbreDesParFaceVisible.length; index++) {
            System.out.println("Nombre de " + (index + 1) + " : " + nbreDesParFaceVisible[index]);
        }
    }

    /**
     * teste si une liste de dés représente une petite suite.
     *
     * @param listeDes liste des dés a tester
     * @return si c'est une petite suite ou pas.
     */
    private static boolean estPetiteSuite(int[] listeDes) {
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
    private static boolean estGrandeSuite(int[] listeDes) {
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
    private static boolean estYatzee(int[] listeDes) {
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
    private static boolean estCarre(int[] nbreDesParFaceVisible) {
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
    private static boolean estBrelan(int[] nbreDesParFaceVisible) {
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
    private static boolean estFull(int[] nbreDesParFaceVisible) {
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
     * Trouve la combinaison des dés, affiche la combinaison et retourne le nombre de points.
     *
     * @param combinaisonsUtilisees Combinaisons de dés déja trouvés
     * @param listeDes              liste des dés à trouvé
     * @return le score obtenu d'apres la combinaison
     */
    private static int calculerEtAfficherScoreObtenu(boolean[] combinaisonsUtilisees, int[] listeDes) {
        int nbPointsObtenu = 0;
        int[] nbreDesParFaceVisible = compterChaqueNbreFaces(listeDes);

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

        } else if (estFull(nbreDesParFaceVisible) && !combinaisonsUtilisees[COMBINAISON_FULL]) {
            nbPointsObtenu = NB_POINTS_FULL;
            combinaisonsUtilisees[COMBINAISON_FULL] = true;
            System.out.println("C'est un full.");

        } else if (estBrelan(nbreDesParFaceVisible) && !combinaisonsUtilisees[COMBINAISON_BRELAN]) {
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
     * affiche un talbeau contenant les combinaisons et si elles ont déja été trouvé
     *
     * @param combinaisonsRestantes tableau contenant les combinaisons déja utilisé
     */
    private static void afficherCombinaisonsRestantes(boolean[] combinaisonsRestantes) {
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
