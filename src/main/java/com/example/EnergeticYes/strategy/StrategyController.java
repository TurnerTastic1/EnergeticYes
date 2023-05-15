package com.example.EnergeticYes.strategy;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "strategy/")
public class StrategyController {

    private final StrategyService StrategyService;

    public StrategyController(StrategyService StrategyService) {this.StrategyService = StrategyService;}

    @GetMapping("hello-world")
    public List<String> hello() {
        return List.of("Hello World from strategy controller!");
    }

    @GetMapping()
    public List<Strategy> getStrategies() {
        return StrategyService.getStrategies();
    }

    @PostMapping()
    public void registerNewStrategy(@RequestBody Strategy strategy) {StrategyService.addNewStrategy(strategy);}

    @DeleteMapping(path = "{strategyId}")
    public void deleteStrategy(@PathVariable("strategyId") Long strategyId) {StrategyService.deleteStrategy(strategyId);}

    @PutMapping(path = "{strategyId}")
    public void updateStrategy(
    		@PathVariable("strategyId") Long strategyId,
    		@RequestBody(required = false) Strategy strategy ) {

    	StrategyService.updateStrategy(strategyId, strategy.getName(), strategy.getDescription(), strategy.getType());
    }
}
