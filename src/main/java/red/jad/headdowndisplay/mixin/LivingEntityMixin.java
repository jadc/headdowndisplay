package red.jad.headdowndisplay.mixin;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import red.jad.headdowndisplay.HDD;
import red.jad.headdowndisplay.backend.HudAnimationHandler;

// TODO: replace w/ packet
@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject( method = "sendEquipmentBreakStatus", at = @At(value = "HEAD") )
    private void onArmorBreak(EquipmentSlot slot, CallbackInfo ci){
        //noinspection ConstantConditions
        if( (Object)this instanceof PlayerEntity ){
            if( HDD.config.revealArmorDecrease() ) HudAnimationHandler.revealHud();
        }
    }
}
