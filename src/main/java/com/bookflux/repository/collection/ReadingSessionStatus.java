package com.bookflux.repository.collection;

import lombok.Getter;

@Getter
public enum ReadingSessionStatus {
    IN_PROGRESS, COMPLETE, CANCELED, STOPPED
}
