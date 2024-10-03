import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class MayanMachine {

  // temp
  static int numBonus = 0;
  static int numMaxWins = 0;
  static int num5000x = 0;
  static int num2500x = 0;
  static int num1000x = 0;
  static int num500x = 0;
  static int num250x = 0;
  static int num100x = 0;
  static int num0x = 0;
  static int num50_100x = 0;
  static int num0_10000x = 0;
  static double biggestBaser = 0.0;
  static double hitRate = 0;
  static int maxWin = 0;

  // user Balance and Bet
  static double userBalance = 1000.00;
  static double userBet = 1.00;
  static int loopBaseSpins = (int) userBalance;
  static int loopBonusBuys = (loopBaseSpins / 100);
  static int loopBonusHuntSpins = (int)(userBalance / 3);
  static int loopFeatureSpins = (loopBaseSpins / 15);

  // universal random usage
  static Random random = new Random();
  static boolean spinActive = false;
  static boolean bonusActive = false;
  static boolean featureActive = false;
  static boolean bonusSpinActive = false;
  static boolean bonusPurchased = false;

  // Create Reels
  static LinkedList<Integer> Col1 = new LinkedList<>();
  static LinkedList<Integer> Col2 = new LinkedList<>();
  static LinkedList<Integer> Col3 = new LinkedList<>();
  static LinkedList<Integer> Col4 = new LinkedList<>();
  static LinkedList<Integer> Col5 = new LinkedList<>();
  static LinkedList<Integer> Col6 = new LinkedList<>();

  // reset after every spin (or reset after bonus)
  static int multiplier = 1;
  static int totemLevel = 0;
  static int totemCharge = 0;
  static int totemCapacity = 20;
  static int freeSpins = 10;

  // reset after every spin
  static double roundPay = 0.00;

  // reset after every tumble
  static int Gold = 0;
  static int Black = 0;
  static int Purple = 0;
  static int Red = 0;
  static int Orange = 0;
  static int Pink = 0;
  static int LGreen = 0;
  static int LBlue = 0;
  static int Tan = 0;
  static int White = 0;

  // reset after every tumble
  static boolean W = false;
  static boolean T = false;
  static boolean LB = false;
  static boolean LG = false;
  static boolean PI = false;
  static boolean O = false;
  static boolean R = false;
  static boolean P = false;
  static boolean B = false;
  static boolean G = false;

  // relating to bonus (resets after every spin)
  static int totalBonusSymbols = 0;
  static int BonusSymbolsOnBoard = 0;
  static boolean BonusTrigger = false;
  static int BonusChance = 4590;


  public static int generateSymbols() {
    int randomNumber = random.nextInt(100);
    if (randomNumber < 15) {
      return 10; // 15% chance White
    } else if (randomNumber < 29) {
      return 9; // 14% chance Tan
    } else if (randomNumber < 42) {
      return 8; // 13% chance LightBlue
    } else if (randomNumber < 54) {
      return 7; // 12% chance LightGreen
    } else if (randomNumber < 65) {
      return 6; // 11% chance Pink
    } else if (randomNumber < 75) {
      return 5; // 10% chance Orange
    } else if (randomNumber < 83) {
      return 4; // 8% chance Red
    } else if (randomNumber < 90) {
      return 3; // 7% chance Purple
    } else if (randomNumber < 96) {
      return 2; // 6% chance Black
    } else {
      return 1; // 4% chance Gold
    }
  }

  // Function to generate random numbers and insert into the given LinkedList
  public static void generateReels(LinkedList<Integer> list) {
    for (int i = 0; i < 11; i++) {
      int randSymbol = generateSymbols(); // Generates a random number between 1 and 9
      if (i == 0) {
        int offsetStage = random.nextInt(2);
        if (offsetStage == 1) {

          if ((totalBonusSymbols < 4) && (random.nextInt(BonusChance) < 100) && !bonusActive && !featureActive) {
            list.add(0);
            totalBonusSymbols++;
          } else {
            list.add(randSymbol);
          }

          i++;

          if ((totalBonusSymbols < 4) && (random.nextInt(BonusChance) < 100) && !bonusActive && !featureActive) {
            list.add(0);
            totalBonusSymbols++;
          } else {
            list.add(randSymbol);
          }

        } else {

          if ((totalBonusSymbols < 4) && (random.nextInt(BonusChance) < 100) && !bonusActive && !featureActive) {
            list.add(0);
            totalBonusSymbols++;
          } else {
            list.add(randSymbol);
          }

        }
      } else {

        if ((totalBonusSymbols < 4) && (random.nextInt(BonusChance) < 100) && !bonusActive && !featureActive) {
          list.add(0);
          totalBonusSymbols++;
        } else {
          list.add(randSymbol);
        }
        i++;

        if ((totalBonusSymbols < 4) && (random.nextInt(BonusChance) < 100) && !bonusActive && !featureActive) {
          list.add(0);
          totalBonusSymbols++;
        } else {
          list.add(randSymbol);
        }
      }
    }
  }

  public static void countSymbols(LinkedList<Integer> list) {
    int currSymbol = 0;

    for (int i = 4; i >= 0; i--) {
      currSymbol = list.get(i);

      if (currSymbol == 10) {
        MayanMachine.White += 1;
      } else if (currSymbol == 9) {
        MayanMachine.Tan += 1;
      } else if (currSymbol == 8) {
        MayanMachine.LBlue += 1;
      } else if (currSymbol == 7) {
        MayanMachine.LGreen += 1;
      } else if (currSymbol == 6) {
        MayanMachine.Pink += 1;
      } else if (currSymbol == 5) {
        MayanMachine.Orange += 1;
      } else if (currSymbol == 4) {
        MayanMachine.Red += 1;
      } else if (currSymbol == 3) {
        MayanMachine.Purple += 1;
      } else if (currSymbol == 2) {
        MayanMachine.Black += 1;
      } else if (currSymbol == 1) {
        MayanMachine.Gold += 1;
      } else if (currSymbol == 0) {
        MayanMachine.BonusSymbolsOnBoard += 1;
      }

    }
  }

  public static void setRemovalFlags() {
    if (White >= 8) {
      W = true;
    }
    if (Tan >= 8) {
      T = true;
    }
    if (LBlue >= 8) {
      LB = true;
    }
    if (LGreen >= 8) {
      LG = true;
    }
    if (Pink >= 8) {
      PI = true;
    }
    if (Orange >= 8) {
      O = true;
    }
    if (Red >= 8) {
      R = true;
    }
    if (Purple >= 8) {
      P = true;
    }
    if (Black >= 8) {
      B = true;
    }
    if (Gold >= 8) {
      G = true;
    }

  }

  public static void removeAndAdd(LinkedList<Integer> list) {

    int currSymbol = 99; // set invalid # for removal
    int numRemovedSymbols = 0;

    // Remove White
    if (W) {
      for (int i = 4; i >= 0; i--) {
        currSymbol = list.get(i);
        if (currSymbol == 10) {
          list.set(i, 11);
          numRemovedSymbols++;
        }
      }
    }

    // Remove Tan
    if (T) {
      for (int i = 4; i >= 0; i--) {
        currSymbol = list.get(i);
        if (currSymbol == 9) {
          list.set(i, 11);
          numRemovedSymbols++;
        }
      }
    }

    // Remove LightBlue
    if (LB) {
      for (int i = 4; i >= 0; i--) {
        currSymbol = list.get(i);
        if (currSymbol == 8) {
          list.set(i, 11);
          numRemovedSymbols++;
        }
      }
    }

    // Remove LightGreen
    if (LG) {
      for (int i = 4; i >= 0; i--) {
        currSymbol = list.get(i);
        if (currSymbol == 7) {
          list.set(i, 11);
          numRemovedSymbols++;
        }
      }
    }

    // Remove Pink
    if (PI) {
      for (int i = 4; i >= 0; i--) {
        currSymbol = list.get(i);
        if (currSymbol == 6) {
          list.set(i, 11);
          numRemovedSymbols++;
        }
      }
    }

    // Remove Orange
    if (O) {
      for (int i = 4; i >= 0; i--) {
        currSymbol = list.get(i);
        if (currSymbol == 5) {
          list.set(i, 11);
          numRemovedSymbols++;
        }
      }
    }

    // Remove Red
    if (R) {
      for (int i = 4; i >= 0; i--) {
        currSymbol = list.get(i);
        if (currSymbol == 4) {
          list.set(i, 11);
          numRemovedSymbols++;
        }
      }
    }

    // Remove Purple
    if (P) {
      for (int i = 4; i >= 0; i--) {
        currSymbol = list.get(i);
        if (currSymbol == 3) {
          list.set(i, 11);
          numRemovedSymbols++;
        }
      }
    }

    // Remove Black
    if (B) {
      for (int i = 4; i >= 0; i--) {
        currSymbol = list.get(i);
        if (currSymbol == 2) {
          list.set(i, 11);
          numRemovedSymbols++;
        }
      }
    }

    // Remove Yellow
    if (G) {
      for (int i = 4; i >= 0; i--) {
        currSymbol = list.get(i);
        if (currSymbol == 1) {
          list.set(i, 11);
          numRemovedSymbols++;
        }
      }
    }

    // find # of symbols to add
    numRemovedSymbols++;
    int addTwoSymbols = (numRemovedSymbols / 2);
    // make it divisible in proper increments for adding

    for (int i = 0; i < addTwoSymbols; i++) {
      int newSymbol = generateSymbols();
      if ((totalBonusSymbols < 4) && (random.nextInt(BonusChance) < 100) && !bonusActive && !featureActive) {
        list.add(0);
        totalBonusSymbols++;
      } else {
        list.add(newSymbol);
      }

      if ((totalBonusSymbols < 4) && (random.nextInt(BonusChance) < 100) && !bonusActive && !featureActive) {
        list.add(0);
        totalBonusSymbols++;
      } else {
        list.add(newSymbol);
      }
    }

    // popping the symbols that need to be removed
    for (int i = 4; i >= 0; i--) {
      currSymbol = list.get(i);
      if (currSymbol == 11) {
        list.remove(i);
      }
    }

  }

  public static void calculatePayout() {

    // current tumble pay amount
    double currTumblePay = 0.0;

    if (W) {
      if (White < 10) {
        currTumblePay += (userBet * 0.10);
      } else if (White < 12) {
        currTumblePay += (userBet * 0.20);
      } else {
        currTumblePay += (userBet * 0.30);
      }
    }

    if (T) {
      if (Tan < 10) {
        currTumblePay += (userBet * 0.15);
      } else if (Tan < 12) {
        currTumblePay += (userBet * 0.3);
      } else {
        currTumblePay += (userBet * 0.45);
      }
    }

    if (LB) {
      if (LBlue < 10) {
        currTumblePay += (userBet * 0.2);
      } else if (LBlue < 12) {
        currTumblePay += (userBet * 0.4);
      } else {
        currTumblePay += (userBet * 0.6);
      }
    }

    if (LG) {
      if (LGreen < 10) {
        currTumblePay += (userBet * 0.25);
      } else if (LGreen < 12) {
        currTumblePay += (userBet * 0.5);
      } else {
        currTumblePay += (userBet * 0.75);
      }
    }

    if (PI) {
      if (Pink < 10) {
        currTumblePay += (userBet * 0.35);
      } else if (Pink < 12) {
        currTumblePay += (userBet * 0.7);
      } else {
        currTumblePay += (userBet * 1.05);
      }
    }

    if (O) {
      if (Orange < 10) {
        currTumblePay += (userBet * 0.5);
      } else if (Orange < 12) {
        currTumblePay += (userBet * 1.00);
      } else {
        currTumblePay += (userBet * 1.5);
      }
    }

    if (R) {
      if (Red < 10) {
        currTumblePay += (userBet * 1.0);
      } else if (Red < 12) {
        currTumblePay += (userBet * 2.0);
      } else {
        currTumblePay += (userBet * 3.0);
      }
    }

    if (P) {
      if (Purple < 10) {
        currTumblePay += (userBet * 1.5);
      } else if (Purple < 12) {
        currTumblePay += (userBet * 3.0);
      } else {
        currTumblePay += (userBet * 4.5);
      }
    }

    if (B) {
      if (Black < 10) {
        currTumblePay += (userBet * 2.25);
      } else if (Black < 12) {
        currTumblePay += (userBet * 4.5);
      } else {
        currTumblePay += (userBet * 6.75);
      }
    }

    if (G) {
      if (Gold < 10) {
        currTumblePay += (userBet * 5.0);
      } else if (Gold < 12) {
        currTumblePay += (userBet * 10.0);
      } else {
        currTumblePay += (userBet * 15.0);
      }
    }

    roundPay += (currTumblePay * multiplier);

  }

  public static void calculateMultiplier() {
    int totemMulti = 0;

    if (totemLevel == 1) {
      totemMulti = 1;
    } else if (totemLevel == 2) {
      totemMulti = (random.nextInt(2) + 2);
    } else if (totemLevel == 3) {
      totemMulti = (random.nextInt(2) + 4);
    } else if (totemLevel == 4) {
      totemMulti = (random.nextInt(4) + 6);
    } else if (totemLevel == 5) {
      totemMulti = (random.nextInt(5) + 10);
    } else if (totemLevel == 6) {
      totemMulti = (random.nextInt(10) + 15);
    } else if (totemLevel == 7) {
      totemMulti = (random.nextInt(25) + 25);
    } else if (totemLevel == 8) {
      totemMulti = (random.nextInt(50) + 50);
    } else if (totemLevel == 9) {
      totemMulti = (random.nextInt(150) + 100);
    } else if (totemLevel >= 10) {
      totemMulti = (random.nextInt(251) + 250);
    }

    multiplier += totemMulti;

    if (multiplier > 10000) {
      multiplier = 10000;
    }

  }

  public static void updateTotem() {

    // Update Totem Bars
    if (W) {
      totemCharge += 2;
    }
    if (T) {
      totemCharge += 3;
    }
    if (LB) {
      totemCharge += 4;
    }
    if (LG) {
      totemCharge += 5;
    }
    if (PI) {
      totemCharge += 6;
    }
    if (O) {
      totemCharge += 7;
    }
    if (R) {
      totemCharge += 8;
    }
    if (P) {
      totemCharge += 9;
    }
    if (B) {
      totemCharge += 10;
    }
    if (G) {
      totemCharge += 20;
    }

    if (totemCharge >= 20 && totemLevel < 10) {
      totemLevel += 1;
      totemCharge = 0;
      freeSpins += 2;
    }


  }

  public static void resetTumble() {

    // reset counts
    White = 0;
    Tan = 0;
    LBlue = 0;
    LGreen = 0;
    Pink = 0;
    Orange = 0;
    Red = 0;
    Purple = 0;
    Black = 0;
    Gold = 0;

    // reset bonus symbol count
    BonusSymbolsOnBoard = 0;

    // reset flags for popping
    W = false;
    T = false;
    LB = false;
    LG = false;
    PI = false;
    O = false;
    R = false;
    P = false;
    B = false;
    G = false;

  }

  public static void resetSpin() {

    // for base game after every spin

    // reset roundPay
    roundPay = 0;

    // reset total Bonus Symbols
    totalBonusSymbols = 0;

    // reset multiplier
    multiplier = 1;

    // reset totem Level
    totemLevel = 1;

  }

  public static void printBoard() {
    System.out.println();
    System.out.println();
    for (int i = 4; i >= 0; i--) {
      System.out.print(Col1.get(i) + " ");
      if (Col1.get(i) != 10) {
        System.out.print(" ");
      }
      System.out.print(Col2.get(i) + " ");
      if (Col2.get(i) != 10) {
        System.out.print(" ");
      }
      System.out.print(Col3.get(i) + " ");
      if (Col3.get(i) != 10) {
        System.out.print(" ");
      }
      System.out.print(Col4.get(i) + " ");
      if (Col4.get(i) != 10) {
        System.out.print(" ");
      }
      System.out.print(Col5.get(i) + " ");
      if (Col5.get(i) != 10) {
        System.out.print(" ");
      }
      System.out.print(Col6.get(i) + " ");
      if (Col6.get(i) != 10) {
        System.out.print(" ");
      }
      System.out.println();
    }
    System.out.println();
  }

  public static void printUserInfo() {

    //System.out.println("Bonus : " + numBonus);
    String formatBalance = String.format("%.2f", userBalance);
    String formatBet = String.format("%.2f", userBet);
    String formatRoundPay = String.format("%.2f", roundPay);

    System.out.println("Round Win : " + formatRoundPay);
    System.out.println("Balance : " + formatBalance);
    System.out.println("Bet : " + formatBet);
    System.out.println("");
  }

  public static void baseGameTotemStrike() {
    int totemLevelBG = (random.nextInt(1000)); // 10 totem levels
    if (totemLevelBG >= 730) {
      totemLevel = 1;
      calculateMultiplier();
    } else if (totemLevelBG >= 520) {
      totemLevel = 2;
      calculateMultiplier();
    } else if (totemLevelBG >= 350) {
      totemLevel = 3;
      calculateMultiplier();
    } else if (totemLevelBG >= 210) {
      totemLevel = 4;
      calculateMultiplier();
    } else if (totemLevelBG >= 100) {
      totemLevel = 5;
      calculateMultiplier();
    } else if (totemLevelBG >= 50) {
      totemLevel = 6;
      calculateMultiplier();
    } else if (totemLevelBG >= 24) {
      totemLevel = 7;
      calculateMultiplier();
    } else if (totemLevelBG >= 12) {
      totemLevel = 8;
      calculateMultiplier();
    } else if (totemLevelBG >= 4) {
      totemLevel = 9;
      calculateMultiplier();
    } else if (totemLevelBG >= 0) {
      totemLevel = 10;
      calculateMultiplier();
    }
  }

  public static void featureTotemStrike() {
    int totemLevelFS = (random.nextInt(584) + 1); // 5-10 totem levels
    if (totemLevelFS >= 351) {
      totemLevel = 5;
      calculateMultiplier();
    } else if (totemLevelFS >= 201) {
      totemLevel = 6;
      calculateMultiplier();
    } else if (totemLevelFS >= 101) {
      totemLevel = 7;
      calculateMultiplier();
    } else if (totemLevelFS >= 51) {
      totemLevel = 8;
      calculateMultiplier();
    } else if (totemLevelFS >= 21) {
      totemLevel = 9;
      calculateMultiplier();
    } else if (totemLevelFS >= 1) {
      totemLevel = 10;
      calculateMultiplier();
    }
  }
  
  public static void baseSpin() {

    //for (int r = 0; r < loopBaseSpins; r++) {
      /*
       * 
       */

      userBalance -= userBet;

      Col1.clear();
      Col2.clear();
      Col3.clear();
      Col4.clear();
      Col5.clear();
      Col6.clear();

      spinActive = true;

      generateReels(Col1);
      generateReels(Col2);
      generateReels(Col3);
      generateReels(Col4);
      generateReels(Col5);
      generateReels(Col6);

      // random totem strike chance base game 5.55 % //set at 1/18
      if (random.nextInt(1000) < 64) {
        baseGameTotemStrike();
      }

      /*
       * 
       */
      // while spin is active repeat tumbling ||||
      while (spinActive) {

        // Turn off for sims
        printBoard(); ///////////////////////////////////////////////////////////
        System.out.println("Multi : " + multiplier); /////////////////////////////////////////

        countSymbols(Col1);
        countSymbols(Col2);
        countSymbols(Col3);
        countSymbols(Col4);
        countSymbols(Col5);
        countSymbols(Col6);

        setRemovalFlags();

        if (W | T | LB | LG | PI | O | R | P | B | G) {
          removeAndAdd(Col1);
          removeAndAdd(Col2);
          removeAndAdd(Col3);
          removeAndAdd(Col4);
          removeAndAdd(Col5);
          removeAndAdd(Col6);
          calculatePayout();
        } else {
          spinActive = false;

          if (BonusSymbolsOnBoard == 4) {
            BonusTrigger = true;
          }
        }

        resetTumble();

        if (BonusTrigger) {
          BonusTrigger = false;
          numBonus++;
          //roundPay += 97.68310226; ////////////////////////////////////////////////////////
          initiateBonus();
        }

      }
      // while spin is active repeat tumbling ||||
      /*
       * 
       */

      // When Spin is Complete
      if (bonusActive) {
        bonusActive = false;
      } else {
        // roundPay = Math.round(roundPay * 100.0) / 100.0;
        if (roundPay >= (10000.00 * userBet)) {
          roundPay = (10000.00 * userBet);
        }
        userBalance += roundPay;
        //
        
        if (biggestBaser < roundPay) {
          biggestBaser = roundPay;
        }
        /*
        if (roundPay > 0.0) {
          hitRate++;
        }
        */
        //
      }

      printUserInfo();
      // Turn off for sims

      // reset spin (reset round)
      resetSpin();
      // System.out.println(r);

      /*
       * 
       */
    //}
    //printUserInfo();
    //System.out.println("Bonuses : " + numBonus);
    // Turn on for sims
    //System.out.println("Biggest Baser : " + String.format("%.2f", biggestBaser));
    //System.out.println("Hit Rate : "
        //+ (String.format("%.2f", (Math.round((hitRate / loopBaseSpins) * 10000.0)) / 100.0)
            //+ " %"));
    //System.out.println("Max Wins : " + maxWin);
  }

  public static void initiateBonus() {

    //for (int r = 0; r < loopBonusBuys; r++) { // loopBonusBuys
      /*
       *
       */
      //userBalance -= userBet * 100; // comment out
      if (bonusPurchased) {
        userBalance -= userBet * 100;
        bonusPurchased = false;
      }

      // set bonusActive = true
      bonusActive = true;

      // reset total Bonus Symbols
      totalBonusSymbols = 0;

      // reset multiplier
      multiplier = 1;

      // set totem Level
      totemLevel = 1;

      // reset free spin value since bonus was activated
      freeSpins = 10;

      for (int spin = 1; spin <= freeSpins; spin++) {
        /*
         * 
         */

        Col1.clear();
        Col2.clear();
        Col3.clear();
        Col4.clear();
        Col5.clear();
        Col6.clear();

        // code wait for user to call spin
        bonusSpinActive = true;

        generateReels(Col1);
        generateReels(Col2);
        generateReels(Col3);
        generateReels(Col4);
        generateReels(Col5);
        generateReels(Col6);

        while (bonusSpinActive) {

          // Turn off for sims
          System.out.println("Multi : " + multiplier);
          // /////////////////////////////////////////////
          printBoard();
          // ////////////////////////////////////////////////////////////////////////////


          countSymbols(Col1);
          countSymbols(Col2);
          countSymbols(Col3);
          countSymbols(Col4);
          countSymbols(Col5);
          countSymbols(Col6);

          setRemovalFlags();

          if (W | T | LB | LG | PI | O | R | P | B | G) {
            removeAndAdd(Col1);
            removeAndAdd(Col2);
            removeAndAdd(Col3);
            removeAndAdd(Col4);
            removeAndAdd(Col5);
            removeAndAdd(Col6);
            calculatePayout();
            updateTotem();
            calculateMultiplier();
          } else {
            bonusSpinActive = false;
          }

          resetTumble();

        }
      

        // When Spin Complete
        roundPay = Math.round(roundPay * 100.0) / 100.0;
        if (roundPay >= 10000.0) {
          roundPay = (10000.00 * userBet);
          spin = freeSpins + 1;
        }

        // Turn off for sims
        System.out.println("FreeSpins Remaining : " + (freeSpins - spin));
        // /////////////////////////
        printUserInfo();
        // ///////////////////////////////////////////////////////////////////////////
        System.out.println("Totem Level : " + totemLevel);
        System.out.println("Totem Charge : " + totemCharge + " / 20");
        // /////////////////////////////////////////

        /*
         * 
         */
      }
/*
      if (roundPay >= (10000.00 * userBet)) {
        roundPay = (10000.00 * userBet);
        numMaxWins++; // comment out
      }


      // comment out |||| //////////////////////////////////////////////////////////////////////////
      else if (roundPay >= 5000.00) {
        num5000x++;
      } else if (roundPay >= 2500.00) {
        num2500x++;
      } else if (roundPay >= 1000.00) {
        num1000x++;
      } else if (roundPay >= 500.00) {
        num500x++;
      } else if (roundPay >= 250.00) {
        num250x++;
      } else if (roundPay >= 100.00) {
        num100x++;
      } else if (roundPay >= 0.0) {
        num0x++;
      }

      if ((roundPay >= 50.00) && (roundPay <= 150.00)) {
        num50_100x++;
      } else {
        num0_10000x++;;
      }
      // comment out |||| //////////////////////////////////////////////////////////////////////////
*/

      userBalance += roundPay;

      /*
      //
      if (roundPay >= 10000) { //////////////////////////////////////////////////////////////////////
        // System.out.println("Round : " + r);
        // ////////////////////////////////////////////////////////
        maxWin++; //////////////////////////////////////////////////////////////////////////////////
      }
*/
      // turn off for sims /////////////////////////////////////////////////////////////////////////
      System.out.println("Bonus Pay : " + String.format("%.2f",roundPay));
      // /////////////////////////////////////////////////////////////

      printUserInfo();
      
      bonusActive = false;

      resetSpin();

      totemLevel = 0;
      totemCharge = 0;
      //} loop bracket
      /*
       * 
       */
    

/*
    // comment out |||| ////////////////////////////////////////////////////////////////////////////
    printUserInfo();
    System.out.println("Free Spins :" + freeSpins);
    System.out.println("numMaxWins : " + numMaxWins);
    System.out.println("num5000xs : " + num5000x);
    System.out.println("num2500xs : " + num2500x);
    System.out.println("num1000xs : " + num1000x);
    System.out.println("num500xs : " + num500x);
    System.out.println("num250xs : " + num250x);
    System.out.println("num100xs : " + num100x);
    System.out.println("num0xs : " + num0x);
    System.out.println("Job : " + num50_100x);
    System.out.println("Fair : " + num0_10000x);
    System.out.println("");
    // comment out |||| ////////////////////////////////////////////////////////////////////////////
*/
  }

  public static void featureSpin() {
    
    featureActive = true;

    //for (int r = 0; r < loopFeatureSpins; r++) {
      /*
       * 
       */

      userBalance -= (userBet * 15);

      Col1.clear();
      Col2.clear();
      Col3.clear();
      Col4.clear();
      Col5.clear();
      Col6.clear();

      spinActive = true;

      generateReels(Col1);
      generateReels(Col2);
      generateReels(Col3);
      generateReels(Col4);
      generateReels(Col5);
      generateReels(Col6);

      // random totem strike chance base game 5.55 % //set at 1/18
        featureTotemStrike();

      /*
       * 
       */
      // while spin is active repeat tumbling ||||
      while (spinActive) {

        // Turn off for sims
        printBoard();
        System.out.println("Multi : " + multiplier);

        countSymbols(Col1);
        countSymbols(Col2);
        countSymbols(Col3);
        countSymbols(Col4);
        countSymbols(Col5);
        countSymbols(Col6);

        setRemovalFlags();

        if (W | T | LB | LG | PI | O | R | P | B | G) {
          removeAndAdd(Col1);
          removeAndAdd(Col2);
          removeAndAdd(Col3);
          removeAndAdd(Col4);
          removeAndAdd(Col5);
          removeAndAdd(Col6);
          calculatePayout();
        } else {
          spinActive = false;

          if (BonusSymbolsOnBoard == 4) {
            BonusTrigger = true;
          }
        }

        resetTumble();


      }
      // while spin is active repeat tumbling ||||
      /*
       * 
       */

      // When Spin is Complete

 
        // roundPay = Math.round(roundPay * 100.0) / 100.0;
        if (roundPay >= (10000.00 * userBet)) {
          roundPay = (10000.00 * userBet);
        }
        userBalance += roundPay;
        //
        /*
        if (biggestBaser < roundPay) {
          biggestBaser = roundPay;
        }
        if (roundPay > 0.0) {
          hitRate++;
        }
        */
        //


      // Turn off for sims
      //printUserInfo();
        printUserInfo();
      // reset spin (reset round)
      resetSpin();
      // System.out.println(r);
      /*
       * 
       */
    //}

    featureActive = false;
    // Turn on for sims
    
    //System.out.println("Biggest Baser : " + String.format("%.2f", biggestBaser));
    //System.out.println("Hit Rate : "
        //+ (String.format("%.2f", (Math.round((hitRate / loopBaseSpins) * 10000.0)) / 100.0)
            //+ " %"));
    //System.out.println("Max Wins : " + maxWin);
    
  }

  
  public static void bonusHuntSpin() {
    BonusChance = 2417;

    //for (int r = 0; r < loopBonusHuntSpins; r++) {
      /*
       * 
       */

      userBalance -= (userBet * 3);


      Col1.clear();
      Col2.clear();
      Col3.clear();
      Col4.clear();
      Col5.clear();
      Col6.clear();

      spinActive = true;

      generateReels(Col1);
      generateReels(Col2);
      generateReels(Col3);
      generateReels(Col4);
      generateReels(Col5);
      generateReels(Col6);

      /*
       * 
       */
      // while spin is active repeat tumbling ||||
      while (spinActive) {

        // Turn off for sims
        printBoard();
        System.out.println("Multi : " + multiplier);

        countSymbols(Col1);
        countSymbols(Col2);
        countSymbols(Col3);
        countSymbols(Col4);
        countSymbols(Col5);
        countSymbols(Col6);

        setRemovalFlags();

        if (W | T | LB | LG | PI | O | R | P | B | G) {
          removeAndAdd(Col1);
          removeAndAdd(Col2);
          removeAndAdd(Col3);
          removeAndAdd(Col4);
          removeAndAdd(Col5);
          removeAndAdd(Col6);
          calculatePayout();
        } else {
          spinActive = false;

          if (BonusSymbolsOnBoard == 4) {
            BonusTrigger = true;
          }
        }

        resetTumble();

        if (BonusTrigger) {
          BonusTrigger = false;
          numBonus++;
          //roundPay += 97.68310226;
          initiateBonus();
        }

      }
      // while spin is active repeat tumbling ||||
      /*
       * 
       */

      // When Spin is Complete
      if (bonusActive) { ////////////////???????REDUNDANCY????????????//////////
        bonusActive = false;
      } else {
        // roundPay = Math.round(roundPay * 100.0) / 100.0;
        if (roundPay >= (10000.00 * userBet)) {
          roundPay = (10000.00 * userBet);
        }
        userBalance += roundPay;
        //
        if (biggestBaser < roundPay) {
          biggestBaser = roundPay;
        }
        if (roundPay > 0.0) {
          hitRate++;
        }
        //
      }

      // Turn off for sims
      printUserInfo();

      // reset spin (reset round)
      resetSpin();
      // System.out.println(r);

      /*
       * 
       */
    //}

    BonusChance = 4590;
    // Turn on for sims
    //printUserInfo();
    //System.out.println("Biggest Baser : " + String.format("%.2f", biggestBaser));
    //System.out.println("Hit Rate : "
        //+ (String.format("%.2f", (Math.round((hitRate / loopBaseSpins) * 10000.0)) / 100.0)
            //+ " %"));
  }

  public void runMenu() {
    printUserInfo();
    Scanner scanner = new Scanner(System.in);
    while (true) {
        System.out.println("Main Menu:");
        System.out.println("1. Base Spin");
        System.out.println("2. Bonus Hunt Spin ");
        System.out.println("3. Initiate Bonus");
        System.out.println("4. Feature Spin");
        System.out.println("5. Increase Bet");
        System.out.println("6. Decrease Bet");
        System.out.println("0. Exit");
        System.out.print("Please select an option: ");
        
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
              baseSpin();
                break;
            case 2:
              bonusHuntSpin();
                break;
            case 3:
              bonusPurchased = true;
              initiateBonus();
                break;
            case 4:
                featureSpin();
                break;
            case 5:
              increaseBet();
              break;
            case 6:
              decreaseBet();
              break;
            case 0:
                System.out.println("Exiting...");
                scanner.close();
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}
  
  
  private void decreaseBet() {
    if(userBet == 0.10) {
      System.out.println("Minimum Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 0.20) {
      userBet = 0.10;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 0.40) {
      userBet = 0.20;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 0.60) {
      userBet = 0.40;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 0.80) {
      userBet = 0.60;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 1.00) {
      userBet = 0.80;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 1.20) {
      userBet = 1.00;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 1.40) {
      userBet = 1.20;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 1.60) {
      userBet = 1.40;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 1.80) {
      userBet = 1.60;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 2.00) {
      userBet = 1.80;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 3.00) {
      userBet = 2.00;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 4.00) {
      userBet = 3.00;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 5.00) {
      userBet = 4.00;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 6.00) {
      userBet = 5.00;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 7.00) {
      userBet = 6.00;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 8.00) {
      userBet = 7.00;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 9.00) {
      userBet = 8.00;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 10.00) {
      userBet = 9.00;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 15.00) {
      userBet = 10.00;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 20.00) {
      userBet = 15.00;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 25.00) {
      userBet = 20.00;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 30.00) {
      userBet = 25.00;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 35.00) {
      userBet = 30.00;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 40.00) {
      userBet = 35.00;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 45.00) {
      userBet = 40.00;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 50.00) {
      userBet = 45.00;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 75.00) {
      userBet = 50.00;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 100.00) {
      userBet = 75.00;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    }
  }

  private void increaseBet() {
    if(userBet == 0.10) {
      userBet = 0.20;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 0.20) {
      userBet = 0.40;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 0.40) {
      userBet = 0.60;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 0.60) {
      userBet = 0.80;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 0.80) {
      userBet = 1.00;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 1.00) {
      userBet = 1.20;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 1.20) {
      userBet = 1.40;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 1.40) {
      userBet = 1.60;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 1.60) {
      userBet = 1.80;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 1.80) {
      userBet = 2.00;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 2.00) {
      userBet = 3.00;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 3.00) {
      userBet = 4.00;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 4.00) {
      userBet = 5.00;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 5.00) {
      userBet = 6.00;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 6.00) {
      userBet = 7.00;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 7.00) {
      userBet = 8.00;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 8.00) {
      userBet = 9.00;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 9.00) {
      userBet = 10.00;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 10.00) {
      userBet = 15.00;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 15.00) {
      userBet = 20.00;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 20.00) {
      userBet = 25.00;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 25.00) {
      userBet = 30.00;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 30.00) {
      userBet = 35.00;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 35.00) {
      userBet = 40.00;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 40.00) {
      userBet = 45.00;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 45.00) {
      userBet = 50.00;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 50.00) {
      userBet = 75.00;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 75.00) {
      userBet = 100.00;
      System.out.println("Bet Size = " + String.format("%.2f", userBet));
    } else if (userBet == 100.00) {
      System.out.println("Maximum Bet Size = " + userBet);
    }
  }

  public static void main(String[] args) {

    // initiateBonus();
    // baseSpin();
    // baseSpin();
    // featureSpin();
    
    // loopBaseSpins
    // loopBonusBuys
    // loopBonusHuntSpins
    // loopFeatureSpins
    
    MayanMachine menu = new MayanMachine();
    menu.runMenu();
        
    //for (int iterations = 0; iterations < 1; iterations++) {
    //  baseSpin();
    //}
  }
}
