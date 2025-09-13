package summonersTerminal;

public enum ChampionClass {
        BRAWLER(
                        new Stats(
                                        700,
                                        200,
                                        25,
                                        30,
                                        45,
                                        0),
                        new Stats(
                                        70,
                                        20,
                                        4,
                                        3,
                                        5,
                                        0)),

        MAGE(
                        new Stats(
                                        450,
                                        500,
                                        10,
                                        30,
                                        20,
                                        50),
                        new Stats(
                                        45,
                                        40,
                                        2,
                                        3,
                                        3,
                                        7)),
        ASSASSIN(
                        new Stats(
                                        500,
                                        300,
                                        10,
                                        20,
                                        60,
                                        10),
                        new Stats(
                                        50,
                                        30,
                                        3,
                                        2,
                                        6,
                                        2));

        private final Stats base, growthPerLevel;

        ChampionClass(Stats base, Stats growthPerLevel) {
                this.base = base;
                this.growthPerLevel = growthPerLevel;
        }

        public Stats base() {
                return base;
        }

        public Stats growthPerLevel() {
                return growthPerLevel;
        }

}