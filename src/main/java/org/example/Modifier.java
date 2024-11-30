package org.example;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Modifier {
    public static void main(String[] args) {
        System.out.println(textModifier());
    }

    private static boolean containsSymbol(String text, String symbol) {
        boolean contains;
        if (text.contains(symbol)) contains = true;
        else contains = false;
        return contains;
    }

    public static String textModifier() {
        System.out.println("Введите Ваш текст:");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        scanner.close();

        //a. Если в тексте между словами присутствует несколько пробелов подряд, надо оставить только один из них.
        // Для реализации этого подпункта нельзя пользоваться методами replace() и replaceAll().
        StringBuilder modifiedText = new StringBuilder();
        boolean doubleSpace = false;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                if (!doubleSpace) {
                    modifiedText.append(text.charAt(i));
                }
                doubleSpace = true;
            } else {
                modifiedText.append(text.charAt(i));
                doubleSpace = false;
            }
        }

        //b. Если в тексте присутствует знак минус (-), это значит, что символ слева от этого знака надо поменять
        // местами с символом, который стоит справа от этого знака. Обратите внимание, что символом может быть не только
        // буква, но и цифра или любой другой знак/символ, в том числе символ пробела. После смены символов местами,
        // знак минус (-) надо удалить из строки результата.
        StringBuilder replaceSymbolsAroundMinus = new StringBuilder();
        for (int i = 0; i < modifiedText.length(); i++) {
            if (modifiedText.charAt(i) == '-') {
                replaceSymbolsAroundMinus.deleteCharAt(replaceSymbolsAroundMinus.length() - 1);
                replaceSymbolsAroundMinus.append(modifiedText.charAt(i + 1));
                replaceSymbolsAroundMinus.append(modifiedText.charAt(i - 1));
                i++;
            } else {
                replaceSymbolsAroundMinus.append(modifiedText.charAt(i));
            }
        }

        //c. Если в тексте присутствует знак плюс (+), вам необходимо заменить его на восклицательный знак (!).
        String changeSymbol = replaceSymbolsAroundMinus.toString().replace('+', '!');

        //d. В тексте могут присутствовать цифры (от 0 до 9). Вам необходимо посчитать их сумму и удалить из текста.
        // Сумму цифр вам нужно добавить в конец результирующей строки через пробел
        // (пробел должен стоять перед суммой цифр). Если цифр в тексте не было - "0" (ноль)
        // в конце строки выводить не нужно.
        int sum = 0;
        String finalModifiedText = "";
        for (int i = 0; i < changeSymbol.length(); i++) {
            char c = changeSymbol.charAt(i);
            if (Character.isDigit(c)) {
                sum += Character.getNumericValue(c);
            } else {
                finalModifiedText += c;
            }
        }
        finalModifiedText += " " + sum;

        return finalModifiedText;
        //Пимер ввода:
        //генрих1  играет+2   л-июбит0школу
        //Пример вывода:
        //генрих играет! илюби
        // тшколу 3

        //Пример ввода №2:
        //Я ю-лбю-л джаву   всем сердцем+
        //Пример вывода:
        //Я люблю джаву всем сердцем!
    }
}
