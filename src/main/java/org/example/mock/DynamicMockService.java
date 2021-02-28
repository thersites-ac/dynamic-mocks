package org.example.mock;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/mock")
@Component
public class DynamicMockService {

    @Autowired
    private MockObjectManager mockObjectManager;

    private static final Logger logger = LoggerFactory.getLogger(DynamicMockService.class);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{handle}")
    public void setMock(@PathParam("handle") String handle, String jsonString) throws JsonProcessingException {
        logger.info("Input string: {}", jsonString);
        ObjectMapper objectMapper = new ObjectMapper();

        Class<?> type = mockObjectManager.lookupMockType(handle);

        if (type != null) {
            Object result = objectMapper.readValue(jsonString, type);
            logger.info("Read the {} {}", type.getSimpleName(), result.toString());
            mockObjectManager.putMock(handle, result);
        }
    }

    @DELETE
    @Path("/{handle}")
    public void deleteMock(@PathParam("handle") String handle) {
        mockObjectManager.deleteMock(handle);
    }

}