package stepdefinitions;

import io.cucumber.java.en.*;
import manageQueries.LoantechQueries;
import org.testng.Assert;
import utilities.ReusableMethods;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoantechStepdefinitions {

    ResultSet resultSet;

    @Given("kullanici loantech database'e baglanir")
    public void kullanici_loantech_database_e_baglanir() {
        ReusableMethods.getMyConnection();
    }

    @When("{string} tablosundaki {string} degerlerini sorgular")
    public void tablosundaki_degerlerini_sorgular(String table, String sutun) {
        String query = LoantechQueries.tablodanSutunSorgusu(table,sutun);
        resultSet = ReusableMethods.executeSelectStatement(query);
    }

    @Then("{int}$ ile {int}$ arasinda {int} amount oldugunu test eder")
    public void $_ile_$_arasinda_amount_oldugunu_test_eder(Integer minTutar, Integer maxTutar, Integer kayitSayisi) throws SQLException {
        // 100$ ile 500$ arasinda 10 amount oldugunu test eder
        // result set bize bir tablo getirir
        // bizden istenen gorev her satirdaki amount miktarini kontrol edip
        // istenen aralikta olanlari saymamiz

        int sayac =0;

        while (resultSet.next()){
            int satirdakiAmountTutari = resultSet.getInt("amount");
            if (satirdakiAmountTutari >= minTutar && satirdakiAmountTutari <= maxTutar){
                sayac++;
            }

        }

        Assert.assertEquals(sayac,kayitSayisi);
    }

    @Then("database baglantisini kapatir")
    public void database_baglantisini_kapatir() {
        ReusableMethods.closeMyConnection();
    }

    @Then("{int}.kaydin name bilgisinin {string} oldugunu test eder")
    public void kaydin_name_bilgisinin_oldugunu_test_eder(Integer satirNo, String expectedName) throws SQLException {

        resultSet.absolute(satirNo);
        String actualName = resultSet.getString("name");

        Assert.assertEquals(actualName,expectedName);

    }




    @Then("email bilgisi {string} olan kisi sayisinin {int} den fazla oldugunu test eder")
    public void email_bilgisi_olan_kisi_sayisinin_den_fazla_oldugunu_test_eder(String arananEmailTuru, Integer minKayitSayisi) throws SQLException {

        int sayac = 0;

        while (resultSet.next()){
            String satirdakiEmail = resultSet.getString("email");
            if (satirdakiEmail.endsWith(arananEmailTuru)){
                sayac++;
            }
        }

        Assert.assertTrue(sayac >= minKayitSayisi);
    }

    @When("{string} tablosundaki tum sutunlari sorgular")
    public void tablosundaki_tum_sutunlari_sorgular(String table) {

        String query = LoantechQueries.tumTabloSorgusu(table);
        resultSet = ReusableMethods.executeSelectStatement(query);
    }

    @Then("id degeri {int} olan kaydin username bilgisinin {string} oldugunu test eder")
    public void id_degeri_olan_kaydin_username_bilgisinin_oldugunu_test_eder(Integer verilenIdDegeri, String expectedUsername) throws SQLException {
        // id degeri 1006 olan kaydin username bilgisinin "visual_user" oldugunu test eder

        while (resultSet.next()){

            int satirdakiIdNo = resultSet.getInt("id");
            String satirdakiUsername = resultSet.getString("username");

            if (satirdakiIdNo == verilenIdDegeri){
                Assert.assertEquals(satirdakiUsername,expectedUsername);
                break;
            }
        }

    }

}