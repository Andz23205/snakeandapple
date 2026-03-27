package SM;

import java.util.Scanner;

public class StudentMain {
    static class Student {
        protected String id;
        protected String fullName;
        protected String dateOfBirth;
        protected String gender;

        public Student() {}

        public Student(String id, String fullName, String dateOfBirth, String gender) {
            this.id = id;
            this.fullName = fullName;
            this.dateOfBirth = dateOfBirth;
            this.gender = gender;
        }

        public void input(Scanner sc) {
            System.out.print("Nhap ma SV: ");
            id = sc.nextLine();
            System.out.print("Nhap ho ten: ");
            fullName = sc.nextLine();
            System.out.print("Nhap ngay thang nam sinh: ");
            dateOfBirth = sc.nextLine();
            System.out.print("Nhap gioi tinh: ");
            gender = sc.nextLine();
        }

        public void show() {
            System.out.println("Ma SV: " + id);
            System.out.println("Ho ten: " + fullName);
            System.out.println("Ngay sinh: " + dateOfBirth);
            System.out.println("Gioi tinh: " + gender);
        }
    }

    static class ITStudent extends Student {
        private double ctdl, rrMath, database, oop, programming;

        public ITStudent() { super(); }

        @Override 
        public void input(Scanner sc) {
            super.input(sc);
            try {
                System.out.print("Nhap diem CTDL-GT: ");
                ctdl = Double.parseDouble(sc.nextLine());
                System.out.print("Nhap diem RR Math: ");
                rrMath = Double.parseDouble(sc.nextLine());
                System.out.print("Nhap diem Database: ");
                database = Double.parseDouble(sc.nextLine());
                System.out.print("Nhap diem OOP: ");
                oop = Double.parseDouble(sc.nextLine());
                System.out.print("Nhap diem Programming: ");
                programming = Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Loi: Vui long nhap so thuc!");
            }
        }

        public double averageScore() {
            return (ctdl + rrMath + database + oop + programming) / 5;
        }

        public String classification() {
            double avg = averageScore();
            if (avg >= 8) return "Gioi";
            else if (avg >= 6.5) return "Kha";
            else if (avg >= 5) return "Trung binh";
            else return "Yeu";
        }

        @Override
        public void show() {
            super.show();
            System.out.println("Diem trung binh: " + String.format("%.2f", averageScore()));
            System.out.println("Xep loai: " + classification());
        }
    }

    static class EconomicsStudent extends Student {
        private double micro, macro, economicLaw;

        public EconomicsStudent() { super(); }

        @Override
        public void input(Scanner sc) {
            super.input(sc);
            try {
                System.out.print("Nhap diem Microeconomics: ");
                micro = Double.parseDouble(sc.nextLine());
                System.out.print("Nhap diem Macroeconomics: ");
                macro = Double.parseDouble(sc.nextLine());
                System.out.print("Nhap diem Economic Law: ");
                economicLaw = Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Loi: Vui long nhap so thuc!");
            }
        }

        public double averageScore() {
            return (micro + macro + economicLaw) / 3;
        }

        @Override
        public void show() {
            super.show();
            System.out.println("Diem trung binh: " + String.format("%.2f", averageScore()));
            System.out.println("Xep loai: "); 
        }
    }

    public static void main(String[] args) {
       
        Scanner sc = new Scanner(System.in);
        
        System.out.println("1. IT Student");
        System.out.println("2. Economics Student");
        System.out.print("Chon loai sinh vien: ");
        
        try {
            int choice = Integer.parseInt(sc.nextLine());

            if (choice == 1) {
                ITStudent it = new ITStudent();
                it.input(sc);
                System.out.println(" THONG TIN SINH VIEN IT ");
                it.show();
            } else if (choice == 2) {
                EconomicsStudent eco = new EconomicsStudent();
                eco.input(sc);
                System.out.println("THONG TIN SINH VIEN KINH TE");
                eco.show();
            } else {
                System.out.println("Lua chon khong hop le!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Vui long nhap so 1 hoac 2!");
        } finally {
            sc.close(); 
        }
    }
}