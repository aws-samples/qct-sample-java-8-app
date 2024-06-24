package com.bhoruka.bloodbank.web;

import com.bhoruka.bloodbank.exception.CampCreationFailedException;
import com.bhoruka.bloodbank.exception.GetCampDetailsFailedException;
import com.bhoruka.bloodbank.model.CampModel;
import com.bhoruka.bloodbank.model.request.CreateCampRequest;
import com.bhoruka.bloodbank.model.request.GetCampRequest;
import com.bhoruka.bloodbank.model.response.CreateCampResponse;
import com.bhoruka.bloodbank.model.response.GetCampResponse;
import com.bhoruka.bloodbank.model.response.Response;
import com.bhoruka.bloodbank.service.CampService;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/camp")
@Slf4j
public class CampController {

    private static final String CAMP_CREATE_SUCCESS_MESSAGE = "Camp created successfully.";

    private static final String CAMP_GET_SUCCESS_MESSAGE = "All values of camp fetch successful";

    @NonNull
    private CampService campService;

    /**
     * Constructor for the camp controller.
     * @param campService mandatory instance of CampService
     */
    @Autowired
    public CampController(@NonNull final CampService campService) {
        this.campService = campService;
    }

    /**
     * Controller endpoint for creating a new camp.
     * @param createCampRequest the incoming create request
     * @return the response with the newly created camp id
     */
    @PostMapping("/create")
    public Response<CreateCampResponse> createCamp(@RequestBody final CreateCampRequest createCampRequest) {
        log.info("Received create request : {}", createCampRequest);

        Response<CreateCampResponse> response = null;
        try {
            String campId = campService.createCamp(createCampRequest);
            response = Response.<CreateCampResponse>builder()
                    .data(CreateCampResponse.builder()
                            .campId(campId)
                            .build())
                    .description(CAMP_CREATE_SUCCESS_MESSAGE)
                    .status(HttpStatus.OK.value())
                    .build();
        } catch (CampCreationFailedException e) {
            response = Response.<CreateCampResponse>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .errorMessage(e.getMessage())
                    .build();
        }
        return response;
    }

    /**
     * Controller endpoint for getting data of camp.
     * @param getCampRequest the incoming get request
     * @return the response with the get output values
     */
    @GetMapping("/get")
    public Response<GetCampResponse> getCamp(@RequestBody final GetCampRequest getCampRequest) {
        log.info("Received get request : {}",getCampRequest);

        Response<GetCampResponse> response = null;
        try {
            CampModel getCamp = campService.getCamp(getCampRequest);

            response = Response.<GetCampResponse>builder()
                    .data(GetCampResponse.builder()
                            .id(getCamp.getId())
                            .partnerId(getCamp.getPartnerId())
                            .dateOfCamp(getCamp.getDateOfCamp())
                            .expectedNoOfDonors(getCamp.getExpectedNoOfDonor())
                            .actualNoOfDonors(getCamp.getActualNoOfDonor())
                            .build())
                    .description(CAMP_GET_SUCCESS_MESSAGE)
                    .status(HttpStatus.OK.value())
                    .build();
        } catch (GetCampDetailsFailedException e) {
            response = Response.<GetCampResponse>builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .errorMessage(e.getMessage())
                    .build();
        }
        return response;
    }
}
