package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import datamodel.Passenger;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

public class TestHTTPService {

    public static void main(String[] args) throws IOException {

        //Helps to bind the socket

        //It will accept incoming traffic only from localhost:8089/test

        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 8089);

        HttpServer server = HttpServer.create(socketAddress, 0);

        //binding process

        server.createContext("/test", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                URI requestURI = exchange.getRequestURI();

                String requestMethod = exchange.getRequestMethod();
                System.out.println(requestMethod);

                System.out.println(requestURI.toString());

                //To print something on webpage from this server

                Passenger passenger = new Passenger("test", 22, 10, true, 1 );

                ObjectMapper mapper = new ObjectMapper();

                String response = mapper.writeValueAsString(passenger);

                byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
                exchange.sendResponseHeaders(200, bytes.length);
                exchange.getResponseBody().write(bytes);

                exchange.sendResponseHeaders(200, -1);

            }
        });

        server.start();

    }
    @Test
    public void testHTTPService() throws IOException, URISyntaxException {

        //Helps to bind the socket

        //It will accept incoming traffic only from localhost:8089/test

        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 8089);

        HttpServer server = HttpServer.create(socketAddress, 0);

        //binding process

        server.createContext("/test", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                URI requestURI = exchange.getRequestURI();
                System.out.println(requestURI.toString());

            }
        });

        server.start();

        //Converting URI (identifier) to URL (location)

        new URI("http://127.0.0.1:8089/test")
                .toURL() //convert
                .openConnection() //set connection
                .connect(); // send request

        server.stop(1);

    }
}
