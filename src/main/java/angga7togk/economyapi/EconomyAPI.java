package angga7togk.economyapi;

import java.util.List;

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

        this.getServer().getCommandMap().registerAll(getName(), List.of(
            new GiveCommand(),
            new SetCommand(),
            new MyCommand(),
            new PayCommand(),
            new SeeCommand(),
            new ReduceCommand(),
            new TopCommand()
        ));
    }

    public static EconomyAPI getInstance(){
        return instance;
    }
}
