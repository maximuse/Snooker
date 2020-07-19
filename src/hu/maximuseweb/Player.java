package hu.maximuseweb;

class Player {
    private int place;
    private String name;
    private String country;
    private int prize;

    Player(int place, String name, String country, int prize) {
        this.place = place;
        this.name = name;
        this.country = country;
        this.prize = prize;
    }

    int getPlace() {
        return place;
    }

    String getName() {
        return name;
    }

    String getCountry() {
        return country;
    }

    int getPrize() {
        return prize;
    }

    @Override
    public String toString() {
        return "Player{" +
               "place=" + place +
               ", name='" + name + "'" +
               ", country='" + country + "'" +
               ", prize=" + prize +
               "}\n";
    }
}