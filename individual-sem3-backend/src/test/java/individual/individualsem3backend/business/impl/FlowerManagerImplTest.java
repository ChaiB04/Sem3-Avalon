package individual.individualsem3backend.business.impl;

import individual.individualsem3backend.business.exception.InvalidProductException;
import individual.individualsem3backend.business.exception.NameAlreadyExistsException;
import individual.individualsem3backend.controller.FlowerRequestResponse.*;
import individual.individualsem3backend.domain.Flower;
import individual.individualsem3backend.persistence.FlowerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlowerManagerImplTest {

    @Mock
    private FlowerRepository flowerRepositoryMock;

    @InjectMocks
    private FlowerManagerImpl flowerManager;

    @Test
    void getProducts() {
        Flower flower1 = Flower.builder().id(23).name("Neuvilette")
                .price(13.32).description("Pretty boy").color("Blue")
                .lifeExpectancy(32).build();

        Flower flower2 = Flower.builder().id(21).name("Wriothlessly")
                .price(323.32).description("Handsome boy").color("Black")
                .lifeExpectancy(10).build();

        when(flowerRepositoryMock.findAll()).thenReturn(List.of(flower1, flower2));

        String colorFilter = "";

        List<Flower> actualResult = flowerManager.getProducts(colorFilter);

        List<Flower> expectedResult = List.of(flower1, flower2);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getEmptyListOfProducts(){
       when(flowerRepositoryMock.findAll()).thenReturn(List.of());

        String colorFilter = "";

        List<Flower> actualResult = flowerManager.getProducts(colorFilter);

        List<Flower> expectedResult =List.of();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void createProduct_successful() {

        Flower flower1 = Flower.builder().id(1).name("Neuvilette")
                .price(13.32).description("Pretty boy").color("Blue")
                .lifeExpectancy(32).build();

        when(flowerRepositoryMock.save(any(Flower.class))).thenReturn(flower1);

        Flower request = Flower.builder().name("Neuvilette")
                .price(13.32).description("Pretty boy").color("Blue")
                .lifeExpectancy(32).build();

        Flower actualResult = flowerManager.createProduct(request);

        verify(flowerRepositoryMock, times(1)).save(any(Flower.class));


        assertEquals(flower1, actualResult);

    }

    @Test
    void createExistingNameProduct_throwsAlreadyExistsException(){
        Flower flower1 = Flower.builder().id(1).name("Neuvilette")
                .price(13.32).description("Pretty boy").color("Blue")
                .lifeExpectancy(32).build();


        when(flowerRepositoryMock.existsByName(flower1.getName())).thenReturn(true);

        Flower request = Flower.builder().id(1).name("Neuvilette")
                .price(13.32).description("Pretty boy").color("Blue")
                .lifeExpectancy(32).build();


        assertThrows(NameAlreadyExistsException.class, () -> flowerManager.createProduct(request));
    }

    @Test
    void deleteProduct_successful() {
        Flower flower1 = Flower.builder().id(1).name("Neuvilette")
                .price(13.32).description("Pretty boy").color("Blue")
                .lifeExpectancy(32).build();

        flowerManager.deleteProduct(flower1.getId());

        verify(flowerRepositoryMock).deleteById(flower1.getId());
    }

    @Test
    void getProduct_successful() {

        Flower flower1 = Flower.builder().id(23).name("Neuvilette")
                .price(13.32).description("Pretty boy").color("Blue")
                .lifeExpectancy(32).build();

        when(flowerRepositoryMock.findById(any())).thenReturn(flower1);

        Optional<Flower> actualResult = flowerManager.getProduct(flower1.getId());

        Flower expectedResult = flower1;

        assertEquals(expectedResult, actualResult.get());
    }

    @Test
    void getNonExistentProduct_ReturnsNull(){
        Flower flower2 = Flower.builder().id(21).name("Wriothlessly")
                .price(323.32).description("Handsome boy").color("Black")
                .lifeExpectancy(10).build();

        when(flowerRepositoryMock.findById(any())).thenReturn(null);

        Optional<Flower> actualResult = flowerManager.getProduct(1);

        if(actualResult.isEmpty()){
            actualResult = null;
        }

        Optional<Flower> expectedResult = null;

        assertEquals(expectedResult, actualResult );
        //assertFalse(actualResult.isPresent());
    }

    @Test
    void updateProduct_successful() {

        //Check with bart pls
        Flower flower3 = Flower.builder().id(2).name("Navia")
                .price(324.32).description("Pretty girl").color("Yellow")
                .lifeExpectancy(2).build();

        Flower flower4 = Flower.builder().id(2).name("Chlorande")
                .price(342.24).description("Handsome girl").color("Purple")
                .lifeExpectancy(90).build();

        when(flowerRepositoryMock.findById(any())).thenReturn(flower3);

        Flower request = Flower.builder().id(2).name("Chlorande")
                .price(342.24).description("Handsome girl").color("Purple")
                .lifeExpectancy(90).build();

        flowerManager.updateProduct(request);

        verify(flowerRepositoryMock).update(any(Flower.class));

        when(flowerRepositoryMock.findById(2)).thenReturn(flower4);

        Optional<Flower> actualResult = flowerManager.getProduct(flower3.getId());


        assertEquals(flower4, actualResult.get());
    }

    @Test
    void updateNonExistentProduct_throwsInvalidProductException(){
        when(flowerRepositoryMock.findById(2)).thenReturn(null);

        Flower request = Flower.builder().id(2).name("Chlorande")
                .price(342.24).description("Handsome girl").color("Purple")
                .lifeExpectancy(90).build();

        assertThrows(InvalidProductException.class, () -> flowerManager.updateProduct(request));
    }
}