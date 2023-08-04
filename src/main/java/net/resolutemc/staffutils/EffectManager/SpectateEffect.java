package net.resolutemc.staffutils.EffectManager;

import net.resolutemc.staffutils.StaffUtils;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class SpectateEffect {

    static boolean nightVisionEnabled = StaffUtils.getInstance().getConfig().getBoolean("SpectateNightVision");

    public static void spectateEffectEnabled(Player player) {
        if (nightVisionEnabled) {
            player.addPotionEffect(PotionEffectType.NIGHT_VISION.createEffect(999999999, 255));
        }
    }

    public static void spectateEffectDisabled(Player player) {
        if (nightVisionEnabled) {
            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
        }
    }
}
