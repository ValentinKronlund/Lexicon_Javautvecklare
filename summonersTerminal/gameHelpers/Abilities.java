package summonersTerminal.gameHelpers;

import java.util.ArrayList;
import java.util.List;

import summonersTerminal.Champion;
import summonersTerminal.Minion;
import summonersTerminal.Stats;

public class Abilities {

    public static boolean ability(Champion champion, String championName, Minion target, List<Minion> minionWave) {
        Stats championStats = champion.getStats();

        switch (championName) {
            case "Garen": {
                int manaCost = 150;
                int damage = (int) Math.round((championStats.attackPower() * 0.3));
                int targetIdx = minionWave.indexOf(target);

                if (championStats.mana() < manaCost) {
                    System.out.println("\nNot enough mana to cast that spell!");
                    return false;
                }

                System.out.println("\nGaren used 'Judgment' for " + manaCost + " mana");
                champion.useMana(manaCost);
                int aoeStart = Math.max(0, targetIdx - 2);
                int aoeEnd = Math.min(minionWave.size() - 1, targetIdx + 2);
                List<Minion> aoeTargets = new ArrayList<>(minionWave.subList(aoeStart, aoeEnd + 1));

                int hits = 0;
                for (Minion minion : aoeTargets) {
                    if (minion == null) {
                        continue;
                    }
                    minion.takeDamage(damage, minionWave, champion);
                    hits++;
                }
                return hits > 0;
            }

            case "Katarina": {
                int damage = (int) Math.round((championStats.attackPower() * 0.4) + (championStats.abilityPower()));
                int manaCost = 125;
                List<Minion> aoeTargets = new ArrayList<>();
                int targetIdx = minionWave.indexOf(target);

                if (championStats.mana() < manaCost) {
                    System.out.println("\nNot enough mana to cast that spell!");
                    return false;
                }

                System.out.println("\nKatarina used 'Bouncing Blade' for " + manaCost + " mana");
                champion.useMana(manaCost);
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
                    minion.takeDamage(damage, minionWave, champion);
                }

                return true;
            }

            case "Veigar": {
                return true;
            }

            default: {
                target.takeDamage(championStats.abilityPower(), minionWave, champion);
                return false;
            }
        }
    }
}
