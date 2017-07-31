



import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.UnderlineStyle;
import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class ExcelHelper {
	

	//横 为行column 
	private static final int SHOES_MAX_COLUMN_COUNT = 5;
	private static File initMainFolder(){
		File mainFolder = new File(Const.MAIN_FOLDER);
		if(!mainFolder.exists()){
			mainFolder.mkdirs();
		}
		return mainFolder;
	}

	public static void createExcel(List<ShoesSimple> shoesSimpleList) throws IOException, WriteException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日_HH时mm分ss秒SSS毫秒");
		File file = new File(initMainFolder().getAbsolutePath(),"Excel"+format.format(new Date())+".xls"  );
		createExcel(file,shoesSimpleList);

	}

	public static void createExcel(File file,List<ShoesSimple> shoesSimpleList) throws WriteException, IOException {

		WritableWorkbook workbook = Workbook.createWorkbook(file);
		WritableSheet sheet = workbook.createSheet("DemoExcel", 0);
		  
		//一些临时变量，用于写到excel中   
	
	
		  
		//预定义的一些字体和格式，同一个Excel中最好不要有太多格式   
		 WritableFont headerFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, 
		    false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLUE);    
		 WritableCellFormat headerFormat = new WritableCellFormat (headerFont);    
		 headerFormat.setAlignment(Alignment.CENTRE);
		   
		 WritableFont titleFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD,
		     false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.RED);    
		 WritableCellFormat titleFormat = new WritableCellFormat (titleFont);    
		   
		 WritableFont detFont = new WritableFont(WritableFont.ARIAL, 8, WritableFont.BOLD, 
		    false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);    
		 WritableCellFormat detFormat = new WritableCellFormat (detFont);    
		   
	 
		  
		 
		 int currentShoesColumnCount = 0;
		 int currentShoesRowCount = 0;
		 
		 
		 
	

		 
		 
		 for(int i=0;i<shoesSimpleList.size();i++){
			 
		
			 
			 ShoesSimple shoesSimple = shoesSimpleList.get(i);
			 
			 Label label = null;   
			
			
			   
			 //add Title   
			 int column;  //控制x轴
			 if(currentShoesColumnCount==0){
				 column = 0;
				 sheet.setColumnView(1, 20);  
			 }else{
				 column = (currentShoesColumnCount)*2;
				 sheet.setColumnView((currentShoesColumnCount)*2+1, 20);  
			 }
			 
			
			 
			
			 int row0; //控制y轴
			 if(currentShoesRowCount==0){
				 row0 = 2;
			 }else{
				 row0 = (currentShoesRowCount)*9+2;
			 }
			 
			 
			 
			
		
			 
			
			  
			 
			 
			 sheet.mergeCells(column, row0-2, column+1, row0-1); 
			 
			 //sheet.mergeCells(y00, x00, y01, x01); 
			 
			 //剩下的事情，就是用上面的内容和格式创建一些单元格，再加到sheet中   
			label=new Label(column, row0-2, "艾佳鞋业样品单", headerFormat);   
			 sheet.addCell(label); 
			 
			
	
			 label=new Label(column, row0, "款号：", titleFormat);   
			 sheet.addCell(label); 
			 label=new Label(column+1, row0, shoesSimple.getModelNo(), detFormat);   
			 sheet.addCell(label); 
			 row0++;
			 
			 label=new Label(column, row0, "颜色：", titleFormat);   
			 sheet.addCell(label);   
			 label=new Label(column+1, row0, shoesSimple.getColor(), detFormat);   
		     sheet.addCell(label);   
			 row0++;
			 
			 
			 label=new Label(column, row0, "内里：", titleFormat);   
			 sheet.addCell(label); 
			 label=new Label(column+1, row0, shoesSimple.getInTheMiddle(), detFormat);   
			 sheet.addCell(label); 
			 row0++;
			 
			 label=new Label(column, row0, "大底：", titleFormat);   
			 sheet.addCell(label); 
			 label=new Label(column+1, row0, shoesSimple.getButtom(), detFormat);   
		     sheet.addCell(label); 
			 row0++;
			 
			 
			   
		
			 
			 
			 
			 
			 if(currentShoesColumnCount>=SHOES_MAX_COLUMN_COUNT){
				 currentShoesColumnCount=0;
				 currentShoesRowCount++;
			 }else{
				 currentShoesColumnCount++;
			 }
			 
			
			
		 }
		
	
		   
		 workbook.write();   
		 workbook.close();   

	}
}
