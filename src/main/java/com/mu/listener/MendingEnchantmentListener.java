package com.mu.listener;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Random;

public class MendingEnchantmentListener implements Listener {

    @EventHandler
    public void onEnchantingEvent(EnchantItemEvent e) {
        if (new Random().nextInt(10) == 0) {
            ItemStack i = e.getItem();
            ItemMeta m = i.getItemMeta();
            m.addEnchant(Enchantment.MENDING, 1, true);
            i.setItemMeta(m);
        }
    }
}
