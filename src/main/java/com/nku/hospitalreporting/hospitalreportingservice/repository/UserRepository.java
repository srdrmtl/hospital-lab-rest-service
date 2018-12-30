package com.nku.hospitalreporting.hospitalreportingservice.repository;

import com.nku.hospitalreporting.hospitalreportingservice.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author serdar
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom{
    
    public List<User> searchUsers(String searchText);
}
