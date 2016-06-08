package nl.fontys.util;

import java.io.Serializable;
import java.sql.Time;
import java.util.TimeZone;
import javax.jws.WebMethod;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
public class FontysTime implements Serializable{
	private long seconds;

	public FontysTime() {
		seconds = 0;
	}
        @WebMethod
	public static FontysTime now() {
		FontysTime someTime;
		someTime = FontysTime.fromSeconds(System.currentTimeMillis() / 1000);
		return someTime;
	}
        @WebMethod
	public static FontysTime fromSeconds(long iseconds) {
		FontysTime someTime;
		someTime = new FontysTime();
		someTime.seconds = iseconds;
		return someTime;
	}
        @WebMethod
	public FontysTime increment(long seconds) {
		this.seconds += seconds;
		return this;
	}
        @WebMethod
	public long asSeconds() {
		return (seconds);
	}
        @WebMethod
	public long subtractTime(FontysTime someTime) {
		return this.asSeconds() - someTime.asSeconds();
	}
        @WebMethod
	public String toString() {
		Time time;

		// de java Time class is in staat strings van tijd objecten
		// te maken echter vindt er ongewenste conversie plaats tov gmt
		// corrigeer hiervoor
		TimeZone current = TimeZone.getDefault();
		TimeZone tz = TimeZone.getTimeZone("GMT");
		TimeZone.setDefault(tz);

		time = new Time(this.asSeconds() * 1000);
		String timeString;
		timeString = time.toString();

		TimeZone.setDefault(current);
		return timeString;
	}
}
