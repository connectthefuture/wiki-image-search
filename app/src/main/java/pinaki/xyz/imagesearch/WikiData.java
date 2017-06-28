package pinaki.xyz.imagesearch;

/**
 * Created by pinaki on 6/27/17.
 * POJO model class to store the Image Data
 */
public class WikiData {
    private String title;
    private WikiImage thumbnail;
    private WikiImage original;
    WikiImage getThumbnail() {
        return thumbnail;
    }
    WikiImage getOriginal() {
        return original;
    }

    String getTitle() {
        return  title;
    }

    static final class WikiImage {
        int width;
        int height;
        String url;
    }

    public WikiData(String thumbUrl, String sourceUrl, String title) {
        this.title = title;
        thumbnail = new WikiImage();
        thumbnail.url = thumbUrl;
        original = new WikiImage();
        original.url = sourceUrl;
    }
}
