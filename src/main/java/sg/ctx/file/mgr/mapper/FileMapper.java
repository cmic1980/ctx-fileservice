package sg.ctx.file.mgr.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import sg.ctx.file.mgr.domain.FileEntity;

import java.util.List;

@Mapper
@Repository
public interface FileMapper {

    @Insert("INSERT INTO file_entity(bucket_id,physical_name,logic_name,logic_path,ext,file_size,gmt_create,gmt_modified) " +
            "VALUES(#{bucketId},#{physicalName},#{logicName},#{logicPath},#{ext},#{fileSize},#{gmtCreate},#{gmtModified})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(FileEntity file);

    @Select("select id, bucket_id as bucketId, physical_name as  physicalName, logic_name as logicName, logic_path as logicPath, " +
            "ext, file_size as fileSize, gmt_create as gmtCreate, gmt_modified as gmtModified " +
            "from file_entity " +
            "where id = #{id}")
    FileEntity selectById(int id);

    @Select("<script> " +
            "select id, bucket_id as bucketId, physical_name as  physicalName, logic_name as logicName, logic_path as logicPath, " +
            "ext, file_size as fileSize, gmt_create as gmtCreate, gmt_modified as gmtModified " +
            "from file_entity " +
            "where id in <foreach item='item' index='index' collection='fileIdList' open='(' separator=',' close=')'> #{item} </foreach> " +
            "</script>")
    List<FileEntity> selectListByIds(@Param("fileIdList")List<Integer> fileIdList);
}
