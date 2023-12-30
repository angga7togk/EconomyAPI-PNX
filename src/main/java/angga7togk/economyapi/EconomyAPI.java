package angga7togk.economyapi;

import angga7togk.economyapi.command.*;
import angga7togk.economyapi.listener.Listeners;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;

public class EconomyAPI extends PluginBase {


    public static EconomyAPI instance;
    public static Config cfg, money;
    public static String prefix;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        this.saveResource("config.yml");
        this.saveResource("money.yml");
        cfg = new Config(this.getDataFolder() + "/config.yml", Config.YAML);
        money = new Config(this.getDataFolder() + "/money.yml", Config.YAML);
        prefix = cfg.getString("prefix");

        this.getServer().getPluginManager().registerEvents(new Listeners(), this);

        this.getServer().getCommandMap().register("givemoney", new GiveCommand());
        this.getServer().getCommandMap().register("setmoney", new SetCommand());
        this.getServer().getCommandMap().register("mymoney", new MyCommand());
        this.getServer().getCommandMap().register("paymoney", new PayCommand());
        this.getServer().getCommandMap().register("seemoney", new SeeCommand());
        this.getServer().getCommandMap().register("reducemoney", new ReduceMoney());
    }

    public static EconomyAPI getInstance(){
        return instance;
    }
}
