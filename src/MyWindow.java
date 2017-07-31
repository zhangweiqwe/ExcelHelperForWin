import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.FontMetrics;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import jxl.write.WriteException;


public class MyWindow extends JFrame implements ActionListener{
	
	private String backups_derectory = Const.FOLDER;
	private String backups_name = Const.BACKUPS_FILE_NAME;
	
	private JButton writeJB,addJB;
	private TextField modelNoJF,colorJF,inTheMiddleJF,buttomJF,pathTF;
	private JLabel logJL;

	private  FileHelper fileHelper = FileHelper.getInstance();
	public List<ShoesSimple> shoesSimples = new ArrayList<>();
	
	
	private String getBackupsFilePath(){
		return backups_derectory+"/"+backups_name;
	}
	
	private File getBackupsFile(){
		return new File(getBackupsFilePath());
	}
	private File getMainDerectory(){
		return new File(backups_derectory);
	}
	private void initData(){
		  try {
	            List<ShoesSimple> listTemp = fileHelper.getData(getBackupsFile());
	            if(listTemp!=null){
	            	shoesSimples.addAll(listTemp);
	            	printf("从\t"+getBackupsFilePath()+"\t中"+"读取到"+shoesSimples.size()+"条记录");
	            }else{
	            	printf("未读取到记录");
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	            printf("读取记录失败");
	        }
	}
	
	public MyWindow(){
		
        setTitle("ExcelHelper");
        setBounds(100, 100, 350, 350);
        setLayout(new FlowLayout());
       
        
        
        
        
        JPanel pane10=new JPanel();  
        JLabel label0=new JLabel("款号");  
        modelNoJF=new TextField();  
        modelNoJF.setColumns(18);  
        pane10.add(label0);  
        pane10.add(modelNoJF);  
        
        
        
        JPanel pane11=new JPanel();
        JLabel label1=new JLabel("颜色");
        colorJF=new TextField();
        colorJF.setColumns(18);
        pane11.add(label1);
        pane11.add(colorJF);
        
        
        JPanel pane12=new JPanel();
        JLabel label2=new JLabel("内里");
        inTheMiddleJF=new TextField();
        inTheMiddleJF.setColumns(18);
        pane12.add(label2);
        pane12.add(inTheMiddleJF);
        
        
        JPanel pane13=new JPanel();
        JLabel label3=new JLabel("大底");
        buttomJF=new TextField();
        buttomJF.setColumns(18);
        pane13.add(label3);
        pane13.add(buttomJF);
        
        writeJB = new JButton("写入");
        addJB = new JButton("添加");
        JPanel pane15=new JPanel();
        pane15.add(writeJB);
        pane15.add(addJB);
        
        
        
        JPanel pane16=new JPanel();
        logJL=new JLabel("暂无日志输出");
        pane16.add(logJL);
       

        
        JPanel pane17=new JPanel();  
        JLabel label7=new JLabel("路径");  
        pathTF=new TextField();  
        pathTF.setColumns(18);  
        pathTF.setText(getMainDerectory().toString());
        pane17.add(label7);  
        pane17.add(pathTF);  
        
        add(pane17);
        
        add(pane10);
        add(pane11);
        add(pane12);
        add(pane13);
        
        
        add(pane15);
        
        add(pane16);
      
        // 设置窗口可视化
        setVisible(true);
        // 设置窗口关闭
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
       
				initData();
		        
		        writeJB.addActionListener(MyWindow.this);
		        addJB.addActionListener(MyWindow.this);
	
		        //setData();
        
	}
	
	private void setData(){
		modelNoJF.setText("ddd");
		colorJF.setText("ddd");
		inTheMiddleJF.setText("ddd");
		buttomJF.setText("ddd");
	}
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 // 判断最初发生Event事件的对象
		
    	
    	
		
        if (e.getSource() == writeJB) {
        	String tempPath =pathTF.getText().toString();
        	if(!tempPath.trim().equals("")){
        		backups_derectory = tempPath;
        	}
        	
        	
        	try {
				if(fileHelper.writeData(getBackupsFile(),shoesSimples)){
					if(ExcelHelper.createExcel(getMainDerectory(),shoesSimples)){
						printf("写入成功"+getBackupsFile()+"\t"+"共"+shoesSimples.size()+"条记录");
					}else{
						printf("写入Excel表格失败");
					}
				}else{
					printf("写入备份失败");
				}
				 
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				printf("写入失败");
			} catch (WriteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				printf("写入失败");
			}
         }
        else if (e.getSource() == addJB) {
        	ShoesSimple simple = getShoesSimple();
        	if(simple==null){
        		return;
        	}
        	if(isAlreadyExists(simple)){
        		printf("记录已存在"+simple.hashCode());
        		return;
        	}
        	if(shoesSimples.add(simple)){
        		printf("添加成功"+simple.hashCode());
        	}else{
        		printf("添加失败");
        	}
        	
        }
	}
	
	//获取输入的内容()
	private ShoesSimple getShoesSimple(){
		ShoesSimple simple = null;
		String s0 = modelNoJF.getText().toString();
		String s1 = colorJF.getText().toString();
		String s2 = inTheMiddleJF.getText().toString();
		String s3 = buttomJF.getText().toString();
		
		if(s0.trim().equals("")||s0.trim().equals("")||s0.trim().equals("")||s0.trim().equals("")){
			printf("输入不能为空");
			return null;
		}
		
		simple = new ShoesSimple(s0, s1, s2, s3);
		return simple;
	}

	public static void main(String[] args) {
		new MyWindow();
	}
	
	private void printf(String str){
		//logJL.setText(str);
		try {
			JlabelSetText(logJL, str);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void JlabelSetText(JLabel jLabel, String longString) 
	            throws InterruptedException {
	        StringBuilder builder = new StringBuilder("<html>");
	        char[] chars = longString.toCharArray();
	        FontMetrics fontMetrics = jLabel.getFontMetrics(jLabel.getFont());
	        int start = 0;
	        int len = 0;
	        while (start + len < longString.length()) {
	            while (true) {
	                len++;
	                if (start + len > longString.length())break;
	                if (fontMetrics.charsWidth(chars, start, len) 
	                        > 350) {
	                    break;
	                }
	            }
	            builder.append(chars, start, len-1).append("<br/>");
	            start = start + len - 1;
	            len = 0;
	        }
	        builder.append(chars, start, longString.length()-start);
	        builder.append("</html>");
	        jLabel.setText(builder.toString());
	    }
	private boolean isAlreadyExists(ShoesSimple simple){
		for(int i=0;i<shoesSimples.size();i++){
			if(shoesSimples.get(i).equals(simple)){
				return true;
			}
		}
		return false;
	}
	
	
	
}
