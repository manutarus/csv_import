package csv;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Commons {

	public String transCode() {
		SecureRandom rd = new SecureRandom();
		return new BigInteger(50, rd).toString(32);
	}

	public String uuid() {
		DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmm");
		Date date = new Date();
		Commons cm = new Commons();
		return ("'port-" + cm.transCode() + "-" + dateFormat.format(date) + "-"
				+ cm.transCode() + "'");
	}

	public String formatDate(String sDate) {
        Format formatter;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MM yyyy HH:mm:ss");
        try
        {
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return formatter.format(simpleDateFormat.parse(sDate));

        }
        catch (ParseException ex)
        {
            System.out.println("Exception "+ex);

            System.out.println(sDate);
            return null;
        }
	}

    public static void main(String[] args) {
        Commons cm = new Commons();

    }

	public String mysql() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return "-- phct patient table migration\n"
				+ "--\n"
				+ "-- ------------------------------------------------------\n"
				+ "-- Date       "
				+ dateFormat.format(date)
				+ "\n\n\n"
				+ "/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;\n"
				+ "/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;\n"
				+ "/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;\n"
				+ "/*!40101 SET NAMES utf8 */;\n\n"
				+ "/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;\n"
				+ "/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;\n"
				+ "/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;\n\n\n"
				+ "--\n" + "-- file generated from patient csv\n" + "--\n\n";

	}

}
