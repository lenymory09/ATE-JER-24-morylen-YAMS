import java.util.Scanner;

public class Main {
    public static final int NB_DES = 5;
    public static final int NB_TENTATIVES_MAX = 3;
    public static final int NB_MANCHES = 5;

    /**
     * Point d'entrée du programme
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        Joueur joueur = new Joueur();
        Resultat resultat = new Resultat();

        // démarrage du jeu
        for (int nbManche = 1; nbManche <= NB_MANCHES; nbManche++) {

            // Affichage nombre Manche
            System.out.println("\nManche " + nbManche + ".");

            // Lancement des dés
            joueur.lancerDesDes(NB_DES);
            int nbTentativesRestantes = NB_TENTATIVES_MAX - 1;

            // affichage et relance des dés si demandé par l'utilisateur
            do {
                joueur.afficherDes();

                System.out.println("Il reste " + nbTentativesRestantes + " tentatives.");

                // Demande si il faut relancer les dés
                if (saisirChaine("Relancer [y/n] ? ").equals("y")) {
                    // Saisie et relance des dés.
                    joueur.relancerCertainsDes(saisirChaine("Quelles dés voulez-vous relancer ? "));
                    --nbTentativesRestantes;
                } else {
                    nbTentativesRestantes = 0;
                }
            } while (nbTentativesRestantes != 0);

            // Calcule et affichage des points de la manche
            resultat.setNbPointsActuel(resultat.calculerScore(joueur.desEnMain));
            System.out.println("Nombre de points obtenu : " + resultat.getNbPointsActuel());

            // Ajouts de ces points dans le score final
            resultat.setNbPointsFinal(resultat.getNbPointsFinal() + resultat.getNbPointsActuel());

            // affichage de ces points au nombre de points final
            System.out.println("Nombre de points final : " + resultat.getNbPointsFinal());

            // afficher Combinaisons restantes
            Combinaisons.afficherCombinaisonsRestantes(resultat.combinaisonsUtilisees);

            if (nbManche != NB_MANCHES) // si ce n'est pas la derniere manche
                saisirChaine("Appuyez sur entrée pour passer à la manche suivante.");
        }

        // Affichage finale des points
        System.out.println("Score final : " + resultat.getNbPointsFinal());
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
}
