/*
 * MIT License
 *
 * Copyright (c) 2018 creeper123123321 and contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.creeper123123321.viarift.gui.multiplayer;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import us.myles.ViaVersion.api.protocol.ProtocolRegistry;
import us.myles.ViaVersion.api.protocol.ProtocolVersion;

public class SaveProtocolButton extends GuiButton {
    private GuiTextField textField;

    public SaveProtocolButton(int id, int x, int y, int width, int height, String text, GuiTextField tf) {
        super(id, x, y, width, height, text);
        textField = tf;
    }

    @Override
    public void onClick(double p_mouseClicked_1_, double p_mouseClicked_3_) {
        super.onClick(p_mouseClicked_1_, p_mouseClicked_3_);
        try {
            ProtocolRegistry.SERVER_PROTOCOL = Integer.parseInt(textField.getText());
        } catch (NumberFormatException e) {
            try {
                ProtocolRegistry.SERVER_PROTOCOL = ProtocolVersion.getClosest(textField.getText()).getId();
            } catch (NullPointerException ignored) {
            }
        }
        textField.setText(ProtocolVersion.isRegistered(ProtocolRegistry.SERVER_PROTOCOL)
                ? ProtocolVersion.getProtocol(ProtocolRegistry.SERVER_PROTOCOL).getName()
                : Integer.toString(ProtocolRegistry.SERVER_PROTOCOL));
    }
}
