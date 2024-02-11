package com.example.demo.Room;

import com.example.demo.Common.Util.SortBy;
import com.example.demo.Favorite.FavoriteRepository;
import com.example.demo.Room.dto.RoomListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final FavoriteRepository favoriteRepository;
    private static final int DEFAULT_PAGE_SIZE = 5;

    @Transactional(readOnly = true)
    public List<RoomListResponseDto> findAllDesc(Optional<Integer> page,
                                                 Optional<SortBy> sortBy) {

        Sort sort = getSortBy(sortBy.orElse(SortBy.ID_DESC));
        Pageable pageable = PageRequest.of(page.orElse(0), DEFAULT_PAGE_SIZE, sort);

        return roomRepository.findAllDesc(pageable).stream()
                .map(RoomListResponseDto::new)
                .collect(Collectors.toList());

    }

    public Sort getSortBy(SortBy sortBy) {
        if (sortBy.getOrderType().equals("ASC")) {
            return Sort.by(sortBy.getSortType()).ascending().and(Sort.by("id")).descending();
        }
        return Sort.by(sortBy.getSortType()).descending().and(Sort.by("id").descending());
    }


}
