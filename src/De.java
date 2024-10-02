public class De {
    private int faceVisible = 1;

    int nbreFaces = 6;


    /**
     * construit un dé avec la face visible et le nombre
     * de faces passé en paramères
     *
     * @param faceVisible face visible du dé
     * @param nbreFaces   nbre de faces du dé
     */
    public De(int faceVisible, int nbreFaces) {
        this.faceVisible = faceVisible;
        this.nbreFaces = nbreFaces;
    }

    /**
     * construit un dé avec le nombre de faces passé en parametre
     *
     * @param nbreFaces du dé
     */
    public De(int nbreFaces) {
        this.nbreFaces = nbreFaces;
    }

    /**
     * construit un dé avec les valeurs par défaut
     */
    public De() {
    }

    public int getFaceVisible() {
        return faceVisible;
    }

    public int getNbreFaces() {
        return nbreFaces;
    }

    public void setFaceVisible(int faceVisible) {
        if (faceVisible <= nbreFaces && faceVisible > 0)
            this.faceVisible = faceVisible;
    }

    public void setNbreFaces(int nbreFaces) {
        if (nbreFaces > 0)
            this.nbreFaces = nbreFaces;
    }

    /**
     * Teste si un dé est égal a un autre par rapport au nombre de face et au nombre sur la face visible
     * @return vrai si le nombre de face et la face visible sont égaux
     */
    @Override
    public boolean equals(Object autreObjet){
        return autreObjet instanceof De &&
                this.nbreFaces == ((De) autreObjet).nbreFaces &&
                this.faceVisible == ((De) autreObjet).faceVisible;
    }


    public void lancer(){
        setFaceVisible((int) (Math.random()*6 + 1));
    }
}
