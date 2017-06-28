package pinaki.xyz.imagesearch;

/**
 * Created by pinaki on 6/27/17.
 * POJO model class to store the Image Data
 */
public class WikiData {
    private String title;
    private WikiImage thumbnail;
    private WikiImage original;
    public WikiImage getThumbnail() {
        return thumbnail;
    }
    public WikiImage getOriginal() {
        return original;
    }

    public String getTitle() {
        return  title;
    }

    public static final class WikiImage {
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
