package team22;

import hockey.api.GoalKeeper;
import hockey.api.Position;
import hockey.api.*;

public class Goalie extends GoalKeeper {
	double lastAngle;
	String dbgStr = "";

	// Middle of our own goalcage, on the goal line
	protected static final Position GOAL_POSITION = new Position(-2600, 0);

	// Number of the goalie.
	public int getNumber() { return 1; }

	// Name of the goalie.
	public String getName() { return "Göran v‾‾v"; }

	// Left handed goalie
	public boolean isLeftHanded() { return true; }

	// Initiate
	public void init() { }

	// Face off
	public void faceOff() { }

	// Called when the goalie is about to receive a penalty shot
	public void penaltyShot() { }

	// Intelligence of goalie.
	public void step() {
		IPuck puck = getPuck();
		if (hasPuck()) {
			shoot(getPlayer(1), 200);
			return;
		}

		if (puck.getSpeed() > 60 && puck.getX() > -2600) {
			double angle = puck.getHeading();
			if (angle < 0) angle += 360;
			double theLastAngle = lastAngle;
			lastAngle = angle;
			if (theLastAngle > -1000)
				angle = (angle + theLastAngle) / 2; // smoothing
			if (angle > 95 && angle < 265) {
				angle *= 3.14159 / 180.0;
				double dx = -Math.cos(angle), dy = Math.sin(angle);
				double Dx = puck.getX() - (-2600), Dy = puck.getY();
				dy *= Dx / dx;
				dy += puck.getY();
				if (-120 < dy && dy < 120) {
					// will hit dy! shit
					setMessage("shit shit shit " + puck.getHeading() + " " + String.format("%.2f", dy));
					int speed;
					dy = Util.clampAbs(dy, 100);
					if (puck.isHeld()) {
						speed = 20;
					}
					else {
						double timeToGoal = (Dx - 80) / (dx * puck.getSpeed());
						dbgStr += String.format(" | %.0f -> %.0f t = %.1f, ", (double)getY(), dy, timeToGoal);
						if (timeToGoal <= 0) {
							speed = MAX_GLIDE;
						}
						else {
							double wantedSpeed = (dy - getY())/(double)timeToGoal;
							int cur = getGlide();
							dbgStr += " gl " + String.format("%.0f %.0f ", (double)cur, wantedSpeed);
							int bestSpeed = 0;
							double bestDif = 100000;
							for (int sp = -440; sp <= 440; sp += 2) {
								double r = Math.abs(sp - cur) / (double)ACCELERATION / timeToGoal;
								// if (r > 1) continue;
								double res = (cur + sp) * r/2 + sp * (1-r);
								double d = Math.abs(res - wantedSpeed);
								if (d < bestDif) {
									bestDif = d;
									bestSpeed = sp;
								}
							}
							speed = Util.clampPos(Math.abs(bestSpeed), 444);
							dbgStr += " best " + speed;
						}
					}
					skate(GOAL_POSITION.getX() + 60, (int)dy, speed);
					turn(getPuck(), MAX_TURN_SPEED);
					return;
				}
				else setMessage("puh position " + String.format("%.2f", dy));
			}
			else setMessage("puh angle " + angle + dbgStr);
		}
		else setMessage("puh speed " + puck.getSpeed() + dbgStr);
		lastAngle = -1000;

		// slow or wrong; keep it safe
		IPlayer holder = puck.getHolder();
		if (holder != null && holder.getX() - (-2600) > 0 && Math.abs(holder.getY()) > Math.abs(holder.getX() - (-2600)) * 0.7) {
			// outside a cone - move to side
			skate(GOAL_POSITION.getX() + 40, Util.clampAbs(puck.getY(), 100), MAX_GLIDE);
			turn(puck, MAX_TURN_SPEED);
		}
		else { // if (Math.random() < 0.7) {
			// protect
			int y = Util.clampAbs(puck.isHeld() ? holder.getY() / 4 : 0, 100);
			skate(GOAL_POSITION.getX() + 60, y, 30);
			turn(0, MAX_TURN_SPEED);
		}
	}
}
