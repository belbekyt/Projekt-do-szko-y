import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
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
            str = str.replace(" Deskorolka Kompletna", "");
            str = str.replace(" Deskorolka kompletna", "");
            str = str.replace(" Dla Dzieci", "");
            skateboardNamesCutted.add(str);
        }

        Elements allSkateboardPrices = document.select("span.gpn");
        List<String> skateboardPricesCutted = new ArrayList<String>();
        for(Element elem: allSkateboardPrices) {
            skateboardPricesCutted.add(elem.text());
        }

        List<Skateboard> skateboards = new ArrayList<Skateboard>();
        for(int i=0; i<skateboardPricesCutted.size()-1; i++){
            Skateboard skateboard = new Skateboard(skateboardNamesCutted.get(i), skateboardPricesCutted.get(i));
            skateboards.add(skateboard);
        }


        PrintWriter zapis = new PrintWriter("zestawienieDeskorolek.txt");
        zapis.println("Zestawienie deskorolek ze strony SkatePro");
        zapis.println(" ");

        for(int i=0; i<skateboards.size(); i++){
            zapis.println(i +". Nazwa: " + skateboards.get(i).getName() + ", Cena: " + skateboards.get(i).getPrice() + ";");
        }

        zapis.close();

        //USUN ID DESEK Z PRZECENY!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    }
}