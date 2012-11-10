package team22;

import hockey.Util;
import hockey.api.IPlayer;

public class Center extends BasePlayer {
	boolean done;
	
	// Number of center player
	public int getNumber() {
		return 19;
	}

	// Name of center player
	public String getName() {
		return "Center";
	}

	// Center player's intelligence
	public void step() {
		if (hasPuck()) {
			int x = getX() - 2600;
			int y = getY();
			setMessage("x" + x + " y" + y);
			
			int d = 1300;
			if (x < 0 && (x * x + y * y) <= d * d) {
				IPlayer goalie = getGoalKeeper(6);
				int my = goalie.getY() + goalie.getStickY()/2;
				setMessage("s x" + x + " y" + y);
				shoot(2600, 95 * (my > 0 ? -1 : 1), 10000);
				done = true;
				//skate(getGoalKeeper(0), 1111);
			}
			else skate(2400, 0, 900);
		} else if (!done) {
			skate(getPuck(), 1001);
		}
	}
}
