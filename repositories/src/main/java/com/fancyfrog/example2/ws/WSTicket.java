package com.fancyfrog.example2.ws;

import com.fancyfrog.example2.model.TravelTickets;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Setter
@Getter
public class WSTicket {
    private Long rank;
    private String productName;
    private String introduction;
    private Integer special;
    private String duration;
    private String commences;
    private String productImage;
    private String productImageThumb;
    private Long destinationId;
    private String city;
    private String productURL;
    private Double priceUSD;
    private Double avgRating;
    private String avgRatingStarURL;
    private String bookingType;
    private String voucherOption;

    public WSTicket(TravelTickets travelTickets){
        BeanUtils.copyProperties(travelTickets,this);
    }
}
