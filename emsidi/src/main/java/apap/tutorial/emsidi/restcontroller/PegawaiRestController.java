package apap.tutorial.emsidi.restcontroller;

import apap.tutorial.emsidi.model.CabangModel;
import apap.tutorial.emsidi.model.PegawaiModel;
import apap.tutorial.emsidi.rest.Setting;
import apap.tutorial.emsidi.service.CabangRestService;
import apap.tutorial.emsidi.service.CabangService;
import apap.tutorial.emsidi.service.PegawaiRestService;
import apap.tutorial.emsidi.service.PegawaiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.NoSuchElementException;
import java.util.List;
import java.time.LocalTime;
import java.util.HashMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/api/v1")
public class PegawaiRestController {
    @Autowired
    private PegawaiRestService pegawaiRestService;

    @Autowired
    private CabangRestService cabangRestService;

    @PostMapping(value = "/pegawai")
    private PegawaiModel createPegawai(@Valid @RequestBody PegawaiModel pegawai, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field."
            );
        } else {
            return pegawaiRestService.createPegawai(pegawai);
        }
    }

    @GetMapping(value = "/pegawai/{noPegawai}")
    private PegawaiModel retrievePegawai(@PathVariable("noPegawai") Long noPegawai) {
        try {
            return pegawaiRestService.getPegawaiByNoPegawai(noPegawai);
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "ID Pegawai " + String.valueOf(noPegawai) + " Not Found."
            );
        }
    }

    @DeleteMapping(value = "pegawai/{noPegawai}")
    private ResponseEntity deletePegawai(@PathVariable("noPegawai") Long noPegawai){
        try {
            pegawaiRestService.deletePegawai(noPegawai);
            return ResponseEntity.ok("Pegawai with ID " + String.valueOf(noPegawai) + " Deleted!");
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Pegawai with ID " + String.valueOf(noPegawai + " Not Found!")
            );
        } catch (UnsupportedOperationException e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Cabang is still open!"
            );
        }
    }

    @PutMapping(value = "/pegawai/{noPegawai}")
    private PegawaiModel updatePegawai(@PathVariable("noPegawai") Long noPegawai, @RequestBody PegawaiModel pegawai) {
        try {
            return pegawaiRestService.updatePegawai(noPegawai, pegawai);
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Cabang with ID " + String.valueOf(noPegawai) + " Not Found!"
            );
        }
    }

    @GetMapping(value = "/list-pegawai")
    private List<PegawaiModel> retrieveListPegawai() { return pegawaiRestService.retrieveListPegawai(); }


    @GetMapping(value = "/pegawai/umur/{noPegawai}")
    private HashMap<String, String> getUmur(@PathVariable("noPegawai") Long noPegawai) { 
        HashMap<String, String> result = new HashMap<>();
        String urlPegawai = Setting.pegawaiUrl;
        PegawaiModel pegawai = pegawaiRestService.getPegawaiByNoPegawai(noPegawai);
        String namaDepan = pegawai.getNamaPegawai().split(" ")[0];
        urlPegawai += "?name="+namaDepan;
        RestTemplate restTemplate = new RestTemplate();
        String dataExternal = restTemplate.getForObject(urlPegawai, String.class);
        CabangModel cabang = cabangRestService.getCabangByNoCabang(pegawai.getCabang().getNoCabang());
        LocalTime now = LocalTime.now();

        if ((now.isBefore(cabang.getWaktuBuka()) || now.isAfter(cabang.getWaktuTutup()))) {
            String[] dataAwal = dataExternal.split(":");
            String umur = dataAwal[2].split(",")[0];
            result.put("noPegawai", String.valueOf(pegawai.getNoPegawai()));
            result.put("namaPegawai", pegawai.getNamaPegawai());
            result.put("jenisKelamin", String.valueOf(pegawai.getJenisKelamin()));
            result.put("umur", umur);
        } else {
            throw new UnsupportedOperationException();
        }

        return result;
    }
    
    @GetMapping(value = "/pegawai/jenisKelamin/{jk}")
    private List<PegawaiModel> retrieveListPegawaiByJK(@PathVariable("jk") int jk) {
        return pegawaiRestService.retrieveListPegawaiByJK(jk);
    }
    

}
