package data;

import dto.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductIO {

    public static List<Product> getProducts() {
        try {
            InputStream is = ProductIO.class.getResourceAsStream("/products.json");
            Product[] products = new ObjectMapper().readValue(is, Product[].class);
            return Arrays.asList(products);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static Product getProduct(String productCode) {
        if (productCode == null) {
            return null;
        }
        for (Product p : getProducts()) {
            if (productCode.equalsIgnoreCase(p.getProductCode()))
                return p;
        }
        return null;
    }
}
