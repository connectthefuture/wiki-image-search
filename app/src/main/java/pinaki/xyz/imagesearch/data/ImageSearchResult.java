package pinaki.xyz.imagesearch.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

/**
 * Created by pinaki on 6/26/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageSearchResult {
    String batchcomplete;
    Query query;

    static final class Query {
        Map<String, Page> pages;
    }

    static final class Page {
        Integer pageid;
        Integer ns;
        String title;
        Integer index;
        ImageSrc thumbnail;
        ImageSrc original;
    }

    static final class ImageSrc {
        Integer width;
        Integer height;
        String source;
        String original; // this might be null
    }
}
