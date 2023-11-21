package com.chsteam.lover.mixin;

import com.chsteam.lover.item.attachment.Gag;
import net.minecraft.network.encryption.PublicPlayerSession;
import net.minecraft.network.message.SentMessage;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.UUID;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin {

    @Accessor("session")
    abstract PublicPlayerSession getSession();

    @ModifyVariable(at = @At("HEAD") , method = "sendChatMessage", ordinal = 0)
    public SentMessage gagChat(SentMessage message) {
        UUID uuid = getSession().sessionId();

        if (Gag.Companion.getGagPlayer().getOrDefault(uuid, false)) {
            SentMessage.Chat chat = (SentMessage.Chat) message;
            return new SentMessage.Chat(SignedMessage.ofUnsigned("这是一个测试"));
        }

        return message;
    }

}
