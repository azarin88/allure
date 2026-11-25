package ru.netology.allure.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateDate(int shift) {
        String date = LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return date;
    }

    public static String generateCity(Faker faker) {
        String[] cities = new String[]{
                "Абакан", "Анадырь", "Архангельск", "Астрахань", "Барнаул",
                "Белгород", "Биробиджан", "Благовещенск", "Брянск", "Великий Новгород",
                "Владикавказ", "Владимир", "Владивосток", "Волгоград", "Вологда",
                "Воронеж", "Грозный", "Горно-Алтайск", "Екатеринбург", "Иваново",
                "Ижевск", "Иркутск", "Йошкар-Ола", "Казань", "Калининград",
                "Калуга", "Кемерово", "Киров", "Кострома", "Краснодар",
                "Красноярск", "Курган", "Курск", "Кызыл", "Липецк",
                "Магадан", "Магас", "Майкоп", "Махачкала", "Москва",
                "Мурманск", "Нальчик", "Нарьян-Мар", "Нижний Новгород", "Новосибирск",
                "Омск", "Орёл", "Оренбург", "Пенза", "Пермь",
                "Петрозаводск", "Петропавловск-Камчатский", "Псков", "Ростов-на-Дону", "Рязань",
                "Салехард", "Самара", "Санкт-Петербург", "Саранск", "Саратов",
                "Севастополь", "Симферополь", "Смоленск", "Ставрополь", "Сыктывкар",
                "Тамбов", "Тверь", "Томск", "Тула", "Тюмень",
                "Улан-Удэ", "Ульяновск", "Уфа", "Хабаровск", "Ханты-Мансийск",
                "Чебоксары", "Челябинск", "Черкесск", "Чита", "Элиста",
                "Южно-Сахалинск", "Якутск", "Ярославль"
        };
        Random random = new Random();
        String city = cities[random.nextInt(cities.length)];
        return city;
    }

    public static String generateName(Faker faker) {
        String name = faker.name().lastName() + " " + faker.name().firstName();
        return name.replace("Ё", "Е").replace("ё", "е");
    }

    public static String generatePhone(Faker faker) {
        String phone = faker.numerify("+79#########");
        return phone;
    }

    public static class Registration {
        private static Faker faker;

        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            faker = new Faker(new Locale(locale));
            UserInfo user = new UserInfo(generateCity(faker), generateName(faker), generatePhone(faker));
            return user;
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}
