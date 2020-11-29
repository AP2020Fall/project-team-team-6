package model;

public enum Color {
    RED{
        @Override
        public String toString(){
            return "red";
        }
    },
    BLUE{
        @Override
        public String toString(){
            return "blue";
        }
    },
    GRAY{
        @Override
        public String toString(){
            return "gray";
        }
    },
    YELLOW{
        @Override
        public String toString(){
            return "yellow";
        }
    },
    BLACK{
        @Override
        public String toString(){
            return "black";
        }
    },
    PURPLE{
        @Override
        public String toString(){
            return "purple";
        }
    };

    private static final Color[] defaultColor = {RED , BLUE , GRAY , YELLOW , BLACK , PURPLE};


    public static Color[] getDefaultColor() {
        return defaultColor;
    }
}
