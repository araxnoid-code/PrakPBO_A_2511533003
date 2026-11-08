package shop.app;

import java.util.List;
import shop.StudentInfo;
import shop.model.DigitalProduct;
import shop.model.PhysicalProduct;
import shop.model.Product;
import shop.notification.CustomerContact;
import shop.notification.EmailChannel;
import shop.notification.NotificationChannel;
import shop.notification.NotificationService;
import shop.notification.SmsChannel;
import shop.notification.WhatsAppChannel;
import shop.payment.BankTransferPayment;
import shop.payment.CreditCardPayment;
import shop.payment.EWalletPayment;
import shop.payment.PaymentMethod;
import shop.service.CheckoutService;
import shop.shipping.ExpressShipping;
import shop.shipping.ShippingService;

public class TugasApp {
    public static void main(String[] args) {

        Product coffee = new PhysicalProduct("KOPI-001", "Kopi Arabika Gayo 250g", 85_000, 300);
        Product rendang = new PhysicalProduct("RDG-002", "Rendang Kemasan 250g", 120_000, 400);
        Product ebook = new DigitalProduct("EBK-003", "E-book Resep Kue Nusantara", 50_000,
        "https://toko-umkm.example/unduh/ebk-003");
        List<Product> cart = List.of(coffee, rendang, ebook);

        System.out.println("Tugas Oleh " + StudentInfo.NAME + " (" + StudentInfo.NIM + ") ");
        System.out.println("== T1: Total dengan biaya admin (Ekspres) ==");
        EWalletPayment eWallet = new EWalletPayment("Dompetku", 268000);
        CreditCardPayment kartuKredit = new CreditCardPayment("Siti Rahma", 1000000);
        BankTransferPayment bankTransfer = new BankTransferPayment("bank nagari");
        PaymentMethod[] payments = {eWallet, kartuKredit, bankTransfer};

        CheckoutService checkout = new CheckoutService();
        ShippingService shipping = new ExpressShipping();

        long total = checkout.total(cart, shipping);
        for (int i = 0; i < 3; i++) {
            PaymentMethod payment = payments[i];
            System.out.println(payment.getName() + ": Rp" + checkout.grandTotal(cart, shipping, payment) + " (admin Rp" + payment.adminFee(total) + ")");
        }

        System.out.println("\nKebijakan kartu kredit: " + kartuKredit.policy());
        System.out.println("Kebijakan e-wallet: " + eWallet.policy());

        System.out.println("== T2: Notifikasi Pesanan ==");
        NotificationService notifServices = new NotificationService();
        notifServices.register(new EmailChannel());
        notifServices.register(new SmsChannel(StudentInfo.SMS_MAX_LENGTH));
        notifServices.register(new WhatsAppChannel());

        notifServices.register(new NotificationChannel() {
            @Override public String channelName() { return "LOG"; }
            @Override public boolean send(CustomerContact contact, String message) {
                if (message.isBlank()) {
                    return false;
                }
                System.out.println("[LOG] " + contact.getName() + ": " + message); return true;
            }
        });

                
        String msg = "Pesanan ORD-0001 sudah dikirim dengan kurir Ekspres. Nomor resi akan muncul di aplikasi dalam 1x24 jam. Terima kasih telah berbelanja produk UMKM Sumatera Barat di toko kami! Silakan hubungi layanan pelanggan jika membutuhkan bantuan terkait pesanan Anda.";

        CustomerContact[] contact = {new CustomerContact("Siti Rahma", "siti@example.com", "081234567890"), new CustomerContact("Budi", "", "+6281298765432")};

        for (int i = 0; i < 2; i++) {
            CustomerContact c = contact[i];
            System.out.println("Terkirim ke " + c.getName() + " lewat " + notifServices.broadcast(c, msg) + " kanal");
        }
    }
}
