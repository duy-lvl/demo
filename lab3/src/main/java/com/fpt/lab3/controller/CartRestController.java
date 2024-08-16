package com.fpt.lab3.controller;


import com.fpt.lab3.model.CartBean;
import com.fpt.lab3.model.PaymentRequest;
import com.fpt.lab3.model.ReturnMessage;
import com.fpt.lab3.service.CartComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/v1/cart")
public class CartRestController {
    @Autowired
    private CartComponent cartComponent;
    @RequestMapping(value = "/add-cart", method = RequestMethod.POST)
    public ReturnMessage addCart(@RequestBody CartBean cartBean) {
        cartComponent.addCart(cartBean.getCustomerId(), cartBean.getCartItemList());
        log.info("print cart with customer:{}, and cart data:{}", cartBean.getCustomerId(), cartComponent.getCart(cartBean.getCustomerId()));
        return new ReturnMessage(true, "Successful");
    }
    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public ReturnMessage addCart(@RequestBody PaymentRequest paymentRequest) {
        return new ReturnMessage(true, "Successful");
    }
}
