package de.hysky.skyblocker.skyblock.events;

import de.hysky.skyblocker.skyblock.tabhud.widget.JacobsContestWidget;
import de.hysky.skyblocker.utils.render.RenderHelper;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Colors;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;

public class JacobEventToast extends EventToast {

    private final String[] crops;

    private static final ItemStack DEFAULT_ITEM = new ItemStack(Items.IRON_HOE);

    public JacobEventToast(long eventStartTime, String name, String[] crops) {
        super(eventStartTime, name, new ItemStack(Items.IRON_HOE));
        this.crops = crops;
    }

    @Override
    public void draw(DrawContext context, TextRenderer textRenderer, long startTime) {
        context.drawGuiTexture(RenderLayer::getGuiTextured, TEXTURE, 0, 0, getWidth(), getHeight());

        int y = (getHeight() - getInnerContentsHeight()) / 2;
        MatrixStack matrices = context.getMatrices();
        if (startTime < 3_000) {
            int k = MathHelper.floor(Math.clamp((3_000 - startTime) / 200.0f, 0.0f, 1.0f) * 255.0f) << 24 | 0x4000000;
            y = 2 + drawMessage(context, 30, y, 0xFFFFFF | k);
        } else {
            int k = (~MathHelper.floor(Math.clamp((startTime - 3_000) / 200.0f, 0.0f, 1.0f) * 255.0f)) << 24 | 0x4000000;


            String s = "Crops:";
            int x = 30 + textRenderer.getWidth(s) + 4;
            context.drawText(textRenderer, s, 30, 7 + (16 - textRenderer.fontHeight) / 2, Colors.WHITE, false);
            for (int i = 0; i < crops.length; i++) {
                context.drawItem(JacobsContestWidget.FARM_DATA.getOrDefault(crops[i], DEFAULT_ITEM), x + i * (16 + 8), 7);
            }
            // IDK how to make the items transparent, so I just redraw the texture on top
            matrices.push();
            matrices.translate(0, 0, 400f);
            RenderHelper.renderNineSliceColored(context, TEXTURE, 0, 0, getWidth(), getHeight(), ColorHelper.fromFloats((k >> 24) / 255f, 1f, 1f, 1f));
            matrices.pop();
            y += textRenderer.fontHeight * message.size();
        }
        matrices.push();
        matrices.translate(0, 0, 400f);
        drawTimer(context, 30, y);

        context.drawItemWithoutEntity(icon, 8, getHeight() / 2 - 8);
        matrices.pop();
    }
}
