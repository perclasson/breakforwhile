package samples;

import java.awt.Color;

import hockey.api.Player;
import hockey.api.GoalKeeper;
import hockey.api.ITeam;

public class VoidTeam implements ITeam {
    public int getLuckyNumber() { return 324; }
    public String getShortName() { return "VOID"; }
    public String getTeamName() { return "Void"; }
    public Color getTeamColor() { return Color.WHITE; }
    public Color getSecondaryTeamColor() { return Color.WHITE; }
    public GoalKeeper getGoalKeeper() { return new VoidPlayer(0); }
    public Player getPlayer(int index) { return new VoidPlayer(index); }
}

class VoidPlayer extends GoalKeeper {
    private static String[] names = {
	"Voidkeeper", "Left Voiddefender", "Right Voiddefender", 
	"Left Voidforward", "Right Voidforward", "Voidcenter"
    };
    private int index;

    public VoidPlayer(int index) { this.index = index; }
    public int getNumber() { return (int)(Math.random()*100); }
    public String getName() { return names[index]; }
    public boolean isLeftHanded() { return false; }
    public void step() { }
}
