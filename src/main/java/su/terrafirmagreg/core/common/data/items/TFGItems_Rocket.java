package su.terrafirmagreg.core.common.data.items;

import com.gregtechceu.gtceu.common.data.models.GTModels;
import com.tterrag.registrate.util.entry.ItemEntry;

import net.minecraft.world.item.Item;

import su.terrafirmagreg.core.TFGCore;

public class TFGItems_Rocket {
    public static void init() {
    }

    public static final ItemEntry<Item> ROCKET_FRAME_ICON = TFGCore.REGISTRATE.item("rocket_frame_icon", Item::new)
            .properties(p -> p.stacksTo(1))
            .model((ctx, prov) -> GTModels.createTextureModel(ctx, prov, TFGCore.id("item/rocket/gui/frame_icon")))
            .register();
    public static final ItemEntry<Item> ROCKET_PLATING_ICON = TFGCore.REGISTRATE.item("rocket_plating_icon", Item::new)
            .properties(p -> p.stacksTo(1))
            .model((ctx, prov) -> GTModels.createTextureModel(ctx, prov, TFGCore.id("item/rocket/gui/plate_icon")))
            .register();
    public static final ItemEntry<Item> ROCKET_INSULATION_ICON = TFGCore.REGISTRATE.item("rocket_insulation_icon", Item::new)
            .properties(p -> p.stacksTo(1))
            .model((ctx, prov) -> GTModels.createTextureModel(ctx, prov, TFGCore.id("item/rocket/gui/insulation_icon")))
            .register();
    public static final ItemEntry<Item> ROCKET_ENGINE_ICON = TFGCore.REGISTRATE.item("rocket_engine_icon", Item::new)
            .properties(p -> p.stacksTo(1))
            .model((ctx, prov) -> GTModels.createTextureModel(ctx, prov, TFGCore.id("item/rocket/gui/engine_icon")))
            .register();
    public static final ItemEntry<Item> ROCKET_BOOSTER_ICON = TFGCore.REGISTRATE.item("rocket_booster_icon", Item::new)
            .properties(p -> p.stacksTo(1))
            .model((ctx, prov) -> GTModels.createTextureModel(ctx, prov, TFGCore.id("item/rocket/gui/booster_icon")))
            .register();
    public static final ItemEntry<Item> ROCKET_CARGO_ICON = TFGCore.REGISTRATE.item("rocket_cargo_icon", Item::new)
            .properties(p -> p.stacksTo(1))
            .model((ctx, prov) -> GTModels.createTextureModel(ctx, prov, TFGCore.id("item/rocket/gui/cargo_icon")))
            .register();
    public static final ItemEntry<Item> ROCKET_FUEL_ICON = TFGCore.REGISTRATE.item("rocket_fuel_icon", Item::new)
            .properties(p -> p.stacksTo(1))
            .model((ctx, prov) -> GTModels.createTextureModel(ctx, prov, TFGCore.id("item/rocket/gui/fuel_icon")))
            .register();

    public static final ItemEntry<Item> ROCKET_OXIDIZER_ICON = TFGCore.REGISTRATE.item("rocket_oxidizer_icon", Item::new)
            .properties(p -> p.stacksTo(1))
            .model((ctx, prov) -> GTModels.createTextureModel(ctx, prov, TFGCore.id("item/rocket/gui/oxidizer_icon")))
            .register();

    public static final ItemEntry<Item> ROCKET_CREW_ICON = TFGCore.REGISTRATE.item("rocket_crew_icon", Item::new)
            .properties(p -> p.stacksTo(1))
            .model((ctx, prov) -> GTModels.createTextureModel(ctx, prov, TFGCore.id("item/rocket/gui/crew_icon")))
            .register();

    public static final ItemEntry<Item> ROCKET_CREW_SOLO_ICON = TFGCore.REGISTRATE.item("rocket_crew_solo_icon", Item::new)
            .properties(p -> p.stacksTo(1))
            .model((ctx, prov) -> GTModels.createTextureModel(ctx, prov, TFGCore.id("item/rocket/gui/crew_icon_solo")))
            .register();

    public static final ItemEntry<Item> ROCKET_CREW_DUO_ICON = TFGCore.REGISTRATE.item("rocket_crew_duo_icon", Item::new)
            .properties(p -> p.stacksTo(1))
            .model((ctx, prov) -> GTModels.createTextureModel(ctx, prov, TFGCore.id("item/rocket/gui/crew_icon_duo")))
            .register();
}
