package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component // делает Bean'ом
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatType = petTypeService.save(cat);

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

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Dmitriy");
        vet1.setLastName("Alexanov");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");

        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
