package studikasus1;

//mendefinisikan class 
public class Buku {

    //Atributte dibuat private agar data buku hanya dapat diakses oleh class buku dan mencegah perubahan yang tidak diinginkan oleh kode diluar kelas.
    private String judul;
    private String penulis;
    private int tahunTerbit;

    //constructor buku, digunakan untuk membuat objek buku dengan menginisialisasi judul, penulis dan tahun terbit buku.
    public Buku(String judul, String penulis, int tahunTerbit){
        this.judul = judul;
        this.penulis = penulis;
        this.tahunTerbit = tahunTerbit;
    }

    //method info buku, untuk menampilkan detail informasi buku.
    public void infoBuku(boolean detail){
        //penggunaan if untuk memberikan fleksibilitas dalam menampilkan informasi buku, jika detail bersifat true maka menampilkan secara detail jika false menampilkan judul saja. 
        if(detail){
            System.out.println("Judul buku: " + judul);
            System.out.println("Penulis: " + penulis);
            System.out.println("Tahun terbit: " + tahunTerbit);
        }else{
            infoBuku();
        }
    }

    //method yang digunakan untuk informasi ringkas buku yang hanya berisi judul. 
    public void infoBuku(){
        System.out.println("Judul buku: " + judul);
    }

    //method yang digunakan untuk menyimpan data buku dalam file.
    public String toString() {
        return  judul + ", " + penulis + ", " + tahunTerbit;
    }
}
