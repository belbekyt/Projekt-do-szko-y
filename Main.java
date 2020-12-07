import java.io.IOException;
import java.util.*;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {
    public static void main(String[] args) throws IOException{
        Connection connect = Jsoup.connect("https://www.skatepro.com.pl/c84.htm");

        Document document = connect.get();

        Elements allSkateboardNames = document.select(".product_name_trim");
        List<String> skateboardNamesCutted = new ArrayList<String>();
        for(Element elem: allSkateboardNames) {
            String str = elem.text();
            str = str.replace("Deskorolka Kompletna", " ");
            str = str.replace("Deskorolka kompletna", " ");
            str = str.replace("Dla Dzieci", " ");
            skateboardNamesCutted.add(str);
        }

        Elements allSkateboardPrices = document.select(".gpb");
        List<String> skateboardPricesCutted = new ArrayList<String>();
        for(Element elem: allSkateboardPrices) {
            skateboardPricesCutted.add(elem.text());
        }

        System.out.println(skateboardNamesCutted.get(0));
        System.out.println(skateboardPricesCutted.get(0));
    }
}
