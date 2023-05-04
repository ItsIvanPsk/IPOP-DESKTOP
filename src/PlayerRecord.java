

public class PlayerRecord {
    private int ranking_id, correctTotems, wrongTotems, points, cycle_idCycle;
    private String aliasPlayer, timeStart, timeEnd;
    private Boolean isVisible;

    public PlayerRecord(
        int rankingId,
        int correctTotems,
        int wrongTotems,
        int points,
        int cycle_idCycle,
        String aliasPlayer,
        String timeStart,
        String timeEnd,
        Boolean isVisible
    ) {
        this.ranking_id = rankingId;
        this.correctTotems = correctTotems;
        this.wrongTotems = wrongTotems;
        this.points = points;
        this.cycle_idCycle = cycle_idCycle;
        this.aliasPlayer = aliasPlayer;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.isVisible = isVisible;
    }
    
    public Boolean getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Boolean isVisible) {
        this.isVisible = isVisible;
    }
    public int getRankingId() {
        return ranking_id;
    }
    public void setRankingId(int idAlias) {
        this.ranking_id = idAlias;
    }
    public int getCorrectTotems() {
        return correctTotems;
    }
    public void setCorrectTotems(int correctTotems) {
        this.correctTotems = correctTotems;
    }
    public int getWrongTotems() {
        return wrongTotems;
    }
    public void setWrongTotems(int wrongTotems) {
        this.wrongTotems = wrongTotems;
    }
    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }
    public int getCycle_idCycle() {
        return cycle_idCycle;
    }
    public void setCycle_idCycle(int cycle_idCycle) {
        this.cycle_idCycle = cycle_idCycle;
    }
    public String getAliasPlayer() {
        return aliasPlayer;
    }
    public void setAliasPlayer(String aliasPlayer) {
        this.aliasPlayer = aliasPlayer;
    }
    public String getTimeStart() {
        return timeStart;
    }
    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }
    public String getTimeEnd() {
        return timeEnd;
    }
    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }
}
