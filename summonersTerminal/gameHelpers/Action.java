package summonersTerminal.gameHelpers;

import helpers.Helpers;
import summonersTerminal.Champion;
import summonersTerminal.Item;

public class Action {
    Helpers helper = new Helpers();

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
