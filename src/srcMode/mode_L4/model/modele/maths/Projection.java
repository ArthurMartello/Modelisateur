package mode_L4.model.modele.maths;

import mode_L4.model.modele.Modele;
import mode_L4.model.modele.objets.Matrice;
import mode_L4.model.modele.objets.Vecteur;

public class Projection extends ModeleChanges {

	public Projection(Modele modele, Vecteur angle) {
		
		super(modele);
		
		Matrice plan = new Matrice(new double[][] {
			{1, 0, angle.getX()},
			{0, 1, angle.getY()},
			{0, 0, angle.getZ()},
			{1, 1, 1}
		});
		
		Matrice inversePlan = new Matrice(new double[][] {
			{1, 0, angle.getX()},
			{0, 1, -angle.getY()},
			{0, 0, angle.getZ()},
			{1, 1, 1}
		});
		
		Matrice projectionBase = new Matrice(new double[][] {
			{1, 0, 0},
			{0, 1, 0},
			{0, 0, 0},
			{1, 1, 1}
		});
		
		Matrice projection = inversePlan.multiplier(plan).multiplier(projectionBase);
		
		super.setChanges(projection.multiplier(modele.getMatrice()));		
	}
}
