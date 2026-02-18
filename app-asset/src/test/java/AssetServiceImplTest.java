import io.github.nelsonsoria.helpdesk.db.Asset;
import io.github.nelsonsoria.helpdesk.db.Category;
import io.github.nelsonsoria.helpdesk.db.Location;
import io.github.nelsonsoria.helpdesk.dtos.AssetDTO;
import io.github.nelsonsoria.helpdesk.repo.IAssetRepo;
import io.github.nelsonsoria.helpdesk.service.AssetServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class AssetServiceImplTest {

    @Mock
    private IAssetRepo assetRepo;

    @InjectMocks
    private AssetServiceImpl assetService;

    @BeforeEach
    void setUp (){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void shouldReturnAllAsset (){

        Category category = new Category();
        category.setId(1L);
        category.setName("Laptop");

        Asset asset1 = new Asset();
        asset1.setId(1L);
        asset1.setName("Dell Latitude 5420");
        asset1.setCategory(category);

        Asset asset2 = new Asset();
        asset2.setId(2L);
        asset2.setName("HP EliteDesk 800");
        asset2.setCategory(category);

        when(assetRepo.findAll()).thenReturn(List.of(asset1,asset2));

        List<AssetDTO> resul = assetService.getAll();

        assertNotNull(resul);
        assertEquals(2,resul.size());
        assertEquals("Dell Latitude 5420", resul.get(0).getName());
        assertEquals("HP EliteDesk 800", resul.get(1).getName());
        assertEquals("Laptop", resul.get(1).getCategory());
    }
    @Test
    void shouldReturnAssetById(){

        Long assetId = 1L;
        Category category = new Category();
        category.setId(1L);
        category.setName("Laptop");

        Asset asset1 = new Asset();
        asset1.setId(assetId);
        asset1.setName("Dell Latitude 5420");
        asset1.setCategory(category);

        when(assetRepo.findById(assetId)).thenReturn(Optional.of(asset1));

        AssetDTO resul = assetService.getAssetById(assetId);

        assertNotNull(resul);
        assertEquals("Dell Latitude 5420", resul.getName());
        assertEquals("Laptop", resul.getCategory());

        verify(assetRepo,times(1)).findById(assetId);
    }

    @Test
    void shouldThrowWhenAssetDoesNotExist(){
        Long assetId = 999L;
        when(assetRepo.findById(assetId)).thenReturn(null);

        assertThrows(RuntimeException.class,() -> assetService.getAssetById(assetId));

    }
}
