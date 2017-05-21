package net.warvale.core.game;

import com.avaje.ebean.config.MatchingNamingConvention;

/**
 * Created by Draem on 5/21/2017.
 */
public class MatchInfo {

    public final Integer PLAYER_COUNT, RED, BLUE;

    public MatchInfo(Integer pc, Integer r, Integer b) {
        this.PLAYER_COUNT = pc;
        this.RED = r;
        this.BLUE = b;
    }

}
