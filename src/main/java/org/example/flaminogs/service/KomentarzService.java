package org.example.flaminogs.service;

import org.example.flaminogs.entity.Komentarz;
import org.example.flaminogs.entity.Multimedium;
import org.example.flaminogs.klasy.KomentarzRsp;
import org.example.flaminogs.repository.KomentarzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class KomentarzService {

    private final KomentarzRepository komentarzRepository;
    private final MultimediumService multimediumService;

    @Autowired
    public KomentarzService(KomentarzRepository komentarzRepository, MultimediumService multimediumService) {
        this.komentarzRepository = komentarzRepository;
        this.multimediumService = multimediumService;
    }

    public List<Komentarz> findAll() {
        return komentarzRepository.findAll();
    }

    public Komentarz save(Komentarz komentarz) {
        return komentarzRepository.save(komentarz);
    }

    public void delete(Integer id){
        komentarzRepository.deleteById(id);
    }

    public void printAllKomentarze() {
        List<Komentarz> komentarze = findAll();
        for (Komentarz komentarz : komentarze) {
            System.out.println("Komentarz ID: " + komentarz.getId() + ", Treść: " + komentarz.getText());
        }
    }

    public List<KomentarzRsp> getAllKomentarze() {
        List<KomentarzRsp> result = new ArrayList<>();

        for (Komentarz komentarz : findAll()) {
            KomentarzRsp temp = new KomentarzRsp();
            temp.setKomentarz(komentarz);

            List<String> tempList = new ArrayList<>();

            for (Multimedium multimedia : multimediumService.findAll()) {
                if (Objects.equals(multimedia.getIdkomentarza(), komentarz.getId())) {
                    tempList.add(multimedia.getMultimedium());
                }
            }
            temp.setMultimedia(tempList);
            result.add(temp);
        }
        return result;
    }
}
