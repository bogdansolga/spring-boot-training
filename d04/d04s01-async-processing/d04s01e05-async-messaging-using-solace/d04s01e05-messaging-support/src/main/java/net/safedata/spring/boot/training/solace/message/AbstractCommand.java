package net.safedata.spring.boot.training.solace.message;

public abstract class AbstractCommand extends AbstractMessage<AbstractMessageType.CommandMessage> {

    public AbstractCommand(long messageId) {
        super(messageId);
    }
}
