package com.example.server.tournament.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TournamentStatisticModel {
    public int allTournaments;
    public int active;
    public int finished;
    public int notStarted;
}
