package teamTemplate;

public class Defender extends BasePlayer {
    // Number of defender
    public int getNumber() { return 10; }

    // Name of defender
    public String getName() { return "Defender"; }

    // Make left defender left handed, right defender right handed.
    public boolean isLeftHanded() { return getIndex() == 1; }

    // Initiate
    public void init() {
	setAimOnStick(false);
    }

    // Defender intelligence
    public void step() {
	if (getPuck().isHeld())
	    skate(getPuck().getHolder(), MAX_SPEED);
	else
	    if (getIndex() == 1)
		skate(-20000, -10000, 1000);
	    else
		skate(-20000, 10000, 1000);
    }
}
