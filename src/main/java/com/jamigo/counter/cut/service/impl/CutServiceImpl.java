package com.jamigo.counter.cut.service.impl;

import com.jamigo.counter.counter.dao.CounterRepository;
import com.jamigo.counter.counter.entity.Counter;
import com.jamigo.counter.cut.dto.CutForCounterDTO;
import com.jamigo.counter.cut.dto.CutForPlatformDTO;
import com.jamigo.counter.cut.entity.Cut;
import com.jamigo.counter.cut.repo.CutRepository;
import com.jamigo.counter.cut.service.CutService;
import com.jamigo.shop.counter_order.entity.CounterOrder;
import com.jamigo.shop.counter_order.repo.CounterOrderRepository;
import com.jamigo.shop.platform_order.entity.PlatformOrder;
import com.jamigo.shop.platform_order.repo.PlatformOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

@Service
public class CutServiceImpl implements CutService {

    @Autowired
    private CutRepository cutRepository;

    @Autowired
    private CounterOrderRepository counterOrderRepository;

    @Autowired
    private PlatformOrderRepository platformOrderRepository;

    @Autowired
    private CounterRepository counterRepository;

    @Override
    public List<CutForPlatformDTO> getAllCutData() {

        List<Cut> cutList = cutRepository.findAll();

        List<CutForPlatformDTO> cutForPlatformDTOList = new ArrayList<>();

        for (var item : cutList) {

            CutForPlatformDTO cutForPlatformDTO = new CutForPlatformDTO();
            cutForPlatformDTO.setCutNo(item.getCutNo());
            cutForPlatformDTO.setMonthlyTime(item.getMonthlyTime());

            Counter counter = counterRepository.findById(item.getCounterNo()).orElse(null);
            if (counter != null) {
                cutForPlatformDTO.setCounterName(counter.getCounterName());
                cutForPlatformDTO.setCutPercent(counter.getCutPercent());
            }

            cutForPlatformDTO.setCutPayStat(item.getCutPayStat());
            cutForPlatformDTO.setMonthly(item.getMonthly());
            cutForPlatformDTO.setCutMoney(item.getMonthly() - item.getCutMonthly());

            cutForPlatformDTOList.add(cutForPlatformDTO);
        }

        return cutForPlatformDTOList;
    }

    @Override
    public List<CutForCounterDTO> getCounterAllCutData(Integer counterNo) {

        List<Cut> cutList = cutRepository.findByCounterNo(counterNo);

        List<CutForCounterDTO> cutForCounterDTOList = new ArrayList<>();

        for (var item : cutList) {

            CutForCounterDTO cutForCounterDTO = new CutForCounterDTO();
            cutForCounterDTO.setCutNo(item.getCutNo());
            cutForCounterDTO.setMonthlyTime(item.getMonthlyTime());

            Counter counter = counterRepository.findById(item.getCounterNo()).orElse(null);
            if (counter != null) {
                cutForCounterDTO.setCutPercent(counter.getCutPercent());
            }


            cutForCounterDTO.setMonthly(item.getMonthly());
            cutForCounterDTO.setCutPayStat(item.getCutPayStat());
            cutForCounterDTO.setCutMoney(item.getMonthly() - item.getCutMonthly());
            cutForCounterDTO.setCutMonthly(item.getCutMonthly());

            cutForCounterDTOList.add(cutForCounterDTO);
        }

        return cutForCounterDTOList;
    }

//    @Scheduled(fixedRate = 60000) // 每 60 秒執行一次
    @Scheduled(cron = "0 0 0 1 * ?")
    public void calculateMonthlyIncome() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfPreviousMonth = now.minusMonths(1).withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endOfPreviousMonth = startOfPreviousMonth.plusMonths(1).minusSeconds(1);

        Timestamp startTimestamp = Timestamp.valueOf(startOfPreviousMonth);
        Timestamp endTimestamp = Timestamp.valueOf(endOfPreviousMonth);

        List<PlatformOrder> platformOrders = platformOrderRepository.findByOrderTimeBetween(startTimestamp, endTimestamp);

        Map<Integer, List<CounterOrder>> counterOrdersMap = platformOrders.stream()
                .flatMap(po -> counterOrderRepository.findAllByPlatformOrderNo(po.getPlatformOrderNo()).stream())
                .collect(Collectors.groupingBy(CounterOrder::getCounterNo));

        counterOrdersMap.forEach((counterNo, orders) -> {
            calculateAndSaveCutForCounter(counterNo, orders, Timestamp.valueOf(startOfPreviousMonth));
        });


    }

    @Async("taskExecutor")
    public void calculateAndSaveCutForCounter(Integer counterNo, List<CounterOrder> orders, Timestamp monthForCut) {

        System.out.println("執行櫃位編號 " + counterNo + " 的排程器，正在結算 " + (monthForCut.getMonth()+1) + " 月");

        int totalIncome = orders.stream()
                .mapToInt(CounterOrder::getActuallyPaid)
                .sum();

        Counter counter = counterRepository.findById(counterNo).orElse(null);
        double rate = 0;
        if (counter != null) {
            rate = counter.getCutPercent();
        }

        Cut cut = new Cut();
        cut.setCounterNo(counterNo);
        cut.setMonthly(totalIncome);

        int cutMonthly = (int) (totalIncome * (1 - rate));

        cut.setCutMonthly(cutMonthly);
        cut.setCutPayStat((byte) 0);
        cut.setMonthlyTime(monthForCut);

        cutRepository.save(cut);
    }


//    @Scheduled(fixedRate = 60000) // 每 60 秒執行一次
//    public void calculateMissingMonthlyIncome() {
//        Optional<PlatformOrder> firstOrderOpt = platformOrderRepository.findFirstByOrderByOrderTimeAsc();
//
//        if (firstOrderOpt.isPresent()) {
//            LocalDateTime firstOrderTime = firstOrderOpt.get().getOrderTime().toLocalDateTime();
//            LocalDateTime now = LocalDateTime.now();
//
//            LocalDateTime startOfMonthForFirstOrder = firstOrderTime.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
//
//            for (LocalDateTime startOfMonth = startOfMonthForFirstOrder;
//                 startOfMonth.isBefore(now.withDayOfMonth(1).minusMonths(1));
//                 startOfMonth = startOfMonth.plusMonths(1)) {
//
//                LocalDateTime endOfMonth = startOfMonth.plusMonths(1).minusSeconds(1);
//                Timestamp startTimestamp = Timestamp.valueOf(startOfMonth);
//                Timestamp endTimestamp = Timestamp.valueOf(endOfMonth);
//
//                List<PlatformOrder> platformOrders = platformOrderRepository.findByOrderTimeBetween(startTimestamp, endTimestamp);
//
//                Map<Integer, List<CounterOrder>> counterOrdersMap = platformOrders.stream()
//                        .flatMap(po -> counterOrderRepository.findAllByPlatformOrderNo(po.getPlatformOrderNo()).stream())
//                        .collect(Collectors.groupingBy(CounterOrder::getCounterNo));
//
//                counterOrdersMap.forEach((counterNo, orders) -> {
//                    calculateAndSaveCutForCounter(counterNo, orders, startTimestamp);
//                });
//            }
//        } else {
//            System.out.println("沒有任何訂單資料");
//        }
//    }
}
