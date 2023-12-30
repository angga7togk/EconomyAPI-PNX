package angga7togk.economyapi.command;

import angga7togk.economyapi.EconomyAPI;
import angga7togk.economyapi.database.EconomyDB;
import angga7togk.economyapi.language.Language;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

import java.util.Map;

public class SeeCommand extends Command {

    public SeeCommand(){
        super("seemoney", "see money player", "/seemoney <player>");
        this.setPermission("seemoney.command");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        String prefix = EconomyAPI.prefix;
        Map<String, String> lang = Language.getLanguage();
        if(this.testPermission(sender)){
            if (args.length < 1){
                sender.sendMessage(prefix + this.getUsage());
                return false;
            }
            String targetName = args[0];
            if(!EconomyDB.playerExists(targetName)){
                sender.sendMessage(prefix + lang.get("player-notfound"));
                return false;
            }
            sender.sendMessage(prefix + "§6Name, §r" + targetName);
            sender.sendMessage(prefix + "§6Money, §r" + EconomyDB.NumberFormat(targetName, EconomyDB.myMoney(targetName)));
        }
        return true;
    }
}
