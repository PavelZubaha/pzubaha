package com.pzubaha.jsop;

import com.pzubaha.jsop.models.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;
/**
 * job4j.ru
 * Chapter_007. JDBC.
 * <p>
 * Contains solution of task 1731.
 * Parser vacancies of site sql.ru.
 * Class provide methods for working with JDBC.
 * Created 13.11.2018.
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class Parser implements Job {
    private static final Logger LOGGER = Logger.getLogger(Parser.class);
    private final SimpleDateFormat format = new SimpleDateFormat("d MMM yy, HH:mm", new Locale("ru", "RU"));
    private final String url = "http://www.sql.ru/forum/job-offers/";
    private final String propertyPath = "/app.properties";

    /**
     * Required method for implementation of Job interface to start some job.
     * @param var1 specified parameters for start.
     * @throws JobExecutionException can throw this exception.
     */
    public void execute(JobExecutionContext var1) throws JobExecutionException {
        LOGGER.info("Start application!");
        parse();
        LOGGER.info("Closing app");
    }

    /**
     * Method for parsing http://www.sql.ru/forum/job-offers/
     * Looks for properties(connection to db), init connection, add vacancies from date of previous added vacancy till now.
     */
    public void parse() {
        try (InputStream in = getClass().getResourceAsStream(propertyPath); UtilsDB dbUtils = new UtilsDB()) {
            Properties props = new Properties();
            LOGGER.debug("Try to load properties");
            props.load(in);
            LOGGER.debug("Try to init connection to db");
            dbUtils.initConection(props);
            dbUtils.addAll(getVacancies(dbUtils.getLastUpdate()));
        } catch (Exception e) {
            LOGGER.warn(e);
        }
    }

    /**
     * Get get vacancies from last update time.
     * @param lastUpdateBefore lust update date.
     * @return list of finded vacancies.
     * @throws IOException signal that something occurs with I/O channels.
     */
    private List<Vacancy> getVacancies(Timestamp lastUpdateBefore) throws IOException {
        Document doc;
        String desc;
        Timestamp date;
        String header;
        String href;
        int id;
        String lowerCHeader;
        List<Vacancy> vacancies = new ArrayList<>();
        boolean continueParsePages = true;
        for (int i = 1; continueParsePages; i++) {
            doc = Jsoup.connect(url + i).get();
            Elements e = doc.getElementsByClass("postslisttopic");
            for (Element el : e) {
                header = el.text();
                header = header.replaceAll("(?m)^[ \t]*\r?\n", "");
                lowerCHeader = header.toLowerCase();
                if (lowerCHeader.toLowerCase().contains("java") && !lowerCHeader.contains("javascript")) {
                    date = getTimestamp(el.parent().child(5).text());
                    if (date.compareTo(lastUpdateBefore) < 0) {
                        continueParsePages = false;
                        break;
                    } else {
                        href = el.child(0).getElementsByTag("a").attr("href");
                        id = Integer.parseInt(href.substring(25, href.indexOf('/', 25)));
                        desc = getDesc(href).replaceAll("(?m)^[ \t]*\r?\n", "");
                        vacancies.add(new Vacancy(
                                id,
                                header,
                                desc,
                                date
                        ));
                    }
                }
            }
        }
//        printVacancyList(vacancies);
        return vacancies;
    }

    /**
     * Print list of vacancies to stdout.
     * @param vacancies list of vacancies.
     */
    private void printVacancyList(List<Vacancy> vacancies) {
        vacancies.forEach(new Consumer<Vacancy>() {
            int counter = 1;
            @Override
            public void accept(Vacancy vacancy) {
                System.out.printf("%d. %s%n\t%s%n", counter++, vacancy.getHeader(), vacancy.getDesc());
            }
        });
    }

    /**
     * Reference to vacancy.
     * @param href given reference to vacancy.
     * @return description of vacancy as String.
     */
    private String getDesc(String href) {
        StringBuilder sb = new StringBuilder();
        String s;
        Document document = null;
        try {
            LOGGER.debug("try to get vacancy list");
            document = Jsoup.connect(href).get();
        } catch (IOException e) {
            LOGGER.warn(e);
        }
        Element msgBody;
        msgBody = Objects.requireNonNull(document).getElementsByClass("msgBody").get(1);
        msgBody.children().forEach(new Consumer<Element>() {
            @Override
            public void accept(Element element) {
                sb.append(String.format("%s%n", element.text()));
            }
        });
        return sb.toString();
    }

    /**
     * Gatting timestamp from string.
     * @param date given date.
     * @return timestam from string.
     */
    private Timestamp getTimestamp(String date) {
        LOGGER.debug("Get timestamp from String date");
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
                LOGGER.warn(e);
            }
        }
        return new Timestamp(calendar.getTimeInMillis());
    }
}
