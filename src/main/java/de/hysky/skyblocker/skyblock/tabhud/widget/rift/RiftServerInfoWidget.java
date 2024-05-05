package de.hysky.skyblocker.skyblock.tabhud.widget.rift;

import de.hysky.skyblocker.skyblock.tabhud.widget.HudWidget;
import de.hysky.skyblocker.skyblock.tabhud.util.Ico;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

/**
 * Special version of the server info widget for the rift!
 *
 */
public class RiftServerInfoWidget extends HudWidget {

	private static final MutableText TITLE = Text.literal("Server Info").formatted(Formatting.LIGHT_PURPLE, Formatting.BOLD);

	public RiftServerInfoWidget() {
		super(TITLE, Formatting.LIGHT_PURPLE.getColorValue());
    }

    @Override
    public void updateContent() {
		this.addSimpleIcoText(Ico.MAP, "Area:", Formatting.LIGHT_PURPLE, 41);
		this.addSimpleIcoText(Ico.NTAG, "Server ID:", Formatting.GRAY, 42);
	}

}
