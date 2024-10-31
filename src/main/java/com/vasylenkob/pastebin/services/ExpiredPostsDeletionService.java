package com.vasylenkob.pastebin.services;

import com.vasylenkob.pastebin.entities.MetaData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ExpiredPostsDeletionService {
    private final MetaDataService metaDataService;
    private final PostService postService;

    @Scheduled(fixedRate = 5000)
    public void deleteExpiredPosts(){
        List<MetaData> expiredPosts = metaDataService.getAllMetaData()
                .stream().filter(MetaData::isExpired)
                .toList();
        expiredPosts.forEach(postService::deletePost);
        log.info("{} post(s) deleted", expiredPosts.size());
    }
}
