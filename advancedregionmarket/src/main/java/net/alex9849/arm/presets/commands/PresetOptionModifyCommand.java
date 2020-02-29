package net.alex9849.arm.presets.commands;

import net.alex9849.arm.Messages;
import net.alex9849.arm.commands.OptionModifyCommand;
import net.alex9849.arm.exceptions.InputException;
import net.alex9849.arm.presets.ActivePresetManager;
import net.alex9849.arm.presets.PresetPlayerPair;
import net.alex9849.arm.presets.presets.Preset;
import net.alex9849.arm.presets.presets.PresetType;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class PresetOptionModifyCommand<SettingsObj> extends OptionModifyCommand<Preset, SettingsObj> {
    private PresetType presetType;

    public PresetOptionModifyCommand(String rootCommand, List<String> permissions, String optionRegex,
                                     String optionDescription, String settingNotFoundMsg, PresetType presetType) {
        super(false, true, rootCommand,
                Arrays.asList("(?i)" + rootCommand + " " + optionRegex),
                Arrays.asList(rootCommand + " " + optionDescription),
                permissions, "", settingNotFoundMsg);
        this.presetType = presetType;
    }

    public PresetOptionModifyCommand(String rootCommand, List<String> permissions, PresetType presetType) {
        super(false, false, rootCommand,
                Arrays.asList("(?i)" + rootCommand),
                Arrays.asList(rootCommand),
                permissions, "", "");
        this.presetType = presetType;
    }

    protected PresetType getPresetType() {
        return this.presetType;
    }

    @Override
    protected Preset getObjectFromCommand(CommandSender sender, String command) throws InputException {
        Player player = (Player) sender;
        Preset preset = ActivePresetManager.getPreset(player, this.presetType);
        if (preset == null) {
            preset = this.presetType.create();
            ActivePresetManager.add(new PresetPlayerPair(player, preset));
        }
        return preset;
    }

    @Override
    protected void sendSuccessMessage(CommandSender sender, Preset obj, SettingsObj settingsObj) {
        sender.sendMessage(Messages.PREFIX + Messages.PRESET_SET);
    }

    @Override
    protected List<String> tabCompleteObject(Player player, String[] args) {
        return new ArrayList<>();
    }
}
