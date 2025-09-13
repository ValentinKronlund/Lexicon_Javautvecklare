package summonersTerminal;

public class Nexus {
    String name;
    public boolean isDestroyed = false;
    private Stats stats;

    public Nexus(String name) {
        this.name = name;
        this.stats = new Stats(
                50,
                0,
                0,
                0,
                0,
                0);
    }

    public void onDeath() {
        this.isDestroyed = true;
    }

    public boolean takeDamage(int damageAmount) {
        try {
            this.stats = new Stats(
                    stats.health() - damageAmount,
                    stats.mana(),
                    stats.armor(),
                    stats.resistance(),
                    stats.attackPower(),
                    stats.abilityPower());

            System.out.println(this.name + " has taken " + damageAmount + " damage!" + " | HP: "
                    + this.stats.health());

            if (this.stats.health() <= 0) {
                onDeath();
                return true;
            }

            return true;
        } catch (Exception e) {
            System.out.println("Some spooky shit happened when minion tried to take damage 👻");
            return false;
        }
    }

    @Override
    public String toString() {
        return "[%s] - HP:%d".formatted(name, stats.health());
    }

}
