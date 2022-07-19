package com.victor.bonitasoft.connectors;

import org.bonitasoft.engine.connector.ConnectorException;
import org.bonitasoft.engine.connector.ConnectorValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IntegerAdderConnectorTest {

    IntegerAdderConnector connector;

    @BeforeEach
    void setUp() {
        connector = new IntegerAdderConnector();
    }

    @Test
    void should_throw_exception_if_mandatory_input_is_missing() {
        assertThrows(ConnectorValidationException.class, () ->
                connector.validateInputParameters()
        );
    }

    @Test
    void should_throw_exception_if_mandatory_input_1_is_empty() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("firstInteger", "");
        connector.setInputParameters(parameters);
        assertThrows(ConnectorValidationException.class, () ->
                connector.validateInputParameters()
        );
    }
    @Test
    void should_throw_exception_if_mandatory_input_2_is_empty() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("secondInteger", "");
        connector.setInputParameters(parameters);
        assertThrows(ConnectorValidationException.class, () ->
                connector.validateInputParameters()
        );
    }

    @Test
    void should_throw_exception_if_mandatory_input_1_is_not_an_integer() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("firstInteger", 50);
        connector.setInputParameters(parameters);
        assertThrows(ConnectorValidationException.class, () ->
                connector.validateInputParameters()
        );
    }
    
    @Test
    void should_throw_exception_if_mandatory_input_2_is_not_an_integer() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("secondInteger", 10);
        connector.setInputParameters(parameters);
        assertThrows(ConnectorValidationException.class, () ->
                connector.validateInputParameters()
        );
    }

}
