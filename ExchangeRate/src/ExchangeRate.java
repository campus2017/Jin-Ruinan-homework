import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketTimeoutException;;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ExchangeRate {

    public static Document getDataByJsoup(String url) {
        Document doc2 = null;
        try {
            doc2 = Jsoup.connect(url).timeout(5000).post();
            String title = doc2.body().toString();
        } catch (SocketTimeoutException e) {
            System.out.println("Socket连接超时");
        } catch (IOException e){
            e.printStackTrace();
        }
        return doc2;
    }
    public static void getThirtyAvg() {
        double dol = 0;
        double eur = 0;
        double hon = 0;
        List<String> list = new ArrayList<String>();
        List<Double> dols = new ArrayList<Double>();
        List<Double> eurs = new ArrayList<Double>();
        List<Double> hons = new ArrayList<Double>();
        List<String> dates = new ArrayList<String>();
        Document document = ExchangeRate
                .getDataByJsoup("" +
                        //"http://wzdig.pbc.gov.cn:8080/dig/ui/search.action?ty=&w=false&f=&dr=true&p=1&sr=score+desc%2Cdatetime+desc&rp=&advtime=&advrange=&fq=&ext=&q=%E4%B8%AD%E5%9B%BD%E5%A4%96%E6%B1%87%E4%BA%A4%E6%98%93%E4%B8%AD%E5%BF%83%E5%8F%97%E6%9D%83%E5%85%AC%E5%B8%83%E4%BA%BA%E6%B0%91%E5%B8%81%E6%B1%87%E7%8E%87%E4%B8%AD%E9%97%B4%E4%BB%B7");
                        "http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/17105/index1.html");

        for (int i = 0; i < 20; i = i + 1) {
            String str = "http://www.pbc.gov.cn";
            String str2 = document.select("a[onclick=void(0)]").get(i).attr("href");
            str += str2;
            list.add(str);

        }

        for (int i = 0; i < list.size(); i++) {
            Document doc = ExchangeRate.getDataByJsoup(list.get(i));
            String temp = doc.select("p").get(0)
                    .html();
            String[] temparray = temp.split("，");
            String doltemp = temparray[1];
            String dateTemp = doltemp;
            doltemp = doltemp.split("：")[1];
            String engtemp = temparray[2];
            String hontemp = temparray[4];
            dol = dol
                    + Double.parseDouble(doltemp.substring(7, doltemp.length() - 1));
            dols.add(Double.parseDouble(doltemp.substring(7, doltemp.length() - 1)));
            eur = eur
                    + Double.parseDouble(engtemp.substring(7, engtemp.length() - 1));
            eurs.add(Double.parseDouble(engtemp.substring(7, engtemp.length() - 1)));
            hon = hon
                    + Double.parseDouble(hontemp.substring(7, hontemp.length() - 1));
            hons.add(Double.parseDouble(hontemp.substring(7, hontemp.length() - 1)));
            int index=0;
            System.out.println(dateTemp);
            for(int j=0;j<dateTemp.length()-1;j++){
//                System.out.println(dateTemp.substring(j,j+1));
                if(dateTemp.substring(j,j+1).equals("日")){
                    index = j;
                    break;
                }
            }
            dates.add(dateTemp.substring(0,index+1));
            System.out.println(dateTemp.substring(0,index+1));
        }
        DecimalFormat df = new DecimalFormat("######0.0000");
        dol = dol / list.size();
        eur = eur / list.size();
        hon = hon / list.size();
        System.out.println(df.format(dol));
        System.out.println(df.format(eur));
        System.out.println(df.format(hon));
        WritableWorkbook wwb = null;
        OutputStream os = null;
        try {
            String[] title = { "1美元对人民币", "1欧元元对人民币", "1港币对人民币" };
            String filePath = "E:/JXL.xls";
            File file = new File(filePath);
            file.createNewFile();
            os = new FileOutputStream(filePath);
            wwb = Workbook.createWorkbook(os);
            WritableSheet sheet = wwb.createSheet("sheet1", 0);
            Label label = new Label(0,0,"30天内RMB汇率中间价");
            sheet.addCell(label);
            for (int i = 0; i < title.length; i++) {
                label = new Label(i+1 , 1 , title[i]);
                sheet.addCell(label);
            }
            int size = dols.size();
            for(int i=0;i<size;i++){
                label = new Label ( 0, i+2 , dates.get(i));
                sheet.addCell(label);
                label = new Label ( 1, i+2 , df.format(dols.get(i)));
                sheet.addCell(label);
                label = new Label ( 2, i+2 , df.format(eurs.get(i)));
                sheet.addCell(label);
                label = new Label ( 3, i+2 , df.format(hons.get(i)));
                sheet.addCell(label);
            }
            label = new Label ( 0, size+2 , "汇率均值");
            sheet.addCell(label);
            label = new Label ( 1, size+2 , df.format(dol));
            sheet.addCell(label);
            label = new Label ( 2, size+2 , df.format(eur));
            sheet.addCell(label);
            label = new Label ( 3, size+2 , df.format(hon));
            sheet.addCell(label);
            wwb.write();
        } catch(FileNotFoundException e){
            System.out.println("文件没找到");
        }  catch (WriteException e) {
            System.out.println("输入异常");
        }
        catch (Exception exception) {
            exception.printStackTrace();
        } finally{
            if(wwb != null)
                try {
                    wwb.close();
                } catch (WriteException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            if(os != null)
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
    public static void main(String[] args) {
        ExchangeRate.getThirtyAvg();
    }
}