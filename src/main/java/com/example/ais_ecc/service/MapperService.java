package com.example.ais_ecc.service;

import com.example.ais_ecc.entity.actions.CreateUD;
import com.example.ais_ecc.models.CreateUDModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapperService {
    private final ModelMapper modelMapper;


    public MapperService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CreateUDModel convertToModel(CreateUD yourEntity) {
        return modelMapper.map(yourEntity, CreateUDModel.class);
    }

    public CreateUD convertToEntity(CreateUDModel yourModel) {
        return modelMapper.map(yourModel, CreateUD.class);
    }
}
