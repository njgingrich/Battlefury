package com.nathan.battlefury.model;

/**
 * Represent a player within a match.
 * <p/>
 * Created by nathan on 3/28/15.
 */
public class Player {
    private long account_id;
    private int player_slot;
    private int hero_id;
    private int[] items;

    private int kills;
    private int deaths;
    private int assists;

    private int gold;
    private int last_hits;
    private int denies;

    private int gpm;
    private int xpm;
    private int gold_spent;

    private int hero_damage;
    private int tower_damage;
    private int hero_healing;
    private int level;
    private AbilityUpgrade[] upgrades;

    public Player() {
        account_id = -1;
        items = new int[6];
    }

    public long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

    public int getPlayer_slot() {
        return player_slot;
    }

    public void setPlayer_slot(int player_slot) {
        this.player_slot = player_slot;
    }

    public int getHero_id() {
        return hero_id;
    }

    public void setHero_id(int hero_id) {
        this.hero_id = hero_id;
    }

    public int[] getItems() {
        return items;
    }

    public String getItemsCSV() {
        StringBuffer buffer = new StringBuffer();
        for (int item : items) {
            buffer.append(item);
            buffer.append(",");
        }
        buffer.setLength(buffer.length() - 1);
        return buffer.toString();
    }

    public void setItems(String items) {
        String[] itemArray = items.split(",");
        for (int i = 0; i < itemArray.length; i++) {
            this.items[i] = Integer.parseInt(itemArray[i]);
        }
    }

    public void setItems(int[] items) {
        this.items = items;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getLast_hits() {
        return last_hits;
    }

    public void setLast_hits(int last_hits) {
        this.last_hits = last_hits;
    }

    public int getDenies() {
        return denies;
    }

    public void setDenies(int denies) {
        this.denies = denies;
    }

    public int getGpm() {
        return gpm;
    }

    public void setGpm(int gpm) {
        this.gpm = gpm;
    }

    public int getXpm() {
        return xpm;
    }

    public void setXpm(int xpm) {
        this.xpm = xpm;
    }

    public int getGold_spent() {
        return gold_spent;
    }

    public void setGold_spent(int gold_spent) {
        this.gold_spent = gold_spent;
    }

    public int getHero_damage() {
        return hero_damage;
    }

    public void setHero_damage(int hero_damage) {
        this.hero_damage = hero_damage;
    }

    public int getTower_damage() {
        return tower_damage;
    }

    public void setTower_damage(int tower_damage) {
        this.tower_damage = tower_damage;
    }

    public int getHero_healing() {
        return hero_healing;
    }

    public void setHero_healing(int hero_healing) {
        this.hero_healing = hero_healing;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public AbilityUpgrade[] getUpgrades() {
        return upgrades;
    }

    public String getUpgradesCSV() {
        StringBuffer buffer = new StringBuffer();
        for (AbilityUpgrade upgrade : upgrades) {
            buffer.append(upgrade.toString());
        }
        return buffer.toString();
    }

    public void setUpgrades(AbilityUpgrade[] upgrades) {
        this.upgrades = upgrades;
    }

    public String toString() {
        return "" + account_id;
    }
}
