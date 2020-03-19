package com.github.rimuruchan.rush.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtil {

    public static final ItemStack KNOCKBACK_STICK = getStick();

    private static ItemStack getStick() {
        ItemStack is = new ItemStack(Material.STICK);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.COLOR_CHAR + "6击退棍");
        im.addEnchant(Enchantment.KNOCKBACK, 1, false);
        is.setItemMeta(im);
        return is;
    }
}