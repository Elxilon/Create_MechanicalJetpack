package com.elxilon.create_mechanical_jetpack.item;

import com.simibubi.create.content.equipment.armor.BacktankItem;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterial;

import java.util.function.Supplier;

public class JetpackItem extends BacktankItem {
    public JetpackItem(Holder<ArmorMaterial> material, Properties properties, ResourceLocation textureLoc, Supplier<BacktankBlockItem> placeable) {
        super(material, properties, textureLoc, placeable);
    }
}
