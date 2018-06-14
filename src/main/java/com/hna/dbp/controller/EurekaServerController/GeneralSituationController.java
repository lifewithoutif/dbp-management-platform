package com.hna.dbp.controller.EurekaServerController;

import com.hna.dbp.controller.AbsController;
import com.hna.dbp.service.GeneralSituationService;
import com.hna.dbp.vo.response.EurekaServerBean.GeneralSituation;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.Arrays;

/**
 *
 *
 * @author s
 *
 */

@RestController
public class GeneralSituationController extends AbsController {

    @Autowired
    private GeneralSituationService generalSituationService;


    //概况
    @RequestMapping(value = "/eureka/generalSituation", method = RequestMethod.GET)
    public GeneralSituation queryEureka(){
       return generalSituationService.queryEureka();
    }

}
