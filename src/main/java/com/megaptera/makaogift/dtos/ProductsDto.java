package com.megaptera.makaogift.dtos;

import java.util.List;

public class ProductsDto {
    private List<ProductDto> products;
    private PageMetadataDto metadata;

    public ProductsDto(List<ProductDto> products, PageMetadataDto pageMetadataDto) {
        this.products = products;
        this.metadata = pageMetadataDto;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public PageMetadataDto getMetadata() {
        return metadata;
    }
}
