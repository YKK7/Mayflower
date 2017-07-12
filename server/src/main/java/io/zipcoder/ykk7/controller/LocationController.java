package io.zipcoder.ykk7.controller;

import io.zipcoder.ykk7.entity.Location;
import io.zipcoder.ykk7.service.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final Logger LOG = LoggerFactory.getLogger(LocationController.class);

    private LocationService locationService;

    @Autowired
    public LocationController(LocationService locationRepo){
        this.locationService = locationRepo;
    }

    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = {"http://localhost:8100","file://"})
    public ResponseEntity<List<Location>> getAll() {
        LOG.info("getting all locations");
        List<Location> locations = new ArrayList<>();

        locationService.findAll().forEach(locations::add);

        if (locations == null || locations.isEmpty()){
            LOG.info("no locations found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Location> get(@PathVariable("id") long id){
        LOG.info("getting location with id: {}", id);
        Location location = locationService.findOne(id);

        if (location == null){
            LOG.info("location with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody Location location, UriComponentsBuilder ucBuilder){
        LOG.info("creating new location: {}", location);

        locationService.save(location);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/locations/{id}").buildAndExpand(location.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Location> update(@PathVariable long id, @RequestBody Location location){
        LOG.info("updating location: {}", location);
        Location currentLocation = locationService.findOne(id);

        if (currentLocation == null){
            LOG.info("Location with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentLocation.setId(location.getId());
        currentLocation.setName(location.getName());
        currentLocation.setAddresses(location.getAddresses());
        currentLocation.setGroup(location.getGroup());

        locationService.save(location);
        return new ResponseEntity<>(currentLocation, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") long id){
        LOG.info("deleting location with id: {}", id);
        Location location = locationService.findOne(id);

        if (location == null){
            LOG.info("Unable to delete. Location with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        locationService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
