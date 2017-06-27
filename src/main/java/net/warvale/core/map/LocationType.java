package net.warvale.core.map;

/**
 * Created by AAces on 6/20/2017.
 */
public enum LocationType {
    CORE {
        @Override
        public boolean isLobby() {
            return false;
        }
    },
    SPAWN {
        @Override
        public boolean isLobby() {
            return false;
        }
    },
    CENTER {
        @Override
        public boolean isLobby() {
            return false;
        }
    },
    LOBBY {
        @Override
        public boolean isLobby() {
            return true;
        }
    };

    public abstract boolean isLobby();
}
