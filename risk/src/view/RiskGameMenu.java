package view;

import model.Country;
import model.RiskGame;

import java.util.ArrayList;


public class RiskGameMenu extends Menu {
    private RiskGame riskGame;
    public RiskGameMenu( Menu parentMenu , RiskGame riskGame) {
        super("Play Risk ", parentMenu);
        this.riskGame = riskGame;
    }

    @Override
    public void show() {
        showsCountries();
        super.show();
    }

    @Override
    public void execute() {
        super.execute();
    }

    private void showsCountries(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{1} {6} {6} {5} {5} {5} {21} {23} {23} {23} {23}  --   --   --   --   --   --   --   --  {38} {38} {32} {32}\n");
        stringBuilder.append("{2} {2} {7} {7} {8} --  {20} {20} {22} {22} {25} {25} {25} {25} {37} {37} {37} {36} {36} {36} {30} {30} {31}\n");
        stringBuilder.append("{9} {9} {9} {4} {4} --  {26} {26} {26} {24} {24} {33} {33} {25} {27} {27} {28} {28} {28} {34} {34} {34} {34}\n");
        stringBuilder.append("{3} {3} {3} {3} {3} --  {18} {18} {18} {18} {18} {16} {33} {33} {33} {29} {29} {29} {35}  --   --   --  --\n");
        stringBuilder.append("{3} --  --  --  --  --  {18} {18} {18} {18} {18} {16} {33} {33} {33} {29} {29} {29} {35}  --   --   --  --\n");
        stringBuilder.append("{13} {11} {11} {11} {11}{18} {18} {18} {18} {18} {16} {33} {33} {33} {29} {29} {29} {35}  --   --   --  --\n");
        stringBuilder.append("{12} {12} {10}  --   -- {14} {14} {14} {14} {15} {15} {33}  --   --   --   --   --  {40} {41} {41}  --  --\n");
        stringBuilder.append(" --   --   --   --   --  --   --   --  {19} {19} {17}  --   --   --   --   --   --  {42} {42} {39}  --  --\n");
        String map = stringBuilder.toString();
        System.out.println(map);
    }
}
