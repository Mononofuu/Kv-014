package edu.softserve.zoo.controller.rest;

import edu.softserve.zoo.Error;
import edu.softserve.zoo.converter.ModelConverter;
import edu.softserve.zoo.dto.BaseDto;
import edu.softserve.zoo.exceptions.ApplicationException;
import edu.softserve.zoo.exceptions.NotFoundException;
import edu.softserve.zoo.model.BaseEntity;
import edu.softserve.zoo.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class provides a skeletal implementation of common basic operations for REST controllers
 *
 * @author Vadym Holub
 */
public abstract class AbstractRestController<Dto extends BaseDto, Entity extends BaseEntity> {

    @Autowired
    protected ModelConverter converter;

    private Class<Entity> entityType;

    protected AbstractRestController(Class<Entity> entityType) {
        this.entityType = entityType;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public Error handleApplicationException(ApplicationException exception) {
        return new Error(exception);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public Error handleNotFoundExceptionException(NotFoundException exception) {
        return new Error(exception);
    }

    @RequestMapping(path="/count", method = RequestMethod.GET)
    public Long count() {
        return getService().count(entityType);
    }

    public Dto getById(@PathVariable Long id) {
        return converter.convertToDto(getService().findOne(id, entityType));
    }

    public List<Dto> getAll() {
        return converter.convertToDto(getService().findAll(entityType));
    }

    public Dto create(@RequestBody Dto dto) {
        return converter.convertToDto(getService().save(converter.convertToEntity(dto)));
    }

    public Dto update(@RequestBody Dto dto, @PathVariable Long id) {
        dto.setId(id);
        return converter.convertToDto(getService().update(converter.convertToEntity(dto)));
    }

    public ResponseEntity delete(@PathVariable Long id) {
        getService().delete(id, entityType);
        return ResponseEntity.ok().build();
    }

    protected abstract Service<Entity> getService();

}