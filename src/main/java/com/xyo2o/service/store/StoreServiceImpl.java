package com.xyo2o.service.store;

import java.util.List;

import com.xyo2o.dao.store.StoreDAO;
import com.xyo2o.domain.store.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreDAO storeDAO;

    public List<Store> findAll() {
        return storeDAO.findAll();
    }

    public List<Store> getStore() {
        return storeDAO.getStore();
    }
}
