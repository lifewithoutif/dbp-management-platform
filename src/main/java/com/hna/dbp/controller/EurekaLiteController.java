package com.hna.dbp.controller;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hna.dbp.eureka.lite.Application;
import com.hna.dbp.eureka.lite.Eureka;
import com.hna.dbp.eureka.lite.Registration;
import com.hna.dbp.vo.response.RequestResult;
import com.netflix.appinfo.InstanceInfo;

/**
 * @author jlgan
 */
@RestController
public class EurekaLiteController extends AbsController {

  private final Eureka eureka;

  public EurekaLiteController(Eureka eureka) {
    this.eureka = eureka;
  }

  @RequestMapping(path = "/apps", method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ModelAndView register(@RequestBody Application application,
      HttpServletRequest request) throws Exception {
    InstanceInfo instanceInfo = this.eureka.register(application);
    URI location = new URI(
        request.getRequestURI() + "/" + application.getName() + "/" + application.getInstance_id());
    Registration registration = new Registration();
    registration.setApplication(application);
    registration.update(instanceInfo);
    RequestResult result = new RequestResult(ResponseEntity.created(location).body(registration));
    return createModelAndView(result);
  }

  @RequestMapping(path = "/apps/{name}/{instanceId}", method = RequestMethod.DELETE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ModelAndView unregister(@PathVariable("name") String name,
      @PathVariable("instanceId") String instanceId) {
    this.eureka.cancel(name, instanceId);
    RequestResult result = new RequestResult(ResponseEntity.noContent().build());
    return createModelAndView(result);
  }

  @RequestMapping(path = "/apps/{name}/{instanceId:.+}", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ModelAndView getInstance(@PathVariable("name") String name,
      @PathVariable("instanceId") String instanceId) {
    Registration registration = this.eureka.getRegistration(name, instanceId);
    RequestResult result = new RequestResult(ResponseEntity.ok(registration));
    return createModelAndView(result);
  }

  @RequestMapping(path = "/apps/{name}/{instanceId}", method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ModelAndView renew(@PathVariable("name") String name,
      @PathVariable("instanceId") String instanceId, @RequestBody Registration registration) {
    InstanceInfo instanceInfo = this.eureka.getInstanceInfo(registration);
    this.eureka.renew(instanceInfo);
    RequestResult result = new RequestResult(ResponseEntity.ok().build());
    return createModelAndView(result);
  }


  @RequestMapping(path = "/apps", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ModelAndView listApps() {
    RequestResult result = new RequestResult(this.eureka.getApplications());
    return createModelAndView(result);
  }

  @RequestMapping(path = "/apps/{name}", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ModelAndView listApps(@PathVariable("name") String name) {
    RequestResult result = new RequestResult(this.eureka.getRegistrations(name));
    return createModelAndView(result);
  }
}
