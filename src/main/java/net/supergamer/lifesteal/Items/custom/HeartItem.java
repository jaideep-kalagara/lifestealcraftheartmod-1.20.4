package net.supergamer.lifesteal.Items.custom;


import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.supergamer.lifesteal.LifestealCraftHeartMod;

public class HeartItem extends Item {

    public HeartItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            EntityAttributeInstance healthAttribute = user.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);

            if (healthAttribute != null) {
                Identifier modifierId = new Identifier(LifestealCraftHeartMod.MOD_ID, "health_boost");

                healthAttribute.setBaseValue(healthAttribute.getValue() + 2.0);
                user.getStackInHand(hand).decrement(1);
                user.getWorld().playSound(null, user.getBlockPos(), SoundEvents.BLOCK_ANVIL_BREAK, SoundCategory.BLOCKS, 1f, 1f);

            }

        }



        return super.use(world, user, hand);
    }
}
