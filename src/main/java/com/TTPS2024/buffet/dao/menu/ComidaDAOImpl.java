package com.TTPS2024.buffet.dao.menu;

import com.TTPS2024.buffet.model.carta.producto.Comida;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
@Repository
public class ComidaDAOImpl implements ComidaDAO {

    @Override
    public void flush() {

    }

    @Override
    public <S extends Comida> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Comida> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Comida> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Comida getOne(Long aLong) {
        return null;
    }

    @Override
    public Comida getById(Long aLong) {
        return null;
    }

    @Override
    public Comida getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Comida> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Comida> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Comida> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Comida> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Comida> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Comida> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Comida, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Comida> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Comida> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Comida> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Comida> findAll() {
        return null;
    }

    @Override
    public List<Comida> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Comida entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Comida> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Comida> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Comida> findAll(Pageable pageable) {
        return null;
    }
}
