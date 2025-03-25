package org.javaguru.travel.insurance.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired private MockMvc mockMvc;

    @Autowired private JsonFileReader jsonFileReader;

    @Test
    public void personFirstNameNullTest() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_personFirstName_is_null.json",
                "rest/TravelCalculatePremiumResponse_personFirstName_is_null.json"
        );
    }

    @Test
    public void personLastNameNullTest() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_personLastName_is_null.json",
                "rest/TravelCalculatePremiumResponse_personLastName_is_null.json"
        );
    }

    @Test
    public void agreementDateFromNullTest() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_agreementDateFrom_is_null.json",
                "rest/TravelCalculatePremiumResponse_agreementDateFrom_is_null.json"
        );
    }

    @Test
    public void agreementDateToNullTest() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_agreementDateTo_is_null.json",
                "rest/TravelCalculatePremiumResponse_agreementDateTo_is_null.json"
        );
    }

    @Test
    public void allFieldsNullTest() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_all_fields_are_null.json",
                "rest/TravelCalculatePremiumResponse_all_fields_are_null.json"
        );
    }

    @Test
    public void agreementDateToLessThanDateFromTest() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_agreementDateTo_less_than_agreementDateFrom.json",
                "rest/TravelCalculatePremiumResponse_agreementDateTo_less_than_agreementDateFrom.json"
        );
    }

    @Test
    public void allFieldsOkTest() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremiumRequest_all_fields_are_ok.json",
                "rest/TravelCalculatePremiumResponse_all_fields_are_ok.json"
        );
    }

    private void executeAndCompare(String jsonRequestFilePath,
                                   String jsonResponseFilePath) throws Exception {
        String jsonRequest = jsonFileReader.readJsonFromFile(jsonRequestFilePath);

        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonRequest)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String responseBodyContent = result.getResponse().getContentAsString();

        String jsonResponse = jsonFileReader.readJsonFromFile(jsonResponseFilePath);

        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(responseBodyContent), mapper.readTree(jsonResponse));
    }
}