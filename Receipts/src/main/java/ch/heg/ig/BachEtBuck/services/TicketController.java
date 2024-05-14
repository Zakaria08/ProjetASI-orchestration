package ch.heg.ig.BachEtBuck.services;

import ch.heg.ig.BachEtBuck.business.Ticket;
import ch.heg.ig.BachEtBuck.persistance.TicketRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
	public ResponseEntity<?> findTicket(@PathVariable(name = "ticketId", required = false) Integer ticketId) {
		try {
			Ticket ticket = this.ticketRepository.findById(ticketId);
			if (ticket == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Aucun ticket trouvé pour l'id : " + ticketId);
			}
			return ResponseEntity.ok(ticket);
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Erreur lors de la récupération du ticket : " + e.getMessage());
		}
	}

	@GetMapping("ticket")
	public ResponseEntity<?> findByPurchaseDate(@RequestParam String purchaseDate) {
		try {
			List<Ticket> tickets = this.ticketRepository.findByPurchaseDate(purchaseDate);
			if (tickets.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Aucun ticket trouvé pour la date donnée : " + purchaseDate);
			}
			return ResponseEntity.ok(tickets);
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Erreur lors de la récupération du ticket par date : " + e.getMessage());
		}
	}

	@GetMapping("/tickets/all")
	public ResponseEntity<?> showTickets() {
		try {
			List<Ticket> tickets = this.ticketRepository.findAll();
			if (tickets.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Aucun ticket trouvé");
			}
			return ResponseEntity.ok(tickets);
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Erreur lors de la récupération des tickets : " + e.getMessage());
		}
	}

	@GetMapping("/tickets/averageAmount")
	public ResponseEntity<?> showAverageAmount() {
		try {
			BigDecimal averageAmount = this.ticketRepository.findAverageAmount();
			if (averageAmount == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("La moyenne du montant des tickets n'a pas été trouvée");
			}
			//Utilisation de Gson pour retourner la réponse au format JSON
			Gson gson = new Gson();
			//Crée un objet Map pour contenir la somme
			Map<String, BigDecimal> response = new HashMap<>();
			response.put("averageAmount", averageAmount);
			//Convertit en JSON
			String jsonData = gson.toJson(response);
			return ResponseEntity.ok(jsonData);
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Erreur lors de la récupération de la moyenne de tous les tickets : " + e.getMessage());
		}
	}

	@GetMapping("/tickets/mostPurchasedItem")
	public ResponseEntity<?> showMostPurchasedItem() {
		try {
			List<String> mostPurchasedItem = this.ticketRepository.findMostPurchasedItem();
			if (mostPurchasedItem == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("L'item le plus acheté n'a pas été trouvé");
			}
			//Utilisation de Gson pour retourner la réponse au format JSON
			Gson gson = new Gson();
			//Crée un objet Map pour contenir la somme
			List<Map<String, String>> responseList = new ArrayList<>();

			for (String result : mostPurchasedItem) {
				Map<String, String> response = new LinkedHashMap<>();
				//Split de la string
				response.put("purchasedItem", result.split(",")[0]);
				response.put("quantity", result.split(",")[1]);
				responseList.add(response);
			}
			//Convertit en JSON
			String jsonData = gson.toJson(responseList);
			return ResponseEntity.ok(jsonData);
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Erreur lors de la récupération de l'item le plus acheté : " + e.getMessage());
		}
	}

	@GetMapping("/tickets/mostUsedPaymentMethod")
	public ResponseEntity<?> showMostUsedPaymentMethod() {
		try {
			List<String> mostUsedPaymentMethod = this.ticketRepository.findMostUsedPaymentMethod();
			if (mostUsedPaymentMethod == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("La moyen de paiement le plus utilisé n'a pas été trouvé");
			}
			//Utilisation de Gson pour retourner la réponse au format JSON
			Gson gson = new Gson();
			//Crée un objet Map pour contenir la somme
			List<Map<String, String>> responseList = new ArrayList<>();

			for (String result : mostUsedPaymentMethod) {
				Map<String, String> response = new LinkedHashMap<>();
				//Split de la string
				response.put("paymentMethod", result.split(",")[0]);
				response.put("quantity", result.split(",")[1]);
				responseList.add(response);
			}
			//Convertit en JSON
			String jsonData = gson.toJson(responseList);
			return ResponseEntity.ok(jsonData);
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Erreur lors de la récupération du moyen de paiement le plus utilisé : " + e.getMessage());
		}
	}

	@GetMapping("/tickets/sum/{year}/{month}")
	public ResponseEntity<?> showSumByYearMonth(@PathVariable(name = "year", required = false) String year, @PathVariable(name = "month", required = false) String month) {
		try {
			if (Integer.parseInt(month) < 1 || Integer.parseInt(month) > 12)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Le mois entré {" + month + "} n'est pas valide.");

			BigDecimal sumByMonth = this.ticketRepository.findSumByYearMonth(year, month);
			if (sumByMonth == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("La somme de tous les tickets pour le mois de " + month + " n'a pas été trouvée.");
			}
			//Utilisation de Gson pour retourner la réponse au format JSON
			Gson gson = new Gson();
			//Crée un objet Map pour contenir la somme
			Map<String, BigDecimal> response = new HashMap<>();
			response.put("sum", sumByMonth);
			//Convertit en JSON
			String jsonData = gson.toJson(response);
			return ResponseEntity.ok(jsonData);
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Erreur lors de la récupération de la somme par mois : " + e.getMessage());
		}
	}

	@GetMapping("tickets/sum/{year}")
	public ResponseEntity<?> showSumByYear(@PathVariable(name = "year", required = false) String year) {
		try {
			BigDecimal sumByYear;
			if(String.valueOf(year).length() > 2) {
				sumByYear = this.ticketRepository.findSumByYear(year);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("La somme de tous les tickets pour l'année " + year + " n'a pas été trouvée.");
			}

			if (sumByYear == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("La somme de tous les tickets pour l'année " + year + " n'a pas été trouvée.");
			}
			//Utilisation de Gson pour retourner la réponse au format JSON
			Gson gson = new Gson();
			//Crée un objet Map pour contenir la somme
			Map<String, BigDecimal> response = new HashMap<>();
			response.put("sum", sumByYear);
			//Convertit en JSON
			String jsonData = gson.toJson(response);
			return ResponseEntity.ok(jsonData);
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Erreur lors de la récupération de la somme par année : " + e.getMessage());
		}
	}

}
