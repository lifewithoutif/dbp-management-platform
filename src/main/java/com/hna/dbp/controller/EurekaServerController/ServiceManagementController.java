package com.hna.dbp.controller.EurekaServerController;


import com.hna.dbp.controller.AbsController;
import com.hna.dbp.service.ServiceManagementService;
import com.hna.dbp.vo.response.EurekaServerBean.*;
import com.hna.dbp.vo.response.RequestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 *
 * @author s
 *
 */
@RestController
public class ServiceManagementController extends AbsController {

    @Autowired
    private ServiceManagementService serviceManagementService;


    //查询所有服务
    @RequestMapping(value = "/eureka/apps", method = RequestMethod.GET)
    public Applications queryEurekaApps(){
        return serviceManagementService.queryEurekaApps();
    }

    //查询指定服务
    @RequestMapping(value = "/eureka/apps/{serviceName}", method = RequestMethod.GET)
    public Application queryByServiceName(@PathVariable String serviceName){
        return serviceManagementService.queryByServiceName(serviceName);
    }

    //查询指定服务的实例
    @RequestMapping(value = "/eureka/apps/{serviceName}/{instanceId}", method = RequestMethod.GET)
    public Instance queryByServiceNameAndInstanceId(@PathVariable String serviceName, @PathVariable String instanceId) {
        return serviceManagementService.queryByServiceNameAndInstanceId(serviceName,instanceId);
    }

    //添加服务
    @RequestMapping(value = "/eureka/apps/{serviceName}", method = RequestMethod.POST)
    public ResponseEntity saveServiceByName(@RequestBody Instances instances,@PathVariable String serviceName){
        return  serviceManagementService.saveServiceByName(instances,serviceName);
    }

    //删除服务实例
    @RequestMapping(value = "/eureka/apps/{serviceName}/{instanceId}", method = RequestMethod.DELETE)
    public ResponseEntity delServiceNameAndInstanceId(@PathVariable String serviceName, @PathVariable String instanceId){
        return serviceManagementService.delServiceNameAndInstanceId(serviceName,instanceId);
    }

    //遍历删除服务实例：删除服务
    @RequestMapping(value = "/eureka/apps/{serviceName}", method = RequestMethod.DELETE)
    public void delServiceNameAndInstanceId(@PathVariable String serviceName){
        Application application = serviceManagementService.queryByServiceName(serviceName);
        List<Instance> instances = application.getInstance();
        for (Instance instance:instances) {
            serviceManagementService.delServiceNameAndInstanceId(serviceName, instance.getInstanceId());
        }
    }



}
