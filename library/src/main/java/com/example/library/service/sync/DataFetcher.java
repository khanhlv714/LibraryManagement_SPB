package com.example.library.service.sync;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DataFetcher<T> {
    List<T> getDataPage(long cursor, long snapshotVersion, Pageable pageable);
}
