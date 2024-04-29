import java.util.HashSet;

public class Problem {
    private final String hash;
    private final HashSet<String> data;

    public Problem(String hash, HashSet<String> data) {
        this.hash = hash;
        this.data = data;
    }

    public String getHash() {
        return hash;
    }

    public HashSet<String> getData() {
        return data;
    }
}
