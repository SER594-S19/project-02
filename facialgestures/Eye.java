package facialgestures;

import java.awt.Color;
import java.awt.Graphics;

public class Eye {

	private VectorForEye position;
	private double rad1;
	private double rad2;

	public Eye(double x, double y, double r1, double r2) {
		position = new VectorForEye(x, y);
		this.rad1 = r1;
		this.rad2 = r2;
	}

	public void draw(Graphics graphic, VectorForEye pos, int mode, boolean showPupil, Color col) {
		VectorForEye positionOfIris = pos.substract(position);
		double distanceFromCenterIris = Math.min(rad1 - rad2, positionOfIris.getLength());
		
		positionOfIris.normalize();
		positionOfIris.multiply(distanceFromCenterIris);
		
		positionOfIris = positionOfIris.addNew(position);
		graphic.setColor(Color.BLACK);
		graphic.fillOval((int) (position.getX() - rad1 - 8), (int) (position.getY() - rad1 - 8), (int) (2 * rad1 + 16),
				(int) (2 * rad1 + 16));

		graphic.setColor(col);
		graphic.fillOval((int) (position.getX() - rad1), (int) (position.getY() - rad1), (int) (2 * rad1),
				(int) (2 * rad1));

		if (showPupil) {
			graphic.setColor(Color.BLACK);
			graphic.fillOval((int) (positionOfIris.getX() - rad2), (int) (positionOfIris.getY() - rad2),
					(int) (2 * rad2), (int) (2 * rad2));

		}
	}

}