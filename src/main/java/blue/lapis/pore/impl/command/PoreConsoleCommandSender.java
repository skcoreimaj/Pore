/*
 * Pore
 * Copyright (c) 2014-2015, Lapis <https://github.com/LapisBlue>
 *
 * The MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package blue.lapis.pore.impl.command;

import blue.lapis.pore.converter.wrapper.PoreConverter;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.spongepowered.api.util.command.source.ConsoleSource;

public class PoreConsoleCommandSender extends PoreCommandSender implements ConsoleCommandSender {

    public static PoreConsoleCommandSender of(ConsoleSource handle) {
        return PoreConverter.of(PoreConsoleCommandSender.class, handle);
    }

    protected PoreConsoleCommandSender(ConsoleSource handle) {
        super(handle);
    }

    @Override
    public ConsoleSource getHandle() {
        return (ConsoleSource) super.getHandle();
    }

    @Override
    public boolean isConversing() {
        throw new UnsupportedOperationException(); // TODO
    }

    @Override
    public void acceptConversationInput(String input) {
        throw new UnsupportedOperationException(); // TODO
    }

    @Override
    public boolean beginConversation(Conversation conversation) {
        throw new UnsupportedOperationException(); // TODO
    }

    @Override
    public void abandonConversation(Conversation conversation) {
        throw new UnsupportedOperationException(); // TODO
    }

    @Override
    public void abandonConversation(Conversation conversation, ConversationAbandonedEvent details) {
        throw new UnsupportedOperationException(); // TODO
    }

    @Override
    public void sendRawMessage(String message) {
        throw new UnsupportedOperationException(); // TODO
    }
}
