# Tutorial APAP
## Authors
* **Suwandi Kurniawan** - *1906299175* - *A*
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