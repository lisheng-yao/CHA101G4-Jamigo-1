package com.jamigo.counter.activityOrder.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jamigo.activity.attendee.entity.ActivityAttendeeService;
import com.jamigo.activity.attendee.entity.ActivityAttendeeVO;
import com.jamigo.counter.activityOrder.entity.ActivityOrderDTO;
import com.jamigo.counter.activityOrder.entity.ActivityOrderService;
import com.jamigo.counter.activityOrder.entity.ActivityOrderVO;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;

@RestController
@RequestMapping("/activityOrder")
public class ActivityOrderController {
	
	@Autowired
	ActivityOrderService activityOrderService;
	
	@Autowired
	ActivityAttendeeService activityAttendeeService;
	
	@PostMapping("/insert")
	public ResponseEntity<?> insert(@RequestBody ActivityOrderVO activityOrderVO){
		
		Integer activityOrderNo = activityOrderService.add(activityOrderVO);
		return ResponseEntity.ok(activityOrderNo);
		
	}
	
	@PostMapping("/insertExp")
	public ResponseEntity<?> updateExp(@RequestBody ActivityOrderVO activityOrderVOTemp) {
		ActivityOrderVO activityOrderVO = activityOrderService.getById(activityOrderVOTemp.getActivityOrderNo());
		activityOrderVO.setActivityScore(activityOrderVOTemp.getActivityScore());
		activityOrderVO.setCommentDetail(activityOrderVOTemp.getCommentDetail());
		activityOrderService.update(activityOrderVO);
		return ResponseEntity.ok("新增成功");
	}
	
	@PutMapping("/updatePart")
	public void updatePart(@RequestBody ActivityOrderDTO activityOrderDTO){
		// 先查找DB物件原始的值，將從前端更新的值(Temp)賦值到新的物件上(VO)
		ActivityOrderVO activityOrderVOTemp = activityOrderDTO.getActivityOrderVO();
		ActivityOrderVO activityOrderVO = activityOrderService.getById(activityOrderVOTemp.getActivityOrderNo());
		
		activityOrderVO.setActivityPaymentStat(activityOrderVOTemp.getActivityPaymentStat());
		if(activityOrderVOTemp.getNumberOfAttendee() != null)
			activityOrderVO.setNumberOfAttendee(activityOrderVOTemp.getNumberOfAttendee());
		// 再把已更新部分的新物件做update
		activityOrderService.update(activityOrderVO);
		
		List<ActivityAttendeeVO> activityAttendeeVOList = activityOrderDTO.getActivityAttendeeVOList();
		for (ActivityAttendeeVO activityAttendeeVO : activityAttendeeVOList) {
			if(activityAttendeeVO.getAttendeeNo() != null)
				activityAttendeeService.update(activityAttendeeVO);
			else {
				ActivityAttendeeVO activityAttendeeVO2 = new ActivityAttendeeVO();
				activityAttendeeVO2.setActivityOrderNo(activityAttendeeVO.getActivityOrderNo());
				activityAttendeeVO2.setAttendeeName(activityAttendeeVO.getAttendeeName());
				activityAttendeeVO2.setAttendeeGender(activityAttendeeVO.getAttendeeGender());
				activityAttendeeVO2.setAttendeeAge(activityAttendeeVO.getAttendeeAge());
				activityAttendeeService.update(activityAttendeeVO2);
			}
			
		}
		
	}
	
	@GetMapping("/getActivityOrderById/{activityOrderNo}")
	public ActivityOrderVO getActivityOrderById(@PathVariable Integer activityOrderNo){
		
		return activityOrderService.getById(activityOrderNo);
		
	}

	@GetMapping("/getActivityOrderByMemberNo/{memberNo}")
	public List<ActivityOrderVO> getActivityOrderByMemberNo(@PathVariable Integer memberNo){
		
		return activityOrderService.getByMemberNo(memberNo);
		
	}
	
	@GetMapping("/getAll")
	public List<ActivityOrderVO> getAll(){
		
		return activityOrderService.getAll();
		
	}

	@GetMapping("/getByCounterNO/{counterNo}")
	public List<ActivityOrderVO> getByCounterNO(@PathVariable Integer counterNo){
		
		return activityOrderService.getByCounterNO(counterNo);
		
	}
	
	@PostMapping("/ecpayCheckout")
	public String ecpayCheckout(@RequestBody MultiValueMap<String, String> requestParams) {
		System.out.println("ecpayCheckout!!!!!!!!!!!!");
		Integer activityOrderNo = Integer.parseInt(requestParams.getFirst("activityOrderNo"));
        String uuId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);
        
        ActivityOrderVO vo = activityOrderService.getById(activityOrderNo);
        
        Timestamp timestamp = vo.getActivityEnrollmentTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String strTimestamp = sdf.format(timestamp);
        
        Integer attendeeNum = vo.getNumberOfAttendee() + 1;
        Integer money = vo.getActivity().getActivityCost();
        Integer totalPay = money * attendeeNum;
        
        AllInOne all = new AllInOne("");

        AioCheckOutALL obj = new AioCheckOutALL();
        obj.setMerchantTradeNo(uuId);
        obj.setMerchantTradeDate(strTimestamp);
        obj.setTotalAmount(String.valueOf(totalPay));

        obj.setTradeDesc("test Description");
        obj.setItemName("Jamigo Mall 線下活動");
        String orderResultURL = "http://localhost:8080/Jamigo/activityOrder/paidResult/" + activityOrderNo.toString();
        obj.setOrderResultURL(orderResultURL);
        obj.setReturnURL("http://172.20.10.11:5000");
        obj.setNeedExtraPaidInfo("N");
//        obj.setClientBackURL("http://localhost:8080/Jamigo/shop/main_page/%E5%95%86%E5%9F%8E%E9%A6%96%E9%A0%81.html");
        String form = all.aioCheckOut(obj, null);

        return form;
    }

    @PostMapping("/paidResult/{activityOrderNo}")
    public void checkPaidResult(
            @PathVariable Integer activityOrderNo,
            @RequestBody String formData) {
    	System.out.println("activityOrderNo" +activityOrderNo);
    	Map<String, String> map = new HashMap<String, String>();

        String[] pairs = formData.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            map.put(pair.substring(0, idx), pair.substring(idx + 1));
        }
        
        if ("1".equals(map.get("RtnCode"))) {
//        	System.out.println("成功功功功");
        	ActivityOrderVO orderVo = activityOrderService.getById(activityOrderNo);
        	orderVo.setActivityPaymentStat(Byte.valueOf((byte) 1));
        	activityOrderService.update(orderVo);
        }
    }
    
//    @GetMapping("/paidResult_test/{activityOrderNo}")
//    public void checkPaidResult(@PathVariable Integer activityOrderNo) {
//    	System.out.println("activityOrderNo" +activityOrderNo);
//    	
//    	ActivityOrderVO orderVo = activityOrderService.getById(activityOrderNo);
//    	orderVo.setActivityPaymentStat(Byte.valueOf((byte) 1));
//    	activityOrderService.update(orderVo);
//    	System.out.println("PaymentStat: " + orderVo.getActivityPaymentStat());
//    }
}
