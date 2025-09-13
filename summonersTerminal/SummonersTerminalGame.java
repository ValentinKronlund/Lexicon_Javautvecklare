package summonersTerminal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SummonersTerminalGame {
    Scanner scanner = new Scanner(System.in);

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

        System.out.println(playerChampion);

        while (playerChampion == null) {

            try {
                System.out.println(
                        "\n👤 Choose your champion: 👤\n"
                                + "(G)aren\n"
                                + "(K)atarina\n"
                                + "(V)eigar\n");

                String championRequest = scanner.nextLine();

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
                        + "Player Champion:" + "\n😎  " + this.playerChampion.toString() + "\n\n"
                        + "Enemy Champion:" + "\n😈  " + this.enemyChampion.toString() + "\n");

        System.out.println("\n\n🔮 Minions have spawned! 🔮");
        return true;
    }

    private boolean GameLoop() {
        while (true) {
            System.out.println("\nNew wave incoming!");
            System.out.println("Wave number: " + waveNumber + "\n");
            int playerActionCount = 0;

            generateMinionWave();
            printWave();

            while (playerActionCount < 5) {
                printBaseActionChoice(playerActionCount);
                try {
                    char playerChoice = scanner.nextLine().charAt(0);
                    switch (playerChoice) {
                        case 'a': {
                            System.out
                                    .println("\nYou used your ability, causing: " + playerChampion.ability()
                                            + " damage!");
                            playerActionCount++;
                            break;
                        }

                        case 'm': {
                            attackAction();
                            playerActionCount++;
                            break;
                        }

                        case 'p': {
                            playerChampion.equip(Item.INFINITY_EDGE);
                            System.out.println("\nYou purchased an item!");
                            playerActionCount += 2;
                            break;
                        }

                        case 's': {
                            System.out.println("\n" + playerChampion.toString());
                            break;
                        }

                        case 'e': {
                            System.out.println("\n" + enemyChampion.toString());
                            break;
                        }

                        case 'w': {
                            printWave();
                            break;
                        }

                        case 'q': {
                            System.out.println(
                                    "\nYou are about to quit the game 😵"
                                            + "\nAre you sure you want to leave and lose 25 LP?"
                                            + "\nYou'll be stuck in elo hell!"
                                            + "\n\nType 'q' if you want to quit");
                            char quitConfirmation = scanner.nextLine().charAt(0);
                            switch (quitConfirmation) {
                                case 'q':
                                    return false;
                                default:
                                    continue;
                            }
                        }

                        default:
                            throw new AssertionError("There is currently no command for: " + playerChoice);
                    }
                } catch (AssertionError err) {
                    System.out.println(err);
                    continue;
                }

            }

            waveNumber++;

        }
    }

    // Action helpers below 👇🏽 --------------------
    private boolean attackAction() {
        printAttackActionChoice();
        int playerChoice = scanner.nextInt();
        scanner.nextLine(); // <-- Scanner bug fix, removes trailing white space

        while (true) {
            try {
                if (minionWave.get(playerChoice - 1) == null) {
                    throw new IllegalArgumentException();
                }

                Minion targetMinion = minionWave.get(playerChoice - 1);
                boolean successfulAttack = targetMinion.takeDamage(playerChampion.attack(), minionWave, playerChampion);
                if (successfulAttack) {
                    System.out.println("\nYou attacked, causing: " + playerChampion.attack() +
                            " damage to the minion!");
                    System.out.println(targetMinion.toString());
                }

                return true;
            } catch (IllegalArgumentException e) {
                System.out.println("No minion at given index: " + playerChoice);
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

    // Print helpers below 👇🏽 ---------------------
    private void printWave() {
        System.out.println("\n-----------------------------");
        for (Minion minion : minionWave) {
            System.out.println(minion.toString());
        }
        System.out.println("\n-----------------------------");
    }

    private void printBaseActionChoice(int playerActionCount) {
        System.out.println("\n\nAction count: " + playerActionCount + "\n");
        System.out.println(
                "\nWhat would you like to do?\n"
                        + "a: Use your ability!\n"
                        + "m: Melee attack!\n"
                        + "p: Purchase an item.\n"
                        + "s: Display your stats.\n"
                        + "e: Display enemy stats.\n"
                        + "w: Display minion wave.\n"
                        + "q: Quit the game");
    }

    private void printAttackActionChoice() {
        System.out.println("\nWhat target would you like to attack?\n");
        for (int i = 0; i < minionWave.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + minionWave.get(i));
        }
    }

}
