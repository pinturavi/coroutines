package com.pintu.coroutines;

import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


class Exchange {
    private Map<String, Double> rates;
   private String base;
   private String date;

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

public class Webservice {
    private static final String BASE = "USD";
    public static void main(String[] args) {
        try {
            final Instant start = Instant.now() ;
            List<String> symbols = Arrays.asList("INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD",
                    "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD",
                    "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD",
                    "SGD", "JPY", "INR", "CAD", "SGD", "JPY","INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD",
                    "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD",
                    "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD",
                    "SGD", "JPY", "INR", "CAD", "SGD", "JPY","INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD",
                    "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD",
                    "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD", "SGD", "JPY", "INR", "CAD",
                    "SGD", "JPY", "INR", "CAD", "SGD", "JPY");
            symbols.parallelStream().forEach(Webservice::printExchangeRates);
            System.out.println(Duration.between(start, Instant.now()).toMillis());
            Thread.sleep(500);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printExchangeRates(String symbol) {
        final String data;
        try {
            data = getExchangeRates(symbol);
            Gson gson = new Gson();
            Exchange exchange = gson.fromJson(data, Exchange.class);
            System.out.println(symbol +"  " + exchange.getRates().get(symbol));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @NotNull
    private static String getExchangeRates(final String symbol) throws IOException {
        final URL url = new URL("https://api.exchangeratesapi.io/latest?base=" + BASE + "&symbols=" + symbol);
        final BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        return reader.lines().findFirst().get();
    }
}
