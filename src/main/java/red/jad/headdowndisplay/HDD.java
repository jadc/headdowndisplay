package red.jad.headdowndisplay;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import red.jad.headdowndisplay.backend.HudAnimationHandler;
import red.jad.headdowndisplay.config.AutoConfigIntegration;
import red.jad.headdowndisplay.config.DefaultConfig;

public class HDD implements ClientModInitializer {

    public static final String MOD_ID = "headdowndisplay";
    public static final String AUTO_CONFIG_MOD_ID = "autoconfig1u";
    public static final String CLOTH_CONFIG_MOD_ID = "cloth-config2";

    public static DefaultConfig config;
    private static KeyBinding keyShowHud;

    @Override
	public void onInitializeClient() {
        HudRenderCallback.EVENT.register((matrixStack, delta) -> {
            if(HDD.config.isEnabled()) HudAnimationHandler.render(delta);
        });

        keyShowHud = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key." + MOD_ID + ".showhud",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                "key.categories." + MOD_ID
        ){});

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyShowHud.wasPressed() && HDD.config.isEnabled()) {
                HudAnimationHandler.revealHud();
            }
        });

		if(FabricLoader.getInstance().isModLoaded(AUTO_CONFIG_MOD_ID)){
            AutoConfig.register(AutoConfigIntegration.class, GsonConfigSerializer::new);
            config = AutoConfig.getConfigHolder(AutoConfigIntegration.class).getConfig();
        }else{
            config = new DefaultConfig();
        }
	}
}
