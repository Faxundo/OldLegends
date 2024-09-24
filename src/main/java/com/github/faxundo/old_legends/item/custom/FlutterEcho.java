package com.github.faxundo.old_legends.item.custom;

import com.github.faxundo.old_legends.OldLegends;
import com.github.faxundo.old_legends.block.OLBlock;
import com.github.faxundo.old_legends.block.entity.EchoOreBlockEntity;
import com.github.faxundo.old_legends.item.OLItem;
import com.github.faxundo.old_legends.item.generic.OLGenericMiningTool;
import com.github.faxundo.old_legends.item.generic.OLGenericRune;
import com.github.faxundo.old_legends.particle.OLParticle;
import com.github.faxundo.old_legends.util.OLHelper;
import com.github.faxundo.old_legends.util.OLHelperParticle;
import com.github.faxundo.old_legends.util.OLTag;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FlutterEcho extends OLGenericMiningTool {

    public static final String ABILITY_NAME_1 = "tooltip.old_legends.flutter_echo_name_1";
    public static final String ABILITY_1 = "tooltip.old_legends.flutter_echo_1";
    public static final String ABILITY_NAME_2 = "tooltip.old_legends.flutter_echo_name_2";
    public static final String ABILITY_2 = "tooltip.old_legends.flutter_echo_2";

    private BlockState blockMinedState;
    private BlockPos blockMinedPos;

    public FlutterEcho(ToolMaterial material, TagKey<Block> effectiveBlocks, Item.Settings settings) {
        super(material, effectiveBlocks, settings.attributeModifiers(FlutterEcho.createAttributeModifiers(ToolMaterials.NETHERITE, 1.0f, -2.f)));
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
    public float getMiningSpeed(ItemStack stack, BlockState state) {
        return 10.0f;
    }

    @Override
    public int getEnchantability() {
        return 18;
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (!world.isClient) {

            boolean hasSilkTouch = false;
            ItemEnchantmentsComponent itemEnchantmentsComponent = stack.getOrDefault(DataComponentTypes.ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT);
            for (RegistryEntry<Enchantment> entry : itemEnchantmentsComponent.getEnchantments()) {
                if (entry.matchesId(Enchantments.SILK_TOUCH.getValue())) {
                    hasSilkTouch = true;
                }
            }

            //If it doesn't have silk touch, create the echo.
            if (!hasSilkTouch) {
                if (getBlockMinedState().isIn(OLTag.Blocks.ORES) && miner instanceof PlayerEntity player) {
                    if (!player.isCreative()) {
                        int mineChance = isAwake() ? OldLegends.CONFIG.flutterEcho.mineChance : OldLegends.CONFIG.flutterEcho.mineChanceAwake;
                        if (OLHelper.getRandomNumber(1, 100) <= mineChance) {
                            echoOreCreation(world, stack, pos, player);
                        }
                    }
                }
            }
        }
        return super.postMine(stack, world, state, pos, miner);
    }

    public void echoOreCreation(World world, ItemStack stack, BlockPos pos, PlayerEntity player) {
        BlockState echoOre = OLBlock.ECHO_ORE.getDefaultState();
        world.setBlockState(getBlockMinedPos(), echoOre);
        BlockEntity blockEntity = world.getBlockEntity(getBlockMinedPos());
        if (blockEntity instanceof EchoOreBlockEntity echoOreBlockEntity) {
            world.playSound(null, pos, SoundEvents.BLOCK_END_PORTAL_FRAME_FILL, SoundCategory.BLOCKS, 0.4f, -0.1f);
            echoOreBlockEntity.setStack(stack);
            echoOreBlockEntity.setPlayer(player);
            echoOreBlockEntity.setOre(getBlockMinedState());
        }
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.getWorld().isClient()) {
            if (attacker instanceof PlayerEntity player) {
                int hitChance = isAwake() ? OldLegends.CONFIG.flutterEcho.attackChance : OldLegends.CONFIG.flutterEcho.attackChanceAwake;
                if (OLHelper.getRandomNumber(1, 100) <= hitChance) {
                    player.playSound(SoundEvents.BLOCK_END_PORTAL_FRAME_FILL, 0.7f, -0.2f);
                    OLHelperParticle.spawnParticle(player.getWorld(), OLParticle.ECHO_PICKAXE, target.getX() + 0.23, target.getY() + 2.3, target.getZ() + 0.15,
                            0.3d, 0.3d, 0.3d);
                    target.damage(attacker.getDamageSources().playerAttack(player), getMaterial().getAttackDamage() / 2);

                }
            }
        }
        return super.postHit(stack, target, attacker);
    }
}
