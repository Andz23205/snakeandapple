import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Bai1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        System.out.print("nhap so phan tu n: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("nhap phan tu thu " + (i + 1) + ": ");
            list.add(sc.nextInt());
        }
        System.out.println("9ds theo thu tu:");
        for (int num : list) {
            System.out.print(num + " ");
        }
        ArrayList<Integer> sortedList = new ArrayList<>(list);
        sortedList.sort(Integer::compareTo);

        System.out.println("\nds tang dan:");
        for (int num : sortedList) {
            System.out.print(num + " ");
        }
        int sum = 0;
        for (int num : list) {
            sum += num;
        }
        double average = (list.size() > 0) ? (double) sum / list.size() : 0;
        System.out.println("\ntb+: " + average);
        if (!list.isEmpty()) {
            int max = list.get(0);
            int min = list.get(0);

            for (int num : list) {
                if (num > max) max = num;
                if (num < min) min = num;
            }

            System.out.println("MAX: " + max);
            System.out.println("MIN: " + min);
        }
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() % 2 == 0) {
                iterator.remove();
            }
        }

        System.out.println("ds con lai la:");
        for (int num : list) {
            System.out.print(num + " ");
        }

        sc.close();
    }
}