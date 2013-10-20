/*
 * This file is part of ${name}.
 *
 * ${copyright} <http://www.greatmancode.com/>
 *
 * ${name} is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ${name} is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with ${name}.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.greatmancode.ircserver.api.net.interfaces;

import java.io.IOException;

public abstract class MessageCodec<T extends Message> {
    private final Class<T> clazz;
    private boolean dynamic;
    private String opcode;

    public MessageCodec(Class<T> clazz, String opcode) {
        this.clazz = clazz;
        this.opcode = opcode;
    }

    public final Class<T> getType() {
        return clazz;
    }

    public final String getOpcode() {
        return opcode;
    }

    void setOpcode(String opcode) {
        this.opcode = opcode;
    }

    public String encode(T message) throws IOException {
        return null;
    }

    public T decode(String[] buffer) throws IOException {
        return null;
    }
}
