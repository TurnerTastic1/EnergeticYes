package com.example.EnergeticYes.trader;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TraderService {

    private final TraderRepository traderRepository;

    public TraderService(TraderRepository traderRepository) {
        this.traderRepository = traderRepository;
    }

    public List<Trader> getTraders() {
        return traderRepository.findAll();
    };

    public void addNewTrader(Trader trader) {
        Optional<Trader> traderOptional = traderRepository.findTraderByEmail(trader.getEmail());
        if (traderOptional.isPresent()) {
            throw new IllegalStateException("Email already in use");
        }
        traderRepository.save(trader);
    }

    public void deleteTrader(Long traderId) {
        boolean exists = traderRepository.existsById(traderId);
        if (!exists) {
        	throw new IllegalStateException("Trader with id " + traderId + " does not exist.");
        }
        traderRepository.deleteById(traderId);
    }

    public void updateTrader(Long traderId, String name, String email) {
    	Trader trader = traderRepository.findById(traderId)
    			.orElseThrow(() -> new IllegalStateException(
    					"Trader with id " + traderId + " does not exist."));
    	if (name != null && name.length() > 0 && !trader.getName().equals(name)) {
    		trader.setName(name);
    	}
    	else if (email != null && email.length() > 0 && !trader.getEmail().equals(email)) {
    		Optional<Trader> traderOptional = traderRepository.findTraderByEmail(email);
    		if (traderOptional.isPresent()) {
    			throw new IllegalStateException("Email already in use");
    		}
    		trader.setEmail(email);
    	}
    	traderRepository.save(trader);
    }
}
