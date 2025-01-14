package com.atapia.apirfds2.Services.Actividad.ResponseObject;

import java.util.ArrayList;
import java.util.List;

import com.atapia.apirfds2.Services.Generic.ResponseGeneric;

public class ResponseGetAll extends ResponseGeneric {
   public class Response {
      public List<Object> listActividad = new ArrayList<>();
   }

   public Response dto = new Response();
}
