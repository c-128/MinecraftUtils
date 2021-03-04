package com.mu.listener;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentOffer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;

public class MaxEnchantingListener implements Listener {

    @EventHandler
    public void onPrepareItemEnchantEvent(PrepareItemEnchantEvent e) {
        for (EnchantmentOffer offer : e.getOffers()) {
            if (offer != null) {
                offer.setEnchantmentLevel(offer.getEnchantment().getMaxLevel());
            }
        }
    }

    @EventHandler
    public void onItemEnchantEvent(EnchantItemEvent e) {
        for (Enchantment enchantment : e.getEnchantsToAdd().keySet()) {
            e.getEnchantsToAdd().put(enchantment, enchantment.getMaxLevel());
        }
    }
}
