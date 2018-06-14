package com.hna.dbp.service;

import com.hna.dbp.vo.response.EurekaServerBean.Application;
import com.hna.dbp.vo.response.EurekaServerBean.Applications;
import com.hna.dbp.vo.response.EurekaServerBean.Instance;
import com.hna.dbp.vo.response.EurekaServerBean.Instances;
import org.springframework.http.ResponseEntity;

public interface ServiceManagementService {

    //查询所有服务
    public Applications queryEurekaApps();

    //查询指定服务
    public Application queryByServiceName(String serviceName);

    //查询指定服务的实例
    public Instance queryByServiceNameAndInstanceId(String serviceName,String instanceId);

    //添加服务
    public ResponseEntity saveServiceByName(Instances instances, String serviceName);

    //删除服务实例
    public ResponseEntity delServiceNameAndInstanceId(String serviceName,String instanceId);




}
