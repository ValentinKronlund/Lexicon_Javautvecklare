package summonersTerminal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import summonersTerminal.gameHelpers.Abilities;
import summonersTerminal.gameHelpers.Damage;

public class Champion {
    String championName;
    ChampionClass championClass;
    private Stats stats;
    private int level = 1;
    private int gold = 50000;
    private boolean isDead = false;
    private boolean inBase = false;

    private final List<Item> items = new ArrayList<>();

    public Champion(
            String championName,
            ChampionClass championClass) {
        this.championName = championName;
        this.championClass = championClass;

        Stats base = championClass.base();
        this.stats = new Stats(
                base.health(), base.mana(), base.armor(), base.resistance(),
                base.attackPower(), base.abilityPower());
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

        Stats newStats = baseAtCurrentLevel().plus(itemBonus);

        this.stats = new Stats(
                newStats.health(),
                newStats.mana(),
                newStats.armor(),
                newStats.resistance(),
                newStats.attackPower(),
                newStats.abilityPower());
    }

    // Actions below 👇🏽 ----------
    public boolean equip(Item item) {
        if (items.size() >= 6) {
            System.out.println("\nYou don't have any more available item slots!");
            return false;
        }
        if (this.gold < item.cost()) {
            System.out.println("\nNot enough gold for that item!");
            return false;
        }
        this.gold -= item.cost();
        for (int i = 0; i <= 3; i++) {
            items.add(item);
        }
        System.out.println("\nYou purchased " + item.toString() + "\n");
        return goToBase();
    }

    public boolean unequip(Item item) {
        if (!items.remove(item))
            return false;

        int sellAmount = (int) Math.round(item.cost() * 0.6);
        System.out.println("\nSold item for: " + sellAmount);
        gold += sellAmount;
        recalcAllStats();
        return true;
    }

    public boolean attack(Minion target, List<Minion> minionWave) {
        return target.takeDamage(this.stats.attackPower(), minionWave, this);
    }

    public boolean attackNexus(Nexus nexus) {
        return nexus.takeDamage(this.stats.attackPower());
    }

    public boolean ability(Minion target, List<Minion> minionWave) {
        return Abilities.ability(this, championName, target, minionWave);
    }

    public boolean goToBase() {
        this.inBase = true;
        System.out.println("\nYou have gone to the base -- HP and Mana reset!✨\n");
        recalcAllStats();
        this.inBase = false;
        return true;
    }

    public boolean onDeath(int playerActionCount) {
        System.out.println("You have been slain! 😵");
        this.isDead = true;
        this.inBase = true;
        playerActionCount = 5;
        recalcAllStats();
        respawn();
        return true;
    }

    public boolean respawn() {
        this.isDead = false;
        this.inBase = false;
        System.out.println("\nYou have respawned! 🩵\n");
        return isDead;
    }

    public boolean takeDamage(int damageAmount, int playerActionCount) {
        int damageTaken = (int) Math.round(Damage.damageAfterArmor(damageAmount, stats.armor()));
        try {
            this.stats = new Stats(
                    stats.health() - damageTaken,
                    stats.mana(),
                    stats.armor(),
                    stats.resistance(),
                    stats.attackPower(),
                    stats.abilityPower());

            System.out.println("You have taken " + damageTaken + " damage!" + " | HP: " + this.stats.health());

            if (this.stats.health() <= 0) {
                onDeath(playerActionCount);
            }

            return true;
        } catch (Exception e) {
            System.out.println("Some spooky shit happened when champion tried to take damage 👻");
            return false;
        }
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

    public boolean getIsDead() {
        return isDead;
    }

    public boolean getInBase() {
        return inBase;
    }

    public List<Item> getItems() {
        return items;
    }

    // ----- Setters
    public void walkFromBase() {
        this.inBase = false;
    }

    public void useMana(int manaCost) {
        this.stats = new Stats(
                stats.health(),
                stats.mana() - manaCost,
                stats.armor(),
                stats.resistance(),
                stats.attackPower(),
                stats.abilityPower());
    }

    public void addGold(int amount) {
        this.gold += amount;
    }

    @Override
    public String toString() {
        String itemsString = items.isEmpty()
                ? "(none)"
                : items.stream()
                        .map(Item::toString)
                        .collect(Collectors.joining("\n"));

        return "[%s] - (Lv.%d %s)\n%s\nGold: %d\nItems: %s ".formatted(championName, level, championClass, stats, gold,
                itemsString);
    }

}
