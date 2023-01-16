package by.intexsoft.weather.repository;

import by.intexsoft.weather.model.Weather;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Integer> {

    List<Weather> findByDate(LocalDate date);

    List<Weather> findByCityIgnoreCaseIn(List<String> cityList);
}
