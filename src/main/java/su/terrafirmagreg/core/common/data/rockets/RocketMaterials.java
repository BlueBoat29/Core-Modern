package su.terrafirmagreg.core.common.data.rockets;

import java.util.*;

import com.gregtechceu.gtceu.data.recipe.CustomTags;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

import su.terrafirmagreg.core.TFGCore;
import su.terrafirmagreg.core.common.block.rocket.InsulatedRocketFrame;
import su.terrafirmagreg.core.common.block.rocket.PlatedRocketFrame;
import su.terrafirmagreg.core.common.data.blocks.TFGBlocks;

public class RocketMaterials {
    public static final Map<Item, RocketPlating> ROCKET_PLATING = new HashMap<>();
    public static final Map<Integer, RocketPlating> ROCKET_PLATING_BY_STATE = new HashMap<>();
    public static final List<RocketPlating> LIST_ROCKET_PLATING = new ArrayList<>();

    public static final Map<Item, RocketInsulation> ROCKET_INSULATION = new HashMap<>();
    public static final Map<Integer, RocketInsulation> ROCKET_INSULATION_BY_STATE = new HashMap<>();
    public static final List<RocketInsulation> LIST_ROCKET_INSULATION = new ArrayList<>();

    public static final Map<Item, RocketFrame> ROCKET_FRAME = new HashMap<>();
    public static final List<RocketFrame> LIST_ROCKET_FRAME = new ArrayList<>();

    public static final Map<ResourceLocation, List<?>> MENU_GROUPS = new LinkedHashMap<>();

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
        //Tier 1 Frame
        addFrame(TFGBlocks.ROCKET_FRAME.asItem(),
                TFGCore.id("tier_1_frame"),
                1);
        //Tier 2 Frame

        //Tier 1 Plate
        addPlate(ForgeRegistries.ITEMS.getValue(ResourceLocation.fromNamespaceAndPath("gtceu", "rocket_alloy_t1_plate")),
                TFGCore.id("tier_1_plate"),
                1,
                2,
                CustomTags.WRENCHES,
                2);

        //Tier 2 Plate
        addPlate(ForgeRegistries.ITEMS.getValue(ResourceLocation.fromNamespaceAndPath("gtceu", "rocket_alloy_t2_plate")),
                TFGCore.id("tier_2_plating"),
                2,
                2,
                CustomTags.WRENCHES,
                4);

        //Tier 1 Insulation
        addInsulation(ForgeRegistries.ITEMS.getValue(ResourceLocation.fromNamespaceAndPath("tfg", "basalt_fiber_plate")),
                TFGCore.id("tier_1_insulation"),
                1,
                1,
                2);

        //Tier 2 Insulation
        addInsulation(ForgeRegistries.ITEMS.getValue(ResourceLocation.fromNamespaceAndPath("tfg", "aes_insulation_roll")),
                TFGCore.id("tier_2_insulation"),
                2,
                1,
                4);

        MENU_GROUPS.put(TFGCore.id("frame"), LIST_ROCKET_FRAME);
        MENU_GROUPS.put(TFGCore.id("plating"), LIST_ROCKET_PLATING);
        MENU_GROUPS.put(TFGCore.id("insulation"), LIST_ROCKET_INSULATION);
    }

    private static void addFrame(Item frameItem, ResourceLocation name, int strength) {
        RocketFrame newFrame = new RocketFrame(
                frameItem,
                name,
                strength);

        ROCKET_FRAME.put(frameItem, newFrame);
        LIST_ROCKET_FRAME.add(newFrame);
    }

    private static void addPlate(Item plateItem, ResourceLocation name, int stateID, int count, TagKey<Item> offhandTool, int strength) {
        RocketPlating newPlate = new RocketPlating(
                stateID,
                plateItem,
                name,
                count,
                offhandTool,
                strength);

        ROCKET_PLATING.put(plateItem, newPlate);
        ROCKET_PLATING_BY_STATE.put(newPlate.stateID(), newPlate);
        LIST_ROCKET_PLATING.add(newPlate);
    }

    private static void addInsulation(Item insulationItem, ResourceLocation name, int stateID, int count, int shielding) {
        RocketInsulation newInsulation = new RocketInsulation(
                stateID,
                insulationItem,
                name,
                count,
                shielding);

        ROCKET_INSULATION.put(insulationItem, newInsulation);
        ROCKET_INSULATION_BY_STATE.put(newInsulation.stateID(), newInsulation);
        LIST_ROCKET_INSULATION.add(newInsulation);
    }

}
