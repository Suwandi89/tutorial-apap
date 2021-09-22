package apap.tutorial.kebunsafari.service;

import apap.tutorial.kebunsafari.model.KebunSafariModel;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class KebunSafariInMemoryService implements KebunSafariService{
    private List<KebunSafariModel> listKebunSafari;

    //Constructor
    public KebunSafariInMemoryService(){
        listKebunSafari = new ArrayList<>();
    }

    @Override
    public void addKebunSafari(KebunSafariModel kebunSafari){
        listKebunSafari.add(kebunSafari);
    }

    @Override
    public List<KebunSafariModel> getKebunSafariList(){
        return listKebunSafari;
    }

    @Override
    public KebunSafariModel getKebunSafariByIdKebunSafari(String idKebunSafari) {
        for (KebunSafariModel x : listKebunSafari){
            if(x.getIdKebunSafari().equals(idKebunSafari)){
                return x;
            }
        }
        return null;
    }

    @Override
    public List<KebunSafariModel> getKebunSafariByTelpKebunSafari(String noTelp) {
        List<KebunSafariModel> listTelp = new ArrayList<KebunSafariModel>();
        for (KebunSafariModel x : listKebunSafari){
            if(x.getNoTelepon().equals(noTelp)){
                listTelp.add(x);
            }
        }
        return listTelp;
    }
}
