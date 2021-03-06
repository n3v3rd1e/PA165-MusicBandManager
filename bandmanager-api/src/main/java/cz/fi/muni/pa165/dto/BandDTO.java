package cz.fi.muni.pa165.dto;

import cz.muni.fi.pa165.enums.Genre;

/**
 * Miroslav Kadlec
 */
public class BandDTO {

    private Long id;
    private String name;
    private String logoURI;
    private Genre genre;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoURI() {
        return logoURI;
    }

    public void setLogoURI(String logoURI) {
        this.logoURI = logoURI;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        BandDTO other = (BandDTO) obj;
        if (this.getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!this.getId().equals(other.getId()))
            return false;
        if (this.getName() == null) {
            if (other.getName() != null)
                return false;
        } else if (!this.getName().equals(other.getName()))
            return false;
        if (this.getGenre()== null) {
            if (other.getGenre() != null)
                return false;
        } else if (!this.getGenre().equals(other.getGenre()))
            return false;
        return true;
    }

    @Override
    public String toString() {
        String result = "BandDTO {"
                + "id=" + this.getId()
                + ",name=" + this.getName()
                + ",logoURI" + this.getLogoURI()
                + ",genre" + this.getGenre().name();
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 17 + (this.getId() != null ? this.getId().hashCode() : 0);
        hash = hash * 31 + (this.getName()!= null ? this.getName().hashCode() : 0);
        hash = hash * 13 + (this.getLogoURI()!= null ? this.getLogoURI().hashCode() : 0);
        hash = hash * 37 + (this.getGenre()!= null ? this.getGenre().hashCode() : 0);
        return hash;
    }

    
}
