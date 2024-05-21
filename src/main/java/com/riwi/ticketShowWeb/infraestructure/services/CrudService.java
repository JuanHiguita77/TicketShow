package com.riwi.ticketShowWeb.infraestructure.services;

//Creamos un generico para el crud pasandole los request, responses, y tipo dato llave primaria
public interface CrudService<RQ, RS, ID> 
{
    void delete(ID id);

    RS save(RQ request);

    RS update(ID id, RQ request);
}
