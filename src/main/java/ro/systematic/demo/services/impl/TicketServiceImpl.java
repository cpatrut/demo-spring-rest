package ro.systematic.demo.services.impl;

import org.springframework.stereotype.Service;
import ro.systematic.demo.models.helpers.TicketModel;
import ro.systematic.demo.services.TicketService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by neop on 14.03.2017.
 */

@Service
public class TicketServiceImpl implements TicketService{

    private static Map<Integer,TicketModel> inMemoryTicketsMap =new HashMap();

    @Override
    public void saveTicket(TicketModel ticketModel) {
        inMemoryTicketsMap.put(ticketModel.getId(),ticketModel);
    }

    @Override
    public List<TicketModel> getAllTicketsAsList() {
        List<TicketModel> ticketModelList = new ArrayList<>();
        for (Integer key: inMemoryTicketsMap.keySet()) {
            ticketModelList.add(inMemoryTicketsMap.get(key));
        }
        return ticketModelList;
    }

    @Override
    public TicketModel getTicketById(int id) {
        return inMemoryTicketsMap.get(new Integer(id));
    }

    @Override
    public void updateTicket(TicketModel ticketModel) {
        inMemoryTicketsMap.put(ticketModel.getId(),ticketModel);
    }

    @Override
    public void deleteTicket(int id) {
        inMemoryTicketsMap.remove(id);
    }

    @Override
    public void deleteAllTickets() {
        inMemoryTicketsMap.clear();
    }

}
