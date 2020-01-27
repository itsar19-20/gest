package utils;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {
	
	private Date dataCorrente;
	
	private Integer giorno;
	
	private Integer mese;
	
	private Integer anno;
	
	private String dataStringa;
	
	private static DateUtil instance;
	
	
	
	private DateUtil() {
		 DateFormat formatoData = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
		 Date data=new Date();
		 Calendar c = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY);
		 c.setTime(data);
		 this.dataCorrente=data;
		 this.dataStringa=formatoData.format( c.getTime()).toString();
		 this.giorno=Integer.parseInt(this.dataStringa.substring(0,2));
		 this.mese=Integer.parseInt(this.dataStringa.substring(3,5));
		 this.anno=Integer.parseInt(this.dataStringa.substring(6,8));
		
	}
	
	
	public static DateUtil getInstance() {
		if(instance==null) {
			instance=new DateUtil();
		}
		
		
		return instance;
	}
	
	
	
	
	public Date getDataCorrente() {
		return dataCorrente;
	}

	public void setDataCorrente(Date dataCorrente) {
		this.dataCorrente = dataCorrente;
	}

	public Integer getGiorno() {
		return giorno;
	}

	public void setGiorno(Integer giorno) {
		this.giorno = giorno;
	}

	public Integer getMese() {
		return mese;
	}

	public void setMese(Integer mese) {
		this.mese = mese;
	}

	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public String getDataStringa() {
		return dataStringa;
	}

	public void setDataStringa(String dataStringa) {
		this.dataStringa = dataStringa;
	}

	
	
	

}
