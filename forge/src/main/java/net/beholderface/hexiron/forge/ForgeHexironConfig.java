package net.beholderface.hexiron.forge;

import net.beholderface.hexiron.HexironConfig;
import net.minecraftforge.common.ForgeConfigSpec;

public class ForgeHexironConfig implements HexironConfig.CommonConfigAccess {
    public ForgeHexironConfig(ForgeConfigSpec.Builder builder){

    }
    public static class Client implements HexironConfig.ClientConfigAccess{
        public Client(ForgeConfigSpec.Builder builder){

        }
    }
    public static class Server implements HexironConfig.ServerConfigAccess{

        private static ForgeConfigSpec.IntValue oakskinCost;
        private static ForgeConfigSpec.IntValue sightCost;
        private static ForgeConfigSpec.IntValue chargeCost;
        private static ForgeConfigSpec.IntValue wingsCost;
        private static ForgeConfigSpec.IntValue invisCost;

        public Server(ForgeConfigSpec.Builder builder){
            String[] costNames = {"oakskinCost", "sightCost", "chargeCost", "wingsCost", "invisCost"};
            int[] defaultCosts = {HexironConfig.ServerConfigAccess.DEFAULT_OAKSKIN_COST, HexironConfig.ServerConfigAccess.DEFAULT_SIGHT_COST,
                    HexironConfig.ServerConfigAccess.DEFAULT_CHARGE_COST, HexironConfig.ServerConfigAccess.DEFAULT_WINGS_COST,
                    HexironConfig.ServerConfigAccess.DEFAULT_INVIS_COST};
            /*for (int i = 0; i < costNames.length; i++){
                builder.translation("text.autoconfig.hexiron.server.spellCosts." + costNames[i]).defineInRange(costNames[i],
                        defaultCosts[i], 1, Integer.MAX_VALUE);
            }*/
            int i = 0;
            oakskinCost = builder.translation("text.autoconfig.hexiron.server.spellCosts." + costNames[i])
                    .comment("All cost numbers are in raw media units, which one amethyst dust is (by default) equal to 10,000 of. You may want to adjust these if your modpack changes amethyst item -> media values.").defineInRange(costNames[i],
                    defaultCosts[i], 1, Integer.MAX_VALUE);
            i++;
            sightCost = builder.translation("text.autoconfig.hexiron.server.spellCosts." + costNames[i]).defineInRange(costNames[i],
                    defaultCosts[i], 1, Integer.MAX_VALUE);
            i++;
            chargeCost = builder.translation("text.autoconfig.hexiron.server.spellCosts." + costNames[i]).defineInRange(costNames[i],
                    defaultCosts[i], 1, Integer.MAX_VALUE);
            i++;
            wingsCost = builder.translation("text.autoconfig.hexiron.server.spellCosts." + costNames[i]).defineInRange(costNames[i],
                    defaultCosts[i], 1, Integer.MAX_VALUE);
            i++;
            invisCost = builder.translation("text.autoconfig.hexiron.server.spellCosts." + costNames[i]).defineInRange(costNames[i],
                    defaultCosts[i], 1, Integer.MAX_VALUE);
            //builder.pop();
        }

        @Override
        public int getOakskinCost() {
            return oakskinCost.get();
        }

        @Override
        public int getSightCost() {
            return sightCost.get();
        }

        @Override
        public int getChargeCost() {
            return chargeCost.get();
        }

        @Override
        public int getWingsCost() {
            return wingsCost.get();
        }

        @Override
        public int getInvisCost() {
            return invisCost.get();
        }
    }
}
