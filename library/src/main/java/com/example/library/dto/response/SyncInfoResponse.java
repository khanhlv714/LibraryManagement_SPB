package com.example.library.dto.response;

import lombok.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SyncInfoResponse {

    private Long snapshotVersion;

    private Integer limit;
}