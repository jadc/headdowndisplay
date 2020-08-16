package red.jad.headdowndisplay.backend;

import net.minecraft.client.MinecraftClient;

public class HudAnimationHandler {

    private static int y = 0;

    public static int getY(){
        return y;
    }

    private static long timeSinceLast;
    private static int previousSlot;

    public static void tick(MinecraftClient client){
        assert client.world != null;
        if(client.world.getTime() - timeSinceLast > 60 && y > -50) y--;
        y = Math.max(Math.min(y, 0), -50);

        assert client.player != null;
        if(client.player.inventory.selectedSlot != previousSlot) {
            y = 0;
            timeSinceLast = client.world.getTime();
        }
        previousSlot = client.player.inventory.selectedSlot;
    }
}
