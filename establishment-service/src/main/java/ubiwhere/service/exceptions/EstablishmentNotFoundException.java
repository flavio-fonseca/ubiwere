/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubiwhere.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author ffonseca
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EstablishmentNotFoundException extends RuntimeException {

  public EstablishmentNotFoundException(String establishment_id) {
    super("Establishment not found for establishment_id:"+establishment_id);
  }

}
