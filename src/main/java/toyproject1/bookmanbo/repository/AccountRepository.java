package toyproject1.bookmanbo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyproject1.bookmanbo.domain.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
