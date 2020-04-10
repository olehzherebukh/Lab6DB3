package ua.lviv.iot.DTO;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.domain.ArtistEntity;
import ua.lviv.iot.exceptions.NoSuchArtistException;

public class ArtistDTO extends ResourceSupport {
    ArtistEntity artist;
    public ArtistDTO(ArtistEntity artist, Link selfLink) throws NoSuchArtistException {
        this.artist = artist;
        add(selfLink);
    }

    public int getArtistId() {
        return artist.getArtistId();
    }

    public String getFirstNameOfArtist() {
        return artist.getFirstNameOfArtist();
    }

    public String getLastNameOfArtist() {
        return artist.getLastNameOfArtist();
    }

    public int getSexSexId() {
        return artist.getSexSexId();
    }
}
