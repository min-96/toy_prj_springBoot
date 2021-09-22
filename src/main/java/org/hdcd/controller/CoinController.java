package org.hdcd.controller;


import lombok.RequiredArgsConstructor;
import org.hdcd.domain.ChargeCoin;
import org.hdcd.service.CoinService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/coin")
public class CoinController {

    private final CoinService coinService;


    @GetMapping("/charge")
    @PreAuthorize("hasRole('MEMBER')")
    public void chargeForm(Model model) throws  Exception{
        ChargeCoin chargeCoin = new ChargeCoin();

        chargeCoin.setAmount(1000);

        model.addAttribute(chargeCoin);
    }
}
