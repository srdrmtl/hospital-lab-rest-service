package com.nku.hospitalreporting.hospitalreportingservice.repository;

import com.nku.hospitalreporting.hospitalreportingservice.model.User;
import java.util.List;

/**
 *
 * @author serdar
 */
public interface UserRepositoryCustom {

    public List<User> searchUsers(String searchText);
    public List<User> findByFileId(String fileId);
    public List<User> getHomeResults();
}
