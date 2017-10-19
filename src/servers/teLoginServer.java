package servers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.Handler;
import android.os.Message;

public class teLoginServer extends Thread {
	private String name;
	private String password;
	private String phone;
	private String touxiang;
	private String sex, age, shenfen;
	private Handler handler;
	private String url;

	public teLoginServer(String name, String password, String phone,
			String sex, String touxiang, String age, String shenfen,
			String url, Handler handler) {
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.sex = sex;
		this.touxiang = touxiang;
		this.age = age;
		this.sex = sex;
		this.shenfen = shenfen;
		this.url = url;
		this.handler = handler;
	}

	private void doget() {
		url = url + "?name=" + name + "&&password=" + password + "&&phone="
				+ phone + "&&sex=" + sex + "&&touxiang=" + touxiang;
		try {
			URL httpurl = new URL(url);
			try {
				HttpURLConnection connection = (HttpURLConnection) httpurl
						.openConnection();
				connection.setRequestMethod("GET");
				connection.setReadTimeout(5000);
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(connection.getInputStream()));
				String str;
				StringBuffer stringBuffer = new StringBuffer();
				while ((str = bufferedReader.readLine()) != null) {
					stringBuffer.append(str);
				}
				String jkkk = stringBuffer.toString();
				Message message = new Message();
				message.obj = jkkk;
				handler.sendMessage(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void dopost() {
		try {
			URL httpurl = new URL(url);
			try {
				HttpURLConnection connection = (HttpURLConnection) httpurl
						.openConnection();
				connection.setRequestMethod("POST");
				connection.setReadTimeout(5000);
				OutputStream outputStream = connection.getOutputStream();
				String content = "name=" + name + "&&password=" + password
						+ "&&phone=" + phone + "&&sex=" + sex + "&&touxiang="
						+ touxiang+"&&tsage="+age+"&&shenfen="+shenfen;
				outputStream.write(content.getBytes());
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(connection.getInputStream()));
				StringBuffer stringBuffer = new StringBuffer();
				String str;
				while ((str = bufferedReader.readLine()) != null) {
					stringBuffer.append(str);
				}
				String jkkk = stringBuffer.toString();
				Message message = new Message();
				message.obj = jkkk;
				handler.sendMessage(message);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		// doget();
		dopost();
	}
}
