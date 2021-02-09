package Server.model.gamesModels;

public enum RiskGameType {
    PRIVATE {
        @Override
        public String toString() {
            return "Private";
        }
    },
    PUBLIC {
        @Override
        public String toString() {
            return "Public";
        }
    },
    OFFLINE {
        @Override
        public String toString() {
            return "Offline";
        }

    };
}
