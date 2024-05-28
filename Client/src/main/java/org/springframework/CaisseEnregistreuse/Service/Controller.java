package org.springframework.CaisseEnregistreuse.Service;

import org.springframework.CaisseEnregistreuse.JSON.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Year;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@org.springframework.stereotype.Controller
public class Controller {

	private static final Logger logger = Logger.getLogger(Controller.class.getName());
	private static final String IPADDRESS = "http://host.docker.internal:5000";

	private final JsonParser jsonParser;
	private final HttpClient httpClient;

	@Autowired
	public Controller(JsonParser jsonParser) {
		this.jsonParser = jsonParser;
		this.httpClient = HttpClient.newHttpClient();
	}

	@GetMapping("/")
	public String homePage(Model model) {
		// Age moyen
		String averageAgeApiUrl = IPADDRESS + "/average_age";
		HttpRequest averageAgeRequest = HttpRequest.newBuilder()
				.uri(URI.create(averageAgeApiUrl))
				.build();

		HttpResponse<String> averageAgeResponse;
		String averageAge = null;
		try {
			averageAgeResponse = httpClient.send(averageAgeRequest, HttpResponse.BodyHandlers.ofString());
			String averageAgeJsonData = averageAgeResponse.body();
			averageAge = jsonParser.parseAverageAge(averageAgeJsonData);
			averageAge = averageAge + " ans";
		} catch (IOException | InterruptedException e) {
			logger.log(Level.SEVERE, "Error while fetching average age", e);
			averageAge = "N/A"; // Valeur par défaut en cas d'erreur
		}

		// Ajout de l'âge moyen au modèle
		model.addAttribute("averageAge", averageAge);

		// les crêpes les plus vendues
		String crepeApiUrl = IPADDRESS + "/best_item";
		HttpRequest crepeRequest = HttpRequest.newBuilder()
				.uri(URI.create(crepeApiUrl))
				.build();

		HttpResponse<String> crepeResponse;
		List<Crepe> topCrepes = null;
		try {
			crepeResponse = httpClient.send(crepeRequest, HttpResponse.BodyHandlers.ofString());
			String jsonData = crepeResponse.body();
			topCrepes = jsonParser.parseCrepes(jsonData);
		} catch (IOException | InterruptedException e) {
			logger.log(Level.SEVERE, "Erreur survenue en voulant afficher le top crepes", e);
			return "error";
		}

		model.addAttribute("topCrepes", topCrepes);

		// Panier moyen d'une crêpe
		String averageCostApiUrl = IPADDRESS + "/average_amount";
		HttpRequest averageCostRequest = HttpRequest.newBuilder()
				.uri(URI.create(averageCostApiUrl))
				.build();

		HttpResponse<String> averageCostResponse;
		double averageAmount = 0.0;
		try {
			averageCostResponse = httpClient.send(averageCostRequest, HttpResponse.BodyHandlers.ofString());
			String averageCostJsonData = averageCostResponse.body();
			logger.info("Average cost JSON data: " + averageCostJsonData); // Ajoutez cette ligne pour vérifier le JSON

			averageAmount = jsonParser.parseAverageAmount(averageCostJsonData);
		} catch (IOException | InterruptedException e) {
			logger.log(Level.SEVERE, "Error while fetching average cost", e);
			averageAmount = 0.0;
		}

		// Ajout du panier moyen au modèle
		model.addAttribute("averageCost", averageAmount);

		// Méthodes de paiement
		String paymentMethodsApiUrl = IPADDRESS + "/payment_method";
		HttpRequest paymentMethodsRequest = HttpRequest.newBuilder()
				.uri(URI.create(paymentMethodsApiUrl))
				.build();

		HttpResponse<String> paymentMethodsResponse;
		List<PaymentMethod> paymentMethods = null;
		try {
			paymentMethodsResponse = httpClient.send(paymentMethodsRequest, HttpResponse.BodyHandlers.ofString());
			String jsonData = paymentMethodsResponse.body();

			// Désérialiser le JSON en liste de PaymentMethod
			paymentMethods = jsonParser.parsePaymentMethods(jsonData);
			logger.info("Successfully parsed payment methods from API response.");
		} catch (IOException | InterruptedException e) {
			logger.log(Level.SEVERE, "Error while fetching and parsing payment methods", e);
			return "error";
		}

		// Trouver la méthode de paiement la plus utilisée
		PaymentMethod mostUsedPaymentMethod = paymentMethods.stream()
				.max((pm1, pm2) -> Double.compare(pm1.getQuantity(), pm2.getQuantity()))
				.orElse(null);

		// Ajout de la méthode de paiement la plus utilisée au modèle
		model.addAttribute("mostUsedPaymentMethod", mostUsedPaymentMethod);

		// CA de l'année
		String year = String.valueOf(Year.now().getValue());
		String caApiUrl = IPADDRESS + "/sum/" + year;
		HttpRequest caRequest = HttpRequest.newBuilder()
				.uri(URI.create(caApiUrl))
				.build();

		HttpResponse<String> caResponse;
		String caYTD = null;
		try {
			caResponse = httpClient.send(caRequest, HttpResponse.BodyHandlers.ofString());
			String jsonData = caResponse.body();
			logger.info("CA API Response: " + jsonData); // Ajouter cette ligne pour vérifier la réponse JSON

			// Extraire le CA de l'année de la réponse JSON
			caYTD = jsonParser.parseCA(jsonData);
			logger.info("Parsed CA_YTD: " + caYTD); // Ajouter cette ligne pour vérifier la valeur extraite
		} catch (IOException | InterruptedException e) {
			logger.log(Level.SEVERE, "Error while fetching CA YTD", e);
			caYTD = "N/A"; // Valeur par défaut en cas d'erreur
		}

		// Ajout du CA de l'année au modèle
		model.addAttribute("CA_YTD", caYTD);

		return "fragments/index";
	}

	@GetMapping("/tables")
	public String tablesPage(Model model) {
		String apiUrl = IPADDRESS + "/all_clients";
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(apiUrl))
				.build();

		HttpResponse<String> response;
		List<Client> clients = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			String jsonData = response.body();

			// Désérialiser le JSON en liste de Clients
			clients = jsonParser.parseClients(jsonData);
			logger.info("Successfully parsed clients from API response.");
		} catch (IOException | InterruptedException e) {
			logger.log(Level.SEVERE, "Error while fetching and parsing clients", e);
			return "error";
		}

		// Ajout de la liste des clients au modèle
		model.addAttribute("clients", clients);
		return "fragments/tables";
	}

	@GetMapping("/tickets")
	public String ticketsPage(Model model) {
		String apiUrl = IPADDRESS + "/all_receipts_per_client";
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(apiUrl))
				.build();

		HttpResponse<String> response;
		List<Client> clients = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			String jsonData = response.body();

			// Désérialiser le JSON en liste de Clients avec tickets
			clients = jsonParser.parseClients(jsonData);
			logger.info("Successfully parsed clients with receipts from API response.");
		} catch (IOException | InterruptedException e) {
			logger.log(Level.SEVERE, "Error while fetching and parsing clients with receipts", e);
			return "error";
		}

		// Ajout de la liste des clients avec tickets au modèle
		model.addAttribute("clients", clients);
		return "fragments/tickets";
	}

	@GetMapping("/averageAge")
	public String averageAgePage(Model model) {
		String apiUrl = IPADDRESS + "/average_age";
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(apiUrl))
				.build();

		HttpResponse<String> response;
		String averageAge = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			String jsonData = response.body();

			// Extraire l'âge moyen de la réponse JSON
			averageAge = jsonParser.parseAverageAge(jsonData);
			logger.info("Successfully fetched average age from API response.");
		} catch (IOException | InterruptedException e) {
			logger.log(Level.SEVERE, "Error while fetching average age", e);
			return "error";
		}

		// Ajout de l'âge moyen au modèle
		model.addAttribute("averageAge", averageAge);
		return "fragments/averageAge";
	}
}