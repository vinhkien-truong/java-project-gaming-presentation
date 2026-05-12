package com.game.hyf.repository;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.game.hyf.model.Game;

public interface GameRepository extends JpaRepository<Game, UUID>{
    
}
