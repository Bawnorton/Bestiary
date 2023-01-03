package com.bawnorton;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.font.TextHandler;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.NarratorManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.apache.commons.lang3.mutable.MutableInt;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class BestiaryScreen extends Screen {
    private final int WIDTH = 256;
    private final int HEIGHT = 176;

    public static final Identifier TEXTURE = new Identifier("bestiary", "textures/gui/bestiary.png");
    private int currentPage;
    private final List<String> pages = Lists.newArrayList();
    private BestiaryTurnWidget nextPageButton;
    private BestiaryTurnWidget previousPageButton;
    @Nullable
    private BestiaryScreen.PageContent pageContent;
    private Text pageIndicatorText;

    public BestiaryScreen() {
        super(NarratorManager.EMPTY);
        this.pageContent = BestiaryScreen.PageContent.EMPTY;
        this.pageIndicatorText = ScreenTexts.EMPTY;
    }

    private int countPages() {
        return this.pages.size();
    }

    protected void init() {
        this.invalidatePageContent();
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("bestiary.close"), (button -> close())).dimensions(this.width / 2 - 100, 196, 98, 20).build());
        int i = (this.width - WIDTH) / 2;
        int j = Math.max(153, (this.height - HEIGHT) / 2 - this.HEIGHT / 4 + 151);
        this.nextPageButton = this.addDrawableChild(new BestiaryTurnWidget(i + WIDTH - 31, j, true, (button) -> this.openNextPage(), true));
        this.previousPageButton = this.addDrawableChild(new BestiaryTurnWidget(i + 11, j - 1, false, (button) -> this.openPreviousPage(), true));
        this.updateButtons();
    }

    private void openPreviousPage() {
        if (this.currentPage > 0) {
            --this.currentPage;
        }

        this.updateButtons();
        this.changePage();
    }

    private void openNextPage() {
        if (this.currentPage < this.countPages() - 1) {
            ++this.currentPage;
        } else {
            this.appendNewPage();
            if (this.currentPage < this.countPages() - 1) {
                ++this.currentPage;
            }
        }

        this.updateButtons();
        this.changePage();
    }

    private void updateButtons() {
//        this.previousPageButton.visible = this.currentPage > 0;
//        this.nextPageButton.visible = this.currentPage < this.countPages() - 1;
        this.previousPageButton.visible = true;
        this.nextPageButton.visible = true;
    }

    private void appendNewPage() {
        if (this.countPages() < 100) {
            this.pages.add("");
        }
    }

    private String getCurrentPageContent() {
        return this.currentPage >= 0 && this.currentPage < this.pages.size() ? this.pages.get(this.currentPage) : "";
    }

    private void setPageContent(String newContent) {
        if (this.currentPage >= 0 && this.currentPage < this.pages.size()) {
            this.pages.set(this.currentPage, newContent);
            this.invalidatePageContent();
        }
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        this.setFocused(null);
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int i = (this.width - WIDTH) / 2;
        int j = Math.max(2, (this.height - HEIGHT) / 2 - this.HEIGHT / 4);
        this.drawTexture(matrices, i, j, 0, 0, WIDTH, HEIGHT);
        int l;
        int m;
        int n = this.textRenderer.getWidth(this.pageIndicatorText);
        this.textRenderer.draw(matrices, this.pageIndicatorText, (float)(i - n + WIDTH - 44), Math.max(18, (this.height - HEIGHT) / 2 - this.HEIGHT / 4 + 20), 0);
        BestiaryScreen.PageContent pageContent = this.getPageContent();
        BestiaryScreen.Line[] var15 = pageContent.lines;
        l = var15.length;

        for(m = 0; m < l; ++m) {
            BestiaryScreen.Line line = var15[m];
            this.textRenderer.draw(matrices, line.text, (float)line.x, (float)line.y, -16777216);
        }

        super.render(matrices, mouseX, mouseY, delta);
    }

    private void changePage() {
        this.invalidatePageContent();
    }

    private BestiaryScreen.PageContent getPageContent() {
        if (this.pageContent == null) {
            this.pageContent = this.createPageContent();
            this.pageIndicatorText = Text.translatable("book.pageIndicator", this.currentPage + 1, this.countPages());
        }
        return this.pageContent;
    }

    private void invalidatePageContent() {
        this.pageContent = null;
    }


    private BestiaryScreen.Position absolutePositionToScreenPosition(BestiaryScreen.Position position) {
        return new BestiaryScreen.Position(position.x + (this.width - WIDTH) / 2 + 36, position.y + 32);
    }

    private BestiaryScreen.PageContent createPageContent() {
        String string = this.getCurrentPageContent();
        if (string.isEmpty()) {
            return BestiaryScreen.PageContent.EMPTY;
        } else {
            List<BestiaryScreen.Line> list = Lists.newArrayList();
            MutableInt mutableInt = new MutableInt();
            MutableBoolean mutableBoolean = new MutableBoolean();
            TextHandler textHandler = this.textRenderer.getTextHandler();
            textHandler.wrapLines(string, 114, Style.EMPTY, true, (style, start, end) -> {
                int i = mutableInt.getAndIncrement();
                String stringx = string.substring(start, end);
                mutableBoolean.setValue(stringx.endsWith("\n"));
                String string2 = StringUtils.stripEnd(stringx, " \n");
                Objects.requireNonNull(this.textRenderer);
                int j = i * 9;
                BestiaryScreen.Position position = this.absolutePositionToScreenPosition(new BestiaryScreen.Position(0, j));
                list.add(new BestiaryScreen.Line(style, string2, position.x, position.y));
            });
            return new BestiaryScreen.PageContent(list.toArray(new Line[0]));
        }
    }

    @Environment(EnvType.CLIENT)
    record PageContent(Line[] lines) {
            static final BestiaryScreen.PageContent EMPTY;

        static {
                EMPTY = new BestiaryScreen.PageContent(new Line[]{new Line(Style.EMPTY, "", 0, 0)});
            }
        }

    @Environment(EnvType.CLIENT)
    static class Line {
        final Style style;
        final String content;
        final Text text;
        final int x;
        final int y;

        public Line(Style style, String content, int x, int y) {
            this.style = style;
            this.content = content;
            this.x = x;
            this.y = y;
            this.text = Text.literal(content).setStyle(style);
        }
    }

    @Environment(EnvType.CLIENT)
        private record Position(int x, int y) {
    }
}
