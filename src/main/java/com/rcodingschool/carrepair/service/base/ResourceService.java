package com.rcodingschool.carrepair.service.base;

import com.rcodingschool.carrepair.domain.base.ResourcePersistable;
import com.rcodingschool.carrepair.exception.base.DuplicateResourceException;
import com.rcodingschool.carrepair.exception.base.ResourceException;
import com.rcodingschool.carrepair.exception.base.ResourceNotFoundException;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class ResourceService<R extends ResourcePersistable<ID>, RSF, ID extends Serializable> {

    protected CrudRepository<R, ID> crudRepository;

    public ResourceService(CrudRepository<R, ID> crudRepository) {
        this.crudRepository = crudRepository;
    }

    public R find(ID resourceId) {
        return crudRepository.findOne(resourceId);
    }

    public Optional<R> findOptional(ID resourceId) {
        return Optional.ofNullable(crudRepository.findOne(resourceId));
    }

    public R findOrThrow(ID resourceId) throws ResourceNotFoundException {
        return findOptional(resourceId).orElseThrow(() -> new ResourceNotFoundException(resourceId));
    }

    public Iterable<R> findAll() {
        return crudRepository.findAll();
    }

    public Iterable<R> searchBy(RSF resourceSearchForm) {
        return findAll();
    }

    public R insert(R resource) throws ResourceException {
        validateBeforeInsertOrThrow(resource);
        return crudRepository.save(resource);
    }

    public R update(R resource) throws ResourceException {
        validateBeforeUpdateOrThrow(resource);
        return crudRepository.save(resource);
    }

    public void deleteById(ID resourceId) throws ResourceException {
        R actualEntity = findOrThrow(resourceId);
        crudRepository.delete(actualEntity);
    }

    protected void validateBeforeInsertOrThrow(R resource) throws ResourceException {
        if (findOptional(resource.getId()).isPresent()) {
            throw new DuplicateResourceException(resource.getId());
        }
    }

    protected void validateBeforeUpdateOrThrow(R resource) throws ResourceException {
        if (!findOptional(resource.getId()).isPresent()) {
            throw new ResourceNotFoundException(resource.getId());
        }
    }

    protected List<R> mapOptionalToList(Optional<R> optionalEntity) {
        return optionalEntity.map(Collections::singletonList).orElse(Collections.emptyList());
    }
}