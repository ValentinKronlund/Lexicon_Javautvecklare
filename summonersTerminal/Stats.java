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
                return new Stats(health + statsModifier.health,
                                mana + statsModifier.mana,
                                armor + statsModifier.armor,
                                resistance + statsModifier.resistance,
                                attackPower + statsModifier.attackPower,
                                abilityPower + statsModifier.abilityPower);
        }

        public Stats minus(Stats statsModifier) {
                return new Stats(health - statsModifier.health,
                                mana - statsModifier.mana,
                                armor - statsModifier.armor,
                                resistance - statsModifier.resistance,
                                attackPower - statsModifier.attackPower,
                                abilityPower - statsModifier.abilityPower);
        }
}