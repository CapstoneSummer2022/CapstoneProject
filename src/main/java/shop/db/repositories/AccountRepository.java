package shop.db.repositories;


import shop.db.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
    /**
     * Find account by email
     * @param email
     * @return Account
     */
    Optional<Account> findByEmail(String email);

    @Query(value = "select * from accounts join account_roles on accounts.id = account_roles.account_id join roles on account_roles.role_id= roles.id where role_name in :role",nativeQuery = true)
    List<Account> findAllByRoleName(@Param("role")String[] role);

    Optional<Account> findById(Integer id);
    /**
     * Check exist account with email
     *
     */
    Boolean existsAccountByEmail(String email);

    @Override
    <S extends Account> S save(S entity);
}