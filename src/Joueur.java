import java.util.Random;

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
     */
    public void afficherDes() {
        // Affichage des lancers des dés
        for (int index = 0; index < NB_DES; index++) {
            System.out.println("Dé " + (index + 1) + " : " + desEnMain[index]);
        }
    }

    /**
     * lancer des dés
     *
     */
    public void lancerDesDes(int nbLancers) {
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
     */
    public void relancerCertainsDes(String desARelancer) {
        for (String deARelancer : desARelancer.split(" ")) {
            desEnMain[Integer.parseInt(deARelancer) - 1] = lancerDe();
        }
    }

    /**
     * Calcule le score et ajoute ce score au score
     */
    public void ajouterScoreObtenu(){
        resultat.setNbPointsActuel(resultat.calculerScore(desEnMain));
        resultat.addScoreActuel();
    }
}
