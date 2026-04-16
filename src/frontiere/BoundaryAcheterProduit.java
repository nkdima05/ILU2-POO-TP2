package frontiere;

import controleur.ControlAcheterProduit;
import personnages.Gaulois;

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
			Gaulois[] vendeurs = controlAcheterProduit.rechercherVendeursProduit(produit);
			if (vendeurs == null || vendeurs.length == 0) {
				System.out.println("Désolé, personne ne vend ce produit au marché.");
			} else {
				System.out.println("Chez quel commerçant voulez-vous acheter des " + produit + " ?");
				for (int i = 0; i < vendeurs.length; i++) {
					System.out.println((i + 1) + " - " + vendeurs[i].getNom());
				}
				int choixVendeur = Clavier.entrerEntier("");
				if (choixVendeur < 1 || choixVendeur > vendeurs.length) {
					System.out.println("Choix invalide.");
				} else {
					Gaulois vendeurChoisi = vendeurs[choixVendeur - 1];
					String nomVendeur = vendeurChoisi.getNom();
					System.out.println(nomAcheteur + " se déplace à l'étal de " + nomVendeur);
					int quantiteSouhaitee = Clavier.entrerEntier("Combien de " + produit + " voulez-vous acheter ?");
					int quantiteAchetee = controlAcheterProduit.acheterProduit(nomVendeur, quantiteSouhaitee);
					if (quantiteAchetee == 0) {
						System.out.println(nomAcheteur + " veut acheter " + quantiteSouhaitee + " " + produit 
										+ ", malheureusement il n'y en a plus !");
					} else if (quantiteAchetee < quantiteSouhaitee) {
						System.out.println(nomAcheteur + " veut acheter " + quantiteSouhaitee + " " + produit 
										+ ", malheureusement " + nomVendeur + " n'en a plus que " + quantiteAchetee + ".");
						System.out.println(nomAcheteur + " achète tout le stock de " + nomVendeur + ".");
					} else {
						System.out.println(nomAcheteur + " achète " + quantiteAchetee + " " + produit + " à " + nomVendeur + ".");
					}
				}
			}
		}
	}
}	
				
			
	

