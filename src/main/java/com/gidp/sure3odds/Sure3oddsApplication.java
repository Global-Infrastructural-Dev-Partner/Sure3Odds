package com.gidp.sure3odds;

import com.gidp.sure3odds.repository.LeaguesRepository;
import com.gidp.sure3odds.repository.TeamsRepository;
import com.gidp.sure3odds.service.TeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication

@EnableSwagger2
public class Sure3oddsApplication {
    //    @Autowired
//    CountriesRepository countriesRepository;
    @Autowired
    LeaguesRepository leaguesRepository;
    @Autowired
    TeamsRepository teamsRepository;

    @Autowired
    TeamsService teamsService;

    public static void main(String[] args) {

        SpringApplication.run(Sure3oddsApplication.class, args);


//
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
    }

    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Sure3Odds API").license("").licenseUrl("").termsOfServiceUrl("")
                .version("1.0").build();
    }


    @Bean
    ApplicationRunner init() {

//        String url = "https://footystats.org/zimbabwe/premier-soccer-league";
//        try {
//            Document page = Jsoup.connect(url).userAgent("https://footystats.org/").get();
////            Elements teamElements = page.select(".cf.mt1e.rmt0.row .table-wrapper  .full-league-table.table-sort.col-sm-12.mobify-table .team.borderRightContent");//country 170
//            Elements teamElements = page.select(/**/".cf.mt1e .cf.section .table-wrapper .full-league-table.table-sort.col-sm-12.mobify-table .team.borderRightContent");//country 170
////            Elements teamElements = page.select(".cf.mt1e .cf.section .cf .table-wrapper .full-league-table.table-sort.col-sm-12.mobify-table .team.borderRightContent");//country 170
//            List<String> teams = new ArrayList<>();
//            for (Element e : teamElements) {
//                teams.add(e.text());
//            }
//
//            int count = 0;
//            for (String teamName : teams) {
//                count++;
//                System.out.println(count + " : " + teamName);
//                Teams newTeam = new Teams();
//                newTeam.setName(teamName);
//
//                Optional<Leagues> leagues = leaguesRepository.findById(801l);//do not forget to change this
//                if (!teamsService.checkTeamExist(leagues.get().getId(), teamName)) {
//                    newTeam.setLeagueID(leagues.get());
//                    newTeam.setCountryID(leagues.get().getCountryID());
//                    Teams saved_teams = teamsRepository.save(newTeam);
//                    System.out.println("saved_teams: " + saved_teams);
//                }
//            }
//        } catch (Exception ex) {
//
//        }


//        String url = "https://footystats.org/api";
//        try {
//
//            Document page = Jsoup.connect(url).userAgent("https://footystats.org/").get();
//            Elements countryElements = page.select(".leagues-offered .col-lg-8.col-sm-12.pb2e .league-data");
//            List<Element> countries = new ArrayList<>();
//            for (Element e : countryElements) {
//                countries.add(e);
//            }
//
//            for (Element country : countries) {
//                String countryTitleString  = country.children().select(".league-title").text();
//                String[] countryTitle = countryTitleString.split("\\s");
//                String title = countryTitle[0];
//                System.out.println(title);
//                Countries newcountry = new Countries();
//                newcountry.setName(title);
//                Countries saved_country = countriesRepository.save(newcountry);
//                System.out.println("saved_country :" + saved_country);
//
//                Elements leaguesElements =  country.children().select(".leagues li");
//                int counter = 0;
//                for (Element leagueEle : leaguesElements){
//                    counter++;
//                    String league = leagueEle.text();
//                    Leagues newleague = new Leagues();
//                    newleague.setName(league);
//                    newleague.setCountryID(saved_country);
//                    Leagues saved_league = leaguesRepository.save(newleague);
////                    System.out.println(counter + ": "+ leagueEle.text());
//                    System.out.println("saved_league :" + saved_country);
//
//                }
//            }
//        } catch (Exception ex) {
//
//        }
        return null;
    }

}
