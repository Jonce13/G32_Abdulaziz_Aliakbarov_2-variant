package uz.pdp.service;

import uz.pdp.entity.Adress;
import uz.pdp.payload.AdressDTO;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.*;

public class AdressServiceImpl implements AdressService{

    private static AdressServiceImpl instance;
    private static Lock lock = new ReentrantLock();
    public List<Adress> adresses = Collections.synchronizedList(new ArrayList<>());

    private AdressServiceImpl() {}

    public static AdressServiceImpl getInstance() {
        if (Objects.isNull(instance)) {
            lock.lock();
            if (Objects.isNull(instance))
                instance = new AdressServiceImpl();
            lock.unlock();
        }
        return instance;
    }


    @Override
    public boolean remove(Integer id) {
        Adress adress = findById(id);
        adresses.remove(adress);
        return true;
    }

    @Override
    public AdressDTO edit(Integer id, AdressDTO adressDTO) {
        Adress adress = findById(id);
        adress.setAdressLine(adressDTO.getAdressLine());
        adress.setCity(adressDTO.getCity());
        adress.setRegion(adressDTO.getRegion());

        return adressDTO;
    }

    @Override
    public AdressDTO add(AdressDTO adressDTO) {
        int id=adresses.size()+1;
        Adress adress=new Adress(id,adressDTO.getRegion(),adressDTO.getCity(),adressDTO.getAdressLine());
        adresses.add(adress);
        adressDTO.setId(id);



        return adressDTO;
    }

    @Override
    public AdressDTO get(Integer id) {
        Adress adress = findById(id);
        return new AdressDTO(id,adress.getRegion(), adress.getCity(), adress.getAdressLine());
    }

    private Adress findById(Integer id) {
        Optional<Adress> optionalAdress = adresses.stream()
                .filter(adress -> adress.getId().equals(id))
                .findFirst();
        return optionalAdress.orElseThrow(() -> new RuntimeException("Adress is not found with id: " + id));
    }
}
