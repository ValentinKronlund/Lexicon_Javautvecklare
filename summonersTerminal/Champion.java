package summonersTerminal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Champion {
    String championName;
    ChampionClass championClass;
    private Stats stats;
    private int level = 1;
    private int gold = 3000;

    private final List<Item> items = new ArrayList<>();

    public Champion(
            String championName,
            ChampionClass championClass) {
        this.championName = championName;
        this.championClass = championClass;

        Stats d = championClass.base();
        this.stats = new Stats(
                d.health(), d.mana(), d.armor(), d.resistance(),
                d.attackPower(), d.abilityPower());
    }

    public void levelUp() {
        this.level++;
        recalcAllStats();
    }

    private Stats baseAtCurrentLevel() {
        Stats st = championClass.base();
        for (int i = 1; i < level; i++) {
            st = st.plus(championClass.growthPerLevel());
        }
        return st;
    }

    private void recalcAllStats() {
        Stats itemBonus = Stats.ZERO;
        for (Item item : items) {
            itemBonus = itemBonus.plus(item.stats());
        }
        this.stats = baseAtCurrentLevel().plus(itemBonus);
    }

    public boolean equip(Item item) {
        if (this.gold < item.cost())
            return false;
        this.gold -= item.cost();
        items.add(item);
        recalcAllStats();
        return true;
    }

    public boolean unequip(Item item) {
        if (!items.remove(item))
            return false;
        gold += (int) Math.round(item.cost() * 0.6);
        recalcAllStats();
        return true;
    }

    public int attack() {
        return this.stats.attackPower();
    }

    public int ability() {
        return this.stats.abilityPower();
    }

    // ----- GETTERS
    public Stats getStats() {
        return stats;
    }

    public int getLevel() {
        return level;
    }

    public int getGold() {
        return gold;
    }

    public List<Item> getItems() {
        return items;
    }

    // ----- Setters

    public void addGold(int amount) {
        this.gold += amount;
    }

    public void addItems(Item item) {
        items.add(item);
    }

    @Override
    public String toString() {
        String itemsString = items.isEmpty()
                ? "(none)"
                : items.stream()
                        .map(Item::toString)
                        .collect(Collectors.joining(", "));

        return "%s (Lv.%d %s) %s | Gold: %d | Items: %s ".formatted(championName, level, championClass, stats, gold,
                itemsString);
    }

}
