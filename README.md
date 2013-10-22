Belajar bikin project akuntansi

## Download ##
Sebelum download contoh program, git sudah harus terinstall di komputer.
jalankan perintah 
      git clone https://github.com/ustadho/akuntansi.git


## Build dan Run ##

Untuk menjalankan projectnya : 

1. Siapkan database Postgresql

    * nama db : akunting_development 
    * username : test 
    * password : test 

2. Jalankan mvn clean install di top level folder
3. Masuk ke folder web, kemudian jalankan mvn jetty:run
4. Siap dibrowse di http://localhost:10000
5. Login dengan username : admin, password: admin

