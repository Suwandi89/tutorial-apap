package apap.tutorial.emsidi.service;

import apap.tutorial.emsidi.model.PegawaiModel;
import apap.tutorial.emsidi.repository.PegawaiDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class PegawaiRestServiceImpl implements PegawaiRestService{

    @Autowired
    private PegawaiDB pegawaiDb;

    @Override
    public PegawaiModel createPegawai(PegawaiModel pegawai){ return pegawaiDb.save(pegawai); }

    @Override
    public List<PegawaiModel> retrieveListPegawai(){ return pegawaiDb.findAll(); };

    @Override
    public PegawaiModel getPegawaiByNoPegawai(Long noPegawai) {
        Optional<PegawaiModel> pegawai = pegawaiDb.findByNoPegawai(noPegawai);

        if (pegawai.isPresent()) {
            return pegawai.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public PegawaiModel updatePegawai(Long noPegawai, PegawaiModel pegawaiUpdate){
        PegawaiModel pegawai = getPegawaiByNoPegawai(noPegawai);
        pegawai.setNamaPegawai(pegawaiUpdate.getNamaPegawai());
        pegawai.setJenisKelamin(pegawaiUpdate.getJenisKelamin());

        return pegawaiDb.save(pegawai);
    }

    @Override
    public void deletePegawai(Long noPegawai) {
        LocalTime now = LocalTime.now();
        PegawaiModel pegawai = getPegawaiByNoPegawai(noPegawai);

        if ((now.isBefore(pegawai.getCabang().getWaktuBuka()) || now.isAfter(pegawai.getCabang().getWaktuTutup()))) {
            pegawaiDb.delete(pegawai);
        } else {
            throw new UnsupportedOperationException();
        }
    };
}
