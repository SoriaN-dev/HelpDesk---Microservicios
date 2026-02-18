import io.github.nelsonsoria.helpdesk.Service.EmployeeServiceImpl;
import io.github.nelsonsoria.helpdesk.client.TicketRestClient;
import io.github.nelsonsoria.helpdesk.db.Employee;
import io.github.nelsonsoria.helpdesk.dto.employee.EmployeeDTO;
import io.github.nelsonsoria.helpdesk.dto.employee.EmployeeSummaryDto;
import io.github.nelsonsoria.helpdesk.dto.tickets.LastTicketDto;
import io.github.nelsonsoria.helpdesk.dto.tickets.TicketStatsDto;
import io.github.nelsonsoria.helpdesk.repository.EmployeeRepository;
import org.gradle.internal.impldep.javax.annotation.meta.When;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.*;


public class EmployeeServiceImplTest {


    @Mock
    private EmployeeRepository employeeRepository;


    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private TicketRestClient restClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnEmployeeById() {

        Long empleadoId = 1L;
        Employee empleadoBD = new Employee();
        empleadoBD.setId(empleadoId);
        empleadoBD.setName("Juan Pérez");
        empleadoBD.setEmail("juan@example.com");

        when(employeeRepository.findById(empleadoId)).thenReturn(empleadoBD);

        EmployeeDTO resultado = employeeService.getEmployeeById(empleadoId);

        assertNotNull(resultado);
        assertEquals("Juan Pérez", resultado.getName());
        assertEquals("juan@example.com", resultado.getEmail());


        verify(employeeRepository, times(1)).findById(empleadoId);
    }


    @Test
    void shouldReturnAllEmployees() {
        // ARRANGE
        Employee emp1 = new Employee();
        emp1.setId(1L);
        emp1.setName("Juan");
        emp1.setEmail("juan@example.com");

        Employee emp2 = new Employee();
        emp2.setId(2L);
        emp2.setName("María");
        emp2.setEmail("maria@example.com");

        when(employeeRepository.listAll()).thenReturn(List.of(emp1, emp2));

        // ACT
        List<EmployeeDTO> resul = employeeService.getAll();

        // ASSERT
        assertNotNull(resul);
        assertEquals(2, resul.size());
        assertEquals("Juan", resul.get(0).getName());
        assertEquals("María", resul.get(1).getName());
    }
    @Test
    void shouldThrowWhenEmployeeDoesNotExist() {

        Long empleadoId = 999L;
        when(employeeRepository.findById(empleadoId)).thenReturn(null);

        assertThrows(RuntimeException.class, () ->
                employeeService.getEmployeeById(empleadoId)
        );
    }
    @Test
    void shouldSaveEmployee(){

        EmployeeDTO input = EmployeeDTO.builder()
                .name("Juan")
                .email("juan@example.com")
                .build();
        EmployeeDTO resul= employeeService.saveEmployee(input);

        assertNotNull(resul);
        assertEquals("Juan",resul.getName());
        assertEquals("juan@example.com",resul.getEmail());
        verify(employeeRepository,times(1)).persist(any(Employee.class));

    }

    @Test
    void shouldReturnEmployeeSummary(){
        Long employeeId= 1L;
        Employee employeeDB = new Employee();
        employeeDB.setId(employeeId);
        employeeDB.setName("Pedro");
        employeeDB.setEmail("juan@example.com");

        TicketStatsDto stats = TicketStatsDto.builder()
                .open(2L)
                .inProgress(2L)
                .closed(3L)
                .highPriority(2L).build();

        LastTicketDto last = LastTicketDto.builder()
                .id(2L)
                .title("Pantalla parpadea")
                .build();

        when(employeeRepository.findById(employeeId)).thenReturn(employeeDB);
        when(restClient.getTicketSummary(employeeId)).thenReturn(stats);
        when(restClient.getLastTicket(employeeId)).thenReturn(last);

        EmployeeSummaryDto resul = employeeService.getEmployeeSummary(employeeId);

        assertNotNull(resul);
        assertEquals(2L,resul.getTickets().getLast().getId());
        assertEquals("Pedro",resul.getEmployee().getName());

        verify(employeeRepository,times(1)).findById(employeeId);
        verify(restClient,times(1)).getTicketSummary(employeeId);
        verify(restClient,times(1)).getLastTicket(employeeId);

    }

}


