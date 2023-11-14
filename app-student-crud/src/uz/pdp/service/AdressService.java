package uz.pdp.service;

import uz.pdp.payload.AdressDTO;

public interface AdressService {
    boolean remove(Integer id);
    AdressDTO edit(Integer id, AdressDTO adressDTO);
    AdressDTO add(AdressDTO adressDTO);
    AdressDTO get(Integer id);


}
