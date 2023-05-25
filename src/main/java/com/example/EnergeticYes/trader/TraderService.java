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

    public ResponseEntity<Map<String, Object>> getTraders() {
        try {
            List<Trader> traders = traderRepository.findAll();
            return ResponseEntity.ok(Map.of("message", "Traders fetched successfully", "traders", traders));
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
        if (traderId == null) {
            String errorMessage = "Trader id is required.";
        	throw new CustomApplicationException(HttpStatus.BAD_REQUEST, errorMessage);
        }
        boolean exists = traderRepository.existsById(traderId);
        if (!exists) {
            String errorMessage = "Trader with id " + traderId + " does not exist.";
        	throw new CustomApplicationException(HttpStatus.BAD_REQUEST, errorMessage);
        }

        try {
            traderRepository.deleteById(traderId);
        } catch (Exception e) {
            String errorMessage = "Unable to delete trader with id " + traderId;
            List<String> errors = new ArrayList<>();
            errors.add(e.toString());
        	throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage, errors);
        }

        return ResponseEntity.ok(Map.of("message", "Trader deleted successfully"));
    }

    public ResponseEntity<Map<String, Object>> updateTrader(Long traderId, String name, String email) {
        if (traderId == null) {
            throw new CustomApplicationException(HttpStatus.BAD_REQUEST, "Trader id is required.");
        }

    	Trader trader = traderRepository.findById(traderId)
    			.orElseThrow(() -> new CustomApplicationException(
                        HttpStatus.BAD_REQUEST, "Trader with id " + traderId + " does not exist."));

    	if (name != null && name.length() > 0 && !trader.getName().equals(name)) {
    		trader.setName(name);
    	}
    	else if (email != null && email.length() > 0 && !trader.getEmail().equals(email)) {
    		Optional<Trader> traderOptional = traderRepository.findTraderByEmail(email);
    		if (traderOptional.isPresent()) {
    			throw new CustomApplicationException(HttpStatus.BAD_REQUEST, "Email already in use");
    		}
    		trader.setEmail(email);
    	} else {
            throw new CustomApplicationException(HttpStatus.BAD_REQUEST, "No fields to update");
        }

        try {
            traderRepository.save(trader);
            return ResponseEntity.ok(Map.of("message", "Trader updated successfully"));
        } catch (Exception e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.toString());
            throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to update trader", errors);
        }
    }
}
