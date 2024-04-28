package sg.edu.nus.iss.Workshop27.model;

public class GameShort {
    private int game_id;
    private String name;

    public GameShort(int gid, String name) {
        this.game_id = gid;
        this.name = name;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int gid) {
        this.game_id = gid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
