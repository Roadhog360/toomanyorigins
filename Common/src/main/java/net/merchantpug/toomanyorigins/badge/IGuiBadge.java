package net.merchantpug.toomanyorigins.badge;

import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.origins.badge.BadgeFactory;
import net.merchantpug.toomanyorigins.client.tooltip.GuiTooltipComponent;
import net.merchantpug.toomanyorigins.registry.TMOBadges;
import net.merchantpug.toomanyorigins.util.GuiBackground;
import net.merchantpug.toomanyorigins.util.GuiContent;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.core.NonNullList;
import net.minecraft.util.Mth;

import java.util.LinkedList;
import java.util.List;

public interface IGuiBadge<P> extends IBadge<P> {

    @Override
    default boolean hasTooltip() {
        return true;
    }

    @Override
    default List<ClientTooltipComponent> generateTooltipComponents(P power, int widthLimit, float time, Font textRenderer) {
        List<ClientTooltipComponent> tooltips = new LinkedList<>();
        NonNullList<GuiContent> contentList = peekContent(time);
        tooltips.add(new GuiTooltipComponent(getBackground(), contentList));
        return tooltips;
    }

    default NonNullList<GuiContent> peekContent(float time) {
        int seed = Mth.floor(time / 30);
        if (getContent().isEmpty()) {
            return NonNullList.create();
        }
        int contentSize = getContent().size();
        NonNullList<GuiContent> contents = NonNullList.create();
        for (int index = 0; index < contentSize; ++index) {
            List<GuiContent> content = getContent().get(index);
            if (content.size() > 0)
                contents.add(content.get(seed % content.size()));
        }
        return contents;
    }

    @Override
    default SerializableData.Instance toData(SerializableData.Instance instance) {
        instance.set("sprite", spriteId());
        instance.set("background", getBackground());
        instance.set("content", getContent());
        return instance;
    }

    @Override
    default BadgeFactory getBadgeFactory() {
        return TMOBadges.GUI.get();
    }

    GuiBackground getBackground();

    List<List<GuiContent>> getContent();

}