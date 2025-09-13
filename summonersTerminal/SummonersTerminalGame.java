package summonersTerminal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import helpers.Helpers;

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
        System.out.println("\n🔮 Welcome, to Summoner's Terminal! 🔮");
        System.out.println(
                "\n[ Rules ]: \n"
                        + "   Your aim is to destroy the enemy nexus 🔻, while protecting your own 💎\n"
                        + "   To attack a nexus, a champion must first break through the enemies minions\n\n"
                        + "   Minions spawn in waves at the start of each combat sequence\n"
                        + "   A minion wave consists of 2 melee minions with 90hp, and 3 caster minions with 70hp\n"
                        + "   Every three waves, a canon minion with 220hp will be added to the wave\n"
                        + "   Minions award gold when killed, which can be used to purchase items between combat sequences\n");
        System.out.println("\n🔮 Minions spawning soon! 🔮\n");

        while (playerChampion == null) {

            try {
                System.out.println(
                        "\n👤 Choose your champion: 👤\n\n"
                                + "(G)aren\n"
                                + "(K)atarina\n"
                                + "(V)eigar\n");

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

        System.out.println(
                "\nChampions selected!\n\n"
                        + "Player Champion 😎" + "\n" + this.playerChampion.toString() + "\n\n"
                        + "Enemy Champion 😈" + "\n" + this.enemyChampion.toString() + "\n");

        System.out.println("\n\n🔮 Minions have spawned! 🔮");
        return true;
    }

    private boolean GameLoop() {
        while (true) {
            if (playerChampion.getIsDead()) {
                System.out.println("\nYou have respawned! 🩵\n");
            }

            System.out.println("\nNew wave incoming!");
            System.out.println("Wave number: " + waveNumber + "\n");
            int playerActionCount = 0;
            boolean inBase = false;

            generateMinionWave();
            printWave();

            while (playerActionCount < 5) {
                inBase = false;
                printBaseActionChoice(playerActionCount);
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
                            inBase = true;
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
                            inBase = true;
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
                            printWave();
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

                if (inBase == false) {
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
            printAttackActionChoice();
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
            printAttackActionChoice();
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

    // Print helpers below 👇🏽 ---------------------
    private void printWave() {
        System.out.println("\n♦️♦️♦️♦️♦️");
        for (Minion minion : minionWave) {
            System.out.println(minion.toString());
        }
        System.out.println("\n♦️♦️♦️♦️♦️");
    }

    private void printBaseActionChoice(int playerActionCount) {
        System.out.println("\n\nAction count: " + playerActionCount + "\n");
        System.out.println(
                "\nWhat would you like to do?\n"
                        + "a: Use your ability!\n"
                        + "m: Melee attack!\n"
                        + "g: Go to base.\n"
                        + "i: View available items.\n"
                        + "p: Purchase an item.\n"
                        + "s: Display your stats.\n"
                        + "e: Display enemy stats.\n"
                        + "w: Display minion wave.\n"
                        + "q: Quit the game\n");
    }

    private void printAttackActionChoice() {
        System.out.println("\nWhat target would you like to attack?\n");
        System.out.println("🗡️ 🗡️ 🗡️ 🗡️ 🗡️");
        for (int i = 0; i < minionWave.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + minionWave.get(i));
        }
        System.out.println("🗡️ 🗡️ 🗡️ 🗡️ 🗡️");

    }

}
