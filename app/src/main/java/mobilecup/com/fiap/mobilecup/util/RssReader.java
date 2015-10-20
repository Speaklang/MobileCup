package mobilecup.com.fiap.mobilecup.util;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import mobilecup.com.fiap.mobilecup.data.RssItem;

/**
 * Created by Lucas on 17/10/2015.
 */
public class RssReader {
    private String rssUrl;

    /**
     * Constructor
     *
     * @param rssUrl
     */
    public RssReader(String rssUrl) {
        this.rssUrl = rssUrl;
    }

    /**
     * Get RSS items.
     *
     * @return
     */
    public List<RssItem> getItems() throws Exception {
        // SAX parse RSS data
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();

        RssParseHandler handler = new RssParseHandler();

        saxParser.parse(rssUrl, handler);

        return handler.getItems();

    }
}