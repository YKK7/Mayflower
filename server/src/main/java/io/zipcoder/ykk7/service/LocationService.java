package io.zipcoder.ykk7.service;


import io.zipcoder.ykk7.entity.Location;
import io.zipcoder.ykk7.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    private LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository){
        this.locationRepository = locationRepository;
    }

    public Iterable<Location> findAll() {
        return locationRepository.findAll();
    }

    public Location findOne(long id) {
        return locationRepository.findOne(id);
    }

    public void save(Location location) {
        locationRepository.save(location);
    }

    public void delete(long id) {
        locationRepository.delete(id);
    }
}
