package com.bhoruka.bloodbank;

import com.bhoruka.bloodbank.model.CampModel;
import com.bhoruka.bloodbank.model.request.CreateCampRequest;
import com.bhoruka.bloodbank.model.request.GetCampRequest;
import com.bhoruka.bloodbank.model.response.CreateCampResponse;
import com.bhoruka.bloodbank.model.response.GetCampResponse;
import com.bhoruka.bloodbank.model.response.Response;

import java.util.Date;
import java.util.Optional;

import org.springframework.http.HttpStatus;

public final class TestCampConstants {

    public static final Date TEST_DATE = new Date();

    public static final String TEST_CAMP_ID = "TestCamp1";

    public static final String TEST_PARTNER_ID = "ABC";

    public static final String CAMP_CREATE_SUCCESS_MESSAGE = "Camp created successfully.";

    public static final String GET_CAMP_SUCCESS_MESSAGE = "All values of camp fetch successful";

    public static final String NULL_CAMP_ID_ERROR_MESSAGE = "Unable to create camp.";

    public static final String GET_CAMP_ERROR_MESSAGE = "Unable to fetch camp details";

    public static final CreateCampRequest CREATE_CAMP_REQUEST = CreateCampRequest.builder()
            .partnerId(TEST_PARTNER_ID)
            .dateOfCamp(TEST_DATE)
            .expectedNoOfDonor(200L)
            .build();

    public static final CreateCampResponse CREATE_CAMP_RESPONSE = CreateCampResponse.builder()
            .campId(TEST_CAMP_ID)
            .build();

    public static final Response<CreateCampResponse> CREATE_CAMP_REST_RESPONSE = Response.<CreateCampResponse>builder()
            .data(CREATE_CAMP_RESPONSE)
            .status(HttpStatus.OK.value())
            .description(CAMP_CREATE_SUCCESS_MESSAGE)
            .build();

    public static final Response<CreateCampResponse> CREATE_CAMP_NULL_ID_ERROR_REST_RESPONSE = Response.<CreateCampResponse>builder()
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .errorMessage(NULL_CAMP_ID_ERROR_MESSAGE)
            .build();

    public static final CampModel CAMP_MODEL_WITHOUT_ID = CampModel.builder()
            .dateOfCamp(TEST_DATE)
            .partnerId(TEST_PARTNER_ID)
            .expectedNoOfDonor(200L)
            .build();

    public static final CampModel VALID_CAMP_MODEL = CampModel.builder()
            .dateOfCamp(TEST_DATE)
            .id(TEST_CAMP_ID)
            .partnerId(TEST_PARTNER_ID)
            .expectedNoOfDonor(200L)
            .build();

    public static final CampModel VALID_GET_CAMP_MODEL = CampModel.builder()
            .id(TEST_CAMP_ID)
            .actualNoOfDonor(200L)
            .dateOfCamp(TEST_DATE)
            .expectedNoOfDonor(200L)
            .partnerId(TEST_PARTNER_ID)
            .build();

    public static final GetCampRequest GET_CAMP_REQUEST = GetCampRequest.builder()
            .campId(TEST_CAMP_ID)
            .build();

    public static final GetCampResponse GET_CAMP_RESPONSE = GetCampResponse.builder()
            .id(TEST_CAMP_ID)
            .partnerId(TEST_PARTNER_ID)
            .dateOfCamp(TEST_DATE)
            .expectedNoOfDonors(200L)
            .actualNoOfDonors(200L)
            .build();

    public static final Response<GetCampResponse> GET_CAMP_REST_RESPONSE = Response.<GetCampResponse>builder()
            .data(GET_CAMP_RESPONSE)
            .status(HttpStatus.OK.value())
            .description(GET_CAMP_SUCCESS_MESSAGE)
            .build();

    public static final Response<GetCampResponse> GET_CAMP_ERROR_REST_RESPONSE = Response.<GetCampResponse>builder()
            .status(HttpStatus.NOT_FOUND.value())
            .errorMessage(GET_CAMP_ERROR_MESSAGE)
            .build();

    public static final Optional<CampModel> GET_VALID_CAMP_DETAILS = Optional.of(CampModel.builder()
            .id(TEST_CAMP_ID)
            .actualNoOfDonor(200L)
            .dateOfCamp(TEST_DATE)
            .expectedNoOfDonor(200L)
            .partnerId(TEST_PARTNER_ID)
            .build());

}
