package com.example.electriccomponentsshop.services.impl;

import com.example.electriccomponentsshop.common.ERole;
import com.example.electriccomponentsshop.dto.AccountDTO;
import com.example.electriccomponentsshop.entities.*;
import com.example.electriccomponentsshop.repositories.*;
import com.example.electriccomponentsshop.services.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    final
    AccountRepository accountRepository;
    final
    ModelMapper modelMap;
    final
    PasswordEncoder passwordEncoder;
    final
    ProvinceRepository provinceRepository;
    final
    DistrictRepository districtRepository;
    final
    WardRepository wardRepository;
    final
    RoleRepository roleRepository;

    @Override
    public AccountDTO findByEmail(String email) {
        Optional<Account> accountOptional = accountRepository.findByEmail(email);
        if(accountOptional.isEmpty()){
            throw new NoSuchElementException("Không tìm thấy tài khoản với email này");
        }
        return convertToDto(accountOptional.get());
    }

    @Override
    public AccountDTO convertToDto(Account account){
        return modelMap.map(account,AccountDTO.class);
    }
    @Override
   public boolean addAccount(AccountDTO accountDTO){
        Account account = new Account();
        List<Role> roles = new ArrayList<>();
        Optional<Role> roleOptional = roleRepository.findByRoleName(ERole.ROLE_EMPLOYEE);
        if(roleOptional.isEmpty()){
            throw new NoSuchElementException("Không tìm thấy chức vụ này");
        }
        Role role = roleOptional.get();
        roles.add(role);
        account.setName(accountDTO.getName());
        account.setPhone(accountDTO.getPhone());
        account.setEmail(accountDTO.getEmail());
        account.setGender(accountDTO.getGender());
        account.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
        setAddress(accountDTO,account);
        account.setBirthDate(Date.valueOf(accountDTO.getDob()));
        account.setRoles(roles);
        return accountRepository.save(account) != null;
    }
    @Override
    public boolean updateAccount(AccountDTO accountDTO,Integer id) {
       Optional<Account> optionalAccount = accountRepository.findById(id);

       if(optionalAccount.isPresent()){
           Account account = optionalAccount.get();
           account.setName(accountDTO.getName());
           account.setPhone(accountDTO.getPhone());
           account.setEmail(accountDTO.getEmail());
           account.setGender(accountDTO.getGender());
           if(!accountDTO.getPassword().equals(optionalAccount.get().getPassword())){
               account.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
           }

           account.setBirthDate(Date.valueOf(accountDTO.getDob()));
//           List<Role> roles = new ArrayList<>();
//           Optional<Role> roleOptional = roleService.findByRoleName(ERole.valueOf(accountDTO.getRole()));
//           if(roleOptional.isEmpty()){
//               throw new NoSuchElementException("Không tìm thấy chức vụ này");
//           }
//           else {
//               roles.add(roleOptional.get());
//           }
//         account.setRoles(roles);
            setAddress(accountDTO, account);
           return accountRepository.save(account) != null;
       }
       else {
           throw new NoSuchElementException("Không tìm thấy tài khoản này");
       }

    }

    private boolean setAddress(AccountDTO accountDTO, Account account) {
        Optional<Province> provinceOptional = provinceRepository.findByName(accountDTO.getProvinceName());

        if(provinceOptional.isEmpty()){
            throw new NoSuchElementException("Không tìm thấy tỉnh/thành phố này");
        }
        else {
            Province province = provinceOptional.get();
            List<District> districtList = province.getDistrictList();
            Optional<District> districtOptional = districtRepository.findByName(accountDTO.getDistrictName());
            if(districtOptional.isEmpty()){
                throw new NoSuchElementException("Không tìm thấy Quận/Huyện này");
            }
            else {
                District district = districtOptional.get();
                if(!districtList.contains(district)){
                    throw new NoSuchElementException("Tỉnh/Thành phố này không có Quận/Huyện này");
                }
                else {
                    Optional<Ward> wardOptional = wardRepository.findByName(accountDTO.getWardName());
                    List<Ward> wardList = district.getWardList();
                    if(wardOptional.isEmpty()){
                        throw new NoSuchElementException("Không có Xã/Phường/Thị trấn này");
                    }
                    else {
                        Ward ward = wardOptional.get();
                        if(!wardList.contains(ward)){
                            throw new NoSuchElementException("Quận/Huyện này không có Xã/Phường/Thị trấn này");
                        }
                        else {
                            account.setProvinceAccount(province);
                            account.setDistrictAccount(district);
                            account.setWardAccount(ward);
                        }

                    }
                }

            }
        }
        return true;
    }

    @Override

    public List<AccountDTO> findAllByRoleName(String... role){
        List<Account> accounts = accountRepository.findAllByRoleName(role);

        return accounts.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public AccountDTO findById(Integer id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if(accountOptional.isEmpty()){
            throw new NoSuchElementException("Không tìm thấy tài khoản có id như trên");
        }
            return convertToDto(accountOptional.get());
    }

    @Override
    public Boolean existsAccountByEmail(String email) {
        return accountRepository.existsAccountByEmail(email);
    }

    @Override
    public <S extends Account> S save(S entity) {
        return accountRepository.save(entity);
    }

    @Override
    public List<AccountDTO> findAll() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<Account> findAll(Sort sort) {
        return accountRepository.findAll(sort);
    }

    @Override
    public List<Account> findAllById(Iterable<Integer> integers) {
        return accountRepository.findAllById(integers);
    }

    @Override
    public <S extends Account> List<S> saveAll(Iterable<S> entities) {
        return accountRepository.saveAll(entities);
    }

    @Override
    public void flush() {
        accountRepository.flush();
    }

    @Override
    public <S extends Account> S saveAndFlush(S entity) {
        return accountRepository.saveAndFlush(entity);
    }

    public <S extends Account> List<S> saveAllAndFlush(Iterable<S> entities) {
        return accountRepository.saveAllAndFlush(entities);
    }

    @Deprecated
    public void deleteInBatch(Iterable<Account> entities) {
        accountRepository.deleteInBatch(entities);
    }

    public void deleteAllInBatch(Iterable<Account> entities) {
        accountRepository.deleteAllInBatch(entities);
    }

    public void deleteAllByIdInBatch(Iterable<Integer> integers) {
        accountRepository.deleteAllByIdInBatch(integers);
    }

    public void deleteAllInBatch() {
        accountRepository.deleteAllInBatch();
    }

    @Deprecated
    public Account getOne(Integer integer) {
        return accountRepository.getOne(integer);
    }

    @Deprecated
    public Account getById(Integer integer) {
        return accountRepository.getById(integer);
    }

    public Account getReferenceById(Integer integer) {
        return accountRepository.getReferenceById(integer);
    }

    public <S extends Account> List<S> findAll(Example<S> example) {
        return accountRepository.findAll(example);
    }

    public <S extends Account> List<S> findAll(Example<S> example, Sort sort) {
        return accountRepository.findAll(example, sort);
    }

    public Page<Account> findAll(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    public boolean existsById(Integer integer) {
        return accountRepository.existsById(integer);
    }

    public long count() {
        return accountRepository.count();
    }

    public void deleteById(Integer integer) {
        accountRepository.deleteById(integer);
    }

    public void delete(Account entity) {
        accountRepository.delete(entity);
    }

    public void deleteAllById(Iterable<? extends Integer> integers) {
        accountRepository.deleteAllById(integers);
    }

    public void deleteAll(Iterable<? extends Account> entities) {
        accountRepository.deleteAll(entities);
    }

    public void deleteAll() {
        accountRepository.deleteAll();
    }

    public <S extends Account> Optional<S> findOne(Example<S> example) {
        return accountRepository.findOne(example);
    }

    public <S extends Account> Page<S> findAll(Example<S> example, Pageable pageable) {
        return accountRepository.findAll(example, pageable);
    }

    public <S extends Account> long count(Example<S> example) {
        return accountRepository.count(example);
    }

    public <S extends Account> boolean exists(Example<S> example) {
        return accountRepository.exists(example);
    }

    public <S extends Account, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return accountRepository.findBy(example, queryFunction);
    }
}
