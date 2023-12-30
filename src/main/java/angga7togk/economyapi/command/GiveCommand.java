package angga7togk.economyapi.command;

import angga7togk.economyapi.EconomyAPI;
import angga7togk.economyapi.database.EconomyDB;
import angga7togk.economyapi.language.Language;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

import java.util.Map;

public class GiveCommand extends Command {

    public GiveCommand() {
        super("givemoney", "give money player", "/givemoney <player> <money>");
        this.setPermission("givemoney.command");
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

            boolean status = EconomyDB.addMoney(targetName, money);
            if(!status){
                sender.sendMessage(prefix + lang.get("fail"));
                return false;
            }
            sender.sendMessage(prefix + lang.get("give-money").replace("{player}", targetName).replace("{money}", String.valueOf(money)));

            if(Server.getInstance().getPlayer(targetName) != null){
                Player target = Server.getInstance().getPlayer(targetName);
                target.sendMessage(prefix + lang.get("accept-money").replace("{player}", sender.getName()).replace("{money}", String.valueOf(money)));
            }
        }
        return true;
    }
}
