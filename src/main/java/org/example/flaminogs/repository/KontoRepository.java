package org.example.flaminogs.repository;

import org.example.flaminogs.entity.Konto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KontoRepository extends JpaRepository<Konto, String> {
}
