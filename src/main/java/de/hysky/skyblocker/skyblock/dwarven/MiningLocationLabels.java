package de.hysky.skyblocker.skyblock.dwarven;

import de.hysky.skyblocker.utils.render.RenderHelper;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.awt.*;

public class MiningLocationLabels {
    final Text name;
    final Category category;
    private final Vec3d centerPos;

    MiningLocationLabels(Category category, BlockPos pos) {
        this.name = Text.literal(category.getName()).withColor(category.getColor());
        this.category = category;
        this.centerPos = pos.toCenterPos();
    }

    public void render(WorldRenderContext context) {
        Vec3d posUp = centerPos.add(0, 1, 0);
        double distance = context.camera().getPos().distanceTo(centerPos);
        float scale = (float) (1 * (distance / 10));
        RenderHelper.renderText(context, name, posUp, scale, true);
        RenderHelper.renderText(context, Text.literal(Math.round(distance) + "m").formatted(Formatting.YELLOW), posUp, scale, MinecraftClient.getInstance().textRenderer.fontHeight + 1, true);
    }

    interface Category {
        String getName();

        int getColor(); //all the color codes are the color of the block the waypoint is for
    }

    enum dwarvenCategory implements Category {
        LAVA_SPRINGS("Lava Springs", new BlockPos(60, 197, -15)),
        CLIFFSIDE_VEINS("Cliffside Veins", new BlockPos(40, 128, 40)),
        RAMPARTS_QUARRY("Rampart's Quarry", new BlockPos(-100, 150, -20)),
        UPPER_MINES("Upper Mines", new BlockPos(-130, 174, -50)),
        ROYAL_MINES("Royal Mines", new BlockPos(130, 154, 30)),
        GLACITE_WALKER("Glacite Walker", new BlockPos(0, 128, 150));


        boolean isTitanium;
        private final String name;
        private final BlockPos location;

        dwarvenCategory(String name, BlockPos location) {
            this.name = name;
            this.location = location;
        }

        public BlockPos getLocation() {
            return location;
        }

        @Override
        public String toString() {
            return name;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public int getColor() {
            if (isTitanium) {
                return 0xd8d6d8;
            }
            return 0x45bde0;
        }
    }

    enum glaciteCategory implements Category {
        AQUAMARINE("Aquamarine", 0x334cb1, new BlockPos[]{new BlockPos(-1, 139, 437), new BlockPos(90, 151, 229), new BlockPos(56, 151, 400), new BlockPos(51, 117, 303)}),
        ONYX("Onyx", 0x191919, new BlockPos[]{new BlockPos(79, 119, 411), new BlockPos(-14, 132, 386), new BlockPos(18, 136, 370), new BlockPos(16, 138, 411), new BlockPos(-68, 130, 408)}),
        PERIDOT("Peridot", 0x667f33, new BlockPos[]{new BlockPos(-61, 147, 302), new BlockPos(91, 122, 397), new BlockPos(-73, 122, 458), new BlockPos(-77, 120, 282)}),
        CITRINE("Citrine", 0x664c33, new BlockPos[]{new BlockPos(-104, 144, 244), new BlockPos(39, 119, 386), new BlockPos(-57, 144, 421), new BlockPos(-47, 126, 418)});

        private final String name;
        private final int color;
        private final BlockPos[] location;

        glaciteCategory(String name, int color, BlockPos[] location) {
            this.name = name;
            this.color = color;
            this.location = location;
        }



        public BlockPos[] getLocations() {
            return location;
        }

        @Override
        public String toString() {
            return name;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public int getColor() {
            return color;
        }
    }
}
