package ua.lviv.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.domain.ArtistEntity;
import ua.lviv.iot.domain.SexEntity;
import ua.lviv.iot.exceptions.NoSuchArtistException;
import ua.lviv.iot.exceptions.NoSuchSexException;
import ua.lviv.iot.repository.ArtistRepository;
import ua.lviv.iot.repository.SexRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SexService {
    @Autowired
    SexRepository sexRepository;

    public SexEntity getSex(Integer sex_id) throws NoSuchSexException {
        SexEntity sex = sexRepository.findById(sex_id).get();
        if (sex == null) throw new NoSuchSexException();
        return sex;
    }

    public List<SexEntity> getAllSex() {
        return sexRepository.findAll();
    }

    @Transactional
    public void createSex(SexEntity sex) {
        sexRepository.save(sex);
    }

    @Transactional
    public void updateSex(SexEntity uSex, Integer sex_id) throws NoSuchSexException {
        SexEntity sex = sexRepository.findById(sex_id).get();
        if (sex == null) throw new NoSuchSexException();
        sex.setSexId(uSex.getSexId());
        sex.setSexOfArtist(uSex.getSexOfArtist());
    }

    @Transactional
    public void deleteSex(Integer sex_id) throws NoSuchSexException {
        Optional sex = sexRepository.findById(sex_id);

        if (!sex.isPresent()) throw new NoSuchSexException();
        sexRepository.delete((SexEntity) sex.get());
    }
}
