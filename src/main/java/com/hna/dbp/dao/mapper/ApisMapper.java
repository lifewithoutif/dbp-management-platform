package com.hna.dbp.dao.mapper;

import com.hna.dbp.dao.entity.Apis;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApisMapper {

	public void insertApis(Apis apis);

	public void deleteApisById(String apisId);

	public void markApisAsDeleted(String apisId);

	public void updateApis(Apis apis);

	public void updateSensitiveApis(Apis apis);

	public Apis findApisById(String apisId);

	public List<Apis> findApisList();

	public int deleteApisListByIds(String[] Ids);

	public int markApisArrayAsDeleted(String[] Ids);

	public int insertApisList(List<Apis> apiss);

	public void updateApisList(List<Apis> apiss);

	public List<Apis> findApisQuery(Apis apis);

	public List<Apis> findApisQueryPage(@Param("obj") Apis apis, @Param("startIndex") int startIndex, @Param("limit") int limit);

	public List<Apis> findApisQueryPageNew(@Param("startIndex") int startIndex, @Param("limit") int limit);

	public int findCountApisQuery(Apis apis);

}
