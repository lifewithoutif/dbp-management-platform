package com.hna.dbp.dao.mapper;

import com.hna.dbp.dao.entity.Plugins;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PluginsMapper {

	public void insertPlugins(Plugins plugins);

	public void deletePluginsById(String pluginsId);

	public void markPluginsAsDeleted(String pluginsId);

	public void updatePlugins(Plugins plugins);

	public void updateSensitivePlugins(Plugins plugins);

	public Plugins findPluginsById(String pluginsId);

	public List<Plugins> findPluginsList();

	public int deletePluginsListByIds(String[] Ids);

	public int markPluginsArrayAsDeleted(String[] Ids);

	public int insertPluginsList(List<Plugins> pluginss);

	public void updatePluginsList(List<Plugins> pluginss);

	public List<Plugins> findPluginsQuery(Plugins plugins);

	public List<Plugins> findPluginsQueryPage(@Param("obj") Plugins plugins, @Param("startIndex") int startIndex, @Param("limit") int limit);

	public List<Plugins> findPluginsQueryPageNew(@Param("startIndex") int startIndex, @Param("limit") int limit);

	public int findCountPluginsQuery(Plugins plugins);

}
