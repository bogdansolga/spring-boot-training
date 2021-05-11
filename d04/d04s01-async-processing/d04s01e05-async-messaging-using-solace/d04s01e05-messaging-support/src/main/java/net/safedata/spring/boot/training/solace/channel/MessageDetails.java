package net.safedata.spring.boot.training.solace.channel;

public @interface MessageDetails {
    Service publisher();

    Service[] subscribers();

    Channel channel();
}
