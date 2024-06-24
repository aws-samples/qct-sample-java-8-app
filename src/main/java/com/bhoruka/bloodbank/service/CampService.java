package com.bhoruka.bloodbank.service;

import com.bhoruka.bloodbank.dao.CampDao;
import com.bhoruka.bloodbank.exception.CampCreationFailedException;
import com.bhoruka.bloodbank.exception.GetCampDetailsFailedException;
import com.bhoruka.bloodbank.model.CampModel;
import com.bhoruka.bloodbank.model.request.CreateCampRequest;

import com.bhoruka.bloodbank.model.request.GetCampRequest;
import java.util.Optional;
import lombok.NonNull;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CampService {

    private static final String NULL_CAMP_ID_ERROR_MESSAGE = "Unable to create camp.";

    private static final String NULL_CAMP_GET_ERROR_MESSAGE = "Unable to get camp details."; //Get API by Anubrata

    @NonNull
    private CampDao campDao;

    /**
     * Constructor for camp service.
     *
     * @param  campDao a camp dao object
     */
    @Autowired
    public CampService(@NonNull final CampDao campDao) {
        this.campDao = campDao;
    }

    /**
     * Method to create a new camp.
     *
     * @param createCampRequest the create request received from the controller
     * @return camp id of the newly created camp
     * @throws CampCreationFailedException when id returned from the dao layer is null
     */
    public String createCamp(@NonNull final CreateCampRequest createCampRequest) throws CampCreationFailedException {
        //TODO check if partnerId exists in DB and throw exception if not
        CampModel createdCamp = campDao.createCamp(CampModel.builder()
                    .partnerId(createCampRequest.getPartnerId())
                    .expectedNoOfDonor(createCampRequest.getExpectedNoOfDonor())
                    .dateOfCamp(createCampRequest.getDateOfCamp())
                    .build());

        if (createdCamp.getId() == null) {
            throw new CampCreationFailedException(NULL_CAMP_ID_ERROR_MESSAGE);
        }
        return createdCamp.getId();
    }

    /**
     * Method to get data of an existing camp.
     *
     * @param getCampRequest gets the request to fetch data from Dao
     * @return CampModel object of an existing camp
     * @throws GetCampDetailsFailedException when no data is received from the dao layer
     */
    public CampModel getCamp(@NonNull final GetCampRequest getCampRequest) throws GetCampDetailsFailedException {
        Optional<CampModel> getCamp = campDao.getCamp(getCampRequest.getCampId());
        if (getCamp.isPresent()) {
            return getCamp.get();
        } else {
            throw new GetCampDetailsFailedException(NULL_CAMP_GET_ERROR_MESSAGE);
        }
    }
}
