package com.diploma.edu.source.validators;

import com.diploma.edu.source.entranceInteraction.ActionType;
import com.diploma.edu.source.exceptions.ResourceNotFoundException;
import com.diploma.edu.source.model.Entrance;
import com.diploma.edu.source.servicies.EntranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Map;

@Component
public class EntranceInteractionValidation {


    private static EntranceService entranceService;

    private static Entrance entrance;
    @Autowired
    public EntranceInteractionValidation(EntranceService entranceService){
        EntranceInteractionValidation.entranceService = entranceService;
    }

    public static void validate(Map<String, String> params){
        if (!params.containsKey("action")){
            throw new ResourceNotFoundException("Взаимодействие невозможно!");
        }

        if (!params.containsKey("entranceId")){
            throw new ResourceNotFoundException("Взаимодействие невозможно!");
        }

        entrance = entranceService.getById(new BigInteger(params.get("entranceId")));

        if (params.get("action").equals(ActionType.BLOCK.getAction()) || params.get("action").equals(ActionType.UNBLOCK.getAction())){
            validateBlockUnblockInteraction(params);
        } else if (params.get("action").equals(ActionType.CLOSE.getAction()) || params.get("action").equals(ActionType.OPEN.getAction())){
            validateOpenCloseInteraction(params);
        } else {
            throw new ResourceNotFoundException("Взаимодействие неверное!");
        }

    }

    private static void validateOpenCloseInteraction(Map<String, String> params){

        if (!entrance.getIsAvailable()){
            throw new ResourceNotFoundException("Взаимодействие невозможно, данная дверь/шлагбаум заблокирован!");
        }

        if (!params.containsKey("ekeyId")){
            throw new ResourceNotFoundException("Взаимодействие невозможно!");
        }
    }

    private static void validateBlockUnblockInteraction(Map<String, String> params){

        //TODO: check validation

        if ((entrance.getIsAvailable() && params.get("action").equals(ActionType.UNBLOCK)) ||
                (!entrance.getIsAvailable() && params.get("action").equals(ActionType.BLOCK))){
            throw new ResourceNotFoundException("Взаимодействие невозможно!");
        }
    }

}
