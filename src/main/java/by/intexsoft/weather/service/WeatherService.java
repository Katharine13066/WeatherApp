package by.intexsoft.weather.service;

import by.intexsoft.weather.dto.WeatherDto;
import by.intexsoft.weather.model.Weather;
import java.time.LocalDate;
import java.util.List;

public interface WeatherService {

    WeatherDto save(Weather weather);

    WeatherDto findById(Integer id);

    List<WeatherDto> findByCity(List<String> cityList);

    List<WeatherDto> findByDate(LocalDate date);

    List<WeatherDto> findAll();
}
