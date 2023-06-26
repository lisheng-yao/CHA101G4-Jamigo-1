package com.jamigo.platform.CounterCtrl.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CounterCtrlDaoImpl implements CounterCtrlDao {

	@Autowired
	private DataSource ds;
//	private final String URL = "jdbc:mysql://localhost:3306/jamigo";
//	private final String USERNAME = "root";
//    private final String PASSWORD = "0000";
	
	private static final String INSERT = 
			  "insert into "
			+ "counter(cutPercent, counterAccount, counterPASSWORD, counterStat) "
			+ "values (?, ?, 'default1234', ?);";
	
	private static final String UPDATE = 
			  "update counter set cutPercent = ?, counterAccount = ?, "
			+ "counterStat = ? where counterNo = ?;";

	private static final String DELETE = 
			"delete from counter where counterNo = ?";
	
	private static final String FIND_BY_PRIMARY_KEY = 
			  "select counterNo, counterName, cutPercent, counterAccount, "
			+ " counterStat, counterGui, counterFloor, counterTel, "
			+ " counterPoc, counterPocPhone, counterPocAddress, counterEmail, counterBankAccount, "
			+ " counterReportcount, counterAbout, counterPic "
			+ "from counter where counterNo = ?;";
	
	private static final String GET_PART_INFO = 
			  "select counterNo, counterName, cutPercent, counterTel, "
			+ "counterStat from counter;";
	
	private static final String GET_ALL = 
			  "select counterNo, counterName, cutPercent, counterAccount, "
			+ " counterStat, counterGui, counterFloor, counterTel, "
			+ " counterPoc, counterPocPhone, counterPocAddress, counterEmail, counterBankAccount, "
			+ " counterReportcount, counterAbout, counterPic "
			+ "from counter;";
	
	private static final String FIND_BY_ACCOUNT_WITH_ID = 
			"SELECT counterNo FROM counter WHERE counterAccount = ? AND (select counterAccount from counter where counterNo = ?) <> ?;";
	private static final String FIND_BY_ACCOUNT = 
			"SELECT counterNo FROM counter WHERE counterAccount = ?;";
	
	@Override
	public List<CounterCtrlVO> getAll() {
		List<CounterCtrlVO> counterList = new ArrayList<>();
//		try(Connection conn = ServiceLocator.getInstance().getDataSource().getConnection();) {
//		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);) {
			try(Connection conn = ds.getConnection();) {
			PreparedStatement ps = conn.prepareStatement(GET_ALL);
			ResultSet rs = ps.executeQuery();
			// 少密碼/圖片
			while (rs.next()) {
				CounterCtrlVO counterVO = new CounterCtrlVO();
				counterVO.setCounterNo(rs.getInt(1));
				counterVO.setCounterName(rs.getString(2));
				counterVO.setCutPercent(rs.getFloat(3));
				counterVO.setCounterAccount(rs.getString(4));
				counterVO.setCounterStat(rs.getByte(5));
				counterVO.setCounterGui(rs.getString(6));
				counterVO.setCounterFloor(rs.getInt(7));
				counterVO.setCounterTel(rs.getString(8));
				counterVO.setCounterPoc(rs.getString(9));
				counterVO.setCounterPocPhone(rs.getString(10));
				counterVO.setCounterPocAddress(rs.getString(11));
				counterVO.setCounterEmail(rs.getString(12));
				counterVO.setCounterBankAccount(rs.getString(13));
				counterVO.setCounterReportCount(rs.getByte(14));
				counterVO.setCounterAbout(rs.getString(15));
				counterList.add(counterVO);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return counterList;
	}
	
	
	@Override
	public void insert(CounterCtrlDTO counterDTO) {
//		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);) {
		try(Connection conn = ds.getConnection();) {
			PreparedStatement ps = conn.prepareStatement(INSERT);
			// 少流水號/圖片
			ps.setFloat(1, counterDTO.getCutPercent());
			ps.setString(2, counterDTO.getCounterAccount());
			ps.setByte(3, counterDTO.getCounterStat());
			
			ps.executeUpdate();
//			System.out.println("kkkkk");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(CounterCtrlDTO counterDTO) {
//		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);) {
		try(Connection conn = ds.getConnection();) {
			PreparedStatement ps = conn.prepareStatement(UPDATE);
			// 少流水號/密碼/圖片
			ps.setFloat(1, counterDTO.getCutPercent());
			ps.setString(2, counterDTO.getCounterAccount());
			ps.setByte(3, counterDTO.getCounterStat());
			ps.setInt(4, counterDTO.getCounterNo());
			
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Integer counterno) {
//		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);) {
		try(Connection conn = ds.getConnection();) {
			PreparedStatement ps = conn.prepareStatement(DELETE);
			ps.setInt(1, counterno);
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public CounterCtrlVO findByPrimaryKey(Integer counterno) {
		CounterCtrlVO counterVO = new CounterCtrlVO();
//		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);) {
		try(Connection conn = ds.getConnection();) {
			PreparedStatement ps = conn.prepareStatement(FIND_BY_PRIMARY_KEY);
			ps.setInt(1, counterno);
			
			ResultSet rs = ps.executeQuery();
			// 少密碼/圖片
			if (rs.next()) {
				counterVO.setCounterNo(rs.getInt(1));
				counterVO.setCounterName(rs.getString(2));
				counterVO.setCutPercent(rs.getFloat(3));
				counterVO.setCounterAccount(rs.getString(4));
				counterVO.setCounterStat(rs.getByte(5));
				counterVO.setCounterGui(rs.getString(6));
				counterVO.setCounterFloor(rs.getInt(7));
				counterVO.setCounterTel(rs.getString(8));
				counterVO.setCounterPoc(rs.getString(9));
				counterVO.setCounterPocPhone(rs.getString(10));
				counterVO.setCounterPocAddress(rs.getString(11));
				counterVO.setCounterEmail(rs.getString(12));
				counterVO.setCounterBankAccount(rs.getString(13));
				counterVO.setCounterReportCount(rs.getByte(14));
				counterVO.setCounterAbout(rs.getString(15));
				counterVO.setCounterPic(rs.getBytes(16));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return counterVO;
	}

	@Override
	public List<CounterCtrlVO> getPartInfo() {
		List<CounterCtrlVO> counterList = new ArrayList<>();
//		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);) {
		try(Connection conn = ds.getConnection();) {
			PreparedStatement ps = conn.prepareStatement(GET_PART_INFO);
			ResultSet rs = ps.executeQuery();
			// 只有櫃位編號/櫃位名稱/抽成比例/櫃位電話/櫃位狀態
			while (rs.next()) {
				CounterCtrlVO counterVO = new CounterCtrlVO();
				counterVO.setCounterNo(rs.getInt(1));
				counterVO.setCounterName(rs.getString(2));
				counterVO.setCutPercent(rs.getFloat(3));
				counterVO.setCounterTel(rs.getString(4));
				counterVO.setCounterStat(rs.getByte(5));
				counterList.add(counterVO);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return counterList;
	}
	
	public CounterCtrlDTO getByAccount(Integer counterNo, String counterAccount) {
		CounterCtrlDTO counterDTO = new CounterCtrlDTO();
//		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);) {
		try(Connection conn = ds.getConnection();) {
			PreparedStatement ps = conn.prepareStatement(FIND_BY_ACCOUNT_WITH_ID);
			ps.setString(1, counterAccount);
			ps.setInt(2, counterNo);
			ps.setString(3, counterAccount);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				counterDTO.setCounterNo(rs.getInt(1));
				return counterDTO;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	public CounterCtrlDTO getByAccount(String counterAccount) {
		CounterCtrlDTO counterDTO = new CounterCtrlDTO();
//		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);) {
		try(Connection conn = ds.getConnection();) {
			PreparedStatement ps = conn.prepareStatement(FIND_BY_ACCOUNT);
			
			ps.setString(1, counterAccount);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				counterDTO.setCounterNo(rs.getInt(1));
				return counterDTO;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
