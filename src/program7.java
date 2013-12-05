//Anthony Mace  CSC205AB
//This program is designed to implement, create,
//and manipulate binary trees.

import java.util.*;
import java.io.*;

public class program7 {

    public static final Scanner console = new Scanner(System.in);

    public static void main(String[] args) {
        printIntro();
        char mainCont = '0';
        char oneTableCont = '0';
        do {
            int mainMenuChoice = getMainMenuChoice();
            if (mainMenuChoice == 1) {
                Table tree = new Table();
                do {
                    int menuChoice = getMenuChoice();
                    if (menuChoice == 1) {
                        System.out.println("An empty table will replace the current");
                        System.out.println("table.");
                        tree = new Table();
                    }
                    performOperation(menuChoice, tree);
                    oneTableCont = continueOneTable();
                } while(oneTableCont == 'y' || oneTableCont == 'Y');
            } else if (mainMenuChoice == 2) {
                readFromFile();
            } else if (mainMenuChoice == 3){
                testRandomTables();
            }
            System.out.println();
            mainCont = continueProg();
        } while(mainCont == 'Y' || mainCont == 'y');
        System.out.println("You have quit the program");
    }

    public static void printIntro() {
        System.out.println("This program allows the user to");
        System.out.println("create, fill, and manipulate a table!");
    }

    public static void printMainMenu() {
        System.out.println();
        System.out.println("1  -  Test one table");
        System.out.println("2  -  Read data file to create table");
        System.out.println("3  -  Create random tables");
        System.out.println();
    }

    public static void printMenu() {
        System.out.println();
        System.out.println("1  -  Create an empty table");
        System.out.println("2  -  Insert an item into the table");
        System.out.println("3  -  Delete an item from the table");
        System.out.println("4  -  Search for an item in the table");
        System.out.println("5  -  Return the height of the tree");
        System.out.println("6  -  Return the number of nodes in the tree");
        System.out.println("7  -  Return average level of nodes in the tree");
        System.out.println("8  -  Print tree (tree-like shape)");
        System.out.println("9  -  Print tree (in-order)");
        System.out.println();
    }

    public static int getMainMenuChoice() {
        printMainMenu();
        System.out.print("Enter menu option: ");
        int choice = console.nextInt();
        while (choice < 1 || choice > 3) {
            System.out.println();
            System.out.println("You did not choose a valid operation!");
            System.out.print("Please reenter a valid operation: ");
            choice = console.nextInt();
        }
        System.out.println();
        return choice;
    }

    public static int getMenuChoice() {
        printMenu();
        System.out.print("Enter menu option: ");
        int choice = console.nextInt();
        while (choice < 1 || choice > 9) {
            System.out.println();
            System.out.println("You did not choose a valid operation!");
            System.out.print("Please reenter a valid operation: ");
            choice = console.nextInt();
        }
        System.out.println();
        return choice;
    }

    public static char continueOneTable() {
        char cont = '0';
        System.out.print("Enter Y to continue with one table, N to quit: ");
        cont = console.next().charAt(0);
        return cont;
    }
    public static char continueProg() {
        char cont = '0';
        System.out.print("Enter Y to continue program, N to quit: ");
        cont = console.next().charAt(0);
        return cont;
    }

    //Decides which operation to perform based on listOperations() value
    public static void performOperation(int opChoice, Table tree) {
        switch (opChoice) {
            case 2:
                insertPlayer(tree);
                break;
            case 3:
                deletePlayer(tree);
                break;
            case 4:
                searchTable(tree);
                break;
            case 5:
                System.out.println("Height of tree: " + tree.getHeight());
                break;
            case 6:
                System.out.println("Number of nodes: " + tree.getSize());
                break;
            case 7:
                System.out.println("Average level: " + tree.getAverageLevel());
                break;
            case 8:
                System.out.println(tree.showTree());
                break;
            case 9:
                System.out.println(tree);
                break;
        }
        System.out.println();
    }

    public static void insertPlayer(Table tree) {
        MLBPlayer player = createPlayer();
        tree.insert(player);
    }

    public static void deletePlayer(Table tree) {
        System.out.println("Enter the key of the player you wish to delete!");
        KeyComparable playerKey = createPlayerKey();
        tree.delete(playerKey);
        System.out.println("Player deleted!");
    }

    public static void searchTable(Table tree) {
        System.out.println("Enter the key of the player you wish to search for!");
        MLBPlayerKey playerKey = createPlayerKey();
        KeyComparable player = tree.search(playerKey);
        if (player != null) {
            System.out.println(player);
        } else {
            System.out.println("Player not found.");
        }
    }

    public static MLBPlayer createPlayer() {
        int jerseyNum = 0;
        String team = "";
        String playerName = "";
        double battingAverage = 0.0;
        MLBPlayer player = null;
        System.out.print("Enter the player jersey number: ");
        jerseyNum = console.nextInt();
        System.out.println();
        System.out.print("Enter the team name: ");
        team = console.next();
        System.out.println();
        System.out.print("Enter the player name: ");
        playerName = console.next();
        System.out.println();
        System.out.print("Enter the batting average: ");
        battingAverage = console.nextDouble();
        player = new MLBPlayer(jerseyNum, team, playerName, battingAverage);
        return player;
    }

    public static MLBPlayerKey createPlayerKey() {
        int jerseyNum = 0;
        String team = "";
        MLBPlayerKey playerKey = null;
        System.out.print("Enter the player jersey number: ");
        jerseyNum = console.nextInt();
        System.out.println();
        System.out.print("Enter the team name: ");
        team = console.next();
        playerKey = new MLBPlayerKey(jerseyNum, team);
        return playerKey;
    }

    public static void readFromFile() {
        Table fileTree = new Table();
        String fileName = getFileName();
        try {
            Scanner fromFile = new Scanner(new File(fileName));
            do {
                int jerseyNum = fromFile.nextInt();
                String team = fromFile.next();
                String player = fromFile.next();
                double batAvg = fromFile.nextDouble();
                MLBPlayer playerFromFile = new MLBPlayer(jerseyNum, team,
                                                         player, batAvg);
                fileTree.insert(playerFromFile);
            } while (fromFile.hasNext());
            System.out.println(fileTree);
        } catch (IOException e) {
            System.out.println("File access error!");
            System.out.println();
        }
    }

    public static String getFileName() {
        System.out.println();
        System.out.println("Name of file to read from: ");
        String file = console.next();
        System.out.println();
        return file;
    }

    public static void testRandomTables() {
        int nextInt = 0;
        double maxHeight = 0;
        double averageLevel = 0.0;
        double height = 0.0;
        Table temp = null;
        System.out.println("  Size\t\tWorst Case\t\tExpected Case");
        for(int i = 4; i < 16; i++) {
            for(int j = 0; j < 10; j++) {
                temp = new Table();
                while (temp.getSize() < Math.pow(2, i)) {
                    Random generator = new Random();
                    nextInt = generator.nextInt();
                    KeyComparableNumber newNum = new KeyComparableNumber(nextInt);
                    temp.insert(newNum);
                }
                averageLevel += temp.getAverageLevel();
                height = temp.getHeight();
                if(height > maxHeight){
                    maxHeight = height;
                }
            }
            averageLevel = averageLevel / 10;
            int size = temp.getSize();
            System.out.printf("%6d" + "%16.2f" + "%19.2f\n", size, maxHeight, averageLevel);
        }
    }
}

interface KeyComparable {

    int keyCompareTo(KeyComparable other);

    String toStringKey();
}

class Node {
    public KeyComparable data;
    public Node left;
    public Node right;

    public Node(KeyComparable item) {
        data = item;
        left = null;
        right = null;
    }
}

class Table {
    private Node _root;

    public Table() {
        _root = null;
    }

    public void insert(KeyComparable item) {
        if (_root != null) {
            insert(_root, item);
        } else {
            _root = new Node(item);
        }
    }

    private void insert(Node myRoot, KeyComparable item) {
        int comp = item.keyCompareTo(myRoot.data);
        if (comp < 0) {
            if (myRoot.left != null) {
                insert(myRoot.left, item);
            } else {
                myRoot.left = new Node(item);
            }
        } else if (comp > 0) {
            if (myRoot.right != null) {
                insert(myRoot.right, item);
            } else {
                myRoot.right = new Node(item);
            }
        }
    }

    public void delete(KeyComparable key) {
        _root = delete(_root, key);
    }

    private Node delete(Node myRoot, KeyComparable key) {
        if (myRoot != null) {
            int comp = key.keyCompareTo(myRoot.data);
            if (comp < 0) {
                myRoot.left = delete(myRoot.left, key);
            } else if (comp > 0) {
                myRoot.right = delete(myRoot.right, key);
            } else {
                if (myRoot.left == null && myRoot.right == null) {
                    myRoot = null;
                } else if (myRoot.left == null) {
                    myRoot = myRoot.right;
                } else if (myRoot.right == null) {
                    myRoot = myRoot.left;
                } else {
                    KeyComparable rep = findMax(myRoot.left);
                    myRoot.data = rep;
                    myRoot.left = delete(myRoot.left, rep);
                }
            }
        }
        return myRoot;
    }

    private KeyComparable findMax(Node myRoot) {
        if (myRoot.right == null) {
            return myRoot.data;
        } else {
            return findMax(myRoot.right);
        }
    }

    public KeyComparable search(KeyComparable key) {
        return search(_root, key);
    }

    private KeyComparable search(Node myRoot, KeyComparable key) {
        if (myRoot == null) {
            return null;
        } else {
            int comp = key.keyCompareTo(myRoot.data);
            if (comp == 0) {
                return myRoot.data;
            } else if (comp < 0) {
                return search(myRoot.left, key);
            } else {
                return search(myRoot.right, key);
            }
        }
    }

    public int getHeight() {
        return getHeight(_root);
    }

    private int getHeight(Node myRoot) {
        if (myRoot == null) {
            return 0;
        } else {
            return 1 + Math.max(getHeight(myRoot.left), getHeight(myRoot.right));
        }
    }

    public int getSize() {
        return getSize(_root);
    }

    private int getSize(Node myRoot) {
        int nodeCount = 0;
        if (myRoot != null) {
            nodeCount++;
            nodeCount += getSize(myRoot.left);
            nodeCount += getSize(myRoot.right);
        }
        return nodeCount;
    }

    public double getAverageLevel() {
        if (_root == null) {
            return 0;
        } else {
            return getAverageLevel(_root, 1) / getSize();
        }
    }

    private double getAverageLevel(Node myRoot, int level) {
        int levels = 0;
        if (myRoot != null) {
            levels += getAverageLevel(myRoot.left, level + 1);
            for (int i = 0; i < level; i++) {
                levels++;
            }
            levels += getAverageLevel(myRoot.right, level + 1);
        } else {
            levels += 0;
        }
        return levels;
    }

    public String toString() {
        return preOrderPrint(_root);
    }

    private String preOrderPrint(Node myRoot) {
        String result = "";
        if (myRoot != null) {
            result += preOrderPrint(myRoot.left);
            result += myRoot.data + "\n";
            result += preOrderPrint(myRoot.right);
        }
        return result;
    }

    public String showTree() {
        return showTree(_root, 0);
    }

    private String showTree(Node myRoot, int level) {
        String result = "";
        if (myRoot != null) {
            result += showTree(myRoot.right, level + 1);
            for (int i = 0; i < level; i++) {
                result += "\t";
            }
            result += myRoot.data.toStringKey();
            result += showTree(myRoot.left, level + 1);
        } else {
            result += "";
        }
        return result;
    }
}

class MLBPlayer extends MLBPlayerKey {
    private String _playerName;
    private double _battingAverage;

    public MLBPlayer(int jerseyNum, String team, String name, double average) {
        super(jerseyNum, team);
        _playerName = name;
        _battingAverage = average;
    }

    public String getPlayerName() {
        return _playerName;
    }

    public double getBattingAverage() {
        return _battingAverage;
    }

    public String toString() {
        return super.toString() +
               "Player Name: " + _playerName + "\n" +
               "Batting Average: " + _battingAverage + "\n";
    }
}

class MLBPlayerKey implements KeyComparable {
    private int _jerseyNumber;
    private String _teamName;

    public MLBPlayerKey(int jerseyNum, String team) {
        _jerseyNumber = jerseyNum;
        _teamName = team;
    }

    public int getJerseyNumber() {
        return _jerseyNumber;
    }

    public String getTeamName() {
        return _teamName;
    }

    public int keyCompareTo(KeyComparable other) {
        int teamNameResult;
        int jerseyDifference;
        int equality = 2;
        if (other instanceof MLBPlayerKey) {
            MLBPlayerKey otherPlayer = (MLBPlayer) other;
            teamNameResult = _teamName.compareTo(otherPlayer._teamName);
            if (teamNameResult == 0) {
                jerseyDifference = _jerseyNumber - otherPlayer._jerseyNumber;
                if (jerseyDifference < 0) {
                    equality = -1;
                } else if (jerseyDifference > 0) {
                    equality = 1;
                } else {
                    equality = 0;
                }
            } else if (teamNameResult < 0) {
                equality = -1;
            } else {
                equality = 1;
            }
        }
        return equality;
    }

    public String toStringKey() {
        return "" + _jerseyNumber + "" + _teamName.substring(0, 3) + "\n";
    }

    public String toString() {
        return "Jersey #: " + _jerseyNumber + "\n" +
               "Team: " + _teamName + "\n";
    }
}

class KeyComparableNumber implements KeyComparable {
    private int _key;

    public KeyComparableNumber(int key) {
        _key = key;
    }

    public int getKey() {
        return _key;
    }

    public int keyCompareTo(KeyComparable other) {
        int comp = 2;
        if (other instanceof KeyComparableNumber) {
            KeyComparableNumber otherNum = (KeyComparableNumber) other;
            comp = _key - otherNum._key;
            if (comp < 0) {
                comp = -1;
            } else if (comp > 0) {
                comp = 1;
            } else {
                comp = 0;
            }
        }
        return comp;
    }

    public String toStringKey() {
        return _key + "";
    }
}