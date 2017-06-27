package pinaki.xyz.imagesearch.data;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import pinaki.xyz.imagesearch.WikiImage;

/**
 * Created by pinaki on 6/26/17.
 */

public class DataManager {
    private static final String THUMBNAIL_QUERY_URL = "https://en.wikipedia.org/w/api" +
            ".php?action=query&prop=pageimages&" +
            "format=json&piprop=thumbnail&pithumbsize=96&pilimit=50&generator=prefixsearch&gpslimit=50&gpssearch=";
    private static final String SOURCE_IMAGE_QUERY_URL = "https://en.wikipedia.org/w/api.php?action=query&prop=" +
            "pageimages&format=json&piprop=original&pageids=";
    private final ObjectMapper mapper;
    private static DataManager SINGLETON;
    public static DataManager getInstance() {
        if (SINGLETON == null) {
            SINGLETON = new DataManager();
        }
        return SINGLETON;
    }
    private DataManager() {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    }
    /* package */ String get(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body() != null ? response.body().string() : null;
    }

    public List<WikiImage> queryThumbNails(String query) {
        List<WikiImage> wikiImages = new ArrayList<>();
        try {
            String s = get(THUMBNAIL_QUERY_URL + query);
            ImageSearchResult imgSearchResult = null;
            imgSearchResult = mapper.readValue(s, ImageSearchResult.class);
            for (Map.Entry<String, ImageSearchResult.Page> entry : imgSearchResult.query.pages.entrySet() ) {
                if (entry.getValue().thumbnail != null && entry.getValue().thumbnail.source != null) {
                    WikiImage wikiImage = new WikiImage(entry.getValue().thumbnail.source, entry.getValue().title,
                            entry.getValue().thumbnail.width, entry.getValue().thumbnail.height);
                    wikiImages.add(wikiImage);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wikiImages;
    }

    List<String> querySourceImageByID(String query) {
        List<String> thumbSrc = new ArrayList<>();
        try {
            String s = get(SOURCE_IMAGE_QUERY_URL + query);
            ImageSearchResult imgSearchResult = null;
            imgSearchResult = mapper.readValue(s, ImageSearchResult.class);
            for (Map.Entry<String, ImageSearchResult.Page> entry : imgSearchResult.query.pages.entrySet() ) {
                if (entry.getValue().original != null && entry.getValue().original.source != null) {
                    thumbSrc.add(entry.getValue().original.source);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return thumbSrc;
    }
}
