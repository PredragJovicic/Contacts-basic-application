package brzocitanje.aplikacija.moji.contactme;

/**
 * Created by Pedja on 12-Jan-18.
 */

class Favoritesitems {


    private String img,name, surname, tel;
    private int position;

    public Favoritesitems(String img, String name, String surname, String tel, int position) {
        this.img = img;
        this.name = name;
        this.surname = surname;
        this.tel = tel;
        this.position = position;
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
