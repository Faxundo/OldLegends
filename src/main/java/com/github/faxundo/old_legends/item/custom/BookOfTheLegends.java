package com.github.faxundo.old_legends.item.custom;

import com.github.faxundo.old_legends.item.OLGenericItem;
import com.github.faxundo.old_legends.screen.BookOfTheLegendsScreenHandler;
import com.github.faxundo.old_legends.sound.OLSound;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class BookOfTheLegends extends OLGenericItem {

    public BookOfTheLegends(Settings settings) {
        super(settings.maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            user.playSound(OLSound.BOOK_OF_THE_LEGENDS_OPEN,1f,0f);
            user.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, playerInventory, playerEntity) ->
                    new BookOfTheLegendsScreenHandler(syncId, playerInventory), this.getName()));
        }
            return super.use(world, user, hand);
    }
}
