package apap.tutorial.emsidi.service;

import apap.tutorial.emsidi.model.PegawaiModel;
import apap.tutorial.emsidi.repository.PegawaiDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService {

    @Autowired
    PegawaiDB pegawaiDb;

    @Override
    public void addPegawai(PegawaiModel pegawai) { pegawaiDb.save(pegawai); }

    @Override
    public void updatePegawai(PegawaiModel pegawai){
        pegawaiDb.save(pegawai);
    }

    @Override
    public PegawaiModel getPegawaiById(Long noPegawai){
        Optional<PegawaiModel> pegawai = pegawaiDb.findByNoPegawai(noPegawai);
        if (pegawai.isPresent()){
            return pegawai.get();
        }
        return null;
    }

    @Override
    public void deletePegawaiById(Long noPegawai){
        pegawaiDb.deleteById(noPegawai);
    }
}
