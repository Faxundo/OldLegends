package com.github.faxundo.old_legends.item.custom;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.item.generic.OLGenericItem;
import com.github.faxundo.old_legends.item.generic.OLGenericPage;
import com.github.faxundo.old_legends.screen.custom.BookOfTheLegendsScreenHandler;
import com.github.faxundo.old_legends.util.OLDataComponent;
import com.github.faxundo.old_legends.util.OLHelper;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.Arrays;

public class BookOfTheLegends extends OLGenericItem {

    public BookOfTheLegends(Settings settings) {
        super(settings.maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            ItemStack itemStack = user.getMainHandStack();

            if (itemStack.contains(OLDataComponent.OWNER)) {
                String owner = itemStack.get(OLDataComponent.OWNER);
                if (owner.equals(user.getUuid().toString())) {
                    user.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, playerInventory, playerEntity) ->
                            new BookOfTheLegendsScreenHandler(syncId, playerInventory), this.getName()));
                } else {
                    user.sendMessage(Text.translatable("tooltip.old_legends.book_of_the_legends.no_owner").setStyle(OLHelper.getStyle("error")));
                }
            } else {
                itemStack.set(OLDataComponent.OWNER, user.getUuid().toString());
                Text text = Text.translatable("item.old_legends.book_of_the_legends")
                        .append(" ")
                        .append(Text.translatable("tooltip.old_legends.book_of_the_legends.of"))
                        .append(" ")
                        .append(user.getDisplayName())
                        .setStyle(OLHelper.getStyle("special"));
                itemStack.set(DataComponentTypes.CUSTOM_NAME, text);
            }
        }
        return super.use(world, user, hand);
    }

    @Override
    public boolean onStackClicked(ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player) {
        if (clickType != ClickType.RIGHT) {
            return false;
        } else {
            if (!stack.contains(OLDataComponent.OWNER)) return false;
            ItemStack itemStack = slot.getStack();
            if (itemStack.getItem() instanceof OLGenericPage) {
                if (!hasPage(OLDataComponent.EMERALD_MOURNING_PAGE, stack)) {
                    addPage(OLDataComponent.EMERALD_MOURNING_PAGE, itemStack);
                    player.sendMessage(itemName(itemStack), false);
                    itemStack.decrement(1);
                } else {
                    player.sendMessage(Text.translatable("tooltip.old_legends.book_of_the_legends.has_page").setStyle(OLHelper.getStyle("error")));
                }
            }

            return true;
        }
    }

    public Text itemName(ItemStack stack) {
        MutableText itemKeys = Text.literal(Arrays.toString(stack.getTranslationKey().split("\\.")));
        String[] listKeys = itemKeys.toString().split(",");
        String item = listKeys[2].replace('_', ' ').
                replace("]", "").
                replace("}", "").
                replaceFirst("\\s", "");
        String[] itemNameList = item.split("\\s");
        String finalText = "";

        for (String word : itemNameList) {
            finalText = finalText + word.substring(0, 1).toUpperCase() + word.substring(1) + " ";
        }

        return Text.literal("[").setStyle(OLHelper.getStyle("rare"))
                .append(Text.literal(finalText).setStyle(OLHelper.getStyle("ability_name_awake")))
                .append(Text.translatable("tooltip.old_legends.book_of_the_legends.new_page").setStyle(OLHelper.getStyle("message")))
                .append("]").setStyle(OLHelper.getStyle("rare"));
    }

    public void addPage(ComponentType<Boolean> component, ItemStack book) {
        if (!book.contains(component)) {
            book.set(component,true);
        }
    }

    public boolean hasPage(ComponentType<Boolean> component, ItemStack book) {
       return book.contains(component);
    }
}
