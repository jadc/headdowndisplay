package red.jad.headdowndisplay.backend;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import red.jad.headdowndisplay.HDD;
import red.jad.headdowndisplay.config.DefaultConfig;

import static red.jad.headdowndisplay.backend.HudAnimationHandler.revealHud;

public class HudConditionHandler {

    private static ItemStack previousStack;
    private static int previousHealth;
    private static int previousHunger;
    private static int previousArmor;
    private static int previousAir;
    private static int previousStatusEffects;

    public static void tick(ClientPlayerEntity player){
        if(HDD.config.revealItem()){
            if(player.getMainHandStack() != previousStack) revealHud();
            previousStack = player.getMainHandStack();
        }

        if(!player.isCreative() && HDD.config.revealStatusEffects()){
            if(player.getActiveStatusEffects().size() != previousStatusEffects) revealHud();
            previousStatusEffects = player.getActiveStatusEffects().size();
        }

        if(!player.isCreative() && HDD.config.revealHealth() != DefaultConfig.Change.never){
            revealDelta((int)player.getHealth(), previousHealth, HDD.config.revealHealth());
            previousHealth = (int)player.getHealth();
        }

        if(!player.isCreative() && HDD.config.revealHunger() != DefaultConfig.Change.never){
            revealDelta(player.getHungerManager().getFoodLevel(), previousHunger, HDD.config.revealHunger());
            previousHunger = player.getHungerManager().getFoodLevel();
        }

        if(!player.isCreative() && HDD.config.revealArmor() != DefaultConfig.Change.never){
            revealDelta(player.getArmor(), previousArmor, HDD.config.revealArmor());
            previousArmor = player.getArmor();
        }

        if(!player.isCreative() && HDD.config.revealAir() != DefaultConfig.Change.never){
            revealDelta(player.getAir(), previousAir, HDD.config.revealAir());
            previousAir = player.getAir();
        }
    }

    public static void revealDelta(float newer, float older, DefaultConfig.Change change){
        if(newer != older && change != DefaultConfig.Change.never){
            if(newer > older && change != DefaultConfig.Change.decrease) revealHud(); // show inc
            if(newer < older && change != DefaultConfig.Change.increase) revealHud(); // show dec
        }
    }
}
