package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.DTO.ArtistDTO;
import ua.lviv.iot.domain.ArtistEntity;
import ua.lviv.iot.exceptions.NoSuchArtistException;
import ua.lviv.iot.service.ArtistService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
public class ArtistController {
    @Autowired
    ArtistService artistService;

    @GetMapping(value = "/api/artist/{artist_id}")
    public ResponseEntity<ArtistDTO> getArtist(@PathVariable Integer artist_id) throws NoSuchArtistException {
        ArtistEntity artist = artistService.getArtist(artist_id);
        Link link = linkTo(methodOn(ArtistController.class).getArtist(artist_id)).withSelfRel();

        ArtistDTO artistDTO = new ArtistDTO(artist, link);

        return new ResponseEntity<>(artistDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/artist")
    public ResponseEntity<List<ArtistDTO>> getAllArtist() throws NoSuchArtistException {
        List<ArtistEntity> artistList = artistService.getAllArtist();
        Link link = linkTo(methodOn(ArtistController.class).getAllArtist()).withSelfRel();

        List<ArtistDTO> artistDTO = new ArrayList<>();
        for (ArtistEntity entity : artistList ) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getArtistId()).withSelfRel();
            ArtistDTO dto = new ArtistDTO(entity, selfLink);
            artistDTO.add(dto);
        }

        return new ResponseEntity<>(artistDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/artist")
    public ResponseEntity<ArtistDTO> addArtist(@RequestBody ArtistEntity newArtist) throws NoSuchArtistException {
        artistService.createArtist(newArtist);
        Link link = linkTo(methodOn(ArtistController.class).getArtist((int) newArtist.getArtistId())).withSelfRel();

        ArtistDTO artistDTO = new ArtistDTO(newArtist, link);

        return new ResponseEntity<>(artistDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/artist/{artist_id}")
    public ResponseEntity<ArtistDTO> updateArtist(@RequestBody ArtistEntity uArtist, @PathVariable Integer artist_id) throws NoSuchArtistException {
        artistService.updateArtist(uArtist, artist_id);
        ArtistEntity artist = artistService.getArtist(artist_id);
        Link link = linkTo(methodOn(ArtistController.class).getArtist(artist_id)).withSelfRel();

        ArtistDTO artistDTO = new ArtistDTO(artist, link);

        return new ResponseEntity<>(artistDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/artist/{artist_id}")
    public  ResponseEntity deleteArtist(@PathVariable Integer artist_id) throws NoSuchArtistException {
        artistService.deleteArtist(artist_id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
