package com.mymovies.mymovies.repository;
import com.mymovies.mymovies.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, String>{

    Optional<Object> findByUsername(String username);
}
