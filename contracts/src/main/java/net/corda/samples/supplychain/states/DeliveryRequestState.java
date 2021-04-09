package net.corda.samples.supplychain.states;

import net.corda.samples.supplychain.contracts.DeliveryRequestStateContract;
import net.corda.core.contracts.BelongsToContract;
import net.corda.core.contracts.ContractState;
import net.corda.core.identity.AbstractParty;
import net.corda.core.identity.AnonymousParty;
import net.corda.core.identity.Party;

import java.util.ArrayList;
import java.util.List;

// *********
// * State *
// *********
@BelongsToContract(DeliveryRequestStateContract.class)
public class DeliveryRequestState implements ContractState {

    private AnonymousParty pickUpFrom;
    private String deliverTo;
    private Party courier;
    private String parcel;
    private List<AbstractParty> participants;

    public DeliveryRequestState(AnonymousParty pickUpFrom, String deliverTo, Party courier, String parcel) {
        this.pickUpFrom = pickUpFrom;
        this.deliverTo = deliverTo;
        this.courier = courier;
        this.parcel = parcel;
        this.participants = new ArrayList<AbstractParty>();
        participants.add(pickUpFrom);
    }

    public AnonymousParty getPickUpFrom() {
        return pickUpFrom;
    }

    public void setPickUpFrom(AnonymousParty pickUpFrom) {
        this.pickUpFrom = pickUpFrom;
    }

    public String getDeliverTo() {
        return deliverTo;
    }

    public void setDeliverTo(String deliverTo) {
        this.deliverTo = deliverTo;
    }

    public Party getCourier() {
        return courier;
    }

    public void setCourier(Party courier) {
        this.courier = courier;
    }

    public String getParcel() {
        return parcel;
    }

    public void setParcel(String parcel) {
        this.parcel = parcel;
    }

    @Override
    public List<AbstractParty> getParticipants() {
        return this.participants;
    }
}