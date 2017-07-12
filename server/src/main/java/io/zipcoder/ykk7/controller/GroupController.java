package io.zipcoder.ykk7.controller;

import io.zipcoder.ykk7.entity.Group;
import io.zipcoder.ykk7.repository.GroupRepository;
import io.zipcoder.ykk7.service.GroupService;
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
@RequestMapping("/groups")
public class GroupController {

    private final Logger LOG = LoggerFactory.getLogger(GroupController.class);

    private GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService){
        this.groupService = groupService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = {"http://localhost:8100","file://"})
    public ResponseEntity<List<Group>> getAll() {
        LOG.info("getting all groups");
        List<Group> groups = new ArrayList<>();

        groupService.findAll().forEach(groups::add);

        if (groups == null || groups.isEmpty()){
            LOG.info("no groups found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Group> get(@PathVariable("id") long id){
        LOG.info("getting group with id: {}", id);
        Group group = groupService.findOne(id);

        if (group == null){
            LOG.info("group with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody Group group, UriComponentsBuilder ucBuilder){
        LOG.info("creating new group: {}", group);

        groupService.save(group);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/groups/{id}").buildAndExpand(group.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Group> update(@PathVariable long id, @RequestBody Group group){
        LOG.info("updating group: {}", group);
        Group currentGroup = groupService.findOne(id);

        if (currentGroup == null){
            LOG.info("Group with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentGroup.setId(group.getId());
        currentGroup.setName(group.getName());
        currentGroup.setLocations(group.getLocations());
        currentGroup.setSubGroups(group.getSubGroups());

        groupService.save(group);
        return new ResponseEntity<>(currentGroup, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") long id){
        LOG.info("deleting group with id: {}", id);
        Group group = groupService.findOne(id);

        if (group == null){
            LOG.info("Unable to delete. Group with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        groupService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
