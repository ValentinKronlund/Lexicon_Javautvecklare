package summonersTerminal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import helpers.Helpers;
import summonersTerminal.gameHelpers.Action;
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

            Action.generateMinionWave(minionWave, waveNumber);
            Copy.waveCopy(minionWave);
            playerChampion.walkFromBase();

            while (playerActionCount < 5 & playerChampion.getIsDead() == false) {
                Copy.baseActionChoiceCopy(playerActionCount);

                try {
                    char playerChoice = helper.askChar(scanner, "");

                    // Main combat choices below 👇🏽 -------------------------
                    switch (playerChoice) {
                        case 'a': {
                            boolean successfulAttack = Action.abilityAction(playerChampion, minionWave);
                            if (successfulAttack)
                                playerActionCount++;
                            break;
                        }

                        case 'm': {
                            Action.attackAction(playerChampion, minionWave);
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
                            continue; // <----- Continue here as to not lose a round or take damage.
                        }
                        case 'p': {
                            Action.purchaseOptions(playerChampion);
                            playerActionCount += 2;
                            break;
                        }

                        case 's': {
                            System.out.println("\n" + playerChampion.toString());
                            continue; // <----- Continue here as to not lose a round or take damage.
                        }

                        case 'e': {
                            System.out.println("\n" + enemyChampion.toString());
                            continue; // <----- Continue here as to not lose a round or take damage.
                        }

                        case 'w': {
                            Copy.waveCopy(minionWave);
                            continue; // <----- Continue here as to not lose a round or take damage.
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

}
