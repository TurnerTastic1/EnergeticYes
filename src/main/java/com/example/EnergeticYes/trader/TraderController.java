package com.example.EnergeticYes.trader;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String, Object>> getTraders() {
        return traderService.getTraders();
    }

    @PostMapping()
    public ResponseEntity<Map<String, Object>> registerNewTrader(@RequestBody Trader trader) {
        return traderService.addNewTrader(trader);
    }

    @DeleteMapping(path = "{traderId}")
    public ResponseEntity<Map<String, Object>> deleteTrader(@PathVariable("traderId") Long traderId) {
    	return traderService.deleteTrader(traderId);
    }

    @PutMapping(path = "{traderId}")
    public ResponseEntity<Map<String, Object>> updateTrader(
    		@PathVariable("traderId") Long traderId,
    		@RequestBody(required = false) Trader trader ) {

    	return traderService.updateTrader(traderId, trader.getName(), trader.getEmail());
    }

}
