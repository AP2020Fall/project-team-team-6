package model;

import java.time.LocalDateTime;

public class RequestForPlaying {
    private Player player;
    private Player invitedPlayer;
    private RiskGame riskGame;
    private boolean isAvailable;
    private LocalDateTime inviteDate;

    public RequestForPlaying(Player player, Player invitedPlayer, RiskGame riskGame) {
        this.player = player;
        this.invitedPlayer = invitedPlayer;
        this.riskGame = riskGame;
        this.inviteDate = LocalDateTime.now();
        this.isAvailable = true;
    }

    public Player getPlayer() {
        return player;
    }

    public RiskGame getRiskGame() {
        return riskGame;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public LocalDateTime getInviteDate() {
        return inviteDate;
    }

    public void setRiskGame(RiskGame riskGame) {
        this.riskGame = riskGame;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Player getInvitedPlayer() {
        return invitedPlayer;
    }

}
