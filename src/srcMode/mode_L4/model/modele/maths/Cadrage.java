package mode_L4.model.modele.maths;

import mode_L4.model.modele.Modele;
import mode_L4.model.modele.objets.Point;
import mode_L4.model.modele.objets.Vecteur;

public class Cadrage extends ModeleChanges {

	private final double margeZoom = 0.15;
	private final int tailleX;
	private final int tailleY;
	private boolean cadre = false;

	public Cadrage(Modele modele, int x, int y) {
		super(modele);
		this.tailleX = x;
		this.tailleY = y;
		if (modele.getListePoints().size() > 1)
			cadrer();
	}
	
	private boolean estCadre() {
		return this.cadre;
	}

	private void cadrer() {

		while (!estCadre()) {

			Point centre = super.getModele().getCentre();
			new Translation(super.getModele(), new Vecteur(centre.getX(), centre.getY(), centre.getZ()));

			// Hauteur
			double haut = super.getModele().minY();
			double bas = super.getModele().maxY();
			double tailleModY = (Math.abs(haut) / (1 - margeZoom) + Math.abs(bas) / (1 - margeZoom));
			double propY = tailleModY / this.tailleY;

			// Largeur
			double gauche = super.getModele().minX();
			double droite = super.getModele().maxX();
			double tailleModX = (Math.abs(gauche) / (1 - margeZoom) + Math.abs(droite) / (1 - margeZoom));
			double propX = tailleModX / this.tailleX;

			if (propY > 0.99 && propY < 1.01 || propX > 0.99 && propX < 1.01) {
				this.cadre = true;
				
			} else {

				if (propY > propX) {
					if (propY < 1) {
						new Zoom(super.getModele(), 1 + propY);
					} else {
						new Zoom(super.getModele(), 1 / (propY));
					}

				} else {

					if (propX < 1) {
						new Zoom(super.getModele(), 1 + propX);
					} else {
						new Zoom(super.getModele(), 1 / (propX));
					}
				}
				
			}
		}
	}

}
