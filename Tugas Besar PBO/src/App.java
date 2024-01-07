import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        // polymorphisme
        Biodata P01 = new Pelanggan();

        Kasir K01 = new Kasir();
        K01.login();

        Scanner scanner = new Scanner(System.in);
        String pilihanUser;
        boolean isLanjutkan = true;

        while (isLanjutkan) {
            System.out.println("========== Perlengkapan Olahraga ===========\n");
            System.out.println("1.\tLihat Data Pelanggan");
            System.out.println("2.\tTambah Data Pelanggan");
            System.out.println("3.\tEdit Data Pelanggan");
            System.out.println("4.\tHapus Data Pelanggan");
            System.out.println("5.\tKeluar");

            System.out.print("\n Pilihan anda: ");
            pilihanUser = scanner.next();

            switch (pilihanUser) {
                case "1":
                    System.out.println("\n=================");
                    System.out.println("LIHAT DATA PELANGGAN");
                    System.out.println("=================");
                    P01.tampilkanData();
                    break;
                case "2":
                    System.out.println("\n=======================");
                    System.out.println("TAMBAH DATA PELANGGAN");
                    System.out.println("==========================");
                    P01.isiBiodata();
                    P01.tampilkanSemuaData();
                    P01.Struk();
                    P01.tampilkanData();
                    break;
                case "3":
                    System.out.println("\n================");
                    System.out.println("UBAH DATA PELANGGAN");
                    System.out.println("================");
                    P01.updateData();
                    P01.tampilkanData();
                    break;
                case "4":
                    System.out.println("\n==============");
                    System.out.println("HAPUS DATA PELANGGAN");
                    System.out.println("==============");
                    P01.deleteData();
                    P01.tampilkanData();
                    break;
                case "5":
                    System.out.println("\n=========================================");
                    System.out.println("ANDA TELAH KELUAR, SILAHKAN LOGIN KEMBALI");
                    System.out.println("===========================================");
                    System.exit(0);
                    break;
                default:
                    System.err.println("\nInput anda tidak ditemukan\nSilahkan pilih [1-4]");
            }
        }
        // exception
        try {
            // mengisi data pelanggan
            P01.isiBiodata();
            // mencetak struk
            P01.Struk();
        } catch (Exception e) {
            System.out.println("Terjadi salah input");
        }

    }

}