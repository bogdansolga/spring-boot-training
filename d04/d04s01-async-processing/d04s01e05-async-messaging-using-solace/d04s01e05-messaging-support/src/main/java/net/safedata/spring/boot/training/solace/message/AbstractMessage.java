package net.safedata.spring.boot.training.solace.message;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class AbstractMessage<T extends AbstractMessageType> implements Serializable {

    private final long messageId;
    private final LocalDateTime creationDateTime;

    AbstractMessage(final long messageId) {
        this.messageId = messageId;
        this.creationDateTime = LocalDateTime.now();
    }

    public abstract String getName();

    public long getMessageId() {
        return messageId;
    }

    public LocalDateTime creationDateTime() {
        return creationDateTime;
    }
}
