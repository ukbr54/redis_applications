package com.fancyfrog.example2.model;

import com.fancyfrog.example2.vo.TicketVO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash("tickets")
@NoArgsConstructor
public class TravelTickets {

    private @Id String id;
    private Long rank;
    private String productType;
    private String productCode;
    private String productName;
    private String introduction;
    private String productText;
    private Integer special;
    private String duration;
    private String productImage;
    private String productImageThumb;
    private Long destinationId;
    private String group1;
    private String category1;
    private String subcategory1;
    private String group2;
    private String category2;
    private String subcategory2;
    private String group3;
    private String category3;
    private String subcategory3;
    private String productURL;
    private Double priceAUD;
    private Double priceNZD;
    private String priceEUR;
    private Double priceGBP;
    private Double priceUSD;
    private Double priceCAD;
    private Double priceCHF;
    private String priceNOK;
    private String priceJPY;
    private Double priceSEK;
    private Double priceHKD;
    private Double priceSGD;
    private Double priceZAR;
    private Double avgRating;
    private String avgRatingStarURL;
    private String bookingType;
    private String voucherOption;

    public TravelTickets(TicketVO ticketVO){
        BeanUtils.copyProperties(ticketVO,this);
    }
}
