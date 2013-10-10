package com.greatmancode.ircserver.api.net;

import com.greatmancode.ircserver.api.net.interfaces.Message;
import com.greatmancode.ircserver.api.net.interfaces.MessageHandler;

import java.util.HashMap;
import java.util.Map;

public class HandlerLookupService {

    private final Map<Class<? extends Message>, MessageHandler<?>> handlers = new HashMap<>();

    protected <T extends Message> void bind(Class<T> clazz, Class<? extends MessageHandler<T>> handlerClass) throws InstantiationException, IllegalAccessException {
        MessageHandler<T> handler = handlerClass.newInstance();
        handlers.put(clazz, handler);
    }

    protected <T extends Message> void bind(Class<T> clazz, MessageHandler<T> handler) {
        handlers.put(clazz, handler);
    }

    @SuppressWarnings ("unchecked")
    public <T extends Message> MessageHandler<T> find(Class<T> clazz) {
        return (MessageHandler<T>) handlers.get(clazz);
    }

    protected HandlerLookupService() {
    }
}
