package com.chsteam.lover.mixin;

import com.chsteam.lover.client.hud.LoverHud;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Shadow
    @Final
    @Mutable
    private MinecraftClient client;

    @Shadow
    private PlayerEntity getCameraPlayer() {
        return null;
    }

    @Inject(method = "drawHeart", at = @At("HEAD"), cancellable = true)
    private void lover$drawHeart(DrawContext context, InGameHud.HeartType type, int x, int y, boolean hardcore, boolean blinking, boolean half, CallbackInfo info) {
        if(LoverHud.INSTANCE.renderLoveHud(context, type, getCameraPlayer(), x,y, blinking, half)) {
            info.cancel();
        }
    }
}
