package frontiere;

import controleur.ControlLibererEtal;

public class BoundaryLibererEtal {
	private ControlLibererEtal controlLibererEtal;

	public BoundaryLibererEtal(ControlLibererEtal controlLibererEtal) {
		this.controlLibererEtal = controlLibererEtal;
	}

	public void libererEtal(String nomVendeur) {
		String[] donneesEtal = controlLibererEtal.libererEtal(nomVendeur);
        if (donneesEtal == null) {
            System.out.println("Mais vous n'êtes pas inscrit sur notre marché aujourd'hui !");
        } else {
            System.out.println("Le vendeur " + donneesEtal[1] + " quitte son étal, il a vendu "
                    + donneesEtal[4] + " parmi " + donneesEtal[3] + " " + donneesEtal[2] + ".");
        }
    }

}
