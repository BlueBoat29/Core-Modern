package su.terrafirmagreg.core.common.data.rockets;

import java.util.HashMap;
import java.util.Map;

import com.gregtechceu.gtceu.data.recipe.CustomTags;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

import su.terrafirmagreg.core.common.block.rocket.InsulatedRocketFrame;
import su.terrafirmagreg.core.common.block.rocket.PlatedRocketFrame;

public class RocketMaterials {
    public static final Map<Item, RocketPlating> ROCKET_PLATING = new HashMap<>();
    public static final Map<Integer, RocketPlating> ROCKET_PLATING_BY_STATE = new HashMap<>();

    public static final Map<Item, RocketInsulation> ROCKET_INSULATION = new HashMap<>();
    public static final Map<Integer, RocketInsulation> ROCKET_INSULATION_BY_STATE = new HashMap<>();

    public static final TagKey<Item> REMOVAL_TOOL = CustomTags.CROWBARS;

    public static ItemStack getInsulationStack(BlockState state) {
        RocketInsulation insulation = ROCKET_INSULATION_BY_STATE.get(state.getValue(InsulatedRocketFrame.INSULATION_ID));
        return new ItemStack(insulation.insulationItem(), insulation.count());
    }

    public static ItemStack getPlatingStack(BlockState state) {
        RocketPlating plating = ROCKET_PLATING_BY_STATE.get(state.getValue(PlatedRocketFrame.PLATING_ID));
        return new ItemStack(plating.plateItem(), plating.count());
    }

    public static void init() {
        //Tier 1 Plate
        Item tier1PlateItem = ForgeRegistries.ITEMS.getValue(ResourceLocation.fromNamespaceAndPath("gtceu", "rocket_alloy_t1_plate"));

        RocketPlating tier1Plate = new RocketPlating(
                1,
                tier1PlateItem,
                ResourceLocation.fromNamespaceAndPath("tfg", "tier_1_plating"),
                2,
                CustomTags.WRENCHES);

        ROCKET_PLATING.put(tier1PlateItem, tier1Plate);
        ROCKET_PLATING_BY_STATE.put(tier1Plate.stateID(), tier1Plate);

        //Tier 2 Plate
        Item tier2PlateItem = ForgeRegistries.ITEMS.getValue(ResourceLocation.fromNamespaceAndPath("gtceu", "rocket_alloy_t2_plate"));

        RocketPlating tier2Plate = new RocketPlating(
                2,
                tier2PlateItem,
                ResourceLocation.fromNamespaceAndPath("tfg", "tier_2_plating"),
                2,
                CustomTags.WRENCHES);

        ROCKET_PLATING.put(tier2PlateItem, tier2Plate);
        ROCKET_PLATING_BY_STATE.put(tier2Plate.stateID(), tier2Plate);

        Item tier1InsulationItem = ForgeRegistries.ITEMS.getValue(ResourceLocation.fromNamespaceAndPath("tfg", "basalt_fiber_plate"));
        RocketInsulation tier1Insulation = new RocketInsulation(
                1,
                tier1InsulationItem,
                ResourceLocation.fromNamespaceAndPath("tfg", "tier_1_insulation"),
                1);

        ROCKET_INSULATION.put(tier1InsulationItem, tier1Insulation);
        ROCKET_INSULATION_BY_STATE.put(tier1Insulation.stateID(), tier1Insulation);

        Item tier2InsulationItem = ForgeRegistries.ITEMS.getValue(ResourceLocation.fromNamespaceAndPath("tfg", "aes_insulation_roll"));
        RocketInsulation tier2Insulation = new RocketInsulation(
                2,
                tier2InsulationItem,
                ResourceLocation.fromNamespaceAndPath("tfg", "tier_2_insulation"),
                1);

        ROCKET_INSULATION.put(tier2InsulationItem, tier2Insulation);
        ROCKET_INSULATION_BY_STATE.put(tier2Insulation.stateID(), tier2Insulation);

    }
}
