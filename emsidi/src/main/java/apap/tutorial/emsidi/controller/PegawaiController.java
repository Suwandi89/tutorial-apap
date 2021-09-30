package apap.tutorial.emsidi.controller;

import apap.tutorial.emsidi.model.CabangModel;
import apap.tutorial.emsidi.model.PegawaiModel;
import apap.tutorial.emsidi.service.CabangService;
import apap.tutorial.emsidi.service.PegawaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalTime;

@Controller
public class PegawaiController {
    @Qualifier("pegawaiServiceImpl")
    @Autowired
    PegawaiService pegawaiService;

    @Qualifier("cabangServiceImpl")
    @Autowired
    CabangService cabangService;

    @GetMapping("/pegawai/add/{noCabang}")
    public String addPegawaiForm(@PathVariable Long noCabang, Model model) {
        PegawaiModel pegawai = new PegawaiModel();
        CabangModel cabang = cabangService.getCabangByNoCabang(noCabang);
        pegawai.setCabang(cabang);
        model.addAttribute("noCabang", noCabang);
        model.addAttribute("pegawai", pegawai);
        return "form-add-pegawai";
    }

    @PostMapping("/pegawai/add")
    public String addPegawaiSubmit(
            @ModelAttribute PegawaiModel pegawai,
            Model model
    ) {
        pegawaiService.addPegawai(pegawai);
        model.addAttribute("noCabang", pegawai.getCabang().getNoCabang());
        model.addAttribute("namaPegawai", pegawai.getNamaPegawai());
        return "add-pegawai";
    }

    @GetMapping("/pegawai/update/{noPegawai}/{noCabang}")
    public String updatePegawaiForm(@PathVariable Long noPegawai, @PathVariable Long noCabang, Model model) {
        PegawaiModel pegawai = pegawaiService.getPegawaiById(noPegawai);
        boolean statusTutup = cabangService.isTutup(noCabang);
        if (statusTutup){
            model.addAttribute("pegawai", pegawai);
            return "form-update-pegawai";
        } else {
            model.addAttribute("statusTutup",statusTutup);
            return "update-pegawai";
        }
    }

    @PostMapping("/pegawai/update")
    public String updatePegawaiSubmit(
            @ModelAttribute PegawaiModel pegawai,
            Model model
    ) {
        pegawaiService.updatePegawai(pegawai);
        boolean statusTutup = true;
        CabangModel cabang = pegawai.getCabang();
        Long noCabang = cabang.getNoCabang();
        model.addAttribute("noCabang",noCabang);
        model.addAttribute("statusTutup",statusTutup);
        model.addAttribute("noPegawai", pegawai.getNoPegawai());
        return "update-pegawai";
    }

    @PostMapping("/pegawai/delete")
    public String deletePegawaiSubmit(
            @ModelAttribute CabangModel cabang,
            Model model
    ) {
        boolean statusTutup = cabangService.isTutup(cabang.getNoCabang());
        if (statusTutup){
            for (PegawaiModel pegawai : cabang.getListPegawai()) {
                pegawaiService.deletePegawaiById(pegawai.getNoPegawai());
            }
        }
        model.addAttribute("noCabang",cabang.getNoCabang());
        model.addAttribute("statusTutup",statusTutup);
        return "delete-pegawai";
    }
}