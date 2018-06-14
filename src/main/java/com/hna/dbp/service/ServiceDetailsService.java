package com.hna.dbp.service;

import com.hna.dbp.vo.response.PageList;
import com.hna.dbp.vo.response.ServiceDetails.ApisResponseVo;
import com.hna.dbp.vo.response.ServiceDetails.PluginsResponseVo;

public interface ServiceDetailsService {

    public PageList<ApisResponseVo> queryListApis(Integer pageIndex, Integer pageSize,String apiName);

    public PageList<PluginsResponseVo> queryListPlugins(Integer pageIndex, Integer pageSize);
}
