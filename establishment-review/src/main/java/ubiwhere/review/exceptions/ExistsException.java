/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubiwhere.review.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author ffonseca
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class ExistsException extends RuntimeException {

  public ExistsException(String establishment_id) {
    super("Review already exists for establishment_id:"+establishment_id);
  }

}
