package com.fancyfrog.example2.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TicketVO {

    @JsonProperty("Rank")
    private Long rank;

    @JsonProperty("ProductType")
    private String productType;

    @JsonProperty("ProductCode")
    private String productCode;

    @JsonProperty("ProductName")
    private String productName;

    @JsonProperty("Introduction")
    private String introduction;

    @JsonProperty("ProductText")
    private String productText;

    @JsonProperty("Special")
    private Integer special;

    @JsonProperty("Duration")
    private String duration;

    @JsonProperty("Commences")
    private String commences;

    @JsonProperty("ProductImage")
    private String productImage;

    @JsonProperty("ProductImageThumb")
    private String productImageThumb;

    @JsonProperty("DestinationID")
    private Long destinationId;

    @JsonProperty("Continent")
    private String continent;

    @JsonProperty("Country")
    private String country;

    @JsonProperty("Region")
    private String region;

    @JsonProperty("City")
    private String city;

    @JsonProperty("IATACode")
    private String IATACode;

    @JsonProperty("Group1")
    private String group1;

    @JsonProperty("Category1")
    private String category1;

    @JsonProperty("Subcategory1")
    private String subcategory1;

    @JsonProperty("Group2")
    private String group2;

    @JsonProperty("Category2")
    private String category2;

    @JsonProperty("Subcategory2")
    private String subcategory2;

    @JsonProperty("Group3")
    private String group3;

    @JsonProperty("Category3")
    private String category3;

    @JsonProperty("Subcategory3")
    private String subcategory3;

    @JsonProperty("ProductURL")
    private String productURL;

    @JsonProperty("PriceAUD")
    private Double priceAUD;

    @JsonProperty("PriceNZD")
    private Double priceNZD;

    @JsonProperty("PriceEUR")
    private String priceEUR;

    @JsonProperty("PriceGBP")
    private Double priceGBP;

    @JsonProperty("PriceUSD")
    private Double priceUSD;

    @JsonProperty("PriceCAD")
    private Double priceCAD;

    @JsonProperty("PriceCHF")
    private Double priceCHF;

    @JsonProperty("PriceNOK")
    private String priceNOK;

    @JsonProperty("PriceJPY")
    private String priceJPY;

    @JsonProperty("PriceSEK")
    private Double priceSEK;

    @JsonProperty("PriceHKD")
    private Double priceHKD;

    @JsonProperty("PriceSGD")
    private Double priceSGD;

    @JsonProperty("PriceZAR")
    private Double priceZAR;

    @JsonProperty("AvgRating")
    private Double avgRating;

    @JsonProperty("AvgRatingStarURL")
    private String avgRatingStarURL;

    @JsonProperty("BookingType")
    private String bookingType;

    @JsonProperty("VoucherOption")
    private String voucherOption;
}
