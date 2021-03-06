package pinaki.xyz.imagesearch.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pinaki.xyz.imagesearch.WikiData;

/**
 * Created by pinaki on 6/26/17.
 */
public class DataManagerTest {
    @Test
    public void queryTest() throws Exception {
        String url = "https://en.wikipedia.org/w/api.php?action=query&prop=pageimages&format=json&piprop=thumbnail&pithumbsize=96&pilimit=50&generator=prefixsearch&gpssearch=Cat&gpslimit=50";
        System.out.println(DataManager.getInstance().get(url));
    }

    @Test
    public void queryThumbNailsTest() throws Exception {
        DataManager dm = DataManager.getInstance();
        List<WikiData> l = dm.queryThumbNails("mars");
        System.out.println(l.get(0));

    }

    @Test
    public void querySourceImageTest() throws Exception {
        List<String> l = DataManager.getInstance().querySourceImageByID("5282");
        System.out.println(l);

    }

    @Test
    public void deserTest() {
        String str = "{\"batchcomplete\":\"\",\"query\":{\"pages\":{\"6678\":{\"pageid\":6678,\"ns\":0," +
                "\"title\":\"Cat\",\"index\":1,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/0/0b/Cat_poster_1.jpg/96px-Cat_poster_1.jpg\",\"width\":96,\"height\":63}},\"316133\":{\"pageid\":316133,\"ns\":0,\"title\":\"Cat Power\",\"index\":49,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/f/fe/Cat_Power_Way_Out_West_2013_%28cropped%29.jpg/67px-Cat_Power_Way_Out_West_2013_%28cropped%29.jpg\",\"width\":67,\"height\":96}},\"78747\":{\"pageid\":78747,\"ns\":0,\"title\":\"Cat Stevens\",\"index\":11,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/a/a7/Yusuf_Islam_BBC2_Folk_Awards.jpg/77px-Yusuf_Islam_BBC2_Folk_Awards.jpg\",\"width\":77,\"height\":96}},\"1519473\":{\"pageid\":1519473,\"ns\":0,\"title\":\"Cat anatomy\",\"index\":30,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/7/77/Meyer_Zeit-Vertreib_1_Tafel_015.jpg/60px-Meyer_Zeit-Vertreib_1_Tafel_015.jpg\",\"width\":60,\"height\":96}},\"5741167\":{\"pageid\":5741167,\"ns\":0,\"title\":\"Cat communication\",\"index\":36,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/6/63/Cats_Jip_and_Noah_communicating_28022008%28004%29.jpg/96px-Cats_Jip_and_Noah_communicating_28022008%28004%29.jpg\",\"width\":96,\"height\":72}},\"1067492\":{\"pageid\":1067492,\"ns\":0,\"title\":\"Catalan independence\",\"index\":43,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/1/16/La_Uni%C3%B3n_i_el_F%C3%A8nix_P1160234.JPG/96px-La_Uni%C3%B3n_i_el_F%C3%A8nix_P1160234.JPG\",\"width\":96,\"height\":72}},\"5282\":{\"pageid\":5282,\"ns\":0,\"title\":\"Catalan language\",\"index\":10,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/c/c6/Catalan_Countries.svg/96px-Catalan_Countries.svg.png\",\"width\":96,\"height\":73}},\"6822\":{\"pageid\":6822,\"ns\":0,\"title\":\"Catalonia\",\"index\":6,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/c/ce/Flag_of_Catalonia.svg/96px-Flag_of_Catalonia.svg.png\",\"width\":96,\"height\":64}},\"2377350\":{\"pageid\":2377350,\"ns\":0,\"title\":\"Catalonia national football team\",\"index\":45},\"5914\":{\"pageid\":5914,\"ns\":0,\"title\":\"Catalysis\",\"index\":15,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/3/30/Low_Temperature_Oxidation_Catalyst.jpeg/96px-Low_Temperature_Oxidation_Catalyst.jpeg\",\"width\":96,\"height\":79}},\"286802\":{\"pageid\":286802,\"ns\":0,\"title\":\"Catalytic converter\",\"index\":26,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/1/1d/DodgeCatCon.jpg/96px-DodgeCatCon.jpg\",\"width\":96,\"height\":90}},\"195952\":{\"pageid\":195952,\"ns\":0,\"title\":\"Catamaran\",\"index\":40,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/d/d1/Bladef16-1up.jpg/64px-Bladef16-1up.jpg\",\"width\":64,\"height\":96}},\"44776\":{\"pageid\":44776,\"ns\":0,\"title\":\"Catania\",\"index\":38,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/5/55/Catania-Etna-Sicilia-Italy-Castielli_CC0_HQ1.JPG/96px-Catania-Etna-Sicilia-Italy-Castielli_CC0_HQ1.JPG\",\"width\":96,\"height\":70}},\"88931\":{\"pageid\":88931,\"ns\":0,\"title\":\"Cataract\",\"index\":22,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/b/ba/Cataract_in_human_eye.png/96px-Cataract_in_human_eye.png\",\"width\":96,\"height\":70}},\"55469\":{\"pageid\":55469,\"ns\":0,\"title\":\"Catch-22\",\"index\":48},\"215873\":{\"pageid\":215873,\"ns\":0,\"title\":\"Catch Me If You Can\",\"index\":39},\"160126\":{\"pageid\":160126,\"ns\":0,\"title\":\"Cate Blanchett\",\"index\":33,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/4/43/Cate_Blanchett_SDCC_2014_%28cropped%29.jpg/74px-Cate_Blanchett_SDCC_2014_%28cropped%29.jpg\",\"width\":74,\"height\":96}},\"1077688\":{\"pageid\":1077688,\"ns\":0,\"title\":\"Catechin\",\"index\":34,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/4/44/%28%2B%29-Catechin.png/96px-%28%2B%29-Catechin.png\",\"width\":96,\"height\":52}},\"295855\":{\"pageid\":295855,\"ns\":0,\"title\":\"Catechism\",\"index\":42,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/8/82/Codex_Manesse_Schulmeister_von_Esslingen.jpg/68px-Codex_Manesse_Schulmeister_von_Esslingen.jpg\",\"width\":68,\"height\":96}},\"7163\":{\"pageid\":7163,\"ns\":0,\"title\":\"Catenary\",\"index\":47,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/0/04/Kette_Kettenkurve_Catenary_2008_PD.JPG/96px-Kette_Kettenkurve_Catenary_2008_PD.JPG\",\"width\":96,\"height\":72}},\"668125\":{\"pageid\":668125,\"ns\":0,\"title\":\"Caterpillar Inc.\",\"index\":18},\"47335\":{\"pageid\":47335,\"ns\":0,\"title\":\"Catfish\",\"index\":24,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/9/94/Ameiurus_melas_by_Duane_Raver.png/96px-Ameiurus_melas_by_Duane_Raver.png\",\"width\":96,\"height\":41}},\"7630\":{\"pageid\":7630,\"ns\":0,\"title\":\"Catharism\",\"index\":19,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/5/5f/Cathar_cross.svg/96px-Cathar_cross.svg.png\",\"width\":96,\"height\":96}},\"235459\":{\"pageid\":235459,\"ns\":0,\"title\":\"Cathay Dragon\",\"index\":37},\"151575\":{\"pageid\":151575,\"ns\":0,\"title\":\"Cathay Pacific\",\"index\":13},\"1677192\":{\"pageid\":1677192,\"ns\":0,\"title\":\"Catherine, Duchess of Cambridge\",\"index\":3,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/e/ea/Catherine_Elizabeth_Middleton_%28colorized%29.jpg/77px-Catherine_Elizabeth_Middleton_%28colorized%29.jpg\",\"width\":77,\"height\":96}},\"45379\":{\"pageid\":45379,\"ns\":0,\"title\":\"Catherine Deneuve\",\"index\":50,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Catherine_Deneuve_1995.jpg/62px-Catherine_Deneuve_1995.jpg\",\"width\":62,\"height\":96}},\"21193292\":{\"pageid\":21193292,\"ns\":0,\"title\":\"Catherine Howard\",\"index\":25,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/3/32/HowardCatherine02.jpeg/77px-HowardCatherine02.jpeg\",\"width\":77,\"height\":96}},\"47406\":{\"pageid\":47406,\"ns\":0,\"title\":\"Catherine Parr\",\"index\":21,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/4/49/Catherine_Parr_from_NPG.jpg/76px-Catherine_Parr_from_NPG.jpg\",\"width\":76,\"height\":96}},\"150996\":{\"pageid\":150996,\"ns\":0,\"title\":\"Catherine Zeta-Jones\",\"index\":17,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/d/df/Catherine_Zeta-Jones_VF_2012_Shankbone_2.jpg/77px-Catherine_Zeta-Jones_VF_2012_Shankbone_2.jpg\",\"width\":77,\"height\":96}},\"44154\":{\"pageid\":44154,\"ns\":0,\"title\":\"Catherine de' Medici\",\"index\":7,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/e/e8/Catherine-de-medici.jpg/71px-Catherine-de-medici.jpg\",\"width\":71,\"height\":96}},\"6942\":{\"pageid\":6942,\"ns\":0,\"title\":\"Catherine of Aragon\",\"index\":4,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/d/d6/Catherine_aragon.jpg/73px-Catherine_aragon.jpg\",\"width\":73,\"height\":96}},\"44240\":{\"pageid\":44240,\"ns\":0,\"title\":\"Catherine the Great\",\"index\":8,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/0/06/Profile_portrait_of_Catherine_II_by_Fedor_Rokotov_%281763%2C_Tretyakov_gallery%29.jpg/85px-Profile_portrait_of_Catherine_II_by_Fedor_Rokotov_%281763%2C_Tretyakov_gallery%29.jpg\",\"width\":85,\"height\":96}},\"6014\":{\"pageid\":6014,\"ns\":0,\"title\":\"Cathode ray tube\",\"index\":16,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/0/07/Oscilloscopic_tube.jpg/96px-Oscilloscopic_tube.jpg\",\"width\":96,\"height\":69}},\"606848\":{\"pageid\":606848,\"ns\":0,\"title\":\"Catholic Church\",\"index\":2,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/1/15/Petersdom_von_Engelsburg_gesehen.jpg/96px-Petersdom_von_Engelsburg_gesehen.jpg\",\"width\":96,\"height\":72}},\"5637031\":{\"pageid\":5637031,\"ns\":0,\"title\":\"Catholic Church in England and Wales\",\"index\":29,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/9/9d/Westminster_Cathedral_at_Dusk%2C_London%2C_UK_-_Diliff.jpg/85px-Westminster_Cathedral_at_Dusk%2C_London%2C_UK_-_Diliff.jpg\",\"width\":85,\"height\":96}},\"410868\":{\"pageid\":410868,\"ns\":0,\"title\":\"Catholic Church in the United States\",\"index\":23,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/8/80/Basilica_of_the_National_Shrine_of_the_Immaculate_Conception.jpg/96px-Basilica_of_the_National_Shrine_of_the_Immaculate_Conception.jpg\",\"width\":96,\"height\":57}},\"158934\":{\"pageid\":158934,\"ns\":0,\"title\":\"Catholic Church sexual abuse cases\",\"index\":14,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/CatholicChurchAbuseScandalGraffitiPortugal2011.JPG/96px-CatholicChurchAbuseScandalGraffitiPortugal2011.JPG\",\"width\":96,\"height\":75}},\"604198\":{\"pageid\":604198,\"ns\":0,\"title\":\"Catholic University of America\",\"index\":41,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/Logo_of_The_Catholic_University_of_America.svg/80px-Logo_of_The_Catholic_University_of_America.svg.png\",\"width\":80,\"height\":96}},\"16086178\":{\"pageid\":16086178,\"ns\":0,\"title\":\"Catholic theology\",\"index\":46,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/7/77/StPetersBasilicaEarlyMorning.jpg/96px-StPetersBasilicaEarlyMorning.jpg\",\"width\":96,\"height\":62}},\"4559718\":{\"pageid\":4559718,\"ns\":0,\"title\":\"Catholicism\",\"index\":12,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/d/da/Latin_Cross.jpg/71px-Latin_Cross.jpg\",\"width\":71,\"height\":96}},\"151432\":{\"pageid\":151432,\"ns\":0,\"title\":\"Cato Institute\",\"index\":32,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/2/2e/Cato_Institute_by_Matthew_Bisanz.JPG/96px-Cato_Institute_by_Matthew_Bisanz.JPG\",\"width\":96,\"height\":69}},\"75861\":{\"pageid\":75861,\"ns\":0,\"title\":\"Cato the Elder\",\"index\":28,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/8/8c/Patrizio_Torlonia.jpg/64px-Patrizio_Torlonia.jpg\",\"width\":64,\"height\":96}},\"215013\":{\"pageid\":215013,\"ns\":0,\"title\":\"Cats (musical)\",\"index\":20},\"468224\":{\"pageid\":468224,\"ns\":0,\"title\":\"Catskill Mountains\",\"index\":44,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/7/71/Central_Catskills_from_Twin_south_summit.jpg/96px-Central_Catskills_from_Twin_south_summit.jpg\",\"width\":96,\"height\":72}},\"26051975\":{\"pageid\":26051975,\"ns\":0,\"title\":\"Cattle\",\"index\":5,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/d/d4/CH_cow_2_cropped.jpg/96px-CH_cow_2_cropped.jpg\",\"width\":96,\"height\":96}},\"1059768\":{\"pageid\":1059768,\"ns\":0,\"title\":\"Cattle feeding\",\"index\":35,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/9/91/Maler_der_Grabkammer_des_Sennudem_001.jpg/96px-Maler_der_Grabkammer_des_Sennudem_001.jpg\",\"width\":96,\"height\":63}},\"312864\":{\"pageid\":312864,\"ns\":0,\"title\":\"Cattle in religion and mythology\",\"index\":31},\"38849149\":{\"pageid\":38849149,\"ns\":0,\"title\":\"Cattle slaughter in India\",\"index\":27,\"thumbnail\":{\"source\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/b/b0/Immolation_Sacrifice%2C_Mouh_Boli%2C_Durga_Puja.jpg/96px-Immolation_Sacrifice%2C_Mouh_Boli%2C_Durga_Puja.jpg\",\"width\":96,\"height\":69}},\"429657\":{\"pageid\":429657,\"ns\":0,\"title\":\"Catwoman\",\"index\":9}}}}";
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        ImageSearchResult imgSearchResult = null;
        try {
            imgSearchResult = mapper.readValue(str, ImageSearchResult.class);
            List<String> thumbSrc = new ArrayList<>();
            for (Map.Entry<String, ImageSearchResult.Page> entry : imgSearchResult.query.pages.entrySet() ) {
                if (entry.getValue().thumbnail != null && entry.getValue().thumbnail.source != null) {
                    thumbSrc.add(entry.getValue().thumbnail.source);
                }
            }
            System.out.println(thumbSrc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private static List<WikiData> getTempList() {
//        List<WikiData> imageList = new ArrayList<>();
//        String url = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/30/Low_Temperature_Oxidation_Catalyst" +
//                ".jpeg/96px-Low_Temperature_Oxidation_Catalyst.jpeg";
//        String title = "Title 1";
//        imageList.add(new WikiData(url, title, 1, 1));
//        url = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/07/Oscilloscopic_tube.jpg/96px-Oscilloscopic_tube.jpg";
//        title = "Title 2";
//        imageList.add(new WikiData(url, title, 1, 1));
//        url = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/ce/Flag_of_Catalonia.svg/96px-Flag_of_Catalonia.svg.png";
//        title = "Title 3";
//        imageList.add(new WikiData(url, title, 1, 1));
//        url = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d6/Catherine_aragon.jpg/73px-Catherine_aragon.jpg";
//        title = "Title 4";
//        imageList.add(new WikiData(url, title, 1, 1));
//        url = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/04/Kette_Kettenkurve_Catenary_2008_PD.JPG/96px-Kette_Kettenkurve_Catenary_2008_PD.JPG";
//        title = "Title 5";
//        imageList.add(new WikiData(url, title, 1, 1));
//        url = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e8/Catherine-de-medici.jpg/71px-Catherine-de-medici.jpg";
//        title = "Title 6";
//        imageList.add(new WikiData(url, title, 1, 1));
//        url = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/55/Catania-Etna-Sicilia-Italy-Castielli_CC0_HQ1.JPG/96px-Catania-Etna-Sicilia-Italy-Castielli_CC0_HQ1.JPG";
//        title = "Title 7";
//        imageList.add(new WikiData(url, title, 1, 1));
//        return imageList;
//    }

}