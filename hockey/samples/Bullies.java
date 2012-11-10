package samples;

import java.awt.Color;
import hockey.api.GoalKeeper;
import hockey.api.IPlayer;
import hockey.api.Player;
import hockey.api.ITeam;
import hockey.api.Util;

public class Bullies implements ITeam {
    public String getShortName() { return "BULL"; }
    public String getTeamName() { return "The Bullies"; }
    public Color getTeamColor() { return Color.GREEN; }
    public Color getSecondaryTeamColor() { return Color.YELLOW; }
    public int getLuckyNumber() { return 13; }
    public GoalKeeper getGoalKeeper() { return new BullieGoalie(); }
    public Player getPlayer(int index) {
	return new Bullie(index);
    }
}

class Bullie extends Player {
    private static int[] numbers = {1, 2, 3, 4, 5, 6};
    private static String[] names = {
	"", "Biff", "Buff", "Harry", "Duff", "Push"
    };
    private int index;

    public Bullie(int index) { this.index = index; }
    public int getNumber() { return numbers[index]; }
    public String getName() { return names[index]; }
    public boolean isLeftHanded() { return false; }
    public void step() {
	if (hasPuck()) // If we have the puck
	    skate(2600, 0, 1000); // Go for the goal
	else if (Util.dist(getX() - getPuck().getX(), // If puck within 5m
			   getY() - getPuck().getY()) < 500)
	    skate(getPuck(), 1000); // Go for puck
	else {
	    IPlayer best = null;
	    for (int i = 0; i < 12; ++i) { // Gå through all players
		IPlayer cur = getPlayer(i);

		if (cur.isOpponent() && // If player is opponent...
		    (best == null || 
		     Util.dist(getX() - cur.getX(), // ...and closest so far...
			       getY() - cur.getY()) <
		     Util.dist(getX() - best.getX(),
			       getY() - best.getY())))
		    best = cur; // ... then remember him
	    }

	    skate(best, 1000); // Tackle the nearest opponent.
	}
    }
}

class BullieGoalie extends GoalKeeper {
    public int getNumber() { return 1; }
    public String getName() { return "Franz Jaeger"; }
    public boolean isLeftHanded() { return false; }
    public void step() {
	skate(-2550, 0, 200); // Stand in middle of goal 1/2 m in front of it.
	turn(getPuck(), MAX_TURN_SPEED); // Turn against puck
    }
}

