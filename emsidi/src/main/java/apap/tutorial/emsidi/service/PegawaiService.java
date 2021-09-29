package apap.tutorial.emsidi.service;

import apap.tutorial.emsidi.model.CabangModel;
import apap.tutorial.emsidi.model.PegawaiModel;

import java.math.BigInteger;

public interface PegawaiService {
    void addPegawai(PegawaiModel pegawai);
    void updatePegawai(PegawaiModel pegawai);
    PegawaiModel getPegawaiById(Long noPegawai);
    void deletePegawaiById(Long noPegawai);
}
