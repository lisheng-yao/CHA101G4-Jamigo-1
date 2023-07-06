package com.jamigo.shop.cart.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jamigo.member.member_data.dao.MemberDataDAO;
import com.jamigo.member.member_data.entity.MemberData;
import com.jamigo.shop.cart.dto.CartDTO;
import com.jamigo.shop.cart.dto.CouponInfoDTO;
import com.jamigo.shop.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private MemberDataDAO memberDataDAO;

    @Override
    public String addOneToCart(CartDTO cartItem, Integer memberNo) {
        Gson gson = new Gson();
        //轉成Json字串
        String dtoString = gson.toJson(cartItem);
        //在轉成Json物件存進Redis
        JsonObject newItem = gson.fromJson(dtoString, JsonObject.class);

        Jedis jedis = null;
        try {
            //取得連線
            jedis = new Jedis("localhost", 6379);
            //確認該會員購物車是否存在
            String cartString = jedis.get("memberNo: " + memberNo);

            //如果不存在建立新的購物車
            JsonArray cart = null;
            if (cartString == null){
                //沒有購物車建一個新的購物車
                cart = new JsonArray();
                //把購物項加入JSONArray(全新購物車)
                cart.add(newItem);
                //包成一個字串存進去Redis
                cartString = cart.toString();
                //放入Redis
                jedis.set("memberNo: " + memberNo, cartString);
            }else {
                //有購物車
                //String cartString轉回JSONArray
                cart = gson.fromJson(cartString, JsonArray.class);

                //檢查商品是否存在購物車
                boolean itemExists = false;
                for (JsonElement element : cart){
                    //取出購物車原有項目元素變成JSONObject
                    JsonObject oldItem = element.getAsJsonObject();

                    //如果productNo一樣，商品數量+1
                    if(oldItem.get("productNo").getAsInt() == newItem.get("productNo").getAsInt()){
                        //舊的商品數量
                        int quantity = oldItem.get("quantity").getAsInt();
                        //新的商品數量
                        int newQuantity = newItem.get("quantity").getAsInt();
                        //新舊數量加總
                        oldItem.addProperty("quantity", quantity + newQuantity);
                        //標記商品存在購物車
                        itemExists = true;
                        break;
                    }
                }
                //商品不存在購物車
                if(!itemExists){
                    cart.add(newItem);
                }
                //更新購物車字串
                cartString = cart.toString();
                //存入Redis
                jedis.set("memberNo: " + memberNo, cartString);
                System.out.println(cartString);
            }
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return "成功加入Redis";
    }

    @Override
    public String changeOneInCart(CartDTO cartItem, Integer memberNo) {
        Gson gson = new Gson();
        //轉成Json字串
        String dtoString = gson.toJson(cartItem);
        //在轉成Json物件存進Redis
        JsonObject newItem = gson.fromJson(dtoString, JsonObject.class);
        Jedis jedis = null;
        try {
            //取得連線
            jedis = new Jedis("localhost", 6379);
            //確認該會員購物車是否存在
            String cartString = jedis.get("memberNo: " + memberNo);

            //如果不存在建立新的購物車
            JsonArray cart = null;
            if (cartString == null){
                //沒有購物車建一個新的購物車
                cart = new JsonArray();
                //把購物項加入JSONArray(全新購物車)
                cart.add(newItem);
                //包成一個字串存進去Redis
                cartString = cart.toString();
                //放入Redis
                jedis.set("memberNo: " + memberNo, cartString);
            }else {
                //有購物車
                //String cartString轉回JSONArray
                cart = gson.fromJson(cartString, JsonArray.class);

                //檢查商品是否存在購物車
                boolean itemExists = false;
                for (JsonElement element : cart){
                    //取出購物車原有項目元素變成JSONObject
                    JsonObject oldItem = element.getAsJsonObject();

                    if(oldItem.get("productNo").getAsInt() == newItem.get("productNo").getAsInt()){
                        //舊的商品數量
                        int quantity = oldItem.get("quantity").getAsInt();
                        //新的商品數量
                        int newQuantity = newItem.get("quantity").getAsInt();
                        //覆蓋為新的商品數量
                        oldItem.addProperty("quantity", newQuantity);
                        //標記商品存在購物車
                        itemExists = true;
                        break;
                    }
                }
                //商品不存在購物車
                if(!itemExists){
                    cart.add(newItem);
                }
                //更新購物車字串
                cartString = cart.toString();
                //存入Redis
                jedis.set("memberNo: " + memberNo, cartString);
                System.out.println(cartString);
            }
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return "成功加入Redis";
    }

    @Override
    public String deleteOneInCart(CartDTO cartItem, Integer memberNo) {
        Gson gson = new Gson();
        //轉成Json字串
        String dtoString = gson.toJson(cartItem);
        //在轉成Json物件存進Redis
        JsonObject newItem = gson.fromJson(dtoString, JsonObject.class);
        Jedis jedis = null;
        try {
            //取得連線
            jedis = new Jedis("localhost", 6379);
            //確認該會員購物車是否存在
            String cartString = jedis.get("memberNo: " + memberNo);

            //如果不存在建立新的購物車
            JsonArray cart = null;
            if (cartString == null){
                //沒有購物車建一個新的購物車
                cart = new JsonArray();
                //把購物項加入JSONArray(全新購物車)
                cart.add(newItem);
                //包成一個字串存進去Redis
                cartString = cart.toString();
                //放入Redis
                jedis.set("memberNo: " + memberNo, cartString);
            }else {
                //有購物車
                //String cartString轉回JSONArray
                cart = gson.fromJson(cartString, JsonArray.class);

                //檢查商品是否存在購物車
                boolean itemExists = false;
                for (JsonElement element : cart){
                    //取出購物車原有項目元素變成JSONObject
                    JsonObject oldItem = element.getAsJsonObject();

                    if(oldItem.get("productNo").getAsInt() == newItem.get("productNo").getAsInt()){

                        cart.remove(oldItem);
                        //標記商品存在購物車
                        itemExists = true;
                        break;
                    }
                }
                //商品不存在購物車
                if(!itemExists){
                    cart.add(newItem);
                }
                //更新購物車字串
                cartString = cart.toString();
                //存入Redis
                jedis.set("memberNo: " + memberNo, cartString);
                System.out.println(cartString);
            }
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return "成功移除Redis";
    }


    @Override
    public List<CartDTO> findAllCartItem(Integer memberNo) {
        List<CartDTO> cartDTOList = new ArrayList<>();
        Jedis jedis = null;
        Gson gson = new Gson();

        try {
            //取得jedis連線
            jedis = new Jedis("localhost", 6379);

            //先取得該會員購物車
            String cartString = jedis.get("memberNo: " + memberNo);
            //如果購物車存在
            if(cartString != null){
                JsonArray cart = gson.fromJson(cartString, JsonArray.class);
                //迭代取出每個JSONObject
                for (JsonElement element : cart){
                    JsonObject oldItem = element.getAsJsonObject();
                    //迴圈每次都建立新物件
                    CartDTO cartDTO = new CartDTO();
                    cartDTO.setCounterNo(oldItem.get("counterNo").getAsInt());
                    cartDTO.setCounterName(oldItem.get("counterName").getAsString());
                    cartDTO.setProductNo(oldItem.get("productNo").getAsInt());
                    cartDTO.setProductName(oldItem.get("productName").getAsString());
                    cartDTO.setProductPrice(oldItem.get("productPrice").getAsInt());
                    cartDTO.setQuantity(oldItem.get("quantity").getAsInt());
                    //加進回應前端的List
                    cartDTOList.add(cartDTO);
                }
            }
        }finally {
            if (jedis != null){
                jedis.close();
            }
        }
        return cartDTOList;
    }

    @Override
    public List<CouponInfoDTO> getCouponsByMemberNo(Integer memberNo) {
        String sql = "SELECT mc.memberCouponNo, mc.couponTypeNo, ct.couponConditions, ct.couponPrice, ct.couponLowest, ct.counterNo " +
                     "FROM member_coupon mc " +
                     "JOIN coupon_type ct ON mc.couponTypeNo = ct.couponTypeNo " +
                     "WHERE mc.memberNo = :memberNo AND mc.couponUsedStat = 0 AND ct.couponExpireDate > current_timestamp();";
        Map<String , Object> map = new HashMap<>();
        map.put("memberNo", memberNo);
        List<CouponInfoDTO> memberCouponsList = namedParameterJdbcTemplate.query(sql, map, (rs, rowNum) -> {
            CouponInfoDTO couponInfoDTO = new CouponInfoDTO();
            couponInfoDTO.setMemberCouponNo(rs.getInt("memberCouponNo"));
            couponInfoDTO.setCounterNo(rs.getInt("counterNo"));
            couponInfoDTO.setCouponTypeNo(rs.getInt("couponTypeNo"));
            couponInfoDTO.setCouponConditions(rs.getString("couponConditions"));
            couponInfoDTO.setCouponPrice(rs.getInt("couponPrice"));
            couponInfoDTO.setCouponLowest(rs.getInt("couponLowest"));
            return couponInfoDTO;
        });
        return memberCouponsList;
    }

    @Override
    public Integer getMemberPointsByNo(Integer memberNo) {
        MemberData member = memberDataDAO.selectById(memberNo);
        Integer memberPoints = member.getMemberPoints();
        System.out.println(memberPoints);
        return memberPoints;
    }

}
