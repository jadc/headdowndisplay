package red.jad.headdowndisplay.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.input.Input;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import red.jad.headdowndisplay.HDD;
import red.jad.headdowndisplay.backend.HudAnimationHandler;
import red.jad.headdowndisplay.backend.HudConditionHandler;

public class HudConditionMixin {

    @Mixin(ClientPlayerEntity.class)
    private static class ClientPlayerEntityMixin {
        @Shadow
        @Final
        protected MinecraftClient client;

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
            if(client.player != null){
                HudConditionHandler.revealDelta(level, client.player.experienceLevel, HDD.config.revealExpLvlIncrease(), HDD.config.revealExpLvlDecrease());
                HudConditionHandler.revealDelta(progress, client.player.experienceProgress, HDD.config.revealExpIncrease(), HDD.config.revealExpDecrease());
            }
        }

        // Jumpbar
        // TODO: Inject method that sets these values, rather than checking every tick
        @Shadow public Input input;
        @Inject( method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getVehicle()Lnet/minecraft/entity/Entity;") )
        private void jumpbarChanged(CallbackInfo ci){
            if(this.input.jumping && HDD.config.revealJumpbarChange()) HudAnimationHandler.revealHud();
        }

        // Mount health
        // TODO: Inject method that sets these values, rather than checking every tick
        @Unique
        private float previousMountHealth;
        @Inject( method = "tickRiding", at = @At(value = "RETURN") )
        private void mountHealthChange(CallbackInfo ci){
            if (((ClientPlayerEntity)(Object)this).getVehicle() instanceof LivingEntity) {
                LivingEntity en = (LivingEntity)((ClientPlayerEntity)(Object)this).getVehicle();
                if(en != null){
                    HudConditionHandler.revealDelta(en.getHealth(), previousMountHealth, HDD.config.revealMountHealthIncrease(), HDD.config.revealMountHealthDecrease());
                    previousMountHealth = en.getHealth();
                }
            }
        }
    }

    @Mixin(ClientPlayerInteractionManager.class)
    private static class ClientPlayerInteractionManagerMixin {
        @Inject( method = "syncSelectedSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayNetworkHandler;sendPacket(Lnet/minecraft/network/Packet;)V") )
        private void revealOnSlotChange(CallbackInfo ci){
            if(HDD.config.revealSlotChange()) HudAnimationHandler.revealHud();
        }
    }
}
