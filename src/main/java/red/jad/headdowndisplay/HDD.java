package red.jad.headdowndisplay;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.loader.api.FabricLoader;
import red.jad.headdowndisplay.backend.HudAnimationHandler;
import red.jad.headdowndisplay.config.AutoConfigIntegration;
import red.jad.headdowndisplay.config.DefaultConfig;

public class HDD implements ClientModInitializer {
    public static final String MOD_ID = "headdowndisplay";
    public static DefaultConfig config;

    @Override
	public void onInitializeClient() {
        HudRenderCallback.EVENT.register((matrixStack, delta) -> {
            if(HDD.config.isEnabled()) HudAnimationHandler.render(delta);
        });

		if(FabricLoader.getInstance().isModLoaded("autoconfig1u")){
            AutoConfig.register(AutoConfigIntegration.class, GsonConfigSerializer::new);
            config = AutoConfig.getConfigHolder(AutoConfigIntegration.class).getConfig();
        }else{
            config = new DefaultConfig();
        }
	}
}
