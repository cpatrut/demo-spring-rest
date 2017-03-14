package ro.systematic.demo.services;

import ro.systematic.demo.models.helpers.TicketModel;

import java.util.List;


/**
 * Created by neop on 13.03.2017.
 */

public interface TicketService {

    void saveTicket(TicketModel ticketModel);

    List<TicketModel> getAllTicketsAsList();

    TicketModel getTicketById(int id);

    void updateTicket(TicketModel ticketModel);

    void deleteTicket(int id);

    void deleteAllTickets();
}
