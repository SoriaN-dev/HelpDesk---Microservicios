package io.github.nelsonsoria.helpdesk.service;

import io.github.nelsonsoria.helpdesk.dtos.AssetDTO;
import io.github.nelsonsoria.helpdesk.repo.IAssetRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AssetServiceImpl implements IAssetService{

    private final IAssetRepo assetRepo;

    @Override
    public List<AssetDTO> getAll() {

        return assetRepo.findAll()
                .stream()
                .map(t->AssetDTO.builder()
                        .name(t.getName())
                        .category(t.getCategory().getName())
                        .location(t.getLocation() != null ? t.getLocation().getName() : null)
                        .build())
                .toList();
    }

    @Override
    public AssetDTO getAssetById(Long assetId) {
        return assetRepo.findById(assetId).
                map(t->AssetDTO.builder()
                        .name(t.getName())
                        .category(t.getCategory().getName())
                        .location(t.getLocation() != null ? t.getLocation().getName() : null)
                        .build()).orElse(null);
    }

}
