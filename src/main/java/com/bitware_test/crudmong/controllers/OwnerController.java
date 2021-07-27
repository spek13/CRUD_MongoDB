package com.bitware_test.crudmong.controllers;

import com.bitware_test.crudmong.models.Owner;
import com.bitware_test.crudmong.repositorys.OwnerRepository;
import org.bson.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class OwnerController {
    @Autowired
    private OwnerRepository ownerRepository;

    @GetMapping("/owners")
    public Map<String, String > getAllOwners() {
        HashMap<String, String> map = new HashMap<>();
        List<Owner> owners = new ArrayList<Owner>();

        ownerRepository.findAll().forEach(owners::add);
        if (owners.isEmpty()) {
            map.put("Cve_Error", "-4");
            map.put("Cve_Mensaje", "No hay clientes");
            return map;
        }
        map.put("Cve_Error", "0");
        map.put("Cve_Mensaje", "Lista de clientes");
        map.put("clientes", String.valueOf(owners));
        return map;
    }

    @GetMapping("/owners/{id}")
    public Map<String, String> getOwner(@PathVariable("id") String id) {
        HashMap<String, String> map = new HashMap<>();

        Optional<Owner> owner = ownerRepository.findById(id);

        if (owner.isPresent()) {
            map.put("Cve_Error", "0");
            map.put("Cve_Mensaje", "Cliente encontrado");
            map.put("clientes", String.valueOf(owner.get()));
            return  map;
        } else {
            map.put("Cve_Error", "-3");
            map.put("Cve_Mensaje", "Cliente no encontrado");
            return  map;
        }
    }

    @PostMapping("/owners/create")
    public Map<String, String> createOwner(@RequestBody Owner owner) {
        HashMap<String, String> map = new HashMap<>();
        try {
            ownerRepository.save(owner);
            map.put("Cve_Error", "0");
            map.put("Cve_Mensaje", "Cliente creado");
            map.put("cliente", String.valueOf(owner));
            return map;
        } catch (Exception e) {
            return typeError(e.getMessage().split(":")[0]);
        }
    }

    @PutMapping("/owners/update/{id}")
    public Map<String, String> updateOwner(@PathVariable("id") String id, @RequestBody Owner owner) {
        HashMap<String, String> map = new HashMap<>();
        try {
            owner.setClient_id(id);
            ownerRepository.save(owner);
            map.put("Cve_Error", "0");
            map.put("Cve_Mensaje", "Cliente actualizado");
            map.put("cliente", String.valueOf(owner));
            return map;
        } catch (Exception e) {
            return typeError(e.getMessage().split(":")[0]);
        }

    }

    @DeleteMapping("/owners/delete/{id}")
    public Map<String, String> deleteUser (@PathVariable("id") String id ){
        HashMap<String, String> map =new HashMap<>();
        try {
            ownerRepository.deleteById(id);

            map.put("Cve_Error", "0");
            map.put("Cve_Mensaje", "Cliente eliminado");
            return map;
        }catch (Exception e){
            return typeError(e.getMessage().split(":")[0]);
        }
    }


    private Map<String, String> typeError(String error) {
        HashMap<String, String> map = new HashMap<>();
        if (error.equals("E11000 duplicate key error collection")) {
            map.put("Cve_Error", "-2");
            map.put("Cve_Mensaje", "Valor duplicado");
            return  map;
        }
        map.put("Cve_Error", "-1");
        map.put("Cve_Mensaje", "La información no se guardo.");
        return map;
    }

}
