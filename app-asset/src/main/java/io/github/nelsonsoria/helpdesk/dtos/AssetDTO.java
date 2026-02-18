package io.github.nelsonsoria.helpdesk.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssetDTO {
    private String name;
    private String category;
    private String location;
}
