package individual.individualsem3backend.business.impl;

import individual.individualsem3backend.business.exception.ProductException;
import individual.individualsem3backend.domain.Product;
import individual.individualsem3backend.persistence.ProductRepository;
import individual.individualsem3backend.persistence.entity.ProductEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductManagerImplTest {

    @Mock
    private ProductRepository productRepositoryMock;

    @InjectMocks
    private ProductManagerImpl productManager;

    @Test
     void createProduct_Successful_ReturnsProduct(){
        Product createProduct = Product.builder().name("Neuvi").description("A blue flower")
                .price(23.22).color("Blue").build();

        ProductEntity productEntity = ProductEntity.builder().name("Neuvi").description("A blue flower")
                .price(23.22).color("Blue").build();

        ProductEntity productEntityReturn = ProductEntity.builder().id(1).name("Neuvi").description("A blue flower")
                .price(23.22).color("Blue").build();

        Product expectedResult = Product.builder().id(1).name("Neuvi").description("A blue flower")
                .price(23.22).color("Blue").build();

        when(productRepositoryMock.save(productEntity)).thenReturn(productEntityReturn);

        Product actualResult = productManager.createProduct(createProduct);

        verify(productRepositoryMock).save(productEntity);

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void createProduct_WithNullRequest_ThrowsProductException(){
        assertThrows(ProductException.class, () -> productManager.createProduct(null));
    }

//    @Test
//    public void getAllProducts_ReturnsList(){
//        ProductEntity productEntity1 = ProductEntity.builder().id(1).name("Neuvi").description("A blue flower")
//                .price(23.22).color("Blue").build();
//
//        ProductEntity productEntity2 = ProductEntity.builder().id(2).name("Chai").description("A pink flower")
//                .price(324.2).color("Pink").build();
//
//        Product product1 = Product.builder().id(1).name("Neuvi").description("A blue flower")
//                .price(23.22).color("Blue").build();
//
//        Product product2 = Product.builder().id(2).name("Chai").description("A pink flower")
//                .price(324.2).color("Pink").build();
//
//        when(productRepositoryMock.findAll()).thenReturn(List.of(productEntity1, productEntity2));
//
//        List<Product> actualResult = productManager.getProducts();
//
//        List<Product> expectedResult = List.of(product1, product2);
//
//        verify(productRepositoryMock).findAll();
//
//        assertEquals(expectedResult, actualResult);
//
//    }

    @Test
    void deleteProduct_Successful(){
        Product product1 = Product.builder().id(1).name("Neuvi").description("A blue flower")
                .price(23.22).color("Blue").build();

        productManager.deleteProduct(product1.getId());

        verify(productRepositoryMock).deleteById(product1.getId());
    }

//    @Test
//    public void deleteProduct_WithNegativeID_ThrowsProductException(){
//        assertThrows(ProductException.class, () -> productManager.deleteProduct(-1));
//    }


    @Test
    void getProduct_Successful_ReturnsProduct(){
        ProductEntity productEntity1 = ProductEntity.builder().id(1).name("Neuvi").description("A blue flower")
                .price(23.22).color("Blue").build();

        Product product1 = Product.builder().id(1).name("Neuvi").description("A blue flower")
                .price(23.22).color("Blue").build();

        when(productRepositoryMock.findById(product1.getId())).thenReturn(Optional.ofNullable(productEntity1));
        Product actualResult = productManager.getProduct(product1.getId());

        verify(productRepositoryMock).findById(1);

        assertEquals(product1, actualResult);
    }

    @Test
    void getProduct_WithNegativeID_ThrowsProductException(){
        assertThrows(ProductException.class, () -> productManager.getProduct(-1));
    }

    @Test
    void updateProduct_Successful(){
        Product product1 = Product.builder().id(1).name("Neuvi").description("A blue flower")
                .price(23.22).color("Blue").build();

        ProductEntity updatedProductEntity = ProductEntity.builder().id(1).name("Chai").description("A pink flower")
                .price(324.2).color("Pink").build();

        Product updatedProduct = Product.builder().id(1).name("Chai").description("A pink flower")
                .price(324.2).color("Pink").build();

        when(productRepositoryMock.findById(product1.getId())).thenReturn(Optional.ofNullable(updatedProductEntity));

        productManager.updateProduct(updatedProduct);

        verify(productRepositoryMock).save(updatedProductEntity);
    }

    @Test
    void updateProduct_WithNullParameters_ThrowsProductException(){
        assertThrows(ProductException.class, () -> productManager.updateProduct(null));
    }

    @Test
    void updateProduct_WithNegativeId_ThrowsProductException(){
        Product product1 = Product.builder().id(-1).name("Neuvi").description("A blue flower")
                .price(23.22).color("Blue").build();

        assertThrows(ProductException.class, () -> productManager.updateProduct(product1));
    }

}
