/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.b.project04.restful;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author T.U.F GAMING
 */

@RestController
public class ProductServiceController {
    private static Map<String, Product> productRepo = new HashMap<>();
    static {
        Product honey = new Product(); //membuat product variable honey
        honey.setId("1");
        honey.setName("Honey");
        honey.setNumber(3);
        honey.setPrice(10000);
        productRepo.put(honey.getId(), honey); //mengisi repo dengan product Honey
        
        Product almond = new Product(); //membuat product variable almond
        almond.setId("2");
        almond.setName("Almond");
        almond.setNumber(2);
        almond.setPrice(15000);
        productRepo.put(almond.getId(), almond); //mengisi repo dengan product Honey
    }
    @RequestMapping(value = "/products") //mengatur request mapping dan menentukan valuenya, disini kita menggunakan /product
    public ResponseEntity<Object> getProduct(){ //mendekalarasikan variable dengan tipe data ResponseEntity
        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK); //berguna untuk memaanggil data yang terdapat pada productRepo
    }
    @RequestMapping(value = "/products", method = RequestMethod.POST) //mengatur request mapping dan valuenya, disini kita menammbahkann method POST
    public ResponseEntity<Object> createProduct(@RequestBody Product product){ //mendekalarasikan variable dengan tipe data ResponseEntity, disini terdapat pemanggilan Product juga untuk menambahkan data ke dalam data product
        if(productRepo.containsKey(product.getId())){ //penggunaan if untuk menghindari duplikasi data yang di input berdasarkan product id dengan cara mengecheck apakah terdapat id yang sama pada data
            return new ResponseEntity<>("Product already exist", HttpStatus.NOT_FOUND); //validasi jika terdapat duplikasi id pada data akan mengirimkan pesan
        } 
        productRepo.put(product.getId(), product); //berguna untuk memasukan id yang diinput ke dalam data product
        return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED); //membuat data baru ke dalam data product dan mengirim pesan berhasil, jika data valid
    }
    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT) //mengatur request mapping dan menentukan path untuk method post
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product){ //mendekalarasikan variable dengan tipe data ResponseEntity. disini kita menggunakan path dan string id yang diambil dari data product
        if(!productRepo.containsKey(id)){ //penggunaan if untuk mengecheck id yang terdapat pada data product
            return new ResponseEntity<>("Product update failed", HttpStatus.NOT_FOUND); //validasi jika id data yang ingin diupdate tidak terdapat pada data product
        } 
        productRepo.remove(id); //untuk menghapus id pada data product
        product.setId(id); //menambahkan kembali id yang baru saja telah diupdate
        productRepo.put(id, product);
        return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);//mengupdate data dan mengrim pesan berhasil jika data berhasil diupdate
    }
    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE) //mengatur request mapping dan menentukan path untuk method delete
    public ResponseEntity<Object> delete(@PathVariable("id")String id){ //mendekalarasikan variable dengan tipe data ResponseEntity dan mengambil path yang ada dengan string id
        if(!productRepo.containsKey(id)){ //penggunaan if untuk mengecheck id yang terdapat pada repo
            return new ResponseEntity<>("Delete failed : the data doesn't exist", HttpStatus.NOT_FOUND); //validasi jika path id yang ingin dihapus tidak ada, akan mengirim pesan gagal
        } 
        productRepo.remove(id); //berguna untuk menghapus id pada repo
        return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK); //menghapus pesan dan memberikan pesan berhasil jika data berhasil dihapus
    }
}
