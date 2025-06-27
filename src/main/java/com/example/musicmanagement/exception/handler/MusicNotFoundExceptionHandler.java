package com.example.musicmanagement.exception.handler;

import com.example.musicmanagement.exception.MusicNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class MusicNotFoundExceptionHandler {

    @ExceptionHandler(MusicNotFoundException.class)
    public String handlerMusicNotFoundException(MusicNotFoundException e,
                                         RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", "対象の楽曲が見つかりませんでした");
        return "redirect:/albums/" + e.getAlbumId();
    }
}
