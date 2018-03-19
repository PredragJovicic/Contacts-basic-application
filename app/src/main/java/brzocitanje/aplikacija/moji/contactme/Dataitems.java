package brzocitanje.aplikacija.moji.contactme;

/**
 * Created by Pedja on 11-Jan-18.
 */

class Dataitems {

    private String img,name, surname, tel;
    private boolean favorite;

    public Dataitems(String img, String name, String surname, String tel, boolean favorite) {
        this.img = img;
        this.name = name;
        this.surname = surname;
        this.tel = tel;
        this.favorite = favorite;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
