package red.jad.headdowndisplay.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import red.jad.headdowndisplay.HDD;
import red.jad.headdowndisplay.backend.HudAnimationHandler;

@Mixin(HungerManager.class)
public class HungerManagerMixin {
    @Shadow private int foodLevel;
    @Shadow private int prevFoodLevel;

    @Inject(method = "update", at = @At(value = "HEAD"))
    private void hungerChanged(PlayerEntity player, CallbackInfo ci){
        if(this.foodLevel > this.prevFoodLevel && HDD.config.revealHungerIncrease()) HudAnimationHandler.revealHud();
        if(this.foodLevel < this.prevFoodLevel && HDD.config.revealHungerDecrease()) HudAnimationHandler.revealHud();
    }
}
