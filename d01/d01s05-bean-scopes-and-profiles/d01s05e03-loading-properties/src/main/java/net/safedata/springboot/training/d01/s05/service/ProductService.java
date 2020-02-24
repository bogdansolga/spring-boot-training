package net.safedata.springboot.training.d01.s05.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * A simple product {@link Service}, which wires the configured properties using the {@link Value} annotation
 *
 * @author bogdan.solga
 */
@Service
public class ProductService {

    @Value("${remote.endpoint.url}")
    private String remoteEndpointURL;

    @Value("${metrics.enabled}")
    private boolean metricsEnabled;

    @Value("${connection.timeout}")
    private int connectionTimeout;

    @Value("${version.number:2.5}") //:2.5 = the default value
    private double versionNumber;

    @Value("${external.property}")
    private String externalProperty;

    @Value("${array.of.strings}")
    private String[] arrayOfStrings;

    @Value("${a.value.on.more.than.one.line}")
    private String aValueOnMultipleLines;

    public void displayLoadedProperties() {
        System.out.println("The remote endpoint is '" + remoteEndpointURL + "'");
        System.out.println("The metrics are enabled: " + metricsEnabled);
        System.out.println("The connection timeout is " + connectionTimeout);
        System.out.println("The version number is " + versionNumber);

        System.out.println("An array of Strings: " + Arrays.asList(arrayOfStrings));

        System.out.println();
        System.out.println("The external property is '" + externalProperty + "'");

        System.out.println("The value on multiple lines is '" + aValueOnMultipleLines + "'");
    }
}
