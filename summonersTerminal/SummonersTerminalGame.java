package summonersTerminal;

import java.util.Scanner;

public class SummonersTerminalGame {
    Scanner scanner = new Scanner(System.in);

    public boolean gameInProgress;
    private Champion playerChampion;
    private Champion enemyChampion;

    public boolean PlayGame() {
        this.gameInProgress = InitiateGame();

        return true;
    }

    private boolean InitiateGame() {
        System.out.println("\n🔮 Welcome, to Summoner's Terminal! 🔮");
        System.out.println(
                "\n[ Rules ]: \n"
                        + "   Your aim is to destroy the enemy nexus 🔻, while protecting your own 💎\n"
                        + "   To attack a nexus, a champion must first break through the enemies minions\n\n"
                        + "   Minions spawn in waves at the start of each combat sequence\n"
                        + "   A minion wave consists of 2 footman minions with 90hp, and 3 caster minions with 70hp\n"
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
                "\nChampions selected!\n"
                        + "Player Champion:" + "\n   " + this.playerChampion.toString() + "\n"
                        + "Enemy Champion:" + "\n   " + this.enemyChampion.toString() + "\n");

        System.out.println("\n\n🔮 Minions have spawned! 🔮");
        return true;
    }

    private void GameLoop() {
        while (true) {
            System.out.println(
                    "\nWhat would you like to do?\n"
                            + "a: Use your ability!\n"
                            + "m: Melee attack!\n"
                            + "p: Purchase an item.\n"
                            + "s: Display your stats.\n"
                            + "e: Display enemy stats.\n"
                            + "q: Quit the game");
            char playerChoice = scanner.nextLine().charAt(0);

            switch (playerChoice) {
                case 'a':
                    System.out.println("\nYou used your ability, causing: " + playerChampion.ability() + " damage!");
                    break;
                case 'm':
                    System.out.println("\nYou attacked, causing: " + playerChampion.attack() + " damage!");
                    break;
                case 'p':
                    playerChampion.equip(Item.INFINITY_EDGE);
                    System.out.println("\nYou purchased an item!");
                    break;
                case 's':
                    System.out.println(playerChampion.toString());
                    break;
                case 'e':
                    System.out.println(enemyChampion.toString());
                    break;
                case 'q':
                    System.out.println(
                            "\nYou are about to quit the game 😵"
                                    + "\nAre you sure you want to leave and lose 25 LP?"
                                    + "\nYou'll be stuck in elo hell");
                    char quitConfirmation = scanner.nextLine().charAt(0);

                default:
                    throw new AssertionError("There is currently no command for: " + playerChoice);
            }
        }
    }

}
