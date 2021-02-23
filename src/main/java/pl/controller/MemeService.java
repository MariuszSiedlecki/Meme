package pl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.model.Meme;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemeService {


    private final MemeRepository memeRepository;

    public void save(final Meme meme){
    memeRepository.save(meme);
    }
    public void save(List<Meme> meme){
        memeRepository.saveAll(meme);
    }

    public void delete(final Meme meme){
        memeRepository.delete(meme);
    }
    public void delete(Integer id){
        memeRepository.deleteById(id);
    }
    public Meme getMemeById(Integer id){
        return memeRepository.getOne(id);
    }
    public Set<Meme> getMemeList(){
        return memeRepository.findAll().stream().collect(Collectors.toSet());
    }
}
