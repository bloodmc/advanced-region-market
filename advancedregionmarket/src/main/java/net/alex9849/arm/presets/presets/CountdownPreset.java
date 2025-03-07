package net.alex9849.arm.presets.presets;

import net.alex9849.arm.Messages;
import net.alex9849.arm.regions.ContractRegion;
import net.alex9849.arm.regions.Region;
import net.alex9849.arm.regions.price.Autoprice.AutoPrice;
import net.alex9849.arm.regions.price.ContractPrice;
import net.alex9849.arm.regions.price.RentPrice;
import net.alex9849.arm.util.TimeUtil;
import net.alex9849.arm.util.stringreplacer.StringCreator;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;

public abstract class CountdownPreset extends Preset {
    private Long extendTime;

    public Long getExtendTime() {
        return this.extendTime;
    }

    public void setExtendTime(String string) {
        this.setExtendTime(RentPrice.stringToTime(string));
    }

    public void setExtendTime(Long time) {
        if(time == null) {
            this.extendTime = null;
            return;
        }
        if (time < 1000) {
            throw new IllegalArgumentException("MaxExtendTime needs to be at least one second!");
        }
        this.extendTime = time;
        this.setAutoPrice(null);
    }

    @Override
    public void setAutoPrice(AutoPrice autoPrice) {
        super.setAutoPrice(autoPrice);
        if(autoPrice != null) {
            this.extendTime = null;
        }
    }

    @Override
    public boolean canPriceLineBeLetEmpty() {
        return (this.getPrice() != null && this.getExtendTime() != null) || this.getAutoPrice() != null;
    }

    @Override
    public HashMap<String, StringCreator> getVariableReplacements() {
        HashMap<String, StringCreator> variableReplacements = super.getVariableReplacements();
        variableReplacements.put("%extendtime%", () -> Messages.getStringValue(this.getExtendTime(), x ->
                TimeUtil.timeInMsToString(x, false, false), Messages.NOT_DEFINED));
        return variableReplacements;
    }

    @Override
    public ConfigurationSection toConfigurationSection() {
        ConfigurationSection section = super.toConfigurationSection();
        section.set("extendTime", this.getExtendTime());
        return section;
    }

}
