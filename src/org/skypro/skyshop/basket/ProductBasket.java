package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.*;
import java.util.stream.Collectors;

public class ProductBasket {
    Map<String, List<Product>> basket = new HashMap<>();

    public void addProduct(String name, Product product) {
        basket.computeIfAbsent(name, k -> new ArrayList<>()).add(product);
    }

    public int getFullPrice() {
        return basket.values().stream()
                .flatMap(List::stream)
                .filter(Objects::nonNull)
                .mapToInt(Product::getPrice)
                .sum();
    }

    public void printBasket() {
        if (!basket.isEmpty()) {
            basket.values().stream()
                    .flatMap(List::stream)
                    .forEach(System.out::println);
            System.out.println("Итого: " + getFullPrice() + "руб." +
                    "\nСпециальных товаров: " + getSpecialCount());
        } else {
            System.out.println("В корзине пусто");
        }
    }

    private int getSpecialCount() {
        return (int) basket.values().stream()
                .flatMap(List::stream)
                .filter(Objects::nonNull).filter(Product::isSpecial)
                .count();
    }

    public boolean checkProductByName(String name) {
        return basket.containsKey(name);
    }

    public void clear() {
        basket.clear();
    }

    public List<String> removeByName(String name) {
        List<String> returning = basket.entrySet().stream()
                .filter(e -> e.getKey().contains(name))
                .flatMap(e -> e.getValue().stream()
                        .map(p -> e.getKey() + " " + p.getName()))
                .collect(Collectors.toList());

        basket.keySet().removeIf(key -> key.contains(name));

        if (returning.isEmpty()) {
            returning.add("Список пуст");
        }

        return returning;
    }
}