package org.springframework.CaisseEnregistreuse.JSON;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.CaisseEnregistreuse.Service.*;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;




@Component
public class JsonParser {

	private final ObjectMapper mapper;
	private static final Logger logger = Logger.getLogger(Controller.class.getName());

	public JsonParser() {
		this.mapper = new ObjectMapper();
	}

	public List<Client> parseClients(String json) throws JsonProcessingException {
		JsonNode root = mapper.readTree(json);
		JsonNode content = root.path("content");
		List<Client> clients = new ArrayList<>();

		if (content.isArray()) {
			for (JsonNode node : content) {
				Client client = mapper.treeToValue(node, Client.class);
				JsonNode receiptsNode = node.path("receipts");

				if (receiptsNode.isArray()) {
					List<Ticket> receipts = new ArrayList<>();
					for (JsonNode receiptNode : receiptsNode) {
						Ticket receipt = mapper.treeToValue(receiptNode, Ticket.class);
						receipts.add(receipt);
					}
					client.setReceipts(receipts);
				}

				clients.add(client);
			}
		}
		return clients;
	}

	public String parseAverageAge(String json) throws JsonProcessingException {
		JsonNode root = mapper.readTree(json);
		String averageAgeText = root.path("content").asText();
		double averageAgeValue = Double.parseDouble(averageAgeText);

		DecimalFormat decimalFormat = new DecimalFormat("#");
		decimalFormat.setRoundingMode(RoundingMode.DOWN);

		return decimalFormat.format(averageAgeValue);
	}

	public List<Crepe> parseCrepes(String json) throws JsonProcessingException {
		JsonNode root = mapper.readTree(json);
		JsonNode content = root.path("content");
		List<Crepe> crepes = new ArrayList<>();

		if (content.isArray()) {
			for (JsonNode node : content) {
				Crepe crepe = mapper.treeToValue(node, Crepe.class);
				crepes.add(crepe);
			}
		}
		return crepes;
	}

	public double parseAverageAmount(String json) throws JsonProcessingException {
		JsonNode root = mapper.readTree(json);
		double sum = root.path("content").path("sum").asDouble();
		sum = Math.round(sum * 100.0) / 100.0;
		return sum;
	}
	//payment method parser
	public List<PaymentMethod> parsePaymentMethods(String json) throws JsonProcessingException {
		JsonNode root = mapper.readTree(json);
		System.out.println(root);
		JsonNode paymentMethodsNode = root.path("content");
		List<PaymentMethod> paymentMethods = new ArrayList<>();

		for (JsonNode paymentMethodNode : paymentMethodsNode) {
			PaymentMethod paymentMethod = mapper.treeToValue(paymentMethodNode, PaymentMethod.class);
			paymentMethods.add(paymentMethod);
		}

		return paymentMethods;
	}
	public String parseCA(String json) throws JsonProcessingException {
		JsonNode root = mapper.readTree(json);
		logger.info("JSON Root: " + root);
		JsonNode contentNode = root.path("content");
		JsonNode sumNode = contentNode.path("sum");
		logger.info("Content Node: " + contentNode);
		logger.info("Sum Node: " + sumNode);
		return sumNode.asText();
	}

}
