package com.pzubaha.jsop;

import com.pzubaha.jsop.models.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;

public class Parser {
    private final SimpleDateFormat format = new SimpleDateFormat("d MMM yy, HH:mm", new Locale("ru", "RU"));


    public static void main(String[] args) {
        Parser parser = new Parser();
        Document doc;
        String url = "http://www.sql.ru/forum/job-offers/";
        String desc;
        String date;
        String header;
        String href;
        String lowercHeader;
        List<Vacancy> vacancies = new ArrayList<>();
        for (int i = 1; i < 2; i++) {
            try {
                doc = Jsoup.connect(url + i).get();
                Elements e = doc.getElementsByClass("postslisttopic");
                for (Element el : e) {
                    header = el.text();
                    header = header.replaceAll("(?m)^[ \t]*\r?\n", "");
                    lowercHeader = header.toLowerCase();
                    if (lowercHeader.toLowerCase().contains("java") && !lowercHeader.contains("javascript")) {
                        href = el.child(0).getElementsByTag("a").attr("href");
                        date = el.parent().child(5).text();
                        desc = parser.getDesc(href).replaceAll("(?m)^[ \t]*\r?\n", "");

                        vacancies.add(new Vacancy(
                                header,
                                desc,
                                parser.getTimestamp(date),
                                href
                        ));
                    }
                }
                parser.printVacancyList(vacancies);
                DBUtils dbUtils = new DBUtils();
                Properties props = new Properties();
                String propertyPath = "/db.properties";
                try (InputStream in = parser.getClass().getResourceAsStream(propertyPath)) {
                    props.load(in);
                    dbUtils.initConection(props);
                    dbUtils.addAll(vacancies);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void printVacancyList(List<Vacancy> vacancies) {
        vacancies.forEach(new Consumer<Vacancy>() {
            int counter = 1;
            @Override
            public void accept(Vacancy vacancy) {
                System.out.printf("%d. %s%n\t%s%n", counter++, vacancy.getHeader(), vacancy.getDesc());
            }
        });
    }

    private String getDesc(String href) throws IOException {
        StringBuilder sb = new StringBuilder();
        String s;
        Document document = Jsoup.connect(href).get();
        Element msgBody;
        msgBody = document.getElementsByClass("msgBody").get(1);
        msgBody.children().forEach(new Consumer<Element>() {
            @Override
            public void accept(Element element) {
                sb.append(String.format("%s%n", element.text()));
            }
        });
        return sb.toString();
    }


    public String parseFor(String url) {
        String r = "";
        try {
            Document doc = Jsoup.connect(url).get();
            doc.getElementsByClass("forumTable").first().children().first().children();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return r;
    }

    public Timestamp getTimestamp(String date) {
        Calendar calendar = Calendar.getInstance();
        if (date.contains("сегодня")) {
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(date.substring(9, 11)));
            calendar.set(Calendar.MINUTE, Integer.parseInt(date.substring(12, 14)));
        } else if (date.contains("вчера")) {
            calendar.add(Calendar.DATE, -1);
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(date.substring(7, 9)));
            calendar.set(Calendar.MINUTE, Integer.parseInt(date.substring(10, 12)));
        } else {
            try {
                calendar.setTime(format.parse(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return new Timestamp(calendar.getTimeInMillis());
    }
}
