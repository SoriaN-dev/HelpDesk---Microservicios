package io.github.nelsonsoria.helpdesk.service;

import io.github.nelsonsoria.helpdesk.dtos.AssetDTO;

import java.util.List;

public interface IAssetService {
    List<AssetDTO> getAll();
    AssetDTO getAssetById(Long assetId);
}
