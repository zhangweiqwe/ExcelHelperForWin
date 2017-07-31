


import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * 
 * 艾佳鞋业样品(鞋子样品)
 * @author admin
 *
 */

public class ShoesSimple {

	
	private String modelNo;//款号
	private String color;//颜色
	private String inTheMiddle;//内里
	private String buttom;//大底

	public ShoesSimple( String modelNo, String color, String inTheMiddle, String buttom) {
		this.modelNo = modelNo;
		this.color = color;
		this.inTheMiddle = inTheMiddle;
		this.buttom = buttom;
	}

	

	public String getModelNo() {
		return modelNo;
	}
	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getInTheMiddle() {
		return inTheMiddle;
	}
	public void setInTheMiddle(String inTheMiddle) {
		this.inTheMiddle = inTheMiddle;
	}
	public String getButtom() {
		return buttom;
	}
	public void setButtom(String buttom) {
		this.buttom = buttom;
	}



	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		
		if(obj == null||!(obj instanceof ShoesSimple)){
			return super.equals(obj);
		}
		
		ShoesSimple simple = (ShoesSimple)obj;
		
		if(modelNo.equals(simple.modelNo)&&color.equals(simple.color)&&inTheMiddle.equals(simple.inTheMiddle)&&buttom.equals(simple.buttom)){
			return true;
		}
		
		return super.equals(obj);
	}

	
}
