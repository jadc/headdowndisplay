package red.jad.headdowndisplay.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import red.jad.headdowndisplay.backend.HudAnimationHandler;
import red.jad.headdowndisplay.backend.HudConditionHandler;

@Mixin(InGameHud.class)
public class HudMovementMixin {

	@Shadow @Final private MinecraftClient client;

	@Inject(method = "tick", at = @At("RETURN"))
	private void injectIntoTick(CallbackInfo ci){
		if(client.player != null) HudConditionHandler.tick(client.player);
	}

	// Hotbar
	@Inject( method = "renderHotbar", at = @At(value = "HEAD") )
	private void translateHotbar(final float f, final MatrixStack matrixStack, final CallbackInfo ci){
		HudAnimationHandler.preInject(matrixStack);
	}
	@Inject( method = "renderHotbar", at = @At(value = "RETURN") )
	private void cancelHotbar(final float f, final MatrixStack matrixStack, final CallbackInfo ci){
		HudAnimationHandler.postInject(matrixStack);
	}

	// Tooltip
	@Inject( method = "renderHeldItemTooltip", at = @At(value = "HEAD") )
	private void translateTooltip(MatrixStack matrixStack, CallbackInfo ci){
		HudAnimationHandler.preInject(matrixStack);
	}
	@Inject( method = "renderHeldItemTooltip", at = @At(value = "RETURN") )
	private void cancelTooltip(MatrixStack matrixStack, CallbackInfo ci){
		HudAnimationHandler.postInject(matrixStack);
	}

	// Hotbar items
	@SuppressWarnings("deprecation")
	@Inject( method = "renderHotbarItem", at = @At(value = "HEAD") )
	private void translateHotbarItems(final int i, final int j, final float f, final PlayerEntity playerEntity, final ItemStack itemStack, final CallbackInfo ci){
		RenderSystem.pushMatrix();
		RenderSystem.translated(0, HudAnimationHandler.getY(), 0);
	}

	@SuppressWarnings("deprecation")
	@Inject( method = "renderHotbarItem", at = @At(value = "RETURN") )
	private void cancelHotbarItems(final int i, final int j, final float f, final PlayerEntity playerEntity, final ItemStack itemStack, final CallbackInfo ci){
		RenderSystem.popMatrix();
	}

	// Experience Bar
	@Inject( method = "renderExperienceBar", at = @At(value = "HEAD") )
	private void translateExperienceBar(final MatrixStack matrices, final int x, final CallbackInfo ci){
		HudAnimationHandler.preInject(matrices);
	}

	@Inject( method = "renderExperienceBar", at = @At(value = "RETURN") )
	private void exitExperienceBar(final MatrixStack matrices, final int x, final CallbackInfo ci){
		HudAnimationHandler.postInject(matrices);
	}

	// Status Bars
	@Inject( method = "renderStatusBars", at = @At(value = "HEAD") )
	private void translateStatusBar(final MatrixStack matrices, final CallbackInfo ci){
		HudAnimationHandler.preInject(matrices);
	}
	@Inject( method = "renderStatusBars", at = @At(value = "RETURN") )
	private void exitStatusBar(final MatrixStack matrices, final CallbackInfo ci){
		HudAnimationHandler.postInject(matrices);
	}

	// Mount Health
	@Inject( method = "renderMountHealth", at = @At(value = "HEAD") )
	private void translateMountHealth(MatrixStack matrices, CallbackInfo ci){
		HudAnimationHandler.preInject(matrices);
	}
	@Inject( method = "renderMountHealth", at = @At(value = "RETURN") )
	private void exitMountHealth(MatrixStack matrices, CallbackInfo ci){
		HudAnimationHandler.postInject(matrices);
	}

	// Mount Jump Bar
	@Inject( method = "renderMountJumpBar", at = @At(value = "HEAD") )
	private void translateMountJumpBar(MatrixStack matrices, int x, CallbackInfo ci){
		HudAnimationHandler.preInject(matrices);
	}
	@Inject( method = "renderMountJumpBar", at = @At(value = "RETURN") )
	private void exitMountJumpBar(MatrixStack matrices, int x, CallbackInfo ci){
		HudAnimationHandler.postInject(matrices);
	}
}
