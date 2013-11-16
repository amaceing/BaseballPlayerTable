//Anthony Mace  CSC205AB
//This program ...

import java.util.*;

public class program7 {

    public static final Scanner console = new Scanner(System.in);

    public static void main(String[] args) {
        MLBPlayer player1 = new MLBPlayer(2, "Diamondbacks", "Jose", .300);
        MLBPlayer player2 = new MLBPlayer(1, "Diamondbacks", "Jose", .300);
        MLBPlayer player3 = new MLBPlayer(2, "Diamondbacks", "Jose", .300);
        MLBPlayer player4 = new MLBPlayer(3, "Cardinals", "Jose", .300);
        KeyComparable[] players = new KeyComparable[4];
        players[0] = player1;
        players[1] = player2;
        players[2] = player3;
        players[3] = player4;
        System.out.println(player1.keyCompareTo(player2));
        System.out.println(player1.keyCompareTo(player3));
        System.out.println(player1.keyCompareTo(player4));
        for (int i = 0; i < players.length; i++) {
            System.out.println(players[i].toStringKey());
        }

    }
}

interface KeyComparable {

    int keyCompareTo(KeyComparable other);

    String toStringKey();
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
        return "Jersey #" + _jerseyNumber + "\n" +
               "Team: " + _teamName.substring(0, 3) + "\n";
    }
}
