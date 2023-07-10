package net.cravencraft.epicparagliders.network;

import net.cravencraft.epicparagliders.capabilities.UpdatedPlayerMovement;
import net.minecraft.network.FriendlyByteBuf;
import tictim.paraglider.capabilities.PlayerMovement;

public record SyncActionMsg (int actionStaminaCost) {
    public static SyncActionMsg read(FriendlyByteBuf buffer) {
        return new SyncActionMsg(buffer.readInt());
    }

    public SyncActionMsg(UpdatedPlayerMovement playerMovement) {
        this(playerMovement.actionStaminaCost);
    }

    public void copyTo(UpdatedPlayerMovement playerMovement){
        playerMovement.actionStaminaCost = actionStaminaCost;
    }

    public void write(FriendlyByteBuf buffer) {
        buffer.writeInt(actionStaminaCost);
    }
}
