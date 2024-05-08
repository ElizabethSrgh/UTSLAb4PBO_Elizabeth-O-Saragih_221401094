import java.util.ArrayList;
import java.util.Scanner;

public class BankingSystem {
    private ArrayList<BankAccount> accounts = new ArrayList<>();

    public void showMenu() {
        System.out.println("==== Banking System Menu ====");
        System.out.println("1. Registrasi akun baru");
        System.out.println("2. Mengirim uang");
        System.out.println("3. Menyimpan uang");
        System.out.println("4. Mengecek informasi rekening pribadi");
        System.out.println("5. Keluar");
        System.out.println("=============================");
    }

    public void registerAccount(String nama, String alamat, String nomor_telepon, int saldo) {
        BankAccount newAccount = new BankAccount(nama, alamat, nomor_telepon, saldo);
        accounts.add(newAccount);
        System.out.println("Akun berhasil terdaftar!");
    }

    public void transferMoney(String nomorAkunPengirim, String nomorAkunPenerima, int jumlahUang) {
        BankAccount pengirim = findAccountByNumber(nomorAkunPengirim);
        BankAccount penerima = findAccountByNumber(nomorAkunPenerima);

        if (pengirim != null && penerima != null) {
            pengirim.transfer(jumlahUang);
            penerima.topUp(jumlahUang);
            System.out.println("Transfer berhasil!");
        } else {
            System.out.println("Nomor akun tidak ditemukan.");
        }
    }

    public void depositMoney(String nomorAkun, int jumlahUang) {
        BankAccount account = findAccountByNumber(nomorAkun);
        if (account != null) {
            account.topUp(jumlahUang);
            System.out.println("Uang berhasil disimpan.");
        } else {
            System.out.println("Nomor akun tidak ditemukan.");
        }
    }

    public void checkAccountInfo(String nomorAkun) {
        BankAccount account = findAccountByNumber(nomorAkun);
        if (account != null) {
            account.showDescription();
        } else {
            System.out.println("Nomor akun tidak ditemukan.");
        }
    }

    private BankAccount findAccountByNumber(String nomorAkun) {
        for (BankAccount account : accounts) {
            if (account != null && account.getNomor_akun().equals(nomorAkun)) {
                return account;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        BankingSystem bankingSystem = new BankingSystem();
        Scanner scanner = new Scanner(System.in);

        boolean isRunning = true;
        while (isRunning) {
            bankingSystem.showMenu();
            System.out.print("Pilih menu: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Masukkan nama: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan alamat: ");
                    String alamat = scanner.nextLine();
                    System.out.print("Masukkan nomor telepon: ");
                    String nomorTelepon = scanner.nextLine();
                    System.out.print("Masukkan saldo awal: ");
                    int saldo = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    bankingSystem.registerAccount(nama, alamat, nomorTelepon, saldo);
                    break;
                case 2:
                    System.out.print("Masukkan nomor akun pengirim: ");
                    String pengirim = scanner.nextLine();
                    System.out.print("Masukkan nomor akun penerima: ");
                    String penerima = scanner.nextLine();
                    System.out.print("Masukkan jumlah uang yang akan ditransfer: ");
                    int jumlahTransfer = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    bankingSystem.transferMoney(pengirim, penerima, jumlahTransfer);
                    break;
                case 3:
                    System.out.print("Masukkan nomor akun: ");
                    String akun = scanner.nextLine();
                    System.out.print("Masukkan jumlah uang yang akan disimpan: ");
                    int jumlahSimpan = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    bankingSystem.depositMoney(akun, jumlahSimpan);
                    break;
                case 4:
                    System.out.print("Masukkan nomor akun: ");
                    String nomorAkun = scanner.nextLine();
                    bankingSystem.checkAccountInfo(nomorAkun);
                    break;
                case 5:
                    System.out.println("Terima kasih telah menggunakan layanan kami.");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih kembali.");
            }
        }
        scanner.close();
    }
}
