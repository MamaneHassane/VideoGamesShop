package com.videogamesshop.vgs_package.service;

import com.videogamesshop.vgs_package.exceptions.VideoGameNotFoundException;
import com.videogamesshop.vgs_package.model.entities.VideoGame;
import com.videogamesshop.vgs_package.model.entities.VideoGameCopy;
import com.videogamesshop.vgs_package.model.records.CreateVideoGameRecord;
import com.videogamesshop.vgs_package.repository.VideoGameCopyRepository;
import com.videogamesshop.vgs_package.repository.VideoGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.videogamesshop.vgs_package.utilities.ImageUtils.compressImage;

@Service
@Transactional
public class VideoGameService {
    private final VideoGameRepository videoGameRepository;
    VideoGameCopyRepository videoGameCopyRepository;
    GameConsoleService gameConsoleService;
    @Autowired
    public VideoGameService(VideoGameRepository videoGameRepository, GameConsoleService gameConsoleService,VideoGameCopyRepository videoGameCopyRepository){
        this.videoGameRepository = videoGameRepository;
        this.gameConsoleService = gameConsoleService;
        this.videoGameCopyRepository = videoGameCopyRepository;
    }
    public VideoGame addVideoGame(VideoGame videoGame){
        return videoGameRepository.save(videoGame);
    }
    public VideoGame addVideoGame(String createVideoGameRecordAsJson, MultipartFile gameImageFile) throws IOException {
        CreateVideoGameRecord createVideoGameRecord  = CreateVideoGameRecord.fromJson(createVideoGameRecordAsJson);
        byte[] bytesArrayImage = compressImage(gameImageFile.getBytes());
        VideoGame videoGame = VideoGame.builder()
                .gameName(createVideoGameRecord.gameName())
                .description(createVideoGameRecord.description())
                .publicationDate(createVideoGameRecord.publicationDate())
                .copies(new ArrayList<VideoGameCopy>())
                .gameConsoles(gameConsoleService.craftVideoGameCopiesListFromNames(createVideoGameRecord.consoles()))
                .gameImage(bytesArrayImage)
                .build();
        return videoGameRepository.save(videoGame);
    }

    public static Optional<VideoGame> addCopyToGame(VideoGameCopy videoGameCopy, VideoGameRepository videoGameRepository) {
        Optional<VideoGame> theVideoGame = videoGameRepository.findById(videoGameCopy.getVideoGame().getId());
        theVideoGame.ifPresent(videoGame -> {
            List<VideoGameCopy> videoGameCopies = videoGame.getCopies();
            videoGameCopies.add(videoGameCopy);
            videoGame.setCopies(videoGameCopies);
            videoGameRepository.save(videoGame);
        });
        return theVideoGame;
    }

    public List<VideoGame> findAllVideoGame(){
        return videoGameRepository.findAll();
    }
    public VideoGame findVideoGameById(Long Id){
        return videoGameRepository.findById(Id).orElseThrow(()-> new VideoGameNotFoundException(Id));
    }
    public VideoGame updateVideoGameById(VideoGame updatedVideoGame, Long Id){
        return videoGameRepository.findById(Id).map(
                videoGame -> {
                    videoGame.setGameName(updatedVideoGame.getGameName());
                    videoGame.setDescription(updatedVideoGame.getDescription());
                    videoGame.setPublicationDate((updatedVideoGame.getPublicationDate()));
                    return videoGameRepository.save(videoGame);
                }
        ).orElseThrow(()-> new VideoGameNotFoundException(Id));
    }
    public void deleteVideoGameById(Long Id){
        videoGameRepository.deleteById(Id);
    }
}
