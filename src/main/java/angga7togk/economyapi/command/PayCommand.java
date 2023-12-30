package angga7togk.economyapi.command;

import angga7togk.economyapi.EconomyAPI;
import angga7togk.economyapi.database.EconomyDB;
import angga7togk.economyapi.language.Language;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

import java.util.Map;

public class PayCommand extends Command {

    public PayCommand(){
        super("paymoney", "pay money to player", "/paymoney <player> <money>", new String[]{"pay"});
        this.setPermission("paymoney.command");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (sender instanceof Player player){
            String prefix = EconomyAPI.prefix;
            Map<String, String> lang = Language.getLanguage();
            if(this.testPermission(sender)){
                if (args.length < 2){
                    player.sendMessage(prefix + this.getUsage());
                    return false;
                }

                String targetName = args[0];
                if(!EconomyDB.playerExists(targetName)){
                    player.sendMessage(prefix + lang.get("player-notfound"));
                    return false;
                }
                int myMoney = EconomyDB.myMoney(player);
                int money;
                try {
                    money = Integer.parseInt(args[1]);
                }catch (NumberFormatException e){
                    player.sendMessage(prefix + lang.get("money-int"));
                    return false;
                }

                if(myMoney < money){
                    player.sendMessage(prefix + lang.get("money-enough"));
                    return false;
                }

                boolean addMoney = EconomyDB.addMoney(targetName, money);
                boolean reduceMoney = EconomyDB.reduceMoney(player, money);
                if(!addMoney && !reduceMoney){
                    player.sendMessage(prefix + lang.get("fail"));
                    return false;
                }

                player.sendMessage(prefix + lang.get("give-money").replace("{player}", targetName).replace("{money}", String.valueOf(money)));

                if(Server.getInstance().getPlayer(targetName) != null){
                    Player target = Server.getInstance().getPlayer(targetName);
                    target.sendMessage(prefix + lang.get("accept-money").replace("{player}", player.getName()).replace("{money}", String.valueOf(money)));
                }
            }
        }
        return true;
    }
}
