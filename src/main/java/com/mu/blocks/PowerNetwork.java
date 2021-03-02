package com.mu.blocks;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.UUID;

public class PowerNetwork {

    private ArrayList<PowerNode> nodes = new ArrayList<>();
    private long powerlevel = 0;
    private long powergeneratoin = 0;
    private long powerconsumtion = 0;
    private String uuid;

    public PowerNetwork() {
        uuid = UUID.randomUUID().toString();
    }

    public void setUUID(String uuid) {
        this.uuid = uuid;
    }

    public String getUUID() {
        return this.uuid;
    }

    public void addNode(PowerNode node) {
        this.nodes.add(node);
    }

    public void removeNode(PowerNode node) {
        this.nodes.remove(node);
    }

    public ArrayList<PowerNode> getNodes() {
        return this.nodes;
    }

    public void setPowerLevel(long level) {
        this.powerlevel = level;
    }

    public long getPowerLevel() {
        return this.powerlevel;
    }

    public void removePowerLevel(long level) {
        this.powerlevel = this.powerlevel - level;
    }

    public void addPowerLevel(long level) {
        this.powerlevel = this.powerlevel + level;
    }

    public boolean hasPower(long am) {
        if (this.powerlevel > am) {
            return true;
        } else {
            return false;
        }
    }

    public PowerNetwork merchNetworks(PowerNetwork... net) {
        PowerNetwork done = new PowerNetwork();
        for (PowerNetwork n : net) {
            done.addGenerator(n.getPowerGeneration());
            for (PowerNode node : n.getNodes()) {
                node.setNetwork(done);
                done.addNode(node);
            }
        }

        return done;
    }

    public long getPowerGeneration() {
        return this.powergeneratoin;
    }

    public void addGenerator(long amount) {
        this.powergeneratoin = this.powergeneratoin + amount;
    }

    public void removeGenerator(long amount) {
        this.powergeneratoin = this.powergeneratoin - amount;
    }

    public void setPowerGeneratoin(long powergeneratoin) {
        this.powergeneratoin = powergeneratoin;
    }

    public void addConsumtion(long amount) {
        this.powerconsumtion = this.powerconsumtion - amount;
    }

    public void setPowerConsumtion(long powerconsumtion) {
        this.powerconsumtion = powerconsumtion;
    }

    public void caculatePower() {
        this.powerlevel = this.powergeneratoin - this.powerconsumtion;
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();

        JsonArray nodes = new JsonArray();
        for (PowerNode node : this.nodes) {
            nodes.add(node.toJson());
        }
        json.add("nodes", nodes);
        json.addProperty("powerlevel", this.powerlevel);
        json.addProperty("uuid", this.uuid);
        json.addProperty("gen", this.powergeneratoin);
        json.addProperty("cons", this.powerconsumtion);

        return json;
    }
}
