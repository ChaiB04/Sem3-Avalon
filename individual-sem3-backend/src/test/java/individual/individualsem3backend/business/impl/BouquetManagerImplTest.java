package individual.individualsem3backend.business.impl;

import individual.individualsem3backend.business.BouquetManagerUseCase;
import individual.individualsem3backend.controller.BouquetRequestResponse.CreateBouquetRequest;
import individual.individualsem3backend.controller.BouquetRequestResponse.CreateBouquetResponse;
import individual.individualsem3backend.controller.BouquetRequestResponse.GetAllBouquetRequest;
import individual.individualsem3backend.controller.BouquetRequestResponse.GetAllBouquetResponse;
import individual.individualsem3backend.domain.Bouquet;
import individual.individualsem3backend.domain.Flower;
import individual.individualsem3backend.persistence.BouquetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BouquetManagerImplTest {

    @Mock
    private BouquetRepository bouquetRepositoryMock;

    @InjectMocks
    private BouquetManagerImpl bouquetManager;

    @Test
    void getProducts() {
                Flower flower1 = Flower.builder().id(23).name("Neuvilette")
                        .price(13.32).description("Pretty boy").color("Blue")
                        .lifeExpectancy(32).build();

                Flower flower2 = Flower.builder().id(21).name("Wriothlessly")
                        .price(323.32).description("Handsome boy").color("Black")
                        .lifeExpectancy(10).build();

        Bouquet bouquet1 = Bouquet.builder().id(1).name("Neuviandrizzly").price(123.12)
                .flowers(List.of(flower1, flower2)).colorOfBow("Blue and black").build();

                Flower flower3 = Flower.builder().id(2).name("Navia")
                        .price(324.32).description("Pretty girl").color("Yellow")
                        .lifeExpectancy(2).build();

                Flower flower4 = Flower.builder().id(5).name("Chlorande")
                        .price(342.24).description("Handsome girl").color("Purple")
                        .lifeExpectancy(90).build();

        Bouquet bouquet2 = Bouquet.builder().id(3).name("NaviaAndChlorande").price(324.23)
                .flowers(List.of(flower3, flower4)).colorOfBow("Yellow and purple").build();

        when(bouquetRepositoryMock.findAll()).thenReturn(List.of(bouquet1, bouquet2));

        GetAllBouquetRequest request = GetAllBouquetRequest.builder().build();

        GetAllBouquetResponse actualResult = bouquetManager.getProducts(request);


        GetAllBouquetResponse expectedResult = GetAllBouquetResponse.builder()
                                    .allBouquets(List.of(bouquet1, bouquet2)).build();

        assertEquals(expectedResult, actualResult);

        verify(bouquetRepositoryMock).findAll();
    }

    @Test
    void createProduct() {
        CreateBouquetResponse response = CreateBouquetResponse.builder().build();

        Flower flower1 = Flower.builder().id(23).name("Neuvilette")
                .price(13.32).description("Pretty boy").color("Blue")
                .lifeExpectancy(32).build();

        Flower flower2 = Flower.builder().id(21).name("Wriothlessly")
                .price(323.32).description("Handsome boy").color("Black")
                .lifeExpectancy(10).build();

        Bouquet bouquet1 = Bouquet.builder().id(1).name("Neuviandrizzly").price(123.12)
                .flowers(List.of(flower1, flower2)).colorOfBow("Blue and black").build();

        when(bouquetRepositoryMock.save(any())).thenReturn(bouquet1);
        Flower flower3 = Flower.builder().id(2).name("Navia")
                .price(324.32).description("Pretty girl").color("Yellow")
                .lifeExpectancy(2).build();

        Flower flower4 = Flower.builder().id(5).name("Chlorande")
                .price(342.24).description("Handsome girl").color("Purple")
                .lifeExpectancy(90).build();
        CreateBouquetRequest request = CreateBouquetRequest.builder().name("Navia")
                .price(213.21).flowers(List.of(flower3, flower4)).colorOfBow("Yellow")
                .description("Test").build();

        CreateBouquetResponse actualResult = bouquetManager.createProduct(request);

        verify(bouquetRepositoryMock).save(any());

        CreateBouquetResponse expectedResult = CreateBouquetResponse.builder().id(1).build();

        assertEquals(expectedResult, actualResult);


    }

    @Test
    void deleteProduct() {
    }

    @Test
    void getProduct() {
    }

    @Test
    void updateProduct() {
    }
}