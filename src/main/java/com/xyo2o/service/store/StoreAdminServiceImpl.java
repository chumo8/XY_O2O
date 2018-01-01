package com.xyo2o.service.store;

import java.util.List;

import com.xyo2o.dao.store.StoreAdminDAO;
import com.xyo2o.domain.store.StoreAdmin;
import com.xyo2o.dto.store.StoreAdminQueryPageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StoreAdminServiceImpl implements StoreAdminService {
    @Autowired
    private StoreAdminDAO storeAdminDAO;
    //高级查询加分页
    public List<StoreAdmin> query(StoreAdminQueryPageDTO dto) {
        int count = storeAdminDAO.getCount(dto);
        dto.setTotal(count);
        return storeAdminDAO.query(dto);
    }
    public List<StoreAdmin> findAll() {
        return storeAdminDAO.findAll();
    }
    public int inset(StoreAdmin admin, int store) {
        return storeAdminDAO.insert(admin,store);
    }
    public int update(StoreAdmin admin, int store, int id) {
        return storeAdminDAO.update(admin,store,id);
    }
    public int delAdmin(int id) {
        return storeAdminDAO.delAdmin(id);
    }
    public StoreAdmin findOne(int id) {
        return storeAdminDAO.findOne(id);
    }

}
