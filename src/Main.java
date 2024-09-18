import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static final int NB_DES = 5;
    public static final int NBRE_FACES = 6;
    public static final int NB_TENTATIVES_MAX = 3;
    public static final int NB_DES_CONCECUTIFS_POUR_PETITE_SUITE = 4;
    public static final int NB_DES_CONCECUTIFS_GRANDE_SUITE = 5;

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

        int[] listeDesTest = {3, 5, 6, 3, 4};
        if (estGrandeSuite(listeDesTest)){
            System.out.println("C'est une grande suite !!!");
        } else if (estPetiteSuite(listeDesTest)){
            System.out.println("C'est une petite suite !!!");
        }

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

            } else if (listeDes[index] != listeDes[index - 1]){ // Teste qu'il n'y ait pas suite de deux nombre égaux (car peut quand meme être une petite suite)
                nbConcecutifs = 1;
            }
        }

        return false;
    }

    /**
     * Teste si la liste des dés est concécutifs
     * @param listeDes liste des dés à tester
     * @return si la liste est une grande suite ou pas.
     */
    private static boolean estGrandeSuite(int[] listeDes){
        boolean estGrandeSuite = true;
        Arrays.sort(listeDes);

        // teste si les dés de la liste sont concécutifs
        for (int index = 1; index < listeDes.length; index++){
            if (listeDes[index] != listeDes[index - 1] + 1) {
                estGrandeSuite = false;
                break;
            }
        }
        return estGrandeSuite;
    }
}