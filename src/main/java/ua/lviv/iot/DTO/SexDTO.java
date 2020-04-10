package ua.lviv.iot.DTO;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.domain.SexEntity;
import ua.lviv.iot.exceptions.NoSuchSexException;

public class SexDTO extends ResourceSupport {
    SexEntity sex;
    public SexDTO(SexEntity sex, Link selfLink) throws NoSuchSexException {
        this.sex = sex;
        add(selfLink);
    }

    public int getSexId() {
        return sex.getSexId();
    }

    public String getSexOfArtist() {
        return sex.getSexOfArtist();
    }
}
