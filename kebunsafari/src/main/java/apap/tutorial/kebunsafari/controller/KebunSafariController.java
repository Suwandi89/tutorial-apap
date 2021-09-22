package apap.tutorial.kebunsafari.controller;

import apap.tutorial.kebunsafari.model.KebunSafariModel;
import apap.tutorial.kebunsafari.service.KebunSafariService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class KebunSafariController {
    @Autowired
    private KebunSafariService kebunSafariService;

    @RequestMapping("/kebun-safari/add")
    public String addKebunSafari(
            @RequestParam (value="id", required = true) String idKebunSafari,
            @RequestParam (value="nama", required = true) String namaKebunSafari,
            @RequestParam (value="alamat", required = true) String alamat,
            @RequestParam (value="noTelepon", required = true) String noTelepon,
            Model model
    ){
        // Membuat objek Kebun Safari baru
        KebunSafariModel kebunSafari = new KebunSafariModel(idKebunSafari, namaKebunSafari, alamat, noTelepon);

        // Memanggil service addKebunSafari
        kebunSafariService.addKebunSafari(kebunSafari);

        // Menambahkan variabel kebunSafari untuk dirender di Thymeleaf
        model.addAttribute("kebunSafari",kebunSafari);

        //Mereturn template html yang dipakai
        return "add-kebun-safari";
    }

    @RequestMapping("/")
    public String listKebunSafari(Model model){
        // Mendapatkan list seluruh objek Kebun Safari
        List<KebunSafariModel> listKebunSafari = kebunSafariService.getKebunSafariList();

        // Menambahkan list untuk dirender di Thymeleaf
        model.addAttribute("listKebunSafari",listKebunSafari);

        // Mereturn template html yang dipakai
        return "get-all-kebun-safari";
    }

    @RequestMapping("/kebun-safari")
    public String getKebunSafariId(@RequestParam(value="id",required = true) String idKebunSafari, Model model){
        // Mendapatkan Objek Kebun Safari sesuai dengan ID
        KebunSafariModel kebunSafari = kebunSafariService.getKebunSafariByIdKebunSafari(idKebunSafari);

        // Menambahkan objek untuk dirender di Thymeleaf
        model.addAttribute("kebunSafari",kebunSafari);

        // Mereturn template html yang dipakai
        return "detail-kebun-safari";
    }

    @GetMapping(value="/kebun-safari/view/{idKebunSafari}")
    public String viewKebun(
            @PathVariable(value = "idKebunSafari", required = true) String idKebunSafari, Model model){
        // Mendapatkan Objek Kebun Safari sesuai dengan ID
        KebunSafariModel kebunSafari = kebunSafariService.getKebunSafariByIdKebunSafari(idKebunSafari);

        // Menambahkan objek untuk dirender di Thymeleaf
        model.addAttribute("kebunSafari",kebunSafari);

        return "detail-kebun-safari";
    }

    @GetMapping("/kebun-safari/update/{idKebunSafari}")
    public String updateKebunSafari(
            @PathVariable (value="idKebunSafari", required = true) String idKebunSafari,
            @RequestParam (value="noTelepon", required = true) String noTelepon,
            Model model
    ){
        // Mendapatkan Objek Kebun Safari sesuai dengan ID
        KebunSafariModel kebunSafari = kebunSafariService.getKebunSafariByIdKebunSafari(idKebunSafari);

        // Memanggil service addKebunSafari
        kebunSafari.setNoTelepon(noTelepon);

        // Menambahkan variabel kebunSafari untuk dirender di Thymeleaf
        model.addAttribute("kebunSafari",kebunSafari);

        //Mereturn template html yang dipakai
        return "update-kebun-safari";
    }

    @GetMapping(value="/kebun-safari/delete/{idKebunSafari}")
    public String deleteKebunSafari(
            @PathVariable(value = "idKebunSafari", required = true) String idKebunSafari, Model model){
        // Mendapatkan list Kebun Safari
        List<KebunSafariModel> listKebun = kebunSafariService.getKebunSafariList();

        // Mendapatkan Objek Kebun Safari sesuai dengan ID
        KebunSafariModel kebunSafari = kebunSafariService.getKebunSafariByIdKebunSafari(idKebunSafari);

        // Menghapus Objek Kebun Safari dari list
        listKebun.remove(kebunSafari);

        return "delete-kebun-safari";
    }

    @RequestMapping(value="/kebun-safari/delete-by-telepon")
    public String deletebyteleponKebunSafari(
            @RequestParam(value="noTelp",required = true) String noTelp, Model model){
        // Mendapatkan list Kebun Safari
        List<KebunSafariModel> listKebun = kebunSafariService.getKebunSafariList();

        //delete element
        List<KebunSafariModel> listKebunbytelp = kebunSafariService.getKebunSafariByTelpKebunSafari(noTelp);

        // Menghapus Objek Kebun Safari dari list
        for (KebunSafariModel e : listKebunbytelp){
            listKebun.remove(e);
        }

        return "delete-kebun-safari";
    }

}
