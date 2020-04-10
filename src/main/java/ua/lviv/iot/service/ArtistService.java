package ua.lviv.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.domain.ArtistEntity;
import ua.lviv.iot.exceptions.NoSuchArtistException;
import ua.lviv.iot.repository.ArtistRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {
    @Autowired
    ArtistRepository artistRepository;

    public ArtistEntity getArtist(Integer artist_id) throws NoSuchArtistException {
        ArtistEntity artist = artistRepository.findById(artist_id).get();//2.0.0.M7
        if (artist == null) throw new NoSuchArtistException();
        return artist;
    }

    public List<ArtistEntity> getAllArtist() {
        return artistRepository.findAll();
    }

    @Transactional
    public void createArtist(ArtistEntity artist) {
        artistRepository.save(artist);
    }

    @Transactional
    public void updateArtist(ArtistEntity uArtist, Integer artist_id) throws NoSuchArtistException {
        ArtistEntity artist = artistRepository.findById(artist_id).get();
        if (artist == null) throw new NoSuchArtistException();
        artist.setFirstNameOfArtist(uArtist.getFirstNameOfArtist());
        artist.setLastNameOfArtist(uArtist.getLastNameOfArtist());
        artist.setSexSexId(uArtist.getSexSexId());
    }

    @Transactional
    public void deleteArtist(Integer artist_id) throws NoSuchArtistException {
        Optional artist = artistRepository.findById(artist_id);

        if (!artist.isPresent()) throw new NoSuchArtistException();
        artistRepository.delete((ArtistEntity) artist.get());
    }
}
