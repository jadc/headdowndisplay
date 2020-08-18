package red.jad.headdowndisplay.mixin;

import net.minecraft.client.network.ClientPlayerInteractionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import red.jad.headdowndisplay.HDD;
import red.jad.headdowndisplay.backend.HudAnimationHandler;

@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {
    @Inject( method = "syncSelectedSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayNetworkHandler;sendPacket(Lnet/minecraft/network/Packet;)V") )
    private void revealOnSlotChange(CallbackInfo ci){
        if(HDD.config.revealSlotChange()) HudAnimationHandler.revealHud();
    }
}
