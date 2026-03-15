package com.github.sakuuj.iotsensorcollector.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.function.Function;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PageWithoutCount<T> {

    private final List<T> content;
    private final int pageSize;

    public static <T> PageWithoutCount<T> of(List<T> content, int pageSize) {

        return PageWithoutCount.<T>builder()
                .content(content)
                .pageSize(pageSize)
                .build();
    }

    public <R> PageWithoutCount<R> map(Function<T, R> mapper) {
        List<R> mappedContent = content.stream()
                .map(mapper)
                .toList();

        return PageWithoutCount.of(mappedContent, pageSize);
    }
}
