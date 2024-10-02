import java.util.Random;

/**
 * @author morylen
 */
public class Joueur {
    public static final int NB_DES = 5;
    public static final int NBRE_FACES = 6;

    int[] desEnMain = new int[5];
    Resultat resultat = new Resultat();



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
    public void afficherDes(int[] listeDes) {
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
    public int[] lancerDesDes(int nbLancers) {
        int[] listeDes = new int[nbLancers];
        for (int index = 0; index < nbLancers; index++) {
            listeDes[index] = lancerDe();
        }
        this.desEnMain = listeDes;
    }

    /**
     * Relance certains dés d'une liste de dés
     *
     * @param desARelancer liste des dés à relancer
     * @param listeDes     liste des dés actuelle
     */
    public void relancerCertainsDes(String desARelancer, int[] listeDes) {
        for (String deARelancer : desARelancer.split(" ")) {
            listeDes[Integer.parseInt(deARelancer) - 1] = lancerDe();
        }
    }

    /**
     * Calcule le score et ajoute ce score au score
     */
    public void ajouterScoreObtenu(){
        resultat.scoreActuelle = resultat.calculerScore(desEnMain);
        resultat.addScoreActuel();
    }
}
