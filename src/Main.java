import java.util.Random;
import java.util.Scanner;

public class Main {
    public static final int NB_DES = 5;
    public static final int NUM_FACES_DE_MAX = 6;
    public static final int NB_TENTATIVES_MAX = 3;

    /**
     * Point d'entrée du programme
     *
     * @param args args
     */
    public static void main(String[] args) {
        // Création du tableau et lancement des dés
        int[] listeDes = lancerDesDes(NB_DES);
        int nbTentativesRestantes = NB_TENTATIVES_MAX - 1;

        do {
            afficherDes(listeDes);

            System.out.println("Il reste " + nbTentativesRestantes + " tentatives.");
            // Demande si il faut relancer les dés
            if (saisirChaine("Relancer [y/n] ? ").equals("y")) {
                // Saisie des dés à relancer
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
        return random.nextInt(NUM_FACES_DE_MAX) + 1;
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
     * @param desARelancer liste des dés à relancer
     * @param listeDes liste des dés actuelle
     */
    private static void relancerCertainsDes(String desARelancer, int[] listeDes){
        for (String deARelancer : desARelancer.split(" ")) {
            listeDes[Integer.parseInt(deARelancer) - 1] = lancerDe();
        }
    }
}