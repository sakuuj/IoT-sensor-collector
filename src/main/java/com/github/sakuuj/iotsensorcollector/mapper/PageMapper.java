package com.github.sakuuj.iotsensorcollector.mapper;

import com.github.sakuuj.iotsensorcollector.dto.PageWithoutCount;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Slice;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface PageMapper {

    default <T> PageWithoutCount<T> toPageWithoutCount(Slice<T> slice) {
        return PageWithoutCount.of(slice.getContent(), slice.getSize());
    }
}
