
  Feature: US1002 cron_schedules name testi
    @wip
    Scenario: TC02 tablodaki isimler beklenen degerler olmalidir
      Given kullanici loantech database'e baglanir
      When "cron_schedules" tablosundaki "name" degerlerini sorgular
      Then 1.kaydin name bilgisinin "5 Minutes" oldugunu test eder
      And 2.kaydin name bilgisinin "10 Minutes" oldugunu test eder
      And database baglantisini kapatir