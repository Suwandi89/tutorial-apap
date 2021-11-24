# Tutorial APAP
## Authors
* **Suwandi Kurniawan** - *1906299175* - *A*
---
## Tutorial 6
### What I have learned today
Saya belajar pentingnya WebSecurity pada pembuatan web karena dapat menjaga keamanan akses pada website, pada lab ini saya belajar membuat User yang memiliki data Username serta Password yang memiliki patern.
### Pertanyaan
1. Jelaskan secara singkat perbedaan Otentikasi dan Otorisasi! Di bagian mana (dalam kode yang telah anda buat) konsep tersebut diimplementasi?
> Otentikasi merupakan proses verifikasi detail pengguna untuk mengidentifikasinya dan memberikan akses ke sistem. Otorisasi merupakan proses memeriksa hak atau izin pengguna yang diotentikasi untuk mendapatkan akses sistem bagian spesifik. Berikut merupakan contoh implementasi otentikasi pada class WebSecurityConfig: 
```
@Autowired
public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
    auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
}
```
Berikut merupakan contoh implementasi otorisasi pada class WebSecurityConfig:
```
@Override
protected void configure(HttpSecurity http) throws Exception {
    http
            .authorizeRequests()
            .antMatchers("/css/**").permitAll()
            .antMatchers("/js/**").permitAll()
            .antMatchers("/user/viewall","/user/add","/user/delete/**").hasAuthority("Admin")
            .antMatchers("/menu/add").hasAuthority("Manajer")
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login").permitAll()
            .and()
            .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/login").permitAll();
}
```
2. Apa itu BCryptPasswordEncoder? Jelaskan secara singkat cara kerja dan tujuannya
> BCryptPasswordEncoder merupakan fungsi hashing yang digunakan untuk mengenkripsi password sehingga tidak diketahui password aslinya walaupun sudah dilihat di database. BCryptPasswordEncoder melakukan encrypt Raw String menjadi String yang sudah diproses dengan algoritma agar tidak mudah diketahui.
3. Apakah penyimpanan password sebaiknya menggunakan encryption atau hashing? Mengapa demikian?
> Penyimpanan password sebaiknya menggunakan encryption atau hashing karena untuk tujuan security sehingga meskipun ada yang mengakses database dan melihat password user namun yang sebenarnya terlihat adalah password yangs udah dilakukan encryption atau hashing.
4. Jelaskan secara singkat apa itu UUID beserta penggunaannya!
> UUID adalah 128-bit angka yang digunakan untuk mengidentifikasi secara unik beberapa objek atau entitas di sistem komputer. UUID digunakan untuk meningkatkan keamanan data pengguna dikarenakan id pengguna akan digenerate secara unik dengan hashing sebanyak 32 karakter secara acak sehingga id pengguna aman dan tidak mudah untuk diretas.
5. Apa kegunaan class UserDetailsServiceImpl.java? Mengapa harus ada class tersebut padahal kita sudah memiliki class UserRoleServiceImpl.java?
> UserDetailsService adalah interface yang saya import dari Spring dalam kerangka kerja Spring Security, yang digunakan untuk mengambil informasi otentikasi dan otorisasi pengguna. Kegunaan class UserDetailsServiceImpl.java adalah implementasi yang digunakan atas UserDetailsService agar dapat menerapkan otentikasi dan otorisasi secara khusus sesuai dengan requirement yang diinginkan.
### What I did not understand
- [ ] Saya masih sedikit bingung terkait syntax-syntax yang digunakan pada pemanfaatan otentikasi dan otorisasi.

---
## Tutorial 5
### What I have learned today
Saya belajar hal baru pada lab kali ini yaitu beberapa dasar-dasar pembuatan API serta pemanfaatannya dibantu dengan aplikasi Postman, kemudian saya juga membuat class baru seperti CabangRestController, CabangRestService, dll. Tentunya semua ini sengat menarik dan menambah ilmu lebih bagi saya.
### Pertanyaan
1. Apa itu Postman? Apa kegunaannya?
> Postman adalah sebuah aplikasi REST Client yang dapat kita gunakan untuk mengetes REST API yang sudah dibuat sebelumnya.
2. Jelaskan fungsi dari anotasi @JsonIgnoreProperties dan @JsonProperty.
> @JsonIgnoreProperties berfungsi untuk mengabaikan pemrosesan komponen JSON yang diterima ketika proses serialisasi dan deserialisasi JSON. Sedangkan @JsonProperty berfungsi untuk melakukan pemetaan nama dari properti beserta dengan Key JSON ketika proses serialisasi dan deserialisasi.
3. Apa kegunaan atribut WebClient?
> Kegunaan atribut WebClient adalah untuk mengirim serta menerima data dari resource URi dan memanggil REST services.
4. Apa itu ResponseEntity dan BindingResult? Apa kegunaannya?
> ResponseEntity merupakan suatu perwakilan dari seluruh respons HTTP yang berguna untuk mengikonfigurasi response HTTP secara keseluruhan. Sedangkan BindingResult berisi informasi terkait validasi serta informasi tentang kesalahan yang mungkin terjadi. BindingResult ini dapat digunakan untuk mengecek kesalahan kita saat pemanggilan method.

### What I did not understand
- [ ] Saya masih sedikit bingung dengan pemanfaatan yang lebih efektif dari json yang didapat dengan mengakses API.

---
## Tutorial 4
### What I have learned today
Saya belajar lebih lanjut mengenai thymeleaf, saya belajar menggunakan komponen yang dapat direuse pada setiap page seperti contoh navbar yang diletakkan dalam fragments, saya juga belajar merapikan tata letak komponen seperti merapikan item ke dalam tabel. Lab ini cukup seru namun pada implementasinya beberapa ternyata cukup sulit terutama add menu pada form add cabang.
### Pertanyaan
1. Jelaskan perbedaan th:include dan th:replace!
> th:include berati memasukkan konten yang ada di fragments kedalam tag dari hostnya. Sedangkan th:replace berfungsi untuk mengganti tag host dengan fragmen yang berarti menghapus tag host dan sebagai pengganti tag host itu akan menambahkan fragmen yang ditentukan termasuk tag fragmen.
2. Jelaskan apa fungsi dari th:object!
> th:object digunakan untuk mensubmit form sehingga dapat mengetahui object apa yang di submit. th:object juga digunakan untuk menentukan object mana yang akan diisi kemudian akan ditangkap oleh controller.
3. Jelaskan perbedaan dari * dan $ pada saat penggunaan th:object! Kapan harus dipakai?
> * akan mengecek atribut atau varible dari th:object yang sudah di deklarasi sebelumnya, sedangkan $ akan mengecek keseluruhan atribut atau variabel pada th:object atau juga yang dipass dari controller.

### What I did not understand
- [ ] Saya bingung dengan implementasi fitur tambah row dan delete row pada form add cabang.

---
## Tutorial 3
### What I have learned today
Saya belajar terkait database dan relasi database dengan konsep CRUD melalui Spring serta pemanfaatan JPARepository untuk melakukan query pada basis data.
### Pertanyaan
1. Tolong jelaskan secara singkat apa kegunaan dari anotasi-anotasi yang ada pada model (@AllArgsConstructor, @NoArgsConstructor, @Setter, @Getter, @Entity, @Table)
> @AllArgsConstructor digunakan untuk membuat constructor secara otomatis pada model yang memiliki parameter, @NoArgsConstructor digunakan untuk membuat constructor secara otomatis pada model yang tidak memiliki parameter, @Setter digunakan untuk menambahkan method setter kepada semua atribut kelas, @Getter digunakan untuk menambahkan method getter kepada semua atribut kelas, @Entity digunakan untuk menyatakan kelas dapat dilakukan mapping ke tabel, @Table digunakan untuk melakukan rename terhadap nama tabel yang ingin digunakan agar tidak mewarisi dari nama kelasnya.
2. Pada class CabangDB, terdapat method findByNoCabang, apakah kegunaan dari method tersebut?
> Method findByNoCabang digunakan untuk mencari cabang berdasarkan noCabang yang dipass dari parameter.
3. Jelaskan perbedaan kegunaan dari anotasi @JoinTable dan @JoinColumn 
> Anotasi @JoinTable digunakan untuk membuat tabel baru berdasarkan 2 tabel yang ingin digabungkan dengan foreign key yang saling terhubung, @JoinColumn digunakan untuk menandai kolom sebagai kolom gabungan untuk asosiasi atau koleksi elemen.
4. Pada class PegawaiModel, digunakan anotasi @JoinColumn pada atribut cabang, apa kegunaan dari name, referencedColumnName, dan nullable dalam anotasi tersebut? dan apa perbedaan nullable dan penggunaan anotasi @NotNull
> Kegunaan dari name adalah untuk membuat nama foreign keynya, referencedColumnName untuk menentukan atribut tujuan foreign keynya, dan nullable digunakan untuk menyatakan tipe dari foreign keynya yang boleh null atau tidak. Kemudian perbedaan dari nullable dan @Notnull adalah pada @Notnull untuk atribut biasa, sedangkan nullable digunakan untuk foreign key.
5. Jelaskan kegunaan FetchType.LAZY, CascadeType.ALL, dan FetchType.EAGER
- FetchType.LAZY digunakan memberi tahu Hibernate untuk tidak melakukan load untuk semua collection objek child tetapi hanya mengambil entitas terkait yang dibutuhkan dari database saat menggunakan relasi, umumnya digunakan untuk relasi One to Many. 
- CascadeType.ALL digunakan untuk menyebarkan semua operasi termasuk yang khusus dari Hibernate dari induk ke entitas anak.
- FetchType.EAGER digunakan untuk memberi tahu Hibernate sehingga segera mendapatkan semua elemen/koleksi relasi saat induknya diambil. Umumnya digunakan untuk relasi Many to One.

### What I did not understand

---
## Tutorial 2
### What I have learned today
Saya belajar lebih dalam terkait cara membuat aplikasi web Springboot dengan model, service, controller. Dan saya belajar membuat method pada Controller untuk mengambil data serta manipulasi data seperti melakukan update dan delete juga (dengan PathVariable, RequestParam). 
### Pertanyaan
1. Cobalah untuk menambahkan sebuah Kebun dengan mengakses link berikut setelah menjalankan program: http://localhost:8080/kebun-safari/add?id=1&nama=Papa%20APAP&alamat=Maung%20Fasilkom&noTelepon=081xxx Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi
> Akan muncul notice Whitelabel Error Page karena HTML yang ingin dirender dengan memanggil URl tersebut yang seharusnya terdapat pada folder template belum dibuat sehingga tidak dapat menampilkan hasil.
2. Menurut kamu anotasi @Autowired pada class Controller tersebut merupakan implementasi dari konsep apa? Dan jelaskan secara singkat cara kerja @Autowired tersebut dalam konteks service dan controller yang telah kamu buat 
> @Autowired pada class controller yang telah saya buat tersebut merupakan implementasi konsep dependency injection. Cara kerjanya adalah dependency dari interfacenya diinject otomatis dengan autowiring ke service yang mengimplementasi autowiring ini yang ditandai dengan @Autowired.
3. Cobalah untuk menambahkan sebuah Kebun dengan mengakses link berikut: http://localhost:8080/kebun-safari/add?id=1&nama=Papa%20APAP&alamat=Maung%20Fasilkom Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi.
> Terjadi error karena pada URl tersebut tidak terdapat parameter nomor telepon yang pada Controllernya sudah ditentukan required sehingga ketika mencoba akses URl tersebut tidak akan bisa menambahkan KebunSafari baru.
4. Jika Papa APAP ingin melihat Kebun Safari dengan nama Papa APAP, link apa yang harus diakses?
> Jika Papa APAP ingin melihatnya perlu mengakses link http://localhost:8080/kebun-safari/view/1 atau http://localhost:8080/kebun-safari?id=1 karena kita sudah menambahkan data KebunSafari dengan nama Papa APAP pada ID 1.
5. Tambahkan 1 contoh Kebun Safari lainnya sesukamu. Lalu cobalah untuk mengakses http://localhost:8080/ , apa yang akan ditampilkan? Sertakan juga bukti screenshotmu.
> Setelah saya tambahkan KebunSafari lain kemudian saya mencoba mengakses link http://localhost:8080/ yang kemudian akan muncul adalah list KebunSafari yang sudah berhasil ditambahkan. Screenshot dapat diakses melalui link berikut ini https://drive.google.com/file/d/1sJKqu1rR_fvEya-Sb8eWJLQklCKX2jxv/view?usp=sharing.

### What I did not understand
- [ ] Saya masih bingung dengan konsep @Autowired dan implementasi pada kondisi seperti apa saja yang cocok

---
## Tutorial 1
### What I have learned today
Saya belajar cara membuat aplikasi web Springboot dengan konsep MVP baik dari syntax yang digunakan seperti pada html Thymeleaf, hingga cara menggunakan git, dan cara memberikan commit message yang baik.
### Github
1. Apa itu Issue Tracker? Apa saja masalah yang dapat diselesaikan dengan Issue Tracker?
> Alat yang digunakan untuk melakukan pelacakan bug dan permintaan fitur selama pengembangan produk. Masalah yang dapat diselesaikan berupa masalah yang terdapat pada alur aplikasi, pelacakan bug, dll.
2. Apa perbedaan dari git merge dan git merge --squash?
> Perbedaannya adalah jika kita menggunakan git merge --squash yang terjadi adalah commitnya menjadi 1 pada target branch. Tetapi pada git merge akan tetap menampilkan seluruh riwayat commit yang pernah dilakukan pada branch awal.
3. Apa keunggulan menggunakan Version Control System seperti Git dalam pengembangan suatu aplikasi?
> Pertama, kita dapat dengan mudah bekerja sama dalam tim untuk membuat sebuah produk karena dapat saling mengontrol perkembangan pembuatannya melalui fitur-fitur seperti branching dan merging, lalu yang kedua adalah kita dapat kembali ke versi yang sebelumnya jika terdapat masalah pada commit terbaru.
### Spring
4. Apa itu library & dependency?
> Library adalah sebuah koleksi fungsi yang dapat dipakai pada aplikasi dan nantinya fungsi-fungsi tersebut dapat digunakan juga pada aplikasi-aplikasi lainnya. Sedangkan dependency adalah hubungan antara kode ataupun kelas yang melakukan fungsinya masing-masing. 
5. Apa itu Maven? Mengapa kita menggunakan Maven? Apakah ada alternatif dari Maven?
> Maven merupakan sebuah software manajemen proyek yang menggunakan konsep Project Object Model (POM) untuk mengembangkan proyek yang berbasis java. Kita menggunakan Maven karena cukup populer di kalangan developer, terlebih lagi Maven menggunakan basis Java yang tentunya populer juga serta mudah dipahami. Alternatif lain dari Maven adalah Gradle, CMake, dll. 
6. Selain untuk pengembangan web, apa saja yang bisa dikembangkan dengan Springframework?
> Selain untuk mengembangkan web, Spring dapat digunakan untuk membuat REST API, aplikasi android, dll.
7. Apa perbedaan dari @RequestParam dan @PathVariable? Kapan sebaiknya menggunakan @RequestParam atau @PathVariable?
> Perbedaan utama @RequestParam dan @PathVariable adalah @RequestParam digunakan untuk mengakses value dari query string, sedangkan @PathVariable mengambil nilai dari URl path. Oleh karena itu @RequestParam sebaiknya digunakan pada aplikasi web tradisional dimana data disimpan melalui query, sedangkan @PathVariable digunakan pada aplikasi web yang menyimpan dan mengakses data dari URL dengan values tanpa query. 
### What I did not understand
- [ ] Seberapa aman menggunakan @RequestParam dan @PathVariable dalam melakukan passing value?