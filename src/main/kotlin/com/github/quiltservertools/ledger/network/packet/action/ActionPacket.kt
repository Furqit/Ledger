package com.github.quiltservertools.ledger.network.packet.action

import com.github.quiltservertools.ledger.actions.ActionType
import com.github.quiltservertools.ledger.network.packet.LedgerPacket
import com.github.quiltservertools.ledger.network.packet.LedgerPacketTypes
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.resources.ResourceLocation

class ActionPacket : LedgerPacket<ActionType> {
    override val channel: ResourceLocation = LedgerPacketTypes.ACTION.id
    override var buf: FriendlyByteBuf = PacketByteBufs.create()

    override fun populate(content: ActionType) {
        // Position
        buf.writeBlockPos(content.pos)
        // Type
        buf.writeUtf(content.identifier)
        // Dimension
        buf.writeResourceLocation(content.world)
        // Objects
        buf.writeResourceLocation(content.oldObjectResourceLocation)
        buf.writeResourceLocation(content.objectResourceLocation)
        // Source
        buf.writeUtf(content.sourceProfile?.name ?: "@" + content.sourceName)
        // Epoch second of event, sent as a long
        buf.writeLong(content.timestamp.epochSecond)
        // Has been rolled back?
        buf.writeBoolean(content.rolledBack)
        // NBT
        buf.writeUtf(content.extraData ?: "")
    }
}
