package red.jad.headdowndisplay.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.input.Input;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import red.jad.headdowndisplay.HDD;
import red.jad.headdowndisplay.backend.HudAnimationHandler;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {
    /*
    @Inject(
            method = "updateHealth",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getHealth()F")
    )
    private void revealHudOnHealthChange(float health, CallbackInfo ci){
        HudAnimationHandler.revealHud();
    }
     */

    @Shadow public Input input;

    // Health
    @Inject( method = "heal", at = @At(value = "HEAD") )
    private void healthIncreased(float amount, CallbackInfo ci){
        if(HDD.config.revealHealthIncrease()) HudAnimationHandler.revealHud();
    }

    @Inject( method = "damage", at = @At(value = "HEAD") )
    private void healthDecreased(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir){
        if(HDD.config.revealHealthDecrease()) HudAnimationHandler.revealHud();
    }

    // Exp
    @Inject( method = "setExperience", at = @At(value = "HEAD") )
    private void expChanged(float progress, int total, int level, CallbackInfo ci){
        if(level > MinecraftClient.getInstance().player.experienceLevel && HDD.config.revealExpLvlIncrease() ) HudAnimationHandler.revealHud();
        if(level < MinecraftClient.getInstance().player.experienceLevel && HDD.config.revealExpLvlDecrease() ) HudAnimationHandler.revealHud();
        if(progress > MinecraftClient.getInstance().player.experienceProgress && HDD.config.revealExpIncrease() ) HudAnimationHandler.revealHud();
        if(progress < MinecraftClient.getInstance().player.experienceProgress && HDD.config.revealExpDecrease() ) HudAnimationHandler.revealHud();
    }

    // Jumpbar
    @Inject( method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getVehicle()Lnet/minecraft/entity/Entity;") )
    private void jumpbarChanged(CallbackInfo ci){
        if(this.input.jumping && HDD.config.revealJumpbarChange()) HudAnimationHandler.revealHud();
    }
}
