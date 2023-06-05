package airbnb.back.controller;

import airbnb.back.dto.room.RoomDetailResponseDto;
import airbnb.back.dto.room.RoomListResponseDto;
import airbnb.back.service.RoomService;
import airbnb.back.util.BaseResponse;
import com.github.pagehelper.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/rooms")
    public BaseResponse<Page<List<RoomListResponseDto>>> get(@RequestParam(required = false, defaultValue = "1") int pageNo, @RequestParam(required = false, defaultValue = "ID_DESC") String sort) {
        String orderKind = sort;
        Page<List<RoomListResponseDto>> listPageInfo = roomService.findAllDesc(pageNo, orderKind);
        return new BaseResponse<>(listPageInfo);
    }

    @GetMapping("/rooms/{roomId}")
    public BaseResponse<RoomDetailResponseDto> getRoomDetail(@PathVariable("roomId") Long roomId) {
        RoomDetailResponseDto roomDetailResponseDto = roomService.getRoomDetail(roomId);
        return new BaseResponse<>(roomDetailResponseDto);
    }

}
