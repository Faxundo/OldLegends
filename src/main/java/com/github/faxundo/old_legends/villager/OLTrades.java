package com.github.faxundo.old_legends.villager;

import com.github.faxundo.old_legends.item.OLItem;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;

import java.util.Optional;

public class OLTrades {
    public static void registerOLTrades() {
        TradeOfferHelper.registerVillagerOffers(OLVillager.SAGE, 1,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new TradedItem(Items.LAPIS_LAZULI, 1),
                            new ItemStack(Items.EMERALD, 1),
                            16, 1, 0.05f));
                });

        TradeOfferHelper.registerVillagerOffers(OLVillager.SAGE, 2,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new TradedItem(Items.EMERALD, 5),
                            new ItemStack(OLItem.BLANK_RUNE, 1),
                            6, 5, 0.05f));

                    factories.add((entity, random) -> new TradeOffer(
                            new TradedItem(OLItem.PALE_GEM, 1),
                            new ItemStack(Items.EMERALD, 15),
                            3, 10, 0.05f));
                });

        TradeOfferHelper.registerVillagerOffers(OLVillager.SAGE, 3,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new TradedItem(Items.EMERALD, 15),
                            new ItemStack(OLItem.DEATH_RUNE, 1),
                            3, 15, 0.05f));

                    factories.add((entity, random) -> new TradeOffer(
                            new TradedItem(Items.EMERALD, 15),
                            new ItemStack(OLItem.SKY_RUNE, 1),
                            3, 15, 0.05f));

                    factories.add((entity, random) -> new TradeOffer(
                            new TradedItem(Items.EMERALD, 15),
                            new ItemStack(OLItem.TIME_RUNE, 1),
                            3, 15, 0.05f));

                    factories.add((entity, random) -> new TradeOffer(
                            new TradedItem(Items.SCULK_SHRIEKER, 1),
                            new ItemStack(Items.EMERALD, 3),
                            16, 20, 0.05f));
                });

        TradeOfferHelper.registerVillagerOffers(OLVillager.SAGE, 4,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new TradedItem(Items.EMERALD, 32),
                            new ItemStack(OLItem.DEATH_RUNE_PAGE, 1),
                            1, 30, 0.05f));

                    factories.add((entity, random) -> new TradeOffer(
                            new TradedItem(Items.EMERALD, 32),
                            new ItemStack(OLItem.SKY_RUNE_PAGE, 1),
                            1, 30, 0.05f));

                    factories.add((entity, random) -> new TradeOffer(
                            new TradedItem(Items.EMERALD, 32),
                            new ItemStack(OLItem.TIME_RUNE_PAGE, 1),
                            1, 30, 0.05f));
                });

        TradeOfferHelper.registerVillagerOffers(OLVillager.SAGE, 5,
                factories -> {

                    factories.add((entity, random) -> new TradeOffer(
                            new TradedItem(Items.EMERALD, 32),
                            Optional.of(new TradedItem(Items.EMERALD, 32)),
                            new ItemStack(OLItem.EMERALD_MOURNING_PAGE, 1),
                            1, 50, 0.05f));
                });
    }
}
