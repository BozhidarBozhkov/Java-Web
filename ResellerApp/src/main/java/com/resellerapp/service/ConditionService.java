package com.resellerapp.service;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.ConditionType;
import com.resellerapp.repository.ConditionRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConditionService {
    private final ConditionRepository conditionRepository;

    public ConditionService(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    @PostConstruct
    public void conditionsInit(){
        if (!isConditionInit()) {
            List<Condition> conditions = new ArrayList<>();

            conditions.add(new Condition().setName(ConditionType.ACCEPTABLE).setDescription("The item is fairly worn but continues to function properly"));
            conditions.add(new Condition().setName(ConditionType.EXCELLENT).setDescription("In perfect condition"));
            conditions.add(new Condition().setName(ConditionType.GOOD).setDescription("Some signs of wear and tear or minor defects"));

            this.conditionRepository.saveAllAndFlush(conditions);
        }

    }

    public boolean isConditionInit() {
        return this.conditionRepository.count() > 0;
    }
}
