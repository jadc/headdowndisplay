package red.jad.headdowndisplay.mixin;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import red.jad.headdowndisplay.HDD;
import red.jad.headdowndisplay.backend.HudAnimationHandler;

// TODO: replace w/ packet
@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject( method = "equipStack", at = @At(value = "HEAD"))
    private void onArmorIncrease(EquipmentSlot slot, ItemStack stack, CallbackInfo ci){
        if(HDD.config.revealArmorIncrease()) HudAnimationHandler.revealHud();
    }
}
