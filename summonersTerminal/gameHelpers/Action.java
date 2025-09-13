package summonersTerminal.gameHelpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import helpers.Helpers;
import summonersTerminal.Champion;
import summonersTerminal.Item;
import summonersTerminal.Minion;
import summonersTerminal.MinionType;
import summonersTerminal.Nexus;

public class Action {
    static Helpers helper = new Helpers();
    static Scanner scanner = new Scanner(System.in);

    public static void generateMinionWave(List<Minion> minionWave, int waveNumber) {
        List<Minion> wave = new ArrayList<>();

        for (int i = 0; i < 2; i++) { // Add Melee minions
            Minion newMinion = new Minion(MinionType.MELEE);
            wave.add(newMinion);
        }
        for (int i = 0; i < 3; i++) { // Add Caster minions
            Minion newMinion = new Minion(MinionType.CASTER);
            wave.add(newMinion);
        }

        if (waveNumber % 3 == 0) {
            Minion newMinion = new Minion(MinionType.CANON);
            wave.add(newMinion);
        }

        for (Minion minion : wave) {
            minionWave.add(minion);
        }
    }

    public static boolean attackAction(Champion playerChampion, List<Minion> minionWave, Nexus nexus) {
        while (true) {
            if (minionWave.size() > 0) {
                Copy.attackActionChoiceCopy(minionWave);
                int targetIdx = helper.askInt(scanner, "");
                try {
                    if (minionWave.get(targetIdx - 1) == null) {
                        System.out.println("No minion at given index: " + targetIdx);
                        continue;
                    }

                    Minion targetMinion = minionWave.get(targetIdx - 1);
                    boolean successfulAttack = playerChampion.attack(targetMinion, minionWave);
                    return successfulAttack;
                } catch (IllegalArgumentException e) {
                    System.out.println("No minion at given index: " + targetIdx);
                    continue;
                }
            } else {
                Copy.attackNexusCopy(nexus);
                playerChampion.attackNexus(nexus);
                return true;
            }
        }
    }

    public static boolean abilityAction(Champion playerChampion, List<Minion> minionWave) {
        while (true) {
            Copy.attackActionChoiceCopy(minionWave);
            int targetIdx = helper.askInt(scanner, "");
            try {
                if (targetIdx > minionWave.size() | targetIdx < 1) {
                    System.out.println("There is no minion at that location -- Try again");
                    continue;
                }
                if (minionWave.get(targetIdx - 1) == null) {
                    System.out.println("No minion at given index: " + targetIdx);
                    continue;
                }

                Minion targetMinion = minionWave.get(targetIdx - 1);
                boolean successfulAttack = playerChampion.ability(targetMinion, minionWave);
                return successfulAttack;
            } catch (IllegalArgumentException e) {
                System.out.println("No minion at given index: " + targetIdx);
                continue;
            }
        }
    }

    public static void purchaseOptions(Champion champion) {
        while (true) {
            int option = helper.askInt(scanner,
                    "\nWhat item would you like to purchase?"
                            + "\n[1] " + Item.THORN_MAIL.toString() +
                            "\n[2] " + Item.ROD_OF_AGES.toString() +
                            "\n[3] " + Item.INFINITY_EDGE.toString() +
                            "\n[4] " + Item.RABADONS_DEATHCAP.toString());

            switch (option) {
                case 1: {
                    champion.equip(Item.THORN_MAIL);
                    return;
                }
                case 2: {

                    champion.equip(Item.ROD_OF_AGES);
                    return;
                }
                case 3: {

                    champion.equip(Item.INFINITY_EDGE);
                    return;
                }
                case 4: {

                    champion.equip(Item.RABADONS_DEATHCAP);
                    return;
                }
                default: {
                    System.out.println("There is no item at the given index of: " + option);
                    continue;
                }
            }

        }

    }
}
