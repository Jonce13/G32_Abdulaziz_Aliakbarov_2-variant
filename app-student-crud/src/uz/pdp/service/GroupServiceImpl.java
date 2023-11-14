package uz.pdp.service;

import uz.pdp.entity.Adress;
import uz.pdp.entity.Group;
import uz.pdp.payload.GroupDTO;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GroupServiceImpl implements GroupService{
    private static GroupServiceImpl instance;
    private static Lock lock = new ReentrantLock();
    public List<Group> groups = Collections.synchronizedList(new ArrayList<>());

    private GroupServiceImpl() {}

    public static GroupServiceImpl getInstance() {
        if (Objects.isNull(instance)) {
            lock.lock();
            if (Objects.isNull(instance))
                instance = new GroupServiceImpl();
            lock.unlock();
        }
        return instance;
    }

    @Override
    public boolean remove(Integer id) {
        Group group = findById(id);
        groups.remove(group);
        return true;
    }

    @Override
    public GroupDTO edit(Integer id, GroupDTO groupDTO) {
        Group group = findById(id);
        group.setName(groupDTO.getName());

        return groupDTO;
    }

    @Override
    public GroupDTO add(GroupDTO groupDTO) {
        int id=groups.size()+1;
        Group group=new Group(id,groupDTO.getName());
        groupDTO.setId(id);
        return groupDTO;
    }

    @Override
    public GroupDTO get(Integer id) {
        Group byId = findById(id);
        return new GroupDTO(byId.getId(),byId.getName());
    }
    private Group findById(Integer id) {
        Optional<Group> optionalGroup = groups.stream()
                .filter(adress -> adress.getId().equals(id))
                .findFirst();
        return optionalGroup.orElseThrow(() -> new RuntimeException("Group is not found with id: " + id));
    }
}
