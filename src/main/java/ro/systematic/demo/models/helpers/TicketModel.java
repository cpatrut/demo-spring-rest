package ro.systematic.demo.models.helpers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by neop on 13.03.2017.
 */
public class TicketModel {

    private Integer id;
    private String ticketStatus =TicketStatus.OPEN.getTicketStatus();
    private String description;
    private Date creationTime=new Date();
    private int estimates;
    private String creator;


    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreationTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(creationTime);
    }
    public Date getCreationTimeDate() {
       return creationTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public int getEstimates() {
        return estimates;
    }

    public void setEstimates(int estimates) {
        this.estimates = estimates;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
