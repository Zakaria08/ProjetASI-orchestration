package ch.heg.ig.BachEtBuck.services;

import ch.heg.ig.BachEtBuck.business.Ticket;
import ch.heg.ig.BachEtBuck.business.Tickets;
import ch.heg.ig.BachEtBuck.persistance.TicketRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Victor Feller
 */
@RestController
public class TicketController {

	private final TicketRepository ticketRepository;

	public TicketController(TicketRepository ticketRepository) {
		this.ticketRepository = ticketRepository;
	}

	@GetMapping("ticket/{ticketId}")
	public Ticket findTicket(@PathVariable(name = "ticketId", required = false) Integer ticketId) {
		return ticketId == null ? new Ticket() : this.ticketRepository.findById(ticketId);
	}

	@GetMapping("ticket")
	public Ticket findByPurchaseDate(
			@PathVariable(name = "purchaseDate", required = false) @RequestParam String purchaseDate) {
		return purchaseDate == null ? new Ticket() : this.ticketRepository.findByPurchaseDate(purchaseDate);
	}

	@GetMapping("/tickets/all")
	public List<Ticket> showTickets() {
		Tickets tickets = new Tickets();
		tickets.getTicketList().addAll(this.ticketRepository.findAll());
		return tickets.getTicketList();
	}

}
