package net.corda.samples.supplychain.states;

import net.corda.samples.supplychain.contracts.OrderMessageStateContract;
import net.corda.core.contracts.BelongsToContract;
import net.corda.core.contracts.ContractState;
import net.corda.core.identity.AbstractParty;
import net.corda.core.identity.AnonymousParty;

import java.util.ArrayList;
import java.util.List;

// *********
// * State *
// *********
@BelongsToContract(OrderMessageStateContract.class)
public class OrderMessageState implements ContractState {

    private String task;
    private AnonymousParty from;
    private AnonymousParty to;
    private List<AbstractParty> participants;

    public OrderMessageState(String task, AnonymousParty from, AnonymousParty to) {
        this.task = task;
        this.from = from;
        this.to = to;
        this.participants = new ArrayList<AbstractParty>();
        participants.add(from);
        participants.add(to);
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public AnonymousParty getFrom() {
        return from;
    }

    public void setFrom(AnonymousParty from) {
        this.from = from;
    }

    public AnonymousParty getTo() {
        return to;
    }

    public void setTo(AnonymousParty to) {
        this.to = to;
    }

    @Override
    public List<AbstractParty> getParticipants() {
        return this.participants;
    }
}