//Anthony Mace  CSC205AB
//This program ...

import java.util.*;

public class program7 {

    public static final Scanner console = new Scanner(System.in);

    public static void main(String[] args) {
        Table tree = new Table();
        MLBPlayer player1 = new MLBPlayer(2, "Diamondbacks", "Jose", .300);
        MLBPlayer player2 = new MLBPlayer(1, "Diamondbacks", "Jose", .300);
        MLBPlayer player3 = new MLBPlayer(2, "Diamondbacks", "Jose", .300);
        MLBPlayer player4 = new MLBPlayer(3, "Cardinals", "Jose", .300);
        KeyComparable[] players = new KeyComparable[4];
        players[0] = player1;
        players[1] = player2;
        players[2] = player3;
        players[3] = player4;
        tree.insert(player1);
        tree.insert(player3);
        tree.insert(player2);
        System.out.println(tree);
        tree.insert(players[3]);
        System.out.println(tree);
        System.out.println(tree.search(player1));
        System.out.println();
        System.out.println(tree.search(players[3]));
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

    public static int getMenuChoice() {
        printMenu();
        System.out.print("Enter menu option: ");
        int choice = console.nextInt();
        while (choice == 3 || choice > 4) {
            System.out.println();
            System.out.println("You did not choose a valid operation!");
            System.out.print("Please reenter a valid operation: ");
            choice = console.nextInt();
        }
        return choice;
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

    public String toString() {
        return preOrderPrint(_root);
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

    private String preOrderPrint(Node myRoot) {
        String result = "";
        if (myRoot != null) {
            result += preOrderPrint(myRoot.left);
            result += myRoot.data + "\n";
            result += preOrderPrint(myRoot.right);
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
        return super.toString() + "\n" +
               "Player Name: " + _playerName + "\n" +
               "Batting Average: " + _battingAverage;
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
        if (other instanceof MLBPlayer) {
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
        return "#" + _jerseyNumber + ", " + _teamName.substring(0, 3);
    }

    public String toString() {
        return "Jersey #: " + _jerseyNumber + "\n" +
               "Team: " + _teamName;
    }
}
