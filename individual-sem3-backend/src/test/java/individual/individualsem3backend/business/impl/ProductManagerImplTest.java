package individual.individualsem3backend.business.impl;

import individual.individualsem3backend.business.exception.ProductException;
import individual.individualsem3backend.domain.Product;
import individual.individualsem3backend.persistence.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductManagerImplTest {
//
//    @Mock
//    private ProductRepository productRepositoryMock;
//
//    @InjectMocks
//    private ProductManagerImpl productManager;
//
//    @Test
//    public void createProduct_Successful_ReturnsProduct(){
//        Product createProduct = Product.builder().name("Neuvi").description("A blue flower")
//                .price(23.22).color("Blue").build();
//
//        Product expectedResult = Product.builder().id(1).name("Neuvi").description("A blue flower")
//                .price(23.22).color("Blue").build();
//
//        when(productRepositoryMock.save(createProduct)).thenReturn(expectedResult);
//
//        Product actualResult = productManager.createProduct(createProduct);
//
//        verify(productRepositoryMock).save(createProduct);
//
//        assertEquals(expectedResult, actualResult);
//
//    }
//
//    @Test
//    public void createProduct_WithNullRequest_ThrowsProductException(){
//        assertThrows(ProductException.class, () -> productManager.createProduct(null));
//    }
//
//    @Test
//    public void getAllProducts_ReturnsList(){
//        Product product1 = Product.builder().id(1).name("Neuvi").description("A blue flower")
//                .price(23.22).color("Blue").build();
//
//        Product product2 = Product.builder().id(2).name("Chai").description("A pink flower")
//                .price(324.2).color("Pink").build();
//
//        when(productRepositoryMock.findAll()).thenReturn(List.of(product1, product2));
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
//
//    @Test
//    public void deleteProduct_Successful(){
//        Product product1 = Product.builder().id(1).name("Neuvi").description("A blue flower")
//                .price(23.22).color("Blue").build();
//
//        productManager.deleteProduct(product1.getId());
//
//        verify(productRepositoryMock).deleteById(product1.getId());
//    }
//
//    @Test
//    public void deleteProduct_WithNegativeID_ThrowsProductException(){
//        assertThrows(ProductException.class, () -> productManager.deleteProduct(-1));
//    }
//
//
//    @Test
//    public void getProduct_Successful_ReturnsProduct(){
//        Product product1 = Product.builder().id(1).name("Neuvi").description("A blue flower")
//                .price(23.22).color("Blue").build();
//
//        when(productRepositoryMock.findById(product1.getId())).thenReturn(product1);
//
//        Product expectedResult = product1;
//
//        Optional<Product> actualResult = productManager.getProduct(product1.getId());
//
//        verify(productRepositoryMock).findById(1);
//
//        assertEquals(expectedResult, actualResult.get());
//    }
//
//    @Test
//    public void getProduct_WithNegativeID_ThrowsProductException(){
//        assertThrows(ProductException.class, () -> productManager.getProduct(-1));
//    }
//
//    @Test
//    public void updateProduct_Successful(){
//        Product product1 = Product.builder().id(1).name("Neuvi").description("A blue flower")
//                .price(23.22).color("Blue").build();
//
//        Product updatedProduct = Product.builder().id(1).name("Chai").description("A pink flower")
//                .price(324.2).color("Pink").build();
//
//        when(productRepositoryMock.findById(product1.getId())).thenReturn(product1);
//
//        productManager.updateProduct(updatedProduct);
//
//        verify(productRepositoryMock).update(updatedProduct);
//    }
//
//    @Test
//    public void updateProduct_WithNullParameters_ThrowsProductException(){
//        assertThrows(ProductException.class, () -> productManager.updateProduct(null));
//    }
//
//    @Test
//    public void updateProduct_WithNegativeId_ThrowsProductException(){
//        Product product1 = Product.builder().id(-1).name("Neuvi").description("A blue flower")
//                .price(23.22).color("Blue").build();
//
//        assertThrows(ProductException.class, () -> productManager.updateProduct(product1));
//    }

}
