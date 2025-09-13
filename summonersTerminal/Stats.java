package summonersTerminal;

public record Stats(
                int health,
                int mana,

                int armor,
                int resistance,

                int attackPower,
                int abilityPower) {

        public static final Stats ZERO = new Stats(0, 0, 0, 0, 0, 0);

        public Stats plus(Stats statsModifier) {
                return new Stats(this.health + statsModifier.health,
                                this.mana + statsModifier.mana,
                                this.armor + statsModifier.armor,
                                this.resistance + statsModifier.resistance,
                                this.attackPower + statsModifier.attackPower,
                                this.abilityPower + statsModifier.abilityPower);
        }

        public Stats minus(Stats statsModifier) {
                return new Stats(this.health - statsModifier.health,
                                this.mana - statsModifier.mana,
                                this.armor - statsModifier.armor,
                                this.resistance - statsModifier.resistance,
                                this.attackPower - statsModifier.attackPower,
                                this.abilityPower - statsModifier.abilityPower);
        }

        @Override
        public String toString() {
                return "HP:%d | MANA:%d | ARM:%d | RES:%d | ATK:%d | ABP:%d".formatted(
                                health,
                                mana,
                                armor,
                                resistance,
                                attackPower,
                                abilityPower);
        }
}