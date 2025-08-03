package com.vexsoftware.votifier.sponge.forwarding;

import com.vexsoftware.votifier.sponge.NuVotifier;
import com.vexsoftware.votifier.support.forwarding.AbstractPluginMessagingForwardingSink;
import com.vexsoftware.votifier.support.forwarding.ForwardedVoteListener;
import org.spongepowered.api.ResourceKey;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.network.EngineConnectionState;
import org.spongepowered.api.network.channel.ChannelBuf;
import org.spongepowered.api.network.channel.raw.RawDataChannel;
import org.spongepowered.api.network.channel.raw.play.RawPlayDataHandler;

public class SpongePluginMessagingForwardingSink extends AbstractPluginMessagingForwardingSink implements RawPlayDataHandler<EngineConnectionState> {

    private final NuVotifier p;
    private final RawDataChannel c;

    public SpongePluginMessagingForwardingSink(NuVotifier p, String channel, ForwardedVoteListener listener) {
        super(listener);

        RawDataChannel c = Sponge.game().channelManager().ofType(ResourceKey.sponge(channel), RawDataChannel.class);
        this.c = c;
        c.play().addHandler(this);
        this.p = p;
    }

    @Override
    public void halt() {
        c.play().removeHandler(this);
    }

    @Override
    public void handlePayload(ChannelBuf data, EngineConnectionState state) {
        byte[] msgDirBuf = data.readBytes(data.available());
        try {
            this.handlePluginMessage(msgDirBuf);
        } catch (Exception e) {
            p.getLogger().error("There was an unknown error when processing a forwarded vote.", e);
        }
    }
}
