package com.hna.dbp.service.impl;

import com.hna.dbp.service.GeneralSituationService;
import com.hna.dbp.vo.response.EurekaServerBean.GeneralSituation;
import org.apache.tomcat.util.codec.binary.Base64;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class GeneralSituationServiceImpl implements GeneralSituationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeneralSituationServiceImpl.class);

    private String eurekaAppsUrl="http://106.2.20.167:8100/eureka/apps";

    private String eurekaUrl="http://106.2.20.167:8100";

    private String testEurekaUrl = "http://localhost:1001";

    @Autowired
    private RestTemplate restTemplate;

    private static HttpHeaders getHeaders(){
        String plainCredentials="root:123456";
        String base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return headers;
    }

    @Override
    public GeneralSituation queryEureka() {
        HttpEntity<String> request = new HttpEntity<String>(getHeaders());
        ResponseEntity<String> exchange = restTemplate.exchange(eurekaUrl, HttpMethod.GET, request, String.class);
        String body = exchange.getBody();
        Document parse = Jsoup.parse(body);
        Element body1 = parse.body();

        Element generalInfo = body1.getElementById("generalInfo");
        Elements tbody = generalInfo.select("tbody");
        Element tr = tbody.select("tr").first();
        Element tr3 = tbody.select("tr").get(3);
        //总eureka服务器数：registered-replicas
        Element tr5 = tbody.select("tr").get(5);
        //异常服务器数：unavailable-replicas
        Element tr6 = tbody.select("tr").get(6);

        Elements td3 = tr.getElementsByTag("td");
        Elements trd3 = tr3.getElementsByTag("td");
        Elements td5 = tr5.getElementsByTag("td");
        Elements td6 = tr6.getElementsByTag("td");
        //总eureka服务数
        Element element12 = td5.get(1);
        String string8 = element12.toString();//http://192.168.100.166:1002/eureka/, http://192.168.100.166:1003/eureka/
        //服务器数
        int serverTotal= string8.split(",").length + 1;

        //异常服务器数
        Element element13 = td6.get(1);
        String string9 = element13.toString();//http://192.168.100.166:1002/eureka/,http://192.168.100.166:1003/eureka/
        int serverAbnormal = string9.split(",").length;

        //总内存
        Element element7 = td3.get(1);
        String string7 = element7.toString();//226mb
        int toalM = Integer.parseInt(string7.substring(4, string7.length() - 7));
        //已用内存
        Element element8 = trd3.get(1);
        String s1 = element8.toString().split(" ")[0];//123mb
        int usedM = Integer.parseInt(s1.substring(4, s1.length() - 2));


        Element tables = body1.select("table").get(1);
        Element instances2 = tables.getElementById("instances");
        Elements td2 = instances2.getElementsByTag("td");
        Element element3 = td2.get(1);
        Element element4 = td2.get(3);
        Element element5 = td2.get(7);
        Element element6 = td2.get(9);
        String string6 = element6.toString();
        String string5 = element5.toString();
        String string3 = element3.toString();
        String string4 = element4.toString();
        //得到当前时间
        String substring3 = string3.substring(4, string3.length() - 5);
        String[] split = substring3.split(" ");
        String s = split[0];

        //得到已上线时间
        String substring4 = string4.substring(4, string4.length() - 5);
        LOGGER.debug("得到已上线时间"+substring4);
//        System.out.println(s+"====="+substring4);
        //期望心跳
        String substring5 = string5.substring(4, string5.length() - 5);
        int expectHeartbeat = Integer.parseInt(substring5);
//        System.out.println(expectHeartbeat);
        //上分钟心跳
        String substring6 = string6.substring(4, string5.length() - 5);
        int realityHeartbeat = Integer.parseInt(substring6);
//        System.out.println(realityHeartbeat);



        Element instances = parse.getElementById("instances");

        Elements td1 = instances.getElementsByTag("td");
        Element element2 = td1.get(1);
        //得到环境字段
        String string2 = element2.toString();
        String substring2 = string2.substring(4, string2.length() - 5);


        Element instanceInfo = parse.getElementById("instanceInfo");

        Elements td = instanceInfo.getElementsByTag("td");
         /*得到：<td>ipAddr</td>
                <td>172.17.0.4</td>
                <td>status</td>
                <td>UP</td>*/

        Element element = td.get(1);
        //得到ipAddr:<td>172.17.0.4</td>
        String string = element.toString();
        //服务器ip字段
        String substring = string.substring(4, string.length() - 5);
        Element element1 = td.get(3);
        //得到<td>UP</td>
        String string1 = element1.toString();
        //状态字段
        String substring1 = string1.substring(4, string1.length() - 5);

        //异常饼状图
        //服务总数:
        Element table = body1.select("table").get(2);
//        System.out.println("table：：："+table);
        Elements tbody1 = table.select("tbody");
//        System.out.println("tbody1:::"+tbody1);
        Elements tr1 = tbody1.select("tr");
        //服务总数为：
        int serviceTotal = tr1.size();

        int aMIs = 0;
        int availabilityZones =0;


        for (int i = 0; i < tr1.size(); i++) {
            Element elements = tr1.get(i);
            Elements td4 = elements.getElementsByTag("td");
            //aMIs
            Element element9 = td4.get(1);
//            System.out.println(element9);
            //availabilityZones
            Element element10 = td4.get(2);
            int i1 = Integer.parseInt(element9.toString().split(" ")[2].substring(1,2));
            aMIs += i1;

            int i2 = Integer.parseInt(element10.toString().split(" ")[2].substring(1,2));
            availabilityZones += i2;
            //serverTotal
            Element element11 = td4.get(3);
            Elements a = element11.getElementsByTag("a");
//            System.out.println("aaa::"+a.size());
        }
        GeneralSituation generalSituation = new GeneralSituation();
        generalSituation.setServerName(substring);
        generalSituation.setServerIp(substring);
        generalSituation.setStatus(substring1);
        generalSituation.setOnlineTime(substring4);
        generalSituation.setAvailableMemory(toalM-usedM);
        generalSituation.setEnvironment(substring2);
        generalSituation.setExpectHeartbeat(expectHeartbeat);
        generalSituation.setRealityHeartbeat(realityHeartbeat);
        generalSituation.setServiceTotal(serviceTotal);
        generalSituation.setInstanceTotal(aMIs);
        generalSituation.setInstanceAbnormal(aMIs-availabilityZones);
        generalSituation.setServerTotal(serverTotal);
        generalSituation.setServerAbnormal(serverAbnormal);

        return generalSituation;
    }
}
