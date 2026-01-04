package com.github.quiltservertools.ledger.network.packet.handshake

import com.github.quiltservertools.ledger.network.packet.LedgerPacket
import com.github.quiltservertools.ledger.network.packet.LedgerPacketTypes
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.resources.ResourceLocation

class HandshakePacket : LedgerPacket<HandshakeContent> {
    override val channel: ResourceLocation = LedgerPacketTypes.HANDSHAKE.id
    override var buf: FriendlyByteBuf = PacketByteBufs.create()
    override fun populate(content: HandshakeContent) {
        // Ledger information
        // Protocol Version
        buf.writeInt(content.protocolVersion)

        // Ledger Version
        buf.writeUtf(content.ledgerVersion)

        // We tell the client mod how many actions we are writing
        buf.writeInt(content.actions.size)

        for (action in content.actions) {
            buf.writeUtf(action)
        }
    }
}
