package com.github.faxundo.old_legends.event;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.block.OLBlock;
import com.github.faxundo.old_legends.block.custom.RuneTableBlock;
import com.github.faxundo.old_legends.util.OLHelper;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class UseBlockHandler implements UseBlockCallback {

    private int range;
    private int maxVillagers;

    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {
        range = OldLegends.CONFIG.runeTable.range;
        maxVillagers = OldLegends.CONFIG.runeTable.maxVillagers;

        if (!player.isSpectator() && !player.isCreative()) {
            BlockPos blockPos = hitResult.getBlockPos();
            ItemStack itemStack = OLHelper.getItemInHand(player, OLBlock.RUNE_TABLE.asItem());

            if (!itemStack.isEmpty()) {

                Box box = new Box(blockPos.getX() - range, blockPos.getY() - range, blockPos.getZ() - range, blockPos.getX() + range, blockPos.getY() + range, blockPos.getZ() + range);

                List<VillagerEntity> villagers = world.getEntitiesByType(EntityType.VILLAGER, box, villagerEntity -> true);

                int villages = Math.round((float) (villagers.size() / maxVillagers));

                // If not-exists villagers, you can put the rune table.
                if (villagers.isEmpty()) return ActionResult.PASS;

                // One wise each max villagers amount.
                // Default = 8
                if (!(OLHelper.amountOfWise(world, box) <= villages)) {
                    player.sendMessage(Text.translatable(RuneTableBlock.INVALID_WISE).setStyle(OLHelper.getStyle("error")));
                    player.playSound(SoundEvents.ENTITY_VILLAGER_NO);
                    return ActionResult.FAIL;
                }

            }
        }
        return ActionResult.PASS;
    }
}
