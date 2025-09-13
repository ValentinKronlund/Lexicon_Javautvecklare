package summonersTerminal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import helpers.Helpers;
import summonersTerminal.gameHelpers.Copy;

public class SummonersTerminalGame {
    Scanner scanner = new Scanner(System.in);
    Helpers helper = new Helpers();

    public boolean gameInProgress;
    private Champion playerChampion;
    private Champion enemyChampion;
    private List<Minion> minionWave = new ArrayList<>();
    private int waveNumber = 1;

    public boolean PlayGame() {
        this.gameInProgress = InitiateGame();
        if (gameInProgress) {
            GameLoop();
        }

        return true;
    }

    private boolean InitiateGame() {
        Copy.initialCopy();

        while (playerChampion == null) {

            try {
                String championRequest = helper.askLine(scanner, "");

                switch (championRequest) {
                    case "Garen", "garen", "G", "g": {
                        this.playerChampion = new Champion("Garen", ChampionClass.BRAWLER);
                        break;
                    }
                    case "Katarina", "katarina", "K", "k": {
                        this.playerChampion = new Champion("Katarina", ChampionClass.ASSASSIN);
                        break;
                    }
                    case "Veigar", "veigar", "V", "v": {
                        this.playerChampion = new Champion("Veigar", ChampionClass.MAGE);
                        break;
                    }
                    default: {
                        System.out.println("\n There is no champion named: " + championRequest);
                        throw new IllegalArgumentException();
                    }
                }
            } catch (IllegalArgumentException err) {
                System.out.println("Try again! ");
                continue;
            }
        }

        this.enemyChampion = new Champion("Lux", ChampionClass.MAGE);

        Copy.championsSelectedCopy(playerChampion, enemyChampion);
        return true;
    }

    private boolean GameLoop() {
        while (true) {
            if (playerChampion.getIsDead() == true) {
                playerChampion.respawn();
            }

            Copy.newWaveCopy(waveNumber);
            int playerActionCount = 0;

            generateMinionWave();
            Copy.waveCopy(minionWave);
            playerChampion.walkFromBase();

            while (playerActionCount < 5 & playerChampion.getIsDead() == false) {
                Copy.baseActionChoiceCopy(playerActionCount);
                try {
                    char playerChoice = helper.askChar(scanner, "");
                    switch (playerChoice) {
                        case 'a': {
                            boolean successfulAttack = abilityAction();
                            if (successfulAttack)
                                playerActionCount++;
                            break;
                        }

                        case 'm': {
                            attackAction();
                            playerActionCount++;
                            break;
                        }

                        case 'b': {
                            playerChampion.goToBase();
                            playerActionCount += 2;
                            break;
                        }

                        case 'i': {
                            System.out.println("\n[1] " + Item.THORN_MAIL.toString() +
                                    "\n[2] " + Item.ROD_OF_AGES.toString() +
                                    "\n[3] " + Item.INFINITY_EDGE.toString() +
                                    "\n[4] " + Item.RABADONS_DEATHCAP.toString());
                            continue;
                        }
                        case 'p': {
                            purchaseOptions(playerChampion);
                            playerActionCount += 2;
                            break;
                        }

                        case 's': {
                            System.out.println("\n" + playerChampion.toString());
                            continue;
                        }

                        case 'e': {
                            System.out.println("\n" + enemyChampion.toString());
                            continue;
                        }

                        case 'w': {
                            Copy.waveCopy(minionWave);
                            continue;
                        }

                        case 'q': {
                            System.out.println(
                                    "\nYou are about to quit the game 😵"
                                            + "\nAre you sure you want to leave and lose 25 LP?"
                                            + "\nYou'll be stuck in elo hell!"
                                            + "\n\nType 'q' if you want to quit");
                            char quitConfirmation = helper.askChar(scanner, "");
                            switch (quitConfirmation) {
                                case 'q':
                                    return false;
                                default:
                                    continue;
                            }
                        }

                        default:
                            System.out.println("There is currently no command for: " + playerChoice);
                            continue;
                    }
                } catch (AssertionError err) {
                    System.out.println(err);
                    continue;
                }

                if (playerChampion.getInBase() == false) {
                    for (Minion minion : minionWave) {
                        playerChampion.takeDamage(minion.attack(), playerActionCount);
                    }
                }

            }

            waveNumber++;

        }
    }

    // Action helpers below 👇🏽 --------------------
    private boolean attackAction() {
        while (true) {
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
        }
    }

    private boolean abilityAction() {
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

    private void generateMinionWave() {
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
            this.minionWave.add(minion);
        }
    }

    private void purchaseOptions(Champion champion) {
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
