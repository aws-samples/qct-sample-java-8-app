package com.bhoruka.bloodbank.dao;

import com.bhoruka.bloodbank.dao.entity.Camp;
import com.bhoruka.bloodbank.dao.repository.CampRepository;
import com.bhoruka.bloodbank.model.CampModel;

import java.util.Optional;

import lombok.NonNull;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CampDao {

    @NonNull
    private CampRepository campRepository;

    /**
     * Constructor for camp service.
     *
     * @param  campRepository a new camp repository
     */
    @Autowired
    public CampDao(@NonNull final CampRepository campRepository) {
        this.campRepository = campRepository;
    }

    /**
     * Checks if a camp exists in the Camp table.
     * @param campId id of the camp
     * @return true if camp exists, false otherwise
     */
    public Boolean campExists(@NonNull final String campId) {
        return campRepository.existsById(campId);
    }

    /**
     * Fetches a camp from the Camp table.
     * @param campId id of the camp
     * @return Optional containing the CampModel or empty if camp does not exist in the db
     */
    public Optional<CampModel> getCamp(@NonNull final String campId) {
        Optional<Camp> campOptional = campRepository.findById(campId);

        if (!campOptional.isPresent()) {
            log.info("Unable to find camp with id : {}", campId);
            return Optional.empty();
        }
        Camp camp = campOptional.get();
        return Optional.of(CampModel.builder()
                .id(camp.getId())
                .actualNoOfDonor(camp.getActualNoOfDonor())
                .dateOfCamp(camp.getDateOfCamp())
                .expectedNoOfDonor(camp.getExpectedNoOfDonor())
                .partnerId(camp.getPartnerId())
                .build());
    }

    /**
     * Creates a new camp in the Camp table.
     * @param campModel model containing the camp information
     * @return a CampModel with the id field populated
     */
    public CampModel createCamp(@NonNull final CampModel campModel) {
        Camp camp = new Camp();
        camp.setExpectedNoOfDonor(campModel.getExpectedNoOfDonor());
        camp.setDateOfCamp(campModel.getDateOfCamp());
        camp.setPartnerId(campModel.getPartnerId());

        log.info("Creating new camp : {}", camp);
        camp = campRepository.save(camp);
        log.info("Camp created successfully : {}", camp);

        return campModel.withId(camp.getId());
    }
}
