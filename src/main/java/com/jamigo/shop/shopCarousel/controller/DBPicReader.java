package com.jamigo.shop.shopCarousel.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.jamigo.shop.shopCarousel.model.ShopCarouselService;



@WebServlet("/DBPicReader")
public class DBPicReader extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ShopCarouselService shopCarouselSvc;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("image/jpg");
		ServletOutputStream out = response.getOutputStream();

		Integer shopCarouselNo = Integer.valueOf(request.getParameter("shopCarouselNo"));
		out.write(shopCarouselSvc.getOneShopCarousel(shopCarouselNo).getShopCarouselPic());

	}

}
