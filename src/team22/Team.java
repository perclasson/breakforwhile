package team22;

import java.awt.Color;
import hockey.api.GoalKeeper;
import hockey.api.Player;
import hockey.api.ITeam;

public class Team implements ITeam {
	// Team Short Name. Max 4 characters.
	public String getShortName() {
		return "∰☯☃⌨";
	}

	// Team Name
	public String getTeamName() {
		return "BreakForWhile";
	}

	// Team color; body color
	public Color getTeamColor() {
		return Color.CYAN;
	}

	// Team color; helmet color.
	public Color getSecondaryTeamColor() {
		return Color.DARK_GRAY;
	}

	// The team's LUCKY NUMBER!!
	public int getLuckyNumber() {
		return -163;
	}

	// Get the goal keeper of the team.
	public GoalKeeper getGoalKeeper() {
		return new Goalie();
	}

	// Get the other five players of the team.
	public Player getPlayer(int index) {
		switch (index) {
		case 1:
			return new Defender(); // Defender
		case 2:
			return new Center(); // Forward
		case 3:
			return new Center(); // Center
		case 4:
			return new Center(); // Center
		case 5:
			return new Center(); // Center
		}
		return null;
	}
}
