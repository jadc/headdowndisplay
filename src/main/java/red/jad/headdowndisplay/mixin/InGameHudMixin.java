package red.jad.headdowndisplay.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import red.jad.headdowndisplay.backend.HudAnimationHandler;

@Mixin(InGameHud.class)
public class InGameHudMixin {

	@Shadow @Final private MinecraftClient client;

	@Inject(at = @At("TAIL"), method = "render")
	private void injectIntoRender(MatrixStack matrices, float tickDelta, CallbackInfo ci){
		if(this.client.player != null) HudAnimationHandler.tick(client);
	}




	/*
	old
	@Inject(at = @At("TAIL"), method = "tick", cancellable = true)
	private void injectIntoTick(CallbackInfo ci){
		if (this.client.player != null) {
			//if (hdd_alpha > 0) hdd_alpha -= 0.1f;
			if(!hdd_show && hdd_y > -50) hdd_y -= hdd_y_speed;
			if(hdd_y >= 0) hdd_y_wait++;
			if(hdd_y_wait > 60){
				hdd_show = !hdd_show;
				hdd_y_wait = 0;
			}
			MinecraftClient.getInstance().getTickDelta()

			if(this.client.player.inventory.selectedSlot != hdd_previousSlot) {
				hdd_show = true;
				hdd_y_wait = 0;
			}
			hdd_previousSlot = this.client.player.inventory.selectedSlot;
		}
	}
	 */

	/*
	@ModifyVariable(
			method = "renderExperienceBar",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/client/util/math/MatrixStack;Ljava/lang/String;FFI)I"),
			print = true,
			ordinal = 0
	)
	private void print(){}
	 */

	/*
		Sliding
	 */

	// Hotbar
	@ModifyArg(
			method = "renderHotbar",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V"),
			index = 2
	)
	private int injectHotbarY(int input){
		return input - HudAnimationHandler.getY();
	}

	// Hotbar items
	@ModifyArg(
			method = "renderHotbar",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHotbarItem(IIFLnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;)V"),
			index = 1
	)
	private int injectHotbarItemY(int input){
		return input - HudAnimationHandler.getY();
	}

	// Experience Bar
	@ModifyArg(
			method = "renderExperienceBar",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V"),
			index = 2
	)
	private int injectExperienceBarY(int input){
		return input - HudAnimationHandler.getY();
	}

	// Experience Level
	@ModifyArg(
			method = "renderExperienceBar",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/client/util/math/MatrixStack;Ljava/lang/String;FFI)I"),
			index = 3
	)
	private float injectExperienceLevelY(float input){
		return input - HudAnimationHandler.getY();
	}

	// Status Bars
	@ModifyArg(
			method = "renderStatusBars",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V"),
			index = 2
	)
	private int injectStatusBars(int input){
		return input - HudAnimationHandler.getY();
	}

	/*
		Fading
	 */
	/*

	@ModifyArg(
			method = "renderHotbar",
			at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;color4f(FFFF)V"),
			index = 3
	)
	private float hotbarAlpha(float input){
		return Math.min(hdd_alpha, 1.0f);
	}

	@Inject(
			method = "renderStatusBars",
			at = @At("HEAD"),
			cancellable = true
	)
	private void statusBarsAlpha(MatrixStack matrices, CallbackInfo ci){
		if(hdd_alpha <= 0.0f) ci.cancel();
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, Math.min(hdd_alpha, 1.0f));
	}

	@Inject(
			method = "renderExperienceBar",
			at = @At(value = "HEAD"),
			cancellable = true
	)
	private void experienceBarAlpha(MatrixStack matrices, int x, CallbackInfo ci){
		//RenderSystem.color4f(1.0F, 1.0F, 1.0F, staticHUDAlpha);
		if(hdd_alpha <= 0.0f) ci.cancel();
	}

	@Inject(
			method = "renderHotbarItem",
			at = @At(value = "HEAD"),
			cancellable = true
	)
	private void hotbarItemAlpha(int i, int j, float f, PlayerEntity playerEntity, ItemStack itemStack, CallbackInfo ci){
		//RenderSystem.color4f(1.0F, 1.0F, 1.0F, staticHUDAlpha);
		if(hdd_alpha <= 0.0f) ci.cancel();
	}

	/*

	OOOLD

	private int staticHUDY = 30;

	@Shadow private int scaledWidth;

	@ModifyVariable(
			method = "renderStatusBars",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;getAttributeValue(Lnet/minecraft/entity/attribute/EntityAttribute;)D"),
			index = 12, ordinal = 5
	)
	private int offsetStatusBars(int m, MatrixStack matrices) {
		return m - staticHUDY;
	}

	/*
	@ModifyVariable(
			method = "renderHotbar",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;setZOffset(I)V"),
			index = 6,
			ordinal = 0
	)
	private int offsetHotbar(int i, float f, MatrixStack matrixStack){
		return i - staticHUDY;
	}
	 */
	/*
	@Shadow @Final private MinecraftClient client;
	@Shadow private ItemStack currentStack;
	private int staticHUDFade;
	private int previousSlot = 0;

	@Inject(at = @At("TAIL"), method = "tick", cancellable = true)
	private void injectFadeIntoTick(CallbackInfo ci){
		if (this.client.player != null) {
			if (staticHUDFade > 0) --staticHUDFade;

			if(this.client.player.inventory.selectedSlot != previousSlot) staticHUDFade = 40;
			previousSlot = this.client.player.inventory.selectedSlot;
		}
	}

	@Inject(at = @At("HEAD"), method = {"renderHotbar", "renderExperienceBar", "renderStatusBars"}, cancellable = true)
	private void hideHUD(float f, MatrixStack matrixStack, CallbackInfo ci) {
		if(staticHUDFade <= 0) ci.cancel();
	}
	 */
}
