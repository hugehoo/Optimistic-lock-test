package com.optimistic;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HumanService {

    private final HumanRepository humanRepository;

    @Transactional
    public int currentMoney(String name) {
        Human human = humanRepository.findHumanByName(name);
        return human.getMoney();
    }

    @Transactional
    public int decreaseMoney(String name, int money) {
        Human human = humanRepository.findHumanByName(name);
        human.decreaseMoney(money);
        return human.getMoney();
    }
}
