package sg.edu.nus.iss.Workshop27.model;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameResponse {
    private List<GameShort> games;
    private int offset;
    private int limit;
    private long total;
    private Date timestamp;

    public GameResponse(List<GameShort> games, int offset, int limit) {
        this.games = games;
        this.offset = offset;
        this.limit = limit;
        this.total = games.size();
        this.timestamp = new Date();
    }

    
}
