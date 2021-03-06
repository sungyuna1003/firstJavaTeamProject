package tib;



import java.io.IOException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class RssReadExam {

	// http://www.kma.go.kr/wid/queryDFS.jsp?gridx=59&gridy=125
	private String rssFeed = "http://www.kma.go.kr/wid/queryDFS.jsp?gridx=%s&gridy=%s"; // 주소
	String MAIN_TITLE = "해동용궁사";
	String sql = "select MAIN_TITLE, gridx, gridy from travel where uc_seq = ?";
	/**
	 * 테스트를 위한 메인메소드
	 */
	public static void main(String[] args) {
		RssReadExam r = new RssReadExam();
		r.getTownForecast("97", "74");
	}

	/**
	 * 해당 좌표의 날씨 정보를 반환.
	 */
	public List<Map<String, String>> getTownForecast(String x, String y) {

		List<Map<String, String>> result = new ArrayList<Map<String, String>>();

		try {

			SAXBuilder parser = new SAXBuilder();
			parser.setIgnoringElementContentWhitespace(true);

			// URL url = new URL(rssFeed);
			// InputSource is = new InputSource(url.openStream());
			// Document doc = parser.build(is);

			String url = String.format(rssFeed, x, y);
			Document doc = parser.build(url);
			Element root = doc.getRootElement();

			Element channel = root.getChild("body");
			List<Element> list = channel.getChildren("data");

			// for (int i=0; i<1; i++) {

			Element el = (Element) list.get(0);
			Map<String, String> data = new LinkedHashMap<String, String>();
			/*
			 * data.put("seq", el.getAttributeValue("seq")); //48시간중 몇번째 인지 가르킴
			 * data.put("hour",el.getChildTextTrim("hour") ); //동네예보 3시간 단위
			 * data.put("day",el.getChildTextTrim("day") ); //1번째날 (0: 오늘/1: 내일/2: 모레)
			 * data.put("temp",el.getChildTextTrim("temp") ); //현재 시간온도
			 * data.put("tmx",el.getChildTextTrim("tmx") ); //최고 온도
			 * data.put("tmn",el.getChildTextTrim("tmn") ); //최저 온도
			 * data.put("sky",el.getChildTextTrim("sky") ); //하늘 상태코드 (1: 맑음, 2: 구름조금,
			 * 3:구름많음, 4:흐림) 
			 * data.put("pty",el.getChildTextTrim("pty") ); //강수 상태코드 (없음 : 0, 비 : 1, 비/눈 : 2, 눈 : 3)
			 * data.put("wfkor",el.getChildTextTrim("wfKor") ); //날씨 한국어 
			 *data.put("wfEn",el.getChildTextTrim("wfEn") ); //날씨 영어
			 * data.put("pop",el.getChildTextTrim("pop") ); //강수 확률%
			 * data.put("r12",el.getChildTextTrim("r12") ); //12시간 예상 강수량
			 * data.put("s12",el.getChildTextTrim("s12") ); //12시간 예상 적설량
			 * data.put("ws",el.getChildTextTrim("ws") ); //풍속(m/s)
			 * data.put("wd",el.getChildTextTrim("wd") ); //풍향 (0~7:북, 북동, 동, 남동, 남, 남서, 서,
			 * 북서) data.put("wdKor",el.getChildTextTrim("wdKor") ); //풍향 한국어
			 * data.put("wdEn",el.getChildTextTrim("wdEn") ); //풍향 영어
			 * data.put("reh",el.getChildTextTrim("reh") ); //습도 %
			 * data.put("r06",el.getChildTextTrim("r06") ); //6시간 예상 강수량
			 * data.put("s06",el.getChildTextTrim("s06") ); //6시간 예상 적설량
			 */
			System.out.println("여행정보 : " + MAIN_TITLE);
			System.out.println("현재기온 : " + el.getChildTextTrim("temp"));
			System.out.println("현재날씨 : " +  el.getChildTextTrim("wfKor"));
			System.out.println("하늘 상태 : " +  el.getChildTextTrim("sky"));
			System.out.println("강수상태 : " + el.getChildText("pty"));
			
			String sky = el.getChildTextTrim("sky");
			if (sky.equals("1")) {
                System.out.println("tib/sunshien.png");
			} else if(sky.equals("2")) {
				System.out.println("tib/sunny_cloudy1.png");
			}else if(sky.equals("3")) {
				System.out.println("tib/sunny_cloudy2.png");
			}else if(sky.equals("")) {
				System.out.println("tib/over_cloudy.png");
			}
           
			String pty = el.getChildTextTrim("pty");
			if (pty.equals("0")) {
                System.out.println("tib/no_rain.png");
			} else if(pty.equals("1")) {
				System.out.println("tib/rain.png");
			}else if(pty.equals("2")) {
				System.out.println("tib/rain_snow.png");
			}else if(pty.equals("3")) {
				System.out.println("tib/snow.png");
			}

          
			
			//System.out.println(data);
			result.add(data);
			// }

		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}
}