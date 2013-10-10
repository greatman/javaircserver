package com.greatmancode.ircserver.api.net;

import com.greatmancode.ircserver.api.net.interfaces.Message;
import com.greatmancode.ircserver.api.net.interfaces.MessageCodec;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CodecLookupService {

    private final ConcurrentMap<Class<? extends Message>, MessageCodec<?>> classTable = new ConcurrentHashMap<>();;
    private ConcurrentMap<String, MessageCodec<?>> codecList = new ConcurrentHashMap<String, MessageCodec<?>>();
    private ClassLoader loader;
    protected CodecLookupService(ClassLoader loader) {
        this.loader = loader;
    }
    protected <T extends Message, C extends MessageCodec<T>> C bind(Class<C> clazz) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        Constructor<C> constructor;
        try {
            constructor = clazz.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("No default constructor for class" + clazz.getName());
        }
        final C codec;
        codec = constructor.newInstance();
        final MessageCodec<?> prevCodec = find(codec.getOpcode());
        if (prevCodec != null) {
            throw new IllegalStateException("Trying to bind a static opcode where one already exists. Static: " + clazz.getSimpleName() + " Other: " + prevCodec.getClass().getSimpleName());
        }
        register(codec);
        return codec;
    }

    private <T extends Message, C extends MessageCodec<T>> void register(C codec) {
        codecList.put(codec.getOpcode(), codec);
        classTable.put(codec.getType(), codec);
    }

    /**
     * Retrieve the {@link MessageCodec} from the lookup table
     * @param opcode The opcode which the codec uses
     * @return The codec, null if not found
     */
    public MessageCodec<?> find(String opcode) {
        return codecList.get(opcode);
    }

    /**
     * Finds a codec by message class.
     *
     * @param clazz The message class.
     * @param <T> The type of message.
     * @return The codec, or {@code null} if it could not be found.
     */
    public <T extends Message> MessageCodec<T> find(Class<T> clazz) {
        return (MessageCodec<T>) classTable.get(clazz);
    }
}
