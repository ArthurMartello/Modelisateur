package mode_L4.model.modele.maths;

import mode_L4.model.modele.Modele;
import mode_L4.model.modele.objets.Matrice;
import mode_L4.model.modele.objets.Point;
import mode_L4.model.modele.objets.Vecteur;

/**
 * Classe destinee a faire tourner tous les points d'un Modele en fonction de
 * differents angles
 *
 */
public class Rotation extends ModeleChanges {

	private double radianX;
	private double radianY;
	private double radianZ;

	/**
	 * Constructeur: definit un modele que l'on fera pivoter sur 0 � 3 angles
	 * 
	 * @param modele
	 *            Modele que l'on va modifier
	 * @param angleX
	 *            angle de rotation des points sur l'axe x
	 * @param angleY
	 *            angle de rotation des points sur l'axe y
	 * @param angleZ
	 *            angle de rotation des points sur l'axe z
	 */
	public Rotation(Modele modele, double angleX, double angleY, double angleZ) {

		super(modele);
		this.radianX = Math.toRadians(angleX);
		this.radianY = Math.toRadians(angleY);
		this.radianZ = Math.toRadians(angleZ);
		
		Point centre = modele.getCentre();
		
		new Translation(modele, new Vecteur(centre.getX(), centre.getY(), centre.getZ()));		
		tourner();		
		new Translation(modele, new Vecteur(-centre.getX(), -centre.getY(), -centre.getZ()));
	}

	private void tourner() {

		for (int i = 0; i < super.getModele().getListePoints().size(); i++) {

			Point p = super.getModele().getListePoints().get(i);
			
			if (radianX != 0) {
				p = rotationX(p);
			}
			if (radianY != 0) {
				p = rotationY(p);
			}
			if (radianZ != 0) {
				p = rotationZ(p);
			}
			super.getModele().getListePoints().get(i).set(p.getX(), p.getY(), p.getZ());
		}
		
		for (int i = 0; i < super.getModele().getListeFaces().size(); i++) {
			super.getModele().getListeFaces().get(i).setBarycentre();
		}
		
		super.getModele().trierListeFaces();
	}
	
	private Point rotationX(Point point) {
		
		Matrice matrice = new Matrice(new double[][]{
			{1, 0, 0},
			{0, Math.cos(radianX), -Math.sin(radianX)},
			{0, Math.sin(radianX), Math.cos(radianX)}
		});
				
		Matrice matricePoint = new Matrice(new double[1][3]);				
		matricePoint.getTableau()[0] = point.getTableau();
		
		double[][] total = matricePoint.multiplier(matrice).getTableau();
		
		return new Point(total[0][0], total[0][1], total[0][2]);
	}
	
	private Point rotationY(Point point) {
		
		Matrice matrice = new Matrice(new double[][]{
			{Math.cos(radianY), 0, Math.sin(radianY)},
			{0, 1, 0},
			{-Math.sin(radianY), 0, Math.cos(radianY)}
		});
		
		Matrice matricePoint = new Matrice(new double[1][3]);
		matricePoint.getTableau()[0] = point.getTableau();
		
		double[][] total = matricePoint.multiplier(matrice).getTableau();
		
		return new Point(total[0][0], total[0][1], total[0][2]);
	}
	
	private Point rotationZ(Point point) {
		
		Matrice matrice = new Matrice(new double[][]{
			{Math.cos(radianZ), -Math.sin(radianZ), 0},
			{Math.sin(radianZ), Math.cos(radianZ), 0},
			{0, 0, 1}
		});
		
		Matrice matricePoint = new Matrice(new double[1][3]);
		matricePoint.getTableau()[0] = point.getTableau();
		
		double[][] tab = matricePoint.multiplier(matrice).getTableau();
		
		return new Point(tab[0][0], tab[0][1], tab[0][2]);
	}
	
}
