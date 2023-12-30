package angga7togk.economyapi.database;

import angga7togk.economyapi.EconomyAPI;
import cn.nukkit.Player;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class EconomyDB {

    public static String getName(String playerName){
        return playerName.toLowerCase();
    }
    public static String getName(Player player){
        return player.getName().toLowerCase();
    }

    public static boolean playerExists(String playerName){
        return EconomyAPI.money.exists(getName(playerName));
    }
    public static boolean playerExists(Player player){
        return EconomyAPI.money.exists(getName(player));
    }

    public static void createNew(String playerName){
        if(!playerExists(playerName)){
            EconomyAPI.money.set(getName(playerName), getStarterMoney());
            EconomyAPI.money.save();
            
        }
    }
    public static void createNew(Player player){
        if(!playerExists(player)){
            EconomyAPI.money.set(getName(player), getStarterMoney());
            EconomyAPI.money.save();
            
        }
    }

    public static Map<String, Integer> getAll(){
        Map<String, Integer> map = new HashMap<>();
        for (String name : EconomyAPI.money.getKeys(false)) {
            map.put(name, EconomyAPI.money.getInt(name));
        }
        return map;
    }

    public static int getMaxMoney(){
        return EconomyAPI.cfg.getInt("max-money");
    }
    public static int getStarterMoney(){
        return EconomyAPI.cfg.getInt("starter-money");
    }

    public static int myMoney(String playerName){
        return EconomyAPI.money.getInt(getName(playerName));
    }
    public static int myMoney(Player player){
        return EconomyAPI.money.getInt(getName(player));
    }

    public static String NumberFormat(int number){
        String myLang = EconomyAPI.cfg.getString("lang");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale(myLang, "ID"));
        return removeRupiah(formatRupiah.format(number));
    }

    private static String removeRupiah(String input) {
        if (input.length() > 3) {
            return input.substring(0, input.length() - 3);
        } else {
            return input;
        }
    }   

    public static boolean setMoney(String playerName, int money){
        playerName = getName(playerName);
        int maxMoney = getMaxMoney();

        if(money > maxMoney){
            return false;
        }
        if(money < 0){
            return false;
        }

        EconomyAPI.money.set(playerName, money);
        EconomyAPI.money.save();
        

        return true;
    }
    public static boolean setMoney(Player player, int money){
        String playerName = getName(player);
        int maxMoney = getMaxMoney();

        if(money > maxMoney){
            return false;
        }
        if(money < 0){
            return false;
        }

        EconomyAPI.money.set(playerName, money);
        EconomyAPI.money.save();
        

        return true;
    }
    public static boolean addMoney(String playerName, int money){
        playerName = getName(playerName);
        int myMoney = myMoney(playerName);
        int maxMoney = getMaxMoney();

        if(money > maxMoney){
            return false;
        }
        if((myMoney + money) > maxMoney){
            return false;
        }
        if(money < 0){
            return false;
        }

        EconomyAPI.money.set(playerName, myMoney + money);
        EconomyAPI.money.save();
        

        return true;
    }
    public static boolean addMoney(Player player, int money){
        String playerName = getName(player);
        int myMoney = myMoney(playerName);
        int maxMoney = getMaxMoney();

        if(money > maxMoney){
            return false;
        }
        if((myMoney + money) > maxMoney){
            return false;
        }
        if(money < 0){
            return false;
        }

        EconomyAPI.money.set(playerName, myMoney + money);
        EconomyAPI.money.save();
        

        return true;
    }
    public static boolean reduceMoney(String playerName, int money){
        playerName = getName(playerName);
        int myMoney = myMoney(playerName);

        if(money < 0){
            return false;
        }
        if(myMoney < money){
            return false;
        }
        EconomyAPI.money.set(playerName, myMoney - money);
        EconomyAPI.money.save();
        

        return true;
    }
    public static boolean reduceMoney(Player player, int money){
        String playerName = getName(player);
        int myMoney = myMoney(playerName);

        if(money < 0){
            return false;
        }

        if(myMoney < money){
            return false;
        }

        EconomyAPI.money.set(playerName, myMoney - money);
        EconomyAPI.money.save();
        return true;
    }

    // Igonored
    public static String NumberFormat(Player player, int number){
        return NumberFormat(number);
    }
    // Igonored
    public static String NumberFormat(String playerName, int number){
        return NumberFormat(number);
    }
}
