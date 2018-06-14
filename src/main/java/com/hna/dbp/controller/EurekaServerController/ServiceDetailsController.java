package com.hna.dbp.controller.EurekaServerController;

import com.hna.dbp.controller.AbsController;
import com.hna.dbp.service.ServiceDetailsService;
import com.hna.dbp.vo.response.PageList;
import com.hna.dbp.vo.response.ServiceDetails.ApisResponseVo;
import com.hna.dbp.vo.response.ServiceDetails.PluginsResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceDetailsController  extends AbsController {

    @Autowired
    private ServiceDetailsService serviceDetailsService;


    @RequestMapping(value = "/details/route", method = RequestMethod.GET)//每页页码数，每页条数
    public PageList<ApisResponseVo> queryListApis(@RequestParam Integer pageIndex, @RequestParam Integer pageSize,@RequestParam String apiName){
        return serviceDetailsService.queryListApis(pageIndex,pageSize,apiName);
    }

    @RequestMapping(value = "/details/plugins", method = RequestMethod.GET)//每页页码数，每页条数
    public PageList<PluginsResponseVo> queryListPlugins(@RequestParam Integer pageIndex, @RequestParam Integer pageSize){
        return serviceDetailsService.queryListPlugins(pageIndex,pageSize);
    }

}
