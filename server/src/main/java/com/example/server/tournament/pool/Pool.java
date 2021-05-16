package com.example.server.tournament.pool;

import com.example.server.tournament.exception.PoolIsEmpty;
import com.example.server.tournament.model.dto.UserDtoForTournament;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class Pool {
    private final BlockingQueue<UserDtoForTournament> invites = new LinkedBlockingQueue<>();

    public UserDtoForTournament getInvite() {
        if (invites.size() > 0){
            return invites.poll();
        }
        throw new PoolIsEmpty("Pool is empty");
    }

    public void addInvite(UserDtoForTournament userDtoForTournament) throws InterruptedException {
        invites.put(userDtoForTournament);
    }

    public List<UserDtoForTournament> getAllInvites(){
        if (invites.size() > 0){
            ArrayList<UserDtoForTournament> allInvites = new ArrayList<>(invites);
            invites.clear();
            return allInvites;
        }
        throw new PoolIsEmpty("Pool is empty");
    }
}
