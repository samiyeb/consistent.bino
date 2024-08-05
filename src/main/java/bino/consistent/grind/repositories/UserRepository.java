package bino.consistent.grind.repositories;
import bino.consistent.grind.entities.*;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {}
