package apap.tutorial.emsidi.service;

import apap.tutorial.emsidi.model.CabangModel;
import apap.tutorial.emsidi.repository.CabangDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CabangServiceImpl implements CabangService {

    @Autowired
    CabangDB cabangDb;

    @Override
    public void addCabang(CabangModel cabang){
        cabangDb.save(cabang);
    }

    @Override
    public void updateCabang(CabangModel cabang){
        cabangDb.save(cabang);
    }

    @Override
    public void deleteCabang(Long noCabang){
        cabangDb.deleteById(noCabang);
    }

    @Override
    public List<CabangModel> getCabangList(){ return cabangDb.findAll();}

    @Override
    public CabangModel getCabangByNoCabang(Long noCabang){
        Optional<CabangModel> cabang = cabangDb.findByNoCabang(noCabang);
        if (cabang.isPresent()){
            return cabang.get();
        }
        return null;
    }

    @Override
    public List<CabangModel> getListCabangSorted(){
        return cabangDb.findAll(Sort.by(Sort.Direction.ASC,"namaCabang"));
    }

    @Override
    @DateTimeFormat(pattern ="HH:mm")
    public boolean isTutup(Long noCabang){
        LocalTime waktuSekarang = LocalTime.now();
        boolean isTutup = false;
        Optional<CabangModel> cabang = cabangDb.findByNoCabang(noCabang);
        if (cabang.isPresent()){
            if (waktuSekarang.isAfter(cabang.get().getWaktuTutup()) || waktuSekarang.isBefore(cabang.get().getWaktuBuka())) {
                isTutup = true;
            }
        }
        return isTutup;
    }
}
