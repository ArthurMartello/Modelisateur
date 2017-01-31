package mode_L4.view.modele;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;

import mode_L4.controler.modele.ModeleControler;
import mode_L4.controler.modele.PaintControler;
import mode_L4.controler.modele.SourisListener;
import mode_L4.model.modele.Parametre;
import mode_L4.model.modele.objets.Face;
import mode_L4.model.modele.objets.Point;
import mode_L4.model.modele.objets.Vecteur;
import mode_L4.view.fenetre.Frame;

/**
 * Dessine les faces et points d'un Modele en fonction de ses options.
 */
public class Painter extends JPanel implements ModeleObserver {

	private static final long serialVersionUID = 3180174571825329502L;

	// Controleur
	private ModeleControler controler;

	// Taille du panel
	private int tailleX;
	private int tailleY;

	// Constantes
	public static final int TAILLEPOINT = 2; // une taille paire est conseillee
	private final Vecteur LUMIERE;

	// Etat de l'affichage
	private PainterState etat;

	// Depuis le Modele
	private List<Face> faces;
	private List<Point> points;
	private Parametre parametre;

	/**
	 * Initialise la vue à partir d'un controleur du Modele, d'une fenêtre dont
	 * on dépend et d'une taille par défaut.
	 * 
	 * @param controler
	 *            Un objet PaintControler
	 * @param tailleX
	 *            Taille x par défaut
	 * @param tailleY
	 *            Taille y par défaut
	 */
	public Painter(PaintControler controler, PainterState etat) {

		this.controler = controler;
		this.LUMIERE = new Vecteur(0, 0, 1.25);
		this.etat = etat;

		// Listeners de la souris
		SourisListener mouseList = new SourisListener((PaintControler) this.controler);
		this.addMouseWheelListener(mouseList);
		this.addMouseMotionListener(mouseList);
		this.addMouseListener(mouseList);

	}

	/**
	 * Redéfinit la taille de la vue
	 */
	public void setSize(int x, int y) {
		this.tailleX = x - Frame.OFFSETX;
		this.tailleY = y - Frame.OFFSETY;
		this.repaint();
	}

	/**
	 * @return La largeur de la vue
	 */
	public double getTailleX() {
		return this.tailleX;
	}

	/**
	 * @return La hauteur de la vue
	 */
	public double getTailleY() {
		return this.tailleY;
	}

	@Override
	protected void paintComponent(Graphics g) {

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, tailleX, tailleY);

		if (this.etat != PainterState.MODEL_DISPLAY) {

			g.setColor(etat.getColor());
			String erreur = etat.getMessage();
			g.drawString(erreur, tailleX / 2 - erreur.length() * 3, tailleY / 2);

		} else if (parametre.getParametre("grille")) {

			g.setColor(new Color(230, 230, 230));
			int dist = 30;
			int nombre = tailleY / 10;

			for (int i = 1; i <= nombre; i++) {
				for (int j = 1; j <= nombre; j++) {

					if (j % 2 == 0 && i % 2 == 0) {
						g.fillRect(i * dist - dist, j * dist - dist, dist, dist);
					} else if (j % 2 == 1 && i % 2 == 1) {
						g.fillRect(i * dist - dist, j * dist - dist, dist, dist);
					}
				}
			}
		}

		if (parametre.getParametre("ombre")	&& (parametre.getParametre("lumiere")) && (parametre.getParametre("faces") || parametre.getParametre("segments"))) {
			for (int i = 0; i < faces.size(); i++) {
				drawFaceOmbre(g, faces.get(i));
			}
		}

		for (int i = 0; i < faces.size(); i++) {
			drawFace(g, faces.get(i));
		}

		if (parametre.getParametre("points")) {
			for (int i = 0; i < points.size(); i++) {
				drawPoint(g, points.get(i));
			}
		}

	}

	private void drawFace(Graphics g, Face face) {

		Polygon p = new Polygon();

		for (int i = 0; i < face.getListePoints().size(); i++) {
			Point point = face.getListePoints().get(i);
			int x = (int) ((point.getX()) + tailleX / 2);
			int y = (int) (tailleY / 2 - (point.getY()));
			p.addPoint(x, y);
		}

		if (parametre.getParametre("faces")) {
			if (parametre.getParametre("lumiere")) {
				g.setColor(face.getColor(LUMIERE));
			} else {
				g.setColor(new Color(200, 200, 200));
			}
			g.fillPolygon(p);
		}

		if (parametre.getParametre("segments")) {
			g.setColor(Color.black);
			g.drawPolygon(p);
		}
	}

	private void drawFaceOmbre(Graphics g, Face face) {

		Polygon p = new Polygon();

		for (int i = 0; i < face.getListePoints().size(); i++) {
			Point point = face.getListePoints().get(i);
			int x = (int) ((point.getX()) + tailleX / 2 / LUMIERE.getNorme());
			int y = (int) (tailleY / 2 - (point.getY()) / LUMIERE.getNorme());
			p.addPoint(x, y);
		}

		g.setColor(new Color(100, 100, 100));

		if (parametre.getParametre("segments") && !parametre.getParametre("faces")) {
			g.drawPolygon(p);
		} else {
			g.fillPolygon(p);
		}
	}

	private void drawPoint(Graphics g, Point point) {

		g.setColor(Color.red);

		g.drawRect((int) ((point.getX()) + tailleX / 2), (int) (tailleY / 2 - (point.getY()) - TAILLEPOINT),
				TAILLEPOINT, TAILLEPOINT);
		g.fillRect((int) ((point.getX()) + tailleX / 2), (int) (tailleY / 2 - (point.getY()) - TAILLEPOINT),
				TAILLEPOINT, TAILLEPOINT);
	}

	@Override
	public void updatePainting(List<Face> faces, List<Point> points) {
		this.faces = faces;
		this.points = points;
		Collections.sort(this.faces);

		if (!points.isEmpty())
			this.etat = PainterState.MODEL_DISPLAY;

		else if (this.etat == PainterState.MODEL_DISPLAY && points.isEmpty())
			this.etat = PainterState.IMPORT_ERROR;

		super.repaint();
	}

	@Override
	public void updateOptions(Parametre parametres) {
		this.parametre = parametres;
		super.repaint();
	}

	@Override
	public void updateBorders(int x, int y) {
		this.setSize(x, y);
	}

}
