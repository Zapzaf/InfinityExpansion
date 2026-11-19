package io.github.mooy1.infinityexpansion.items.gear;

import javax.annotation.Nonnull;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import io.github.mooy1.infinityexpansion.InfinityExpansion;
import io.github.mooy1.infinityexpansion.categories.Groups;
import io.github.mooy1.infinityexpansion.items.blocks.InfinityWorkbench;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.attributes.ProtectionType;
import io.github.thebusybiscuit.slimefun4.core.attributes.ProtectiveArmor;
import io.github.thebusybiscuit.slimefun4.core.attributes.Soulbound;
import io.github.thebusybiscuit.slimefun4.implementation.items.armor.SlimefunArmorPiece;

/**
 * armor
 * <p>
 * Whether it gives potion effects and is soulbound comes from {@code gear-options} in the config: Slimefun treats
 * an item as soulbound when its class implements {@link Soulbound}, so the soulbound armor is a subclass.
 *
 * @author Mooy1
 */
public class InfinityArmor extends SlimefunArmorPiece implements ProtectiveArmor, NotPlaceable {

    private static final NamespacedKey KEY = InfinityExpansion.createKey("infinity_armor");

    /** Infinity armor with the given potion effects, soulbound or not. */
    @Nonnull
    public static InfinityArmor create(SlimefunItemStack item, PotionEffect[] effects, ItemStack[] recipe, boolean soulbound) {
        return soulbound ? new WithSoulbound(item, effects, recipe) : new InfinityArmor(item, effects, recipe);
    }

    public InfinityArmor(SlimefunItemStack item, PotionEffect[] effects, ItemStack[] recipe) {
        super(Groups.INFINITY_CHEAT, item, InfinityWorkbench.TYPE, recipe, effects);
    }

    @Nonnull
    @Override
    public ProtectionType[] getProtectionTypes() {
        return new ProtectionType[] {
                ProtectionType.BEES, ProtectionType.RADIATION, ProtectionType.FLYING_INTO_WALL
        };
    }

    @Override
    public boolean isFullSetRequired() {
        return false;
    }

    @Nonnull
    @Override
    public NamespacedKey getArmorSetId() {
        return KEY;
    }

    /** Infinity armor that stays in the inventory on death. */
    public static final class WithSoulbound extends InfinityArmor implements Soulbound {

        public WithSoulbound(SlimefunItemStack item, PotionEffect[] effects, ItemStack[] recipe) {
            super(item, effects, recipe);
        }

    }

}
