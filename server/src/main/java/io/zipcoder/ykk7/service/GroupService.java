package io.zipcoder.ykk7.service;

import io.zipcoder.ykk7.entity.Group;
import io.zipcoder.ykk7.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    private GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository){
        this.groupRepository = groupRepository;
    }


    public Iterable<Group> findAll() {
        return groupRepository.findAll();
    }

    public Group findOne(long id) {
        return groupRepository.findOne(id);
    }

    public void save(Group group) {
        groupRepository.save(group);
    }

    public void delete(long id) {
        groupRepository.delete(id);
    }
}
