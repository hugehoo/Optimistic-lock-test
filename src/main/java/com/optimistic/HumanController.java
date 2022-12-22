package com.optimistic;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/human")
@RequiredArgsConstructor
@Slf4j
public class HumanController {

    private final HumanService humanService;

    @GetMapping
    public String getHealthCheck() {
        return "health check";
    }

    @GetMapping("/decrease")
    public String decreaseMoney(@RequestParam(value = "name") String name,
                                @RequestParam(value = "money") int money) {

        String result;
        try {
            humanService.decreaseMoney(name, money);
            result = "현재 남은 돈 " + humanService.currentMoney(name);
        } catch (ObjectOptimisticLockingFailureException oe) {
            log.info("재시도");
            return decreaseMoney(name, money);
        }catch (Exception e) {
            log.info(e.toString());
            result = "에러 발생";
        }
        log.info(result);
        return result;
    }
}