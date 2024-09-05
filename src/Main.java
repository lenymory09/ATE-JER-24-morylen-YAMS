import java.util.Random;
import java.util.Scanner;

public class Main {
    public static final int NB_DES = 5;
    public static final int NUM_FACES_DE_MAX = 6;
    public static final int NB_TENTATIVES_MAX = 3;
    public static final String[] REPONSES_POSSIBLES = new String[]{"y", "n"};

    /**
     * Point d'entrée du programme
     * @param args args
     */
    public static void main(String[] args) {
        // Création du tableau et lancement des dés
        int[] listeDes = lancerDesDes(NB_DES);
        int nbTentativesRestantes = NB_TENTATIVES_MAX - 1;

        // Relancement des dés
        do {
            afficherDes(listeDes);

            System.out.println("Il reste " + nbTentativesRestantes + " tentatives.");
            // Demande si il faut relancer les dés
            if (saisirChaineParmiPlusieursChoix("Relancer [y/n] ? ", REPONSES_POSSIBLES).equals("y")) {
                // Saisie des dés à relancer
                String desARelancer = saisirChaine("Quelles dés voulez-vous relancer ? ");
                for (String deARelancer : desARelancer.split(" ")) {
                    listeDes[Integer.parseInt(deARelancer) - 1] = lancerDe();
                }
                --nbTentativesRestantes;
            } else {
                nbTentativesRestantes = 0;
            }
        } while (nbTentativesRestantes != 0);

        // Calcul et affichage de la somme des dés
        afficherDes(listeDes);
        System.out.println("Le total des dés est : " + calculerSommeDes(listeDes, NB_DES) + ".");
    }

    /**
     * Lance un dé
     * @return le numéro du lancer
     */
    private static int lancerDe() {
        Random random = new Random();
        return random.nextInt(NUM_FACES_DE_MAX) + 1;
    }

    /**
     * Affiche les des
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
     * @param listeDes liste des dés
     * @return la somme
     */
    private static int calculerSommeDes(int[] listeDes, int nbDes) {
        int totalDes = 0;
        for (int index = 0; index < nbDes; index++) {
            totalDes += listeDes[index];
        }
        return totalDes;
    }

    /**
     * Fait la saisie d'une chaine de caractères
     * @param prompt pour l'utilisateur
     * @return la saisie
     */
    private static String saisirChaine(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.nextLine();
    }

    /**
     * Demande la saisie de l'utilisateur d'apres plusieurs choix
     * @param prompt pour l'utilisateur
     * @param listeChoix liste des options de l'utilisateur
     * @return saisie de l'utilisateur
     */
    private static String saisirChaineParmiPlusieursChoix(String prompt, String[] listeChoix) {
        String saisie;
        boolean repeterSaisie = true;
        do {
            // Saisie de l'utilisateur
            System.out.print(prompt);
            Scanner scanner = new Scanner(System.in);
            saisie = scanner.nextLine();

            // Controle si saisie est valide
            for (String choix : listeChoix)
                if (choix.equals(saisie)) {
                    repeterSaisie = false;
                    break;
                }
            if (repeterSaisie)
                System.out.println("La saisie est invalide");
        } while (repeterSaisie);
        return saisie;
    }
}