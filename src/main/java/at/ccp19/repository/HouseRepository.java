package at.ccp19.repository;

import at.ccp19.model.House;
import at.ccp19.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Integer> {

}
