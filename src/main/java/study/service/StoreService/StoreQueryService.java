package study.service.StoreService;

import study.domain.Store;

public interface StoreQueryService {
    Store findById(Long storeId);
}
