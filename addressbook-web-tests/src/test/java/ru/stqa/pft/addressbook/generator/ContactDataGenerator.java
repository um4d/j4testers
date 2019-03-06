package ru.stqa.pft.addressbook.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContactDataGenerator {

    @Parameter(names = {"-c"}, description = "Contact count")
    public int count;

    @Parameter(names = {"-f"}, description = "Target file")
    public String file;

    @Parameter(names = {"-d"}, description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander.newBuilder().addObject(generator).build().parse(args);
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("csv")){
            saveAsCsv(contacts, new File(file));
        } else if (format.equals("json")) {
            saveAsJson(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format");
        }
    }

    private void saveAsJson(List<ContactData> groups, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(groups);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        try (Writer writer = new FileWriter(file)) {
            for (ContactData contact : contacts) {
                writer.write(String.format("%s;%s;%s\n", contact.getName(),
                        contact.getLastName(), contact.getGroups()));
            }
        }
    }

    private static String[] firstNames = {"Oliver", "Jake", "Noah", "James", "Jack", "Connor", "Liam", "John", "Harry", "Callum", "Mason", "Robert", "Jacob", "Jacob", "Jacob", "Michael", "Charlie", "Kyle", "William", "William", "Thomas", "Joe", "Ethan", "David", "George", "Reece", "Michael", "Richard", "Oscar", "Rhys", "Alexander", "Joseph", "James", "Charlie", "James", "Charles", "1William", "Damian", "Daniel", "Thomas"};
    private static String[] lastNames = {"Smith", "Murphy", "Smith", "Li", "Smith", "Smith", "Jones", "OKelly", "Johnson", "Smith", "Jones", "Wilson", "Williams", "OSullivan", "Williams", "Lam", "Williams", "Williams", "Brown", "Walsh", "Brown", "Martin", "Brown", "Brown", "Taylor", "Smith", "Jones", "Gelbero", "Wilson", "Taylor", "Davies", "OBrien", "Miller", "Roy", "Taylor", "Jones", "Wilson", "Byrne", "Davis", "Tremblay", "Morton", "Singh", "Evans", "ORyan", "Garcia", "Lee", "White", "Wang", "Thomas", "OConnor", "Rodriguez", "Gagnon", "Martin", "Anderson", "Roberts", "ONeill", "Wilson", "Wilson", "Anderson", "Li"};
    private static String[] cities = {"Азов", "Александров", "Алексин", "Альметьевск", "Анапа", "Ангарск", "Анжеро-Судженск", "Апатиты", "Арзамас", "Армавир", "Арсеньев", "Артем", "Архангельск", "Асбест", "Астрахань", "Ачинск", "Балаково", "Балахна", "Балашиха", "Балашов", "Барнаул", "Батайск", "Белгород", "Белебей", "Белово", "Белогорск (Амурская область)", "Белорецк", "Белореченск", "Бердск", "Березники", "Березовский (Свердловская область)", "Бийск", "Биробиджан", "Благовещенск (Амурская область)", "Бор", "Борисоглебск", "Боровичи", "Братск", "Брянск", "Бугульма", "Буденновск", "Бузулук", "Буйнакск", "Великие Луки", "Великий Новгород", "Верхняя Пышма", "Видное", "Владивосток", "Владикавказ", "Владимир", "Волгоград", "Волгодонск", "Волжск", "Волжский", "Вологда", "Вольск", "Воркута", "Воронеж", "Воскресенск", "Воткинск", "Всеволожск", "Выборг", "Выкса", "Вязьма", "Гатчина", "Геленджик", "Георгиевск", "Глазов", "Горно-Алтайск", "Грозный", "Губкин", "Гудермес", "Гуково", "Гусь-Хрустальный", "Дербент", "Дзержинск", "Димитровград", "Дмитров", "Долгопрудный", "Домодедово", "Донской", "Дубна", "Евпатория", "Егорьевск", "Ейск", "Екатеринбург", "Елабуга", "Елец", "Ессентуки", "Железногорск (Красноярский край)", "Железногорск (Курская область)", "Жигулевск", "Жуковский", "Заречный", "Зеленогорск", "Зеленодольск", "Златоуст", "Иваново", "Ивантеевка", "Ижевск", "Избербаш", "Иркутск", "Искитим", "Ишим", "Ишимбай", "Йошкар-Ола", "Казань", "Калининград", "Калуга", "Каменск-Уральский", "Каменск-Шахтинский", "Камышин", "Канск", "Каспийск", "Кемерово", "Керчь", "Кинешма", "Кириши", "Киров (Кировская область)", "Кирово-Чепецк", "Киселевск", "Кисловодск", "Клин", "Клинцы", "Ковров", "Когалым", "Коломна", "Комсомольск-на-Амуре", "Копейск", "Королев", "Кострома", "Котлас", "Красногорск", "Краснодар", "Краснокаменск", "Краснокамск", "Краснотурьинск", "Красноярск", "Кропоткин", "Крымск", "Кстово", "Кузнецк", "Кумертау", "Кунгур", "Курган", "Курск", "Кызыл", "Лабинск", "Лениногорск", "Ленинск-Кузнецкий", "Лесосибирск", "Липецк", "Лиски", "Лобня", "Лысьва", "Лыткарино", "Люберцы", "Магадан", "Магнитогорск", "Майкоп", "Махачкала", "Междуреченск", "Мелеуз", "Миасс", "Минеральные Воды", "Минусинск", "Михайловка", "Михайловск (Ставропольский край)", "Мичуринск", "Москва", "Мурманск", "Муром", "Мытищи", "Набережные Челны", "Назарово", "Назрань", "Нальчик", "Наро-Фоминск", "Находка", "Невинномысск", "Нерюнгри", "Нефтекамск", "Нефтеюганск", "Нижневартовск", "Нижнекамск", "Нижний Новгород", "Нижний Тагил", "Новоалтайск", "Новокузнецк", "Новокуйбышевск", "Новомосковск", "Новороссийск", "Новосибирск", "Новотроицк", "Новоуральск", "Новочебоксарск", "Новочеркасск", "Новошахтинск", "Новый Уренгой", "Ногинск", "Норильск", "Ноябрьск", "Нягань", "Обнинск", "Одинцово", "Озерск (Челябинская область)", "Октябрьский", "Омск", "Орел", "Оренбург", "Орехово-Зуево", "Орск", "Павлово", "Павловский Посад", "Пенза", "Первоуральск", "Пермь", "Петрозаводск", "Петропавловск-Камчатский", "Подольск", "Полевской", "Прокопьевск", "Прохладный", "Псков", "Пушкино", "Пятигорск", "Раменское", "Ревда", "Реутов", "Ржев", "Рославль", "Россошь", "Ростов-на-Дону", "Рубцовск", "Рыбинск", "Рязань", "Салават", "Сальск", "Самара", "Санкт-Петербург", "Саранск", "Сарапул", "Саратов", "Саров", "Свободный", "Севастополь", "Северодвинск", "Северск", "Сергиев Посад", "Серов", "Серпухов", "Сертолово", "Сибай", "Симферополь", "Славянск-на-Кубани", "Смоленск", "Соликамск", "Солнечногорск", "Сосновый Бор", "Сочи", "Ставрополь", "Старый Оскол", "Стерлитамак", "Ступино", "Сургут", "Сызрань", "Сыктывкар", "Таганрог", "Тамбов", "Тверь", "Тимашевск", "Тихвин", "Тихорецк", "Тобольск", "Тольятти", "Томск", "Троицк", "Туапсе", "Туймазы", "Тула", "Тюмень", "Узловая", "Улан-Удэ", "Ульяновск", "Урус-Мартан", "Усолье-Сибирское", "Уссурийск", "Усть-Илимск", "Уфа", "Ухта", "Феодосия", "Фрязино", "Хабаровск", "Ханты-Мансийск", "Хасавюрт", "Химки", "Чайковский", "Чапаевск", "Чебоксары", "Челябинск", "Черемхово", "Череповец", "Черкесск", "Черногорск", "Чехов", "Чистополь", "Чита", "Шадринск", "Шали", "Шахты", "Шуя", "Щекино", "Щелково", "Электросталь", "Элиста", "Энгельс", "Южно-Сахалинск", "Юрга", "Якутск", "Ялта", "Ярославль"};
    private static String[] streets = {"Ленина", "Победы", "Октябрьская", "Кирова", "Курская", "Летняя", "Лазо", "Левобережная", "Лобанова", "Лыковская", "Магистральная", "Новинки", "Никитская", "Орджоникидзе", "Осенняя", "Планерная", "Правды", "Ремизова", "Рябиновая", "Силикатная", "Сосновая"};
    private static String[] domens = {"mail.ru", "gmail.com", "yahoo.com", "yandex.ru", "amazon.com", "mycompany.int"};
    private static Random rnd = new Random();

    private static String getRndValue (String[] list) {
        return list[rnd.nextInt(list.length)];
    }
    private static String rndFirstName() {
        return getRndValue(firstNames);
    }
    private static String rndLasttName() {
        return getRndValue(lastNames);
    }
    private static String rndPhone() {
        return Integer.toString(rnd.nextInt(999999999));
    }
    private static String rndMail() {
        return String.format("%s@%s", rndFirstName(), getRndValue(domens));
    }
    private static String rndAddress() {
        return String.format("г. %s, ул. %s, д. %s, кв. %s", getRndValue(cities),
                getRndValue(streets), rnd.nextInt(100), rnd.nextInt(200));
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            ContactData contact = new ContactData().withName(rndFirstName())
                    .withLname(rndLasttName())
                    .withPhoneHome(rndPhone()).withPhoneMobile(rndPhone()).withPhoneWork(rndPhone())
                    .withEmail1(rndMail()).withEmail2(rndMail())
                    .withEmail3(rndMail()).withAddress(rndAddress());
            contacts.add(contact);
        }
        return contacts;
    }
}
