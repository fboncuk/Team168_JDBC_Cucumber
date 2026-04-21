
  Feature: US1001 kullanici deposits tablosu amount testi

    Scenario: TC01 deposits tablosunda belirli amount degerlerine sahip kayit testi

      Given kullanici loantech database'e baglanir
      When "deposits" tablosundaki "amount" degerlerini sorgular
      Then 100$ ile 500$ arasinda 10 amount oldugunu test eder
      And database baglantisini kapatir