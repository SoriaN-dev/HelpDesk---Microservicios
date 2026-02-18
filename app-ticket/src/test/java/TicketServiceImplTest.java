import io.github.nelsonsoria.helpdesk.client.AssetRestClient;
import io.github.nelsonsoria.helpdesk.client.EmployeeRestClient;
import io.github.nelsonsoria.helpdesk.db.Ticket;
import io.github.nelsonsoria.helpdesk.dto.asset.AssetDTO;
import io.github.nelsonsoria.helpdesk.dto.ticket.TicketAssetDto;
import io.github.nelsonsoria.helpdesk.dto.ticket.TicketDTO;
import io.github.nelsonsoria.helpdesk.enums.Priority;
import io.github.nelsonsoria.helpdesk.enums.Status;
import io.github.nelsonsoria.helpdesk.repository.TicketRepository;
import io.github.nelsonsoria.helpdesk.service.TicketServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TicketServiceImplTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private AssetRestClient assetRestClient;

    @Mock
    private EmployeeRestClient employeeRestClient;

    @InjectMocks
    private TicketServiceImpl ticketService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnAllTickets(){

        Ticket ticket1 = new Ticket();
        ticket1.setId(1L);
        ticket1.setStatus(Status.CLOSED);
        ticket1.setPriority(Priority.CRITICAL);
        ticket1.setTitle("PC dañada");

        Ticket ticket2 = new Ticket();
        ticket2.setId(2L);
        ticket2.setStatus(Status.CLOSED);
        ticket2.setPriority(Priority.CRITICAL);
        ticket2.setTitle("Impresora Dañada");

        when(ticketRepository.listAll()).thenReturn(List.of(ticket1,ticket2));

        List<TicketDTO> result = ticketService.getAll();

        assertNotNull(result);
        assertEquals(2,result.size());
        assertEquals("PC dañada",result.get(0).getTitle());
        assertEquals("Impresora Dañada",result.get(1).getTitle());


    }

    @Test
    void shoudThrowWhenTicketByIdEmployeeDoesNotExist(){

        Long employeeId = 300L;
        when(ticketRepository.findLastByEmployee(employeeId)).thenReturn(null);

        assertThrows(RuntimeException.class, () ->
                ticketService.getLastTicket(employeeId));
    }

    @Test
    void shouldReturnAllTicketsForEmployee() {
        Long employeeId = 1L;
        Long assetId = 2L;

        Ticket ticket1 = new Ticket();
        ticket1.setId(1L);
        ticket1.setStatus(Status.CLOSED);
        ticket1.setEmployee(employeeId);
        ticket1.setAsset(assetId);
        ticket1.setTitle("PC Dañada");

        Ticket ticket2 = new Ticket();
        ticket2.setId(2L);
        ticket2.setStatus(Status.CLOSED);
        ticket2.setEmployee(employeeId);
        ticket2.setAsset(assetId);
        ticket2.setPriority(Priority.CRITICAL);
        ticket2.setTitle("Impresora Dañada");

        AssetDTO assetDTO = AssetDTO.builder()
                .name("HP EliteDesk 800")
                .category("Laptop")
                .location("Oficina central")
                .build();

        when(ticketRepository.list("id", employeeId)).thenReturn(List.of(ticket1, ticket2));
        when(assetRestClient.getAssetById(assetId)).thenReturn(assetDTO);

        List<TicketAssetDto> result = ticketService.getTicketForEmployee(employeeId);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("PC Dañada", result.get(0).getTitle());
        assertEquals("Impresora Dañada", result.get(1).getTitle());
        assertEquals("HP EliteDesk 800", result.get(0).getAsset().getName());

    }

}
