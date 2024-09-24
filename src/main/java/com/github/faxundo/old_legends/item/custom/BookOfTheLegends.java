package com.github.faxundo.old_legends.item.custom;

import com.github.faxundo.old_legends.item.generic.OLGenericItem;
import com.github.faxundo.old_legends.item.generic.OLGenericPage;
import com.github.faxundo.old_legends.screen.custom.BookOfTheLegendsScreenHandler;
import com.github.faxundo.old_legends.sound.OLSound;
import com.github.faxundo.old_legends.util.OLDataComponent;
import com.github.faxundo.old_legends.util.OLHelper;
import com.github.faxundo.old_legends.util.OLTag;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

public class BookOfTheLegends extends OLGenericItem {

    public static final String PAGES = "tooltip.old_legends.book_of_the_legends.pages";
    public static final String OF = "tooltip.old_legends.book_of_the_legends.of";
    public static final String NO_OWNER = "tooltip.old_legends.book_of_the_legends.no_owner";
    public static final String HAS_PAGE = "tooltip.old_legends.book_of_the_legends.has_page";
    public static final String NEW_PAGE = "tooltip.old_legends.book_of_the_legends.new_page";
    public static final String USAGE = "tooltip.old_legends.book_of_the_legends.usage";

    public BookOfTheLegends(Settings settings) {
        super(settings.maxCount(1).component(OLDataComponent.OWNER, "").component(OLDataComponent.PAGES, ""));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            ItemStack itemStack = user.getMainHandStack();
            String owner = itemStack.get(OLDataComponent.OWNER);

            if (itemStack.contains(OLDataComponent.OWNER)) {
                if (itemStack.get(OLDataComponent.OWNER).isEmpty() && user.isSneaky() && !user.isCreative()) {
                    itemStack.set(OLDataComponent.OWNER, user.getUuid().toString());
                    Text text = Text.translatable(this.getName().getString())
                            .append(" ")
                            .append(Text.translatable(OF))
                            .append(" ")
                            .append(user.getDisplayName())
                            .setStyle(OLHelper.getStyle("special"));
                    itemStack.set(DataComponentTypes.CUSTOM_NAME, text);

                } else if (owner.equals(user.getUuid().toString())) {
                    user.playSound(OLSound.BOOK_OF_THE_LEGENDS_OPEN,5.0f,0.0f);
                    user.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, playerInventory, playerEntity) ->
                            new BookOfTheLegendsScreenHandler(syncId, playerInventory), this.getName()));
                } else {
                    user.sendMessage(Text.translatable(NO_OWNER).setStyle(OLHelper.getStyle("error")));
                }
            }
        }
        return super.use(world, user, hand);
    }

    @Override
    public boolean onStackClicked(ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player) {
        if (clickType != ClickType.RIGHT) {
            return false;
        } else {
            if (!stack.contains(OLDataComponent.OWNER) && !player.isCreative()) return false;
            String owner = stack.get(OLDataComponent.OWNER);
            if (!owner.equals(player.getUuid().toString())) return false;

            ItemStack itemStack = slot.getStack();

            if (itemStack.getItem() instanceof OLGenericPage) {
                if (!hasPage(stack, itemStack)) {
                    addPage(stack, itemStack);
                    if (player instanceof ClientPlayerEntity clientPlayerEntity) {
                        clientPlayerEntity.sendMessage(itemName(itemStack), false);
                    }
                    itemStack.decrement(1);
                } else {
                    if (player instanceof ClientPlayerEntity clientPlayerEntity) {
                        clientPlayerEntity.sendMessage(Text.translatable(HAS_PAGE).setStyle(OLHelper.getStyle("error")));
                    }
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
                .append(Text.translatable(NEW_PAGE).setStyle(OLHelper.getStyle("message")))
                .append("]").setStyle(OLHelper.getStyle("rare"));
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        OLHelper.appendTooltipHelper(stack,tooltip);

        TagKey<Item> pageTag = OLTag.Items.PAGE;
        List<Item> itemsInTag = Registries.ITEM
                .stream()
                .filter(item -> Registries.ITEM.getEntryList(pageTag)
                        .map(list -> list.contains(Registries.ITEM.getEntry(item)))
                        .orElse(false))
                .toList();

        tooltip.add(Text.translatable(PAGES)
                .append(": " + countPages(stack) + "/" + itemsInTag.size())
                .setStyle(OLHelper.getStyle("charges")));
        super.appendTooltip(stack, context, tooltip, type);
    }

    public void addPage(ItemStack book, ItemStack pageStack) {
        if (book.contains(OLDataComponent.PAGES)) {
            String pages = book.get(OLDataComponent.PAGES);
            String page = pageStack.getTranslationKey();
            if (pageStack.getItem() instanceof OLGenericPage) {
                if (!pages.contains(",")) {
                    book.set(OLDataComponent.PAGES, page + ",");
                } else {
                    book.set(OLDataComponent.PAGES, pages + page + ",");
                }
            }
        }
    }

    public boolean hasPage(ItemStack book, ItemStack pageStack) {
        if (book.contains(OLDataComponent.PAGES)) {
            String pages = book.get(OLDataComponent.PAGES);
            String page = pageStack.getTranslationKey();
            return pages.contains(page);
        }
        return false;
    }

    public int countPages(ItemStack book) {
        if (book.contains(OLDataComponent.PAGES)) {
            String pages = book.get(OLDataComponent.PAGES);
            if (pages != null && pages.contains(",")) {
                String[] listPages = pages.split(",");
                return listPages.length;
            }
        }
        return 0;
    }
}
