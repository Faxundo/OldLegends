package com.github.faxundo.old_legends.item;

import com.github.faxundo.old_legends.util.OLHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class OLGenericItem extends Item {

    public OLGenericItem(Settings settings) {
        super(settings.fireproof());
    }

    @Override
    public Text getName(ItemStack itemStack) {
        return OLHelper.getNameHelper(this.getDefaultStack(), false);
    }
}
