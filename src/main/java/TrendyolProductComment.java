import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TrendyolProductComment extends Thread{

    private Connection c;
    private Element link;
    private WebDriver driver;
    private Statement stmt;

    public TrendyolProductComment(Element link, Statement stmt, Connection c) {

        this.c = c;
        this.link=link;
        this.stmt=stmt;
        this.driver = new FirefoxDriver();
    }

    @Override
    public void run() {

        String linkSTR = "https://www.trendyol.com"+link.attr("href");

        String[] arrOfStr = linkSTR.split("\\?boutiqueId", 2);
        linkSTR=arrOfStr[0]+"/yorumlar";

        driver.get(linkSTR);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Document docComment = Jsoup.parse(driver.getPageSource());

        try {

            String saller = String.valueOf(docComment.getElementsByClass("product-brand"));
            saller = saller.replace("<span class=\"product-brand\">","").replace(" </span>","");

            String prName = String.valueOf(docComment.getElementsByClass("product-name"));
            prName = prName.replace("<span class=\"product-name\">","").replace("</span>","");

            Elements comments = docComment.select("div.pr-rnr-com").first().getElementsByClass("rnr-com-w");

            boolean flag = false;
            String sql = "";
            String id = "";

            for (Element comment : comments){

                int starCounter = 0;

                for(Element full : comment.getElementsByClass("ratings readonly").first().getElementsByClass("full")){

                    if (full.attr("style").toString().contains("width: 0px;")==true){
                        starCounter+=1;
                    }

                    if (full.attr("style").toString().contains("width: 0%;")==true){
                        starCounter+=1;
                    }

                }

                starCounter = 5-starCounter;

                /*
                if (starCounter==5 || starCounter==4){
                    continue;
                }

                else{
                    System.out.println(starCounter);
                }
                */

                if(flag==false) {

                    sql = "INSERT INTO TBL_Product (Product_Name,Product_Brand,Product_Link)" +
                            "VALUES ('" + prName + "', '" + saller + "', '" + arrOfStr[0] + "');";
                    stmt.executeUpdate(sql);
                    c.commit();

                    sql = "SELECT MAX(Product_Id) AS [id] FROM TBL_Product;";
                    ResultSet rs = stmt.executeQuery(sql);
                    id = rs.getString("id");

                    System.out.println(prName);

                    flag = true;

                }


                String commentSTR =  comment.select("p").toString();
                commentSTR = commentSTR.replace("<p>", "").replace("</p>","");

                sql = "INSERT INTO TBL_Comment (Product_Id,Comment_Content,Comment_Evaluation) " +
                        "VALUES ('"+id+"', '"+commentSTR+"', '"+String.valueOf(starCounter)+"');";

                stmt.executeUpdate(sql);
                c.commit();

            }

        } catch (Exception e){

        }

        driver.close();
    }

}
