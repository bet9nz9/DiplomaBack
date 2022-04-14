package com.diploma.edu.source.entranceInteraction;

import com.diploma.edu.source.model.Ekey;
import com.diploma.edu.source.model.Entrance;
import com.diploma.edu.source.model.Logger;
import com.diploma.edu.source.servicies.EkeyService;
import com.diploma.edu.source.servicies.EntranceService;
import com.diploma.edu.source.servicies.LoggerService;
import com.diploma.edu.source.validators.EntranceInteractionValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Map;

@Component
public class EntranceActions {


    private static EkeyService ekeyService;

    private static EntranceService entranceService;

    private static LoggerService loggerService;

    private static Logger logger;

    @Autowired
    public EntranceActions(EkeyService ekeyService, EntranceService entranceService, LoggerService loggerService){
        EntranceActions.ekeyService = ekeyService;
        EntranceActions.entranceService = entranceService;
        EntranceActions.loggerService = loggerService;
    }

    public static void interact(Map<String, String> params){

        EntranceInteractionValidation.validate(params);

        String action = params.get("action");

        if (action.equals(ActionType.BLOCK.getAction()) || action.equals(ActionType.UNBLOCK.getAction())){
            blockUnblockInteraction(params);
        } else {
            openCloseInteraction(params);
        }
    }

    private static void blockUnblockInteraction(Map<String, String> params){
        logger = new Logger();
        Entrance entrance = entranceService.getById(new BigInteger(params.get("entranceId")));

        logger.setDateAndTime(new Date());
        logger.setEntrance(entrance);
        logger.setMessage(MessageFormat.format(ActionMessage.BLOCK_UNBLOCK_MESSAGE.getMessage(),
                params.get("entranceId"),
                params.get("action")));

        loggerService.create(logger);
        entrance.setIsAvailable(!entrance.getIsAvailable());
        if (!entrance.getIsAvailable()){
            entrance.setIsOpen(false);
        }
        entranceService.update(entrance);
    }

    private static void openCloseInteraction(Map<String, String> params){
        logger = new Logger();
        Ekey ekey = ekeyService.getById(new BigInteger(params.get("ekeyId")));
        Entrance entrance = entranceService.getById(new BigInteger(params.get("entranceId")));

        logger.setDateAndTime(new Date());
        logger.seteKey(ekey);
        logger.setEntrance(entrance);
        logger.setMessage(MessageFormat.format(ActionMessage.OPEN_CLOSE_MESSAGE.getMessage(),
                params.get("action"),
                entrance.getId(),
                ekey.getId()));

        loggerService.create(logger);
        entrance.setIsOpen(!entrance.getIsOpen());
        entranceService.update(entrance);
    }

}
