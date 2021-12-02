package com.example.evm;

import com.example.evm.DB_Manager;

public class Voter {
    String voterID;
    String tehsil;
    Party parties;

    public void toVote(){
        DB_Manager dbManager = new DB_Manager();
        //dbManager.castVote(this.voterID);
    }
}
