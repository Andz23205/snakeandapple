package StudentMain;

import java.util.Scanner;

class student {
    protected String id;
    protected String fullname;
    protected String birth;
    protected String gender;
    protected String email;

    public student() {}

    public student(String id, String fullname, String birth, String gender, String email) {
        this.id = id;
        this.fullname = fullname;
        this.birth = birth;
        this.gender = gender;
        this.email = email;
    }

    public String getid() { return id; }
    public void setid(String id) { this.id = id; }

    public String getfullname() { return fullname; }
    public void setfullname(String fullname) { this.fullname = fullname; }

    public String getbirth() { return birth; }
    public void setbirth(String birth) { this.birth = birth; }

    public String getgender() { return gender; }
    public void setgender(String gender) { this.gender = gender; }

    public String getemail() { return email; }
    public void setemail(String email) { this.email = email; }

    private boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    private boolean isValidBirth(String birth) {
        return birth.matches("\\d{2}/\\d{2}/\\d{4}");
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    public void input() {
        Scanner sc = new Scanner(System.in);

        do {
            System.out.print("ID: ");
            id = sc.nextLine();
            if (isEmpty(id))
                System.out.println("ID khong duoc rong!");
        } while (isEmpty(id));

        do {
            System.out.print("FULL NAME: ");
            fullname = sc.nextLine();
            if (isEmpty(fullname))
                System.out.println("Ten khong duoc rong!");
        } while (isEmpty(fullname));

        do {
            System.out.print("Date of birth (dd/MM/yyyy): ");
            birth = sc.nextLine();
            if (!isValidBirth(birth))
                System.out.println("Ngay sinh phai dung dinh dang dd/MM/yyyy!");
        } while (!isValidBirth(birth));

        do {
            System.out.print("Gender: ");
            gender = sc.nextLine();
            if (isEmpty(gender))
                System.out.println("Gioi tinh khong duoc rong!");
        } while (isEmpty(gender));

        do {
            System.out.print("Email: ");
            email = sc.nextLine();
            if (!isValidEmail(email))
                System.out.println("Email khong hop le!");
        } while (!isValidEmail(email));
    }

    public void show() {
        System.out.println("=== THONG TIN SINH VIEN ===");
        System.out.println("ID: " + id);
        System.out.println("Full name: " + fullname);
        System.out.println("Date of birth: " + birth);
        System.out.println("Gender: " + gender);
        System.out.println("Email: " + email);
    }
}
