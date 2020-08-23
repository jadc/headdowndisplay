package red.jad.headdowndisplay.backend;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import red.jad.headdowndisplay.HDD;

import static red.jad.headdowndisplay.backend.HudAnimationHandler.revealHud;

public class HudConditionHandler {

    private static ItemStack previousStack;
    private static int previousArmor;
    private static int previousHunger;
    private static int previousAir;
    private static int previousStatusEffects;

    // TODO: Inject into methods that set these values, rather than checking every tick
    public static void tick(ClientPlayerEntity player){
        if(HDD.config.revealItemChange()){
            if(player.getMainHandStack() != previousStack) revealHud();
            previousStack = player.getMainHandStack();
        }

        if(HDD.config.revealStatusEffects()){
            if(player.getActiveStatusEffects().size() != previousStatusEffects) revealHud();
            previousStatusEffects = player.getActiveStatusEffects().size();
        }

        if(HDD.config.revealArmorIncrease() || HDD.config.revealArmorDecrease()){
            revealDelta(player.getArmor(), previousArmor, HDD.config.revealArmorIncrease(), HDD.config.revealArmorDecrease());
            previousArmor = player.getArmor();
        }

        if(HDD.config.revealHungerIncrease() || HDD.config.revealHungerDecrease()){
            revealDelta(player.getHungerManager().getFoodLevel(), previousHunger, HDD.config.revealHungerIncrease(), HDD.config.revealHungerDecrease());
            previousHunger = player.getHungerManager().getFoodLevel();
        }

        if(HDD.config.revealAirIncrease() || HDD.config.revealAirDecrease()){
            revealDelta(player.getAir(), previousAir, HDD.config.revealAirIncrease(), HDD.config.revealAirDecrease());
            previousAir = player.getAir();
        }
    }

    public static void revealDelta(float newer, float older, boolean increase, boolean decrease){
        if(newer != older && (increase || decrease)){
            if(newer > older && increase) revealHud();
            if(newer < older && decrease) revealHud();
        }
    }
}
