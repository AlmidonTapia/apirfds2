package com.atapia.apirfds2.Services.Person.ResponseObject;

import java.util.ArrayList;
import java.util.List;

public class ResponseGetAll {
   public class Response {
      public List<Object> listPerson = new ArrayList<>();
   }

   public Response response = new Response();
}
