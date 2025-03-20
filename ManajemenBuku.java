package studikasus1;

//mengimpor kelas-kelas yang diperlukan untuk I/O
/*Untuk membaca dan menulis file 
 * menyimpan daftar buku
 * menerima input dari pengguna*/
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//mendefinisikan class, untuk menambah, menyimpan, menampilkan dan membaca data buku.
public class ManajemenBuku {

    //NAMA_FILE digunakan untuk menyimpan nama file yaitu dataBuku.txt, dibuat final agar nama file tidak bisa berubah selama program berjalan.
    private static final String NAMA_FILE = "dataBuku.txt";
    //daftarBuku adalah list yang digunakan untuk menyimpan semua Buku yang dimasukan oleh pengguna. 
    private static ArrayList<Buku> daftarBuku = new ArrayList<>();

    //Method yang akan dieksekusi pertama
    public static void main(String[] args) {

        //untuk memastikan data buku yang sudah disimpan dapat diakses.
        bacaDatadiFile();
        //membuat objek scanner untuk menerima input pengguna.
        Scanner in = new Scanner(System.in);
        int pilihan; 

        //do-while digunakan untuk memastikan program terus berjalan sampai pengguna memilih opsi keluar.
        do{
            System.out.println("\n Menu: ");
            System.out.println("1. Tambah Buku.");
            System.out.println("2. Tampilkan Daftar Buku.");
            System.out.println("3. Keluar.");
            System.out.print("Opsi: ");
            pilihan = in.nextInt();
            in.nextLine();

            //switch-case digunakan untuk menangani opsi yang dipilih oleh pengguna. Memproses pilihan berdasarkan input pengguna.
            switch (pilihan) {
                case 1:
                    tambahBuku(in);
                    break;

                case 2:
                    infoDaftarBuku(in);
                    break;
                
                case 3:
                    simpanDataBuku();
                    System.out.println("Terima Kasih!");
                    break;
            
                default:
                    System.out.println("Pilihan tidak valid, silahkan masukan angka sesuai pilihan!");
                    break;
            }

        }while ( pilihan != 3 );

        //menutup objek scanner
        in.close();
               
    }

    //menambah buku baru kedalam daftarBuku. Untuk mengumpulkan dan menyimpan informasi buku dari pengguna.
    private static void tambahBuku(Scanner in){

        //meminta pengguna memasukan detail buku 
        System.out.println("\n Masukan detail buku: ");
        System.out.print("Judul: ");
        String judul = in.nextLine();
        System.out.print("Penulis: ");
        String penulis = in.nextLine();
        System.out.print("Tahun terbit: ");
        int tahunTerbit = in.nextInt();
        in.nextLine();

        //membuat objek buku baru dan menambahkannya ke dalam daftar buku
        Buku buku = new Buku(judul, penulis, tahunTerbit);
        daftarBuku.add(buku);
        System.out.println("Buku berhasil ditambahkan!");

    }

    //menampilkan daftar buku berdasarkan pilihan pengguna
    private static void infoDaftarBuku(Scanner in){

        //untuk memeriksa apakah daftar buku kosong.
        if(daftarBuku.isEmpty()){
            System.out.println("Daftar buku kosong!");
            return;
        }

        //memberikan opsi pada pengguna untuk informasi yang ringkas atau lengkap.
        System.out.println("\n Pilih jenis tambilan daftar buku: ");
        System.out.println("1. Ringkas.");
        System.out.println("2. Detail.");
        System.out.print("Opsi: ");
        int pilih = in.nextInt();
        in.nextLine();

        //menampilkan daftar buku berdasarkan pilihan pengguna.
        for(Buku buku : daftarBuku){
            if(pilih == 1){
                buku.infoBuku();
            }else if(pilih == 2){
                buku.infoBuku(true);
            }else{
                System.out.println("Pilihan tidak valid, silahkan masukan angka sesuai pilihan! ");
                break;
            }
            
        }


    }

    //menyimpan data buku dari daftarBuku ke file dataBuku.txt.
    private static void simpanDataBuku(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(NAMA_FILE))){    //membuka file untuk menulis data buku kedalamnya.
            for (Buku buku : daftarBuku){   //loop untuk menulis data setiap buku kedalam file.
                writer.write(buku.toString());   //agar dapat dibaca kembali.
                writer.newLine();
            }
        }catch (IOException e){    //menangani kesalahan yang mungkin terjadi saat program melakuakn proses I/O
            System.out.println("Terjadi kesalahan dalam menyimpan data buku: " + e.getMessage());
        }
    }

    //memastikan data buku yang sudah disimpan sebelumnya dapat diakses saat program dijalankan.
    private static void bacaDatadiFile(){
        try(BufferedReader reader = new BufferedReader(new FileReader(NAMA_FILE))){  // membukan file dan membaca isinya.
            String line;    //mendeklarasikan variable line untuk menyimpan setiap baris yang dibaca dari file.
            while ((line = reader.readLine()) != null) {    //loop while digunakan untuk membaca seluruh file.
                String[] data = line.split(", ");    //memisahkan baris yang dibaca menjadi beberapa bagian dengan tanda koma.
                if(data.length == 3){       //memastikan array data memiliki 3 elemen. 
                    String judul = data[0];    //elemen pertama dari array data yang berisi judul.
                    String penulis = data[1];  //elemen kedua dari data array yang menyimpan nama penulis.
                    int tahunTerbit = Integer.parseInt(data[2]);     //elemen ketiga dari data array yang menyimpan tahun terbit buku.
                    daftarBuku.add(new Buku(judul, penulis, tahunTerbit));   //menyimpan data buku yang telah dibaca dari file ke dalam daftar buku yang dapat digunakan oleh program.
                }
            }
        }catch(IOException e){     //menangani kesalahan yang mungkin terjadi saat program melakuakn proses I/O
            System.out.println("Terjadi kesalahan saat membaca data buku: " + e.getMessage());
        }
    }
}