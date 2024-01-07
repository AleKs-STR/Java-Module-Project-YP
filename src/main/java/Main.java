import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberPeople;

        while (true) {
            System.out.print("На скольких человек необходимо разделить счет: ");

            if (scanner.hasNextInt()) {
                numberPeople = scanner.nextInt();

                if (numberPeople <= 1) {
                    System.out.println("Некорректное значение. Введите количество людей больше 1.");
                } else {
                    break;
                }
            } else {
                scanner.next();
                System.out.println("Некорректное значение. Введите целое число.");
            }
        }

        System.out.println("Создан калькулятор для " + numberPeople + " человек");

        List<Product> products = new ArrayList<>();

        while (true) {
            System.out.print("Введите название товара или 'Завершить' для окончания: ");
            String name = scanner.next();

            if (name.equalsIgnoreCase("Завершить")) {
                break;
            }

            System.out.print("Введите стоимость товара в формате рубли.копейки: ");
            double price;

            while (true) {
                String priceInput = scanner.next();
                try {
                    price = Double.parseDouble(priceInput);
                    if (price < 0) {
                        System.out.println("Некорректное значение. Введите положительное число.");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Некорректное значение. Введите число.");
                }
            }

            Product product = new Product(name, price);
            products.add(product);

            System.out.println("Товар успешно добавлен.");

            System.out.print("Хотите добавить еще один товар? (Введите 'Завершить' для окончания): ");
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

        long roundedTotal = Math.round(total);
        System.out.println("Общая сумма: " + roundedTotal);
        double amountPerPerson = (double) roundedTotal / numberPeople;
        long roundedAmount = Math.round(amountPerPerson);
        String currency = getCurrencyString(roundedAmount);
        System.out.println("Каждому человеку необходимо заплатить: " + roundedAmount + " " + currency);
    }

    private static String getCurrencyString(long amount) {
        int roundedAmount = (int) amount;
        int lastDigit = roundedAmount % 10;
        int lastTwoDigits = roundedAmount % 100;

        if (lastTwoDigits >= 11 && lastTwoDigits <= 19) {
            return "рублей";
        } else if (lastDigit == 1) {
            return "рубль";
        } else if (lastDigit >= 2 && lastDigit <= 4) {
            return "рубля";
        } else {
            return "рублей";
        }
    }
}
