package net.supergamer.lifesteal.Items.custom;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.supergamer.lifesteal.networking.ModMessages;

public class HeartItem extends Item {

    public HeartItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
//            user.getInventory().main.clear();
            user.getStackInHand(hand).damage(1, user,
                    playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
            ClientPlayNetworking.send(ModMessages.HEART_ID, PacketByteBufs.create());
        }

        return super.use(world, user, hand);
    }
}
