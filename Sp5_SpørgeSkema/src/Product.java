public class Product {
    private String name;
    private int accepted;
    private int score;

    public Product(String name,  int accepted, int score) {
        this.name = name;
        this.accepted = accepted;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public int getAccepted() {
        return accepted;
    }

    public void setAccepted(int accepted) {
        this.accepted = accepted;
    }
}
