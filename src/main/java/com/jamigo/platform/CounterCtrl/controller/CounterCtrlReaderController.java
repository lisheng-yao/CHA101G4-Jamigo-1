package com.jamigo.platform.CounterCtrl.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jamigo.platform.CounterCtrl.model.CounterCtrlService;


@Controller
@RequestMapping("/counterCtrlImg")
public class CounterCtrlReaderController{
	
	@Autowired
	private CounterCtrlService service;

	@GetMapping("/{counterNo}")
	public void counterCtrlReader(@PathVariable Integer counterNo, HttpServletResponse resp) {
		
		try {
			byte[] fileContent = service.getOneCounter(counterNo).getCounterPic();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);
			resp.getOutputStream().write(fileContent);
			resp.getOutputStream().flush();
		} catch (Exception e) {
			try {
				// 抓取NullPointerException
				resp.sendRedirect("/Jamigo/platform/counter_ctrl/image-nodata/111.jpg");
//				e.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		ServletOutputStream out = resp.getOutputStream();
//
//		try {
//			Integer counterNo = Integer.parseInt(req.getParameter("counterNo"));
//			CounterCtrlService CounterCtrlService = new CounterCtrlService();
//			byte[] pic = CounterCtrlService.getOneCounter(counterNo).getCounterPic();
//			resp.setContentType("image/jpeg");
//			out.write(pic);
//			out.flush();
//		} catch (Exception e) {
////			e.printStackTrace();
//			InputStream in = getServletContext().getResourceAsStream("/CounterCtrlControll/image-nodata/111.jpg");
//			byte[] buf = new byte[in.available()];
//			in.read(buf);
//			out.write(buf);
//			out.flush();
//			in.close();
//		}
		
		
//		byte[] pic = null;
//		try(Connection conn = ServiceLocator.getInstance().getDataSource().getConnection();
//				ByteArrayOutputStream binaryOutStream = new ByteArrayOutputStream();
//				ServletOutputStream out = resp.getOutputStream();) {
//			PreparedStatement ps = conn.prepareStatement(FIND_PIC_BY_PRIMARY_KEY);
//			ps.setInt(1, counterNo); // 取得前端送出的id
//			ResultSet rs = ps.executeQuery();
			
//			if(rs.next()) {
//				pic = rs.getBytes(1);
////				InputStream pic = rs.getBinaryStream(1);
////				BufferedImage readImage = ImageIO.read(pic);
////				ImageIO.write(readImage, "jpeg", binaryOutStream);
////				byte[] bytePic = binaryOutStream.toByteArray();
//			}
//			resp.setContentType("image/jpeg");
//			out.write(pic);
//			out.flush();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
