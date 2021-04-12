package br.com.cvc.banktransferscheduler.gateway.database;

import br.com.cvc.banktransferscheduler.gateway.database.entities.TransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransferRepository extends JpaRepository<TransferEntity, Long> {
}
