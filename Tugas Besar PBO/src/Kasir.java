import java.util.Scanner;

public class Kasir implements Login {
    String password = "dillabaik";
    String captcha = "augst84";
    String username = "Aquamen";
    String inputCaptcha;
    String inputPassword;

    // method login
    public void login() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("LAKUKAN LOGIN TERLEBIH DAHULU");

        // Username
        System.err.println("Username\t: " + username);

        // Captcha
        System.out.println("Kode captcha = " + captcha);
        System.out.println("Masukkan captcha");
        inputCaptcha = scanner.next();

        while (!inputCaptcha.equalsIgnoreCase(captcha)) {
            System.out.println("ANDA BUKAN KASIR\n");
            System.out.println("Masukkan captcha");
            inputCaptcha = scanner.next();
        }

        System.out.println("CAPTCHA BENAR, ANDA KASIR\n\n");

        // Password
        System.out.println("Masukkan Password");
        inputPassword = scanner.next();

        while (!inputPassword.equals(password)) {
            System.out.println("PASSWORD SALAH\n");
            System.out.println("Masukkan password : ");
            inputPassword = scanner.next();
        }

        System.out.println("BERHASIL LOGIN\n\n");

    }

}
