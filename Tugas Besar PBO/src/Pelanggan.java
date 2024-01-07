import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.sql.*;

public class Pelanggan extends Biodata {
    static final String DB_URL = "jdbc:mysql://localhost:3306/tbjava-database";
    static final String USER = "root";
    static final String PASS = "";
    static Connection conn;
    static ResultSet rs;

    static List<Pelanggan> pelangganList = new ArrayList<>();

    // Method to insert data into the database using isiBiodata
    public void isiBiodata() throws Exception {
        Scanner scanStr = new Scanner(System.in);
        Scanner scanIn = new Scanner(System.in);

        System.out.print("Masukkan no nota = ");
        No_Nota = scanStr.next();
        System.out.print("Masukkan nama pelanggan = ");
        nama_Pelanggan = scanStr.next();
        System.out.print("Masukkan no HP = ");
        noHP_Pelanggan = scanStr.next();
        System.out.print("Masukkan alamat = ");
        alamat_Pelanggan = scanStr.next();
        System.out.print("Masukkan nama barang = ");
        namaBarang = scanStr.next();
        System.out.print("Masukkan jenis barang = ");
        jenisBarang = scanStr.next();
        System.out.print("Masukkan harga barang = ");
        hargaBarang = scanIn.nextInt();
        System.out.print("Masukkan jumlah barang = ");
        jumlahBarang = scanIn.nextInt();

        Pelanggan pelanggan = new Pelanggan();
        pelanggan.No_Nota = No_Nota;
        pelanggan.nama_Pelanggan = nama_Pelanggan;
        pelanggan.noHP_Pelanggan = noHP_Pelanggan;
        pelanggan.alamat_Pelanggan = alamat_Pelanggan;
        pelanggan.namaBarang = namaBarang;
        pelanggan.jenisBarang = jenisBarang;
        pelanggan.hargaBarang = hargaBarang;
        pelanggan.jumlahBarang = jumlahBarang;

        Integer totalBayar = hargaBarang * jumlahBarang;
        this.totalBayar = totalBayar;
        pelanggan.totalBayar = totalBayar;
        pelangganList.add(pelanggan);
    }

    public void tampilkanSemuaData() {
        System.out.println("+--------------------------------+");
        System.out.println("|    BIODATA Perlengkapan Olahraga   |");
        System.out.println("+--------------------------------+");
        Integer totalBayar = hargaBarang * jumlahBarang;
        this.totalBayar = totalBayar;
        for (Pelanggan pelanggan : pelangganList) {
            System.out.println("No Faktur: " + pelanggan.No_Nota);
            System.out.println("Nama: " + pelanggan.nama_Pelanggan);
            System.out.println("No HP: " + pelanggan.noHP_Pelanggan);
            System.out.println("Alamat: " + pelanggan.alamat_Pelanggan);
            System.out.println("Jenis: " + pelanggan.jenisBarang);
            System.out.println("Menu: " + pelanggan.namaBarang);
            System.out.println("Harga: " + pelanggan.hargaBarang);
            System.out.println("Jumlah: " + pelanggan.jumlahBarang);
            System.out.println("Total: " + pelanggan.totalBayar);
            System.out.println("----------------------------------");
        }
    }

    // method untuk mencetak struk
    @Override
    public void Struk() throws Exception {
        Integer totalBayar = hargaBarang * jumlahBarang;
        this.totalBayar = totalBayar;

        // date
        Date date = new Date();
        SimpleDateFormat hari = new SimpleDateFormat("'Hari/Tanggal \t:' EEEEEEEEEE dd-MM-yy");
        SimpleDateFormat jam = new SimpleDateFormat("'Waktu \t\t:' hh:mm:ss z");

        System.out.println("----------- Perlengkapan Olahraga -----------");
        System.out.println(hari.format(date));
        System.out.println(jam.format(date));
        System.out.println("Nota \t        : " + No_Nota);
        System.out.println("====================================");
        System.out.println("---------- BIODATA ----------");
        System.out.println("Nama Pelanggan \t        : " + nama_Pelanggan);
        System.out.println("No HP \t\t: " + noHP_Pelanggan);
        System.out.println("Alamat \t\t: " + alamat_Pelanggan);
        System.out.println("------ DATA PEMBELIAN BARANG -------");
        System.out.println("Jenis \t      : " + jenisBarang);
        System.out.println("Barang \t      : " + namaBarang);
        System.out.println("Harga \t\t  : " + hargaBarang);
        System.out.println("Jumlah \t\t: " + jumlahBarang);
        System.out.println("Total  \t        : " + this.totalBayar);
        System.out.println("------------------------------------");
        System.out.println("Kasir \t\t: Rahmatul Fa Dilla M\n");

        // method string
        System.out.println("toUpperCase\t: " + nama_Pelanggan.toUpperCase());
        System.out.println("length\t\t: " + nama_Pelanggan.length());

        // Call the insertDataNew method to insert data into the database
        insertDataNew();
    }

    public void insertDataNew() {
        try {
            // Explicitly load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
                String sql = "INSERT INTO identitas_barang (No_Nota, nama_Pelanggan, noHP_Pelanggan, alamat_Pelanggan, JenisBarang, namaBarang, hargaBarang, jumlahBarang, totalBayar) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                    pstmt.setString(1, No_Nota);
                    pstmt.setString(2, nama_Pelanggan);
                    pstmt.setString(3, noHP_Pelanggan);
                    pstmt.setString(4, alamat_Pelanggan);
                    pstmt.setString(5, jenisBarang);
                    pstmt.setString(6, namaBarang);
                    pstmt.setInt(7, hargaBarang);
                    pstmt.setInt(8, jumlahBarang);
                    pstmt.setInt(9, totalBayar);

                    int rowsInserted = pstmt.executeUpdate();
                    if (rowsInserted > 0) {
                        System.out.println("\nData inserted successfully into 'identitas_barang' table!");
                    } else {
                        System.out.println("\nFailed to insert data into 'identitas_barang' table.");
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }

    public void tampilkanData() throws Exception {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement()) {

            String sql = "SELECT * FROM identitas_barang";
            rs = stmt.executeQuery(sql);

            System.out.println("+--------------------------------+");
            System.out.println("|    BIODATA PELANGGAN  |");
            System.out.println("+--------------------------------+");

            while (rs.next()) {
                String No_Nota = rs.getString("No_Nota");
                String nama_Pelanggan = rs.getString("nama_Pelanggan");
                String noHP_Pelanggan = rs.getString("noHP_Pelanggan");
                String alamat_Pelanggan = rs.getString("alamat_Pelanggan");
                String namaBarang = rs.getString("JenisBarang");
                String jenisBarang = rs.getString("JenisBarang");
                String hargaBarang = rs.getString("namaBarang");
                String jumlahBarang = rs.getString("jumlahBarang");
                String totalBayar = rs.getString("totalBayar");

                System.out.println(String.format("%s. %s -- %s -- (%s)", No_Nota, nama_Pelanggan, noHP_Pelanggan,
                        alamat_Pelanggan, namaBarang, jenisBarang, hargaBarang, jumlahBarang, totalBayar));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateData() throws Exception {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement()) {

            Scanner scanStr = new Scanner(System.in);
            Scanner scanIn = new Scanner(System.in);

            System.out.print("Masukkan Faktur yang akan diubah = ");
            No_Nota = scanStr.next();
            System.out.print("Masukkan Nama = ");
            nama_Pelanggan = scanStr.next();
            System.out.print("Masukkan No HP = ");
            noHP_Pelanggan = scanStr.next();
            System.out.print("Masukkan Alamat = ");
            alamat_Pelanggan = scanStr.next();
            System.out.print("Masukkan Jenis = ");
            namaBarang = scanStr.next();
            System.out.print("Masukkan Barang = ");
            jenisBarang = scanStr.next();
            System.out.print("Masukkan Harga = ");
            hargaBarang = scanIn.nextInt();
            System.out.print("Masukkan Jumlah = ");
            jumlahBarang = scanIn.nextInt();

            Integer totalBayar = hargaBarang * jumlahBarang;
            this.totalBayar = totalBayar;

            String sql = "UPDATE identitas_barang SET nama_Pelanggan=?, noHp_Pelanggan=?, alamat_Pelanggan=?, JenisBarang=?, namaBarang=?, hargaBarang=?, jumlahBarang=?, totalBayar=? WHERE No_Nota = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nama_Pelanggan);
                pstmt.setString(2, noHP_Pelanggan);
                pstmt.setString(3, alamat_Pelanggan);
                pstmt.setString(4, jenisBarang);
                pstmt.setString(5, namaBarang);
                pstmt.setInt(6, hargaBarang);
                pstmt.setInt(7, jumlahBarang);
                pstmt.setInt(8, totalBayar);
                pstmt.setString(9, No_Nota);

                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteData() throws Exception {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement()) {

            Scanner scannerDel = new Scanner(System.in);
            // ambil input dari user
            System.out.print("Faktur yang mau dihapus : ");
            String no_faktur = (scannerDel.nextLine());

            // buat query hapus
            String sql = "DELETE FROM identitas_barang WHERE No_Nota=?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, no_faktur);
                pstmt.executeUpdate();
            }

            System.out.println("Data telah terhapus...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * public static void main(String[] args) {
     * Pelanggan pelanggan = new Pelanggan();
     * try {
     * pelanggan.isiBiodata();
     * pelanggan.Struk();
     * pelanggan.tampilkanData();
     * pelanggan.updateData();
     * pelanggan.deleteData();
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     */
}