package com.example.restfulwebservice.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
// JpaRepository<T, V>
// List<T> findAll()
// List<T> findAll(sort var1)
// List<T> findAllById(Iterable<ID> var1)
// void deleteInBatch()
// void deleteAllInbatch()
// T getOne(ID var1)
}
