package in.fssa.srcatering.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.model.AddressBook;
import in.fssa.srcatering.util.ConnectionUtil;
import in.fssa.srcatering.util.Logger;

public class AddressBookDAO {

	/**
	 * 
	 * @param addressBook
	 * @throws DAOException
	 */
	public void create(AddressBook addressBook) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			String query = "INSERT INTO address_book(user_id, name, email, phone_number,door_no,street_name,sub_locality, city,"
					+ "district, state, pincode,set_as_default) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, addressBook.getUserId());
			ps.setString(2, addressBook.getName().trim());
			ps.setString(3, addressBook.getEmail().trim());
			ps.setString(4, addressBook.getPhoneNumber().trim());
			ps.setString(5, addressBook.getDoorNo().trim());
			ps.setString(6, addressBook.getStreetName().trim());
			ps.setString(7, addressBook.getSubLocality().trim());
			ps.setString(8, addressBook.getCity().trim());
			ps.setString(9, addressBook.getDistrict().trim());
			ps.setString(10, addressBook.getState().trim());
			ps.setInt(11, addressBook.getPincode());
			ps.setInt(12, addressBook.isSetAsDefault());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}
	
	/**
	 * 
	 * @param addressBook
	 * @throws DAOException
	 */
	public void update(AddressBook addressBook) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			String query = "UPDATE address_book SET user_id = ?, name = ?, email = ?, phone_number = ?, door_no = ?,"
					+ "street_name = ?, stub_locality = ?, city = ?, district = ?, state = ?, pincode = ? WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, addressBook.getUserId());
			ps.setString(2, addressBook.getName());
			ps.setString(3, addressBook.getEmail());
			ps.setString(4, addressBook.getPhoneNumber());
			ps.setString(5, addressBook.getDoorNo());
			ps.setString(6, addressBook.getStreetName());
			ps.setString(7, addressBook.getSubLocality());
			ps.setString(8, addressBook.getCity());
			ps.setString(9, addressBook.getDistrict());
			ps.setString(10, addressBook.getState());
			ps.setInt(11, addressBook.getPincode());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}
	
	/**
	 * 
	 * @param addressId
	 * @throws DAOException
	 */
	public void setStausFalse(int addressId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			String query = "UPDATE address_book SET status = 0 WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, addressId);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}
	
	/**
	 * 
	 * @param addressId
	 * @throws DAOException
	 */
	public void setStausTrue(int addressId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			String query = "UPDATE address_book SET status = 1 WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, addressId);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}
	
	
	/**
	 * 
	 * @param addressId
	 * @throws DAOException
	 */
	public void setAsDefaultTrue(int addressId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			String query = "UPDATE address_book SET set_as_default = 1 WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, addressId);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}
	
	/**
	 * 
	 * @param addressId
	 * @throws DAOException
	 */
	public void setAsDefaultFalse(int addressId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			String query = "UPDATE address_book SET set_as_default = 0 WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, addressId);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}
	
	/**
	 * 
	 * @param addressId
	 * @throws DAOException
	 */
	public void setSelectedTrue(int addressId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			String query = "UPDATE address_book SET selected = 1 WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, addressId);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}
	
	
	/**
	 * 
	 * @param addressId
	 * @throws DAOException
	 */
	public void setSelectedFalse(int addressId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			String query = "UPDATE address_book SET selected = 0 WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, addressId);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	
	
	/**
	 * 
	 * @param addressBook
	 * @return
	 * @throws DAOException
	 */
	public AddressBook isAddressAlreadyExists(AddressBook addressBook) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		AddressBook addressBook1 = null;
		
		try {
			String query = "SELECT id,user_id, name, email, phone_number, door_no, street_name, sub_locality, city, district, state,"
					+ "pincode,status,set_as_default,selected FROM address_book WHERE user_id = ? AND name = ? AND email=? AND phone_number = ? AND door_no = ? AND street_name = ? AND "
					+ "sub_locality = ? AND city = ? AND district = ? AND state=? AND pincode = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, addressBook.getUserId());
			ps.setString(2, addressBook.getName().trim());
			ps.setString(3, addressBook.getEmail().trim());
			ps.setString(4, addressBook.getPhoneNumber().trim());
			ps.setString(5, addressBook.getDoorNo().trim());
			ps.setString(6, addressBook.getStreetName().trim());
			ps.setString(7, addressBook.getSubLocality().trim());
			ps.setString(8, addressBook.getCity().trim());
			ps.setString(9, addressBook.getDistrict().trim());
			ps.setString(10, addressBook.getState().trim());
			ps.setInt(11, addressBook.getPincode());
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				addressBook1 = new AddressBook();
				addressBook1.setId(rs.getInt("id"));
				addressBook1.setUserId(rs.getInt("user_id"));
				addressBook1.setName(rs.getString("name"));
				addressBook1.setEmail(rs.getString("email"));
				addressBook1.setPhoneNumber(rs.getString("phone_number"));
				addressBook1.setDoorNo(rs.getString("door_no"));
				addressBook1.setStreetName(rs.getString("street_name"));
				addressBook1.setSubLocality(rs.getString("sub_locality"));
				addressBook1.setCity(rs.getString("city"));
				addressBook1.setDistrict(rs.getString("district"));
				addressBook1.setState(rs.getString("state"));
				addressBook1.setPincode(rs.getInt("pincode"));
				addressBook1.setStatus(rs.getInt("status"));
				addressBook1.setSetAsdefault(rs.getInt("set_as_default"));
				addressBook1.setSelected(rs.getInt("selected"));
			}
			
		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps,rs);
		}
		return addressBook1;
	}
	
	
	/**
	 * 
	 * @param addressId
	 * @return
	 * @throws DAOException
	 */
	public AddressBook findAddressByAddressId(int addressId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		AddressBook addressBook = null;
		
		try {
			String query = "SELECT id,user_id, name, email, phone_number, door_no, street_name, sub_locality, city, district, state,"
					+ "pincode, status, set_as_default, selected FROM address_book WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, addressId);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				addressBook = new AddressBook();
				addressBook.setId(rs.getInt("id"));
				addressBook.setUserId(rs.getInt("user_id"));
				addressBook.setName(rs.getString("name"));
				addressBook.setEmail(rs.getString("email"));
				addressBook.setPhoneNumber(rs.getString("phone_number"));
				addressBook.setDoorNo(rs.getString("door_no"));
				addressBook.setStreetName(rs.getString("street_name"));
				addressBook.setSubLocality(rs.getString("sub_locality"));
				addressBook.setCity(rs.getString("city"));
				addressBook.setDistrict(rs.getString("district"));
				addressBook.setState(rs.getString("state"));
				addressBook.setPincode(rs.getInt("pincode"));
				addressBook.setStatus(rs.getInt("status"));
				addressBook.setSetAsdefault(rs.getInt("set_as_default"));
				addressBook.setSelected(rs.getInt("selected"));
			}
			
		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps,rs);
		}
		return addressBook;
	}
	
	
	/**
	 * 
	 * @param userId
	 * @return
	 * @throws DAOException 
	 */
	public List<AddressBook> findAllAddressesByUserId(int userId) throws DAOException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<AddressBook> addressList = new ArrayList<>();
		
		try {
			String query = "SELECT id,user_id, name, email, phone_number, door_no, street_name, sub_locality, city, district, state,"
					+ "pincode, status, set_as_default, selected FROM address_book WHERE user_id = ? AND status = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				AddressBook addressBook = new AddressBook();
				addressBook.setId(rs.getInt("id"));
				addressBook.setUserId(rs.getInt("user_id"));
				addressBook.setName(rs.getString("name"));
				addressBook.setEmail(rs.getString("email"));
				addressBook.setPhoneNumber(rs.getString("phone_number"));
				addressBook.setDoorNo(rs.getString("door_no"));
				addressBook.setStreetName(rs.getString("street_name"));
				addressBook.setSubLocality(rs.getString("sub_locality"));
				addressBook.setCity(rs.getString("city"));
				addressBook.setDistrict(rs.getString("district"));
				addressBook.setState(rs.getString("state"));
				addressBook.setPincode(rs.getInt("pincode"));
				addressBook.setStatus(rs.getInt("status"));
				addressBook.setSetAsdefault(rs.getInt("set_as_default"));
				addressBook.setSelected(rs.getInt("selected"));
				addressList.add(addressBook);
			}
			
		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps,rs);
		}
		return addressList;
	}
	
	
	/**
	 * 
	 * @param addressId
	 * @throws DAOException
	 */
	public void isAdressIdIsValid(int addressId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT id FROM address_book WHERE id = ? AND status = 1	OR selected = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, addressId);
			
			rs = ps.executeQuery();
			
			if(!rs.next()) {
				throw new DAOException("Invalid AddressId");
			}
			
		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps,rs);
		}
	}
	
	/**
	 * 
	 * @return
	 * @throws DAOException
	 */
	public AddressBook findDefaultAddressByUserId(int userId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		AddressBook addressBook = null;
		
		try {
			String query = "SELECT id,user_id, name, email, phone_number, door_no, street_name, sub_locality, city, district, state,"
					+"pincode, status, set_as_default, selected FROM address_book WHERE user_id = ? AND set_as_default = 1 AND status = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, userId);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				addressBook = new AddressBook();
				addressBook.setId(rs.getInt("id"));
				addressBook.setUserId(rs.getInt("user_id"));
				addressBook.setName(rs.getString("name"));
				addressBook.setEmail(rs.getString("email"));
				addressBook.setPhoneNumber(rs.getString("phone_number"));
				addressBook.setDoorNo(rs.getString("door_no"));
				addressBook.setStreetName(rs.getString("street_name"));
				addressBook.setSubLocality(rs.getString("sub_locality"));
				addressBook.setCity(rs.getString("city"));
				addressBook.setDistrict(rs.getString("district"));
				addressBook.setState(rs.getString("state"));
				addressBook.setPincode(rs.getInt("pincode"));
				addressBook.setStatus(rs.getInt("status"));
				addressBook.setSetAsdefault(rs.getInt("set_as_default"));
				addressBook.setSelected(rs.getInt("selected"));
			}
			
		} catch (SQLException e) {
			Logger.error(e);
			throw new DAOException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps,rs);
		}
		return addressBook;
		
	}
	
	
}



