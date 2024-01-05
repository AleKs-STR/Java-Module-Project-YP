import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberPeople;

        while (true) {
            System.out.print("На скольких человек разделить счет?: ");

            if (scanner.hasNextInt()) {
                numberPeople = scanner.nextInt();
                if (numberPeople <= 1) {
                    System.out.println("Некорректное значение! Введите количество людей больше 1 ");
                } else {
                    break;
                }
            } else {
                System.out.println("Некоректное значение! Введите целое число.");
            }
        }
        System.out.println("Создан калькулятор на " + numberPeople + " человек");

        List<Product> products = new ArrayList<>();

        while (true) {
            System.out.println("Введите название товара или 'Завершить' для окончания: ");
            String name = scanner.next();
            if (name.equalsIgnoreCase("Завершить")) {
                break;
            }
            System.out.println("Введите стоимость товара в формате рубли.копейки: ");
            double price = scanner.nextDouble();

            Product product = new Product(name, price);
            products.add(product);
            System.out.println("Товар успешно добавлен.");
            System.out.println("Хотите добавить еще один товар? (Введите 'Завершить' для окончания): ");
            String continueInput = scanner.next();

            if (continueInput.equalsIgnoreCase("Завершить")) {
                break;
            }
        }
        System.out.println("Список товаров: ");
        double total = 0;

        for (Product product : products) {
            System.out.println(product.getName() + " - " + product.getPrice());
            total += product.getPrice();
        }

        System.out.println("Общая сумма: " + total);

        double amountPerPerson = total / numberPeople;
        String currency = (int) amountPerPerson == 1 ? "рубль" : "рубля";
        System.out.println("Каждому человеку необходимо заплатить: " + amountPerPerson + " " + currency);
    }
}

