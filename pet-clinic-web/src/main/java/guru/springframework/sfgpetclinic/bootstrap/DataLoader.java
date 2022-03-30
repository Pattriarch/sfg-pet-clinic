package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component // делает Bean'ом
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry= specialityService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Daniil");
        owner1.setLastName("Safonov");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("8929281302");

        Pet daniilsPet = new Pet();
        daniilsPet.setPetType(savedDogType);
        daniilsPet.setOwner(owner1);
        daniilsPet.setBirthDate(LocalDate.now());
        daniilsPet.setName("Rex");
        owner1.getPets().add(daniilsPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Sergey");
        owner2.setLastName("Simonov");
        owner2.setAddress("13 Brickerel");
        owner2.setCity("Miami");
        owner2.setTelephone("841924827");

        Pet sergeysPet = new Pet();
        sergeysPet.setPetType(savedCatType);
        sergeysPet.setOwner(owner2);
        sergeysPet.setBirthDate(LocalDate.now());
        sergeysPet.setName("Misha");
        owner2.getPets().add(sergeysPet);

        ownerService.save(owner2);

        Visit catVisit = new Visit();
        catVisit.setPet(sergeysPet);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");

        visitService.save(catVisit);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Dmitriy");
        vet1.setLastName("Alexanov");
        vet1.getSpecialities().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialities().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
