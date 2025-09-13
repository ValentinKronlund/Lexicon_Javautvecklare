package summonersTerminal;

public enum Minion {
        MELEE(
                        new Stats(
                                        90,
                                        0,
                                        10,
                                        0,
                                        17,
                                        0),
                        new Stats(
                                        0,
                                        0,
                                        0,
                                        0,
                                        0,
                                        0)),
        CASTER(
                        new Stats(
                                        70,
                                        0,
                                        0,
                                        0,
                                        13,
                                        0),
                        new Stats(
                                        0,
                                        0,
                                        0,
                                        0,
                                        0,
                                        0)),
        CANON(
                        new Stats(
                                        220,
                                        0,
                                        25,
                                        10,
                                        25,
                                        0),
                        new Stats(
                                        0,
                                        0,
                                        0,
                                        0,
                                        0,
                                        0));

        private final Stats base, growthPerCycle;

        Minion(Stats base, Stats growthPerCycle) {
                this.base = base;
                this.growthPerCycle = growthPerCycle;
        }

        public Stats base() {
                return base;
        }

        public Stats growthPerCycle() {
                return growthPerCycle;
        }

}
