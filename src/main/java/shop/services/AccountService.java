package shop.services;

import shop.db.dto.AccountDTO;
import shop.db.entities.Account;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface AccountService {
    AccountDTO findByEmail(String email) ;

    boolean addAccount(AccountDTO accountDTO);
    AccountDTO convertToDto(Account account) ;
    boolean updateAccount(AccountDTO accountDTO,Integer id);
    List<AccountDTO> findAllByRoleName(String... role) ;

    AccountDTO findById(Integer id);

    Boolean existsAccountByEmail(String email);

    <S extends Account> S save(S entity);

    List<AccountDTO> findAll();

    List<Account> findAll(Sort sort);

    List<Account> findAllById(Iterable<Integer> integers);

    <S extends Account> List<S> saveAll(Iterable<S> entities);

    void flush();

    <S extends Account> S saveAndFlush(S entity);
}
