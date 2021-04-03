/*
 * File: ServiceCommentTour.java
 * author: David Villalobos
 * Date: 2021/04/02
 */

package com.getyourtour.service;

import com.getyourtour.dao.DaoCommentTour;
import com.getyourtour.model.CommentTour;

import java.util.List;

public class ServiceCommentTour {

    private final DaoCommentTour dao_commentTour = new DaoCommentTour();

    public CommentTour getCommentTour(Integer id){
        return dao_commentTour.get(id);
    }

    public List<CommentTour> getAllCommentTours(){
        return dao_commentTour.get();
    }

    public List<CommentTour> getCommentToursByTour(Integer id_tour){
        return dao_commentTour.getByTour(id_tour);
    }

    public List<CommentTour> getCommentToursByUser(Integer id_user){
        return dao_commentTour.getByUser(id_user);
    }

    public int addCommentTour(CommentTour commentTour){
        if(commentTour.getTour().getId() != null && commentTour.getUser().getId() != null){
            return -1;
        }
        return dao_commentTour.post(commentTour);
    }

    public int updateCommentTour(CommentTour c){
        return dao_commentTour.put(c);
    }

    public int deleteCommentTour(Integer id){
        return dao_commentTour.delete(id);
    }

    public int deleteCommentTourByUser(Integer id_tour, Integer id_user){
        return dao_commentTour.delete(id_tour, id_user);
    }

}
