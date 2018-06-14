package com.hna.dbp.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hna.dbp.service.ServiceManagementService;
import com.hna.dbp.utils.DateUtil;
import com.hna.dbp.vo.response.EurekaServerBean.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ServiceManagementServiceImpl implements ServiceManagementService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceManagementServiceImpl.class);

    private String eurekaAppsUrl="http://106.2.20.167:8100/eureka/apps";

    private String testeurekaAppsUrl="http:localhost:1001//eureka/apps";


    @Autowired
    private RestTemplate restTemplate;

    private static HttpHeaders getHeaders(){
        String plainCredentials="root:123456";
        String base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + base64Credentials);
//        headers.add("Authorization", "Basic " + base64Credentials);

//        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return headers;
    }

    @Override
    public Applications queryEurekaApps(){
        HttpEntity<String> request = new HttpEntity<String>(getHeaders());
        ResponseEntity<JSONObject> exchange = restTemplate.exchange(eurekaAppsUrl, HttpMethod.GET, request, JSONObject.class);
        JSONObject body = exchange.getBody();
        String string = body.toJSONString();

        //由于json返回字段中有非法字符：$ @，这些字段不能被自动识别，出现字段遗漏。 没用下面这种方法
        Datas datas = JSON.parseObject(string, Datas.class);
//        System.out.println("aaaaa---"+datas);


        //Applications表
        Applications applications = body.getObject("applications", Applications.class);
//        System.out.println("applications:"+applications);

        JSONObject bodyJSONObject1 = body.getJSONObject("applications");
        System.out.println(bodyJSONObject1);
        List<Application> lists = bodyJSONObject1.getObject("application", List.class);
        Object[] objects = lists.toArray();

        for (Object object:objects) {
//            System.out.println("application::::"+object);
            String jsonString = JSONUtils.toJSONString(object);
            //Application表
            //由于json返回字段中有非法字符：$ @，这些字段不能被自动识别，出现字段遗漏。 没用下面这种方法
            Application application = JSON.parseObject(jsonString, Application.class);
//            System.out.println("application:::::"+application);
        }
        List<Application> listApplication = new ArrayList<>();
        JSONArray arraylist = bodyJSONObject1.getJSONArray("application");
        Object object7 = bodyJSONObject1.get("versions__delta");
        String versions__delta = object7.toString();
        Object object8 = bodyJSONObject1.get("apps__hashcode");
        String apps__hashcode = object8.toString();

//        System.out.println("arraylist:"+arraylist);
        for (Object object:arraylist) {
            Application application = new Application();
            List<Instance> listInstance = new ArrayList<>();
            String jsonString = JSONUtils.toJSONString(object);
            JSONObject jsonObject = JSON.parseObject(jsonString);
            JSONArray instances = jsonObject.getJSONArray("instance");
            Object nameobj = jsonObject.get("name");
            String name = (String)nameobj;
            for (Object obj:instances) {
//                System.out.println("obj"+obj);
                String jsonString1 = JSONUtils.toJSONString(obj);
                JSONObject jsonObject1 = JSON.parseObject(jsonString1);
                System.out.println("jsonObject1+++"+jsonObject1);
                //instanceId
                Object object1 = jsonObject1.get("instanceId");
                String instanceId = object1.toString();
//                System.out.println("instanceId==="+instanceId);

                JSONObject metadata = jsonObject1.getJSONObject("metadata");
//                System.out.println(metadata);
                JSONObject leaseInfo = jsonObject1.getJSONObject("leaseInfo");
//                System.out.println(leaseInfo);
                JSONObject securePort = jsonObject1.getJSONObject("securePort");
//                System.out.println(securePort);
                JSONObject dataCenterInfo = jsonObject1.getJSONObject("dataCenterInfo");
//                System.out.println(dataCenterInfo);
                JSONObject port = jsonObject1.getJSONObject("port");
//                System.out.println(port);

                //leaseInfo
                Object object11 = leaseInfo.get("renewalIntervalInSecs");
                int renewalIntervalInSecs=(int)object11;
                Object object12 = leaseInfo.get("durationInSecs");
                int durationInSecs=(int)object12;
                Object object14 = leaseInfo.get("evictionTimestamp");
                long evictionTimestamp = Long.parseLong(object14.toString());
                Object object15 = leaseInfo.get("registrationTimestamp");
                long registrationTimestamp =Long.parseLong(object15.toString());
                Object object16 = leaseInfo.get("lastRenewalTimestamp");
                long lastRenewalTimestamp = Long.parseLong(object16.toString());
                Object object17 = leaseInfo.get("serviceUpTimestamp");
                long serviceUpTimestamp = Long.parseLong(object17.toString());
                String serviceUpTimestampdate = DateUtil.getDate(serviceUpTimestamp);
                LeaseInfo leaseInfoo = new LeaseInfo();
                leaseInfoo.setDurationInSecs(durationInSecs);
                leaseInfoo.setRenewalIntervalInSecs(renewalIntervalInSecs);
                leaseInfoo.setEvictionTimestamp(evictionTimestamp);
                leaseInfoo.setRegistrationTimestamp(registrationTimestamp);
                leaseInfoo.setLastRenewalTimestamp(lastRenewalTimestamp);
                leaseInfoo.setServiceUpTimestamp(serviceUpTimestampdate);


                //metadata
                Object object6 = metadata.get("@class");
                String sclass =(String) object6;
                Metadatas metadatas = new Metadatas();
                metadatas.setAclass(sclass);

                //securePort
                Object object4 = securePort.get("$");
                int p = (int)object4;
                Object object5 = securePort.get("@enabled");
                String senabled= (String) object5;
                SecurePort securePortBean = new SecurePort();
                securePortBean.setEnabled(senabled);
                securePortBean.setPort(p);

                //port值
                Object object2 = port.get("$");
                int i = (int)object2;
                Object object3 = port.get("@enabled");
                String enabled= (String) object3;
                Ports ports = new Ports();
                ports.setPort(i);
                ports.setEnabled(enabled);
//                System.out.println(ports);

                //DataCenterInfo
                Object object9 = dataCenterInfo.get("@class");
                String aclass = (String) object9;
                Object object10 = dataCenterInfo.get("name");
                String dname = (String) object10;
                DataCenterInfo dataCenterInfoo = new DataCenterInfo();
                dataCenterInfoo.setName(dname);
                dataCenterInfoo.setAclass(aclass);

                Instance instance = new Instance();
                instance.setInstanceId(instanceId);
                instance.setActionType(jsonObject1.get("actionType").toString());
                instance.setApp(jsonObject1.get("app").toString());
                instance.setAstDirtyTimestamp(jsonObject1.get("lastDirtyTimestamp").toString());
                instance.setCountryId(Integer.parseInt(jsonObject1.get("countryId").toString()));
                instance.setDataCenterInfo(dataCenterInfoo);
                instance.setHealthCheckUrl(jsonObject1.get("healthCheckUrl").toString());
                instance.setHomePageUrl(jsonObject1.get("homePageUrl").toString());
                instance.setHostName(jsonObject1.get("hostName").toString());
                instance.setIpAddr(jsonObject1.get("ipAddr").toString());
                instance.setIsCoordinatingDiscoveryServer(jsonObject1.get("isCoordinatingDiscoveryServer").toString());
                instance.setLastUpdatedTimestamp(jsonObject1.get("lastUpdatedTimestamp").toString());
                instance.setLeaseInfo(leaseInfoo);
                instance.setMetadata(metadatas);
                instance.setOverriddenstatus(jsonObject1.get("overriddenstatus").toString());
                instance.setPort(ports);
                instance.setSecurePort(securePortBean);
                instance.setSecureVipAddress(jsonObject1.get("secureVipAddress").toString());
                instance.setStatus(jsonObject1.get("status").toString());
                instance.setStatusPageUrl(jsonObject1.get("statusPageUrl").toString());
                instance.setVipAddress(jsonObject1.get("vipAddress").toString());
                listInstance.add(instance);
            }
            application.setName(name);
            application.setInstance(listInstance);
            application.setNum(listInstance.size());
            listApplication.add(application);
        }
        Applications applicationss = new Applications();
        applicationss.setApps__hashcode(apps__hashcode);
        applicationss.setVersions__delta(versions__delta);
        applicationss.setApplication(listApplication);
        return applicationss;
    }

    //查询指定服务
    @Override
    public Application queryByServiceName(String serviceName) {
        Application application = new Application();
        HttpEntity<String> request = new HttpEntity<String>(getHeaders());
        ResponseEntity<JSONObject> exchange = restTemplate.exchange(eurekaAppsUrl+"/"+serviceName, HttpMethod.GET, request, JSONObject.class);
        JSONObject body = exchange.getBody();
        JSONObject bodyJSONObject = body.getJSONObject("application");
        Object object2 = bodyJSONObject.get("name");
        String name = (String)object2;
        JSONArray instances = bodyJSONObject.getJSONArray("instance");
        List<Instance> listInstance = new ArrayList<>();
        for (Object obj:instances) {
            String jsonString1 = JSONUtils.toJSONString(obj);
            JSONObject jsonObject1 = JSON.parseObject(jsonString1);
            //instanceId
            Object instanceobject = jsonObject1.get("instanceId");
            String instanceId = instanceobject.toString();
//             System.out.println("instanceId==="+instanceId);
            JSONObject port = jsonObject1.getJSONObject("port");
            JSONObject securePort = jsonObject1.getJSONObject("securePort");
            JSONObject dataCenterInfo = jsonObject1.getJSONObject("dataCenterInfo");
            JSONObject leaseInfo = jsonObject1.getJSONObject("leaseInfo");
            JSONObject metadata = jsonObject1.getJSONObject("metadata");

            Object object = port.get("$");
            int p1 = (int)object;
            Object object1 = port.get("@enabled");
            String enabled = (String) object1;
            Ports ports = new Ports();
            ports.setPort(p1);
            ports.setEnabled(enabled);

            //securePort
            Object object4 = securePort.get("$");
            int p = (int)object4;
            Object object5 = securePort.get("@enabled");
            String senabled= (String) object5;
            SecurePort securePortBean = new SecurePort();
            securePortBean.setEnabled(senabled);
            securePortBean.setPort(p);

            //metadata
            Object object6 = metadata.get("@class");
            String sclass =(String) object6;
            Metadatas metadatas = new Metadatas();
            metadatas.setAclass(sclass);

            //DataCenterInfo
            Object object9 = dataCenterInfo.get("@class");
            String aclass = (String) object9;
            Object object10 = dataCenterInfo.get("name");
            String dname = (String) object10;
            DataCenterInfo dataCenterInfoo = new DataCenterInfo();
            dataCenterInfoo.setName(dname);
            dataCenterInfoo.setAclass(aclass);

            //leaseInfo
            Object object11 = leaseInfo.get("renewalIntervalInSecs");
            int renewalIntervalInSecs=(int)object11;
            Object object12 = leaseInfo.get("durationInSecs");
            int durationInSecs=(int)object12;
            Object object14 = leaseInfo.get("evictionTimestamp");
            long evictionTimestamp = Long.parseLong(object14.toString());
            Object object15 = leaseInfo.get("registrationTimestamp");
            long registrationTimestamp =Long.parseLong(object15.toString());
            Object object16 = leaseInfo.get("lastRenewalTimestamp");
            long lastRenewalTimestamp = Long.parseLong(object16.toString());
            Object object17 = leaseInfo.get("serviceUpTimestamp");
            long serviceUpTimestamp = Long.parseLong(object17.toString());
            String serviceUpTimestampdate = DateUtil.getDate(serviceUpTimestamp);
            LeaseInfo leaseInfoo = new LeaseInfo();
            leaseInfoo.setDurationInSecs(durationInSecs);
            leaseInfoo.setRenewalIntervalInSecs(renewalIntervalInSecs);
            leaseInfoo.setEvictionTimestamp(evictionTimestamp);
            leaseInfoo.setRegistrationTimestamp(registrationTimestamp);
            leaseInfoo.setLastRenewalTimestamp(lastRenewalTimestamp);
            leaseInfoo.setServiceUpTimestamp(serviceUpTimestampdate);

            Instance instance = new Instance();
            instance.setInstanceId(instanceId);
            instance.setActionType(jsonObject1.get("actionType").toString());
            instance.setApp(jsonObject1.get("app").toString());
            instance.setAstDirtyTimestamp(jsonObject1.get("lastDirtyTimestamp").toString());
            instance.setCountryId(Integer.parseInt(jsonObject1.get("countryId").toString()));
            instance.setDataCenterInfo(dataCenterInfoo);
            instance.setHealthCheckUrl(jsonObject1.get("healthCheckUrl").toString());
            instance.setHomePageUrl(jsonObject1.get("homePageUrl").toString());
            instance.setHostName(jsonObject1.get("hostName").toString());
            instance.setIpAddr(jsonObject1.get("ipAddr").toString());
            instance.setIsCoordinatingDiscoveryServer(jsonObject1.get("isCoordinatingDiscoveryServer").toString());
            instance.setLastUpdatedTimestamp(jsonObject1.get("lastUpdatedTimestamp").toString());
            instance.setLeaseInfo(leaseInfoo);
            instance.setMetadata(metadatas);
            instance.setOverriddenstatus(jsonObject1.get("overriddenstatus").toString());
            instance.setPort(ports);
            instance.setSecurePort(securePortBean);
            instance.setSecureVipAddress(jsonObject1.get("secureVipAddress").toString());
            instance.setStatus(jsonObject1.get("status").toString());
            instance.setStatusPageUrl(jsonObject1.get("statusPageUrl").toString());
            instance.setVipAddress(jsonObject1.get("vipAddress").toString());
            listInstance.add(instance);

        }
        application.setName(name);
        application.setNum(listInstance.size());
        application.setInstance(listInstance);
        return application;
    }

    //查询指定服务的实例
    @Override
    public Instance queryByServiceNameAndInstanceId(String serviceName, String instanceId) {
        System.out.println(eurekaAppsUrl+"/"+serviceName+"/"+instanceId);
        Instance instance = new Instance();
        HttpEntity<String> request = new HttpEntity<String>(getHeaders());
        ResponseEntity<JSONObject> exchange = restTemplate.exchange(eurekaAppsUrl+"/"+serviceName+"/"+instanceId, HttpMethod.GET, request, JSONObject.class);
        if(exchange.hasBody()){
            JSONObject body = exchange.getBody();
            JSONObject jsonObject1 = body.getJSONObject("instance");
            Object object = jsonObject1.get("instanceId");
            String instanceid=(String) object;

            JSONObject port = jsonObject1.getJSONObject("port");
            JSONObject securePort = jsonObject1.getJSONObject("securePort");
            JSONObject dataCenterInfo = jsonObject1.getJSONObject("dataCenterInfo");
            JSONObject leaseInfo = jsonObject1.getJSONObject("leaseInfo");
            JSONObject metadata = jsonObject1.getJSONObject("metadata");

            Object objectp = port.get("$");
            int p1 = (int)objectp;
            Object object1 = port.get("@enabled");
            String enabled = (String) object1;
            Ports ports = new Ports();
            ports.setPort(p1);
            ports.setEnabled(enabled);

            //securePort
            Object object4 = securePort.get("$");
            int p = (int)object4;
            Object object5 = securePort.get("@enabled");
            String senabled= (String) object5;
            SecurePort securePortBean = new SecurePort();
            securePortBean.setEnabled(senabled);
            securePortBean.setPort(p);

            //metadata
            Object object6 = metadata.get("@class");
            String sclass =(String) object6;
            Metadatas metadatas = new Metadatas();
            metadatas.setAclass(sclass);

            //DataCenterInfo
            Object object9 = dataCenterInfo.get("@class");
            String aclass = (String) object9;
            Object object10 = dataCenterInfo.get("name");
            String dname = (String) object10;
            DataCenterInfo dataCenterInfoo = new DataCenterInfo();
            dataCenterInfoo.setName(dname);
            dataCenterInfoo.setAclass(aclass);

            //leaseInfo
            Object object11 = leaseInfo.get("renewalIntervalInSecs");
            int renewalIntervalInSecs=(int)object11;
            Object object12 = leaseInfo.get("durationInSecs");
            int durationInSecs=(int)object12;
            Object object14 = leaseInfo.get("evictionTimestamp");
            long evictionTimestamp = Long.parseLong(object14.toString());
            Object object15 = leaseInfo.get("registrationTimestamp");
            long registrationTimestamp =Long.parseLong(object15.toString());
            Object object16 = leaseInfo.get("lastRenewalTimestamp");
            long lastRenewalTimestamp = Long.parseLong(object16.toString());
            Object object17 = leaseInfo.get("serviceUpTimestamp");
            long serviceUpTimestamp = Long.parseLong(object17.toString());
            String serviceUpTimestampdate = DateUtil.getDate(serviceUpTimestamp);
            LeaseInfo leaseInfoo = new LeaseInfo();
            leaseInfoo.setDurationInSecs(durationInSecs);
            leaseInfoo.setRenewalIntervalInSecs(renewalIntervalInSecs);
            leaseInfoo.setEvictionTimestamp(evictionTimestamp);
            leaseInfoo.setRegistrationTimestamp(registrationTimestamp);
            leaseInfoo.setLastRenewalTimestamp(lastRenewalTimestamp);
            leaseInfoo.setServiceUpTimestamp(serviceUpTimestampdate);

            instance.setInstanceId(instanceid);
            instance.setActionType(jsonObject1.get("actionType").toString());
            instance.setApp(jsonObject1.get("app").toString());
            instance.setAstDirtyTimestamp(jsonObject1.get("lastDirtyTimestamp").toString());
            instance.setCountryId(Integer.parseInt(jsonObject1.get("countryId").toString()));
            instance.setDataCenterInfo(dataCenterInfoo);
            instance.setHealthCheckUrl(jsonObject1.get("healthCheckUrl").toString());
            instance.setHomePageUrl(jsonObject1.get("homePageUrl").toString());
            instance.setHostName(jsonObject1.get("hostName").toString());
            instance.setIpAddr(jsonObject1.get("ipAddr").toString());
            instance.setIsCoordinatingDiscoveryServer(jsonObject1.get("isCoordinatingDiscoveryServer").toString());
            instance.setLastUpdatedTimestamp(jsonObject1.get("lastUpdatedTimestamp").toString());
            instance.setLeaseInfo(leaseInfoo);
            instance.setMetadata(metadatas);
            instance.setOverriddenstatus(jsonObject1.get("overriddenstatus").toString());
            instance.setPort(ports);
            instance.setSecurePort(securePortBean);
            instance.setSecureVipAddress(jsonObject1.get("secureVipAddress").toString());
            instance.setStatus(jsonObject1.get("status").toString());
            instance.setStatusPageUrl(jsonObject1.get("statusPageUrl").toString());
            instance.setVipAddress(jsonObject1.get("vipAddress").toString());
        }
        return instance;
    }

    @Override
    public ResponseEntity saveServiceByName(Instances instances, String serviceName) {
        System.out.println(">><<"+instances.toString());
        String string = JSONObject.toJSONString(instances);
        JSONObject jsonObject0 = JSONObject.parseObject(string);
        Object object = jsonObject0.get("instance");

        JSONObject jsonObject1 = JSONObject.parseObject( JSONObject.toJSONString(object));
        jsonObject1.putIfAbsent("app",jsonObject1.get("appName").toString());
        jsonObject1.remove("appName");

        Map port = new HashMap();
        port.put("$",jsonObject1.get("port").toString());
        port.put("@enabled","true");
        jsonObject1.remove("port");
        jsonObject1.putIfAbsent("port",port);

        Map securePort = new HashMap();
        securePort.put("$",jsonObject1.get("securePort").toString());
        securePort.put("@enabled","false");
        jsonObject1.remove("securePort");
        jsonObject1.putIfAbsent("securePort",securePort);

        jsonObject1.remove("dataCenterInfo");
        Map dataCenterInfo = new HashMap();
        dataCenterInfo.put("@class","com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo");
        dataCenterInfo.put("name","MyOwn");
        jsonObject1.putIfAbsent("dataCenterInfo",dataCenterInfo);

        Object obj = jsonObject1.get("leaseInfo");
        String string2 = JSONObject.toJSONString(obj);
        JSONObject jsonObject3 = JSONObject.parseObject(string2);
        Map leaseInfo = new HashMap();
        leaseInfo.putIfAbsent("renewalIntervalInSecs",jsonObject3.get("renewalIntervalInSecs").toString());
        leaseInfo.putIfAbsent("durationInSecs",jsonObject3.get("durationInSecs").toString());
        leaseInfo.putIfAbsent("registrationTimestamp",jsonObject3.get("registrationTimestamp").toString());
        leaseInfo.putIfAbsent("lastRenewalTimestamp",jsonObject3.get("registrationTimestamp").toString());
        leaseInfo.putIfAbsent("evictionTimestamp",jsonObject3.get("evictionTimestamp").toString());
        leaseInfo.putIfAbsent("serviceUpTimestamp",jsonObject3.get("serviceUpTimestamp").toString());
        jsonObject1.remove("leaseInfo");
        jsonObject1.putIfAbsent("leaseInfo",leaseInfo);

        jsonObject1.remove("metadata");
        Map metadata = new HashMap();
        metadata.put("@class","java.util.Collections$EmptyMap");
        jsonObject1.putIfAbsent("metadata",metadata);

        Object lastUpdatedTimestampStr = jsonObject1.get("lastUpdatedTimestamp").toString();
        String lastDirtyTimestampStr = jsonObject1.get("lastDirtyTimestamp").toString();
        jsonObject1.remove("lastUpdatedTimestamp");
        jsonObject1.remove("lastDirtyTimestamp");
        jsonObject1.putIfAbsent("lastUpdatedTimestamp",lastUpdatedTimestampStr);
        jsonObject1.putIfAbsent("lastDirtyTimestamp",lastDirtyTimestampStr);

        Object ipAddr = jsonObject1.get("iPAddr");
        jsonObject1.remove("iPAddr");
        jsonObject1.putIfAbsent("ipAddr",ipAddr);

        Object vipAddress = jsonObject1.get("vIPAddress");
        jsonObject1.remove("vIPAddress");
        jsonObject1.putIfAbsent("vipAddress",vipAddress);

        Object countryId = jsonObject1.get("countryId");
        jsonObject1.remove("countryId");
        jsonObject1.putIfAbsent("countryId",countryId.toString());


        jsonObject1.putIfAbsent("isCoordinatingDiscoveryServer",jsonObject1.get("coordinatingDiscoveryServer").toString());
        jsonObject1.remove("coordinatingDiscoveryServer");
        jsonObject1.remove("dirty");
        jsonObject1.remove("healthCheckUrls");
        jsonObject1.remove("id");
        jsonObject1.remove("sID");
        jsonObject1.remove("version");

        System.out.println(jsonObject1);
        String string1 = jsonObject1.toJSONString();
        Object parse = JSONUtils.parse(string1);
        Map instance = new HashMap();
        instance.put("instance",parse);
        String string4 = JSONObject.toJSONString(instance);
        System.out.println("iiiiii"+string4);

        HttpEntity<String> stringHttpEntity = new HttpEntity<>(string4, getHeaders());
        ResponseEntity<JSONObject> jsonObjectResponseEntity =null;
        try {
            jsonObjectResponseEntity = restTemplate.postForEntity(eurekaAppsUrl + "/" + serviceName, stringHttpEntity, JSONObject.class);
        } catch (RestClientException e) {
            e.printStackTrace();
        }

//        ResponseEntity<String> exchange = restTemplate.exchange(eurekaAppsUrl + "/" + serviceName, HttpMethod.POST, stringHttpEntity, String.class);
//        Map map = restTemplate.postForObject(eurekaAppsUrl + "/" + serviceName, stringHttpEntity, Map.class);
        System.out.println("====>"+jsonObjectResponseEntity);
        return jsonObjectResponseEntity;
    }

    @Override
    public ResponseEntity delServiceNameAndInstanceId(String serviceName, String instanceId) {
        System.out.println(">>>>"+serviceName+"==="+instanceId);
        HttpEntity<String> request = new HttpEntity<String>(getHeaders());
        ResponseEntity<JSONObject> exchange = restTemplate.exchange(eurekaAppsUrl + "/" + serviceName + "/" + instanceId, HttpMethod.DELETE, request, JSONObject.class);
        System.out.println(exchange);
        return exchange;
    }
}
