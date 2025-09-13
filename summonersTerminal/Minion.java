package summonersTerminal;

import java.util.List;

public class Minion {
    String minionName;
    MinionType minionType;
    private Stats stats;
    private int goldValue;

    public Minion(
            MinionType minionType) {
        this.minionName = minionType.nameType();
        this.minionType = minionType;
        this.goldValue = minionType.goldValue();

        Stats base = minionType.base();
        this.stats = new Stats(
                base.health(),
                base.mana(),
                base.armor(),
                base.resistance(),
                base.attackPower(),
                base.abilityPower());
    }

    public void onDeath(
            List<Minion> wave,
            Champion champion) {
        System.out.println(
                "Minion has died!\n"
                        + champion.championName + " has been awarded with 🥇"
                        + goldValue + " gold!");
        wave.remove(this);
        champion.addGold(goldValue);
    }

    public int attack() {
        return this.stats.attackPower();
    }

    public boolean takeDamage(int damageAmount, List<Minion> wave, Champion champion) {
        try {
            this.stats = new Stats(
                    stats.health() - damageAmount,
                    stats.mana(),
                    stats.armor(),
                    stats.resistance(),
                    stats.attackPower(),
                    stats.abilityPower());

            System.out.println(this.minionName + " Minion has taken " + damageAmount + " damage!" + " | HP: "
                    + this.stats.health());

            if (this.stats.health() <= 0) {
                onDeath(wave, champion);
            }

            return true;
        } catch (Exception e) {
            System.out.println("Some spooky shit happened when minion tried to take damage 👻");
            return false;
        }
    }

    @Override
    public String toString() {
        return "%s HP:%d | Gold Value: %d".formatted(minionName, stats.health(), goldValue);
    }
}
