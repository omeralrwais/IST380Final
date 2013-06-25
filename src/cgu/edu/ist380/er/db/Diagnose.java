package cgu.edu.ist380.er.db;

import java.util.Date;

public class Diagnose {
	
    private int id;
	private int pid;
	private double bodyTemperature;
	private boolean ambulance;
	private boolean bleed;
	private boolean dizzy;
	private boolean pain;
	private boolean bone;
	private boolean cold;
	private boolean toothache;
	private boolean wound;
	private boolean speak;
	private boolean urine;
	private boolean breath;
	private int painScale;
	private Date visitTime;
	
	public Diagnose() {
		super();
		this.bodyTemperature = 0.0;
		this.ambulance = false;
		this.bleed = false;
		this.dizzy = false;
		this.pain = false;
		this.bone = false;
		this.cold = false;
		this.toothache = false;
		this.wound = false;
		this.speak = false;
		this.urine = false;
		this.breath = false;
		this.painScale = -1;
		this.visitTime= new Date(0,0,1);
		this.pid=0;
		this.id=0;
	}
	
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}
	
	public Date getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}

	public double getBodyTemperature() {
		return bodyTemperature;
	}
	public void setBodyTemperature(double bodyTemperature) {
		this.bodyTemperature = bodyTemperature;
	}
	public boolean isAmbulance() {
		return ambulance;
	}
	public void setAmbulance(boolean ambulance) {
		this.ambulance = ambulance;
	}
	public boolean isBleed() {
		return bleed;
	}
	public void setBleed(boolean bleed) {
		this.bleed = bleed;
	}
	public boolean isDizzy() {
		return dizzy;
	}
	public void setDizzy(boolean dizzy) {
		this.dizzy = dizzy;
	}
	public boolean isPain() {
		return pain;
	}
	public void setPain(boolean pain) {
		this.pain = pain;
	}
	public boolean isBone() {
		return bone;
	}
	public void setBone(boolean bone) {
		this.bone = bone;
	}
	public boolean isCold() {
		return cold;
	}
	public void setCold(boolean cold) {
		this.cold = cold;
	}
	public boolean isToothache() {
		return toothache;
	}
	public void setToothache(boolean toothache) {
		this.toothache = toothache;
	}
	public boolean isWound() {
		return wound;
	}
	public void setWound(boolean wound) {
		this.wound = wound;
	}
	public boolean isSpeak() {
		return speak;
	}
	public void setSpeak(boolean speak) {
		this.speak = speak;
	}
	public boolean isUrine() {
		return urine;
	}
	public void setUrine(boolean urine) {
		this.urine = urine;
	}
	public boolean isBreath() {
		return breath;
	}
	public void setBreath(boolean breath) {
		this.breath = breath;
	}
	public int getPainScale() {
		return painScale;
	}
	public void setPainScale(int painScale) {
		this.painScale = painScale;
	}
	
	
	

}
