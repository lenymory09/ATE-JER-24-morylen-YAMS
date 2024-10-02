import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * @author morylen
 */
public class Main {
    public static final int NB_DES = 5;
    public static final int NBRE_FACES = 6;
    public static final int NB_TENTATIVES_MAX = 3;
    public static final int NB_DES_CONCECUTIFS_POUR_PETITE_SUITE = 4;
    public static final int NB_MANCHES = 5;


    public static final String[] COMBINAISONS_STRING = {"BRELAN     ", "CARRÉ      ", "FULL       ", "PETITE SUITE", "GRANDE SUITE", "YAHTZEE    ", "CHANCE     "};






    /**
     * Point d'entrée du programme
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        Joueur joueur = new Joueur();
        joueur.lancerDesDes(NB_DES);

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
     * affiche le nombre de dés qui montre chaque face
     *
     * @param nbreDesParFaceVisible nbre de dés qui montre chaque face
     */
    private static void afficherNbreFacesVisibles(int[] nbreDesParFaceVisible) {
        for (int index = 0; index < nbreDesParFaceVisible.length; index++) {
            System.out.println("Nombre de " + (index + 1) + " : " + nbreDesParFaceVisible[index]);
        }
    }
}
