package com.example.EnergeticYes.trader;

import com.example.EnergeticYes.exception.CustomApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TraderService {

    private final TraderRepository traderRepository;

    public TraderService(TraderRepository traderRepository) {
        this.traderRepository = traderRepository;
    }

    public List<Trader> getTraders() {
        try {
            return traderRepository.findAll();
        } catch (Exception e) {
            throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to fetch traders");
        }
    };

    public ResponseEntity<Map<String, Object>> addNewTrader(Trader trader) {
        System.out.println(trader);
        List<String> errors = new ArrayList<>();
        if (trader.getName() == null || trader.getName().isEmpty()) {
            errors.add("Name is required");
        }

        if (trader.getEmail() == null || trader.getEmail().isEmpty()) {
            errors.add("Email is required");
        }

        Optional<Trader> traderOptional = traderRepository.findTraderByEmail(trader.getEmail());
        if (traderOptional.isPresent()) {
            errors.add("Email already exists");
        }

        if (!errors.isEmpty()) {
            throw new CustomApplicationException(HttpStatus.BAD_REQUEST, "Invalid request", errors);
        }

        try {
            traderRepository.save(trader);
        } catch (Exception e) {
            errors = new ArrayList<>();
            errors.add(e.toString());
            throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to save trader", errors);
        }

        return ResponseEntity.ok(Map.of("message", "Trader added successfully"));
    }

    public ResponseEntity<Map<String, Object>> deleteTrader(Long traderId) {
        boolean exists = traderRepository.existsById(traderId);
        if (!exists) {
            String errorMessage = "Trader with id " + traderId + " does not exist.";
        	throw new CustomApplicationException(HttpStatus.BAD_REQUEST, errorMessage);
        }
        traderRepository.deleteById(traderId);
        return ResponseEntity.ok(Map.of("message", "Trader deleted successfully"));
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
