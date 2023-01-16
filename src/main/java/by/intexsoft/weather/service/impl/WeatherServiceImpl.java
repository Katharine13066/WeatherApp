package by.intexsoft.weather.service.impl;

import by.intexsoft.weather.dto.WeatherDto;
import by.intexsoft.weather.mapper.WeatherMapper;
import by.intexsoft.weather.model.Weather;
import by.intexsoft.weather.repository.WeatherRepository;
import by.intexsoft.weather.service.WeatherService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository weatherRepository;

    private final WeatherMapper weatherMapper;

    @Autowired
    public WeatherServiceImpl(WeatherRepository weatherRepository, WeatherMapper weatherMapper) {
        this.weatherRepository = weatherRepository;
        this.weatherMapper = weatherMapper;
    }

    @Override
    @Transactional
    public WeatherDto save(Weather weather) {
        return weatherMapper.toDto(weatherRepository.save(weather));
    }

    @Override
    public WeatherDto findById(Integer id) {
        return weatherMapper.toDto(weatherRepository.getById(id));
    }

    @Override
    public List<WeatherDto> findByCity(List<String> cityList) {
        return weatherMapper.toDtos(weatherRepository.findByCityIgnoreCaseIn(cityList));
    }

    @Override
    public List<WeatherDto> findByDate(LocalDate date) {
        return weatherMapper.toDtos(weatherRepository.findByDate(date));
    }

    @Override
    public List<WeatherDto> findAll() {
        List<Weather> result = weatherRepository.findAll();
        return weatherMapper.toDtos(result);
    }
}
