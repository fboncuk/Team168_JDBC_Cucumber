package manageQueries;

public class LoantechQueries {

    public static String tablodanSutunSorgusu(String tablo , String sorgulanacakSutun){
        return "SELECT "+ sorgulanacakSutun + " FROM " + tablo;
    }

    public static String tumTabloSorgusu(String tabloAdi){
        return "SELECT * FROM " + tabloAdi;
    }


}
