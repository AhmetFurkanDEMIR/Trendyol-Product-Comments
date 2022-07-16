import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.sql.*;
import java.io.IOException;
import java.util.List;

public class TrendyolProduct {

    public static void main(String args[]) throws IOException, InterruptedException, ClassNotFoundException, SQLException {

        String genders = "1,2,3";

        String brands = "38,37,271,101990,43,44,136,230,33,124,160,101439,436,189,257,146279,131,634,150,859";

        // Products
        String url = "https://www.trendyol.com/sr?wg="+genders+"&wb="+brands+"&wc=82&os=1&pi=";

        // firefox driver
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/driver/geckodriver");

        // sqlite
        Class.forName("org.sqlite.JDBC");
        Connection c = DriverManager.getConnection("jdbc:sqlite:TrendyolProduct.sqlite3");
        c.setAutoCommit(false);
        Statement stmt = c.createStatement();

        // Pages
        for(int i=1; i<209; i++) {

            Document doc = Jsoup.connect(url+String.valueOf(i)).get();
            System.out.println("Epoch : "+String.valueOf(i));

            List<Element> links = doc.select("div.p-card-chldrn-cntnr > a");

            for (Element link : links)
            {

                new TrendyolProductComment(link, stmt, c).start();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }

        }

        stmt.close();
        c.close();

    }

}
