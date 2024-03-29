package apap.tutorial.emsidi.service;

import apap.tutorial.emsidi.model.CabangModel;

import java.time.LocalTime;
import java.util.List;

public interface CabangService {
    void addCabang(CabangModel cabang);
    void updateCabang(CabangModel cabang);
    void deleteCabang(Long noCabang);
    List<CabangModel> getCabangList();
    CabangModel getCabangByNoCabang(Long noCabang);
    List<CabangModel> getListCabangSorted();
    boolean isTutup(Long noCabang);
}
