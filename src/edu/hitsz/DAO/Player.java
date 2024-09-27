package edu.hitsz.DAO;

public class Player {
    private String playerName;
    private int playId;
    private int playScore;
    private String playTime;
    private String DifficultyLevel;

    public Player(String playerName, int playId, int playScore, String playTime, String DifficultyLevel) {
        this.playerName = playerName;
        this.playId = playId;
        this.playScore = playScore;
        this.playTime = playTime;
        this.DifficultyLevel = DifficultyLevel;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayId() {
        return playId;
    }

    public void setPlayId(int playId) {
        this.playId = playId;
    }

    public int getPlayScore() {
        return playScore;
    }

    public void setPlayScore(int playScore) {
        this.playScore = playScore;
    }

    public String getPlayTime() {
        return playTime;
    }

    public void setPlayTime(String playTime) {
        this.playTime = playTime;
    }

    public void setDifficultyLevel(String Level) { this.DifficultyLevel = Level; }
    public String getDifficultyLevel(){
        return DifficultyLevel;
    }
}