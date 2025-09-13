package summonersTerminal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Champion {
    String championName;
    ChampionClass championClass;
    private Stats stats;
    private int level = 1;
    private int gold = 500;
    private boolean isDead = false;

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
        if (this.gold < item.cost()) {
            System.out.println("\nNot enough gold for that item!");
            return false;
        }
        this.gold -= item.cost();
        items.add(item);
        System.out.println("\nYou purchased " + item.toString() + "\n");
        recalcAllStats();
        return true;
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

    public boolean ability(Minion target, List<Minion> minionWave) {
        if (championName.equals("Katarina")) {
            int damage = (int) Math.round((this.stats.attackPower() * 0.4) + (this.stats.abilityPower()));
            int manaCost = 125;
            List<Minion> aoeTargets = new ArrayList<>();
            int targetIdx = minionWave.indexOf(target);

            if (this.stats.mana() >= manaCost) {
                System.out.println("\nKatarina used 'Bouncing Blade' for " + manaCost + " mana");
                aoeTargets.add(target);

                for (int i = 1; i <= 2; i++) {
                    if (targetIdx > (minionWave.size() - 1) | (targetIdx + i) > (minionWave.size() - 1)) {
                        continue;
                    }

                    if (minionWave.get(targetIdx + i) == null) {
                        continue;
                    }

                    aoeTargets.add(minionWave.get(targetIdx + i));

                }

                for (Minion minion : aoeTargets) {
                    minion.takeDamage(damage, minionWave, this);
                }

                this.stats = new Stats(
                        this.stats.health(),
                        this.stats.mana() - manaCost,
                        this.stats.armor(),
                        this.stats.resistance(),
                        this.stats.attackPower(),
                        this.stats.abilityPower());
            } else if (this.stats.mana() < manaCost) {
                System.out.println("\nNot enough mana to cast that spell!");
                return false;
            }
            return true;
        }
        return target.takeDamage(this.stats.abilityPower(), minionWave, this);
    }

    public boolean goToBase() {
        recalcAllStats();
        return true;
    }

    public boolean onDeath(int playerActionCount) {
        System.out.println("You have been slain! 😵");
        this.isDead = true;
        playerActionCount = 5;
        recalcAllStats();
        return true;
    }

    public boolean takeDamage(int damageAmount, int playerActionCount) {
        try {
            this.stats = new Stats(
                    stats.health() - damageAmount,
                    stats.mana(),
                    stats.armor(),
                    stats.resistance(),
                    stats.attackPower(),
                    stats.abilityPower());

            System.out.println("You have taken " + damageAmount + " damage!" + " | HP: " + this.stats.health());

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

    public List<Item> getItems() {
        return items;
    }

    // ----- Setters

    public void addGold(int amount) {
        this.gold += amount;
    }

    @Override
    public String toString() {
        String itemsString = items.isEmpty()
                ? "(none)"
                : items.stream()
                        .map(Item::toString)
                        .collect(Collectors.joining(", "));

        return "[%s] - (Lv.%d %s)\n%s\nGold: %d\nItems: %s ".formatted(championName, level, championClass, stats, gold,
                itemsString);
    }

}
