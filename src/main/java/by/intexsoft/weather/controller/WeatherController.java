package by.intexsoft.weather.controller;

import by.intexsoft.weather.dto.WeatherDto;
import by.intexsoft.weather.model.Weather;
import by.intexsoft.weather.service.WeatherService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    // http://localhost:8080/weather
    @PostMapping(value = "/weather")
    public ResponseEntity<?> save(@RequestBody Weather weather) {
        try{
            weatherService.save(weather);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //http://localhost:8080/weather
    //http://localhost:8080/weather?date=1986-01-01
    //http://localhost:8080/weather?date=1986-01-01&&city=Nashville
    //http://localhost:8080/weather?date=1986-01-01&&city=Nashville,London
    @GetMapping(value = "/weather")
    public ResponseEntity<List<WeatherDto>> findAll(@RequestParam(value = "date", required = false)LocalDate date,
                                                 @RequestParam(value = "cityList", required = false)List<String> cityList) {
        try{
            List<WeatherDto> weathers;
            if (cityList != null && !cityList.isEmpty()) {
                weathers = weatherService.findByCity(cityList);
                if(date != null) {
                    weathers.removeIf(x -> !(x.getDate().toString().equals(date.toString())));
                }
            } else if(date != null){
                weathers = weatherService.findByDate(date);
            } else {
                weathers = weatherService.findAll();
            }
            return weathers != null && !weathers.isEmpty()
                    ? new ResponseEntity<>(weathers, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //http://localhost:8080/weather/1
    @GetMapping(value = "/weather/{id}")
    public ResponseEntity<WeatherDto> findById(@PathVariable(name = "id") Integer id) {
        try{
            WeatherDto weatherDto = weatherService.findById(id);
            return new ResponseEntity<>(weatherDto, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
