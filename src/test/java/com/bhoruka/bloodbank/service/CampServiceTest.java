package com.bhoruka.bloodbank.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import com.bhoruka.bloodbank.TestCampConstants;
import com.bhoruka.bloodbank.dao.CampDao;
import com.bhoruka.bloodbank.exception.CampCreationFailedException;

import com.bhoruka.bloodbank.exception.GetCampDetailsFailedException;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CampServiceTest {

    @Mock
    private CampDao campDao;

    @InjectMocks
    private CampService campService;

    @Before
    public void setup() {
        campService = new CampService(campDao);
    }

    @Test(expected = NullPointerException.class)
    public void constructor_nullValue_throwsNullPointerException() {
        campService = new CampService(null);
    }

    @Test
    public void createCamp_success() {
        when(campDao.createCamp(ArgumentMatchers.any())).thenReturn(TestCampConstants.VALID_CAMP_MODEL);

        assertThat(campService.createCamp(TestCampConstants.CREATE_CAMP_REQUEST), is(TestCampConstants.TEST_CAMP_ID));
    }

    @Test(expected = CampCreationFailedException.class)
    public void createCamp_failed_throwsCampCreationFailedException() {
        when(campDao.createCamp(ArgumentMatchers.any())).thenReturn(TestCampConstants.CAMP_MODEL_WITHOUT_ID);

        campService.createCamp(TestCampConstants.CREATE_CAMP_REQUEST);
    }

    @Test(expected = NullPointerException.class)
    public void createCamp_nullValue_throwsNullPointerException() {
        campService.createCamp(null);
    }

    @Test
    public void getCamp_success() {
        when(campDao.getCamp(ArgumentMatchers.any()))
                .thenReturn(TestCampConstants.GET_VALID_CAMP_DETAILS);

        assertThat(campService.getCamp(TestCampConstants.GET_CAMP_REQUEST), is(TestCampConstants.VALID_GET_CAMP_MODEL));
    }

    @Test(expected = GetCampDetailsFailedException.class)
    public void getCamp_failed_throwsGetCampDetailsFailedException() {
        when(campDao.getCamp(ArgumentMatchers.any())).thenReturn(Optional.empty());

        campService.getCamp(TestCampConstants.GET_CAMP_REQUEST);
    }

    @Test(expected = NullPointerException.class)
    public void getCamp_nullValue_throwsNullPointerException() {
        campService.getCamp(null);
    }
}
