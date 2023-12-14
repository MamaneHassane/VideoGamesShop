package com.videogamesshop.vgs_package.exceptions;

public class VideoGameCopyNotFoundException extends RuntimeException {
    public VideoGameCopyNotFoundException(Long Id){
        super("Video game copy with identifier "+ Id + " cannot be found...");
    }
}
