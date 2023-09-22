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

        verify(flowerRepositoryMock).findAll();

        List<Flower> expectedResult = List.of(flower1, flower2);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getEmptyListOfProducts(){
       when(flowerRepositoryMock.findAll()).thenReturn(List.of());

        String colorFilter = "";

        List<Flower> actualResult = flowerManager.getProducts(colorFilter);

        verify(flowerRepositoryMock).findAll();

        List<Flower> expectedResult =List.of();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void createProduct_successful() {

        Flower flower1 = Flower.builder().name("Neuvilette")
                .price(13.32).description("Pretty boy").color("Blue")
                .lifeExpectancy(32).build();

        Flower flower1Return = Flower.builder().id(1).name("Neuvilette")
                .price(13.32).description("Pretty boy").color("Blue")
                .lifeExpectancy(32).build();

        when(flowerRepositoryMock.save(flower1)).thenReturn(flower1Return);

        Flower request = Flower.builder().name("Neuvilette")
                .price(13.32).description("Pretty boy").color("Blue")
                .lifeExpectancy(32).build();

        Flower actualResult = flowerManager.createProduct(request);

        verify(flowerRepositoryMock).save(flower1);

        verify(flowerRepositoryMock, times(1)).save(flower1);


        assertEquals(flower1Return, actualResult);

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
        verify(flowerRepositoryMock).existsByName(flower1.getName());
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

        when(flowerRepositoryMock.findById(flower1.getId())).thenReturn(flower1);

        Optional<Flower> actualResult = flowerManager.getProduct(flower1.getId());

        verify(flowerRepositoryMock).findById(flower1.getId());

        Flower expectedResult = flower1;

        assertEquals(expectedResult, actualResult.get());
    }

    @Test
    void getNonExistentProduct_ReturnsNull(){
        Flower flower2 = Flower.builder().id(21).name("Wriothlessly")
                .price(323.32).description("Handsome boy").color("Black")
                .lifeExpectancy(10).build();

        when(flowerRepositoryMock.findById(flower2.getId())).thenReturn(null);

        Optional<Flower> actualResult = flowerManager.getProduct(21);

        if(actualResult.isEmpty()){
            actualResult = null;
        }

        Optional<Flower> expectedResult = null;

        assertEquals(expectedResult, actualResult );

        verify(flowerRepositoryMock).findById(flower2.getId());
        //assertFalse(actualResult.isPresent());
    }

    @Test
    void updateProduct_successful() {

        //Check with bart pls

        //first flower that want to change
        Flower flower3 = Flower.builder().id(2).name("Navia")
                .price(324.32).description("Pretty girl").color("Yellow")
                .lifeExpectancy(2).build();

        //call repository mock
        when(flowerRepositoryMock.findById(flower3.getId())).thenReturn(flower3);

        //request to change to this flower
        Flower request = Flower.builder().id(2).name("Chlorande")
                .price(342.24).description("Handsome girl").color("Purple")
                .lifeExpectancy(90).build();

        flowerManager.updateProduct(request);

        verify(flowerRepositoryMock).update(flower3);
    }

    @Test
    void updateNonExistentProduct_throwsInvalidProductException(){
        when(flowerRepositoryMock.findById(2)).thenReturn(null);

        Flower request = Flower.builder().id(2).name("Chlorande")
                .price(342.24).description("Handsome girl").color("Purple")
                .lifeExpectancy(90).build();



        assertThrows(InvalidProductException.class, () -> flowerManager.updateProduct(request));

        verify(flowerRepositoryMock).findById(2);
    }
}