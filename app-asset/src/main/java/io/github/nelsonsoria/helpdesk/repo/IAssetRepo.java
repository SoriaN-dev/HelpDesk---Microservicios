package io.github.nelsonsoria.helpdesk.repo;

import io.github.nelsonsoria.helpdesk.db.Asset;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface IAssetRepo extends JpaRepository<Asset,Long> {
}
