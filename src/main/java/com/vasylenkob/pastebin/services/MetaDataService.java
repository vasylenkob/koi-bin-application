package com.vasylenkob.pastebin.services;

import com.vasylenkob.pastebin.models.entities.MetaData;
import com.vasylenkob.pastebin.repo.MetaDataRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MetaDataService {

    private MetaDataRepo metaDataRepo;

    public MetaData saveMetaData(MetaData metaData){
      return metaDataRepo.save(metaData);
    }

    public Optional<MetaData> getMetaData(Long postId){
        return metaDataRepo.findById(postId);
    }
}
