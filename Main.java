import java.util.Scanner;

class MyList<E> {
    private Object[] data;
    private int elementsCount;
    private int bufferSize;

    public MyList() {
        this.bufferSize = 10;
        this.data = new Object[bufferSize];
        this.elementsCount = 0;
    }

    // 1. Додавання елементів в кінець
    public void add(E element) {
        if (elementsCount == bufferSize) {
            resize();
        }
        data[elementsCount] = element;
        elementsCount++;
    }

    // 2. Додавання елементів за індексом
    public void add(int index, E element) {
        if (index < 0 || index > elementsCount) {
            throw new IndexOutOfBoundsException("Некоректний індекс для додавання: " + index);
        }
        if (elementsCount == bufferSize) {
            resize();
        }
        for (int i = elementsCount; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = element;
        elementsCount++;
    }

    public E remove(int index) {
        if (index < 0 || index >= elementsCount) {
            throw new IndexOutOfBoundsException("Індекс видалення поза межами: " + index);
        }
        E removedElement = (E) data[index];
        for (int i = index; i < elementsCount - 1; i++) {
            data[i] = data[i + 1];
        }
        data[elementsCount - 1] = null;
        elementsCount--;
        return removedElement;
    }

    public E get(int index) {
        if (index < 0 || index >= elementsCount) {
            throw new IndexOutOfBoundsException("Індекс доступу поза межами: " + index);
        }
        return (E) data[index];
    }

    public int size() {
        return elementsCount;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    private void resize() {
        bufferSize = bufferSize * 2;
        Object[] newData = new Object[bufferSize];
        for (int i = 0; i < elementsCount; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
}

public class Main {
    public static void main(String[] args) {
        MyList<String> list = new MyList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Додати в кінець");
            System.out.println("2. Додати за індексом");
            System.out.println("3. Видалити за індексом");
            System.out.println("4. Отримати за індексом");
            System.out.println("5. Кількість елементів та розмір буфера");
            System.out.println("0. Вихід");
            System.out.print("Оберіть опцію: ");

            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1" -> {
                        System.out.print("Введіть дані: ");
                        list.add(scanner.nextLine());
                        System.out.println("Елемент додано успішно.");
                    }
                    case "2" -> {
                        System.out.print("Введіть індекс: ");
                        int idx = Integer.parseInt(scanner.nextLine());
                        System.out.print("Введіть дані: ");
                        list.add(idx, scanner.nextLine());
                        System.out.println("Елемент вставлено.");
                    }
                    case "3" -> {
                        System.out.print("Індекс для видалення: ");
                        int idx = Integer.parseInt(scanner.nextLine());
                        System.out.println("Видалено: " + list.remove(idx));
                    }
                    case "4" -> {
                        System.out.print("Введіть індекс: ");
                        int idx = Integer.parseInt(scanner.nextLine());
                        System.out.println("Результат: " + list.get(idx));
                    }
                    case "5" -> {
                        System.out.println("Присутньо елементів: " + list.size());
                        System.out.println("Місткість буфера: " + list.getBufferSize());
                    }
                    case "0" -> {
                        System.out.println("Програму завершено.");
                        return;
                    }
                    default -> System.out.println("Невірний пункт меню!");
                }
            } catch (Exception e) {
                System.out.println("Помилка: " + e.getMessage());
            }
        }
    }
}
