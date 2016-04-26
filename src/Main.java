import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Начиная с вершины треугольника (см. пример ниже) и перемещаясь вниз на смежные числа, максимальная сумма до основания составляет 23.
 * Найдите максимальную сумму пути от вершины до основания следующего треугольника на
 * https://dl.dropboxusercontent.com/u/28873424/tasks/simple_triangle.txt
 * Примечание: Эту задачу можно решить перебором всех возможных вариантов, их здесь 16384.
 * Но в случае увеличении количества строк, задачу уже нельзя будет решить простым перебором.
 * Поэтому требуется найти более рациональный подход.
 * Код решения задачи необходимо выложить в публичный репозиторий github, bitbucket или любой другой.
 * Дополнительным бонусом будет найти решение для этого треугольника https://dl.dropboxusercontent.com/u/28873424/tasks/triangle.txt
 */

/**
 * UPD - для треугольника на 15 строк сумма - 1074, для 100 строк - 7273.
 */

public class Main {
    static int trianleSize = 100;
    static String HardTrianglePath = "C:\\Users\\Nikita\\Sotheme\\src\\triangle.txt";
    static String SimpleTrianglePath = "C:\\Users\\Nikita\\Sotheme\\src\\simple_triangle.txt";

    public static void main(String[] args) {
        // Don't forget to change trianleSize parameter (number of columns) after file is changed !!!
        int[][] array = getArray(trianleSize, HardTrianglePath);
        int sum = 0;
        for (int y = array.length - 1; y > 0; y--) {
            for (int x = 0; x < y; x++) {
                array[y - 1][x] += Math.max(array[y][x], array[y][x + 1]);
                sum = array[y - 1][x];
            }
        }
        System.out.println("The sum is " + sum);
    }

    private static int[][] getArray(int size, String filePath) {
        String[] strings = new String[size];
        List<String[]> list = new ArrayList<String[]>();
        int[][] array = new int[strings.length][strings.length];
        int i = 0;
        try {
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                strings[i] = scanner.nextLine();
                i++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (i = 0; i < strings.length; i++) {
            list.add(strings[i].split(" "));
        }

        // Make diagonal matrix  with A[i][j] structure
        for (int y = list.size() - 1; y >= 0; y--) {
            for (int x = 0; x <= y; x++) {
                array[y][x] = Integer.parseInt(list.get(y)[x]);
            }
        }
        return array;
    }
}
