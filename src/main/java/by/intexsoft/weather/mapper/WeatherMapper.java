package by.intexsoft.weather.mapper;

import by.intexsoft.weather.dto.WeatherDto;
import by.intexsoft.weather.model.Weather;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WeatherMapper {

    WeatherDto toDto(Weather weather);

    List<WeatherDto> toDtos(List<Weather> weatherList);
}