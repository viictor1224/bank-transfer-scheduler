package br.com.cvc.banktransferscheduler.entities.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransferRepository extends JpaRepository<TransferEntity, Long> {

}
