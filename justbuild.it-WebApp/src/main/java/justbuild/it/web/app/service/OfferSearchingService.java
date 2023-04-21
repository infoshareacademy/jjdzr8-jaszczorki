package justbuild.it.web.app.service;

import justbuild.it.web.app.entity.Offer;
import justbuild.it.web.app.repository.OfferFileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static justbuild.it.web.app.entity.constants.AppConstants.OFFERS_FILEPATH;

@Service
public class OfferSearchingService implements OfferSearchingServiceInterface {

    private final OfferFileRepository offerFileRepository;
    private final Logger LOGGER;

    public OfferSearchingService(OfferFileRepository offerFileRepository) {
        this.offerFileRepository = offerFileRepository;
        this.LOGGER = LoggerFactory.getLogger(OfferSearchingService.class);
    }

    @Override
    public List<Offer> getOffersList() {
        LOGGER.debug("Retrieving all offers from file {}", OFFERS_FILEPATH);
        return offerFileRepository.getOffersFromJsonFile(OFFERS_FILEPATH);
    }

    @Override
    public List<Offer> getOffersListFilteredBySearchValue(String searchValue) {
        LOGGER.debug("Retrieving offers from file {} filtered by search value '{}'", OFFERS_FILEPATH, searchValue);
        List<Offer> offers = offerFileRepository.getOffersFromJsonFile(OFFERS_FILEPATH);
        offers.sort(Comparator.comparing(Offer::getDate).reversed());

        return offers.stream()
                .filter(value -> value.getOfferContent().toLowerCase().contains(searchValue.toLowerCase())
                        || value.getOfferId().toString().contains(searchValue)
                        || value.getCity().toLowerCase().contains(searchValue.toLowerCase())
                        || value.getUser().getCompany().toLowerCase().contains(searchValue.toLowerCase())
                        || value.getUser().getLastName().toLowerCase().contains(searchValue.toLowerCase())
                        || value.getServiceCategory().toString().toLowerCase().contains(searchValue.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Offer> getOffersListFilteredByCategory(String category) {
        LOGGER.debug("Retrieving offers from file {} filtered by category '{}'", OFFERS_FILEPATH, category);
        List<Offer> offers = offerFileRepository.getOffersFromJsonFile(OFFERS_FILEPATH);
        offers.sort(Comparator.comparing(Offer::getDate).reversed());
        return offers.stream()
                .filter(value -> value.getServiceCategory().toString().equals(category))
                .collect(Collectors.toList());
    }
}