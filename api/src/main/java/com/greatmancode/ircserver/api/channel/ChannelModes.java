/*
 * This file is part of IRCServer API.
 *
 * Copyright (c) 2013-2013, Greatmancode <http://www.greatmancode.com/>
 *
 * IRCServer API is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * IRCServer API is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with IRCServer API.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.greatmancode.ircserver.api.channel;

import java.util.HashMap;
import java.util.Map;

public enum ChannelModes {
    
    BAN("b"),
    HALFOP("h"),
    INVITE_ONLY("i"),
    INVITATION_MASK("I"),
    KEYLOCK("k"),
    LIMIT("l"),
    MODERATED("m"),
    NO_EXTERNAL_MESAGES("n"),
    OPERATOR("o"),
    CREATOR("O"),
    QUIET("q"),
    SECRET("s"),
    TOPIC_LOCK("t"),
    VOICE("v");

    private static final Map<String, ChannelModes> modeList = new HashMap<String, ChannelModes>();
    private String value;

    ChannelModes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


    public static ChannelModes getValue(String letter) {
        return modeList.get(letter);
    }

    static {
        for (ChannelModes mode : ChannelModes.values()) {
            modeList.put(mode.getValue(), mode);
        }
    }

    public static String getConnectString() {
        String value = "";

        for (Map.Entry<String, ChannelModes> mode : modeList.entrySet()) {
            value += mode.getKey();
        }
        return value;
    }
}
