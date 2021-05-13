package net.safedata.spring.boot.training.solace.message;

public abstract class AbstractDomainEvent extends AbstractMessage<AbstractMessageType.DomainEventMessage> {

    public AbstractDomainEvent(long messageId) {
        super(messageId);
    }
}
