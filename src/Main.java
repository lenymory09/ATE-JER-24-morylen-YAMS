import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static final int NB_DES = 5;
    public static final int NBRE_FACES = 6;
    public static final int NB_TENTATIVES_MAX = 3;

    /**
     * Point d'entrée du programme
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        // Création du tableau et lancement des dés
        int[] listeDes = lancerDesDes(NB_DES);
        int nbTentativesRestantes = NB_TENTATIVES_MAX - 1;

        // compter le nombre de chaque face
        int[] nbChiffre = new int[NBRE_FACES];
        compterChaqueNbreFaces(listeDes, nbChiffre);
        afficherNbreFacesVisibles(nbChiffre);

        int[] listeDesTest = {1, 2, 3, 4, 5};

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

        // Calcul et affichage de la somme des dés
        afficherDes(listeDes);
        System.out.println("Le total des dés est : " + calculerSommeDes(listeDes) + ".");
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
     * @param listeDes              liste des dés
     * @param nbreDesParFaceVisible nbre de dés qui montre chaque faces
     */
    private static void compterChaqueNbreFaces(int[] listeDes, int[] nbreDesParFaceVisible) {
        int[] nbChiffreTemp = new int[NBRE_FACES];

        for (int de : listeDes) {
            nbreDesParFaceVisible[de - 1]++;
        }
        nbreDesParFaceVisible = nbChiffreTemp;
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
     * Test si des dés représente un Yatzee
     *
     * @param listeDes à tester
     * @return vrai si yatzee et faux si pas yatzee
     */
    private static boolean estYatzee(int[] listeDes) {
        boolean estYatzee = true;
        for (int listeDe : listeDes) {
            if (listeDe != listeDes[0])
                estYatzee = false;
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
        boolean estCarre = false;
        for (int nbreFaces : nbreDesParFaceVisible) {
            if (nbreFaces == 4)
                estCarre = true;
        }

        return estCarre;
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
            if (nbDes == 3)
                estBrelan = true;
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


    private static boolean estPetiteSuite(int[] listeDes) {
        int[] listeDesTemp = listeDes;
        boolean estPetiteSuite = true;

        Arrays.sort(listeDesTemp); // Triage de la liste des dés

        for (int index = 0; index < listeDesTemp.length; index++) {
            if (listeDesTemp[index] != index + 1 && listeDesTemp[index] != index + 2 && listeDesTemp[index] != index + 3) {
                estPetiteSuite = false;
            }
        }
        return estPetiteSuite;
    }
}