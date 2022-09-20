package com.example.electriccomponentsshop.repositories;


import com.example.electriccomponentsshop.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {

    Optional<Account> findByEmail(String email);

    @Query(value = "select * from accounts join account_roles on accounts.id = account_roles.account_id join roles on account_roles.role_id= roles.id where status = b'1' and role_name in :role", nativeQuery = true)
    List<Account> findAllByRoleName(@Param("role")String[] role);

    @Query(value = "select distinct a.* from accounts a join orders o on o.employee_id = a.id where a.status = b'1' group by o.employee_id", nativeQuery = true)
    List<Account> findAllEmployeeInOrder ();

    @Query(value = "select accounts.* from accounts join account_roles on accounts.id = account_roles.account_id join roles on account_roles.role_id= roles.id where status = b'1' and role_name = 'ROLE_EMPLOYEE' and accounts.id not in :ids limit 1", nativeQuery = true)
    Account findEmployeeNotYetInOrder (@Param("ids")int[] ids);

    @Query(value = "select a.* from accounts a join orders o on o.employee_id = a.id where a.status = b'1' group by o.employee_id order by count(o.id) limit 1", nativeQuery = true)
    Account findLessOrderEmployee ();

    Optional<Account> findById(Integer id);

    /**
     * Check exist account with email
     *
     */
    Boolean existsAccountByEmail(String email);
    @Query(value = "select * from accounts join account_roles on accounts.id = account_roles.account_id join roles on account_roles.role_id= roles.id where role_name = 'ROLE_CUSTOMER' and phone = :phone",nativeQuery = true)
    Optional<Account> findAccountCustomerByPhone(String phone);
    @Override
    <S extends Account> S save(S entity);
}
