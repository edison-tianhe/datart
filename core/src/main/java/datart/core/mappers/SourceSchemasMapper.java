package datart.core.mappers;

import datart.core.entity.SourceSchemas;
import datart.core.mappers.ext.CRUDMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface SourceSchemasMapper extends CRUDMapper {
    @Delete({
        "delete from source_schemas",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into source_schemas (id, source_id, ",
        "update_time, `schemas`)",
        "values (#{id,jdbcType=VARCHAR}, #{sourceId,jdbcType=VARCHAR}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{schemas,jdbcType=LONGVARCHAR})"
    })
    int insert(SourceSchemas record);

    @InsertProvider(type=SourceSchemasSqlProvider.class, method="insertSelective")
    int insertSelective(SourceSchemas record);

    @Select({
        "select",
        "id, source_id, update_time, `schemas`",
        "from source_schemas",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="source_id", property="sourceId", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="schemas", property="schemas", jdbcType=JdbcType.LONGVARCHAR)
    })
    SourceSchemas selectByPrimaryKey(String id);

    @UpdateProvider(type=SourceSchemasSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SourceSchemas record);

    @Update({
        "update source_schemas",
        "set source_id = #{sourceId,jdbcType=VARCHAR},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "`schemas` = #{schemas,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKeyWithBLOBs(SourceSchemas record);

    @Update({
        "update source_schemas",
        "set source_id = #{sourceId,jdbcType=VARCHAR},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(SourceSchemas record);
}