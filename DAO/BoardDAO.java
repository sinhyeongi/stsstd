package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;



import DTO.BoardDTO;

public class BoardDAO {
	
	public Connection dbcon() throws Exception{
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/mydb";
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url, "root", "mysql");	

		return conn;
	}
	public BoardDTO read(int num)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String subject,content,name,password;

		String sql = "select * from board where num=?";
		

		
		try{
			conn = dbcon();
			
		
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			BoardDTO boardDTO = new BoardDTO();
			
			while(rs.next()){
				
				num = rs.getInt("num");
				subject = rs.getString("subject");
				content = rs.getString("content");
				name = rs.getString("name");
				password = rs.getString("password");
				
				boardDTO.setNum(num);
				boardDTO.setSubject(subject);
				boardDTO.setContent(content);
				boardDTO.setName(name);
				boardDTO.setPassword(password);
			} 
			return boardDTO;
			
			 
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			try{
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		return null;
	}
	public ArrayList<BoardDTO> list() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();

		String sql = "select * from board;";
		
		String num,subject,content,name;

		try{
			conn = dbcon();
			
		
			pstmt = conn.prepareStatement(sql);
					
			rs = pstmt.executeQuery(sql);
			
			
			while(rs.next()){
				BoardDTO boardDTO = new BoardDTO();
				num = rs.getString("num");
				subject = rs.getString("subject");
				content = rs.getString("content");
				name = rs.getString("name");
				
				boardDTO.setNum(Integer.parseInt(num));
				boardDTO.setSubject(subject);
				boardDTO.setContent(content);
				boardDTO.setName(name);
				
				list.add(boardDTO);
				
			} 
			return list;
		
			 
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			try{
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	
	
		return null;
	}
	public void delete(int i) {

		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "DELETE FROM board where num=?";

		try{

			conn = dbcon();
			
		
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, i);
			pstmt.execute();
			
			
			 
		} 
		catch(Exception e){
			e.printStackTrace();
		} 
		finally{
			try{
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} 
			catch(Exception e){
				e.printStackTrace();
			}
		}
	
	}
	public void update(BoardDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "UPDATE board SET subject=?, content=?,name=?,password=?,signdate=now() where num=?;";
		

		try{
	
			conn = dbcon();
			
			
			pstmt = conn.prepareStatement(sql);
					
			pstmt.setString(1, dto.getSubject());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getPassword());
			pstmt.setInt(5, dto.getNum());
			pstmt.execute();
			
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		} 
		finally{
			try{
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} 
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
	
	}
	public void post(BoardDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "INSERT INTO board(subject,name,content,password,signdate) values(?,?,?,?,now())";

		try{

			conn = dbcon();
			
		
			pstmt = conn.prepareStatement(sql);
			
			
			
			pstmt.setString(1, dto.getSubject());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getPassword());
		
			pstmt.execute();
				
	
			 
	
			 
			 
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			try{
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
