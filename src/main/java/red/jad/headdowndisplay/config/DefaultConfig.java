package red.jad.headdowndisplay.config;

public class DefaultConfig {
    public boolean isEnabled(){ return true; }
    public double getMinY(){ return -60; }
    public double getMaxY(){ return 0; }
    public double getSpeed(){ return 1; }
    public double getAcceleration(){ return 1; }
    public double getTimeVisible(){ return 2; }

    public boolean revealSlotChange(){ return true; }
    public boolean revealItemChange(){ return false; }
    public boolean revealHealthIncrease(){ return false; }
    public boolean revealHealthDecrease(){ return true; }
    public boolean revealHungerIncrease(){ return false; }
    public boolean revealHungerDecrease(){ return false; }
    public boolean revealArmorIncrease(){ return true; }
    public boolean revealArmorDecrease(){ return true; }
    public boolean revealExpIncrease(){ return false; }
    public boolean revealExpDecrease(){ return false; }
    public boolean revealExpLvlIncrease(){ return false; }
    public boolean revealExpLvlDecrease(){ return false; }
    public boolean revealAirIncrease(){ return false; }
    public boolean revealAirDecrease(){ return true; }
    public boolean revealMountHealthIncrease(){ return false; }
    public boolean revealMountHealthDecrease(){ return true; }
    public boolean revealJumpbarChange(){ return true; }
}
