package org.example.flaminogs.repository;

import org.example.flaminogs.entity.Komentarz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KomentarzRepository extends JpaRepository<Komentarz, Integer> {
}
