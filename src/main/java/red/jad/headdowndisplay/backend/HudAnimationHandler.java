package red.jad.headdowndisplay.backend;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
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
        assert MinecraftClient.getInstance().world != null;
        lastRevealed = MinecraftClient.getInstance().world.getTime();
    }

    private static float now = 0;
    public static void render(float delta){
        float before = now;
        assert MinecraftClient.getInstance().world != null;
        now = MinecraftClient.getInstance().world.getTime() + delta;
        float tdelta = now - before;

        if(MinecraftClient.getInstance().world.getTime() - lastRevealed > HDD.config.getTimeVisible()*20 && y > HDD.config.getMinY()){
            speed += HDD.config.getAcceleration() * tdelta;
            y -= speed * tdelta;
        }
    }

    public static void preInject(final MatrixStack matrices){
        matrices.push();
        matrices.translate(0, HudAnimationHandler.getY(), 0);
    }

    public static void postInject(final MatrixStack matrices){
        matrices.pop();
    }
}
