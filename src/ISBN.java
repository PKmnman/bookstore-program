import java.util.ArrayList;
import java.util.Arrays;

/*************************************************************************************
 *  Program:     ISBN.java
 *  Date:        January 31, 2019
 *  Purpose:     $PROJECT_PURPOSE
 *  Author:      Gary Reeves
 *  Course:      Introduction to Computing I
 *  Assignment:  Bookstore Program
 *************************************************************************************/
public class ISBN {
	
	private int length;
	
	byte[] digits, prefix, group, publisher, title;
	byte check;
	
	public ISBN() {
		initDigits("0-0000-0000-0");
	}
	
	public ISBN(String iSBN){
		if(iSBN.length() == 10 || iSBN.length() == 13){
			initDigits(iSBN);
		}else{
			throw new IllegalArgumentException("Invalid ISBN");
		}
	}
	
	private void initDigits() {
		String isbn = this.toString();
		
		this.digits = new byte[isbn.length()];
		this.length = this.digits.length;
		
		int pos = 0;
		for (int i = 0; i < isbn.length(); i++){
			if(isbn.charAt(i) != '-'){
				digits[pos] = Byte.valueOf(isbn.charAt(i) + "");
				pos++;
			}
		}
	}
	
	private void initDigits(String iSBN) {
		
		ArrayList<byte[]> parts = new ArrayList<>();
		this.digits = new byte[10];
		this.length = this.digits.length;
		this.check = Byte.valueOf(iSBN.charAt(iSBN.length() - 1) + "");
		
		int pos = 0, last = 0;
		for (int i = 0; i < iSBN.length(); i++){
			if(iSBN.charAt(i) != '-'){
				digits[pos] = Byte.valueOf(iSBN.charAt(i) + "");
				pos++;
			}else{
				parts.add(Arrays.copyOfRange(digits, last, pos));
				last = pos;
			}
		}
		
		
		
		if(length == 10){
			this.prefix = null; this.group = parts.get(0);
			this.publisher = parts.get(1); this.title = parts.get(2);
		}else {
			this.prefix = parts.get(0); this.group = parts.get(1);
			this.publisher = parts.get(2); this.title = parts.get(3);
		}
	}
	
	public boolean checkISBN(final byte[] digits){
		int t = 0, s = 0;
		if(length == 10){
			for (int i = 0; i < 10; i++) {
				t += digits[i];
				s += t;
			}
			
			return (s % 11) == 0;
		}else if(length == 13){
			for (int i = 0; i < 13; i+=2) {
				t += digits[i];
			}
			for (int i = 1; i < 13; i+=2){
				t += digits[i] * 3;
			}
			
			s = 10 - (t%10);
			return s == digits[12];
		}else{
			return false;
		}
		
	}
	
	@Override
	public String toString() {
		StringBuilder isbn = new StringBuilder();
		if(prefix != null){
			ArrayList<byte[]> parts = new ArrayList<>();
			parts.add(prefix);
			parts.add(group);
			parts.add(publisher);
			parts.add(title);
			for(byte[] arr:parts){
				for (int i = 0; i < arr.length; i++) {
					isbn.append(arr[i]);
				}
				isbn.append("-");
			}
			isbn.append(check);
		}else{
			ArrayList<byte[]> parts = new ArrayList<>();
			parts.add(group);
			parts.add(publisher);
			parts.add(title);
			for(byte[] arr:parts){
				for (byte val:arr) {
					isbn.append(val + "");
				}
				isbn.append("-");
			}
			isbn.append(check);
		}
		
		return isbn.toString();
	}
	
}
