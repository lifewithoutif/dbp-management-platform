package com.hna.dbp.service.impl;

import com.hna.dbp.dao.entity.Apis;
import com.hna.dbp.dao.entity.Plugins;
import com.hna.dbp.dao.mapper.ApisMapper;
import com.hna.dbp.dao.mapper.PluginsMapper;
import com.hna.dbp.service.ServiceDetailsService;
import com.hna.dbp.utils.CommonNumberUtils;
import com.hna.dbp.vo.response.PageList;
import com.hna.dbp.vo.response.ServiceDetails.ApisResponseVo;
import com.hna.dbp.vo.response.ServiceDetails.PluginsResponseVo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ServiceDetailsServiceImpl implements ServiceDetailsService{

    @Autowired
    private ApisMapper apisMapper;

    @Autowired
    private PluginsMapper pluginsMapper;

    @Override
    public PageList<ApisResponseVo> queryListApis(Integer pageIndex, Integer pageSize,String apiName) {
        PageList<ApisResponseVo> pageList = new PageList<>(pageIndex,pageSize);
        List<ApisResponseVo> apisResponseVoList = new ArrayList<>();
        //每页起始数
        int startIndex = CommonNumberUtils.pageIndex2RecordStart(pageIndex, pageSize);
        int count=0;
        Apis api = new Apis();
        if (StringUtils.isNotBlank(apiName)) {
            api.setApiName(apiName);
        }
        try {
            List<Apis> apisList = apisMapper.findApisQuery(api);
            if (apisList!=null&&apisList.size()>0){
                count = apisList.size();
            }
            List<Apis> apisQueryPageList = apisMapper.findApisQueryPage(api,startIndex, pageSize);
            if (apisQueryPageList !=null && apisQueryPageList.size()>0){
                for (Apis apis:apisQueryPageList) {
                    ApisResponseVo apisResponseVo = new ApisResponseVo();
                    BeanUtils.copyProperties(apisResponseVo,apis);
                    apisResponseVoList.add(apisResponseVo);
                }
                //总数
                pageList.setCount(count);
                //页码数
                pageList.setPageIndex(pageIndex);
                //每页条数
                pageList.setPageSize(pageSize);
                //每页开始数
                pageList.setStartIndex(startIndex);
                pageList.setDataList(apisResponseVoList);
            }else {
                pageList.setDataList(Collections.emptyList());
                pageList.setStartIndex(startIndex);
                pageList.setPageSize(pageSize);
                pageList.setPageIndex(1);
                pageList.setCount(count);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return pageList;
    }

    @Override
    public PageList<PluginsResponseVo> queryListPlugins(Integer pageIndex, Integer pageSize) {
        PageList<PluginsResponseVo> pageList = new PageList<>(pageIndex,pageSize);
        List<PluginsResponseVo> apisResponseVoList = new ArrayList<>();
        //每页起始数
        int startIndex = CommonNumberUtils.pageIndex2RecordStart(pageIndex, pageSize);
        int count=0;
        try {
            List<Plugins> pluginsList = pluginsMapper.findPluginsList();
            if (pluginsList!=null&&pluginsList.size()>0){
                count = pluginsList.size();
            }
            List<Plugins> pluginsQueryPageNew = pluginsMapper.findPluginsQueryPageNew(startIndex, pageSize);

            if (pluginsQueryPageNew !=null && pluginsQueryPageNew.size()>0){
                for (Plugins plugins:pluginsQueryPageNew) {
                    PluginsResponseVo pluginsResponseVo = new PluginsResponseVo();
                    BeanUtils.copyProperties(pluginsResponseVo,plugins);
                    apisResponseVoList.add(pluginsResponseVo);
                }
                //总数
                pageList.setCount(count);
                //页码数
                pageList.setPageIndex(pageIndex);
                //每页条数
                pageList.setPageSize(pageSize);
                //每页开始数
                pageList.setStartIndex(startIndex);
                pageList.setDataList(apisResponseVoList);
            }else {
                pageList.setDataList(Collections.emptyList());
                pageList.setStartIndex(startIndex);
                pageList.setPageSize(pageSize);
                pageList.setPageIndex(1);
                pageList.setCount(count);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return pageList;
    }
}
