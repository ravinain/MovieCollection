/**
 * 
 */
package com.movie.rest.client;

import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.client.ClientProperties;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sun.istack.internal.logging.Logger;

/**
 * @author cdacr
 * 
 */
public final class ImdbClient {

	/**
	 * 
	 */
	private final Client client;
	/**
	 * 
	 */
	private static final Logger LOGGER = Logger.getLogger(ImdbClient.class);
	/**
	 * 
	 */
	private static final String OMDB_URL = "http://www.omdbapi.com/";

	/**
	 * 
	 */
	private static final int CONNECT_TIMEOUT = 5000;
	/**
	 * 
	 */
	private static final int READ_TIMEOUT = 10000;

	/**
	 * 
	 */
	public ImdbClient() {
		client = ClientBuilder.newClient();
		client.property(ClientProperties.CONNECT_TIMEOUT, CONNECT_TIMEOUT);
		client.property(ClientProperties.READ_TIMEOUT, READ_TIMEOUT);
	}

	/**
	 * 
	 * @param movieTitle 
	 * @return JSONObject
	 * @throws SocketTimeoutException 
	 */
	public JSONObject getImdbInfo(final String movieTitle)
			throws SocketTimeoutException {
		WebTarget target;
		JSONParser parser;
		JSONObject json = new JSONObject();
		try {
			target = client.target(new URI(OMDB_URL));
			LOGGER.info(target.getUri().toString());
			target = target.path("");
			target = target.queryParam("t", movieTitle);
			target = target.queryParam("r", "json");
			final String response = target.request().get(String.class);
			parser = new JSONParser();
			json = (JSONObject) parser.parse(response);
			LOGGER.info(json.toJSONString());
		} catch (final URISyntaxException | ParseException e) {
			LOGGER.severe(e.getMessage());
		}

		return json;
	}

}