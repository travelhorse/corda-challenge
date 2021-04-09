package net.corda.samples.supplychain.states;

import net.corda.samples.supplychain.contracts.ParcelStateContract;
import net.corda.core.contracts.BelongsToContract;
import net.corda.core.contracts.ContractState;
import net.corda.core.identity.AbstractParty;
import net.corda.core.identity.AnonymousParty;

import java.util.ArrayList;
import java.util.List;

// *********
// * State *
// *********
@BelongsToContract(ParcelStateContract.class)
public class ParcelState implements ContractState {

    private AnonymousParty pickUpFrom;
    private AnonymousParty deliverTo;
    private String parcel;
    private String status;
    private AbstractParty courier;
    private List<AbstractParty> participants;


    public ParcelState(AnonymousParty pickUpFrom, AnonymousParty deliverTo, String parcel, String status, AbstractParty courier) {
        this.pickUpFrom = pickUpFrom;
        this.deliverTo = deliverTo;
        this.parcel = parcel;
        this.status = status;
        this.courier = courier;
        this.participants = new ArrayList<AbstractParty>();
        participants.add(pickUpFrom);
        participants.add(deliverTo);
        participants.add(courier);
    };

    public AnonymousParty getPickUpFrom() {
        return pickUpFrom;
    }

    public void setPickUpFrom(AnonymousParty pickUpFrom) {
        this.pickUpFrom = pickUpFrom;
    }

    public AnonymousParty getDeliverTo() {
        return deliverTo;
    }

    public void setDeliverTo(AnonymousParty deliverTo) {
        this.deliverTo = deliverTo;
    }

    public String getParcel() {
        return parcel;
    };

    public void setParcel(String parcel) {
        this.parcel = parcel;
    };

    public String getStatus() {
        return status;
    };

    public void setStatus(String status) {
        this.status = status;
    };

    public AbstractParty getCourier() {
        return courier;
    }

    public void setCourier(AnonymousParty courier) {
        this.courier = courier;
    }

    @Override
    public List<AbstractParty> getParticipants() {
        return this.participants;
    }
}