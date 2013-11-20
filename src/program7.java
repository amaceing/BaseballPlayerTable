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
