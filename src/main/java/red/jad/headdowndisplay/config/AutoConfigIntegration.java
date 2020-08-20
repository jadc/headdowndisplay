package red.jad.headdowndisplay.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import red.jad.headdowndisplay.HDD;

@Config(name = HDD.MOD_ID)
@Config.Gui.Background("minecraft:textures/block/obsidian.png")
public class AutoConfigIntegration extends DefaultConfig implements ConfigData {

    public boolean enabled = true;
    public double min_y = -60;
    public double max_y = 0;
    public double speed = 1;
    public double acceleration = 1;
    public double time_visible = 2;

    @Override public boolean isEnabled(){ return enabled; }
    @Override public double getMinY(){ return min_y; }
    @Override public double getMaxY(){ return max_y; }
    @Override public double getSpeed(){ return speed; }
    @Override public double getAcceleration(){ return acceleration; }
    @Override public double getTimeVisible(){ return time_visible; }

    @ConfigEntry.Gui.CollapsibleObject
    Reveal reveal = new Reveal();

    private static class Reveal {
        public boolean slot_change = true;
        public boolean item_change = false;
        public boolean health_increase = false;
        public boolean health_decrease = true;
        public boolean hunger_increase = false;
        public boolean hunger_decrease = false;
        public boolean armor_increase = true;
        public boolean armor_decrease = true;
        public boolean exp_increase = false;
        public boolean exp_decrease = false;
        public boolean exp_lvl_increase = false;
        public boolean exp_lvl_decrease = false;
        public boolean air_increase = false;
        public boolean air_decrease = true;
        public boolean mount_health_increase = false;
        public boolean mount_health_decrease = true;
        public boolean jumpbar = true;

    }
    @Override public boolean revealSlotChange(){ return reveal.slot_change; }
    @Override public boolean revealItemChange(){ return reveal.item_change; }
    @Override public boolean revealHealthIncrease(){ return reveal.health_increase; }
    @Override public boolean revealHealthDecrease(){ return reveal.health_decrease; }
    @Override public boolean revealHungerIncrease(){ return reveal.hunger_increase; }
    @Override public boolean revealHungerDecrease(){ return reveal.hunger_decrease; }
    @Override public boolean revealArmorIncrease(){ return reveal.armor_increase; }
    @Override public boolean revealArmorDecrease(){ return reveal.armor_decrease; }
    @Override public boolean revealExpIncrease(){ return reveal.exp_increase; }
    @Override public boolean revealExpDecrease(){ return reveal.exp_decrease; }
    @Override public boolean revealExpLvlIncrease(){ return reveal.exp_lvl_increase; }
    @Override public boolean revealExpLvlDecrease(){ return reveal.exp_lvl_decrease; }
    @Override public boolean revealAirIncrease(){ return reveal.air_increase; }
    @Override public boolean revealAirDecrease(){ return reveal.air_decrease; }
    @Override public boolean revealMountHealthIncrease(){ return reveal.mount_health_increase; }
    @Override public boolean revealMountHealthDecrease(){ return reveal.mount_health_decrease; }
    @Override public boolean revealJumpbarChange(){ return reveal.jumpbar; }
}
