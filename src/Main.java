import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String apiKey = "67a75202992d7017f578a51e";
        String baseUrl = "https://v6.exchangerate-api.com/v6/";
        String endpoint = "latest/USD";
        String url = baseUrl + apiKey + "/" + endpoint;

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            Map<String, Object> parsedResponse = new com.google.gson.Gson().fromJson(response.body(), Map.class);
            Map<String, Double> rates = (Map<String, Double>) parsedResponse.get("conversion_rates");

            int option;
            do {
                System.out.println("***************************************");
                System.out.println("   Sea bienvenido/a al Conversor de Moneda =]");
                System.out.println("***************************************");
                System.out.println("1) Dólar >> Peso argentino");
                System.out.println("2) Peso argentino >> Dólar");
                System.out.println("3) Dólar >> Real brasileño");
                System.out.println("4) Real brasileño >> Dólar");
                System.out.println("5) Dólar >> Peso colombiano");
                System.out.println("6) Peso colombiano >> Dólar");
                System.out.println("7) Salir");
                System.out.println("Elija una opción válida:");
                System.out.println("***************************************");

                option = scanner.nextInt();

                if (option >= 1 && option <= 6) {
                    System.out.print("Ingrese la cantidad que desea convertir: ");
                    double amount = scanner.nextDouble();

                    double result = 0.0;
                    switch (option) {
                        case 1: // USD to ARS
                            result = amount * rates.get("ARS");
                            System.out.printf("%.2f USD son %.2f ARS%n", amount, result);
                            break;
                        case 2: // ARS to USD
                            result = amount / rates.get("ARS");
                            System.out.printf("%.2f ARS son %.2f USD%n", amount, result);
                            break;
                        case 3: // USD to BRL
                            result = amount * rates.get("BRL");
                            System.out.printf("%.2f USD son %.2f BRL%n", amount, result);
                            break;
                        case 4: // BRL to USD
                            result = amount / rates.get("BRL");
                            System.out.printf("%.2f BRL son %.2f USD%n", amount, result);
                            break;
                        case 5: // USD to COP
                            result = amount * rates.get("COP");
                            System.out.printf("%.2f USD son %.2f COP%n", amount, result);
                            break;
                        case 6: // COP to USD
                            result = amount / rates.get("COP");
                            System.out.printf("%.2f COP son %.2f USD%n", amount, result);
                            break;
                        default:
                            System.out.println("Opción no válida.");
                    }
                } else if (option != 7) {
                    System.out.println("Por favor, seleccione una opción válida.");
                }

            } while (option != 7);

            System.out.println("Gracias por usar el Conversor de Moneda. ¡Hasta luego!");

        } catch (Exception e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }
    }
}