package apap.tutorial.emsidi.service;

import apap.tutorial.emsidi.model.PegawaiModel;

import java.util.List;

public interface PegawaiRestService {
    PegawaiModel createPegawai(PegawaiModel pegawai);
    List<PegawaiModel> retrieveListPegawai();
    List<PegawaiModel> retrieveListPegawaiByJK(int jk);
    PegawaiModel getPegawaiByNoPegawai(Long noPegawai);
    PegawaiModel updatePegawai(Long noPegawai, PegawaiModel pegawaiUpdate);
    void deletePegawai(Long noPegawai);
}
