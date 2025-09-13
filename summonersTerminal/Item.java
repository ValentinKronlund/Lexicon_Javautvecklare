package summonersTerminal;

public enum Item {
        THORN_MAIL(
                        new Stats(
                                        150,
                                        0,
                                        75,
                                        0,
                                        0,
                                        0),
                        800),
        ROD_OF_AGES(
                        new Stats(
                                        75,
                                        150,
                                        0,
                                        0,
                                        0,
                                        20),
                        1200),
        INFINITY_EDGE(
                        new Stats(
                                        0,
                                        0,
                                        0,
                                        0,
                                        50,
                                        0),
                        1200),
        RABADONS_DEATHCAP(
                        new Stats(
                                        0,
                                        0,
                                        0,
                                        0,
                                        0,
                                        50),
                        1200),
                        ;

        private final Stats stats;
        private final int cost;

        Item(Stats itemStats, int cost) {
                this.stats = itemStats;
                this.cost = cost;
        }

        public Stats stats() {
                return stats;
        }

        public int cost() {
                return cost;
        }

        @Override
        public String toString() {
                return name().replace('_', ' ') + " [HP+" + stats.health()
                                + " MP+" + stats.mana()
                                + " ARM+" + stats.armor()
                                + " RES+" + stats.resistance()
                                + " ATK+" + stats.attackPower()
                                + " ABI+" + stats.abilityPower()
                                + " | COST " + cost + "]";
        }

        public static Item parse(String text) {
                String key = text.trim().toUpperCase().replace(' ', '_');
                return Item.valueOf(key);
        }
}
