package red.jad.headdowndisplay.backend;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Util;

import javax.swing.text.Utilities;

public class HudAnimationHandler {

    private static float y = 0;

    public static int getY(){
        return (int)Math.max(Math.min(y, 0), -50);
    }
    public static void revealHud(){
        y = 0;
        assert MinecraftClient.getInstance().world != null;
        timeSinceLast = MinecraftClient.getInstance().world.getTime();
    }

    private static long timeSinceLast;
    //private static int previousSlot;
    private static ItemStack previousStack;

    public static void tick(){
        MinecraftClient client = MinecraftClient.getInstance();
        assert client.world != null;
        if(client.world.getTime() - timeSinceLast > 60 && y > -50) {
            y -= MinecraftClient.getInstance().getTickDelta();
            System.out.println(getY());
        }

        // If changed slot
        /*
        assert client.player != null;
        if(client.player.inventory.selectedSlot != previousSlot)  revealHud();
        previousSlot = client.player.inventory.selectedSlot;
         */
        assert client.player != null;
        if(client.player.getMainHandStack() != previousStack) revealHud();
        previousStack = client.player.getMainHandStack();
    }
}
