import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.function.Function;

public class BythephoneApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Locale locale = Locale.forLanguageTag("es-CO");
        NumberFormat cop = NumberFormat.getCurrencyInstance(locale);
        cop.setMaximumFractionDigits(0);
        DateTimeFormatter dateFormat =
                DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(locale);
        DateTimeFormatter dateTimeFormat =
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(locale);

        String[] brands = {
            "Samsung", "Apple", "Xiaomi", "Motorola",
            "Samsung", "Xiaomi", "Motorola", "Apple"
        };
        String[] names = {
            "Galaxy A55 5G", "iPhone 13", "Redmi Note 13 Pro 5G", "Edge 50 Fusion",
            "Galaxy A35 5G", "Redmi Note 13", "Moto G84 5G", "iPhone 15"
        };
        long[] prices = {
            1_899_900, 2_399_900, 1_499_900, 1_399_900,
            1_199_900, 799_900, 899_900, 3_599_900
        };
        String[] specs = {
            "Android · 8 GB RAM · 256 GB · 5G",
            "iOS · 4 GB RAM · 128 GB · 5G",
            "Android · 8 GB RAM · 256 GB · 5G",
            "Android · 8 GB RAM · 256 GB · 5G",
            "Android · 8 GB RAM · 128 GB · 5G",
            "Android · 8 GB RAM · 256 GB",
            "Android · 8 GB RAM · 256 GB · 5G",
            "iOS · 6 GB RAM · 128 GB · 5G"
        };
        String[] providers = {"Agaval", "Addi", "Sistecrédito", "Banco de Bogotá"};
        double[] rates = {0.05, 0.10, 0.10, 0.03};
        String[] statuses = {"Recibido", "En diagnóstico", "En reparación", "Listo"};
        String[] statusDescriptions = {
            "Recibimos tu equipo y registramos la solicitud.",
            "El equipo técnico está revisando la falla reportada.",
            "Se están realizando las labores de reparación del equipo.",
            "El servicio técnico finalizó; el equipo está listo."
        };
        String[] faqQuestions = {
            "¿Qué cubre la garantía?",
            "¿Cómo radico un servicio técnico?",
            "¿Cómo consulto el estado de mi solicitud?",
            "¿La fecha de recogida queda confirmada?",
            "¿Qué métodos de pago se aceptan?",
            "¿El simulador aprueba mi crédito?",
            "¿Los precios de la cotización quedan reservados?",
            "¿Cuánto tarda un envío?",
            "¿Las tarifas de envío son definitivas?"
        };
        String[] faqAnswers = {
            "La cobertura y vigencia dependen de las condiciones del fabricante y de la compra.",
            "Completa los datos del equipo, describe la falla y elige una fecha preferida.",
            "Ingresa el código único de ticket que recibiste al radicar.",
            "No. La fecha es una preferencia y requiere confirmación del equipo de atención.",
            "Confirma los métodos disponibles directamente con Bythephone.",
            "No. El cálculo es informativo y no representa aprobación ni condiciones reales.",
            "No. Los precios y la disponibilidad deben confirmarse después del vencimiento.",
            "Depende del destino y la cobertura; los tiempos son estimados.",
            "No. Las tarifas mostradas son de demostración y no están confirmadas por una transportadora."
        };

        List<Map<String, Object>> quotes = new ArrayList<>();
        List<Map<String, Object>> simulations = new ArrayList<>();
        List<Map<String, Object>> serviceRequests = new ArrayList<>();
        List<Map<String, Object>> orders = new ArrayList<>();

        Function<Long, String> money = value -> cop.format(value);
        Function<String, String> readRequired = prompt -> {
            while (true) {
                System.out.print(prompt);
                String value = input.nextLine().trim();
                if (!value.isEmpty()) {
                    return value;
                }
                System.out.println("Este dato es obligatorio.");
            }
        };
        Function<String, String> readOptional = prompt -> {
            System.out.print(prompt);
            return input.nextLine().trim();
        };

        System.out.println("============================================");
        System.out.println("       BYTHEPHONE - SIMULADOR JAVA");
        System.out.println("============================================");
        System.out.println("Simulación de las secciones del sitio en la terminal.");
        System.out.println("No se inicia un servidor y la página web no se conecta a Java.");
        System.out.println("Los datos de prueba existen únicamente durante esta ejecución.");

        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("---------------- MENÚ PRINCIPAL ----------------");
            System.out.println("1. Inicio y catálogo");
            System.out.println("2. Cotizaciones");
            System.out.println("3. Simulador de crédito");
            System.out.println("4. Preguntas frecuentes");
            System.out.println("5. Radicar garantía o servicio técnico");
            System.out.println("6. Seguimiento por ticket");
            System.out.println("7. Actualizar estado del servicio (demostración)");
            System.out.println("8. Envío y checkout de demostración");
            System.out.println("9. Inicio de sesión y atención");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");
            String optionText = input.nextLine().trim();

            if ("0".equals(optionText)) {
                running = false;
            } else if ("1".equals(optionText)) {
                System.out.println();
                System.out.println("INICIO · TECNOLOGÍA QUE TE CONECTA");
                System.out.println("Bienvenido a Bythephone. Explora el catálogo o filtra por marca.");
                System.out.println("Marcas disponibles: Samsung, Apple, Xiaomi y Motorola.");
                String brandFilter = readOptional.apply("Marca para filtrar (Enter para ver todas): ");
                System.out.println();
                System.out.println("CATÁLOGO DE EQUIPOS");
                int shown = 0;
                for (int i = 0; i < names.length; i++) {
                    if (!brandFilter.isEmpty()
                            && !brands[i].toLowerCase(Locale.ROOT).contains(brandFilter.toLowerCase(Locale.ROOT))) {
                        continue;
                    }
                    System.out.printf(
                            "%d. %s %s — %s%n   %s%n",
                            i + 1, brands[i], names[i], money.apply(prices[i]), specs[i]);
                    shown++;
                }
                if (shown == 0) {
                    System.out.println("No hay equipos de esa marca en el catálogo de demostración.");
                }
                System.out.println("Precios y existencias sujetos a confirmación.");
            } else if ("2".equals(optionText)) {
                System.out.println();
                System.out.println("COTIZACIONES · vigencia de 7 días");
                for (int i = 0; i < names.length; i++) {
                    System.out.printf("%d. %s %s — %s%n", i + 1, brands[i], names[i], money.apply(prices[i]));
                }
                System.out.println("Selecciona productos y escribe 0 para generar la cotización.");
                Map<Integer, Integer> items = new LinkedHashMap<>();
                while (true) {
                    System.out.print("Número de producto (0 para terminar): ");
                    String selectionText = input.nextLine().trim();
                    int selection;
                    try {
                        selection = Integer.parseInt(selectionText);
                    } catch (NumberFormatException exception) {
                        System.out.println("Ingresa un número de producto válido.");
                        continue;
                    }
                    if (selection == 0) {
                        break;
                    }
                    if (selection < 1 || selection > names.length) {
                        System.out.println("El producto seleccionado no existe.");
                        continue;
                    }
                    int index = selection - 1;
                    int currentQuantity = items.getOrDefault(index, 0);
                    if (currentQuantity >= 99) {
                        System.out.println("La cantidad máxima por equipo es 99.");
                        continue;
                    }
                    System.out.print("Cantidad para " + names[index] + " (1-" + (99 - currentQuantity) + "): ");
                    String quantityText = input.nextLine().trim();
                    int quantity;
                    try {
                        quantity = Integer.parseInt(quantityText);
                    } catch (NumberFormatException exception) {
                        System.out.println("Ingresa una cantidad válida.");
                        continue;
                    }
                    if (quantity < 1 || quantity > 99 - currentQuantity) {
                        System.out.println("La cantidad debe estar entre 1 y " + (99 - currentQuantity) + ".");
                        continue;
                    }
                    items.put(index, currentQuantity + quantity);
                }
                if (items.isEmpty()) {
                    System.out.println("No se generó una cotización porque no seleccionaste productos.");
                    continue;
                }
                LocalDate generatedDate = LocalDate.now();
                String quoteNumber = "BT-COT-"
                        + UUID.randomUUID().toString().substring(0, 8).toUpperCase(Locale.ROOT);
                long total = 0;
                System.out.println();
                System.out.println("COTIZACIÓN " + quoteNumber);
                System.out.println("Generada: " + dateFormat.format(generatedDate));
                System.out.println("Válida hasta: " + dateFormat.format(generatedDate.plusDays(7)));
                System.out.printf("%-38s %8s %18s %18s%n", "Producto", "Cantidad", "Precio unitario", "Total");
                for (Map.Entry<Integer, Integer> item : items.entrySet()) {
                    int index = item.getKey();
                    int quantity = item.getValue();
                    long lineTotal = prices[index] * quantity;
                    total += lineTotal;
                    System.out.printf(
                            "%-38s %8d %18s %18s%n",
                            brands[index] + " " + names[index],
                            quantity,
                            money.apply(prices[index]),
                            money.apply(lineTotal));
                }
                Map<String, Object> quote = new LinkedHashMap<>();
                quote.put("number", quoteNumber);
                quote.put("generatedDate", generatedDate);
                quote.put("items", items);
                quote.put("total", total);
                quotes.add(quote);
                System.out.println("Total estimado: " + money.apply(total));
                System.out.println("No incluye envío. Los precios deben confirmarse.");
                System.out.println("Cotizaciones simuladas en esta ejecución: " + quotes.size());
            } else if ("3".equals(optionText)) {
                System.out.println();
                System.out.println("SIMULADOR DE CRÉDITO · cálculo informativo");
                System.out.println("1. Seleccionar un equipo");
                System.out.println("2. Ingresar un monto");
                System.out.print("Tipo de compra: ");
                String purchaseType = input.nextLine().trim();
                long purchasePrice;
                String purchaseName;
                if ("1".equals(purchaseType)) {
                    for (int i = 0; i < names.length; i++) {
                        System.out.printf("%d. %s %s — %s%n", i + 1, brands[i], names[i], money.apply(prices[i]));
                    }
                    System.out.print("Selecciona el equipo: ");
                    int productIndex;
                    try {
                        productIndex = Integer.parseInt(input.nextLine().trim()) - 1;
                    } catch (NumberFormatException exception) {
                        System.out.println("Selección inválida.");
                        continue;
                    }
                    if (productIndex < 0 || productIndex >= names.length) {
                        System.out.println("Selección inválida.");
                        continue;
                    }
                    purchaseName = brands[productIndex] + " " + names[productIndex];
                    purchasePrice = prices[productIndex];
                } else if ("2".equals(purchaseType)) {
                    System.out.print("Monto de compra en COP (1 a 100.000.000): ");
                    try {
                        purchasePrice = Long.parseLong(input.nextLine().trim().replace(".", "").replace(",", ""));
                    } catch (NumberFormatException exception) {
                        System.out.println("Ingresa un monto válido.");
                        continue;
                    }
                    if (purchasePrice < 1 || purchasePrice > 100_000_000) {
                        System.out.println("El monto debe estar entre $1 y $100.000.000 COP.");
                        continue;
                    }
                    purchaseName = "Monto personalizado";
                } else {
                    System.out.println("Selecciona 1 o 2.");
                    continue;
                }
                System.out.print("Cuota inicial en COP (0 a " + money.apply(purchasePrice - 1) + "): ");
                long downPayment;
                try {
                    downPayment = Long.parseLong(input.nextLine().trim().replace(".", "").replace(",", ""));
                } catch (NumberFormatException exception) {
                    System.out.println("Ingresa una cuota inicial válida.");
                    continue;
                }
                if (downPayment < 0 || downPayment >= purchasePrice) {
                    System.out.println("La cuota inicial debe ser menor al valor de compra.");
                    continue;
                }
                System.out.print("Número de cuotas (3-36): ");
                int installments;
                try {
                    installments = Integer.parseInt(input.nextLine().trim());
                } catch (NumberFormatException exception) {
                    System.out.println("Ingresa un plazo válido.");
                    continue;
                }
                if (installments < 3 || installments > 36) {
                    System.out.println("El plazo debe estar entre 3 y 36 cuotas.");
                    continue;
                }
                for (int i = 0; i < providers.length; i++) {
                    System.out.printf("%d. %s — %.0f%% tasa única%n", i + 1, providers[i], rates[i] * 100);
                }
                System.out.print("Selecciona la entidad: ");
                int providerIndex;
                try {
                    providerIndex = Integer.parseInt(input.nextLine().trim()) - 1;
                } catch (NumberFormatException exception) {
                    System.out.println("Selecciona una entidad válida.");
                    continue;
                }
                if (providerIndex < 0 || providerIndex >= providers.length) {
                    System.out.println("Selecciona una entidad válida.");
                    continue;
                }
                long financed = purchasePrice - downPayment;
                long interest = Math.round(financed * rates[providerIndex]);
                long repaymentTotal = financed + interest;
                long monthlyPayment = (repaymentTotal + installments - 1) / installments;
                String simulationNumber = "BT-CRED-"
                        + UUID.randomUUID().toString().substring(0, 8).toUpperCase(Locale.ROOT);
                Map<String, Object> simulation = new LinkedHashMap<>();
                simulation.put("number", simulationNumber);
                simulation.put("purchase", purchaseName);
                simulation.put("provider", providers[providerIndex]);
                simulation.put("interest", interest);
                simulation.put("monthlyPayment", monthlyPayment);
                simulations.add(simulation);
                System.out.println();
                System.out.println("SIMULACIÓN " + simulationNumber);
                System.out.println("Equipo o monto: " + purchaseName);
                System.out.println("Entidad: " + providers[providerIndex]
                        + " · tasa única del " + Math.round(rates[providerIndex] * 100) + "%");
                System.out.println("Valor de compra: " + money.apply(purchasePrice));
                System.out.println("Cuota inicial: " + money.apply(downPayment));
                System.out.println("Monto financiado: " + money.apply(financed));
                System.out.println("Interés estimado: " + money.apply(interest));
                System.out.println("Total a cuotas: " + money.apply(repaymentTotal));
                System.out.println("Plazo: " + installments + " cuotas");
                System.out.println("Cuota mensual estimada: " + money.apply(monthlyPayment));
                System.out.println("Esta simulación no es una oferta ni una aprobación de crédito.");
            } else if ("4".equals(optionText)) {
                System.out.println();
                System.out.println("PREGUNTAS FRECUENTES");
                String search = readOptional.apply("Buscar (Enter para ver todas): ").toLowerCase(locale);
                int found = 0;
                for (int i = 0; i < faqQuestions.length; i++) {
                    if (search.isEmpty()
                            || faqQuestions[i].toLowerCase(locale).contains(search)
                            || faqAnswers[i].toLowerCase(locale).contains(search)) {
                        System.out.println();
                        System.out.println("P: " + faqQuestions[i]);
                        System.out.println("R: " + faqAnswers[i]);
                        found++;
                    }
                }
                if (found == 0) {
                    System.out.println("No encontramos coincidencias. Prueba otra palabra.");
                }
                System.out.println();
                System.out.println("Atención: WhatsApp +57 301 537 9982.");
            } else if ("5".equals(optionText)) {
                System.out.println();
                System.out.println("GARANTÍA Y SERVICIO TÉCNICO · radicación de demostración");
                String customerName = readRequired.apply("Nombre completo: ");
                String customerEmail = readRequired.apply("Correo electrónico: ");
                if (!customerEmail.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
                    System.out.println("Ingresa un correo válido.");
                    continue;
                }
                String customerPhone = readRequired.apply("Teléfono: ");
                String equipment = readRequired.apply("Equipo: ");
                String serialNumber = readOptional.apply("IMEI o número de serie (opcional): ");
                System.out.println("1. Garantía");
                System.out.println("2. Servicio técnico");
                System.out.println("3. Diagnóstico");
                System.out.print("Tipo de solicitud: ");
                int requestTypeIndex;
                try {
                    requestTypeIndex = Integer.parseInt(input.nextLine().trim()) - 1;
                } catch (NumberFormatException exception) {
                    System.out.println("Selecciona un tipo válido.");
                    continue;
                }
                if (requestTypeIndex < 0 || requestTypeIndex > 2) {
                    System.out.println("Selecciona un tipo válido.");
                    continue;
                }
                String failureDescription = readRequired.apply("Describe la falla (mínimo 10 caracteres): ");
                if (failureDescription.length() < 10) {
                    System.out.println("La descripción debe tener al menos 10 caracteres.");
                    continue;
                }
                String department = readRequired.apply("Departamento: ");
                String city = readRequired.apply("Ciudad o municipio: ");
                String address = readRequired.apply("Dirección de recogida: ");
                System.out.print("Fecha preferida (AAAA-MM-DD): ");
                LocalDate pickupDate;
                try {
                    pickupDate = LocalDate.parse(input.nextLine().trim());
                } catch (RuntimeException exception) {
                    System.out.println("Ingresa una fecha válida en formato AAAA-MM-DD.");
                    continue;
                }
                if (pickupDate.isBefore(LocalDate.now())) {
                    System.out.println("La fecha debe ser igual o posterior a hoy.");
                    continue;
                }
                String pickupTime = readRequired.apply("Franja horaria preferida: ");
                String pickupNotes = readOptional.apply("Indicaciones adicionales (opcional): ");
                boolean ticketExists;
                String ticket;
                do {
                    ticket = "BT-ST-" + UUID.randomUUID()
                            .toString().replace("-", "").substring(0, 10).toUpperCase(Locale.ROOT);
                    ticketExists = false;
                    for (Map<String, Object> existingRequest : serviceRequests) {
                        if (ticket.equals(existingRequest.get("ticket"))) {
                            ticketExists = true;
                            break;
                        }
                    }
                } while (ticketExists);
                String[] requestTypes = {"Garantía", "Servicio técnico", "Diagnóstico"};
                LocalDateTime createdAt = LocalDateTime.now();
                List<Map.Entry<String, LocalDateTime>> history = new ArrayList<>();
                history.add(new java.util.AbstractMap.SimpleEntry<>("Recibido", createdAt));
                Map<String, Object> request = new LinkedHashMap<>();
                request.put("ticket", ticket);
                request.put("createdAt", createdAt);
                request.put("customerName", customerName);
                request.put("customerEmail", customerEmail);
                request.put("customerPhone", customerPhone);
                request.put("equipment", equipment);
                request.put("serialNumber", serialNumber);
                request.put("requestType", requestTypes[requestTypeIndex]);
                request.put("failureDescription", failureDescription);
                request.put("department", department);
                request.put("city", city);
                request.put("address", address);
                request.put("pickupDate", pickupDate);
                request.put("pickupTime", pickupTime);
                request.put("pickupNotes", pickupNotes);
                request.put("history", history);
                serviceRequests.add(request);
                System.out.println();
                System.out.println("Solicitud simulada correctamente.");
                System.out.println("Ticket: " + ticket);
                System.out.println("Estado inicial: Recibido");
                System.out.println("Recogida preferida: " + dateFormat.format(pickupDate) + " · " + pickupTime);
                System.out.println("La recogida no queda confirmada ni se envía a una transportadora.");
            } else if ("6".equals(optionText) || "7".equals(optionText)) {
                if (serviceRequests.isEmpty()) {
                    System.out.println("No hay solicitudes en esta ejecución. Registra una en la opción 5.");
                    continue;
                }
                System.out.println();
                System.out.println("SEGUIMIENTO DE SERVICIO");
                String requestedTicket = readOptional.apply(
                        "Código del ticket (Enter para la solicitud más reciente): ").toUpperCase(Locale.ROOT);
                Map<String, Object> request = null;
                if (requestedTicket.isEmpty()) {
                    request = serviceRequests.get(serviceRequests.size() - 1);
                } else {
                    for (Map<String, Object> candidate : serviceRequests) {
                        if (requestedTicket.equals(candidate.get("ticket"))) {
                            request = candidate;
                            break;
                        }
                    }
                }
                if (request == null) {
                    System.out.println("No se encontró el ticket.");
                    continue;
                }
                @SuppressWarnings("unchecked")
                List<Map.Entry<String, LocalDateTime>> history =
                        (List<Map.Entry<String, LocalDateTime>>) request.get("history");
                if ("7".equals(optionText)) {
                    for (int i = 0; i < statuses.length; i++) {
                        System.out.println((i + 1) + ". " + statuses[i]);
                    }
                    System.out.print("Nuevo estado: ");
                    int statusIndex;
                    try {
                        statusIndex = Integer.parseInt(input.nextLine().trim()) - 1;
                    } catch (NumberFormatException exception) {
                        System.out.println("Selecciona un estado válido.");
                        continue;
                    }
                    if (statusIndex < 0 || statusIndex >= statuses.length) {
                        System.out.println("Selecciona un estado válido.");
                        continue;
                    }
                    history.add(new java.util.AbstractMap.SimpleEntry<>(statuses[statusIndex], LocalDateTime.now()));
                    System.out.println("Estado actualizado en la simulación: " + statuses[statusIndex]);
                } else {
                    String currentStatus = history.get(history.size() - 1).getKey();
                    System.out.println("Ticket: " + request.get("ticket"));
                    System.out.println("Equipo: " + request.get("equipment"));
                    System.out.println("Tipo: " + request.get("requestType"));
                    System.out.println("Falla: " + request.get("failureDescription"));
                    System.out.println("Dirección: " + request.get("address") + ", "
                            + request.get("city") + ", " + request.get("department"));
                    System.out.println("Recogida preferida: "
                            + dateFormat.format((LocalDate) request.get("pickupDate"))
                            + " · " + request.get("pickupTime"));
                    System.out.println("Estado actual: " + currentStatus);
                    System.out.println("Línea de tiempo:");
                    for (int i = 0; i < statuses.length; i++) {
                        boolean reached = false;
                        for (Map.Entry<String, LocalDateTime> entry : history) {
                            if (statuses[i].equals(entry.getKey())) {
                                reached = true;
                            }
                        }
                        String marker = statuses[i].equals(currentStatus) ? "[>] "
                                : reached ? "[x] " : "[ ] ";
                        System.out.println(marker + statuses[i] + " — " + statusDescriptions[i]);
                        for (Map.Entry<String, LocalDateTime> entry : history) {
                            if (statuses[i].equals(entry.getKey())) {
                                System.out.println("    Actualizado: " + dateTimeFormat.format(entry.getValue()));
                            }
                        }
                    }
                }
            } else if ("8".equals(optionText)) {
                System.out.println();
                System.out.println("ENVÍO Y CHECKOUT · simulación, no procesa pagos");
                String[] sampleNames = {
                    "Celular de muestra", "Celular 5G de muestra", "Celular premium de muestra"
                };
                long[] samplePrices = {799900, 1499900, 2399900};
                for (int i = 0; i < sampleNames.length; i++) {
                    System.out.printf("%d. %s — %s%n", i + 1, sampleNames[i], money.apply(samplePrices[i]));
                }
                Map<Integer, Integer> items = new LinkedHashMap<>();
                while (true) {
                    System.out.print("Producto (0 para continuar): ");
                    int selection;
                    try {
                        selection = Integer.parseInt(input.nextLine().trim());
                    } catch (NumberFormatException exception) {
                        System.out.println("Selecciona un número válido.");
                        continue;
                    }
                    if (selection == 0) {
                        break;
                    }
                    if (selection < 1 || selection > sampleNames.length) {
                        System.out.println("Ese producto no existe.");
                        continue;
                    }
                    int index = selection - 1;
                    System.out.print("Cantidad (1-99): ");
                    int quantity;
                    try {
                        quantity = Integer.parseInt(input.nextLine().trim());
                    } catch (NumberFormatException exception) {
                        System.out.println("Ingresa una cantidad válida.");
                        continue;
                    }
                    if (quantity < 1 || quantity > 99) {
                        System.out.println("La cantidad debe estar entre 1 y 99.");
                        continue;
                    }
                    items.put(index, items.getOrDefault(index, 0) + quantity);
                    if (items.get(index) > 99) {
                        items.put(index, 99);
                    }
                }
                if (items.isEmpty()) {
                    System.out.println("No se creó un pedido porque el carrito está vacío.");
                    continue;
                }
                String recipient = readRequired.apply("Nombre de quien recibe: ");
                String email = readRequired.apply("Correo electrónico: ");
                if (!email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
                    System.out.println("Ingresa un correo válido.");
                    continue;
                }
                String phone = readRequired.apply("Teléfono: ");
                String department = readRequired.apply("Departamento: ");
                String city = readRequired.apply("Ciudad o municipio: ");
                String address = readRequired.apply("Dirección: ");
                System.out.println("Método de pago (simulado):");
                System.out.println("1. Tarjeta (simulación)");
                System.out.println("2. Transferencia (simulación)");
                System.out.println("3. Pago al recibir (simulación)");
                System.out.print("Selecciona el método: ");
                String paymentChoice = input.nextLine().trim();
                if (!"1".equals(paymentChoice) && !"2".equals(paymentChoice) && !"3".equals(paymentChoice)) {
                    System.out.println("Selecciona un método válido.");
                    continue;
                }
                long subtotal = 0;
                for (Map.Entry<Integer, Integer> item : items.entrySet()) {
                    subtotal += samplePrices[item.getKey()] * item.getValue();
                }
                String normalizedCity = java.text.Normalizer.normalize(city, java.text.Normalizer.Form.NFD)
                        .replaceAll("\\p{M}", "").toLowerCase(Locale.ROOT);
                long shipping = "Antioquia".equalsIgnoreCase(department)
                        ? ("girardota".equals(normalizedCity) ? 8000 : 15000)
                        : 25000;
                String shippingZone = "Antioquia".equalsIgnoreCase(department)
                        ? ("girardota".equals(normalizedCity)
                                ? "Entrega local · Girardota" : "Envío departamental · Antioquia")
                        : "Envío nacional · Colombia";
                String[] paymentMethods = {
                    "Tarjeta (simulación)", "Transferencia (simulación)", "Pago al recibir (simulación)"
                };
                String orderNumber = "BT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase(Locale.ROOT);
                Map<String, Object> order = new LinkedHashMap<>();
                order.put("number", orderNumber);
                order.put("recipient", recipient);
                order.put("email", email);
                order.put("phone", phone);
                order.put("city", city);
                order.put("address", address);
                order.put("items", items);
                order.put("subtotal", subtotal);
                order.put("shipping", shipping);
                order.put("total", subtotal + shipping);
                orders.add(order);
                System.out.println();
                System.out.println("COMPROBANTE DE DEMOSTRACIÓN " + orderNumber);
                System.out.println("Recibe: " + recipient + " · " + email + " · " + phone);
                System.out.println("Dirección: " + address + ", " + city + ", " + department);
                for (Map.Entry<Integer, Integer> item : items.entrySet()) {
                    int index = item.getKey();
                    System.out.println(item.getValue() + " × " + sampleNames[index] + " — "
                            + money.apply(samplePrices[index] * item.getValue()));
                }
                System.out.println("Subtotal: " + money.apply(subtotal));
                System.out.println("Envío: " + shippingZone + " · " + money.apply(shipping));
                System.out.println("Total estimado: " + money.apply(subtotal + shipping));
                System.out.println("Método: " + paymentMethods[Integer.parseInt(paymentChoice) - 1]);
                System.out.println("No se realizó ningún cobro ni se confirmó una compra real.");
            } else if ("9".equals(optionText)) {
                System.out.println();
                System.out.println("INICIO DE SESIÓN Y ATENCIÓN");
                System.out.println("El inicio de sesión no está conectado a un sistema de usuarios.");
                System.out.println("No ingreses una contraseña real; no se guardan credenciales.");
                System.out.println("Atención telefónica: +57 301 537 9982");
                System.out.println("WhatsApp: https://wa.me/573015379982");
                System.out.println("Consulta las preguntas frecuentes desde la opción 4.");
            } else {
                System.out.println("Opción inválida. Selecciona un número del menú.");
            }
        }

        System.out.println("La simulación terminó. Las cotizaciones, créditos, solicitudes y pedidos "
                + "temporales fueron descartados.");
        input.close();
    }
}
