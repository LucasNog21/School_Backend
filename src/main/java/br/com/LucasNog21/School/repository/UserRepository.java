package br.com.LucasNog21.School.repository;

import br.com.LucasNog21.School.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long>{


    @Query("SELECT u FROM User u WHERE u.userName =:userName")
    User findByUserName(@Param("userName")String userName);
}
