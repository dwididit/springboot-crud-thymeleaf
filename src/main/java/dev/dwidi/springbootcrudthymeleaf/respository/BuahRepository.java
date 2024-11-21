package dev.dwidi.springbootcrudthymeleaf.respository;

import dev.dwidi.springbootcrudthymeleaf.entity.Buah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuahRepository extends JpaRepository<Buah, Long> {
    Optional<Buah> findByNamaBuah(String namaBuah);
    Optional<Buah> findByBuahIdAndIsDeletedFalse(Long buahId);
    List<Buah> findAllByIsDeletedFalse();
}
