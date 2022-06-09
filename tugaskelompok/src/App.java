import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        //1. TRANFER DARI SUSI KE BUDI
        //2. CETAK LOG MUTASI
        //3. KELUAR
        Nasabah.cetakNomorRekening();
        ArrayList<Nasabah> nasabah = new ArrayList<Nasabah>();
        nasabah.add(new Nasabah("0214578","Susi"));
        nasabah.add(new Nasabah("0214577","Budi"));

        String norek ="0214578";

        //Nasabah susi = new Nasabah("0214578","Susi");
        // susi.Nama="Susi";
        // susi.NoRek="0214578";

        //System.out.println(susi);
       //utilitas 
        //Nasabah budi = new Nasabah();
        // budi.Nama="Budi";
        // budi.NoRek="0214577";
       // Nasabah handoko = new Nasabah();
        // handoko.Nama="handoko";
        // handoko.NoRek="1234577";

        // System.out.println(susi.getNama() + " " + susi.getNoRek());
        // System.out.println(budi.getNama() + " " + budi.getNoRek());
        // System.out.println(handoko.getNama() + " " + handoko.getNoRek());
        ArrayList<String> logMutasiSusi = new ArrayList<String>();
        ArrayList<String> logMutasiBudi = new ArrayList<String>();
        int pilihan;
        String yn="y";
        Scanner input = new Scanner(System.in);
        //perulangan untuk menu
        do{
            //cetak menu
            cetakMenu();
            pilihan = input.nextInt();

            //percabangan
            if(pilihan==1){
                String NoRekTujuan="";
                String NoRekAsal="";
                int nominal;
                input.nextLine();
                //masukkan nomor rekening Asal 
                System.out.print("Masukkan Nomor Rekening Asal :");
                NoRekAsal = input.nextLine();
                //transfer
                //1. masukkan no rek
                System.out.print("Masukkan Nomor Rekening Tujuan :");
                NoRekTujuan = input.nextLine();
                //2. masukkan nominal transfer
                System.out.print("Masukkan Nominal Transfer : ");
                nominal = input.nextInt();
                Nasabah rekAsal, rekTujuan;

                //cek no rek asal
                if(cariNoRek(NoRekAsal, nasabah) >=0){
                    rekAsal = nasabah.get(cariNoRek(NoRekAsal, nasabah));
                }else{
                    System.out.print("Nomor Rek. Tidak Di temukan");
                    continue;
                }


                //3. cek apakah no rek ada/cocok/sesuai
                if(cariNoRek(NoRekTujuan, nasabah) >=0){
                    rekTujuan = nasabah.get(cariNoRek(NoRekTujuan, nasabah));
                        //4. cek apakah no rek 1 punya uang yang cukup
                    if((rekAsal.getSaldo() -100000)>nominal){
                             //jika semua kondisi benar, transfer uang, tulis log mutasi
                             //Pseudocode
                             rekAsal.tambahSaldo(nominal*-1);
                             int tmp = nominal;
                             rekTujuan.tambahSaldo(tmp);
                             System.out.println("Transfer Telah Berhasil, sisa saldo "+ rekAsal.getSaldo());
                             //catat log mutasi
                             logMutasiSusi.add("Transfer ke Rek "+ rekAsal.getNoRek() + " Sebesar "+ nominal);
                             logMutasiBudi.add("Kredit dari Rek "+ rekTujuan.getNoRek() + " Sebesar "+ nominal);
                        //simpan data ke arraylist
                        //cari index rek tujuan cariNoRek(NoRekTujuan, nasabah)
                        
                        nasabah.set(cariNoRek(NoRekAsal, nasabah),rekAsal);
                        nasabah.set(cariNoRek(NoRekTujuan, nasabah),rekTujuan);
                        System.out.println("Transfer Sukses");
                    } 
                }
            }
            else if(pilihan==2){
                //cetak mutasi
                for (String mutasi : logMutasiSusi) {
                    System.out.println(mutasi);
                }
            }
            else if(pilihan==3){
                for (Nasabah nsb : nasabah) {
                    System.out.println(nsb);
                }
            }
            else if(pilihan==4) break;
            else continue;
        }while(yn.equalsIgnoreCase("y"));

    }
    /*Cetak Menu Program */
    public static void cetakMenu(){
        System.out.println("Bank ABCD");
        System.out.println("1. TRANFER DARI SUSI KE BUDI");
        System.out.println("2. CETAK LOG MUTASI");
        System.out.println("3. Print Data Seua Nasabah");
        System.out.println("4. KELUAR");
        System.out.print("Masukkan pilihan anda ... ");
       
    }
    /*Cetak Menu Program test non static */
    public  void cetakMenu2(){
        System.out.println("Bank ABCD");
        System.out.println("1. TRANFER DARI SUSI KE BUDI");
        System.out.println("2. CETAK LOG MUTASI");
        System.out.println("3. KELUAR");
        System.out.print("Masukkan pilihan anda ... ");
        cetakMenu();
    }

    public static int cariNoRek(String norek,ArrayList<Nasabah> nasabah){
        int idx =0;
        for (Nasabah nsb : nasabah) {
            if(nsb.getNoRek().equalsIgnoreCase(norek)){
              //  System.out.print("Nomor Rek Di temukan pada index ke "+ idx);
                return idx;
            }
            idx+=1;
        }
        return -1;
    }
}
