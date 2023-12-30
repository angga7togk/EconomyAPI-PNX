package angga7togk.economyapi.command;

import angga7togk.economyapi.EconomyAPI;
import angga7togk.economyapi.database.EconomyDB;
import angga7togk.economyapi.language.Language;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

import java.util.Map;

public class ReduceCommand extends Command {

    public ReduceCommand() {
        super("reducemoney", "reduce money player", "/reducemoney <player> <money>");
        this.setPermission("reducemoney.command");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        String prefix = EconomyAPI.prefix;
        Map<String, String> lang = Language.getLanguage();

        if(testPermission(sender)){
            if(args.length < 2){
                sender.sendMessage(prefix + this.getUsage());
                return false;
            }
            String targetName = args[0];
            if(!EconomyDB.playerExists(targetName)){
                sender.sendMessage(prefix + lang.get("player-notfound"));
                return false;
            }
            int money;
            try {
                money = Integer.parseInt(args[1]);
            }catch (NumberFormatException e){
                sender.sendMessage(prefix + lang.get("money-int"));
                return false;
            }

            boolean status = EconomyDB.reduceMoney(targetName, money);
            if(!status){
                sender.sendMessage(prefix + lang.get("fail"));
                return false;
            }
            sender.sendMessage(prefix + lang.get("give-money").replace("{player}", targetName).replace("{money}", String.valueOf(money)));
        }
        return true;
    }
}
