package team22;

import java.util.Random;

import hockey.api.IPlayer;

public class Center extends BasePlayer {
	Random rand = new Random();

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
			int d = 1300;
			if (x < 0 && (x * x + y * y) <= d * d) {
				IPlayer goalie = getGoalKeeper(6);
				int goalie_y = goalie.getY() + goalie.getStickY() / 2;

				int shoot_y = 95 * ((goalie_y > 0) ? -1 : 1);
				int shoot_x = 2600;

				shoot(shoot_x, shoot_y, 10000);
			} else {
				skate(2400, 0, 1000);
			}
		} else {
			// Our team has the puck but not me
			if (getPuck().isHeld() && !getPuck().getHolder().isOpponent()) {
				// Stalk opposites teams players
				skate(getPlayer(rand.nextInt(11 - 7 + 1) + 7), 1000);
			} else {
				skate(getPuck(), 1001);
			}
		}
	}
}
