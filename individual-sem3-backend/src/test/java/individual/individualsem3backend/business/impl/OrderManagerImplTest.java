package individual.individualsem3backend.business.impl;

import individual.individualsem3backend.business.exception.OrderException;
import individual.individualsem3backend.domain.*;
import individual.individualsem3backend.persistence.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderManagerImplTest {
//
//    @Mock
//    private OrderRepository orderRepositoryMock;
//
//    @InjectMocks
//    private OrderManagerImpl orderManager;
//
//    @Test
//    public void getAllOrders_Successfully(){
//        Product product1 = Product.builder().id(1).name("Neuvi").description("A blue flower")
//                .price(23.22).color("Blue").build();
//
//        Product product2 = Product.builder().id(2).name("Chai").description("A pink flower")
//                .price(324.2).color("Pink").build();
//
//        Order order1 = Order.builder().id(1).userId(1).products(List.of(product1)).dateOfPurchase(Date.valueOf("2023-01-01")).isBundled(false).build();
//
//        Order order2 = Order.builder().id(2).userId(1).products(List.of(product1, product2)).dateOfPurchase(Date.valueOf("2023-11-31")).isBundled(true).build();
//
//        when(orderRepositoryMock.findAll(1)).thenReturn(List.of(order1, order2));
//
//        List<Order> actualResult = orderManager.getAllOrders(1);
//
//        List<Order> expectedResult = List.of(order1, order2);
//
//        verify(orderRepositoryMock).findAll(1);
//
//        assertEquals(expectedResult, actualResult);
//    }
//
//    @Test
//    public void getAllOrders_WithNegativeUserId_ThrowsOrderException(){
//        assertThrows(OrderException.class, () -> orderManager.getAllOrders(-1));
//    }
//
//    @Test
//    public void findOrderById_WithNegativeId_ThrowsOrderException(){
//        assertThrows(OrderException.class, () -> orderManager.findOrderById(-1));
//    }
//
//    @Test
//    public void findOrderById_Successfully(){
//        Product product1 = Product.builder().id(1).name("Neuvi").description("A blue flower")
//                .price(23.22).color("Blue").build();
//
//        Product product2 = Product.builder().id(2).name("Chai").description("A pink flower")
//                .price(324.2).color("Pink").build();
//
//        Order order2 = Order.builder().id(2).userId(1).products(List.of(product1, product2)).dateOfPurchase(Date.valueOf("2023-11-31")).isBundled(true).build();
//
//        when(orderRepositoryMock.findOrderById(order2.getId())).thenReturn(order2);
//
//        Optional<Order> actualResult = orderManager.findOrderById(order2.getId());
//
//        verify(orderRepositoryMock).findOrderById(order2.getId());
//
//        assertEquals(order2, actualResult.get());
//
//    }
//
//    @Test
//    public void createOrder_Successfully(){
//        Product product1 = Product.builder().id(1).name("Neuvi").description("A blue flower")
//                .price(23.22).color("Blue").build();
//
//        Product product2 = Product.builder().id(2).name("Chai").description("A pink flower")
//                .price(324.2).color("Pink").build();
//
//        Order order2 = Order.builder().userId(1).products(List.of(product1, product2)).dateOfPurchase(Date.valueOf("2023-11-31")).isBundled(true).build();
//
//        Order returnOrder = Order.builder().id(1).userId(1).products(List.of(product1, product2)).dateOfPurchase(Date.valueOf("2023-11-31")).isBundled(true).build();
//
//        when(orderRepositoryMock.save(order2)).thenReturn(returnOrder);
//
//        Order actualResult = orderManager.create(order2);
//
//        verify(orderRepositoryMock).save(order2);
//
//        assertEquals(returnOrder, actualResult);
//
//    }
//
//    @Test
//    public void createOrder_WithNullParameter_ThrowsOrderException(){
//        assertThrows(OrderException.class, () -> orderManager.create(null));
//    }
//
//    @Test
//    public void deleteOrder_Successfully(){
//        orderManager.delete(1);
//        verify(orderRepositoryMock).deleteById(1);
//    }
//
//    @Test
//    public void deleteOrder_WithNegativeId_ThrowsOrderException(){
//        assertThrows(OrderException.class, () -> orderManager.delete(-1));
//    }
//
//    @Test
//    public void updateOrder_Successfully(){
//        Product product1 = Product.builder().id(1).name("Neuvi").description("A blue flower")
//                .price(23.22).color("Blue").build();
//
//        Product product2 = Product.builder().id(2).name("Chai").description("A pink flower")
//                .price(324.2).color("Pink").build();
//
//        Order order1 = Order.builder().id(1).userId(1).products(List.of(product1)).dateOfPurchase(Date.valueOf("2023-01-01")).isBundled(false).build();
//
//        Order order2 = Order.builder().id(1).userId(1).products(List.of(product1, product2)).dateOfPurchase(Date.valueOf("2023-11-31")).isBundled(true).build();
//
//        when(orderRepositoryMock.findOrderById(1)).thenReturn(order1);
//
//        Order actualResult = orderManager.update(order2);
//
//        verify(orderRepositoryMock).findOrderById(1);
//
//        assertEquals(order2, actualResult);
//
//
//    }
//
//    @Test
//    public void updateOrder_WithNullParameter_ThrowsOrderException(){
//        assertThrows(OrderException.class, () -> orderManager.update(null));
//    }
//
//    @Test
//    public void updateOrder_WithNegativeId_ThrowsOrderException(){
//
//        Product product1 = Product.builder().id(1).name("Neuvi").description("A blue flower")
//                .price(23.22).color("Blue").build();
//
//        Order order1 = Order.builder().id(-1).userId(1).products(List.of(product1)).dateOfPurchase(Date.valueOf("2023-01-01")).isBundled(false).build();
//
//
//        assertThrows(OrderException.class, () -> orderManager.update(order1));
//    }

}
