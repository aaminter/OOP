package org.skypro.skyshop;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.exceptions.BestResultNotFoundException;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.searchable.Searchable;
import org.skypro.skyshop.searchengine.SearchEngine;

import java.util.Set;

public class App {
    public static void main(String[] args) {
        Product bread = new SimpleProduct("Хлеб Красная цена", 24);
        Product milk = new DiscountedProduct("Молоко Счастливое детство", 69, 10);
        Product sausage = new FixPriceProduct("Колбаса Вязанка");
        Product sausagePAPA = new FixPriceProduct("Колбаса Папа Может");
        Product cheese = new SimpleProduct("Сыр Liebendorf", 220);
        Product chocolate = new DiscountedProduct("Шоколад Россия - Щедрая душа", 80, 50);
        Product chips = new SimpleProduct("Чипсы Lay\\'s", 175);
        Article sausageSecond = new Article("Колбаса <<Папа может>>", "Одна из тех, что мне нравится");
        Article sausageThird = new Article("Колбаса <<Докторская>>", "Постоянно её беру");

        ProductBasket basket = new ProductBasket();
        basket.addProduct("Хлеб", bread);
        basket.addProduct("Молоко", milk);
        basket.addProduct("Колбаса", sausage);
        basket.addProduct("Колбаса", sausagePAPA);
        basket.addProduct("Сыр", cheese);
        basket.addProduct("Шоколад", chocolate);
        basket.addProduct("Чипсы", chips);

        System.out.println();
        System.out.println("Корзина");
        basket.printBasket();
        if (basket.checkProductByName("Колбаса")) {
            System.out.println("Есть такой продукт");
        } else {
            System.out.println("Нет такого");
        }
        System.out.println(basket.removeByName("Колбаса"));
        basket.printBasket();
        if (basket.checkProductByName("Абрикос")) {
            System.out.println("Есть такой продукт");
        } else {
            System.out.println("Нет такого");
        }
        System.out.println(basket.removeByName("Абрикос"));
        System.out.println();
        basket.clear();
        basket.printBasket();


        SearchEngine searchables = new SearchEngine();
        searchables.add(bread);
        searchables.add(milk);
        searchables.add(sausage);
        searchables.add(sausagePAPA);
        searchables.add(cheese);
        searchables.add(chocolate);
        searchables.add(chips);
        searchables.add(sausageSecond);
        searchables.add(sausageThird);
        try {
            System.out.println(searchables.search("Колбаса"));
        } catch (BestResultNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println();
        try {
            Set<Searchable> searchingTmp = searchables.search("о");
            System.out.println(searchingTmp);
        } catch (BestResultNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }
}