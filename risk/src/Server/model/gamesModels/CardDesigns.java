package Server.model.gamesModels;

public enum CardDesigns {
    INFANTRY {
        @Override
        public String toString() {
            return "Infantry";
        }
    },
    CAVALRY {
        @Override
        public String toString() {
            return "Cavalry";
        }
    },
    ARTILLERY {
        @Override
        public String toString() {
            return "Artillery";
        }
    };
}
