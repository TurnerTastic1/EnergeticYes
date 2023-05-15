package com.example.EnergeticYes.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StrategyService {

    private final StrategyRepository strategyRepository;

    @Autowired
    public StrategyService(StrategyRepository strategyRepository) {
        this.strategyRepository = strategyRepository;
    }
    public List<Strategy> getStrategies() {
        return strategyRepository.findAll();
    }

    public void addNewStrategy(Strategy strategy) {
        System.out.println(strategy);
        strategyRepository.save(strategy);
    }

    public void deleteStrategy(Long strategyId) {
        boolean exists = strategyRepository.existsById(strategyId);
        if (!exists) {
        	throw new IllegalStateException("strategy with id " + strategyId + " does not exists");
        }
        strategyRepository.deleteById(strategyId);
    }

    public void updateStrategy(Long strategyId, String name, String description, String type) {
        	Strategy strategy = strategyRepository.findById(strategyId)
        			.orElseThrow(() -> new IllegalStateException(
        					"strategy with id " + strategyId + " does not exists"));
        	if (name != null && name.length() > 0 && !strategy.getName().equals(name)) {
        		strategy.setName(name);
        	}
        	if (description != null && description.length() > 0 && !strategy.getDescription().equals(description)) {
        		strategy.setDescription(description);
        	}
        	if (type != null && type.length() > 0 && !strategy.getType().equals(type)) {
        		strategy.setType(type);
        	}
        	strategyRepository.save(strategy);
    }
}
