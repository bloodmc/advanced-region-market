package net.alex9849.arm.presets.commands;

import net.alex9849.arm.Permission;
import net.alex9849.arm.exceptions.InputException;
import net.alex9849.arm.presets.presets.Preset;
import net.alex9849.arm.presets.presets.PresetType;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class RemoveCommandCommand extends PresetOptionModifyCommand<Integer> {

    public RemoveCommandCommand(PresetType presetType) {
        super("removecommand", Arrays.asList(Permission.ADMIN_PRESET_REMOVECOMMAND),
                "[0-9]+", "ID", "", presetType);
    }

    @Override
    protected Integer getSettingsFromCommand(CommandSender sender, String command) throws InputException {
        return Integer.parseInt(command.split(" ")[1]) - 1;
    }

    @Override
    protected void applySetting(CommandSender sender, Preset object, Integer setting) throws InputException {
        object.removeCommand(setting);
    }

    @Override
    protected List<String> tabCompleteSettingsObject(Player player, String[] args) {
        return null;
    }
}
