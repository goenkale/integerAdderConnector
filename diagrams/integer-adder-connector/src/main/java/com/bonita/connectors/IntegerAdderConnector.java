package com.bonita.connectors;

import java.util.logging.Logger;

import org.bonitasoft.engine.connector.AbstractConnector;
import org.bonitasoft.engine.connector.ConnectorException;
import org.bonitasoft.engine.connector.ConnectorValidationException;

public class IntegerAdderConnector extends AbstractConnector {

    private static final Logger LOGGER = Logger.getLogger(IntegerAdderConnector.class.getName());
    
    /**
     * Perform validation on the inputs defined on the connector definition (src/main/resources/integer-adder-connector.def)
     * You should: 
     * - validate that mandatory inputs are presents
     * - validate that the content of the inputs is coherent with your use case (e.g: validate that a date is / isn't in the past ...)
     */
    @Override
    public void validateInputParameters() throws ConnectorValidationException {
        checkMandatoryStringInput("firstInteger");
        checkMandatoryStringInput("secondInteger");
    }

    protected void checkMandatoryStringInput(String inputName) throws ConnectorValidationException {
        try {
            String value = (String) getInputParameter(inputName);
            if (value == null || value.isEmpty()) {
                throw new ConnectorValidationException(this,
                        String.format("Mandatory parameter '%s' is missing.", inputName));
            }
            Integer.parseInt(inputName);
        } catch (ClassCastException e) {
            throw new ConnectorValidationException(this, String.format("'%s' parameter must be an Integer", inputName));
        }
    }

    /**
     * Core method: 
     * - Execute all the business logic of your connector using the inputs (connect to an external service, compute some values ...).
     * - Set the output of the connector execution. If outputs are not set, connector fails.
     */
    @Override
    protected void executeBusinessLogic() throws ConnectorException {
    	String firstInteger = (String) getInputParameter("firstInteger");
    	String secondInteger = (String) getInputParameter("secondInteger");
        LOGGER.info(String.format("First integer: %s", firstInteger));
        LOGGER.info(String.format("Second integer: %s", secondInteger));
        setOutputParameter("result", String.format("%s", Integer.parseInt(firstInteger) + Integer.parseInt(secondInteger)));
    }
    
    /**
     * [Optional] Open a connection to remote server
     */
    @Override
    public void connect() throws ConnectorException{}

    /**
     * [Optional] Close connection to remote server
     */
    @Override
    public void disconnect() throws ConnectorException{}
}