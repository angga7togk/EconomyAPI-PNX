package angga7togk.economyapi.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import angga7togk.economyapi.database.EconomyDB;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

public class TopCommand extends Command{
    
    public TopCommand(){
        super("topmoney", "Top Money Player");
        setPermission("topmoney.command");
    }
    /** */
    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (testPermission(sender)) {
            List<Map.Entry<String, Integer>> list = new ArrayList<>(EconomyDB.getAll().entrySet());
            Collections.sort(list, Map.Entry.<String, Integer>comparingByValue().reversed());

            StringBuilder msg = new StringBuilder("§aTop Money§r");
            int i = 1;
            for (Map.Entry<String,Integer> entry: list) {
                Entry.comparingByValue().reversed();
                String name = entry.getKey();
                int money = entry.getValue();
                msg.append("\n§r{no}. §e{name}: §a{money}"
                .replace("{no}", String.valueOf(i))
                .replace("{name}", name)
                .replace("{money}", EconomyDB.NumberFormat(money)));
                i++;
                if (i > 10) {
                    break;
                }
            }
            sender.sendMessage(msg.toString());
        }
        return true;
    }
}
