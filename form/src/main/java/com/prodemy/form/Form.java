package com.prodemy.form;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class Form
 */
@WebServlet("/Form")
public class Form extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static final String url = "jdbc:postgresql://localhost/form";
	static final String user = "postgres";
	static final String password = "Baba3003";
	
	public static Connection connect() {
		Connection conn = null;
		try {
			DriverManager.registerDriver(new org.postgresql.Driver());
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Koneksi berhasil");
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nama = request.getParameter("nama");
		String nim = request.getParameter("nim");
		String alamat = request.getParameter("alamat");
		String tanggalLahir = request.getParameter("tanggalLahir");
		String prodi = request.getParameter("prodi");
		String fakultas = request.getParameter("fakultas");
		
		PrintWriter out = response.getWriter();
		
		Connection conn = null;
		try{
			String sql = "insert into \"Mahasiswa\" values('"
					+nim+"','"
					+nama+"','"
					+alamat+"','"
					+tanggalLahir+"','"
					+prodi+"','"
					+fakultas+"')";
			conn = connect();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			
			out.print("<html><body>");
			out.print("<h1>Data Terkirim</h1>");
			out.print("<h3>Nama : "+nama+"</h3>");
			out.print("<h3>NIM : "+nim+"</h3>");
			out.print("<h3>Alamat : "+alamat+"</h3>");
			out.print("<h3>Tanggal Lahir : "+tanggalLahir+"</h3>");
			out.print("<h3>Prodi : "+prodi+"</h3>");
			out.print("<h3>Fakultas : "+fakultas+"</h3>");
			out.print("</body></html>");
		}catch(SQLException e) {
			e.printStackTrace();
			
			out.print("<html><body>");
			out.print("<h1>Data Gagal Terkirim</h1>");
			out.print("</body></html>");
		}finally {
			try {
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
