package ro.systematic.demo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ro.systematic.demo.models.helpers.TicketModel;
import ro.systematic.demo.services.TicketService;

import java.util.List;

/**
 * Created by neop on 14.03.2017.
 */
@RestController
@RequestMapping("/tickets/")
public class TicketsController {

    private static final Logger logger = LoggerFactory.getLogger(TicketsController.class);

    @Autowired
    TicketService ticketService;


    /*
        Method to retrieve all users
     */
    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<TicketModel>> listAllUsers() {

        List<TicketModel> tickets = ticketService.getAllTicketsAsList();

        if(tickets.isEmpty()){
            return new ResponseEntity<List<TicketModel>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<TicketModel>>(tickets, HttpStatus.OK);
    }

    /*
        Method to save an user
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResponseEntity<Void> saveTicket(@RequestBody TicketModel ticketModel,UriComponentsBuilder ucBuilder){

        logger.info("save method called");
        ticketService.saveTicket(ticketModel);

        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setLocation(ucBuilder.path("/ticket/{id}").buildAndExpand(ticketModel.getId()).toUri());
        return new ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED);
    }

    /*
        Method to retrieve a ticket
     */

    @RequestMapping(value = "/tickets/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TicketModel> getTicket(@PathVariable("id") int id){
        logger.info("fetching ticket with id" + id);
        TicketModel ticketModel=ticketService.getTicketById(id);

        if(ticketModel == null){
            logger.warn("ticket with id " + id + " not found");
            return new ResponseEntity<TicketModel>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<TicketModel>(ticketModel,HttpStatus.OK);
    }

    /*
            Updating a ticket
     */

    @RequestMapping(value = "/ticket/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TicketModel> updateTicket(@PathVariable("id") int id, @RequestBody TicketModel ticketModel) {
        logger.info("Updating ticket " + id);

        TicketModel currentTicket = ticketService.getTicketById(id);

        if (currentTicket==null) {
            logger.warn("Ticket with id " + id + " not found");
            return new ResponseEntity<TicketModel>(HttpStatus.NOT_FOUND);
        }

        currentTicket.setCreationTime(ticketModel.getCreationTimeDate());
        currentTicket.setCreator(ticketModel.getCreator());
        currentTicket.setDescription(ticketModel.getDescription());
        currentTicket.setEstimates(ticketModel.getEstimates());
        currentTicket.setTicketStatus(ticketModel.getTicketStatus());

        ticketService.updateTicket(currentTicket);
        return new ResponseEntity<TicketModel>(currentTicket, HttpStatus.OK);
    }

    /*
            Delete a ticket
     */
    @RequestMapping(value="/ticket/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<TicketModel> deleteTicket(@PathVariable ("id") int id){
        logger.info("fetching and deleting ticket with id " + id);

        TicketModel ticketModel = ticketService.getTicketById(id);
        if(ticketModel == null) {
            logger.warn("unable to delete, ticket with id "+ id + " not found");
            return new ResponseEntity<TicketModel>(HttpStatus.NOT_FOUND);
        }

        ticketService.deleteTicket(id);
        return new ResponseEntity<TicketModel>(HttpStatus.NO_CONTENT);
    }

    /*
        Delete all tickets
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<TicketModel> deleteAllTickets(){
        logger.warn("deleting all users");

        ticketService.deleteAllTickets();
        return new ResponseEntity<TicketModel>(HttpStatus.NO_CONTENT);
    }

}
