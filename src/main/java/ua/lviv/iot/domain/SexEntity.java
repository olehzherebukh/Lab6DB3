package ua.lviv.iot.domain;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "sex", schema = "zherebukh", catalog = "")
public class SexEntity {
    private int sexId;
    private String sexOfArtist;
    private Collection<ArtistEntity> artistsBySexId;

    public SexEntity(int sexId, String sexOfArtist) {
        this.sexId = sexId;
        this.sexOfArtist = sexOfArtist;
    }

    public SexEntity(String sexOfArtist) {
        this.sexOfArtist = sexOfArtist;
    }

    public SexEntity() {

    }

    @Id
    @Column(name = "sex_id", nullable = false)
    public int getSexId() {
        return sexId;
    }

    public void setSexId(int sexId) {
        this.sexId = sexId;
    }

    @Basic
    @Column(name = "sex_of_artist", nullable = true, length = 45)
    public String getSexOfArtist() {
        return sexOfArtist;
    }

    public void setSexOfArtist(String sexOfArtist) {
        this.sexOfArtist = sexOfArtist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SexEntity sexEntity = (SexEntity) o;

        if (sexId != sexEntity.sexId) return false;
        if (sexOfArtist != null ? !sexOfArtist.equals(sexEntity.sexOfArtist) : sexEntity.sexOfArtist != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sexId;
        result = 31 * result + (sexOfArtist != null ? sexOfArtist.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "sexBySexSexId")
    public Collection<ArtistEntity> getArtistsBySexId() {
        return artistsBySexId;
    }

    public void setArtistsBySexId(Collection<ArtistEntity> artistsBySexId) {
        this.artistsBySexId = artistsBySexId;
    }
}
