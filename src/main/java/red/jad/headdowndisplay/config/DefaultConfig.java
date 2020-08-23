package red.jad.headdowndisplay.config;

public class DefaultConfig {

    public enum Change {increase, decrease, always, never}

    public boolean isEnabled(){ return true; }
    public double getMinY(){ return -60; }
    public double getMaxY(){ return 0; }
    public double getSpeed(){ return 1; }
    public double getTimeVisible(){ return 2; }

    public boolean revealSlot(){ return true; }
    public boolean revealItem(){ return false; }
    public boolean revealStatusEffects(){ return false; }
    public Change revealHealth(){ return Change.decrease; }
    public Change revealHunger(){ return Change.never; }
    public Change revealArmor(){ return Change.always; }
    public Change revealExp(){ return Change.never; }
    public Change revealExpLvl(){ return Change.never; }
    public Change revealAir(){ return Change.decrease; }
    public Change revealMountHealth(){ return Change.decrease; }
    public boolean revealJumpbarChange(){ return true; }
}
