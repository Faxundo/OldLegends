package com.github.faxundo.old_legends.item;

import com.github.faxundo.old_legends.util.OLHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class OLGenericItem extends Item {

    public OLGenericItem(Settings settings) {
        super(settings.fireproof());
    }

    @Override
    public Text getName(ItemStack itemStack) {
        return OLHelper.getNameHelper(this.getDefaultStack(), false);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return super.use(world, user, hand);
    }
}
