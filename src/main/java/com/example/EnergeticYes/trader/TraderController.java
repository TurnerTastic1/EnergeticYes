package com.example.EnergeticYes.trader;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "trader/")
public class TraderController {

    private final TraderService traderService;

    public TraderController(TraderService traderService) {
        this.traderService = traderService;
    }

    @GetMapping("hello-world")
    public List<String> hello() {
        return List.of("Hello World from trader controller!");
    }

    @GetMapping()
    public List<Trader> getTraders() {
        return traderService.getTraders();
    }

    @GetMapping(path = "{traderId}")
    public Trader getTraderById(@PathVariable("traderId") Long traderId) {
        return traderService.getTraderById(traderId);
    }

    @PostMapping()
    public void registerNewTrader(@RequestBody Trader trader) {
        traderService.addNewTrader(trader);
    }

    @DeleteMapping(path = "{traderId}")
    public void deleteTrader(@PathVariable("traderId") Long traderId) {
    	traderService.deleteTrader(traderId);
    }

    @PutMapping(path = "{traderId}")
    public void updateTrader(
    		@PathVariable("traderId") Long traderId,
    		@RequestBody(required = false) Trader trader ) {

    	traderService.updateTrader(traderId, trader.getName(), trader.getEmail());
    }

}
