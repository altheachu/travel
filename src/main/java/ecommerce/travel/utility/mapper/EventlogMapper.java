package ecommerce.travel.utility.mapper;

import ecommerce.travel.utility.entity.Eventlog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface EventlogMapper {

    @Insert("INSERT INTO Eventlog(id, msg_id, send_time, content, type) VALUES (#{id}, #{msgId}, #{sendTime}, #{content}, #{type})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer createEventLog(Eventlog eventlog);
}
