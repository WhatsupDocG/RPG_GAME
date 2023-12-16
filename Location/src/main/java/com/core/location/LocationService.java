package com.core.location;

import com.core.location.web.LocationReq;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
public class LocationService {

    private final LocationRepo locationRepo;
    
    private final MessageSource messageSource;

    public LocationService(LocationRepo locationRepo, MessageSource messageSource){
        this.locationRepo = locationRepo;
        this.messageSource = messageSource;
    }

    public Location getLocationById(Integer id){
        return locationRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageSource
                        .getMessage("com.core.location.web.LocationWithIdNotExists", new Object[]{id},
                                LocaleContextHolder.getLocale())));
    }

    public Page<Location> findAllLocation(Pageable pageable){
        return locationRepo.findAll(pageable);
    }

    public Location createLocation(LocationReq req) {
        Location location = new Location();
        this.prepareLocation(location,req);
        locationRepo.save(location);
        return location;
    }

    @Transactional
    public void deleteLocation(Integer id) {
        try {
            locationRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageSource
                    .getMessage("com.core.location.web.LocationWithIdNotExists", new Object[]{id},
                            LocaleContextHolder.getLocale()));
        }
    }

    public Location updateLocation(Location location, LocationReq req){
        this.prepareLocation(location,req);
        locationRepo.save(location);
        return location;
    }
    public void prepareLocation(Location location, LocationReq locationBaseReq) {
        location.setName(locationBaseReq.getName());
    }

}
