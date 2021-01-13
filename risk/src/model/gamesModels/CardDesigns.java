package model.gamesModels;

import java.util.ArrayList;

public enum CardDesigns {
    INFANTRY{
        @Override
        public String toString(){
            return "Infantry";
        }
    },
    CAVALRY{
        @Override
        public String toString(){
            return "Cavalry";
        }
    },
    ARTILLERY{
        @Override
        public String toString(){
            return "Artillery";
        }
    };
}
