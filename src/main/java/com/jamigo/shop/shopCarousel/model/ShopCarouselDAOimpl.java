package com.jamigo.shop.shopCarousel.model;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class ShopCarouselDAOimpl implements ShopCarouselDAO {
	@Autowired
	private DataSource ds;

//	static {
//		try {
//			InitialContext ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/jamigo");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}

	private static final String GET_ALL_STMT = "SELECT shopCarouselNo,shopCarouselTitle,shopCarouselText,shopCarouselStartTime,shopCarouselEndTime,shopCarouselState,shopCarouselUrl FROM shop_carousel";
	private static final String INSERT_STMT = "INSERT INTO shop_carousel (shopCarouselTitle,shopCarouselText,shopCarouselPic,shopCarouselStartTime,shopCarouselEndTime,shopCarouselState,shopCarouselUrl) VALUES (?, ?, ?, ?, ?,?,?)";
	private static final String GET_ONE_STMT = "SELECT shopCarouselNo,shopCarouselTitle,shopCarouselText,shopCarouselPic,shopCarouselStartTime,shopCarouselEndTime,shopCarouselState,shopCarouselUrl FROM shop_carousel WHERE shopCarouselNo = ?";
	private static final String UPDATE = "UPDATE shop_carousel set shopCarouselTitle = ?,shopCarouselText = ?,shopCarouselPic = ?,shopCarouselStartTime = ?,shopCarouselEndTime = ?,shopCarouselState = ?,shopCarouselUrl = ? WHERE shopCarouselNo = ?";
	private static final String DELETE = "DELETE FROM shop_carousel where shopCarouselNo = ?";

	// =========================== 查詢全部 =================================
	@Override
	public List<ShopCarouselVO> getAll() {
		List<ShopCarouselVO> list = new ArrayList<ShopCarouselVO>();
		ShopCarouselVO shopCarouselVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				shopCarouselVO = new ShopCarouselVO();

				shopCarouselVO.setShopCarouselNo(rs.getInt("shopCarouselNO"));
				shopCarouselVO.setShopCarouselTitle(rs.getString("shopCarouselTitle"));
				shopCarouselVO.setShopCarouselText(rs.getString("shopCarouselText"));
				// shopCarouselVO.setShopCarouselPic(rs.getBytes("shopCarouselPic")); 圖片用不到不查了
				// System.out.println(rs.getBytes("shopCarouselPic"));
				shopCarouselVO.setShopCarouselStartTime(rs.getDate("shopCarouselStartTime"));
				shopCarouselVO.setShopCarouselEndTime(rs.getDate("shopCarouselEndTime"));
				shopCarouselVO.setShopCarouselState(rs.getInt("shopCarouselState"));
				shopCarouselVO.setShopCarouselUrl(rs.getString("shopCarouselUrl"));
				list.add(shopCarouselVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	// =========================== 新增 =================================
	@Override
	public void insert(ShopCarouselVO shopCarouselVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, shopCarouselVO.getShopCarouselTitle());
			pstmt.setString(2, shopCarouselVO.getShopCarouselText());
			pstmt.setBytes(3, shopCarouselVO.getShopCarouselPic());
			pstmt.setDate(4, shopCarouselVO.getShopCarouselStartTime());
			pstmt.setDate(5, shopCarouselVO.getShopCarouselEndTime());
			pstmt.setInt(6, shopCarouselVO.getShopCarouselState());
			pstmt.setString(7, shopCarouselVO.getShopCarouselUrl());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	// =============================== 查單一 ===================================
	@Override
	public ShopCarouselVO findByPrimaryKey(Integer shopCarouselNo) {

		ShopCarouselVO shopCarouselVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, shopCarouselNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				shopCarouselVO = new ShopCarouselVO();
				shopCarouselVO.setShopCarouselNo(rs.getInt("shopCarouselNO"));
				shopCarouselVO.setShopCarouselTitle(rs.getString("shopCarouselTitle"));
				shopCarouselVO.setShopCarouselText(rs.getString("shopCarouselText"));
				shopCarouselVO.setShopCarouselPic(rs.getBytes("shopCarouselPic"));
				// System.out.println(rs.getBytes("shopCarouselPic"));
				shopCarouselVO.setShopCarouselStartTime(rs.getDate("shopCarouselStartTime"));
				shopCarouselVO.setShopCarouselEndTime(rs.getDate("shopCarouselEndTime"));
				shopCarouselVO.setShopCarouselState(rs.getInt("shopCarouselState"));
				shopCarouselVO.setShopCarouselUrl(rs.getString("shopCarouselUrl"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return shopCarouselVO;
	}

	@Override
	public void update(ShopCarouselVO shopCarouselVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, shopCarouselVO.getShopCarouselTitle());
			pstmt.setString(2, shopCarouselVO.getShopCarouselText());
			pstmt.setBytes(3, shopCarouselVO.getShopCarouselPic());
			pstmt.setDate(4, shopCarouselVO.getShopCarouselStartTime());
			pstmt.setDate(5, shopCarouselVO.getShopCarouselEndTime());
			pstmt.setInt(6, shopCarouselVO.getShopCarouselState());
			pstmt.setString(7, shopCarouselVO.getShopCarouselUrl());
			pstmt.setInt(8, shopCarouselVO.getShopCarouselNo());

			pstmt.executeUpdate();
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer shopCarouselNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, shopCarouselNo);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

}
