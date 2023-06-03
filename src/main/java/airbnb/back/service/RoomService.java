package airbnb.back.service;

import airbnb.back.dto.room.RoomDetailResponseDto;
import airbnb.back.dto.room.RoomListResponseDto;
import airbnb.back.mapper.RoomMapper;
import airbnb.back.util.BaseResponseStatus;
import airbnb.back.util.exception.RoomException;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static airbnb.back.util.BaseResponseStatus.INACTIVE_ROOM;
import static airbnb.back.util.BaseResponseStatus.NONE_ROOM;

@RequiredArgsConstructor
@Service
@Slf4j
public class RoomService {
    private final RoomMapper roomMapper;

    public Page<List<RoomListResponseDto>> findAllDesc(int pageNo, String orderKind) {
        PageHelper.startPage(pageNo, 5); //pageSize : 5, 5개씩 출력
        Page<List<RoomListResponseDto>> allDesc = roomMapper.findAllDesc(pageNo,orderKind);
        return allDesc;
    }

    public RoomDetailResponseDto getRoomDetail(Long roomId) {
        log.info("roomMapper.findById(roomId) = {}", roomMapper.findById(roomId));

        String result = roomMapper.findById(roomId);
        if(result == null) throw new RoomException(NONE_ROOM);
        if(result.toUpperCase().equals("INACTIVE")) throw new RoomException(INACTIVE_ROOM);

        RoomDetailResponseDto dto = roomMapper.findByRoomId(roomId);
        log.info("roomDto = {}", dto);

        return dto;


    }

}
