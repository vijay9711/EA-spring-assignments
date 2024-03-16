package properties.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
@Component
public class CountryConfig {
    @Value("#{'${countries}'.split(',')}")
    private List<String> countries;

    public CountryConfig() {
    }

    public CountryConfig(List<String> countries) {
        this.countries = countries;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryConfig that = (CountryConfig) o;
        return Objects.equals(countries, that.countries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countries);
    }

    @Override
    public String toString() {
        return "CountryConfig{" +
                "countries=" + countries +
                '}';
    }
}

