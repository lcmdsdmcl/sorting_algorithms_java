/*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/>.
*/

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import util.DBData;

public class Main {

	public static void main(String[] args) throws SQLException {		
		
		int rowNo; // number of rows = size
		// Random randomGenerator = new Random(); // for test purposes only
		long startTime, stopTime, elapsedTime; // for timing
		double bigO; // Big O
		
		Connection conn = null; // MYSQL Connection/J driver
		Statement stmt = null; 	// Statement instance
		ResultSet rs = null; 	// data returned from DB
		
		String SQL_TABLE = "SELECT * FROM rand_num";
		
		try {			
			conn = DBData.getConnection();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(SQL_TABLE);
			
			// get number of rows
			rs.last();
			rowNo = rs.getRow();
			rs.beforeFirst();
			System.out.println("Number of elements: " + rowNo);
			
			DefineArray array = new DefineArray(rowNo); 	// array holding DB data
			DefineArray arrayBackup = new DefineArray(rowNo); // backup data
			
			// copying DB data into ARRAY
			System.out.println("Copying from DB into Array...");
			while(rs.next()) {
				int number = rs.getInt("number");
				array.insertElement(number);
			}
			
			// print out the numbers
			// array.displayArray();
			
			// creating backup			
			backupArray(array, arrayBackup, rowNo);
			System.out.println();
						
			// Bubble Sort
			backupArray(arrayBackup, array, rowNo); //copying original data into array
			System.out.println("Bubble Sorting, O(N^2)..."); 
			startTime = System.currentTimeMillis(); // start counting
			array.bubbleSort();						// sorting
			stopTime = System.currentTimeMillis();  // stop counting
			elapsedTime = stopTime - startTime;		// calculating elapsed time	
			System.out.println("shellSort elapsedTime (ms) = " + elapsedTime);
			bigO = rowNo*rowNo;						// big O
			System.out.println("big O = " + bigO);
			System.out.println();
			
			//Selection Sort
			backupArray(arrayBackup, array, rowNo);
			System.out.println("Selection Sorting, O(N^2)...");
			startTime = System.currentTimeMillis();
			array.selectionSort();
			stopTime = System.currentTimeMillis();
			elapsedTime = stopTime - startTime;			
			System.out.println("shellSort elapsedTime (ms) = " + elapsedTime);
			bigO = rowNo*rowNo;;
			System.out.println("big O = " + bigO);
			System.out.println();
			
			// Insertion Sort
			backupArray(arrayBackup, array, rowNo);
			System.out.println("Insertion Sorting, O(N^2)...");
			startTime = System.currentTimeMillis();
			array.insertionSort();
			stopTime = System.currentTimeMillis();
			elapsedTime = stopTime - startTime;			
			System.out.println("shellSort elapsedTime (ms) = " + elapsedTime);
			bigO = rowNo*rowNo;
			System.out.println("big O = " + bigO);
			System.out.println();
			
			// Shell Sort
			backupArray(arrayBackup, array, rowNo);
			System.out.println("Shell Sorting, O(N*(logN)^2)...");
			startTime = System.currentTimeMillis();
			array.shellSort();
			stopTime = System.currentTimeMillis();
			elapsedTime = stopTime - startTime;			
			System.out.println("shellSort elapsedTime (ms) = " + elapsedTime);
			bigO = rowNo*(Math.log10(rowNo) * Math.log10(rowNo)) ;
			System.out.println("big O = " + bigO);
			System.out.println();
			
			// Quick Sort, should be used without recursion
			backupArray(arrayBackup, array, rowNo);
			System.out.println("Quick Sorting, O(N*logN)...");
			System.out.println();
			
			System.out.println("END");
						
		} catch (SQLException e) {
			System.err.println("getMessage: " + e.getMessage());			
			System.err.println("getSQSstate: " + e.getSQLState());
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}	
	}
	
	// copying data from array1 into array 2
	public static void backupArray(DefineArray array, DefineArray array2, int elemNo) {
		System.out.println("Creating backup...");
		array2.setCellNumber(0); // zero elements by default
		for (int i = 0; i < elemNo; i++) {
			long elem = array.getElement(i);
			array2.insertElement(elem);
		}
	}
}
