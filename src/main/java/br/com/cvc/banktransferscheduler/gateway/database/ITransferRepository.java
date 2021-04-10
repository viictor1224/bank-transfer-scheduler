package br.com.cvc.banktransferscheduler.gateway.database;

import br.com.cvc.banktransferscheduler.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransferRepository extends JpaRepository<Transfer, Long> {
}
