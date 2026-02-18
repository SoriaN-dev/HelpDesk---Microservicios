package io.github.nelsonsoria.helpdesk.rest;


import io.github.nelsonsoria.helpdesk.dtos.AssetDTO;
import io.github.nelsonsoria.helpdesk.service.IAssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/assets"
)
@RequiredArgsConstructor
public class AssetRest {

    private final IAssetService assetService;

    @GetMapping
    public List<AssetDTO> getAll(){
        return assetService.getAll();

    }

    @GetMapping("/{assetId}")
    public AssetDTO getAssetById( @PathVariable("assetId") Long assetId){
        return assetService.getAssetById(assetId);
    }
}
