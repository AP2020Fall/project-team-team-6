package server.model.gamesModels;

public enum Color {
    RED {
        @Override
        public String toString() {
            return "red";
        }
    },
    BLUE {
        @Override
        public String toString() {
            return "blue";
        }
    },
    GREEN {
        @Override
        public String toString() {
            return "green";
        }
    },
    YELLOW {
        @Override
        public String toString() {
            return "yellow";
        }
    },
    ORANGE {
        @Override
        public String toString() {
            return "orange";
        }
    },
    PURPLE {
        @Override
        public String toString() {
            return "purple";
        }
    };

    private static final Color[] defaultColor = {RED, BLUE, GREEN, YELLOW, ORANGE, PURPLE};


    public static Color[] getDefaultColor() {
        return defaultColor;
    }
}
