package com.puntored.movies.service;

import com.puntored.movies.dto.ApiResponseDTO;
import com.puntored.movies.dto.InventoryRequestDTO;
import com.puntored.movies.entity.Film;
import com.puntored.movies.entity.Inventory;
import com.puntored.movies.entity.Store;
import com.puntored.movies.repository.FilmRepository;
import com.puntored.movies.repository.InventoryRepository;
import com.puntored.movies.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    FilmRepository filmRepository;

    public ApiResponseDTO<List<Inventory>> findAll() {
        ApiResponseDTO<List<Inventory>> response = new ApiResponseDTO<>();
        try {
            List<Inventory> dataResponse = inventoryRepository.findAll();
            if (dataResponse.isEmpty()) {
                response.setFailQuery("No se encontraron resultados");
                return response;
            }
            response.setSuccesQuery(dataResponse);

        } catch (Exception e) {
            response.setFailService();
            System.out.println("Error: " + e.getMessage());
        }
        return response;
    }

    public ApiResponseDTO<Inventory> findById(long id) {
        ApiResponseDTO<Inventory> response = new ApiResponseDTO<>();
        try {
            Optional<Inventory> dataResponse = inventoryRepository.findById(id);
            if (dataResponse.isEmpty()) {
                response.setFailQuery("No se encontraron resultados");
                return response;
            }
            response.setSuccesQuery(dataResponse.get());

        } catch (Exception e) {
            response.setFailService();
            System.out.println("Error: " + e.getMessage());
        }
        return response;
    }

    public ApiResponseDTO<Inventory> update(long id, InventoryRequestDTO request) {
        ApiResponseDTO<Inventory> response = new ApiResponseDTO<>();
        try {
            Inventory dataResponse =  inventoryRepository.findById(id).orElse(null);
            if (Objects.isNull(dataResponse)) {
                response.setFailQuery("No se encontraron resultados");
                return response;
            }

            Store store = storeRepository.findById(request.getStoreId()).orElse(null);
            if (Objects.isNull(store)){
                response.setDeclinedTrasaction(null);
                response.setMessage("No existe la tienda con id: " + request.getStoreId());
                return response;
            }

            Film film = filmRepository.findById(request.getFilmId()).orElse(null);
            if (Objects.isNull(film)){
               response.setDeclinedTrasaction(null);
                response.setMessage("No existe la pelicula con id: " + request.getFilmId());
                return response;
            }

            dataResponse.setFilm(film);
            dataResponse.setStore(store);
            dataResponse.setQuantity(request.getQuantity());

            inventoryRepository.save(dataResponse);
            response.setSuccesQuery(dataResponse);

        } catch (Exception e) {
            response.setFailService();
            System.out.println("Error: " + e.getMessage());
        }
        return response;
    }

    public ApiResponseDTO<Inventory> create(InventoryRequestDTO request) {
        ApiResponseDTO<Inventory> response = new ApiResponseDTO<>();
        try {
            Store store = storeRepository.findById(request.getStoreId()).orElse(null);
            if (Objects.isNull(store)){
                response.setDeclinedTrasaction(null);
                response.setMessage("No existe la tienda con id: " + request.getStoreId());
                return response;
            }

            Film film = filmRepository.findById(request.getFilmId()).orElse(null);
            if (Objects.isNull(film)){
                response.setDeclinedTrasaction(null);
                response.setMessage("No existe la pelicula con id: " + request.getFilmId());
                return response;
            }

            Inventory newInventory = new Inventory();
            newInventory.setFilm(film);
            newInventory.setStore(store);
            newInventory.setQuantity(request.getQuantity());

            Inventory inventory = inventoryRepository.save(newInventory);

            if (inventoryRepository.findById(inventory.getInventoryId()).isEmpty()){
                response.setDeclinedTrasaction(inventory);
                return response;
            }

            response.setSuccesQuery(newInventory);

        } catch (Exception e) {
            response.setFailService();
            System.out.println("Error: " + e.getMessage());
        }
        return response;
    }

    public ApiResponseDTO<Inventory> delete(long id){
        ApiResponseDTO<Inventory> response = new ApiResponseDTO<>();
        try {
            Inventory inventory = inventoryRepository.findById(id).orElse(null);
            if (Objects.isNull(inventory)){
                response.setDeclinedTrasaction(null);
                return response;
            }
            inventoryRepository.deleteById(id);
            response.setSuccesTrasaction(inventory);
        }catch (Exception e){
            response.setFailService();
            System.out.println("Error: " + e.getMessage());
        }
        return response;
    }

}
