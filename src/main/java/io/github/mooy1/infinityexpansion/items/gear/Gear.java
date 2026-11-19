package io.github.mooy1.infinityexpansion.items.gear;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;

import lombok.experimental.UtilityClass;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.mooy1.infinityexpansion.InfinityExpansion;
import io.github.mooy1.infinityexpansion.categories.Groups;
import io.github.mooy1.infinityexpansion.items.blocks.InfinityWorkbench;
import io.github.mooy1.infinityexpansion.items.materials.Materials;
import io.github.mooy1.infinityexpansion.utils.Util;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;

@UtilityClass
public final class Gear {

    public static final SlimefunItemStack ENDER_FLAME = new SlimefunItemStack(
            "ENDER_FLAME",
            Material.ENCHANTED_BOOK,
            "&cEnder Flame"
    );
    public static final SlimefunItemStack CROWN = new SlimefunItemStack(
            "INFINITY_CROWN",
            Material.NETHERITE_HELMET,
            "&bInfinity Crown",
            "&7Night Vision I",
            "&7Conduit Power I",
            "&7Elytra Crash Immunity"
    );
    public static final SlimefunItemStack CHESTPLATE = new SlimefunItemStack(
            "INFINITY_CHESTPLATE",
            Material.NETHERITE_CHESTPLATE,
            "&bInfinity Chestplate",
            "&7Strength II",
            "&7Resistance I",
            "&7Fire Resistance I",
            "&7Bee Sting Immunity"
    );
    public static final SlimefunItemStack LEGGINGS = new SlimefunItemStack(
            "INFINITY_LEGGINGS",
            Material.NETHERITE_LEGGINGS,
            "&bInfinity Leggings",
            "&7Haste III",
            "&7Regeneration I",
            "&7Saturation I",
            "&7Radiation Immunity"
    );
    public static final SlimefunItemStack BOOTS = new SlimefunItemStack(
            "INFINITY_BOOTS",
            Material.NETHERITE_BOOTS,
            "&bInfinity Boots",
            "&7Speed III",
            "&7Dolphins Grace I"
    );
    public static final SlimefunItemStack INFINITY_MATRIX = new SlimefunItemStack(
            "INFINITY_MATRIX",
            Material.NETHER_STAR,
            "&fInfinity Matrix",
            "&6Gives Unlimited Flight",
            "&7Right-Click to enable/disable and claim",
            "&7Crouch and Right-Click to remove ownership",
            "&bSoulbound"
    );
    public static final SlimefunItemStack SHIELD = new SlimefunItemStack(
            "INFINITY_SHIELD",
            Material.SHIELD,
            "&bCosmic Aegis"
    );
    public static final SlimefunItemStack BLADE = new SlimefunItemStack(
            "INFINITY_BLADE",
            Material.NETHERITE_SWORD,
            "&bBlade of the Cosmos"
    );
    public static final SlimefunItemStack PICKAXE = new SlimefunItemStack(
            "INFINITY_PICKAXE",
            Material.NETHERITE_PICKAXE,
            "&9World Breaker"
    );
    public static final SlimefunItemStack AXE = new SlimefunItemStack(
            "INFINITY_AXE",
            Material.NETHERITE_AXE,
            "&4Nature's Ruin"
    );
    public static final SlimefunItemStack SHOVEL = new SlimefunItemStack(
            "INFINITY_SHOVEL",
            Material.NETHERITE_SHOVEL,
            "&aMountain Eater"
    );
    public static final SlimefunItemStack BOW = new SlimefunItemStack(
            "INFINITY_BOW",
            Material.BOW,
            "&6Sky Piercer"
    );
    public static final SlimefunItemStack VEIN_MINER_RUNE = new SlimefunItemStack(
            "VEIN_MINER_RUNE",
            Material.DIAMOND,
            "&bVein Miner Rune",
            "&7Upgrades a tool to vein-mine certain materials"
    );

    public static void setup(InfinityExpansion plugin) {
        // gear-options in the config
        boolean armorEffects = plugin.getConfig().getBoolean("gear-options.armor-potion-effects");
        boolean bowEffects = plugin.getConfig().getBoolean("gear-options.bow-potion-effects");
        boolean soulbound = plugin.getConfig().getBoolean("gear-options.soulbound");

        if (!armorEffects) {
            removeEffectLore(CROWN, CHESTPLATE, LEGGINGS, BOOTS);
        }

        addInfinityEnchants(plugin, soulbound,
                CROWN, CHESTPLATE, LEGGINGS, BOOTS,
                AXE, BLADE, PICKAXE,
                SHIELD, SHOVEL, BOW
        );
        EnchantmentStorageMeta storageMeta = (EnchantmentStorageMeta) ENDER_FLAME.getItemMeta();
        Objects.requireNonNull(storageMeta).addStoredEnchant(Enchantment.FIRE_ASPECT, 10, true);
        ENDER_FLAME.setItemMeta(storageMeta);
        new SlimefunItem(Groups.MAIN_MATERIALS, ENDER_FLAME, RecipeType.MAGIC_WORKBENCH, new ItemStack[] {
                Materials.ENDER_ESSENCE.item(), Materials.ENDER_ESSENCE.item(), Materials.ENDER_ESSENCE.item(),
                Materials.ENDER_ESSENCE.item(), new ItemStack(Material.BOOK), Materials.ENDER_ESSENCE.item(),
                Materials.ENDER_ESSENCE.item(), Materials.ENDER_ESSENCE.item(), Materials.ENDER_ESSENCE.item()
        }).register(plugin);
        InfinityArmor.create(CROWN, armorEffects ? new PotionEffect[] {
                new PotionEffect(PotionEffectType.NIGHT_VISION, 600, 0, false, false, false),
                new PotionEffect(PotionEffectType.CONDUIT_POWER, 600, 0, false, false, false),
        } : NO_EFFECTS, new ItemStack[] {
                null, Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), null,
                Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(),
                Materials.INFINITE_INGOT.item(), Materials.VOID_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.VOID_INGOT.item(), Materials.INFINITE_INGOT.item(),
                null, Materials.INFINITE_INGOT.item(), null, null, Materials.INFINITE_INGOT.item(), null,
                null, null, null, null, null, null,
                null, null, null, null, null, null
        }, soulbound).register(plugin);
        InfinityArmor.create(CHESTPLATE, armorEffects ? new PotionEffect[] {
                new PotionEffect(PotionEffectType.RESISTANCE, 600, 0, false, false, false),
                new PotionEffect(PotionEffectType.STRENGTH, 600, 1, false, false, false),
                new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 600, 0, false, false, false)
        } : NO_EFFECTS, new ItemStack[] {
                null, Materials.INFINITE_INGOT.item(), null, null, Materials.INFINITE_INGOT.item(), null,
                Materials.INFINITE_INGOT.item(), Materials.VOID_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.VOID_INGOT.item(), Materials.INFINITE_INGOT.item(),
                Materials.VOID_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.VOID_INGOT.item(),
                Materials.VOID_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.VOID_INGOT.item(), Materials.VOID_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.VOID_INGOT.item(),
                null, Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), null,
                null, Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), null
        }, soulbound).register(plugin);
        InfinityArmor.create(LEGGINGS, armorEffects ? new PotionEffect[] {
                new PotionEffect(PotionEffectType.HASTE, 600, 2, false, false, false),
                new PotionEffect(PotionEffectType.REGENERATION, 600, 0, false, false, false),
                new PotionEffect(PotionEffectType.SATURATION, 600, 0, false, false, false),
        } : NO_EFFECTS, new ItemStack[] {
                null, Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), null,
                Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(),
                Materials.VOID_INGOT.item(), Materials.INFINITE_INGOT.item(), null, null, Materials.INFINITE_INGOT.item(), Materials.VOID_INGOT.item(),
                Materials.VOID_INGOT.item(), Materials.INFINITE_INGOT.item(), null, null, Materials.INFINITE_INGOT.item(), Materials.VOID_INGOT.item(),
                Materials.VOID_INGOT.item(), Materials.INFINITE_INGOT.item(), null, null, Materials.INFINITE_INGOT.item(), Materials.VOID_INGOT.item(),
                null, Materials.INFINITE_INGOT.item(), null, null, Materials.INFINITE_INGOT.item(), null
        }, soulbound).register(plugin);
        InfinityArmor.create(BOOTS, armorEffects ? new PotionEffect[] {
                new PotionEffect(PotionEffectType.SPEED, 600, 2, false, false, false),
                new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 600, 0, false, false, false),
        } : NO_EFFECTS, new ItemStack[] {
                null, null, null, null, null, null,
                Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), null, null, Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(),
                Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), null, null, Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(),
                Materials.VOID_INGOT.item(), Materials.VOID_INGOT.item(), null, null, Materials.VOID_INGOT.item(), Materials.VOID_INGOT.item(),
                Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), null, null, Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(),
                Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), null, null, Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item()
        }, soulbound).register(plugin);
        InfinityTool.create(SHIELD, new ItemStack[] {
                Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), null, null, Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(),
                Materials.INFINITE_INGOT.item(), Materials.VOID_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.VOID_INGOT.item(), Materials.INFINITE_INGOT.item(),
                Materials.INFINITE_INGOT.item(), Materials.VOID_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.VOID_INGOT.item(), Materials.INFINITE_INGOT.item(),
                Materials.INFINITE_INGOT.item(), Materials.VOID_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.VOID_INGOT.item(), Materials.INFINITE_INGOT.item(),
                null, Materials.INFINITE_INGOT.item(), Materials.VOID_INGOT.item(), Materials.VOID_INGOT.item(), Materials.INFINITE_INGOT.item(), null,
                null, Materials.INFINITE_INGOT.item(), Materials.VOID_INGOT.item(), Materials.VOID_INGOT.item(), Materials.INFINITE_INGOT.item(), null
        }, soulbound).register(plugin);
        InfinityBow.create(Groups.INFINITY_CHEAT, BOW, InfinityWorkbench.TYPE, new ItemStack[] {
                null, Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.VOID_INGOT.item(), null, null,
                Materials.INFINITE_INGOT.item(), null, Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.VOID_INGOT.item(), null,
                Materials.VOID_INGOT.item(), null, null, ENDER_FLAME.item(), Materials.INFINITE_INGOT.item(), Materials.VOID_INGOT.item(),
                null, Materials.VOID_INGOT.item(), null, null, Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(),
                null, null, Materials.VOID_INGOT.item(), null, null, Materials.INFINITE_INGOT.item(),
                null, null, null, Materials.VOID_INGOT.item(), Materials.INFINITE_INGOT.item(), null
        }, bowEffects, soulbound).register(plugin);
        InfinityTool.create(AXE, new ItemStack[] {
                null, Materials.VOID_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), null, null,
                Materials.VOID_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.VOID_INGOT.item(), null,
                null, Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.VOID_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(),
                null, null, Materials.VOID_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(),
                null, Materials.VOID_INGOT.item(), null, Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.VOID_INGOT.item(),
                Materials.VOID_INGOT.item(), null, null, null, Materials.VOID_INGOT.item(), null
        }, soulbound).register(plugin);
        InfinityTool.create(BLADE, new ItemStack[] {
                null, null, null, null, Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(),
                null, null, null, Materials.INFINITE_INGOT.item(), Materials.VOID_INGOT.item(), Materials.INFINITE_INGOT.item(),
                null, null, Materials.INFINITE_INGOT.item(), Materials.VOID_INGOT.item(), Materials.INFINITE_INGOT.item(), null,
                Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.VOID_INGOT.item(), Materials.INFINITE_INGOT.item(), null, null,
                null, Materials.VOID_INGOT.item(), Materials.INFINITE_INGOT.item(), null, null, null,
                Materials.VOID_INGOT.item(), null, Materials.INFINITE_INGOT.item(), null, null, null
        }, soulbound).register(plugin);
        InfinityTool.create(SHOVEL, new ItemStack[] {
                null, null, null, Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(),
                null, null, Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(),
                null, null, Materials.INFINITE_INGOT.item(), Materials.VOID_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(),
                null, null, Materials.VOID_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), null,
                null, Materials.VOID_INGOT.item(), null, null, null, null,
                Materials.VOID_INGOT.item(), null, null, null, null, null
        }, soulbound).register(plugin);
        InfinityTool.create(PICKAXE, new ItemStack[] {
                null, Materials.VOID_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), null,
                null, null, null, Materials.INFINITE_INGOT.item(), Materials.VOID_INGOT.item(), Materials.INFINITE_INGOT.item(),
                null, null, null, Materials.VOID_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(),
                null, null, Materials.VOID_INGOT.item(), null, null, Materials.INFINITE_INGOT.item(),
                null, Materials.VOID_INGOT.item(), null, null, null, Materials.VOID_INGOT.item(),
                Materials.VOID_INGOT.item(), null, null, null, null, null
        }, soulbound).register(plugin);
        new InfinityMatrix(Groups.INFINITY_CHEAT, INFINITY_MATRIX, InfinityWorkbench.TYPE, new ItemStack[] {
                Materials.INFINITE_INGOT.item(), null, Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), null, Materials.INFINITE_INGOT.item(),
                Materials.INFINITE_INGOT.item(), Materials.VOID_INGOT.item(), Materials.VOID_INGOT.item(), Materials.VOID_INGOT.item(), Materials.VOID_INGOT.item(), Materials.INFINITE_INGOT.item(),
                Materials.VOID_INGOT.item(), Materials.VOID_INGOT.item(), new ItemStack(Material.ELYTRA), new ItemStack(Material.ELYTRA), Materials.VOID_INGOT.item(), Materials.VOID_INGOT.item(),
                Materials.VOID_INGOT.item(), Materials.VOID_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), Materials.VOID_INGOT.item(), Materials.VOID_INGOT.item(),
                Materials.INFINITE_INGOT.item(), Materials.VOID_INGOT.item(), Materials.VOID_INGOT.item(), Materials.VOID_INGOT.item(), Materials.VOID_INGOT.item(), Materials.INFINITE_INGOT.item(),
                Materials.INFINITE_INGOT.item(), null, Materials.INFINITE_INGOT.item(), Materials.INFINITE_INGOT.item(), null, Materials.INFINITE_INGOT.item()
        }).register(plugin);
        new VeinMinerRune(Groups.MAIN_MATERIALS, VEIN_MINER_RUNE, RecipeType.MAGIC_WORKBENCH, new ItemStack[] {
                Materials.MAGSTEEL_PLATE.item(), SlimefunItems.PICKAXE_OF_VEIN_MINING.item(), Materials.MAGSTEEL_PLATE.item(),
                Materials.ENDER_ESSENCE.item(), SlimefunItems.BLANK_RUNE.item(), Materials.ENDER_ESSENCE.item(),
                Materials.MAGSTEEL_PLATE.item(), SlimefunItems.PICKAXE_OF_VEIN_MINING.item(), Materials.MAGSTEEL_PLATE.item(),
        }).register(plugin);
    }

    private static final PotionEffect[] NO_EFFECTS = new PotionEffect[0];

    /** The lore lines of the armor's potion effects, removed when gear-options.armor-potion-effects is off. */
    private static final Set<String> EFFECT_LORE = Set.of(
            "Night Vision I", "Conduit Power I",
            "Strength II", "Resistance I", "Fire Resistance I",
            "Haste III", "Regeneration I", "Saturation I",
            "Speed III", "Dolphins Grace I"
    );

    private static void removeEffectLore(SlimefunItemStack... items) {
        for (SlimefunItemStack item : items) {
            ItemMeta meta = item.getItemMeta();
            List<String> lore = meta.getLore();
            if (lore != null && lore.removeIf(line -> EFFECT_LORE.contains(ChatColor.stripColor(line)))) {
                meta.setLore(lore);
                item.setItemMeta(meta);
            }
        }
    }

    private static void addInfinityEnchants(InfinityExpansion plugin, boolean soulbound, SlimefunItemStack... items) {
        ConfigurationSection typeSection = plugin.getConfig().getConfigurationSection("infinity-enchant-levels");

        if (typeSection == null) {
            InfinityExpansion.log(Level.SEVERE, "Config section \"infinity-enchant-levels\" missing, Check your config and report this!");
            return;
        }

        for (SlimefunItemStack item : items) {
            ItemMeta meta = item.getItemMeta();

            // lore
            List<String> lore;
            if (meta.hasLore()) {
                lore = meta.getLore();
            }
            else {
                lore = new ArrayList<>();
            }
            if (soulbound) {
                lore.add(ChatColor.AQUA + "Soulbound");
            }
            meta.setLore(lore);

            // find path
            String itemPath = item.getItemId().replace("INFINITY_", "").toLowerCase();
            ConfigurationSection itemSection = typeSection.getConfigurationSection(itemPath);

            // unbreakable and enchants
            meta.setUnbreakable(Objects.requireNonNull(itemSection).getBoolean("unbreakable"));
            for (Map.Entry<Enchantment, Integer> entry : Util.getEnchants(itemSection).entrySet()) {
                meta.addEnchant(entry.getKey(), entry.getValue(), true);
            }

            item.setItemMeta(meta);
        }
    }

}
