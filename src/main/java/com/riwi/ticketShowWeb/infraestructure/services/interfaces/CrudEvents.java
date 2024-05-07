package com.riwi.ticketShowWeb.infraestructure.services.interfaces;

import org.springframework.data.domain.Page;

/*
RQ= Request
RS= Response
ID= LLave primaria de la entidad
*/
public interface CrudEvents<RQ, RS, ID> {
    public void delete(ID id);
    public RS save(RQ request);
    public RS update(RQ request, ID id);
    public Page<RS> listAll(int page, int size);
}
