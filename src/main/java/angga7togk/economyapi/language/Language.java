package angga7togk.economyapi.language;

import angga7togk.economyapi.EconomyAPI;

import java.util.HashMap;
import java.util.Map;

public class Language {

    public static Map<String, String> getLanguage(){
        String lang = EconomyAPI.cfg.getString("lang");
        if(!baseLanguage().containsKey(lang)){
            return baseLanguage().get("en");
        }
        return baseLanguage().get(lang);
    }

    private static Map<String, Map<String, String>> baseLanguage() {
        Map<String, Map<String, String>> lang = new HashMap<>();
        lang.put("en", new HashMap<>());
        lang.put("id", new HashMap<>());

        // Bahasa Inggris
        Map<String, String> en = lang.get("en");
        en.put("player-notfound", "§cPlayer not found.");
        en.put("money-enough", "§cSorry, your money is not enough.");
        en.put("money-int", "§cMoney must be a number.");
        en.put("give-money", "§aSuccessfully gave money to {player} in the amount of {money}");
        en.put("accept-money", "§a{player} gave you money in the amount of {money}");
        en.put("set-money", "§aSuccessfully set {player}'s money to {money}");
        en.put("fail", "§cFailed!");

        // Bahasa Indonesia
        Map<String, String> id = lang.get("id");
        id.put("player-notfound", "§cPemain tidak ditemukan.");
        id.put("money-enough", "§cMaaf, uang Anda tidak cukup.");
        id.put("money-int", "§cUang harus berupa angka.");
        id.put("give-money", "§aBerhasil memberikan uang ke {player} sebesar {money}");
        id.put("accept-money", "§a{player} memberikan uang kepada Anda sebesar {money}");
        id.put("set-money", "§aBerhasil mengatur uang {player} menjadi {money}");
        id.put("fail", "§cGagal!");

        return lang;
    }

}
