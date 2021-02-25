package pl.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.model.Meme;

@Repository
public interface MemeRepository extends JpaRepository<Meme, Integer> {

}
