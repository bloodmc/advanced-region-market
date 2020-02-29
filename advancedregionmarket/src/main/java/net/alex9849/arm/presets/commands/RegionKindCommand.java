package net.alex9849.arm.presets.commands;

import net.alex9849.arm.AdvancedRegionMarket;
import net.alex9849.arm.Messages;
import net.alex9849.arm.Permission;
import net.alex9849.arm.exceptions.InputException;
import net.alex9849.arm.presets.presets.Preset;
import net.alex9849.arm.presets.presets.PresetType;
import net.alex9849.arm.regionkind.RegionKind;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegionKindCommand extends PresetOptionModifyCommand<RegionKind> {

    public RegionKindCommand(PresetType presetType) {
        super("regionkind", Arrays.asList(Permission.ADMIN_PRESET_SET_REGIONKIND),
                "[^;\n ]+", "[REGIONKIND]", Messages.REGIONKIND_DOES_NOT_EXIST, presetType);
    }

    @Override
    protected RegionKind getSettingsFromCommand(CommandSender sender, String command) throws InputException {
        RegionKind rk = AdvancedRegionMarket.getInstance().getRegionKindManager()
                .getRegionKind(command.split(" ")[1]);
        if(rk == RegionKind.SUBREGION) {
            throw new InputException(sender, Messages.SUB_REGION_REGIONKIND_ONLY_FOR_SUB_REGIONS);
        }
        return rk;
    }

    @Override
    protected void applySetting(CommandSender sender, Preset object, RegionKind setting) {
        object.setRegionKind(setting);
    }

    @Override
    protected List<String> tabCompleteSettingsObject(Player player, String[] args) {
        if (args.length == 2) {
            return new ArrayList<>();
        }
        return AdvancedRegionMarket.getInstance().getRegionKindManager()
                .completeTabRegionKinds(args[1], "");
    }
}
