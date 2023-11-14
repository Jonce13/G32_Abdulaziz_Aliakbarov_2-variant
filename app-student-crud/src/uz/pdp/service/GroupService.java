package uz.pdp.service;

import uz.pdp.payload.GroupDTO;

public interface GroupService {
    boolean remove(Integer id);
    GroupDTO edit(Integer id, GroupDTO groupDTO);
    GroupDTO add(GroupDTO groupDTO);
    GroupDTO get(Integer id);
}
