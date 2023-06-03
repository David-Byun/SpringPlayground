package airbnb.back.mapper;

import airbnb.back.dto.room.RoomDetailResponseDto;
import airbnb.back.dto.room.RoomListResponseDto;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoomMapper {
    Page<List<RoomListResponseDto>> findAllDesc(int pageNo, String orderKind);
    String findById(Long roomId); //status를 가져옴

    RoomDetailResponseDto findByRoomId(Long roomId);


}
