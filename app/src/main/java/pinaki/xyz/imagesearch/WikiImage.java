package pinaki.xyz.imagesearch;

/**
 * Created by pinaki on 6/27/17.
 * POJO model class to store the Image Data
 */
public class WikiImage {
    String url;
    String title;
    int width;
    int height;
    public WikiImage(String url, String title, int width, int height) {
        this.url = url;
        this.title = title;
        this.width = width;
        this.height = height;
    }
}
