package com.diploma.edu.source.validators;

import com.diploma.edu.source.entranceInteraction.ActionType;
import com.diploma.edu.source.entranceInteraction.EntranceInteractionParams;
import com.diploma.edu.source.exceptions.IncorrectDataException;
import com.diploma.edu.source.exceptions.IncorrectStatusException;
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
        if (!params.containsKey(EntranceInteractionParams.ACTION.getParam())){
            throw new IncorrectDataException("Взаимодействие невозможно!");
        }

        if (!params.containsKey("entranceId")){
            throw new IncorrectDataException("Взаимодействие невозможно!");
        }

        entrance = entranceService.getById(new BigInteger(params.get("entranceId")));

        if (params.get(EntranceInteractionParams.ACTION.getParam()).equals(ActionType.BLOCK.getAction()) || params.get(EntranceInteractionParams.ACTION.getParam()).equals(ActionType.UNBLOCK.getAction())){
            validateBlockUnblockInteraction(params);
        } else if (params.get(EntranceInteractionParams.ACTION.getParam()).equals(ActionType.CLOSE.getAction()) || params.get(EntranceInteractionParams.ACTION.getParam()).equals(ActionType.OPEN.getAction())){
            validateOpenCloseInteraction(params);
        } else {
            throw new IncorrectStatusException("Взаимодействие неверное!");
        }

    }

    private static void validateOpenCloseInteraction(Map<String, String> params){

        if (!entrance.getIsAvailable()){
            throw new IncorrectStatusException("Взаимодействие невозможно, данная дверь/шлагбаум заблокирован!");
        }

        if (!params.containsKey("ekeyId")){
            throw new IncorrectDataException("Взаимодействие невозможно!");
        }
    }

    private static void validateBlockUnblockInteraction(Map<String, String> params){
        if ((entrance.getIsAvailable() && params.get(EntranceInteractionParams.ACTION.getParam()).equals(ActionType.UNBLOCK.getAction())) ||
                (!entrance.getIsAvailable() && params.get(EntranceInteractionParams.ACTION.getParam()).equals(ActionType.BLOCK.getAction()))){
            throw new IncorrectStatusException("Взаимодействие невозможно!");
        }
    }

}
