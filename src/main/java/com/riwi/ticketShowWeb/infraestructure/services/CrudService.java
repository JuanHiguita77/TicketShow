package com.riwi.ticketShowWeb.infraestructure.services;

import org.springframework.data.domain.Page;

//Creamos un generico para el crud pasandole los request, responses, y tipo dato llave primaria
public interface CrudService<RQ, RS, ID> 
{
    void delete(ID id);

    RS save(RQ request);

    RS update(ID id, RQ request);

    Page<RS> listAll(int page, int size);
}
