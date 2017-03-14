package ro.systematic.demo.models.helpers;

/**
 * Created by neop on 13.03.2017.
 */
public enum TicketStatus {
    OPEN("open"),CLOSED("closed"),ONHOLD("onHold"),TESTABLE("testable"),RELEASING("releasing"),INPROGRESS("inprogress");

    String ticketStatus;

    private TicketStatus(String ticketStatus){
        this.ticketStatus=ticketStatus;
    }
    public String getTicketStatus(){
        return ticketStatus;
    }
}
