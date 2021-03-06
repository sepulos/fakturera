package eu.programisci.relacje.service;

import eu.programisci.relacje.dto.RequestDTO;
import eu.programisci.relacje.dto.ResponseDTO;
import eu.programisci.relacje.enums.ECechaKlienta;
import eu.programisci.relacje.ob.*;
import eu.programisci.relacje.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RelacjeServiceImpl implements IRelacjeService {

    @Autowired
    private IKlientRepository klientRepository;
    @Autowired
    private IAdresRepository adresRepository;
    @Autowired
    private IEmailRepository emailRepository;
    @Autowired
    private ITelefonRepository telefonRepository;
    @Autowired
    private IKontoRepository kontoRepository;
    @Autowired
    private ISubskrypcjaRepository subskrypcjaRepository;

    @Override
    public void init() {
        KlientOB klient1 = uzupelnijKlienta1();
        KlientOB klient2 = uzupelnijKlienta2();

        AdresOB adresWspolny = new AdresOB();
        adresWspolny.setPelnyAdres("Nigdzie, Wspólna 5/10");
        adresWspolny = adresRepository.save(adresWspolny);

        klient1.getAdresy().add(adresWspolny);
        klient2.getAdresy().add(adresWspolny);

        klientRepository.save(klient1);
        klientRepository.save(klient2);
    }

    private KlientOB uzupelnijKlienta1() {
        KlientOB klient = new KlientOB();
        klient.setImie("Jan");
        klient.setNazwisko("Kowalski");

        KontoOB konto = new KontoOB();
        konto.setLogin("janek");
        konto.setHaslo("qwe123");
        klient.setKonto(konto);

        Set<AdresOB> adresy = new HashSet<>();
        AdresOB adres1 = new AdresOB();
        adres1.setPelnyAdres("Olsztyn, Słoneczna 54 D2/15");
        adresy.add(adres1);
        AdresOB adres2 = new AdresOB();
        adres2.setPelnyAdres("Olsztyn, Warszawska 1/1");
        adresy.add(adres2);
        AdresOB adres3 = new AdresOB();
        adres3.setPelnyAdres("Olsztyn, Dworcowa 1/1");
        adresy.add(adres3);
        AdresOB adres4 = new AdresOB();
        adres4.setPelnyAdres("Warszawa, Olsztyńska 1/1");
        adresy.add(adres4);
        AdresOB adres5 = new AdresOB();
        adres5.setPelnyAdres("Olsztynek, Malinowa 1/1");
        adresy.add(adres5);
        klient.setAdresy(adresy);

        Set<ECechaKlienta> cechy = new HashSet<>();
        cechy.add(ECechaKlienta.BLONDYN);
        cechy.add(ECechaKlienta.CHUDY);
        cechy.add(ECechaKlienta.WYSOKI);
        klient.setCechyKlienta(cechy);

        List<SubskrypcjaOB> subskrypcje = new ArrayList<>();
        SubskrypcjaOB subskrypcja1 = new SubskrypcjaOB();
        subskrypcja1.setNazwa("Pakiet Plus");
        subskrypcje.add(subskrypcja1);
        SubskrypcjaOB subskrypcja2 = new SubskrypcjaOB();
        subskrypcja2.setNazwa("Pakiet Mega");
        subskrypcje.add(subskrypcja2);
        SubskrypcjaOB subskrypcja3 = new SubskrypcjaOB();
        subskrypcja3.setNazwa("Pakiet Retro");
        subskrypcje.add(subskrypcja3);
        klient.setSubskrypcje(subskrypcje);

        Set<TelefonOB> telefony = new HashSet<>();
        TelefonOB telefon1 = new TelefonOB();
        telefon1.setNumer("555555555");
        telefon1 = telefonRepository.save(telefon1);
        telefony.add(telefon1);
        TelefonOB telefon2 = new TelefonOB();
        telefon2.setNumer("555333333");
        telefon2 = telefonRepository.save(telefon2);
        telefony.add(telefon2);
        klient.setTelefony(telefony);

        klient = klientRepository.save(klient);

        EmailOB email1 = new EmailOB();
        email1.setAdres("jkowalski@wp.pl");
        email1.setKlient(klient);
        emailRepository.save(email1);

        EmailOB email2 = new EmailOB();
        email2.setAdres("jan.kowalski@gmail.com");
        email2.setKlient(klient);
        emailRepository.save(email2);

        return klient;
    }

    private KlientOB uzupelnijKlienta2() {
        KlientOB klient = new KlientOB();
        klient.setImie("Anna");
        klient.setNazwisko("Nowak");

        KontoOB konto = new KontoOB();
        konto.setLogin("anka666");
        konto.setHaslo("anka666");
        klient.setKonto(konto);

        Set<AdresOB> adresy = new HashSet<>();
        AdresOB adres1 = new AdresOB();
        adres1.setPelnyAdres("Warszawa, Aleje Jerozolimskie 66/6");
        adresy.add(adres1);
        AdresOB adres2 = new AdresOB();
        adres2.setPelnyAdres("Kraków, Plac Ofiar Smogu 5");
        adresy.add(adres2);
        AdresOB adres3 = new AdresOB();
        adres3.setPelnyAdres("Olsztynek, Akacjowa 7");
        adresy.add(adres3);
        klient.setAdresy(adresy);

        Set<ECechaKlienta> cechy = new HashSet<>();
        cechy.add(ECechaKlienta.RUDY);
        cechy.add(ECechaKlienta.GRUBY);
        cechy.add(ECechaKlienta.NISKI);
        klient.setCechyKlienta(cechy);

        List<SubskrypcjaOB> subskrypcje = new ArrayList<>();
        SubskrypcjaOB subskrypcja1 = new SubskrypcjaOB();
        subskrypcja1.setNazwa("Pakiet Cebulowy");
        subskrypcje.add(subskrypcja1);
        klient.setSubskrypcje(subskrypcje);

        Set<TelefonOB> telefony = new HashSet<>();
        TelefonOB telefon1 = new TelefonOB();
        telefon1.setNumer("123123123");
        telefon1 = telefonRepository.save(telefon1);
        telefony.add(telefon1);
        TelefonOB telefon2 = new TelefonOB();
        telefon2.setNumer("321321321");
        telefon2 = telefonRepository.save(telefon2);
        telefony.add(telefon2);
        klient.setTelefony(telefony);

        klient = klientRepository.save(klient);

        EmailOB email1 = new EmailOB();
        email1.setAdres("anka666@dobramama.pl");
        email1.setKlient(klient);
        emailRepository.save(email1);
        EmailOB email2 = new EmailOB();
        email2.setAdres("ania352341@gmail.com");
        email2.setKlient(klient);
        emailRepository.save(email2);

        return klient;
    }

    @Override
    public List<ResponseDTO> query(RequestDTO aCrit) {
        return null;
    }
}
