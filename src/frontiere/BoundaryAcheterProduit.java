package frontiere;

import controleur.ControlAcheterProduit;

public class BoundaryAcheterProduit {
	private ControlAcheterProduit controlAcheterProduit;

	public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
		this.controlAcheterProduit = controlAcheterProduit;
	}

	public void acheterProduit(String nomAcheteur) {
		if (!controlAcheterProduit.verifierIdentite(nomAcheteur)) {
			System.out.println("Je suis désolée " + nomAcheteur
					+ " mais il faut être un habitant de notre village pour commercer ici.");
		} else {
			String produit = Clavier.entrerChaine("Quel produit souhaitez-vous acheter ?");
			String[] nomsVendeurs = controlAcheterProduit.rechercherVendeursProduit(produit);
			if (nomsVendeurs == null || nomsVendeurs.length == 0) {
				System.out.println("Désolé, personne ne vend ce produit au marché.");
			} else {
				interagirAvecVendeurs(nomAcheteur, produit, nomsVendeurs);
			}
		}
	}

	private void interagirAvecVendeurs(String nomAcheteur, String produit, String[] nomsVendeurs) {
		afficherListeVendeurs(produit, nomsVendeurs);
		int choixVendeur = Clavier.entrerEntier("");
		if (choixVendeur < 1 || choixVendeur > nomsVendeurs.length) {
			System.out.println("Choix invalide.");
		} else {
			String nomVendeur = nomsVendeurs[choixVendeur - 1];
			procederTransaction(nomAcheteur, nomVendeur, produit);
		}
	}

	private void afficherListeVendeurs(String produit, String[] nomsVendeurs) {
		System.out.println("Chez quel commerçant voulez-vous acheter des " + produit + " ?");
		for (int i = 0; i < nomsVendeurs.length; i++) {
			System.out.println((i + 1) + " - " + nomsVendeurs[i]);
		}
	}

	private void procederTransaction(String nomAcheteur, String nomVendeur, String produit) {
		System.out.println(nomAcheteur + " se déplace à l'étal de " + nomVendeur);
		int quantiteSouhaitee = Clavier.entrerEntier("Combien de " + produit + " voulez-vous acheter ?");
		int quantiteAchetee = controlAcheterProduit.acheterProduit(nomVendeur, quantiteSouhaitee);
		afficherMessageConfirmation(nomAcheteur, nomVendeur, produit, quantiteSouhaitee, quantiteAchetee);
	}

	private void afficherMessageConfirmation(String nomAcheteur, String nomVendeur, String produit,
			int quantiteSouhaitee, int quantiteAchetee) {
		if (quantiteAchetee == 0) {
			System.out.println(nomAcheteur + " veut acheter " + quantiteSouhaitee + " " + produit
					+ ", malheureusement il n'y en a plus !");
		} else if (quantiteAchetee < quantiteSouhaitee) {
			System.out.println(nomAcheteur + " veut acheter " + quantiteSouhaitee + " " + produit + ", malheureusement "
					+ nomVendeur + " n'en a plus que " + quantiteAchetee + ".");
			System.out.println(nomAcheteur + " achète tout le stock de " + nomVendeur + ".");
		} else {
			System.out.println(nomAcheteur + " achète " + quantiteAchetee + " " + produit + " à " + nomVendeur + ".");
		}
	}
}
