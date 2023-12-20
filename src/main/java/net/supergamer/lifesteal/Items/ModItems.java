package net.supergamer.lifesteal.Items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.supergamer.lifesteal.Items.custom.HeartItem;
import net.supergamer.lifesteal.LifestealCraftHeartMod;

public class ModItems {


        public static final Item HEART = registerItem("heart", new HeartItem(new FabricItemSettings().maxDamage(1).fireproof()));


        private static void addItemToIngredientItemGroup(FabricItemGroupEntries entries) {
            entries.add(HEART);
        }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(LifestealCraftHeartMod.MOD_ID, name), item);
    }

    public static void  registerModItems() {
        LifestealCraftHeartMod.LOGGER.info("Registering Mod Items for " + "lifesteal");

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(ModItems::addItemToIngredientItemGroup);
    }
}
