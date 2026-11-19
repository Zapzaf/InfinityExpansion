package io.github.mooy1.infinityexpansion.items.gear;

import javax.annotation.Nonnull;

import org.bukkit.inventory.ItemStack;

import io.github.mooy1.infinityexpansion.categories.Groups;
import io.github.mooy1.infinityexpansion.items.blocks.InfinityWorkbench;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.attributes.Soulbound;

/**
 * tools and weapons (blade, axe, pickaxe, shovel, shield)
 * <p>
 * Soulbound or not comes from {@code gear-options.soulbound} in the config: Slimefun treats an item as soulbound
 * when its class implements {@link Soulbound}, so the soulbound tool is a subclass.
 *
 * @author Mooy1
 */
public class InfinityTool extends SlimefunItem implements NotPlaceable {

    /** An Infinity tool, soulbound or not. */
    @Nonnull
    public static InfinityTool create(SlimefunItemStack stack, ItemStack[] recipe, boolean soulbound) {
        return soulbound ? new WithSoulbound(stack, recipe) : new InfinityTool(stack, recipe);
    }

    public InfinityTool(SlimefunItemStack stack, ItemStack[] recipe) {
        super(Groups.INFINITY_CHEAT, stack, InfinityWorkbench.TYPE, recipe);
    }

    /** An Infinity tool that stays in the inventory on death. */
    public static final class WithSoulbound extends InfinityTool implements Soulbound {

        public WithSoulbound(SlimefunItemStack stack, ItemStack[] recipe) {
            super(stack, recipe);
        }

    }

}
