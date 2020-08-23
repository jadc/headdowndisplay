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
    public double time_visible = 2;

    @Override public boolean isEnabled(){ return enabled; }
    @Override public double getMinY(){ return min_y; }
    @Override public double getMaxY(){ return max_y; }
    @Override public double getSpeed(){ return speed; }
    @Override public double getTimeVisible(){ return time_visible; }

    @ConfigEntry.Gui.CollapsibleObject
    Reveal reveal = new Reveal();

    private static class Reveal {
        public boolean slot = true;
        public boolean item = false;
        public boolean status_effects = false;

        @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
        public Change health = Change.decrease;

        @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
        public Change hunger = Change.never;

        @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
        public Change armor = Change.always;

        @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
        public Change exp = Change.never;

        @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
        public Change exp_lvl = Change.never;

        @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
        public Change air = Change.decrease;

        @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
        public Change mount_health = Change.decrease;

        public boolean jumpbar = true;

    }
    @Override public boolean revealSlot(){ return reveal.slot; }
    @Override public boolean revealItem(){ return reveal.item; }
    @Override public boolean revealStatusEffects(){ return reveal.status_effects; }
    @Override public Change revealHealth(){ return reveal.health; }
    @Override public Change revealHunger(){ return reveal.hunger; }
    @Override public Change revealArmor(){ return reveal.armor; }
    @Override public Change revealExp(){ return reveal.exp; }
    @Override public Change revealExpLvl(){ return reveal.exp_lvl; }
    @Override public Change revealAir(){ return reveal.air; }
    @Override public Change revealMountHealth(){ return reveal.mount_health; }
    @Override public boolean revealJumpbarChange(){ return reveal.jumpbar; }
}
