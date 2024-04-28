package com.github.faxundo.old_legends.item.custom;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.block.OLBlock;
import com.github.faxundo.old_legends.block.entity.EchoOreBlockEntity;
import com.github.faxundo.old_legends.item.generic.OLGenericMiningTool;
import com.github.faxundo.old_legends.item.generic.OLGenericRune;
import com.github.faxundo.old_legends.item.OLItem;
import com.github.faxundo.old_legends.particle.OLParticle;
import com.github.faxundo.old_legends.util.OLHelper;
import com.github.faxundo.old_legends.util.OLHelperParticle;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FlutterEcho extends OLGenericMiningTool {

    private BlockState blockMinedState;
    private BlockPos blockMinedPos;

    public FlutterEcho(float attackDamage, float attackSpeed, ToolMaterial material, TagKey<Block> effectiveBlocks, Settings settings) {
        super(attackDamage, attackSpeed, material, effectiveBlocks, settings);
        blockMinedState = Blocks.AIR.getDefaultState();
        blockMinedPos = new BlockPos(0, -100, 0);
        setId("flutter_echo");
        setAmountPassives(2);
    }

    public BlockState getBlockMinedState() {
        return blockMinedState;
    }

    public void setBlockMinedState(BlockState blockMinedState) {
        this.blockMinedState = blockMinedState;
    }

    public BlockPos getBlockMinedPos() {
        return blockMinedPos;
    }

    public void setBlockMinedPos(BlockPos blockMinedPos) {
        this.blockMinedPos = blockMinedPos;
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.getItem() instanceof OLGenericRune && ingredient.getTranslationKey().contains(OLItem.TIME_RUNE.getTranslationKey());
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        return 10.0f;
    }

    @Override
    public int getEnchantability() {
        return 18;
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (!world.isClient) {
            if (OLHelper.isOreBlock(getBlockMinedState())
                    && miner instanceof PlayerEntity player) {
                if (!player.isCreative()) {
                    int mineChance = isAwake() ? OldLegends.CONFIG.flutterEcho.mineChance : OldLegends.CONFIG.flutterEcho.mineChanceAwake;
                    if (OLHelper.getRandomNumber(1, 100) <= mineChance) {
                        echoOreCreation(world, stack, pos);
                    }
                }
            }
        }
        return super.postMine(stack, world, state, pos, miner);
    }

    public void echoOreCreation(World world, ItemStack stack, BlockPos pos) {
        BlockState echoOre = OLBlock.ECHO_ORE.getDefaultState();
        world.setBlockState(getBlockMinedPos(), echoOre);
        BlockEntity blockEntity = world.getBlockEntity(getBlockMinedPos());
        if (blockEntity instanceof EchoOreBlockEntity echoOreBlockEntity) {
            if (stack.hasEnchantments()) {
                NbtList listEnchantments = stack.getEnchantments();
                for (int i = 0; i < listEnchantments.size(); i++) {
                    String enchantment = listEnchantments.getCompound(i).getString("id");
                    short level = listEnchantments.getCompound(i).getShort("lvl");
                    if (enchantment.contains("fortune")) {
                        echoOreBlockEntity.setHasFortune(true);
                        echoOreBlockEntity.setFortuneLevel(level);
                        break;
                    }
                }
            }
            world.playSound(null, pos, SoundEvents.BLOCK_END_PORTAL_FRAME_FILL, SoundCategory.BLOCKS, 0.4f, -0.1f);
            echoOreBlockEntity.setOre(getBlockMinedState());
        }
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.getWorld().isClient()) {
            if (attacker instanceof PlayerEntity player) {
                int hitChance = isAwake() ? OldLegends.CONFIG.flutterEcho.attackChance : OldLegends.CONFIG.flutterEcho.attackChanceAwake;
                if (OLHelper.getRandomNumber(1, 100) <= hitChance) {
                    player.playSound(SoundEvents.BLOCK_END_PORTAL_FRAME_FILL, SoundCategory.PLAYERS, 0.7f, -0.2f);
                    OLHelperParticle.spawnParticle(player.getWorld(), OLParticle.ECHO_PICKAXE, target.getX() + 0.23, target.getY() + 2.3, target.getZ() + 0.15,
                            0.3d, 0.3d, 0.3d);
                    target.damage(attacker.getDamageSources().playerAttack(player), this.getAttackDamage() / 2);

                }
            }
        }
        return super.postHit(stack, target, attacker);
    }
}
