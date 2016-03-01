package global.vn.hn.emulator;

@SuppressWarnings("rawtypes")
public class Data implements Comparable {
	public String moment;
	public int type;
	public String content;
	public String content2;

	public Data() {
		// TODO Auto-generated constructor stub
	}

	public Data(String moment, int type, String content) {
		super();
		this.moment = moment;
		this.type = type;
		this.content = content;
	}

	public Data(String moment, int type, String content, String content2) {
		super();
		this.moment = moment;
		this.type = type;
		this.content = content;
		this.content2 = content2;
	}

	public String getMoment() {
		return moment;
	}

	public void setMoment(String moment) {
		this.moment = moment;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		Data obj = (Data) o;
		return this.getMoment().compareTo(obj.getMoment());
	}

	public String getContent2() {
		return content2;
	}

	public void setContent2(String content2) {
		this.content2 = content2;
	}

}