package com.puntored.movies.service;

import com.puntored.movies.dto.ApiResponseDTO;
import com.puntored.movies.dto.StoreRequestDTO;
import com.puntored.movies.entity.Store;
import com.puntored.movies.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public ApiResponseDTO<List<Store>> findAll() {
        ApiResponseDTO<List<Store>> response = new ApiResponseDTO<>();
        try {
            List<Store> dataResponse = storeRepository.findAll();
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

    public ApiResponseDTO<Store> findById(long id) {
        ApiResponseDTO<Store> response = new ApiResponseDTO<>();
        try {
            Optional<Store> dataResponse = storeRepository.findById(id);
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

    public ApiResponseDTO<Store> update(long id, StoreRequestDTO request) {
        ApiResponseDTO<Store> response = new ApiResponseDTO<>();
        try {
            Store dataResponse =  storeRepository.findById(id).orElse(null);
            if (Objects.isNull(dataResponse)) {
                response.setFailQuery("No se encontraron resultados");
                return response;
            }

            dataResponse.setAddress(request.getAddress());
            storeRepository.save(dataResponse);
            response.setSuccesQuery(dataResponse);

        } catch (Exception e) {
            response.setFailService();
            System.out.println("Error: " + e.getMessage());
        }
        return response;
    }

    public ApiResponseDTO<Store> create(StoreRequestDTO request) {
        ApiResponseDTO<Store> response = new ApiResponseDTO<>();
        try {

            Store newStore = new Store();
            newStore.setAddress(request.getAddress());
            Store store = storeRepository.save(newStore);

            if (storeRepository.findById(store.getStoreId()).isEmpty()){
                response.setDeclinedTrasaction(store);
                return response;
            }

            response.setSuccesQuery(newStore);

        } catch (Exception e) {
            response.setFailService();
            System.out.println("Error: " + e.getMessage());
        }
        return response;
    }

    public ApiResponseDTO<Store> delete(long id){
        ApiResponseDTO<Store> response = new ApiResponseDTO<>();
        try {
            Store store = storeRepository.findById(id).orElse(null);
            if (Objects.isNull(store)){
                response.setDeclinedTrasaction(null);
                return response;
            }
            storeRepository.deleteById(id);
            response.setSuccesTrasaction(store);
        }catch (Exception e){
            response.setFailService();
            System.out.println("Error: " + e.getMessage());
        }
        return response;
    }
}
