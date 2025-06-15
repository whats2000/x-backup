package com.github.zly2006.xbackup.gui

import com.github.zly2006.xbackup.api.IBackup
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.text.Text

class RestoreInfoScreen(private val backup: IBackup) : Screen(Text.translatable("xb.gui.restore.title")) {
    override fun init() {
        addDrawableChild(
            ButtonWidget.builder(Text.translatable("xb.gui.restore.close")) {
                client?.setScreen(null)
            }.dimensions(width / 2 - 75, height - 28, 150, 20).build()
        )
    }

    override fun render(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        renderBackground(context, mouseX, mouseY, delta)
        super.render(context, mouseX, mouseY, delta)
        context.drawCenteredTextWithShadow(textRenderer, title, width / 2, 20, 0xFFFFFF)
        var y = height / 2 - 20
        val idText = Text.translatable("xb.gui.restore.id", backup.id)
        context.drawCenteredTextWithShadow(textRenderer, idText, width / 2, y, 0xFFFFFF)
        y += 12
        if (backup.comment.isNotEmpty()) {
            val comment = Text.translatable("xb.gui.restore.comment", backup.comment)
            context.drawCenteredTextWithShadow(textRenderer, comment, width / 2, y, 0xFFFFFF)
        }
    }

    companion object {
        fun open(backup: IBackup) {
            val client = MinecraftClient.getInstance()
            client.execute { client.setScreen(RestoreInfoScreen(backup)) }
        }
    }
}
