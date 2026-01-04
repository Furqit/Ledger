package com.github.quiltservertools.ledger.utility

import net.minecraft.nbt.CompoundTag
import net.minecraft.world.item.Item

data class ItemData(val item: Item, val changes: CompoundTag?)
