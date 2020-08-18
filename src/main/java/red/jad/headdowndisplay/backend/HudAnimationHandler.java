package red.jad.headdowndisplay.backend;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Shadow;
import red.jad.headdowndisplay.HDD;

public class HudAnimationHandler {

    private static double y = 0;
    private static double speed = HDD.config.getSpeed();

    public static double getY(){
        return -y;
        //return Math.min(Math.max(y, 0), 50);
    }
    private static long lastRevealed;
    public static void revealHud(){
        y = HDD.config.getMaxY();
        speed = 0;
        lastRevealed = MinecraftClient.getInstance().world.getTime();
    }

    private static float now = 0;
    public static void render(float delta){
        float before = now;
        now = MinecraftClient.getInstance().world.getTime() + delta;
        float tdelta = now - before;

        if(MinecraftClient.getInstance().world.getTime() - lastRevealed > HDD.config.getTimeVisible()*20 && y > HDD.config.getMinY()){
            speed += HDD.config.getAcceleration() * tdelta;
            y -= speed * tdelta;
        }
    }

    private static ItemStack previousStack;
    private static int previousSlot;
    private static int previousArmor;
    private static float previousJumpbar;

    public static void tick(MinecraftClient client){
        /*
        if(HDD.config.revealSlotChange()){
            if(client.player.inventory.selectedSlot != previousSlot) revealHud();
            previousSlot = client.player.inventory.selectedSlot;
        }
        if(HDD.config.revealJumpbarChange() && client.player.hasJumpingMount()){
            if(client.player.method_3151() != previousJumpbar) revealHud();
            previousJumpbar = client.player.method_3151();
        }
         */
        if(HDD.config.revealItemChange()){
            if(client.player.getMainHandStack() != previousStack) revealHud();
            previousStack = client.player.getMainHandStack();
        }
        if(HDD.config.revealArmorIncrease() || HDD.config.revealArmorDecrease()){
            if(client.player.getArmor() > previousArmor && HDD.config.revealArmorIncrease()) revealHud();
            if(client.player.getArmor() < previousArmor && HDD.config.revealArmorDecrease()) revealHud();
            previousArmor = client.player.getArmor();
        }
    }

    /*
        Injections
     */
    public static void preInject(final MatrixStack matrices){
        matrices.push();
        matrices.translate(0, HudAnimationHandler.getY(), 0);
    }

    public static void postInject(final MatrixStack matrices){
        matrices.pop();
    }
}
