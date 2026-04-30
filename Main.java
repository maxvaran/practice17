import java.util.Scanner;

class MyList<E> {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;

    public MyList() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    public void add(E element) {
        ensureCapacity();
        elements[size++] = element;
    }

    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Індекс: " + index + ", Розмір: " + size);
        }
        ensureCapacity();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    public E remove(int index) {
        checkIndex(index);
        E oldValue = (E) elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null;
        return oldValue;
    }

    public E get(int index) {
        checkIndex(index);
        return (E) elements[index];
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return elements.length;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            int newSize = elements.length * 2;
            Object[] newElements = new Object[newSize];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Індекс: " + index + ", Розмір: " + size);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        MyList<String> list = new MyList<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Меню керування переліком ---");
            System.out.println("1. Додати в кінець");
            System.out.println("2. Додати за індексом");
            System.out.println("3. Видалити за індексом");
            System.out.println("4. Отримати за індексом");
            System.out.println("5. Статус (розмір та буфер)");
            System.out.println("0. Вихід");
            System.out.print("Виберіть дію: ");

            String cmd = sc.nextLine();

            try {
                switch (cmd) {
                    case "1" -> {
                        System.out.print("Введіть значення: ");
                        list.add(sc.nextLine());
                        System.out.println("Додано.");
                    }
                    case "2" -> {
                        System.out.print("Введіть індекс: ");
                        int i = Integer.parseInt(sc.nextLine());
                        System.out.print("Введіть значення: ");
                        list.add(i, sc.nextLine());
                        System.out.println("Вставлено.");
                    }
                    case "3" -> {
                        System.out.print("Введіть індекс для видалення: ");
                        int i = Integer.parseInt(sc.nextLine());
                        System.out.println("Видалено елемент: " + list.remove(i));
                    }
                    case "4" -> {
                        System.out.print("Введіть індекс для пошуку: ");
                        int i = Integer.parseInt(sc.nextLine());
                        System.out.println("Результат: " + list.get(i));
                    }
                    case "5" -> {
                        System.out.println("Кількість елементів: " + list.size());
                        System.out.println("Місткість буфера (масиву): " + list.capacity());
                    }
                    case "0" -> {
                        System.out.println("Вихід з програми...");
                        System.exit(0);
                    }
                    default -> System.out.println("Помилка: невідома команда.");
                }
            } catch (Exception e) {
                System.out.println("Виникла помилка: " + e.getMessage());
            }
        }
    }
}