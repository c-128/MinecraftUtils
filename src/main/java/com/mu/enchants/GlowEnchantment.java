package com.mu.enchants;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;

public class GlowEnchantment extends Enchantment {

    private static GlowEnchantment enc;

    public GlowEnchantment(NamespacedKey key) {
        super(key);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getMaxLevel() {
        return 0;
    }

    @Override
    public int getStartLevel() {
        return 0;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return null;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        return false;
    }

    public static void registerGlow() {
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);

            GlowEnchantment glow = new GlowEnchantment(new NamespacedKey("glow_enchantment", "glow_enchantment"));
            enc = glow;
            Enchantment.registerEnchantment(glow);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static GlowEnchantment getGlow() {
        return enc;
    }
}
