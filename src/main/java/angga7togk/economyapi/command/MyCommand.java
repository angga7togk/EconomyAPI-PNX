package angga7togk.economyapi.command;

import angga7togk.economyapi.EconomyAPI;
import angga7togk.economyapi.database.EconomyDB;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

public class MyCommand extends Command {
    public MyCommand() {
        super("mymoney",  "see my money");
        this.setPermission("mymoney.command");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if(sender instanceof Player player){
            if(testPermission(sender)){
                if(EconomyDB.playerExists(player)){
                    player.sendMessage(EconomyAPI.prefix + "§6Money, §r" + EconomyDB.NumberFormat(player, EconomyDB.myMoney(player)));
                }
            }
        }
        return true;
    }
}
