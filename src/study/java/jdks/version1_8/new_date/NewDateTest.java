package study.java.jdks.version1_8.new_date;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class NewDateTest {
	public static void main(String[] args) {
		Clock clock =Clock.systemDefaultZone();
		System.out.println(clock.getZone().getId());
		long mills = clock.millis();
	    System.out.println(mills);
	    System.out.println(System.currentTimeMillis());
	    
	    Instant instant = clock.instant();
	    Date legacyDate =Date.from(instant);
	    System.out.println(legacyDate.getTime());
	    
	    System.out.println(ZoneId.getAvailableZoneIds());
	    ZoneId zone1 = ZoneId.of("Europe/Berlin");
	    ZoneId zone2 = ZoneId.of("Brazil/East");
	    ZoneId zone3 = ZoneId.of("Asia/Chungking");
	    System.out.println(zone1.getRules());
	    System.out.println(zone2.getRules());
	    System.out.println(zone3.getRules());

	    LocalTime now1 = LocalTime.now(zone1);
	    LocalTime now2 = LocalTime.now(zone2);
	    LocalTime now3 = LocalTime.now(zone3);
	    System.out.println(now1.toString());
	    System.out.println(now2.toString());
	    System.out.println(now3.toString());
	    
	}

}
