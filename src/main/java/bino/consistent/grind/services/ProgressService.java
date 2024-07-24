package bino.consistent.grind.services;
import bino.consistent.grind.repositories.*;
import bino.consistent.grind.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgressService {
    @Autowired
    private ProgressRepository progressRepository;

    public Progress createProgress(Progress progress) {
        return progressRepository.save(progress);
    }

}
