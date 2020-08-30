package org.everton.rest.controller;
import org.everton.domain.entity.Video;
import org.everton.domain.repository.Videos;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/videos")
public class VideoController {

    private Videos videos;

    public VideoController(Videos videos) {
        this.videos = videos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Video save(@RequestBody @Valid Video video){
        return videos.save(video);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable Integer id ){
        videos.findById(id)
                .map(video -> {
                    videos.delete(video);
                    return video;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Video não encontrado") );

    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update( @PathVariable Integer id,
                        @RequestBody @Valid Video video){
        videos
                .findById(id)
                .map(videoExistente -> {
                    video.setId(videoExistente.getId());
                    videos.save(video);
                    return videoExistente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Video não encontrado") );
    }

    @GetMapping
    public List<Video> find(Video filtro ){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING );

        Example example = Example.of(filtro, matcher);
        return videos.findAll(example);
    }

}
