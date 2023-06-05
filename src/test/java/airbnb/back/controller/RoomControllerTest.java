package airbnb.back.controller;

import airbnb.back.dto.room.RoomDetailResponseDto;
import airbnb.back.dto.room.RoomListResponseDto;
import airbnb.back.entity.Status;
import airbnb.back.entity.user.User;
import airbnb.back.service.RoomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RoomController.class)
class RoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RoomService service;

    @DisplayName("Room 1개 조 테스트 - 성공시")
    @WithMockUser(roles = {"USER", "ADMIN"})
    @Test
    void roomFindById() throws Exception {
          //given
          long roomId = 1;
          RoomDetailResponseDto roomDetailResponseDto = new RoomDetailResponseDto();
          roomDetailResponseDto.setRoomId(1L);
          roomDetailResponseDto.setHostName("John Doe");
          roomDetailResponseDto.setMetropolitan("New York");
          roomDetailResponseDto.setCity("Manhattan");
          roomDetailResponseDto.setTown("Midtown");
          roomDetailResponseDto.setLatitude(40.7128f);
          roomDetailResponseDto.setLongitude(-74.0060f);
          roomDetailResponseDto.setRoomName("Cozy Studio Apartment");
          roomDetailResponseDto.setPrice(100);
          roomDetailResponseDto.setMaxGuest(2);
          roomDetailResponseDto.setRoomDescription("A cozy studio apartment in the heart of Manhattan.");
          roomDetailResponseDto.setCheckinTime("14:00");
          roomDetailResponseDto.setCheckoutTime("11:00");
          roomDetailResponseDto.setRoomAverageScore(4.5);
          roomDetailResponseDto.setReviewCount(10);
          roomDetailResponseDto.setRoomImageUrls(Arrays.asList(
                  "https://example.com/images/1.jpg",
                  "https://example.com/images/2.jpg",
                  "https://example.com/images/3.jpg"
          ));

        //when
        when(service.getRoomDetail(roomId)).thenReturn(roomDetailResponseDto);

        //then
        mockMvc.perform(get("/app/rooms/1")
                        .content("application/json")
                        .content(objectMapper.writeValueAsString(roomDetailResponseDto))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.roomId").value(1))
                .andDo(print());
    }

    @DisplayName("Room 전체 불러오기 테스트 - 성공시")
    @WithMockUser(roles = {"USER", "ADMIN"})
    @Test
    void roomAllGet() throws Exception {
        //given
        RoomDetailResponseDto room1 = new RoomDetailResponseDto();
        room1.setRoomId(1L);
        room1.setHostName("John Doe");
        room1.setMetropolitan("New York");
        room1.setCity("Manhattan");
        room1.setTown("Midtown");
        room1.setLatitude(40.7128f);
        room1.setLongitude(-74.0060f);
        room1.setRoomName("Cozy Studio Apartment");
        room1.setPrice(100);
        room1.setMaxGuest(2);
        room1.setRoomDescription("A cozy studio apartment in the heart of Manhattan.");
        room1.setCheckinTime("14:00");
        room1.setCheckoutTime("11:00");
        room1.setRoomAverageScore(4.5);
        room1.setReviewCount(10);
        room1.setRoomImageUrls(Arrays.asList(
                "https://example.com/images/1.jpg",
                "https://example.com/images/2.jpg",
                "https://example.com/images/3.jpg"
        ));

        RoomDetailResponseDto room2 = new RoomDetailResponseDto();
        room2.setRoomId(2L);
        room2.setHostName("Jane Smith");
        room2.setMetropolitan("London");
        room2.setCity("City of London");
        room2.setTown("Westminster");
        room2.setLatitude(51.5074f);
        room2.setLongitude(-0.1278f);
        room2.setRoomName("Spacious Apartment");
        room2.setPrice(150);
        room2.setMaxGuest(4);
        room2.setRoomDescription("A spacious apartment in the heart of London.");
        room2.setCheckinTime("15:00");
        room2.setCheckoutTime("10:00");
        room2.setRoomAverageScore(4.2);
        room2.setReviewCount(8);
        room2.setRoomImageUrls(Arrays.asList(
                "https://example.com/images/4.jpg",
                "https://example.com/images/5.jpg",
                "https://example.com/images/6.jpg"
        ));

        List<RoomDetailResponseDto> roomList = Arrays.asList(room1, room2);


        //then
        mockMvc.perform(get("/app/rooms/1")
                        .content("application/json")
                        .content(objectMapper.writeValueAsString(roomList))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.roomId").value(1))
                .andDo(print());



    }

}